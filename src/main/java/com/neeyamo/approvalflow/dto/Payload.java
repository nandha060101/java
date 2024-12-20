
package com.neeyamo.approvalflow.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.neeyamo.approvalflow.utility.Util;

@Component
public class Payload extends ApprovalFlowDTO {
	List<Payload> list = new ArrayList<>();
	private Fields fields;
	private String value;
	private String valueCode;
	private String valueCode2;
	private String firstName;
	private String lastName;
	private String gender;
	private String preferredName;
	private String dateOfBirth;
	private Integer id;
	private String title;
	private String employeeStatus;
	private String businessunitName;
	private String businessunitCode;
	private String employeeGlobalId;
	private String phoneNumberOfficial;
	private String emailOfficial;
	private String workLocationId;
	private String locationCode;
	private String locationName;
	private String emailId;
	private String phoneNumber;
	Object compensation;
	Payload component1;
	Payload component2;
	Payload component3;
	Payload component4;
	Payload employee;
	Payload employeerelation;
	private String relatedEmployeeId;
	private String departmentName;
	private String departmentCode;
	private boolean enabled;
	private String reportingManagerFuntionalEmployeeId;
	private String managerName;
	private String recruiterName;
	Payload businessunit;

	private String totalItems;
	private String page;
	private String totalPages;

	private String expressionCode;
	private String appName;
	private String appCode;
	private String approverValueLabel;
	private String roleName;
	private String roleCode;
	private Object role;
	private String employeeId;
	private String fieldCode;
	private String fieldName;

	private String groupCode;
	private String groupName;

	List<Payload> clientList = new ArrayList<>();
	List<Payload> countryList = new ArrayList<>();
	List<Payload> buList = new ArrayList<>();

	public Object getRole() {
		return role;
	}

	public void setRole(Object role) {
		this.role = role;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	@Override
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getRoleCode() {
		return roleCode;
	}

	@Override
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Payload getEmployeerelation() {
		return employeerelation;
	}

	public void setEmployeerelation(Payload employeerelation) {
		this.employeerelation = employeerelation;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	private String relationType;
	private String compensationPlanId;
	private String compensationPlanName;

	private String currencyName;
	private String currencyType;
	private String ctc;
	private Integer offertransactionid;

	/**
	 * @return the list
	 */
	public List<Payload> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Payload> list) {
		this.list = list;
	}

	/**
	 * @return the valueCode2
	 */
	public String getValueCode2() {
		return valueCode2;
	}

	/**
	 * @param valueCode2 the valueCode2 to set
	 */
	public void setValueCode2(String valueCode2) {
		this.valueCode2 = valueCode2;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the employeeStatus
	 */
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * @param employeeStatus the employeeStatus to set
	 */
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public String getBusinessunitName() {
		return businessunitName;
	}

	public void setBusinessunitName(String businessunitName) {
		this.businessunitName = businessunitName;
	}

	public String getBusinessunitCode() {
		return businessunitCode;
	}

	public void setBusinessunitCode(String businessunitCode) {
		this.businessunitCode = businessunitCode;
	}

	public String getEmployeeGlobalId() {
		return employeeGlobalId;
	}

	public void setEmployeeGlobalId(String employeeGlobalId) {
		this.employeeGlobalId = employeeGlobalId;
	}

	public String getPhoneNumberOfficial() {
		return phoneNumberOfficial;
	}

	public void setPhoneNumberOfficial(String phoneNumberOfficial) {
		this.phoneNumberOfficial = phoneNumberOfficial;
	}

	public String getEmailOfficial() {
		return emailOfficial;
	}

	public void setEmailOfficial(String emailOfficial) {
		this.emailOfficial = emailOfficial;
	}

	public String getWorkLocationId() {
		return workLocationId;
	}

	public void setWorkLocationId(String workLocationId) {
		this.workLocationId = workLocationId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Payload getComponent1() {
		return component1;
	}

	public void setComponent1(Payload component1) {
		this.component1 = component1;
	}

	public Payload getComponent2() {
		return component2;
	}

	public void setComponent2(Payload component2) {
		this.component2 = component2;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Payload getComponent3() {
		return component3;
	}

	public void setComponent3(Payload component3) {
		this.component3 = component3;
	}

	public String getCompensationPlanId() {
		return compensationPlanId;
	}

	public void setCompensationPlanId(String compensationPlanId) {
		this.compensationPlanId = compensationPlanId;
	}

	public String getCompensationPlanName() {
		return compensationPlanName;
	}

	public void setCompensationPlanName(String compensationPlanName) {
		this.compensationPlanName = compensationPlanName;
	}

	public Payload getComponent4() {
		return component4;
	}

	public void setComponent4(Payload component4) {
		this.component4 = component4;
	}

	public Object getCompensation() {
		return compensation;
	}

	public void setCompensation(Object compensation) {
		this.compensation = compensation;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	public Payload getEmployee() {
		return employee;
	}

	public void setEmployee(Payload employee) {
		this.employee = employee;
	}

	public Integer getOffertransactionid() {
		return offertransactionid;
	}

	public void setOffertransactionid(Integer offertransactionid) {
		this.offertransactionid = offertransactionid;
	}

	public String getRelatedEmployeeId() {
		return relatedEmployeeId;
	}

	public void setRelatedEmployeeId(String relatedEmployeeId) {
		this.relatedEmployeeId = relatedEmployeeId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getReportingManagerFuntionalEmployeeId() {
		return reportingManagerFuntionalEmployeeId;
	}

	public void setReportingManagerFuntionalEmployeeId(String reportingManagerFuntionalEmployeeId) {
		this.reportingManagerFuntionalEmployeeId = reportingManagerFuntionalEmployeeId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public Payload getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(Payload businessunit) {
		this.businessunit = businessunit;
	}

	public String getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getExpressionCode() {
		return expressionCode;
	}

	public void setExpressionCode(String expressionCode) {
		this.expressionCode = expressionCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	@Override
	public String getEmployeeId() {
		return employeeId;
	}

	@Override
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
		setApproverValueLabel(employeeId);
	}

	public String getApproverValueLabel() {
		return approverValueLabel;
	}

	public void setApproverValueLabel(String approverValueLabel) {
		this.approverValueLabel = approverValueLabel;
		if (Util.isNotNullOrEmpty(getFirstName()) || Util.isNotNullOrEmpty(getLastName())) {
			this.approverValueLabel = approverValueLabel + "-" + getFirstName() + " " + getLastName();
		}
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<Payload> getClientList() {
		return clientList;
	}

	public void setClientList(List<Payload> clientList) {
		this.clientList = clientList;
	}

	public List<Payload> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Payload> countryList) {
		this.countryList = countryList;
	}

	public List<Payload> getBuList() {
		return buList;
	}

	public void setBuList(List<Payload> buList) {
		this.buList = buList;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
