package com.neeyamo.approvalflow.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.neeyamo.approvalflow.dto.ApprovalFlowDTO;
import com.neeyamo.approvalflow.dto.EssAppEventsDTO;
import com.neeyamo.approvalflow.dto.EssCountDTO;
import com.neeyamo.approvalflow.dto.EssStatusDTO;
import com.neeyamo.approvalflow.dto.MDMServiceDTO;
import com.neeyamo.approvalflow.dto.SessionDTO;
import com.neeyamo.approvalflow.pojo.TbApprovalMasClient;
import com.neeyamo.approvalflow.pojo.TbApprovalTransLog;
import com.neeyamo.approvalflow.repository.ApprovalFlowMasClientRepository;
import com.neeyamo.approvalflow.repository.ApprovalFlowTransLogRepository;
import com.neeyamo.approvalflow.security.AppUser;
import com.neeyamo.approvalflow.service.PrinmergeService;
import com.neeyamo.approvalflow.service.StatusCount;
import com.neeyamo.approvalflow.utility.Constants;
import com.neeyamo.approvalflow.utility.Util;
import com.neeyamo.approvalflow.webservices.MDMWebService;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ApprovalFlowDAO {
	@PersistenceContext
	private EntityManager entityManage;
	@Autowired
	ApprovalFlowTransLogRepository approvalFlowTransLogRepository;
	@Autowired
	ApprovalFlowMasClientRepository approvalFlowMasClientRepository;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Autowired
	PrinmergeService prinmergeService;
	@Autowired
	MDMWebService mdmWebService;

	private static final Logger logger = LoggerFactory.getLogger(ApprovalFlowDAO.class);

	public ApprovalFlowDTO insertApprovalFlowTransactionsLogs(ApprovalFlowDTO approvalFlowDTO) {
		logger.info("Begin : insertApprovalFlowTransactionsLogs in class: {}", getClass().getName());
		try {
			TbApprovalTransLog tbDataFlowApplicationTransLogs = new TbApprovalTransLog();
			if (Util.isNotNullOrEmpty(approvalFlowDTO.getApprovalTransLogId())) {
				tbDataFlowApplicationTransLogs = entityManage.find(TbApprovalTransLog.class,
						approvalFlowDTO.getApprovalTransLogId());
				tbDataFlowApplicationTransLogs.setUpdatedBy(approvalFlowDTO.getCreatedBy());
				tbDataFlowApplicationTransLogs.setUpdatedOn(new Date());
			} else {
				tbDataFlowApplicationTransLogs = new TbApprovalTransLog();
				tbDataFlowApplicationTransLogs.setCreatedBy(approvalFlowDTO.getCreatedBy());
				tbDataFlowApplicationTransLogs.setCreatedOn(new Date());
			}
			tbDataFlowApplicationTransLogs.setTransactionId(approvalFlowDTO.getTransactionId());
			tbDataFlowApplicationTransLogs.setApplicationCode(approvalFlowDTO.getApplicationCode());
			tbDataFlowApplicationTransLogs.setApplicationName(approvalFlowDTO.getApplicationName());
			tbDataFlowApplicationTransLogs.setEmployeeId(approvalFlowDTO.getEmployeeId());

			MDMServiceDTO mdmServiceDTO = mdmWebService.fetchfromMdm(approvalFlowDTO.getClientCode(),
					approvalFlowDTO.getEmployeeId());

			String firstName = "";
			String lastName = "";
			if (Util.isNotNullOrEmpty(mdmServiceDTO)) {
				if (Util.isNotNullOrEmpty(mdmServiceDTO.getPayload().getList())) {
					firstName = mdmServiceDTO.getPayload().getList().get(0).getFirstName();
					if (Util.isNotNullOrEmpty(mdmServiceDTO.getPayload().getList().get(0).getLastName())) {
						lastName = mdmServiceDTO.getPayload().getList().get(0).getLastName();
					}
				}
			}
			tbDataFlowApplicationTransLogs.setEmployeeName(firstName + " " + lastName);

			tbDataFlowApplicationTransLogs.setStatus(approvalFlowDTO.getStatus());
			tbDataFlowApplicationTransLogs.setAppEvents(approvalFlowDTO.getAppEvents());

			if (Util.isNotNullOrEmpty(approvalFlowDTO.getApplicationData())) {
				tbDataFlowApplicationTransLogs.setApplicationData(approvalFlowDTO.getApplicationData());
			} else {
				tbDataFlowApplicationTransLogs.setApplicationData(null);
			}

			if (Util.isNotNullOrEmpty(approvalFlowDTO.getActionItems())) {
				tbDataFlowApplicationTransLogs.setActionItems(approvalFlowDTO.getActionItems());
			} else {
				tbDataFlowApplicationTransLogs.setActionItems(null);
			}
			
			if (Util.isNotNullOrEmpty(approvalFlowDTO.getAssignedBy())) {
				tbDataFlowApplicationTransLogs.setAssignedBy(approvalFlowDTO.getAssignedBy());
			}
			if (Util.isNotNullOrEmpty(approvalFlowDTO.getAssignedTo())) {
				tbDataFlowApplicationTransLogs.setAssignedTo(approvalFlowDTO.getAssignedTo());
			}
			tbDataFlowApplicationTransLogs.setIsActive(true);
			approvalFlowTransLogRepository.save(tbDataFlowApplicationTransLogs);
		} catch (Exception e) {
			logger.info("Exception in method insertApprovalFlowTransactionsLogs ", e);
		}
		logger.info("End : insertApprovalFlowTransactionsLogs in class: {}", getClass().getName());
		return approvalFlowDTO;
	}

	public void insertClient(String clientCode, String tenent) {
		logger.info("Begin : insertClient in class: {}", getClass().getName());
		try {
			TbApprovalMasClient tbApprovalMasClient = new TbApprovalMasClient();
			tbApprovalMasClient.setCreatedBy("Admin");
			tbApprovalMasClient.setCreatedOn(new Date());

			tbApprovalMasClient.setClientCode(clientCode);
			tbApprovalMasClient.setTenent(tenent);

			tbApprovalMasClient.setIsActive(true);

			approvalFlowMasClientRepository.save(tbApprovalMasClient);
		} catch (Exception e) {
			logger.info("Exception in method insertClient ", e);
		}
		logger.info("End : insertClient in class: {}", getClass().getName());
	}

	// fetch application count
	///26-Seb-2024  Peter ALphonse A(ET1348)  New UI added
	public EssCountDTO getAppCount(String option,String assignedTo,String assignedBy) {
		Util.setLogs("BEGIN: getAppCount in class {}" + getClass().getName());
		EssCountDTO essCountDTO = new EssCountDTO();
		try {
			SessionDTO sessionDto = getSessionVariables();

			if (option.equals(Constants.ONE)) {
				List<StatusCount> applicationCount = null;
				if(Util.isNotNullOrEmpty(assignedTo)) {
				applicationCount = approvalFlowTransLogRepository
						.myTeamApprovalsStatusToMeCount(sessionDto.getEmployeeId());
				}
				else if(Util.isNotNullOrEmpty(assignedBy)) {
					applicationCount = approvalFlowTransLogRepository
							.myTeamApprovalsStatusByMeCount(sessionDto.getEmployeeId());
				}

				if (Util.isNotNullOrEmpty(applicationCount)) {
					EssStatusDTO[] essStatus = new EssStatusDTO[applicationCount.size()];
					for (int i = 0; i < applicationCount.size(); i++) {
						if (Util.isNotNullOrEmpty(applicationCount.get(i).getApplicationName())
								&& Util.isNotNullOrEmpty(applicationCount.get(i).getCount())) {
							essStatus[i] = new EssStatusDTO(i + 1, applicationCount.get(i).getApplicationCode(),
									applicationCount.get(i).getApplicationName(), 1, applicationCount.get(i).getCount(),
									Constants.STATUS_CODE_OPEN);
						}

					}
					essCountDTO.setEssStatus(essStatus);
					
					
				}
				List<StatusCount> eventCount = null;
				if(Util.isNotNullOrEmpty(assignedTo)) {
				eventCount = approvalFlowTransLogRepository
						.fetchAppEventsToMeCount(sessionDto.getEmployeeId(), "time");
				}
				else if(Util.isNotNullOrEmpty(assignedBy)) {
					eventCount = approvalFlowTransLogRepository
							.fetchAppEventsByMeCount(sessionDto.getEmployeeId(), "time");
				}

				if (Util.isNotNullOrEmpty(eventCount)) {
					EssAppEventsDTO[] essEventCount = new EssAppEventsDTO[eventCount.size()];
					for (int i = 0; i < eventCount.size(); i++) {
						if (Util.isNotNullOrEmpty(eventCount.get(i).getAppEvents())
								&& Util.isNotNullOrEmpty(eventCount.get(i).getAppEventsCount())) {
							essEventCount[i] = new EssAppEventsDTO(i + 1, eventCount.get(i).getAppEvents(), 1,
									eventCount.get(i).getAppEventsCount(), Constants.STATUS_CODE_OPEN);
						}

					}
					essCountDTO.setEssAppEventsDTO(essEventCount);
				}
			}

			Util.setLogs("END: getAppCount in class {}" + getClass().getName());
		} catch (Exception e) {
			Util.setLogsException("Exception in method getAppCount ", e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "getAppCount", e, null);
		}
		return essCountDTO;
	}

	// fetch Team approval datas
	///26-Seb-2024  Peter ALphonse A(ET1348)  New UI added
	///23-oct-2024 Peter ALphonse A(ET1348) ByMe OnMe issue
	public ApprovalFlowDTO fetchApprovalRecords(int noOfRecords, int pageNumber, String elementToSearch,
			List<String> columnsToSearch, String appCode, String appEvent, List<String> jsonData,String assignedTo,String assignedBy) {
		Util.setLogs("Begins: fetchMyTeamApprovals in class:" + getClass().getName());
		ApprovalFlowDTO approvalFlowDTO = new ApprovalFlowDTO();
		List<TbApprovalTransLog> teamApprovals = null;
		Integer totalCount = 0;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		try {
			SessionDTO sessionDto = getSessionVariables();
			if (elementToSearch.equals("null") || elementToSearch.equals("") || elementToSearch.equals("undefined")) {

				Pageable pageable = PageRequest.of(pageNumber - 1, noOfRecords,
						Sort.by("approvalTransLogId").descending());

				if (Util.isNullOrEmpty(appEvent) || appEvent.equals("null")) {
					if(Util.isNotNullOrEmpty(assignedTo)) {
					teamApprovals = approvalFlowTransLogRepository
							.fetchApprovalRecordsToMe(assignedTo, appCode, pageable).getContent();
					totalCount = approvalFlowTransLogRepository.findTotalCountToMe(assignedTo, appCode);
					}
					else if(Util.isNotNullOrEmpty(assignedBy)) {
						teamApprovals = approvalFlowTransLogRepository
								.fetchApprovalRecordsByMe(assignedBy, appCode, pageable).getContent();
						totalCount = approvalFlowTransLogRepository.findTotalCountByMe(assignedBy, appCode);
					}

					
				} else {
					if(Util.isNotNullOrEmpty(assignedTo)) {
					teamApprovals = approvalFlowTransLogRepository
							.fetchApprovalRecordsToMe(sessionDto.getEmployeeId(), appCode, appEvent, pageable).getContent();

					totalCount = approvalFlowTransLogRepository.findTotalCountToMe(sessionDto.getEmployeeId(), appCode,
							appEvent);
				}
				else if(Util.isNotNullOrEmpty(assignedBy)) {
					teamApprovals = approvalFlowTransLogRepository
							.fetchApprovalRecordsByMe(sessionDto.getEmployeeId(), appCode, appEvent, pageable).getContent();

					totalCount = approvalFlowTransLogRepository.findTotalCountByMe(sessionDto.getEmployeeId(), appCode,
							appEvent);
				}
				}

				if (Util.isNotNullOrEmpty(teamApprovals)) {
					concatTwoObjects(teamApprovals, approvalFlowDTO);
					approvalFlowDTO.setTotalCount(totalCount);
				}

			} else {
				Pageable pageable = PageRequest.of(pageNumber - 1, noOfRecords);
				Query query = entityManager.createQuery(String.valueOf(fetchSearchQuery(elementToSearch,
						columnsToSearch, sessionDto.getEmployeeId(), appCode, jsonData, appEvent)), TbApprovalTransLog.class);
				approvalFlowDTO.setTotalCount(query.getResultList().size());
				query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
				query.setMaxResults(pageable.getPageSize());
				concatTwoObjects(query.getResultList(), approvalFlowDTO);
			}
		} catch (Exception e) {
			Util.setLogsException("Exception in method fetchMyTeamApprovals ", e);
			prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "fetchMyTeamApprovals", e, null);
		} finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		Util.setLogs("End: fetchMyTeamApprovals in class:" + getClass().getName());
		return approvalFlowDTO;
	}

	// method to concat application data and TbApprovalTransLog
	public void concatTwoObjects(List<TbApprovalTransLog> teamApprovals, ApprovalFlowDTO approvalFlowDTO) {
		List<JSONObject> approvalListJson = new ArrayList<>();
		if (Util.isNotNullOrEmpty(teamApprovals)) {
			ObjectMapper mapper = new ObjectMapper();

			List<String> appDatasList = new ArrayList<>();
			List<String> appDatas = new ArrayList<>();
			JsonNode jsonNode = null;
			try {
				for (int i = 0; i < teamApprovals.size(); i++) {
					if (Util.isNotNullOrEmpty(teamApprovals.get(i).getApplicationData())) {
						jsonNode = mapper.readTree(teamApprovals.get(i).getApplicationData());
						break;
					}
				}
				Iterator<String> iterator = jsonNode.getFieldNames();
				iterator.forEachRemaining(appDatasList::add);
			} catch (IOException e) {
				logger.info("Exception in method concatTwoObjects - Line Number: {} || {}",
						e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
				logger.info(e.getMessage(), e);
				prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "concatTwoObjects", e, null);
			}

			JSONParser parser = new JSONParser();
			for (TbApprovalTransLog myApprovalsList : teamApprovals) {
				JSONObject myApprovalsListJson = null;
				JSONObject applicationDataJson = null;
				JSONObject dynamicHeaders = new JSONObject();
				try {
					myApprovalsListJson = (JSONObject) parser.parse(mapper.writeValueAsString(myApprovalsList));
					if (Util.isNotNullOrEmpty(myApprovalsList.getApplicationData())) {
						applicationDataJson = (JSONObject) parser.parse(myApprovalsList.getApplicationData());

						if (Util.isNotNullOrEmpty(appDatasList)) {
							int appDatasListsize = appDatasList.size();
							for (int i = 0; i < appDatasListsize; i++) {
								dynamicHeaders.put("tagKey" + i, applicationDataJson.getAsString(appDatasList.get(i)));
							}
						}
					}

				} catch (Exception e) {
					logger.info("Exception in method concatTwoObjects - Line Number: {} || {}",
							e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getClassName());
					logger.info(e.getMessage(), e);
					prinmergeService.exceptionMailTrigger(e.getStackTrace()[0].getClassName(), "concatTwoObjects", e,
							null);
				}
				myApprovalsListJson.putAll(dynamicHeaders);

				approvalListJson.add(myApprovalsListJson);
			}
			approvalFlowDTO.setApprovalListJson(Util.isNotNullOrEmpty(approvalListJson) ? approvalListJson : null);
			for (int i = 0; i < appDatasList.size(); i++) {
				String camelToSnake = Util.camelToSnake(appDatasList.get(i));
				appDatas.add(camelToSnake);
			}
			approvalFlowDTO.setAppDatasList(Util.isNotNullOrEmpty(appDatas) ? appDatas : null);
		}
	}

	// create query for Global Search
	private Object fetchSearchQuery(String valueToSerch, List<String> columnsToSearch, String employeeId,
			String appCode, List<String> jsonData, String appEvent) {
		Util.setLogs("BEGIN: fetchSearchQuery in class {}" + getClass().getName());

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT p FROM  TbApprovalTransLog p WHERE (");
		int columnListSize = columnsToSearch.size();
		for (int i = 0; i < columnListSize; i++) {
			queryString.append(
					"p." + columnsToSearch.get(i) + " " + "like" + " " + "'" + "%" + valueToSerch + "%" + "'" + " ");

			queryString.append("or" + " ");

		}
		if (Util.isNotNullOrEmpty(jsonData)) {
			int jsonDataSize = jsonData.size();
			for (int i = 0; i < jsonDataSize; i++) {
				queryString.append("JSON_EXTRACT(applicationData , '$.\"" + Util.snakeToCamel(jsonData.get(i)) + "\"') like " + "'%"
						+ valueToSerch + "%' ");
				if (jsonData.size() != i + 1) {
					queryString.append("or" + " ");
				}
			}
		}
		if(appCode.equals("time")) {
		  queryString.append(") AND p.isActive = true AND (p.assignedTo LIKE '%" + employeeId + "%' or p.employeeId LIKE '%"
					+ employeeId + "%') and p.applicationCode = '" + appCode + "' and p.appEvents = '"+ appEvent + "'"
							+ " and p.status ='Open' ORDER BY p.approvalTransLogId DESC");
		}
		else {
		queryString.append(") AND p.isActive = true AND (p.assignedTo LIKE '%" + employeeId + "%' or p.employeeId LIKE '%"
				+ employeeId + "%') and p.applicationCode = '" + appCode + "' and p.status ='Open'"
				+ " ORDER BY p.approvalTransLogId DESC");
		}

		Util.setLogs("END: fetchSearchQuery in class {}" + getClass().getName());
		return queryString;
	}

	public SessionDTO getSessionVariables() {
		Authentication authenticationSession = SecurityContextHolder.getContext().getAuthentication();
		AppUser httpSession = (AppUser) authenticationSession.getPrincipal();
		return httpSession.getSessionDTOFromWidget();
	}
}
