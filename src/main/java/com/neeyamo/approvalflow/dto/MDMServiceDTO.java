package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class MDMServiceDTO {

	private String restrictions;
	private int page;
	private int size;
	private String status;
	Payload payload;
	private String description;

	public MDMServiceDTO() {
		super();
	}

	public MDMServiceDTO(String restrictions, int page, int size) {
		super();
		this.restrictions = restrictions;
		this.page = page;
		this.size = size;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the payload
	 */
	public Payload getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MDMServiceDTO [restrictions=" + restrictions + ", page=" + page + ", size=" + size + "]";
	}
}
