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
@Proxy(lazy=false)
@Table(name = "tb_approval_mas_client", catalog="approvalflow")
public class TbApprovalMasClient implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	private Long clientId;
	private String clientCode;
	private String tenent;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Boolean isActive;
	
    
	public TbApprovalMasClient() {
		// contstuctor
	}
	
	@Column(name = "clientId", unique = true, nullable = false)
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	@Column(name = "clientCode", length = 100)
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	@Column(name = "tenent", length = 100)
	public String getTenent() {
		return tenent;
	}
	public void setTenent(String tenent) {
		this.tenent = tenent;
	}
	@Column(name = "createdBy", length = 200)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdOn", length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "updatedBy", length = 200)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedOn", length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
