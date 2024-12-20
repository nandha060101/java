package com.neeyamo.approvalflow.service;

import com.neeyamo.approvalflow.dto.NeosuiteDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;
import com.neeyamo.approvalflow.dto.SnsDTO;

public interface CommonApiService {

	NeosuiteDTO fetchAppDetails();

	void updateCustomerOnboarding(ResponseDTO response, SnsDTO snsDTO, SnsDTO applicationData);

}
