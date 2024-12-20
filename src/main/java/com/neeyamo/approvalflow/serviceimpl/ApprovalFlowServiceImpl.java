package com.neeyamo.approvalflow.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import com.neeyamo.approvalflow.dao.ApprovalFlowDAO;
import com.neeyamo.approvalflow.dto.ApprovalFlowDTO;
import com.neeyamo.approvalflow.dto.EssCountDTO;
import com.neeyamo.approvalflow.dto.OutBoundResponseDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.dto.SessionDTO;
import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;
import com.neeyamo.approvalflow.pojo.TbApprovalTransLog;
import com.neeyamo.approvalflow.repository.ApiRegistrationRepository;
import com.neeyamo.approvalflow.repository.ApprovalFlowTransLogRepository;
import com.neeyamo.approvalflow.security.AppUser;
import com.neeyamo.approvalflow.service.ApprovalFlowService;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;
import com.neeyamo.approvalflow.webservices.ExternalWebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApprovalFlowServiceImpl implements ApprovalFlowService {

	@Autowired
	ApprovalFlowDAO approvalFlowDAO;
	@Autowired
	PrinmergeService prinmergeService;
	@Autowired
	ApprovalFlowTransLogRepository approvalFlowTransLogRepository;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	ApiRegistrationRepository apiRegistrationRepository;
	@Autowired
	ExternalWebService externalWebService;

	private static final Logger logger = LoggerFactory.getLogger(ApprovalFlowServiceImpl.class);

	@Override
	public ApprovalFlowDTO insertDataFlowTransactionLog(ApprovalFlowDTO approvalFlowDTO) {
		logger.info("Begins: insertDataFlowTransactionLog in class: {}", getClass().getName());
		try {
			if (Util.isNotNullOrEmpty(approvalFlowDTO.getApplicationCode())) {
				if (Util.isNotNullOrEmpty(approvalFlowDTO.getClientCode())) {
					List<TbApprovalTransLog> oldList = approvalFlowTransLogRepository.fetchApprovalFlowTransLog(
							approvalFlowDTO.getTransactionId(), approvalFlowDTO.getApplicationCode());
					if (Util.isNotNullOrEmpty(oldList)) {
						approvalFlowDTO.setApprovalTransLogId(oldList.get(0).getApprovalTransLogId());
					}
					approvalFlowDAO.insertApprovalFlowTransactionsLogs(approvalFlowDTO);
					approvalFlowDTO.setResponseString(Constants.SUCCESS);

				} else {
					// client is missing
					approvalFlowDTO.setResponseString("Client data is missing");
				}
			} else {
				// application is missing
				approvalFlowDTO.setResponseString("Application data is missing");
			}

		} catch (Exception e) {
			logger.info("Exception in method insertDataFlowTransactionLog - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "insertDataFlowTransactionLog",
					e, null);
			approvalFlowDTO.setResponseString(Constants.ERROR);
		}
		logger.info("End: insertDataFlowTransactionLog in class: {}", getClass().getName());
		return approvalFlowDTO;
	}

	// 12/07/2021 Saravana Stalled workflow changes
	// transaction log bulk insert method
	@Override
	public ApprovalFlowDTO insertDataFlowTransactionLogBulk(ApprovalFlowDTO approvalFlowDTO) {
		logger.info("Begins: insertDataFlowTransactionLogBulk in class: {}", getClass().getName());
		List<String> transactionList = new ArrayList<>();
		try {
			if (Util.isNotNullOrEmpty(approvalFlowDTO.getInputTransactionList())) {
				for (Iterator<TbApprovalTransLog> iterator = approvalFlowDTO.getInputTransactionList()
						.iterator(); iterator.hasNext();) {
					TbApprovalTransLog transactionDto = iterator.next();
					if (Util.isNotNullOrEmpty(transactionDto.getTransactionId())) {
						approvalFlowDTO.setAppEvents(transactionDto.getAppEvents());
						approvalFlowDTO.setTransactionId(transactionDto.getTransactionId());
						approvalFlowDTO.setStatus(transactionDto.getStatus());
						approvalFlowDTO.setEmployeeId(transactionDto.getEmployeeId());
						approvalFlowDTO.setApplicationData(transactionDto.getApplicationData());
						approvalFlowDTO.setAssignedBy(transactionDto.getAssignedBy());
						approvalFlowDTO.setAssignedTo(transactionDto.getAssignedTo());
						approvalFlowDTO.setCreatedBy(transactionDto.getCreatedBy());
						insertDataFlowTransactionLog(approvalFlowDTO);
						transactionList.add(transactionDto.getTransactionId());
					} else {
						// transaction Id is missing
						approvalFlowDTO.setResponseString("Transaction data is missing");
					}
				}
				approvalFlowDTO.setTransactionList(transactionList);
			}

		} catch (Exception e) {
			logger.info("Exception in method insertDataFlowTransactionLogBulk - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(),
					"insertDataFlowTransactionLogBulk", e, null);
		}
		logger.info("End: insertDataFlowTransactionLogBulk in class: {}", getClass().getName());
		return approvalFlowDTO;
	}

	// find application count
	@Override
	public EssCountDTO getAppCount(String option, String appName,String assignedTo,String assignedBy) {
		logger.info("Begins: getAppCount in class: {}", getClass().getName());
		EssCountDTO essCountDTO = new EssCountDTO();

		essCountDTO = approvalFlowDAO.getAppCount(option,assignedTo,assignedBy);

		logger.info("END: getAppCount in class: {}", getClass().getName());
		return essCountDTO;
	}

	// fetch Team approval datas
	@Override
	public ApprovalFlowDTO fetchApprovalRecords(int noOfRecords, int pageNumber, String elementToSearch,
			List<String> columnsToSearch, String appCode, String appEvent, List<String> jsonData,String assignedTo,String assignedBy) {
		logger.info("Begins: fetchApprovalRecords in class: {}", getClass().getName());
		ApprovalFlowDTO approvalFlowDTO = new ApprovalFlowDTO();

		approvalFlowDTO = approvalFlowDAO.fetchApprovalRecords(noOfRecords, pageNumber, elementToSearch,
				columnsToSearch, appCode, appEvent, jsonData,assignedTo,assignedBy);

		logger.info("END: fetchApprovalRecords in class: {}", getClass().getName());
		return approvalFlowDTO;
	}

	// update status from outbound response
	@Override
	public ApprovalFlowDTO updateStatus(OutBoundResponseDTO outBoundResponseDTO, String bearerToken) {
		ApprovalFlowDTO approvalFlowDTO = new ApprovalFlowDTO();
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String apiString = null;
			Util.setLogs("BEGIN: updateStatus in class {}" + getClass().getName());

			if (Util.isNotNullOrEmpty(outBoundResponseDTO.getApplicationCode())) {
				TbApprovalApiReg tbApprovalApiReg = apiRegistrationRepository
						.fetchApplicationsApiDatas(outBoundResponseDTO.getApplicationCode());

				if (Util.isNotNullOrEmpty(tbApprovalApiReg) && Util.isNotNullOrEmpty(tbApprovalApiReg.getActionApi())) {
					if (Util.isNotNullOrEmpty(tbApprovalApiReg.getUserName())
							&& Util.isNotNullOrEmpty(tbApprovalApiReg.getPassword())) {
						apiString = Util.replaceApiPlaceHolder(tbApprovalApiReg.getActionApi(),
								outBoundResponseDTO.getClientCode());
						if (Util.isNotNullOrEmpty(apiString)) {
							responseDTO = externalWebService.sendActionStatus(apiString, tbApprovalApiReg.getUserName(),
									tbApprovalApiReg.getPassword(), outBoundResponseDTO);
						} else {
							approvalFlowDTO.setStatus(Constants.APINOTREGISTERED);
						}
					} else {
						apiString = Util.replaceApiPlaceHolder(tbApprovalApiReg.getActionApi(),
								outBoundResponseDTO.getClientCode());
						if (Util.isNotNullOrEmpty(apiString)) {
							responseDTO = externalWebService.sendActionStatus(apiString,bearerToken, outBoundResponseDTO);
						} else {
							approvalFlowDTO.setStatus(Constants.APINOTREGISTERED);
						}
						
					}
				} else {
					approvalFlowDTO.setStatus(Constants.APINOTREGISTERED);
				}
			}
			if (Util.isNotNullOrEmpty(responseDTO) && Util.isNotNullOrEmpty(responseDTO.getStatus())
					&& Util.isNotNullOrEmpty(responseDTO.getTransactionList())) {
				if (responseDTO.getStatus().equals("Nudged")) {
					approvalFlowDTO.setStatus("Nudged");
				} else {
					approvalFlowTransLogRepository.updateStatus(responseDTO.getTransactionList(),
							responseDTO.getStatus(),outBoundResponseDTO.getApplicationCode());
					approvalFlowDTO.setStatus("Success");
				}
			} else if (!approvalFlowDTO.getStatus().equals("ApiNotRegistered")) {
				approvalFlowDTO.setStatus("Error");
			}
			Util.setLogs("END: updateStatus in class {}" + getClass().getName());
		} catch (Exception e) {
			Util.setLogsException("Exception in method updateStatus ", e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "updateStatus", e, null);
			approvalFlowDTO.setStatus("Error");
		}
		return approvalFlowDTO;
	}

	public SessionDTO getSessionVariables() {
		Authentication authenticationSession = SecurityContextHolder.getContext().getAuthentication();
		AppUser httpSession = (AppUser) authenticationSession.getPrincipal();
		return httpSession.getSessionDTOFromWidget();
	}

	@Override
	public TbApprovalApiReg getApiRoutePathDetails(String applicationCode) {
		List<TbApprovalApiReg> apiRegistrationList =new ArrayList<>();
		TbApprovalApiReg apiRegistrationDTO = new TbApprovalApiReg();
		try {
			apiRegistrationList=apiRegistrationRepository.duplicateCheck(applicationCode);
			if(Util.isNotNullOrEmpty(apiRegistrationList)) {
				apiRegistrationDTO=apiRegistrationList.get(0);
			}
		}catch (Exception e) {
			Util.setLogsException("Exception in method updateStatus ", e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "getApiRoutePathDetails", e, null);
		}
		return apiRegistrationDTO;
	}

	///28-Nov-2024  Peter ALphonse A(ET1348)  hide tab
	@Override
	public EssCountDTO getTabAppCount(String option, String appName, String assignedTo, String assignedBy) {
		logger.info("Begins: getAppCount in class: {}", getClass().getName());
		EssCountDTO essCountDTO = new EssCountDTO();
		EssCountDTO essCountDTOReqest = new EssCountDTO();

		essCountDTO = approvalFlowDAO.getAppCount(option,assignedTo,null);
		if(!Util.isNotNullOrEmpty(essCountDTO) || !Util.isNotNullOrEmpty(essCountDTO.getEssAppEventsDTO())) {
			essCountDTOReqest.setOnMeRequest(false);
		}else {
			essCountDTOReqest.setOnMeRequest(true);
		}
	
		essCountDTO = approvalFlowDAO.getAppCount(option,null,assignedBy);
		
		if(!Util.isNotNullOrEmpty(essCountDTO) || !Util.isNotNullOrEmpty(essCountDTO.getEssStatus())) {
			essCountDTOReqest.setMyRequest(false);
		}else {
			essCountDTOReqest.setMyRequest(true);
		}
		essCountDTO.setOnMeRequest(essCountDTOReqest.getOnMeRequest());
		essCountDTO.setMyRequest(essCountDTOReqest.getMyRequest());
		logger.info("END: getAppCount in class: {}", getClass().getName());
		return essCountDTO;
	}
}
