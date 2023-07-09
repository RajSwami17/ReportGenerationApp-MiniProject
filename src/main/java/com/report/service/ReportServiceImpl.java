package com.report.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.report.entity.EligibilityDetails;
import com.report.repo.IEligibilityDetailsRepo;
import com.report.request.SearchRequest;
import com.report.response.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private IEligibilityDetailsRepo detailsRepo;
	
	@Override
	public List<String> getPlanNames() {
		return detailsRepo.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return detailsRepo.getUniquePlanStatuses();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		List<EligibilityDetails> eligRecords = null;
		if(isSearchReqEmpty(request))
		{
			eligRecords = detailsRepo.findAll();
		}
		else
		{
			//user can select only on plan name and click on search button
			//user can select only on plan status and click on search button
			//user can select both plan name and plan status and click on search button
			//user can select startDate and endDate and click on search button
			//user can select plan name,start date and end date and click on search button
			//user can select plan status,start date and end date and click on search button
			//user can select plan name,plan status,start date,end date and click on search button
			//only select on search button
			
			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();
			
			EligibilityDetails details = new EligibilityDetails();
			
			if(planName != null && !planName.equals(""))
			{
				//add plan name to where clause
				details.setPlanName(planName);
			}
			if(planStatus != null && !planStatus.equals(""))
			{
				//add plan name to where clause
				details.setPlanStatus(planStatus);
			}
			if(startDate != null && endDate != null)
			{
				//add start date and end date to the where clause
				details.setStartDate(startDate);
				details.setEndDate(endDate);
			}
			
			//query by Example in data jpa
			Example<EligibilityDetails> of = Example.of(details);
			eligRecords = detailsRepo.findAll(of);
		}
		List<SearchResponse> response = new ArrayList<>();
		for(EligibilityDetails eligRecord : eligRecords)
		{
			SearchResponse sr = new SearchResponse();//create the object for copy the records which is eligible as per user search.
			BeanUtils.copyProperties(eligRecord, sr);//here we copy the eligRecord object to sr object.
			response.add(sr);//add the records to list object
		}
		return response;
	}
	private boolean isSearchReqEmpty(SearchRequest request)
	{ 
		if(request.getPlanName()!=null && !request.getPlanName().equals(""))
		{
			return false;
		}
		if(request.getPlanStatus()!=null && !request.getPlanStatus().equals(""))
		{
			return false;
		}
		if(request.getStartDate()!=null && !request.getStartDate().equals(""))
		{
			return false;
		}
		if(request.getEndDate()!=null && !request.getEndDate().equals(""))
		{
			return false;
		}
		return true;
	}
}
