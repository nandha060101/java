package com.neeyamo.approvalflow.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.neeyamo.approvalflow.pojo.TbApprovalTransLog;
import com.nimbusds.jose.shaded.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author ET1057
 *
 */
@Component
public class ApprovalFlowDTO {
	private String clientCode;
	private String clientName;
	private String countryCode;
	private String countryName;
	private String legalEntityName;
	private String legalEntityCode;
	private String buCode;
	private String appCode;
	private String specialCases;
	private Integer levelCount;
	private String approverType;
	private String approverValue;
	private String flowCode;
	private String applicationCode;
	private String applicationName;
	private String actionItems;
	private String buName;
	private Integer noOfLevels;
	private String dynamicLevelCode;
	private Integer levelNumber;
	private String queryCode;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Boolean isActive;
	private BigInteger flowId;
	private BigInteger dynamicLevelsId;
	private String approverTypeCode;
	private String specialCaseValue;
	private String queryName;
	private String message;
	private Integer totalCount;
	private Integer pageNumber;
	private Integer size;
	private String roleCode;
	private String mode;
	private String modeType;
	private String modeValue;
	private String actionsAllowed;
	private String roleName;
	private String employeeId;
	private String applicationData;
	private Integer roleMappingId;
	private String status;
	private String api;
	private String apiJSON;
	private String searchKey;
	private String transactionId;
	private String currentLevel;
	private String eventBy;
	private String eventById;
	private String pendingWith;
	private String pendingWithId;
	private String stallApproverId;
	private String appEvents;
	private Boolean stallWorkflowRequired;
	private Integer stallRecoveryDays;
	private Long logTransactionId;
	private BigInteger stallWorkFlowId;
	private String responseString;
	private String roleData;
	private String apiType;
	private Boolean isSpecialCaseMatch;
	private String deletePassword;
	private String fieldTypeCode;
	List<String> columnToSearch;
	EssStatusDTO[] essStatus;
	EssAppEventsDTO[] essAppEventsDTO;
	List<Object> appdatas;

	// AuidtLog
	private String moduleName;
	private String uniqueCode;
	private String description;
	private String objectData;

	// escalation and auto action
	private String actionType;
	private Integer noOfDays;
	private String escalationDataStr;

	private String assignedBy;
	private String assignedTo;
	private String initiatedBy;
	private Long approvalTransLogId;
	List<TbApprovalTransLog> inputTransactionList;
	List<TbApprovalTransLog> myApprovalsList;
	List<TbApprovalTransLog> myTeamApprovalsList;
	private List<JSONObject> approvalListJson;
	List<String> appDatasList;
	List<String> transactionList;
	List<String> approvalsList;
	List<String> applicationNameList;
	List<String> statusList;
	private int itemsConfigPerPageToDisplay;
	private int currentPageToDisplay;
	private String myOrTeamAppr;

	public ApprovalFlowDTO(String approverTypeCode2, String approverType2) {
		this.approverTypeCode = approverTypeCode2;
		this.approverType = approverType2;
	}

	public ApprovalFlowDTO() {
	}

