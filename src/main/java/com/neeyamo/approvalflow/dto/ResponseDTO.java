
package com.neeyamo.approvalflow.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResponseDTO {
	private String title;
	private String status;
	private Object payload;
	private String description;
	private int count;
	private List<String> transactionList;

	public ResponseDTO() {

	}

	public ResponseDTO(String status, Object payload, String description) {

		this.status = status;
		this.payload = payload;
		this.description = description;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Object getPayload() {

		return payload;
	}

	public void setPayload(Object payload) {

		this.payload = payload;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<String> transactionList) {
		this.transactionList = transactionList;
	}

}
