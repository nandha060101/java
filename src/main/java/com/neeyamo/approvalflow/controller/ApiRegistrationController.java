package com.neeyamo.approvalflow.controller;

import java.util.List;

import com.neeyamo.approvalflow.dto.ApiRegistrationDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.service.ApiRegistrationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("approvalflow/externalapi")
public class ApiRegistrationController {

	@Autowired
	ApiRegistrationService apiRegistrationService;

	private static final Logger logger = LoggerFactory.getLogger(ApiRegistrationController.class);

	// fetch api registration datas
	@GetMapping(value = "tenants/{tenantsId}/registerApi/register")
	public ResponseEntity<Object> fetchApiRegisteredData(
			@PathVariable(value = "tenantsId", required = true) String tenantsId,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "itemsPerPageToDisplay") int itemsPerPageToDisplay,
			@RequestParam(value = "textToSearch") String textToSearch,
			@RequestParam(value = "columnsToSearch") List<String> columnsToSearch) {
		ApiRegistrationDTO apiRegistrationDTO = new ApiRegistrationDTO();
		try {
			apiRegistrationDTO = apiRegistrationService.fetchApiRegisteredData(currentPage, itemsPerPageToDisplay,
					textToSearch, columnsToSearch);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(apiRegistrationDTO, HttpStatus.OK);
		
	}

	// save api registration datas
	@PostMapping(value = "tenants/{tenantsId}/registerApi/register")
	public ResponseEntity<Object> saveApiRegData(@PathVariable(value = "tenantsId", required = true) String tenantsId,
			@RequestBody ApiRegistrationDTO apiRegistrationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = apiRegistrationService.saveApiRegData(apiRegistrationDTO);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	// delete api registration datas
	@DeleteMapping(value = "tenants/{tenantsId}/registerApi/register/{apiRegId}")
	public ResponseEntity<Object> deleteApiRegData(@PathVariable(value = "tenantsId", required = true) String tenantsId,
			@PathVariable(value = "apiRegId", required = true) Integer apiRegId) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = apiRegistrationService.deleteApiRegData(apiRegId);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
}
