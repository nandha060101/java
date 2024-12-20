package com.neeyamo.approvalflow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neeyamo.approvalflow.dto.ApprovalFlowDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.hibernateutility.DBContextHolder;
import com.neeyamo.approvalflow.hibernateutility.DBTypeEnum;
import com.neeyamo.approvalflow.hibernateutility.TenantContext;
import com.neeyamo.approvalflow.service.ApprovalFlowService;
import com.neeyamo.approvalflow.service.AutoDBCreationService;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

@RestController
@RequestMapping("approvalflow/externalapi")
public class ExternalApiController {

	@Autowired
	ExecutorService executor;
	@Autowired
	ApprovalFlowService approvalFlowService;
	@Autowired
	PrinmergeService prinmergeService;
	@Autowired
	AutoDBCreationService autoDBCreationService;

	private static final Logger logger = LoggerFactory.getLogger(ExternalApiController.class);

	@PostMapping(value = "tenants/{tenantsId}/save-transaction-logs")
	public ResponseEntity<Object> insertDataFlowTransactionLogApi(
			@PathVariable(value = "tenantsId", required = true) String tenantsId,
			@RequestBody ApprovalFlowDTO approvalFlowDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		logger.info("Begin : insertDataFlowTransactionLog");
		try {
			if (Util.isNotNullOrEmpty(approvalFlowDTO.getApplicationCode())
					&& Util.isNotNullOrEmpty(tenantsId)) {
				DBContextHolder.setCurrentDb(DBTypeEnum.MAIN);
				// running thread in executor service for load fix
				TenantContext.setCurrentTenant(Constants.TENENT_PREFIX + tenantsId.toLowerCase());
				approvalFlowDTO = insertTransLog(approvalFlowDTO);
			} else {
				// application client is missing
				approvalFlowDTO.setResponseString("Application/Client data is missing");
			}
			responseDTO.setDescription(approvalFlowDTO.getResponseString());
			responseDTO.setPayload(approvalFlowDTO.getTransactionList());
		} catch (Exception e) {
			logger.info("Exception in method insertDataFlowTransactionLog - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			responseDTO.setStatus(Constants.ERROR);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "insertDataFlowTransactionLog",
					e, null);
		}
		logger.info("End : insertDataFlowTransactionLog");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	// 14/03/2023 Saravana approval flow insert
	public ApprovalFlowDTO insertTransLog(ApprovalFlowDTO approvalFlowDTO) {
		List<String> transactionList = new ArrayList<>();
		try {
			if (Util.isNullOrEmpty(approvalFlowDTO.getInputTransactionList())) {
				if (Util.isNotNullOrEmpty(approvalFlowDTO.getTransactionId())) {
					approvalFlowService.insertDataFlowTransactionLog(approvalFlowDTO);
					transactionList.add(approvalFlowDTO.getTransactionId());
					approvalFlowDTO.setTransactionList(transactionList);
				} else {
					// transaction Id is missing
					approvalFlowDTO.setResponseString("Transaction data is missing");
				}
			} else {
				// Bulk insert
				approvalFlowService.insertDataFlowTransactionLogBulk(approvalFlowDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in method insertTransLog - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "insertTransLog", e, null);
		}
		return approvalFlowDTO;
	}

	// Auto DB creation
	@GetMapping(value = "tenants/{tenantsId}/auto-db")
	public ResponseEntity<Object> autoDB(@PathVariable(value = "tenantsId", required = true) String tenantsId) {
		DBContextHolder.setCurrentDb(DBTypeEnum.MAIN);
		ResponseDTO response = autoDBCreationService.autoDB(tenantsId, false);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
