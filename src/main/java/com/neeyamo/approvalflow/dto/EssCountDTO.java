package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class EssCountDTO {

	private EssStatusDTO[] essStatus;
	private EssAppEventsDTO[] essAppEventsDTO;
	private Boolean onMeRequest;
	private Boolean myRequest;
	
	public EssStatusDTO[] getEssStatus() {
		return essStatus;
	}
	public void setEssStatus(EssStatusDTO[] essStatus) {
		this.essStatus = essStatus;
	}
	public EssAppEventsDTO[] getEssAppEventsDTO() {
		return essAppEventsDTO;
	}
	public void setEssAppEventsDTO(EssAppEventsDTO[] essAppEventsDTO) {
		this.essAppEventsDTO = essAppEventsDTO;
	}
	public Boolean getOnMeRequest() {
		return onMeRequest;
	}
	public void setOnMeRequest(Boolean onMeRequest) {
		this.onMeRequest = onMeRequest;
	}
	public Boolean getMyRequest() {
		return myRequest;
	}
	public void setMyRequest(Boolean myRequest) {
		this.myRequest = myRequest;
	}
}
