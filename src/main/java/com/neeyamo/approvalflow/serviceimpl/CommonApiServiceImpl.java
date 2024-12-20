package com.neeyamo.approvalflow.serviceimpl;

import com.neeyamo.approvalflow.dto.NeosuiteDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.dto.SnsDTO;
import com.neeyamo.approvalflow.service.CommonApiService;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;
import com.neeyamo.approvalflow.webservices.CustomerOnboardingWebService;
import com.neeyamo.approvalflow.webservices.Neosuitewebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonApiServiceImpl implements CommonApiService {

	@Autowired
	Neosuitewebservice neosuitewebservice;

	@Autowired
	CustomerOnboardingWebService customerOnboardingWebService;

	@Override
	public NeosuiteDTO fetchAppDetails() {
		return neosuitewebservice.fetchApplicationDetails();
	}

	// 21-Aug-24 - Guruprasath - customer onboarding api call
	@Override
	public void updateCustomerOnboarding(ResponseDTO response, SnsDTO snsDTO, SnsDTO applicationData) {
		SnsDTO customerOnboardingRequest = new SnsDTO();
		customerOnboardingRequest.setClientCode(Util.assignStringData(snsDTO.getSubscribedProducts().getClientCode()));
		customerOnboardingRequest.setClientName(Util.assignStringData(snsDTO.getSubscribedProducts().getClientName()));
		customerOnboardingRequest
				.setCountryCode(Util.assignStringData(snsDTO.getSubscribedProducts().getCountryCode()));
		customerOnboardingRequest
				.setCountryName(Util.assignStringData(snsDTO.getSubscribedProducts().getCountryName()));
		customerOnboardingRequest
				.setLegalEntityCode(Util.assignStringData(snsDTO.getSubscribedProducts().getLegalEntityCode()));
		customerOnboardingRequest
				.setLegalEntityName(Util.assignStringData(snsDTO.getSubscribedProducts().getLegalEntityName()));
		customerOnboardingRequest
		.setCreatedBy(Util.assignStringData(snsDTO.getSubscribedProducts().getCreatedBy()));

		customerOnboardingRequest.setAppCode(Util.assignStringData(applicationData.getAppCode()));
		customerOnboardingRequest.setAppName(Util.assignStringData(applicationData.getAppName()));
		customerOnboardingRequest.setAppType(Util.assignStringData(applicationData.getAppType()));

		SnsDTO payload = new SnsDTO();
		payload.setStatusType(Constants.AUTO_DB);
		payload.setRemarks(response.getDescription());
		payload.setStatus(response.getStatus());
		customerOnboardingRequest.setPayload(payload);
		customerOnboardingWebService.sendResponseToCustomerOnboarding(customerOnboardingRequest);
	}
}
