/**
 * 
 */
package com.neeyamo.approvalflow.service;

import com.neeyamo.approvalflow.dto.ResponseDTO;

/**
 * @author ET1057
 *
 */
public interface AutoDBCreationService {
	public ResponseDTO autoDB(String clientCode, Boolean isUse);
}
