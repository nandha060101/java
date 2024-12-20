package com.neeyamo.approvalflow.webservices;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.neeyamo.approvalflow.dto.OutBoundResponseDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.utility.Constants;

@Component
public class ExternalWebService {

	@Autowired
	PrinmergeService prinmergeService;

	private static final Logger logger = LoggerFactory.getLogger(ExternalWebService.class);

	public ResponseDTO sendActionStatus(String actionApi, String userName, String password, OutBoundResponseDTO outBoundResponseDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<?> request = authorization(userName, password, outBoundResponseDTO);
			ResponseEntity<ResponseDTO> response = null;
			//logger.info("\nRequest for external webservice from sendActionStatus method :  request = > {}",
			//		Util.getComponentRequestBody(outBoundResponseDTO));
			response = restTemplate.exchange(actionApi, HttpMethod.POST, request, ResponseDTO.class);

			responseDTO = response.getBody();
		} catch (Exception e) {
			responseDTO.setStatus("Error");
			logger.info("Exception in method sendActionStatus - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "sendActionStatus", e, null);
		}
		return responseDTO;
	}

	public HttpEntity<Object> authorization(String userName, String password, OutBoundResponseDTO outBoundResponseDTO) {
		HttpEntity<Object> request = null;
		try {
			HttpHeaders headers = setHeader(userName, password);
			request = new HttpEntity<>(outBoundResponseDTO, headers);
		} catch (Exception e) {
			logger.info("Exception in method authorization - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "authorization", e, null);
		}
		return request;
	}

	HttpHeaders setHeader(String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		String plainCreds = username + Constants.COLON + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add(Constants.AUTHORIZATION, Constants.BASIC + base64Creds);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	public ResponseDTO sendActionStatus(String actionApi, String bearerToken, OutBoundResponseDTO outBoundResponseDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
			ResponseEntity<ResponseDTO> response = null;
			//logger.info("\nRequest for external webservice from sendActionStatus method :  request = > {}",
			//		Util.getComponentRequestBody(outBoundResponseDTO));
			headers.set("Authorization", "Bearer " + bearerToken);
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<?> request = new HttpEntity<>(outBoundResponseDTO,headers);
			response = restTemplate.exchange(actionApi, HttpMethod.POST, request, ResponseDTO.class);
			responseDTO = response.getBody();
		} catch (Exception e) {
			responseDTO.setStatus("Error");
			logger.info("Exception in method sendActionStatus - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "sendActionStatus", e, null);
		}
		return responseDTO;
	}
}
