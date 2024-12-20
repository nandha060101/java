package com.neeyamo.approvalflow.apiaccess;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.neeyamo.approvalflow.dto.SessionDTO;
import com.neeyamo.approvalflow.security.AppUser;
import com.neeyamo.approvalflow.utility.Constants;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
	private ObjectMapper objectMapper = new ObjectMapper();

	public CustomJwtAuthenticationConverter(String resourceId) {
	}

	@Override
	public AbstractAuthenticationToken convert(final Jwt source) {

		objectMapper.registerModule(new JavaTimeModule());
		JsonNode jsonNode = objectMapper.valueToTree(source.getClaims());
		SessionDTO sessionDTO = new SessionDTO();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		jsonNode.at("/resource_access/neosuite-admin/roles")
				.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.textValue())));
		sessionDTO.setPrefferedName(jsonNode.at("/additionalDetails/mdm/firstName").textValue() + " "
				+ jsonNode.at("/additionalDetails/mdm/lastName").textValue());
		sessionDTO.setFirstName(jsonNode.at("/additionalDetails/mdm/firstName").textValue());
		sessionDTO.setLastName(jsonNode.at("/additionalDetails/mdm/lastName").textValue());
		sessionDTO.setEmail(jsonNode.at("/additionalDetails/mdm/email").textValue());
		sessionDTO.setGlobalEmployeeId(jsonNode.at("/preferred_username").textValue());
		sessionDTO.setEmployeeId(jsonNode.at("/preferred_username").textValue());
		sessionDTO.setClientCode(jsonNode.at("/additionalDetails/mdm/clientCode").textValue());
		sessionDTO.setCountryCode(jsonNode.at("/additionalDetails/mdm/countryCode").textValue());
		sessionDTO.setBusinessUnit(jsonNode.at("/additionalDetails/mdm/buCode").textValue());
		sessionDTO.setBuCode(jsonNode.at("/additionalDetails/mdm/buCode").textValue());
		ArrayNode arrayNode = (ArrayNode) jsonNode.at("/additionalDetails/neosuite/appRoles");
		for (int i = 0; i < arrayNode.size(); i++) {
			if (arrayNode.get(i).get("appCode").asText().equals(Constants.APP_CODE)) {
				sessionDTO.setAppCode(arrayNode.get(i).get("appCode").asText());
				ArrayNode roles = (ArrayNode) arrayNode.get(i).get("roles");
				sessionDTO.setRoleName(roles.get(0).get("roleCode").asText());
				grantedAuthorities.add(new SimpleGrantedAuthority(roles.get(0).get("roleCode").asText()));
			}
		}
		AppUser user = new AppUser(sessionDTO.getEmployeeId(), "", grantedAuthorities, sessionDTO);
		return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);

	}
}