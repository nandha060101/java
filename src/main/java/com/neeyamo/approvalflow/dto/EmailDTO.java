package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class EmailDTO extends ApprovalFlowDTO {
	private String emailFrom;
	private String emailTo;
	private String emailCC;
	private String emailBCC;
	private String application;
	private String client;
	private String country;
	private String businessUnit;
	private String templateCode; // Fetch template on template code
	private String languageCode; // Fetch Language on language code
	private String subject;
	private Object attibutesMap; // for wrapper fields data
	private boolean sync;
	private boolean sendMail;
	private boolean addAttachment;
	private boolean downloadFile;
	private TemplateAttachmentDTO[] templateAttachment;
	// email attachment
	private String remarks;
	private String templateName;
	private String templatePath;
	private String employeeName;
	private String queryMessage;
	private String scenarioName;
	private String summaryId;
	private String approverId;
	private String attachmentTemplateCode;
	private String globalEmployeeId;

	public String getGlobalEmployeeId() {
		return globalEmployeeId;
	}

	public void setGlobalEmployeeId(String globalEmployeeId) {
		this.globalEmployeeId = globalEmployeeId;
	}

	public String getAttachmentTemplateCode() {
		return attachmentTemplateCode;
	}

	public void setAttachmentTemplateCode(String attachmentTemplateCode) {
		this.attachmentTemplateCode = attachmentTemplateCode;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getActualUser() {
		return actualUser;
	}

	public void setActualUser(String actualUser) {
		this.actualUser = actualUser;
	}

	private String actualUser;


	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailCC() {
		return emailCC;
	}

	public void setEmailCC(String emailCC) {
		this.emailCC = emailCC;
	}

	public String getEmailBCC() {
		return emailBCC;
	}

	public void setEmailBCC(String emailBCC) {
		this.emailBCC = emailBCC;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Object getAttibutesMap() {
		return attibutesMap;
	}

	public void setAttibutesMap(Object attibutesMap) {
		this.attibutesMap = attibutesMap;
	}

	public boolean isSync() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public boolean isSendMail() {
		return sendMail;
	}

	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}

	public boolean isAddAttachment() {
		return addAttachment;
	}

	public void setAddAttachment(boolean addAttachment) {
		this.addAttachment = addAttachment;
	}

	public boolean isDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(boolean downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getQueryMessage() {
		return queryMessage;
	}

	public void setQueryMessage(String queryMessage) {
		this.queryMessage = queryMessage;
	}

	public TemplateAttachmentDTO[] getTemplateAttachment() {
		return templateAttachment;
	}

	public void setTemplateAttachment(TemplateAttachmentDTO[] templateAttachment) {
		this.templateAttachment = templateAttachment;
	}

}
