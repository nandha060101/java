package com.neeyamo.approvalflow.webservices;

import com.neeyamo.approvalflow.dto.MDMServiceDTO;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.utility.Constants;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MDMWebService {

	@Autowired
	PrinmergeService prinmergeService;

	private static final Logger logger = LoggerFactory.getLogger(MDMWebService.class);

	@Value("${mdmWebService.URL}")
	private String mdmURL;

	@Value("${mdmWebService.username}")
	private String username;

	@Value("${mdmWebService.password}")
	private String password;

	@Value("${mdmWebService.fetchEmployeeURL}")
	private String mdmWebServiceEmployeeDetails;

	public MDMServiceDTO fetchfromMdm(String clientName, String employeeId) {
		MDMServiceDTO mdmservicedto = null;
		try {
			String clientData = clientName.toLowerCase();
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<?> request = authorization(username, password);
			ResponseEntity<MDMServiceDTO> response = null;
			response = restTemplate.exchange(mdmURL + clientData + mdmWebServiceEmployeeDetails
					+ "?restrictions=employee.employeeGlobalId='" + employeeId + "'" , HttpMethod.GET,
					request, MDMServiceDTO.class);

			mdmservicedto = response.getBody();
		} catch (Exception e) {
			logger.info("Exception in method fetchfromMdm - Line Number: {} || {}",
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
			logger.info(e.getMessage(), e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "fetchfromMdm", e, null);
		}
		return mdmservicedto;
	}

	public HttpEntity<Object> authorization(String userName, String password) {
		HttpEntity<Object> request = null;
		try {
			HttpHeaders headers = setHeader(userName, password);
			request = new HttpEntity<>(headers);
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
}
