package com.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.report.entity.EligibilityDetails;

@Repository
public interface IEligibilityDetailsRepo extends JpaRepository<EligibilityDetails,Integer>{

	
	//to retrieve specific column data
	@Query("select distinct(planName)from EligibilityDetails")
	public List<String> getUniquePlanNames();
	
	@Query("select distinct(planStatus)from EligibilityDetails")
	public List<String> getUniquePlanStatuses();

}
	