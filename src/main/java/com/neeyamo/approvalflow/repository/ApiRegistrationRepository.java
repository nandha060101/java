package com.neeyamo.approvalflow.repository;

import java.util.List;

import com.neeyamo.approvalflow.pojo.TbApprovalApiReg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApiRegistrationRepository extends JpaRepository<TbApprovalApiReg, Integer> {

	// fetch all data
	@Query("Select c from TbApprovalApiReg c where c.isActive=true")
	Page<TbApprovalApiReg> fetchApiRegistartionData(Pageable pageable);
	
	// To find total count
	Integer countByIsActiveTrue();

	// query for duplicate app code checkup
	@Query(value = "select p from TbApprovalApiReg p where p.applicationCode = :appCode and p.isActive = 1")
	List<TbApprovalApiReg> duplicateCheck(@Param(value = "appCode") String appCode);

	// query for duplicate app code checkup during edit
	@Query(value = "select p from TbApprovalApiReg p where p.applicationCode = :appCode and p.isActive = 1 and p.apiRegId <> :apiRegId")
	List<TbApprovalApiReg> duplicateCheckForEdit(@Param(value = "appCode") String appCode,
			@Param(value = "apiRegId") Integer apiRegId);

	// delete
	@Modifying
	@Transactional
	@Query("update TbApprovalApiReg p set p.isActive = 0 where p.apiRegId = :apiRegId")
	void deleteApiRegData(@Param(value = "apiRegId") Integer apiRegId);

	// fetch specific applications data
	@Query("select p from TbApprovalApiReg p where p.applicationCode = :applicationCode and p.isActive = 1")
	TbApprovalApiReg fetchApplicationsApiDatas(@Param(value = "applicationCode") String applicationCode);

}
