package com.report.service;

import java.util.List;

import com.report.request.SearchRequest;
import com.report.response.SearchResponse;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<SearchResponse> searchPlans(SearchRequest request);
	
	//public void exportExcel(List<SearchResponse> records);
	
	//public void exportPdf(List<SearchResponse> records);
	
}
 