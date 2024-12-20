package com.neeyamo.approvalflow.controller;

import com.neeyamo.approvalflow.dto.NeosuiteDTO;
import com.neeyamo.approvalflow.service.CommonApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("approvalflow/api")
public class CommonApiController {

	@Autowired
	CommonApiService commonApiService;

	private static final Logger logger = LoggerFactory.getLogger(CommonApiController.class);

	// fetch api registration datas
	@GetMapping(value = "tenants/{tenantsId}/neosuite")
	public ResponseEntity<Object> fetchAppDetails() {
		NeosuiteDTO applicationDetails = new NeosuiteDTO();
		try {
			applicationDetails = commonApiService.fetchAppDetails();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(applicationDetails, HttpStatus.OK);
	}
}
