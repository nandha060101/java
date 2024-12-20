package com.neeyamo.approvalflow.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neeyamo.approvalflow.dto.ApiRegistrationDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.dto.SessionDTO;
import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;
import com.neeyamo.approvalflow.repository.ApiRegistrationRepository;
import com.neeyamo.approvalflow.security.AppUser;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

@Transactional
@Repository
public class ApiRegistrationDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ApiRegistrationRepository apiRegistrationRepository;

	private static final Logger logger = LoggerFactory.getLogger(ApiRegistrationDAO.class);

	public ResponseDTO saveApiRegData(ApiRegistrationDTO apiRegistrationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		TbApprovalApiReg tbApprovalApiReg = new TbApprovalApiReg();

		if (Util.isNotNullOrEmpty(apiRegistrationDTO.getApiRegId())) {
		    tbApprovalApiReg = entityManager.find(TbApprovalApiReg.class,
					apiRegistrationDTO.getApiRegId());
			tbApprovalApiReg.setUpdatedBy(
					Util.isNotNullOrEmpty(apiRegistrationDTO.getUpdatedBy()) ? apiRegistrationDTO.getUpdatedBy() : null);

			tbApprovalApiReg.setUpdatedOn(new Date());
			responseDTO.setDescription(Constants.UPDATE);

		}
		else {
			tbApprovalApiReg.setCreatedBy(Util.isNotNullOrEmpty(apiRegistrationDTO.getCreatedBy())
					? apiRegistrationDTO.getCreatedBy()
					: null);
			tbApprovalApiReg.setCreatedOn(new Date());
			responseDTO.setDescription(Constants.SAVE);
		}
		tbApprovalApiReg.setApplicationCode(
				Util.isNotNullOrEmpty(apiRegistrationDTO.getAppCode()) ? apiRegistrationDTO.getAppCode() : null);
		
		tbApprovalApiReg.setApplicationName(
				Util.isNotNullOrEmpty(apiRegistrationDTO.getAppName()) ? apiRegistrationDTO.getAppName() : null);
		
		tbApprovalApiReg.setActionApi(
				Util.isNotNullOrEmpty(apiRegistrationDTO.getActionApi()) ? apiRegistrationDTO.getActionApi() : null);
		
		tbApprovalApiReg.setUserName(
				Util.isNotNullOrEmpty(apiRegistrationDTO.getUserName()) ? apiRegistrationDTO.getUserName() : null);
		
		tbApprovalApiReg.setPassword(
				Util.isNotNullOrEmpty(apiRegistrationDTO.getPassword()) ? apiRegistrationDTO.getPassword() : null);
		tbApprovalApiReg.setRoutingPath(
				Util.isNotNullOrEmpty(apiRegistrationDTO.getRoutingPath()) ? apiRegistrationDTO.getRoutingPath() : null);
		tbApprovalApiReg.setIsActive(true);
		
		apiRegistrationRepository.save(tbApprovalApiReg);
		responseDTO.setStatus(Constants.SUCCESS);
		return responseDTO;
	}
	
	public ApiRegistrationDTO globalSearch(Pageable pageable, String textToSearch, List<String> columnsToSearch) {
		ApiRegistrationDTO apiRegistrationDTO = new ApiRegistrationDTO();
		
		Query query = entityManager.createQuery(String.valueOf(fetchSearchQuery(textToSearch, columnsToSearch)),
				TbApprovalApiReg.class);
		apiRegistrationDTO.setTotalCount(query.getResultList().size());
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		apiRegistrationDTO.setTbApprovalApiRegList(query.getResultList());
		return apiRegistrationDTO;
	}
	
	private Object fetchSearchQuery(String textToSearch, List<String> columnsToSearch) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT p FROM TbApprovalApiReg p WHERE (");
		int columnListSize = columnsToSearch.size();
		for (int i = 0; i < columnListSize; i++) {
			queryString.append(
					"p." + columnsToSearch.get(i) + " " + "like" + " " + "'" + "%" + textToSearch + "%" + "'" + " ");
			if (columnsToSearch.size() != i + 1) {
				queryString.append("or" + " ");
			}
		}
		queryString.append(") AND p.isActive = 1 ORDER BY p.createdOn DESC");
		return queryString;

	}
	
	public SessionDTO getSessionVariables() {
		Authentication authenticationSession = SecurityContextHolder.getContext().getAuthentication();
		AppUser httpSession = (AppUser) authenticationSession.getPrincipal();
		return httpSession.getSessionDTOFromWidget();
	}

}
