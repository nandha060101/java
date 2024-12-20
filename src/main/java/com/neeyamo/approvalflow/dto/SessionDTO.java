/**
 * 
 */
package com.neeyamo.approvalflow.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <Description of class and its use.>
 * 
 * Copyright 2019, Neeyamo India All rights reserved.
 * 
 * @author ET1057 Saravana on May 18, 2019
 * @version 1.0
 */
public class SessionDTO implements Serializable{
	
	 private String email;
	    private String employeeId;
	    private String firstName;
	    private String lastName;
	    private String clientCode;
	    private String clientName;
	    private String countryCode;
	    private String buCode;
	    private String departmentCode;
	    private String department;
	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private String jwtToken;
	    private String timeZone;
	    private String country;
	    private String businessUnit;
	    private String roleName;
		private List<String> roleNameList;
		private String globalEmployeeId;
		private String employeeName;
		private String workLocationId;
		private String languageCode;
		private SessionDTO widgetSession;
		private String prefferedName;
		private String appCode;
		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return the employeeId
		 */
		public String getEmployeeId() {
			return employeeId;
		}
		/**
		 * @param employeeId the employeeId to set
		 */
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
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
		 * @return the clientCode
		 */
		public String getClientCode() {
			return clientCode;
		}
		/**
		 * @param clientCode the clientCode to set
		 */
		public void setClientCode(String clientCode) {
			this.clientCode = clientCode;
		}
		/**
		 * @return the clientName
		 */
		public String getClientName() {
			return clientName;
		}
		/**
		 * @param clientName the clientName to set
		 */
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		/**
		 * @return the countryCode
		 */
		public String getCountryCode() {
			return countryCode;
		}
		/**
		 * @param countryCode the countryCode to set
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		/**
		 * @return the buCode
		 */
		public String getBuCode() {
			return buCode;
		}
		/**
		 * @param buCode the buCode to set
		 */
		public void setBuCode(String buCode) {
			this.buCode = buCode;
		}
		/**
		 * @return the departmentCode
		 */
		public String getDepartmentCode() {
			return departmentCode;
		}
		/**
		 * @param departmentCode the departmentCode to set
		 */
		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}
		/**
		 * @return the department
		 */
		public String getDepartment() {
			return department;
		}
		/**
		 * @param department the department to set
		 */
		public void setDepartment(String department) {
			this.department = department;
		}
		/**
		 * @return the jwtToken
		 */
		public String getJwtToken() {
			return jwtToken;
		}
		/**
		 * @param jwtToken the jwtToken to set
		 */
		public void setJwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
		}
		/**
		 * @return the timeZone
		 */
		public String getTimeZone() {
			return timeZone;
		}
		/**
		 * @param timeZone the timeZone to set
		 */
		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}
		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}
		/**
		 * @param country the country to set
		 */
		public void setCountry(String country) {
			this.country = country;
		}
		/**
		 * @return the businessUnit
		 */
		public String getBusinessUnit() {
			return businessUnit;
		}
		/**
		 * @param businessUnit the businessUnit to set
		 */
		public void setBusinessUnit(String businessUnit) {
			this.businessUnit = businessUnit;
		}
		/**
		 * @return the roleName
		 */
		public String getRoleName() {
			return roleName;
		}
		/**
		 * @param roleName the roleName to set
		 */
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public List<String> getRoleNameList() {
			return roleNameList;
		}
		public void setRoleNameList(List<String> roleNameList2) {
			this.roleNameList = roleNameList2;
		}
		public String getGlobalEmployeeId() {
			return globalEmployeeId;
		}
		public void setGlobalEmployeeId(String globalEmployeeId) {
			this.globalEmployeeId = globalEmployeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public String getWorkLocationId() {
			return workLocationId;
		}
		public void setWorkLocationId(String workLocationId) {
			this.workLocationId = workLocationId;
		}
		public String getLanguageCode() {
			return languageCode;
		}
		public void setLanguageCode(String languageCode) {
			this.languageCode = languageCode;
		}
		public SessionDTO getWidgetSession() {
			return widgetSession;
		}
		public void setWidgetSession(SessionDTO widgetSession) {
			this.widgetSession = widgetSession;
		}
		public String getPrefferedName() {
			return prefferedName;
		}
		public void setPrefferedName(String prefferedName) {
			this.prefferedName = prefferedName;
		}
		public String getAppCode() {
			return appCode;
		}
		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}
		
		
	    
}
