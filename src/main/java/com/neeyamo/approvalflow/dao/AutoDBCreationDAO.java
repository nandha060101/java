///    Copyright (c)  Neeyamoworks Enterprise Solution. All Rights Reserved.                 

///    Private/Proprietary: No disclosure outside Neeyamoworks Enterprise Solution except by  

///    written agreement.             

///   </copyright> 

///    File History 

///    $File: AutoDBCreationDAO

///    $Author:  ET1057 Saravana

//Modify
/// Tag          Date           ModifiedBy          Description  
///   1.         12/07/2021     Saravana         Stalled workflow changes

package com.neeyamo.approvalflow.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.transaction.Transactional;

import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.hibernateutility.MultiTenantConnectionProviderImpl;
import com.neeyamo.approvalflow.utility.Constants;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AutoDBCreationDAO {
	@Autowired
	MultiTenantConnectionProviderImpl multiTenantCon;
	@Autowired
	ApprovalFlowDAO approvalFlowDAO;

	@Autowired
	private ResourceLoader resourceLoader;

	private static final Logger logger = LoggerFactory.getLogger(AutoDBCreationDAO.class);
	private String database = " The database ";
	private String exist = " exists. ";

	public boolean checkSchema(String schemaName) {

		String schema = schemaName;
		String schemaExist = database + schema + exist;

		try (Connection con = multiTenantCon.getAnyConnection(); Statement stmt = con.createStatement();) {
			if (stmt.execute("SHOW DATABASES;")) {
				try (ResultSet resultset = stmt.getResultSet();) {
					while (resultset.next()) {
						String dbName = resultset.getString("Database");

						if (schema.equals(dbName)) {
							logger.info(schemaExist);
							return schema.equals(dbName);
						}
					}
				}
			}
			return false;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public ResponseDTO autoDB(String clientCode, Boolean isUse) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String schemaDBName = ("approvalflow_" + clientCode);
			if (!checkSchema(schemaDBName)) {
				String dbCreation = "Creating Database : " + schemaDBName;
				logger.info(dbCreation);
				File sqlDirectory = resourceLoader.getResource("classpath:approvalflowclient.sql").getFile();
				createDB(schemaDBName, sqlDirectory, isUse);
				clientInsert(clientCode, schemaDBName);
				responseDTO = new ResponseDTO(Constants.SUCCESS, null, Constants.AUTO_DB_CREATED);
			} else {
				responseDTO = new ResponseDTO(Constants.SUCCESS, database + schemaDBName + exist,
						Constants.AUTO_DB_EXISTS);
			}
		} catch (Exception e) {
			logger.error("DB creation Error {}", e.getMessage(), e);
			responseDTO = new ResponseDTO(Constants.FAILED, e.getMessage(), Constants.DB_CREATION_ERROR);
		}
		return responseDTO;
	}

	public String createDB(String schemaDBName, File sqlDirectory, Boolean isUse) {
		String dbCreateSuccessfull = "Database created : " + schemaDBName;
		String dbCreationError = "Failed to create Database : " + schemaDBName;
		try (Connection con = multiTenantCon.getAnyConnection(); Statement stmt = con.createStatement();) {
			String query = "CREATE DATABASE " + schemaDBName + "";
			String useQuery = "";
			if (Boolean.TRUE.equals(isUse)) {
				useQuery = "USE " + schemaDBName + "";
			}
			stmt.executeUpdate(query);
			if (Boolean.TRUE.equals(isUse)) {
				stmt.executeUpdate(useQuery);
			}
			con.setCatalog(schemaDBName);
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con);
			// Give the input file to Reader
			try (Reader reader = new BufferedReader(new FileReader(sqlDirectory))) {
				sr.setLogWriter(null);
				sr.setErrorLogWriter(null);
				// Exctute script
				sr.runScript(reader);
				logger.info(dbCreateSuccessfull);
				return dbCreateSuccessfull;
			}
		} catch (SQLException | IOException e) {
			logger.error(dbCreationError, e.getMessage(), e);
			return dbCreationError;
		}
	}

	public void clientInsert(String clientCode, String tenent) {
		approvalFlowDAO.insertClient(clientCode, tenent);
	}
}
