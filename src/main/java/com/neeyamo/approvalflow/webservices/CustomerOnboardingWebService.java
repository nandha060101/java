package com.neeyamo.approvalflow.webservices;

import java.util.Arrays;

import com.neeyamo.approvalflow.dto.SnsDTO;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerOnboardingWebService {

	@Value("${customerOnboarding.URL}")
	String basicURL;
	@Value("${customerOnboarding.username}")
	String snsUserName;
	@Value("${customerOnboarding.password}")
	String snsPassword;
	static final Logger LOG = LogManager.getLogger(CustomerOnboardingWebService.class.getName());

	// 21-Aug-24 - Guruprasath - customer onboarding api call
	public void sendResponseToCustomerOnboarding(SnsDTO snsDTO) {
		try {
			RestTemplate restTemplate1 = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			String plainCred = snsUserName + Constants.COLON + snsPassword;
			byte[] plainCredsByte = plainCred.getBytes();
			byte[] base64CredsByte = Base64.encodeBase64(plainCredsByte);
			String base64Cred = new String(base64CredsByte);
			header.add(Constants.AUTHORIZATION, Constants.BASIC + base64Cred);
			header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			header.setContentType(MediaType.APPLICATION_JSON);
			LOG.info("\nRequest for sendResponse from CustomerOnboardingWebService :  request = > {}",
					Util.getComponentRequestBody(snsDTO));
			ResponseEntity<String> response = restTemplate1.exchange(basicURL, HttpMethod.POST,
					new HttpEntity<>(snsDTO, header), String.class);
			String data = response.getBody();
			LOG.info("\nResponse for sendResponse from CustomerOnboardingWebService : response body => {}", data);
		} catch (Exception e) {
			LOG.info("\nException in sendResponse from CustomerOnboardingWebService =>", e);
		}

	}
}