	@SuppressWarnings("all")
	public ApprovalFlowDTO(String approverType, String approverValue, String flowCode, String dynamicLevelCode,
			Integer levelNumber, String queryCode, String createdBy, Date createdOn, String updatedBy, Date updatedOn,
			Boolean isActive, BigInteger dynamicLevelsId, String queryName, String status, String api) {
		this.approverType = approverType;
		this.approverValue = approverValue;
		this.flowCode = flowCode;
		this.dynamicLevelCode = dynamicLevelCode;
		this.levelNumber = levelNumber;
		this.queryCode = queryCode;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isActive = isActive;
		this.dynamicLevelsId = dynamicLevelsId;
		this.queryName = queryName;
		this.status = status;
		this.api = api;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getBuCode() {
		return buCode;
	}

	public void setBuCode(String buCode) {
		this.buCode = buCode;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getSpecialCases() {
		return specialCases;
	}

	public void setSpecialCases(String specialCases) {
		this.specialCases = specialCases;
	}

	public Integer getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(Integer levelCount) {
		this.levelCount = levelCount;
	}

	public String getApproverType() {
		return approverType;
	}

	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}

	public String getApproverValue() {
		return approverValue;
	}

	public void setApproverValue(String approverValue) {
		this.approverValue = approverValue;
	}

	public List<TbApprovalTransLog> getMyApprovalsList() {
		return myApprovalsList;
	}

	public void setMyApprovalsList(List<TbApprovalTransLog> myApprovalsList) {
		this.myApprovalsList = myApprovalsList;
	}

	public List<TbApprovalTransLog> getMyTeamApprovalsList() {
		return myTeamApprovalsList;
	}

	public void setMyTeamApprovalsList(List<TbApprovalTransLog> myTeamApprovalsList) {
		this.myTeamApprovalsList = myTeamApprovalsList;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	public Integer getNoOfLevels() {
		return noOfLevels;
	}

	public void setNoOfLevels(Integer noOfLevels) {
		this.noOfLevels = noOfLevels;
	}

	public String getDynamicLevelCode() {
		return dynamicLevelCode;
	}

	public void setDynamicLevelCode(String dynamicLevelCode) {
		this.dynamicLevelCode = dynamicLevelCode;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public BigInteger getFlowId() {
		return flowId;
	}

	public void setFlowId(BigInteger flowId) {
		this.flowId = flowId;
	}

	public BigInteger getDynamicLevelsId() {
		return dynamicLevelsId;
	}

	public void setDynamicLevelsId(BigInteger dynamicLevelsId) {
		this.dynamicLevelsId = dynamicLevelsId;
	}

	public String getApproverTypeCode() {
		return approverTypeCode;
	}

	public void setApproverTypeCode(String approverTypeCode) {
		this.approverTypeCode = approverTypeCode;
	}

	public String getSpecialCaseValue() {
		return specialCaseValue;
	}

	public void setSpecialCaseValue(String specialCaseValue) {
		this.specialCaseValue = specialCaseValue;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getModeValue() {
		return modeValue;
	}

	public void setModeValue(String modeValue) {
		this.modeValue = modeValue;
	}

	public String getActionsAllowed() {
		return actionsAllowed;
	}

	public void setActionsAllowed(String actionsAllowed) {
		this.actionsAllowed = actionsAllowed;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getApplicationData() {
		return applicationData;
	}

	public void setApplicationData(String applicationData) {
		this.applicationData = applicationData;
	}

	public Integer getRoleMappingId() {
		return roleMappingId;
	}

	public void setRoleMappingId(Integer roleMappingId) {
		this.roleMappingId = roleMappingId;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjectData() {
		return objectData;
	}

	public void setObjectData(String objectData) {
		this.objectData = objectData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getApiJSON() {
		return apiJSON;
	}

	public void setApiJSON(String apiJSON) {
		this.apiJSON = apiJSON;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public List<String> getColumnToSearch() {
		return columnToSearch;
	}

	public void setColumnToSearch(List<String> columnToSearch) {
		this.columnToSearch = columnToSearch;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getEventBy() {
		return eventBy;
	}

	public void setEventBy(String eventBy) {
		this.eventBy = eventBy;
	}

	public String getEventById() {
		return eventById;
	}

	public void setEventById(String eventById) {
		this.eventById = eventById;
	}

	public String getPendingWith() {
		return pendingWith;
	}

	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}

	public String getPendingWithId() {
		return pendingWithId;
	}

	public void setPendingWithId(String pendingWithId) {
		this.pendingWithId = pendingWithId;
	}

	public String getStallApproverId() {
		return stallApproverId;
	}

	public void setStallApproverId(String stallApproverId) {
		this.stallApproverId = stallApproverId;
	}

	public String getAppEvents() {
		return appEvents;
	}

	public void setAppEvents(String appEvents) {
		this.appEvents = appEvents;
	}

	public Boolean getStallWorkflowRequired() {
		return stallWorkflowRequired;
	}

	public void setStallWorkflowRequired(Boolean stallWorkflowRequired) {
		this.stallWorkflowRequired = stallWorkflowRequired;
	}

	public Integer getStallRecoveryDays() {
		return stallRecoveryDays;
	}

	public void setStallRecoveryDays(Integer stallRecoveryDays) {
		this.stallRecoveryDays = stallRecoveryDays;
	}

	public Long getLogTransactionId() {
		return logTransactionId;
	}

	public void setLogTransactionId(Long logTransactionId) {
		this.logTransactionId = logTransactionId;
	}

	public BigInteger getStallWorkFlowId() {
		return stallWorkFlowId;
	}

	public void setStallWorkFlowId(BigInteger stallWorkFlowId) {
		this.stallWorkFlowId = stallWorkFlowId;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public String getRoleData() {
		return roleData;
	}

	public void setRoleData(String roleData) {
		this.roleData = roleData;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public Boolean getIsSpecialCaseMatch() {
		return isSpecialCaseMatch;
	}

	public void setIsSpecialCaseMatch(Boolean isSpecialCaseMatch) {
		this.isSpecialCaseMatch = isSpecialCaseMatch;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getEscalationDataStr() {
		return escalationDataStr;
	}

	public void setEscalationDataStr(String escalationDataStr) {
		this.escalationDataStr = escalationDataStr;
	}

	public String getDeletePassword() {
		return deletePassword;
	}

	public void setDeletePassword(String deletePassword) {
		this.deletePassword = deletePassword;
	}

	public String getFieldTypeCode() {
		return fieldTypeCode;
	}

	public void setFieldTypeCode(String fieldTypeCode) {
		this.fieldTypeCode = fieldTypeCode;
	}

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public List<TbApprovalTransLog> getInputTransactionList() {
		return inputTransactionList;
	}

	public void setInputTransactionList(List<TbApprovalTransLog> inputTransactionList) {
		this.inputTransactionList = inputTransactionList;
	}

	public Long getApprovalTransLogId() {
		return approvalTransLogId;
	}

	public void setApprovalTransLogId(Long approvalTransLogId) {
		this.approvalTransLogId = approvalTransLogId;
	}

	public List<String> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<String> transactionList) {
		this.transactionList = transactionList;
	}

	public EssStatusDTO[] getEssStatus() {
		return essStatus;
	}

	public void setEssStatus(EssStatusDTO[] essStatus) {
		this.essStatus = essStatus;
	}

	public List<String> getApprovalsList() {
		return approvalsList;
	}

	public void setApprovalsList(List<String> approvalsList) {
		this.approvalsList = approvalsList;
	}

	public List<String> getApplicationNameList() {
		return applicationNameList;
	}

	public void setApplicationNameList(List<String> applicationNameList) {
		this.applicationNameList = applicationNameList;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public int getItemsConfigPerPageToDisplay() {
		return itemsConfigPerPageToDisplay;
	}

	public void setItemsConfigPerPageToDisplay(int itemsConfigPerPageToDisplay) {
		this.itemsConfigPerPageToDisplay = itemsConfigPerPageToDisplay;
	}

	public int getCurrentPageToDisplay() {
		return currentPageToDisplay;
	}

	public void setCurrentPageToDisplay(int currentPageToDisplay) {
		this.currentPageToDisplay = currentPageToDisplay;
	}

	public String getMyOrTeamAppr() {
		return myOrTeamAppr;
	}

	public void setMyOrTeamAppr(String myOrTeamAppr) {
		this.myOrTeamAppr = myOrTeamAppr;
	}

	public List<Object> getAppdatas() {
		return appdatas;
	}

	public void setAppdatas(List<Object> appdatas) {
		this.appdatas = appdatas;
	}

	public List<String> getAppDatasList() {
		return appDatasList;
	}

	public void setAppDatasList(List<String> appDatasList) {
		this.appDatasList = appDatasList;
	}

	public List<JSONObject> getApprovalListJson() {
		return approvalListJson;
	}

	public void setApprovalListJson(List<JSONObject> approvalListJson) {
		this.approvalListJson = approvalListJson;
	}

	public EssAppEventsDTO[] getEssAppEventsDTO() {
		return essAppEventsDTO;
	}

	public void setEssAppEventsDTO(EssAppEventsDTO[] essAppEventsDTO) {
		this.essAppEventsDTO = essAppEventsDTO;
	}

	public String getActionItems() {
		return actionItems;
	}

	public void setActionItems(String actionItems) {
		this.actionItems = actionItems;
	}

}
