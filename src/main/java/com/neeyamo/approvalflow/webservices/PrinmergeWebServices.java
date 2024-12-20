
package com.neeyamo.approvalflow.webservices;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.neeyamo.approvalflow.dto.EmailDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

@Repository

public class PrinmergeWebServices {
	private static final Logger logger = LoggerFactory.getLogger(PrinmergeWebServices.class);

	@Value("${prinmergeLite.username}")
	private String username;
	@Value("${prinmergeLite.password}")
	private String password;
	@Value("${prinmergeLite.appcode}")
	private String appcode;
	@Value("${prinmergeLite.prinmergeMailURL}")
	private String prinmergeMailURL;

	public ResponseEntity<Object> sendPrinmergeMailWebService(EmailDTO emailDTO, FileSystemResource body) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		emailDTO.setApplication(appcode);
		emailDTO.setEmailFrom(Constants.SUPPORT_OFFER_EMAIL);
		emailDTO.setSendMail(true);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		if (Util.isNotNullOrEmpty(body)) {
			map.add("attachment", body);
		}
		map.add("properties", emailDTO);
		ResponseDTO res = new ResponseDTO();
		try {
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
			ResponseEntity<Resource> resource = restTemplate.postForEntity(prinmergeMailURL, request, Resource.class);
			logger.info("Mail Sent Successfully {}" , resource);
		} catch (Exception e) {
			logger.info("Exception in SendMail. {}" , e.getMessage() , e);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
