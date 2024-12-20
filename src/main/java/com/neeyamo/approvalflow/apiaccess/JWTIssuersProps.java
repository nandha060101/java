package com.neeyamo.approvalflow.apiaccess;

import java.util.ArrayList;
import java.util.List;

import com.neeyamo.approvalflow.pojo.TbApprovalMasClient;
import com.neeyamo.approvalflow.repository.ApprovalFlowMasClientRepository;
import com.neeyamo.approvalflow.utility.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
public class JWTIssuersProps {
	@Autowired
	ApprovalFlowMasClientRepository approvalFlowMasClientRepository;
	private List<String> issuers;

	public List<String> getIssuers() {
		if (issuers != null) {
			String temp = issuers.get(0);
			issuers = new ArrayList<>();
			issuers.add(temp);
			List<TbApprovalMasClient> dto = approvalFlowMasClientRepository.checkClientData();
			if (Util.isNotNullOrEmpty(dto)) {
				for (int i = 0; i < dto.size(); i++) {
					String issuer = issuers.get(0) + dto.get(i).getClientCode();
					issuers.add(issuer);
				}
			}
		}
		return issuers;
	}

	public void setIssuers(List<String> issuers) {
		this.issuers = issuers;
	}

	public void addIssuer(String issuer) {
		// do nothing

	}
}