package com.neeyamo.approvalflow.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Table(name = "tb_approval_api_reg", catalog="approvalflow")
public class TbApprovalApiReg implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer apiRegId;
	private String applicationCode;
	private String applicationName;
	private String actionApi;
	private String userName;
	private String password;
	private String routingPath;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private boolean isActive;
	
	@Column(name = "apiRegId", unique = true, nullable = false)
	public Integer getApiRegId() {
		return apiRegId;
	}
	public void setApiRegId(Integer apiRegId) {
		this.apiRegId = apiRegId;
	}
	
	@Column(name = "applicationCode", length = 100)
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	@Column(name = "applicationName", length = 100)
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	@Column(name = "actionApi", length = 500)
	public String getActionApi() {
		return actionApi;
	}
	public void setActionApi(String actionApi) {
		this.actionApi = actionApi;
	}
	
	@Column(name = "userName", length = 100)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", length = 100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Column(name = "routingPath", length = 500)
	public String getRoutingPath() {
		return routingPath;
	}
	public void setRoutingPath(String routingPath) {
		this.routingPath = routingPath;
	}
	
	@Column(name = "createdBy", length = 100)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdOn", length = 19)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name = "updatedBy", length = 100)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedOn", length = 19)
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@Column(name = "isActive")
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
