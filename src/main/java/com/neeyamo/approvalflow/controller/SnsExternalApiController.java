//<copyright from='2020' to='2050' company=’Neeyamoworks Enterprise Solution.'> 

/// Copyright (c) Neeyamoworks Enterprise Solution. All Rights Reserved. 
/// Private/Proprietary: No disclosure outside Neeyamoworks Enterprise Solution except by 
/// written agreement. 
/// </copyright> /// /// File History /// $File: AbsenceSplitTransactionController.Java. 
/// $Date: Feb 15, 2024
/// $Author: Guruprasath
//// Modify 
/// Tag       Date           ModifiedBy     		Description 
/// 1         21-Aug-24      Guruprasath         	SNS integration for client creation
package com.neeyamo.approvalflow.controller;

import java.util.LinkedHashMap;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.dto.SnsDTO;
import com.neeyamo.approvalflow.hibernateutility.DBContextHolder;
import com.neeyamo.approvalflow.hibernateutility.DBTypeEnum;
import com.neeyamo.approvalflow.service.AutoDBCreationService;
import com.neeyamo.approvalflow.service.CommonApiService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("approvalflow/external-api")
public class SnsExternalApiController {
	@Autowired
	AutoDBCreationService autoDBCreationService;

	@Autowired
	CommonApiService commonApiService;

	static final Logger LOG = LogManager.getLogger(SnsExternalApiController.class.getName());

	// 21-Aug-24 - Guruprasath - SNS integration for client creation
	@SuppressWarnings("rawtypes")
	@PostMapping(value = { "tenants/root/client-creation" })
	public ResponseEntity<Object> createClientDataBase(@RequestBody Object snsRequest) {
		LOG.info("SnsExternalApiController : insertClientDetails method called");
		LOG.info("snsRequest ->{}", Util.getComponentRequestBody(snsRequest));
		LinkedHashMap dt = (LinkedHashMap) snsRequest;
		if (dt.containsKey("Type")) {
			RestTemplate restTemplate = new RestTemplate();
			LOG.info("SnsExternalApiControllersubscriptioncalled");
			String url = (String) dt.get("SubscribeURL");
			LOG.info("SubscribeURL ->{}", url);
			String res = restTemplate.getForObject(url, String.class);
			LOG.info("Subscription Confirmation Response  ->{}", res);
			return new ResponseEntity<>("Thank you, subcription confirmation called", HttpStatus.OK);

		} else {
			createSchema(snsRequest);
			return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
		}
	}

	// 21-Aug-24 - Guruprasath - SNS integration for client creation
	private void createSchema(Object snsRequest) {
		LOG.info("SnsExternalApiController: database check called ");
		DBContextHolder.setCurrentDb(DBTypeEnum.MAIN);
		ObjectMapper objectMapper = new ObjectMapper();
		SnsDTO snsDTO = objectMapper.convertValue(snsRequest, SnsDTO.class);
		if (Util.isNotNullOrEmpty(snsDTO) && Util.isNotNullOrEmpty(snsDTO.getSubscribedProducts())
				&& Util.isNotNullOrEmpty(snsDTO.getSubscribedProducts().getApplicationList())) {
			Optional<SnsDTO> result = snsDTO.getSubscribedProducts().getApplicationList().stream()
					.filter(app -> app.getAppCode().equals(Constants.APP_CODE)).findFirst();
			if (result.isPresent()) {
				ResponseDTO response = autoDBCreationService.autoDB(snsDTO.getSubscribedProducts().getClientCode(),
						false);
				SnsDTO applicationData = result.get();
				commonApiService.updateCustomerOnboarding(response, snsDTO, applicationData);
			}
		}
	}
}
