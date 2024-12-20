package com.neeyamo.approvalflow.serviceimpl;

import java.util.List;

import com.neeyamo.approvalflow.dao.ApiRegistrationDAO;
import com.neeyamo.approvalflow.dto.ApiRegistrationDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;
import com.neeyamo.approvalflow.repository.ApiRegistrationRepository;
import com.neeyamo.approvalflow.service.ApiRegistrationService;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ApiRegistrationServiceImpl implements ApiRegistrationService {

	@Autowired
	PrinmergeService prinmergeService;

	@Autowired
	ApiRegistrationRepository apiRegistrationRepository;

	@Autowired
	ApiRegistrationDAO apiRegistrationDAO;

	private static final Logger logger = LoggerFactory.getLogger(ApiRegistrationServiceImpl.class);

	// fetch api registered data
	@Override
	public ApiRegistrationDTO fetchApiRegisteredData(int currentPage, int itemsPerPageToDisplay, String textToSearch,
			List<String> columnsToSearch) {
		logger.info("Begins: fetchApiRegisteredData in class: {}", getClass().getName());
		ApiRegistrationDTO apiRegistrationDTO = new ApiRegistrationDTO();
		Integer totalCount = 0;
		try {
			if (textToSearch.equals("null") || textToSearch.equals("")) {
				Pageable pageable = PageRequest.of(currentPage - 1, itemsPerPageToDisplay,
						Sort.by("createdOn").descending());
				Page<TbApprovalApiReg> tbApprovalApiRegList = apiRegistrationRepository
						.fetchApiRegistartionData(pageable);
				totalCount = apiRegistrationRepository.countByIsActiveTrue();
				if (Util.isNotNullOrEmpty(tbApprovalApiRegList)) {
					apiRegistrationDTO.setTbApprovalApiRegList(tbApprovalApiRegList.getContent());
				} else {
					apiRegistrationDTO.setTbApprovalApiRegList(null);
				}
				if (Util.isNotNullOrEmpty(totalCount)) {
					apiRegistrationDTO.setTotalCount(totalCount);
				}
			} else {
				Pageable pageable = PageRequest.of(currentPage - 1, itemsPerPageToDisplay);
				apiRegistrationDTO = apiRegistrationDAO.globalSearch(pageable, textToSearch, columnsToSearch);
			}

		} catch (Exception e) {
			logger.info("Exception in method fetchApiRegisteredData - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "fetchApiRegisteredData", e,
					null);
		}
		logger.info("End: fetchApiRegisteredData in class: {}", getClass().getName());
		return apiRegistrationDTO;
	}

	// save or edit api registered data
	@Override
	public ResponseDTO saveApiRegData(ApiRegistrationDTO apiRegistrationDTO) {
		logger.info("Begins: saveApiRegData in class: {}", getClass().getName());
		ResponseDTO responseDTO = new ResponseDTO();
		List<TbApprovalApiReg> duplicateClientCheck = null;
		try {
			if (Util.isNullOrEmpty(apiRegistrationDTO.getApiRegId())) {
				duplicateClientCheck = apiRegistrationRepository.duplicateCheck(apiRegistrationDTO.getAppCode());
			} else if (Util.isNotNullOrEmpty(apiRegistrationDTO.getApiRegId())) {
				duplicateClientCheck = apiRegistrationRepository.duplicateCheckForEdit(apiRegistrationDTO.getAppCode(),
						apiRegistrationDTO.getApiRegId());
			}

			if (Util.isNullOrEmpty(duplicateClientCheck)) {
				responseDTO = apiRegistrationDAO.saveApiRegData(apiRegistrationDTO);
			} else if (Util.isNotNullOrEmpty(duplicateClientCheck)) {
				responseDTO.setDescription(Constants.ALREADYEXIST);
				responseDTO.setStatus(Constants.ALREADYEXIST);
			}
		} catch (Exception e) {
			logger.info("Exception in method saveApiRegData - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "saveApiRegData", e, null);
		}
		logger.info("END: saveApiRegData in class: {}", getClass().getName());
		return responseDTO;

	}

	// delete api registered data
	@Override
	public ResponseDTO deleteApiRegData(Integer apiRegId) {
		logger.info("Begins: deleteApiRegData in class: {}", getClass().getName());
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			apiRegistrationRepository.deleteApiRegData(apiRegId);

			responseDTO.setStatus("Deleted");
		} catch (Exception e) {
			logger.info("Exception in method deleteApiRegData - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "deleteApiRegData", e, null);
		}
		logger.info("END: deleteApiRegData in class: {}", getClass().getName());
		return responseDTO;
	}

}
