package com.neeyamo.approvalflow.service;

public interface StatusCount {
	
	String getApplicationName();
	String getApplicationCode();
	Integer getCount();
	
	String getAppEvents();
	Integer getAppEventsCount();
}
