package com.neeyamo.approvalflow.dto;

import java.util.Date;
import java.util.List;

import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;

import org.springframework.stereotype.Component;

@Component
public class ApiRegistrationDTO {

	private Integer apiRegId;
	private String appCode;
	private String appName;
	private String actionApi;
	private String userName;
	private String password;
	private String routingPath;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private short isActive;
	private Integer totalCount;
	private List<TbApprovalApiReg> tbApprovalApiRegList;
	
	
	public Integer getApiRegId() {
		return apiRegId;
	}
	public void setApiRegId(Integer apiRegId) {
		this.apiRegId = apiRegId;
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
	public String getActionApi() {
		return actionApi;
	}
	public void setActionApi(String actionApi) {
		this.actionApi = actionApi;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public short getIsActive() {
		return isActive;
	}
	public void setIsActive(short isActive) {
		this.isActive = isActive;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<TbApprovalApiReg> getTbApprovalApiRegList() {
		return tbApprovalApiRegList;
	}
	public void setTbApprovalApiRegList(List<TbApprovalApiReg> tbApprovalApiRegList) {
		this.tbApprovalApiRegList = tbApprovalApiRegList;
	}
	public String getRoutingPath() {
		return routingPath;
	}
	public void setRoutingPath(String routingPath) {
		this.routingPath = routingPath;
	}
	
	
}
