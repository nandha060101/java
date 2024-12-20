package com.neeyamo.approvalflow.utility;

public class Constants {
	private Constants() {
		throw new IllegalStateException("Constants class");
	}
	public static final String APP_CODE = "approvalflow";
	// Property File Selection
	public static final String PROPERTY_FILE = "localhost.properties";
	public static final String DATABASE_NAME = "erms";

	// Common for dataflow
	public static final String COUNTRY = "Country";
	public static final String BUSINESSUNIT = "Business Unit";
	public static final String MDM_USERNAME = "offer";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BASIC = "Basic ";
	public static final String PROPERTIES = "properties ";
	public static final String COLON = ":";
	public static final String QUESTION_MARK = "?";
	public static final String SIGNING_KEY = "Neeyamo@123";
	public static final String OAUTH_ROLE = "APIADMIN";
	public static final String OAUTH_USER = "approvalflowAdmin";
	public static final String OAUTH_P = "$2a$10$XqV/096KB9X1f2uwur5SxeD8BUnC.2mJ0q9X8wj/G.SF1UXXC6svC";
	public static final String CLIENT_ID = "clientId";
	public static final String COUNTRY_ID = "countryId";
	public static final String BU_ID = "businessUnitId";
	public static final String ROUTINE_ID = "routineId";
	public static final String APP_NAME = "applicationName";
	public static final String APP_REAL_NAME = "approvalflow";
	public static final String CLIENT = "Client";
	public static final String CLIENTCODE = "clientCode";
	public static final String ACTION = "action";
	public static final String NOTES = "notes";
	public static final String NOTES_VALUE = "Please find the offer request";
	public static final String APPLICATION = "application";
	public static final String APPLICATION_VALUE = "approvalflow";
	public static final String COMMENT = "comment";
	public static final String COMMENT_VALUE = "Changed to the status of";
	public static final String ASSIGNEDTO = "assignedTo";
	public static final String ASSIGNEDBY = "assignedBy";
	public static final String URLLINK = "urlLink";
	
	public static final String AUTHHEADER = "authHeader";
	public static final String AUTHHEADER_VALUE = "authHeader";
	public static final String APPROVEJSON = "approvalJsonTemplate";
	public static final String REJECTJSON = "rejectJsonTemplate";
	public static final String CANCELJSON = "cancelJsonTemplate";
	public static final String EMPLOYEEID = "employeeId";
	
	public static final String TRANSACTIONID = "transactionId";
	public static final String CONSTANT_DATA = "send_data";
	public static final String CONSTANT_URL = "sendUrl";
	public static final String CTC = "ctc";
	public static final String COMPENSATION = "compensation";
	public static final String CANDIDATE_ID = "candidateId";
	public static final String INPUT_DATA = "inputData";
	public static final String OUTPUT_DATA = "outputData";
	public static final String MANAGER = "manager";
	// MDM Constants
	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String FOUR = "4";
	public static final String FIVE = "5";
	public static final String SIX = "6";
	public static final String SEVEN = "7";
	public static final String EIGHT = "8";
	public static final String NINE = "9";

	//status message
	public static final String STATUS_OPEN = "Open";
	public static final String STATUS_APPROVED = "Approved";
	public static final String STATUS_REJECTED = "Rejected";
	public static final String STATUS_OVERDUE = "Overdue";
	public static final String ALREADYEXIST = "AlreadyExist";
	public static final String UPDATE = "update";
	public static final String SAVE = "save";
	public static final String APINOTREGISTERED = "ApiNotRegistered";
	
	public static final String STATUS_CODE_OPEN = "stp001";
	public static final String STATUS_CODE_APPROVED = "stp002";
	public static final String STATUS_CODE_REJECTED = "stp003";
	public static final String STATUS_CODE_OVERDUE = "stp004";
	
	
	
	public static final String SUCCESS = "Success";
	public static final String ERROR = "Error";
	public static final String MAPPING_NOT_FOUND = "Mapping not found";
	public static final String COMBINATION_MAPPED_SUCCESSFULLY = "Combination Mapped Successfully";
	public static final String COMBINATION_UPDATED_SUCCESSFULLY = "Combination Updated Successfully";
	public static final String MAP_EXISTS = "Selected Combination already exists";
	public static final String CONFIGURAION_ALREADY_IN_USE = "Selected Configuration is in Use";
	public static final String MAP_ROUTINE = "Please check the Configurations";
	public static final String MAP_DELETION = "Mapping Deleted Successfully";
	public static final String RESTRICTIONS_EMP = "restrictions=employee.employeeGlobalId IN (";
	public static final String CLOSE_BRACE = ")";
	public static final String CLIENT_NAME = "neeyamo";
	public static final String ROOT = "ROOT";
	public static final String CAL_BU_RESTRICTION = "restrictions=businessunit.clientCode='";
	public static final String CAL_COU_RESTRICTION = "'&businessunit.countryCode='";
	public static final String EMP_RESTRICTION = "restrictions=employeeId='";
	public static final String JOB_EMP_RESTRICTION = "restrictions=job.employeeGlobalId='";
	public static final String SINGLE_QUOTE = "'";
	public static final String DELETEPASSWORD = "Dataflow@1234";
	// new Constants
	public static final String CONSTANT_ENCRYPTION_KEY = "AES/CBC/NoPadding";
	public static final String EXCEPTION="Exception";
	public static final String RESPONSE_TIME = "ResponseTime";
	public static final String TENENT_PREFIX = "approvalflow_";
	public static final String TENENT_DEFAULT = "approvalflow";
	public static final String CLIENT_ADMIN = "ClientAdmin";
	public static final String SUPPORT_OFFER_EMAIL = "noreply@neeyamo.com";
	// Token constants
    public static final String SUB = "sub";
    public static final String APP_ROLES = "appRoles";
    public static final String NEEYAMO = "neeyamo";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDITIONAL_DETAILS = "additionalDetails";
    public static final String ROLES = "roles";
    public static final String EMPLOYEE_ID = "employeeId";
    
    // COB constants
    public static final String AUTO_DB = "AutoDB";
    public static final String AUTO_DB_EXISTS = "Database already exists";
    public static final String AUTO_DB_CREATED = "Database created successfully";
    public static final String DB_CREATION_ERROR = "Error occured while creating database";
    public static final String FAILED = "Failed";
}
