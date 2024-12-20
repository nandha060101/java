package com.neeyamo.approvalflow.service;

import java.util.List;

import com.neeyamo.approvalflow.dto.ApiRegistrationDTO;
import com.neeyamo.approvalflow.dto.ResponseDTO;

public interface ApiRegistrationService {

	ApiRegistrationDTO fetchApiRegisteredData(int currentPage, int itemsPerPageToDisplay, String textToSearch, List<String> columnsToSearch);

	ResponseDTO saveApiRegData(ApiRegistrationDTO apiRegistrationDTO);

	ResponseDTO deleteApiRegData(Integer apiRegId);

}
