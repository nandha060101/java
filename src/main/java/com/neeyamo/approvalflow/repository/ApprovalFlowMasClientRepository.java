package com.neeyamo.approvalflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neeyamo.approvalflow.pojo.TbApprovalMasClient;

public interface ApprovalFlowMasClientRepository extends JpaRepository<TbApprovalMasClient, Long>  {
	
	@Query("Select c from TbApprovalMasClient c where c.clientCode=:clientCode and c.isActive=true ")
	List<TbApprovalMasClient> checkClientData(@Param("clientCode") String clientCode);
	
	@Query("Select c from TbApprovalMasClient c where c.isActive=true ")
	List<TbApprovalMasClient> checkClientData();
	
}
