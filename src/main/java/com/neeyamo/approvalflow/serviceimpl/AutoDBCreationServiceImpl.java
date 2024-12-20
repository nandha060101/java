///    Copyright (c)  Neeyamoworks Enterprise Solution. All Rights Reserved.                 

///    Private/Proprietary: No disclosure outside Neeyamoworks Enterprise Solution except by  

///    written agreement.             

///   </copyright> 

///    File History 

///    $File: AutoDBCreationServiceImpl

///    $Author:  ET1057 Saravana

//Modify
/// Tag          Date           ModifiedBy          Description  
///   8.         11/11/2021     Saravana         aurora DB implementation
package com.neeyamo.approvalflow.serviceimpl;

import javax.transaction.Transactional;

import com.neeyamo.approvalflow.dao.AutoDBCreationDAO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.service.AutoDBCreationService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ET1057
 *
 */

@Service
@Transactional
public class AutoDBCreationServiceImpl implements AutoDBCreationService {

	@Autowired
	AutoDBCreationDAO autoDBCreationDAO;

	private static final Logger logger = LoggerFactory.getLogger(AutoDBCreationServiceImpl.class);

	@Override
	public ResponseDTO autoDB(String clientCode, Boolean isUse) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			if (Util.isNotNullOrEmpty(clientCode)) {
				if (Boolean.TRUE.equals(isUse)) {
					responseDTO = autoDBCreationDAO.autoDB(clientCode.toLowerCase(), true);
				} else {
					responseDTO = autoDBCreationDAO.autoDB(clientCode.toLowerCase(), false);
				}
			}
		} catch (Exception e) {
			logger.error("DB creation Error {}", e.getMessage(), e);
			responseDTO = new ResponseDTO(Constants.FAILED, e.getMessage(), Constants.DB_CREATION_ERROR);
		}
		return responseDTO;
	}
}
