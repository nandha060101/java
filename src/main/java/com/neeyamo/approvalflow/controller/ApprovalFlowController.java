package com.neeyamo.approvalflow.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neeyamo.approvalflow.dto.ApprovalFlowDTO;
import com.neeyamo.approvalflow.dto.EssCountDTO;
import com.neeyamo.approvalflow.dto.OutBoundResponseDTO;
import com.neeyamo.approvalflow.hibernateutility.DBContextHolder;
import com.neeyamo.approvalflow.hibernateutility.DBTypeEnum;
import com.neeyamo.approvalflow.hibernateutility.TenantContext;
import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;
import com.neeyamo.approvalflow.service.ApprovalFlowService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

@RestController
@RequestMapping("approvalflow/api")
public class ApprovalFlowController {

	@Autowired
	ApprovalFlowService approvalFlowService;

	private static final Logger logger = LoggerFactory.getLogger(ApprovalFlowController.class);

	// fetch Application Count
	///26-Seb-2024  Peter ALphonse A(ET1348)  New UI added
	@GetMapping(value = "tenants/{tenantsId}/approvals/app-count")
	public ResponseEntity<Object> getAppCount(@PathVariable(value = "tenantsId", required = true) String tenantsId,
			@RequestParam String option, @RequestParam String appName,
			@RequestParam(value = "assignedTo") String assignedTo,
			@RequestParam(value = "assignedBy") String assignedBy) {
		EssCountDTO essCountDTO = new EssCountDTO();
		try {
			DBContextHolder.setCurrentDb(DBTypeEnum.READ1);
			TenantContext.setCurrentTenant(Constants.TENENT_PREFIX + tenantsId.toLowerCase());
			essCountDTO = approvalFlowService.getAppCount(option, appName,assignedTo,assignedBy);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(essCountDTO, HttpStatus.OK);
	}
	
	// fetch Application Count
		///28-Nov-2024  Peter ALphonse A(ET1348)  hide tab
		@GetMapping(value = "tenants/{tenantsId}/approvals/app-tab-count")
		public ResponseEntity<Object> getTabAppCount(@PathVariable(value = "tenantsId", required = true) String tenantsId,
				@RequestParam String option, @RequestParam String appName,
				@RequestParam(value = "assignedTo") String assignedTo,
				@RequestParam(value = "assignedBy") String assignedBy) {
			EssCountDTO essCountDTO = new EssCountDTO();
			try {
				DBContextHolder.setCurrentDb(DBTypeEnum.READ1);
				TenantContext.setCurrentTenant(Constants.TENENT_PREFIX + tenantsId.toLowerCase());
				essCountDTO = approvalFlowService.getTabAppCount(option, appName,assignedTo,assignedBy);
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
			return new ResponseEntity<>(essCountDTO, HttpStatus.OK);
		}

	// fetch Team Approvals datas
	///26-Seb-2024  Peter ALphonse A(ET1348)  New UI added
	@GetMapping(value = "tenants/{tenantsId}/approvals/team-approvals")
	public ResponseEntity<Object> fetchApprovalRecords(
			@PathVariable(value = "tenantsId", required = true) String tenantsId, @RequestParam int noOfRecords,
			@RequestParam int pageNumber, @RequestParam(value = "elementToSearch") String elementToSearch,
			@RequestParam(value = "columnsToSearch") List<String> columnsToSearch,
			@RequestParam(value = "appCode") String appCode, @RequestParam(value = "appEvent") String appEvent,
			@RequestParam(value = "jsonData") List<String> jsonData,
			@RequestParam(value = "assignedTo") String assignedTo,
			@RequestParam(value = "assignedBy") String assignedBy) {
		ApprovalFlowDTO approvalFlowDTO = new ApprovalFlowDTO();
		try {
			DBContextHolder.setCurrentDb(DBTypeEnum.READ1);
			TenantContext.setCurrentTenant(Constants.TENENT_PREFIX + tenantsId.toLowerCase());
			approvalFlowDTO = approvalFlowService.fetchApprovalRecords(noOfRecords, pageNumber, elementToSearch,
					columnsToSearch, appCode, appEvent, jsonData,assignedTo,assignedBy);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(approvalFlowDTO, HttpStatus.OK);
	}

	// update status using outbound response
	@PutMapping(value = "tenants/{tenantsId}/approvals/update")
	public ResponseEntity<Object> updateStatus(@PathVariable(value = "tenantsId", required = true) String tenantsId,
			@RequestBody OutBoundResponseDTO outBoundResponseDTO,@RequestHeader(name = "Authorization") String bearerToken) {
		ApprovalFlowDTO approvalFlowDTO = new ApprovalFlowDTO();
		try {
			DBContextHolder.setCurrentDb(DBTypeEnum.READ1);
			TenantContext.setCurrentTenant(Constants.TENENT_PREFIX + tenantsId.toLowerCase());
			if (Util.isNotNullOrEmpty(outBoundResponseDTO)) {
				approvalFlowDTO = approvalFlowService.updateStatus(outBoundResponseDTO,bearerToken.replace("Bearer ", ""));
			} else {
				approvalFlowDTO.setStatus("Error");
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(approvalFlowDTO, HttpStatus.OK);
	}
	
	
	// fetch Application Route path
		@GetMapping(value = "tenants/{tenantsId}/approvals/route-path")
		public ResponseEntity<Object> fetchApiRoutePathDetails(@PathVariable(value = "tenantsId", required = true) String tenantsId,
				 @RequestParam String applicationCode) {
			TbApprovalApiReg apiRegistrationDto = new TbApprovalApiReg();
			try {
				DBContextHolder.setCurrentDb(DBTypeEnum.READ1);
				TenantContext.setCurrentTenant(Constants.TENENT_DEFAULT);
				apiRegistrationDto = approvalFlowService.getApiRoutePathDetails(applicationCode);
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
			return new ResponseEntity<>(apiRegistrationDto, HttpStatus.OK);
		}
}
