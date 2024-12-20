package com.neeyamo.approvalflow.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NeosuiteDTO {

	private String status;
	private List<Payload> payload;
	private String description;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Payload> getPayload() {
		return payload;
	}
	public void setPayload(List<Payload> payload) {
		this.payload = payload;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
