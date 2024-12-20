package com.neeyamo.approvalflow.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OutBoundResponseDTO {

	private String actionBy;
	private String actionType;
	private String applicationCode;
	private String clientCode;
	private List<TransactionListDTO> transactionList;
	public String getActionBy() {
		return actionBy;
	}
	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public List<TransactionListDTO> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<TransactionListDTO> transactionList) {
		this.transactionList = transactionList;
	}
	
	
}
