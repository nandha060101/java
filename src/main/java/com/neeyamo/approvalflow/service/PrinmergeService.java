package com.neeyamo.approvalflow.service;

public interface PrinmergeService {
	void exceptionMailTrigger(String className, String methodName, Exception exception, String objectData);
}
