package com.neeyamo.approvalflow.service;

import java.util.List;

import com.neeyamo.approvalflow.dto.ApprovalFlowDTO;
import com.neeyamo.approvalflow.dto.EssCountDTO;
import com.neeyamo.approvalflow.dto.OutBoundResponseDTO;
import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;

public interface ApprovalFlowService {

	ApprovalFlowDTO insertDataFlowTransactionLog(ApprovalFlowDTO approvalFlowDTO);

	ApprovalFlowDTO insertDataFlowTransactionLogBulk(ApprovalFlowDTO approvalFlowDTO);

	ApprovalFlowDTO fetchApprovalRecords(int noOfRecords, int pageNumber, String elementToSearch,
			List<String> columnsToSearch, String appCode, String appEvent, List<String> jsonData,String assignedTo,String assignedBy);

	ApprovalFlowDTO updateStatus(OutBoundResponseDTO outBoundResponseDTO,String bearerToken );

	EssCountDTO getAppCount(String option, String appName,String assignedTo,String assignedBy);

	TbApprovalApiReg getApiRoutePathDetails(String applicationCode);
	
	EssCountDTO getTabAppCount(String option, String appName,String assignedTo,String assignedBy);

}
