package com.neeyamo.approvalflow.repository;

import java.util.List;

import com.neeyamo.approvalflow.pojo.TbApprovalTransLog;
import com.neeyamo.approvalflow.service.StatusCount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApprovalFlowTransLogRepository extends JpaRepository<TbApprovalTransLog, Long> {

	@Query("Select c from TbApprovalTransLog c where c.transactionId=:transactionId and c.applicationCode=:applicationCode and c.isActive=true ")
	List<TbApprovalTransLog> fetchApprovalFlowTransLog(@Param("transactionId") String transactionId,
			@Param("applicationCode") String applicationCode);

	// find all approval datas using assigned to
	@Query("Select c from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') LIKE "
			+ "CONCAT('%,', :assignedTo, ',%')) and "
			+ "c.applicationCode = :appCode and c.status ='Open' and c.isActive=true order by c.approvalTransLogId DESC")
	Page<TbApprovalTransLog> fetchApprovalRecordsToMe(@Param("assignedTo") String assignedTo,
			@Param("appCode") String appCode, Pageable pageable);
	
	@Query("Select c from TbApprovalTransLog c where c.employeeId = :assignedBy and "
			+ "c.applicationCode = :appCode and c.status ='Open' and c.isActive=true order by c.approvalTransLogId DESC")
	Page<TbApprovalTransLog> fetchApprovalRecordsByMe(@Param("assignedBy") String assignedBy,
			@Param("appCode") String appCode, Pageable pageable);

	// find total count
	@Query("Select count(c) from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') LIKE "
			+ "CONCAT('%,', :assignedTo, ',%')) and "
			+ "c.applicationCode = :appCode and c.status ='Open' and c.isActive=true order by c.approvalTransLogId DESC")
	Integer findTotalCountToMe(@Param("assignedTo") String assignedTo,@Param("appCode") String appCode);
	
	@Query("Select count(c) from TbApprovalTransLog c where c.employeeId = :assignedBy and "
			+ "c.applicationCode = :appCode and c.status ='Open' and c.isActive=true order by c.approvalTransLogId DESC")
	Integer findTotalCountByMe(@Param("assignedBy") String assignedBy, @Param("appCode") String appCode);

	///23-oct-2024 Peter ALphonse A(ET1348) ByMe OnMe issue
	// find all approval datas using assigned to and app events
	@Query("Select c from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') LIKE "
			+ "CONCAT('%,', :assignedTo, ',%')) and "
			+ "c.applicationCode = :appCode and c.appEvents = :appEvent and c.status ='Open' "
			+ "and c.isActive=true order by c.approvalTransLogId DESC")
	Page<TbApprovalTransLog> fetchApprovalRecordsToMe(@Param("assignedTo") String assignedTo,
			@Param("appCode") String appCode, @Param("appEvent") String appEvent, Pageable pageable);

	///23-oct-2024 Peter ALphonse A(ET1348) ByMe OnMe issue
	// find total count with appevents
	@Query("Select count(c) from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') LIKE "
			+ "CONCAT('%,', :assignedTo, ',%')) and "
			+ "c.applicationCode = :appCode and c.appEvents = :appEvent and c.status ='Open' and c.isActive=true")
	Integer findTotalCountToMe(@Param("assignedTo") String assignedTo, @Param("appCode") String appCode,
			@Param("appEvent") String appEvent);

	// fetch distinct Team approvals for Advanced filter
	@Query("Select DISTINCT c.appEvents from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') "
			+ "LIKE CONCAT('%,', :assignedTo, ',%') or c.employeeId = :assignedTo) and c.isActive=true")
	List<String> fetchTeamApprovalsList(@Param("assignedTo") String assignedTo);

	// fetch distinct Team Application Name for Advanced filter
	@Query("Select DISTINCT c.applicationName from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') "
			+ "LIKE CONCAT('%,', :assignedTo, ',%') or c.employeeId = :assignedTo) and c.isActive=true")
	List<String> fetchTeamApplicationName(@Param("assignedTo") String assignedTo);

	// fetch distinct Team status for Advanced filter
	@Query("Select DISTINCT c.status from TbApprovalTransLog c where (CONCAT(',', c.assignedTo, ',') "
			+ "LIKE CONCAT('%,', :assignedTo, ',%') or c.employeeId = :assignedTo) and c.isActive=true")
	List<String> fetchTeamStatus(@Param("assignedTo") String assignedTo);

	@Query("SELECT c.applicationName as applicationName,c.applicationCode as applicationCode,"
			+ " COUNT(*) as count FROM " + "TbApprovalTransLog c "
			+ "WHERE (CONCAT(',', c.assignedTo, ',') LIKE CONCAT('%,', :assignedTo, ',%') "
			+ ") and c.status = 'Open' " 
			+ "and c.isActive=true " + "GROUP BY c.applicationCode order by applicationName")
	List<StatusCount> myTeamApprovalsStatusToMeCount(@Param("assignedTo") String assignedTo);
	
	@Query("SELECT c.applicationName as applicationName,c.applicationCode as applicationCode,"
			+ " COUNT(*) as count FROM " + "TbApprovalTransLog c "
			+ "WHERE c.employeeId = :assignedBy and c.status = 'Open' " 
			+ "and c.isActive=true " + "GROUP BY c.applicationCode order by applicationName")
	List<StatusCount> myTeamApprovalsStatusByMeCount(@Param("assignedBy") String assignedTo);

	@Query("SELECT c.appEvents as appEvents," + " COUNT(*) as appEventsCount FROM " + "TbApprovalTransLog c "
			+ "WHERE (CONCAT(',', c.assignedTo, ',') LIKE CONCAT('%,', :assignedTo, ',%') "
			+ ") and c.applicationName = :appName and c.status = 'Open' " + "and c.isActive=true " + "GROUP BY c.appEvents"
					+ " order by appEvents")
	List<StatusCount> fetchAppEventsToMeCount(@Param("assignedTo") String assignedTo,
			@Param("appName") String appName);
	
	@Query("SELECT c.appEvents as appEvents," + " COUNT(*) as appEventsCount FROM " + "TbApprovalTransLog c "
			+ "WHERE (c.employeeId = :assignedTo) and c.applicationName = :appName and c.status = 'Open' " + "and c.isActive=true " + "GROUP BY c.appEvents"
			+ " order by appEvents")
	List<StatusCount> fetchAppEventsByMeCount(@Param("assignedTo") String assignedTo,
			@Param("appName") String appName);
	

	// update status
	@Modifying
	@Transactional
	@Query("UPDATE TbApprovalTransLog p SET p.status = :status WHERE p.transactionId in (:transactionList) AND p.isActive = true and p.applicationCode=:applicationCode")
	void updateStatus(@Param("transactionList") List<String> transactionList, @Param("status") String status,@Param("applicationCode") String applicationCode);

	///23-oct-2024 Peter ALphonse A(ET1348) ByMe OnMe issue
	@Query("Select c from TbApprovalTransLog c where c.employeeId = :assignedBy and "
			+ "c.applicationCode = :appCode and c.status ='Open' and c.isActive=true and c.appEvents = :appEvent order by c.approvalTransLogId DESC")
	Page<TbApprovalTransLog> fetchApprovalRecordsByMe(@Param("assignedBy") String assignedBy,
			@Param("appCode") String appCode, @Param("appEvent") String appEvent, Pageable pageable);
	
	@Query("Select count(c) from TbApprovalTransLog c where c.employeeId = :assignedBy and "
			+ "c.applicationCode = :appCode and c.status ='Open' and c.isActive=true and c.appEvents = :appEvent order by c.approvalTransLogId DESC")
	Integer findTotalCountByMe(@Param("assignedBy") String assignedBy, @Param("appCode") String appCode,
			@Param("appEvent") String appEvent);
}
