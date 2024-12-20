
package com.neeyamo.approvalflow.utility;

/*
 * @(#)Util.java 1.0 Nov 4, 2015
 * 
 
 * 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of Neeyamo Enterprise Solutions
 * Pvt. Ltd. ("Confidential Information"). You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you entered into with
 * Neeyamo.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeyamo.approvalflow.dto.SessionDTO;
import com.neeyamo.approvalflow.security.AppUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * <Description of class and its use.>
 * 
 * Copyright 2015, Neeyamo India All rights reserved.
 * 
 * @author 101811 Vivek Sharma on 3:55:02 PM Nov 4, 2015
 * @version 1.0
 */
@Component
public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	private static final String AES_KEY = "aesEncryptionKey";
	private static final String INIT_VECTOR = "encryptionIntVec";

	@Autowired
	private static final String KEY = "TheBestSecretKey";
	private static final String ALGO = "AES";

	/***
	 * It's an object which is private, final and static. Also it refers to null
	 * object throughout the session.
	 */
	private static final Object nullObject = null;

	/***
	 * This is a constructor of Util class and it is private because nobody should
	 * be able to instantiate this class.
	 */
	private Util() {

	}

	/***
	 * Description This method returns null object to invoking peace of code from
	 * where it is called.
	 * 
	 * @return null object.
	 * 
	 *
	 * @return Object
	 */
	public static Object resetToNull() {

		return nullObject;
	}

	/***
	 * Description This method consists an object and checks that is null or not.
	 * This method returns true if object is null else returns false if it is not
	 * null.
	 * 
	 * @param object
	 * @return true if object is null else returns false if it is not null.
	 * 
	 *
	 * @return boolean
	 */
	public static boolean isNull(Object object) {

		return object == null;
	}

	/***
	 * Description This method consists an object and checks that is null or not.
	 * This method returns false if object is null else returns true if it is not
	 * null.
	 * 
	 * @param object
	 * @return false if object is null else returns true if it is not null.
	 * 
	 *
	 * @return boolean
	 */
	public static boolean isNotNull(Object object) {

		return object != null;
	}

	/**
	 * This method checks that object is null or is empty. This method returns true
	 * if object is null or is empty and returns false if an object is not null or
	 * is not empty.
	 * 
	 * @param object
	 * 
	 * @return boolean
	 */
	public static String assignStringData(String value) {
		if (Util.isNullOrEmpty(value)) {
			value = null;
		}
		return value;
	}

	public static boolean isNullOrEmpty(Object object) {

		// checks if Object is not null
		if (object != null) {

			// if object is not null then checks if it is of String type
			if (object instanceof String) {
				// if object is instance of String then check if it is blank or not
				return ("").equals(object);
			} else if (object instanceof List) {
				// if object is instance of String then check it is blank or not
				return ((List<?>) object).isEmpty();
			} else if (object instanceof Map) {
				// if object is instance of String then check it is blank or not
				return ((Map<?, ?>) object).isEmpty();
			}

			else {

				return false;
			}

		} else {
			return true;
		}

	}

	/**
	 * This method checks that object is not null or is not empty. This method
	 * returns true if object is not null or is not empty and returns false if an
	 * object is null or empty.
	 * 
	 * @param object
	 * 
	 * @return boolean
	 */
	public static boolean isNotNullOrEmpty(Object object) {

		// check Object is not null
		if (object != null && !object.equals("null") && !object.equals("undefine")) {

			// if object is not null then check for it is of String type
			if (object instanceof String) {
				// if object is instance of String then check it is blank or not
				return !("").equals(object);
			} else if (object instanceof List) {
				// if object is instance of String then check it is blank or not
				return !((List<?>) object).isEmpty();
			} else if (object instanceof Map) {
				// if object is instance of String then check it is blank or not
				return !((Map<?, ?>) object).isEmpty();
			}

			else {

				return true;
			}

		} else {
			return false;
		}

	}

	public static String getPropertyValue(String filePath, String key) throws IOException {

		try {
			InputStream inputStream = Util.class.getResourceAsStream(filePath);

			Properties properties = new Properties();

			properties.load(inputStream);

			return properties.getProperty(key);

		} catch (Exception e) {
			logger.error(Constants.EXCEPTION, e);
			return null;
		}
	}

	public static String getMessage(String bundle, String key) {

		ResourceBundle messages = null;
		if (isNotNullOrEmpty(bundle) && isNotNullOrEmpty(key)) {
			messages = ResourceBundle.getBundle(bundle);
			return messages.getString(key);
		} else {
			return "";
		}
	}

	public static String generateRandomString(int captchaLength) {

		String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder captchaStrBuffer = new StringBuilder();

		try {
			Random rnd = SecureRandom.getInstanceStrong();
			// build a random captchaLength chars salt
			while (captchaStrBuffer.length() < captchaLength) {
				int index = (rnd.nextInt() * saltChars.length());
				captchaStrBuffer.append(saltChars.substring(index, index + 1));
			}
		} catch (NoSuchAlgorithmException e) {

			logger.error(Constants.EXCEPTION, e);

		}

		return captchaStrBuffer.toString();

	}

	public static String encrypt(String text) {
		return encryptDecryptCommon(Cipher.ENCRYPT_MODE, text);
	}

	public static String decrypt(String text) {
		return encryptDecryptCommon(Cipher.DECRYPT_MODE, text);
	}

	public static String encryptDecryptCommon(Integer mode, String text) {
		try {
			Cipher cipher = Cipher.getInstance(Constants.CONSTANT_ENCRYPTION_KEY);
			byte[] keyBytes = new byte[16];
			byte[] b = KEY.getBytes(StandardCharsets.UTF_8);
			int len = b.length;
			if (len > keyBytes.length)
				len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGO);
			IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
			cipher.init(mode, keySpec, ivSpec);

			if (mode == Cipher.ENCRYPT_MODE) {
				byte[] results = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
				return Base64.getEncoder().encodeToString(results);
			} else {
				byte[] results = cipher.doFinal(Base64.getDecoder().decode(text));
				return new String(results, StandardCharsets.UTF_8);
			}

		} catch (Exception e) {
			logger.error(Constants.EXCEPTION, e);
			return null;
		}
	}

	public static String encrypt(Object value) {

		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(Constants.CONSTANT_ENCRYPTION_KEY);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(serialize(value));
			return org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);

		} catch (Exception ex) {
			logger.error(Constants.EXCEPTION, ex);
		}
		return null;
	}

	public static Object decryptAuthKey(String encrypted) {

		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(Constants.CONSTANT_ENCRYPTION_KEY);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encrypted));

			return deserialize(original);
		} catch (Exception ex) {
			logger.error(Constants.EXCEPTION, ex);
		}

		return null;
	}

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {

		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	public static void setLogs(String message) {
		Authentication authenticationSession = SecurityContextHolder.getContext().getAuthentication();
		AppUser httpSession = (AppUser) authenticationSession.getPrincipal();
		httpSession.getSessionDTOFromWidget().getClientName();
		logger.info("{} - {} - {} - {} : {}", httpSession.getSessionDTOFromWidget().getClientName(),
				httpSession.getSessionDTOFromWidget().getCountry(),
				httpSession.getSessionDTOFromWidget().getBusinessUnit(),
				httpSession.getSessionDTOFromWidget().getEmployeeId(), message);
	}

	public static void setLogsException(String message, Exception exception) {
		Authentication authenticationSession = SecurityContextHolder.getContext().getAuthentication();
		AppUser httpSession = (AppUser) authenticationSession.getPrincipal();
		httpSession.getSessionDTOFromWidget().getClientName();
		logger.info("{} - {} - {} - {} : {}  -Line Number: {} || {}",
				httpSession.getSessionDTOFromWidget().getClientName(),
				httpSession.getSessionDTOFromWidget().getCountry(),
				httpSession.getSessionDTOFromWidget().getBusinessUnit(),
				httpSession.getSessionDTOFromWidget().getEmployeeId(), message,
				exception.getStackTrace()[0].getLineNumber(), exception.getStackTrace()[0].getClassName());
		logger.info(exception.getMessage(), exception);
	}

	public static SessionDTO getSessionVariables() {
		Authentication authenticationSession = SecurityContextHolder.getContext().getAuthentication();
		AppUser httpSession = (AppUser) authenticationSession.getPrincipal();
		return httpSession.getSessionDTOFromWidget();
	}

	public static String getComponentRequestBody(Object logMsg) {
		String jsonStringbal = null;
		try {
			ObjectMapper mapperBalance = new ObjectMapper();
			jsonStringbal = mapperBalance.writeValueAsString(logMsg);
		} catch (Exception ex) {
			logger.info("Error in getComponentRequestBody ->{}", ex.getMessage(), ex);
		}
		return jsonStringbal;
	}

	public static String camelToSnake(String value) {
		value = value.substring(0, 1).toUpperCase() + value.substring(1);
		return value.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

	public static String snakeToCamel(String value) {
		value = value.substring(0, 1).toLowerCase() + value.substring(1);
		return value.replaceAll("\\s", "");
	}

	public static String replaceApiPlaceHolder(String api, String textToReplace) {
		String[] apiParts = api.split("/");

		for (int i = 0; i < apiParts.length; i++) {
			String part = apiParts[i];

			if (part.contains("{") && part.contains("}")) {
				apiParts[i] = textToReplace;
				break;
			}
		}

		return String.join("/", apiParts);
	}
}
