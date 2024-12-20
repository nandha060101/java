package com.neeyamo.approvalflow.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class SnsDTO {

	private SnsDTO subscribedProducts;
	private String clientCode;
	private String clientName;
	private String countryCode;
	private String countryName;
	private String legalEntityCode;
	private String legalEntityName;
	private String appType;
	private String createdBy;
	private List<SnsDTO> applicationList;
	private String appCode;
	private String appName;
	private SnsDTO payload;
	private String statusType;
	private String status;
	private String remarks;
	private String sourceClientCode;
	private String sourceCountryCode;
	private String sourceLegalEntityCode;
	public SnsDTO getSubscribedProducts() {
		return subscribedProducts;
	}
	public void setSubscribedProducts(SnsDTO subscribedProducts) {
		this.subscribedProducts = subscribedProducts;
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
	public String getLegalEntityCode() {
		return legalEntityCode;
	}
	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}
	public String getLegalEntityName() {
		return legalEntityName;
	}
	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<SnsDTO> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<SnsDTO> applicationList) {
		this.applicationList = applicationList;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public SnsDTO getPayload() {
		return payload;
	}
	public void setPayload(SnsDTO payload) {
		this.payload = payload;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSourceClientCode() {
		return sourceClientCode;
	}
	public void setSourceClientCode(String sourceClientCode) {
		this.sourceClientCode = sourceClientCode;
	}
	public String getSourceCountryCode() {
		return sourceCountryCode;
	}
	public void setSourceCountryCode(String sourceCountryCode) {
		this.sourceCountryCode = sourceCountryCode;
	}
	public String getSourceLegalEntityCode() {
		return sourceLegalEntityCode;
	}
	public void setSourceLegalEntityCode(String sourceLegalEntityCode) {
		this.sourceLegalEntityCode = sourceLegalEntityCode;
	}
	
	
}
