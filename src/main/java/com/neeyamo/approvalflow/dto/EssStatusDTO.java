package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class EssStatusDTO {

	private Integer id;
	private String appCode;
	private String appName;
	private Integer statusId;
	private Integer appCount;
	private String stsCode;

	public EssStatusDTO() {
		
	}
	public EssStatusDTO(Integer id, String appCode, String appName, Integer statusId, Integer appCount, String stsCode) {
		super();
		this.id = id;
		this.appCode = appCode;
		this.appName = appName;
		this.statusId = statusId;
		this.appCount = appCount;
		this.stsCode = stsCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStsCode() {
		return stsCode;
	}

	public void setStsCode(String stsCode) {
		this.stsCode = stsCode;
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
	public Integer getAppCount() {
		return appCount;
	}
	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

}
