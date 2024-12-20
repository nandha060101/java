package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class EssAppEventsDTO {

	private Integer id;
	private String appEvents;
	private Integer statusId;
	private Integer appEventsCount;
	private String stsCode;

	public EssAppEventsDTO() {
		
	}

	public EssAppEventsDTO(Integer id, String appEvents, Integer statusId, Integer appEventsCount, String stsCode) {
		super();
		this.id = id;
		this.appEvents = appEvents;
		this.statusId = statusId;
		this.appEventsCount = appEventsCount;
		this.stsCode = stsCode;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppEvents() {
		return appEvents;
	}

	public void setAppEvents(String appEvents) {
		this.appEvents = appEvents;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getAppEventsCount() {
		return appEventsCount;
	}

	public void setAppEventsCount(Integer appEventsCount) {
		this.appEventsCount = appEventsCount;
	}

	public String getStsCode() {
		return stsCode;
	}

	public void setStsCode(String stsCode) {
		this.stsCode = stsCode;
	}
	
	
}
