///    Copyright (c)  Neeyamoworks Enterprise Solution. All Rights Reserved.                 

///    Private/Proprietary: No disclosure outside Neeyamoworks Enterprise Solution except by  

///    written agreement.             

///   </copyright> 

///    File History 

///    $File: PrinmergeServiceImpl

///    $Author:  ET1057 Saravana

//Modify
/// Tag          Date           ModifiedBy          Description  
///   1.         08/05/2020      Saravana         Prinmerge service logics
package com.neeyamo.approvalflow.serviceimpl;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neeyamo.approvalflow.dto.EmailDTO;
import com.neeyamo.approvalflow.dto.TemplateAttachmentDTO;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.utility.Util;
import com.neeyamo.approvalflow.webservices.PrinmergeWebServices;

@Service
@Transactional
public class PrinmergeServiceImpl implements PrinmergeService {

	private static final Logger logger = LoggerFactory.getLogger(PrinmergeServiceImpl.class);

	@Value("${prinmergeLite.appcode}")
	private String appcode;
	@Value("${prinmergeLite.bodyTemplate}")
	private String bodyTemplate;
	@Value("${prinmergeLite.attachmentTemplate}")
	private String attachmentTemplate;
	@Autowired
	PrinmergeWebServices prinmergeWebServices;

	// exception email trigger method
	@SuppressWarnings("all")
	public void exceptionMailTrigger(String className, String methodName, Exception exception, String objectData) {
		EmailDTO mailDetailsDTO = new EmailDTO();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Map<String, Object> attributes = new HashMap<>();
		try {
			File file = File.createTempFile("Dataflow-", ".log");
			PrintStream ps = new PrintStream(file);
			if (Util.isNotNullOrEmpty(exception)) {
				exception.printStackTrace(ps);
			}else {
				
			}
			ps.append("Request Data:");
			ps.append(objectData);
			FileSystemResource body = new FileSystemResource(file);
			Date currentDate = new Date();
			String strCurrentDate = dateFormat.format(currentDate);
			attributes.put("className", className);
			attributes.put("methodName", methodName);
			attributes.put("exception", exception);
			if(Util.isNotNullOrEmpty(exception)) {
			attributes.put("exceptionMessage", exception.toString());
			}
			attributes.put("requestData", objectData);
			attributes.put("currentDate", strCurrentDate);
			mailDetailsDTO.setAttibutesMap(attributes);
			TemplateAttachmentDTO templateAttachmentDTO = new TemplateAttachmentDTO();
			TemplateAttachmentDTO[] templatenew = new TemplateAttachmentDTO[1];
			templateAttachmentDTO.setApplication(appcode);
			templateAttachmentDTO.setTemplateCode(attachmentTemplate);
			templateAttachmentDTO.setLanguageCode("en");
			templateAttachmentDTO.setAttibutesMap(mailDetailsDTO.getAttibutesMap());
			templatenew[0] = templateAttachmentDTO;

			mailDetailsDTO.setLanguageCode("en");
			mailDetailsDTO.setApplication(appcode);
			mailDetailsDTO.setEmailTo("et1344@neeyamoworks.com");
			mailDetailsDTO.setTemplateCode(bodyTemplate);
			mailDetailsDTO.setSync(false);
			mailDetailsDTO.setSendMail(true);
			mailDetailsDTO.setDownloadFile(true);
			mailDetailsDTO.setAddAttachment(true);

			prinmergeWebServices.sendPrinmergeMailWebService(mailDetailsDTO, body);
		} catch (Exception e) {
			logger.info("Exception in mdmService of  MdmServiceImpl. {}", e.getMessage(), e);
		}
	}
}
