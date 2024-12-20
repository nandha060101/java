package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class TransactionListDTO {

	private String appEvents;
	private String applicationData;
	private String assignedBy;
	private String assignedTo;
	private String createdBy;
	private String employeeId;
	private String status;
	private String transactionId;
	public String getAppEvents() {
		return appEvents;
	}
	public void setAppEvents(String appEvents) {
		this.appEvents = appEvents;
	}
	public String getApplicationData() {
		return applicationData;
	}
	public void setApplicationData(String applicationData) {
		this.applicationData = applicationData;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
}
