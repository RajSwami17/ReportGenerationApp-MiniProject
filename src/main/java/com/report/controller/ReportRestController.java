package com.report.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.report.reports.ExcelReportGenerator;
import com.report.reports.PdfReportGenerator;
import com.report.request.SearchRequest;
import com.report.response.SearchResponse;
import com.report.service.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/plan-names")
	public List<String> getPlanNames()
	{
		return reportService.getPlanNames();
	}
	
	@GetMapping("/plan-statuses")
	public List<String> getPlanStatuses()
	{
		return reportService.getPlanStatuses();
	}
	
	@PostMapping("/search")
	public List<SearchResponse> getAllRecords(@RequestBody SearchRequest request)
	{
		return reportService.searchPlans(request);
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response)throws Exception
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Plans.xls";
		
		response.addHeader(headerKey, headerValue);
		
		List<SearchResponse> records = reportService.searchPlans(null);
		ExcelReportGenerator excel = new ExcelReportGenerator();
		excel.generateExcel(records, response);
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response)throws Exception
	{
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Plans.pdf";
		
		response.addHeader(headerKey, headerValue);
		
		List<SearchResponse> records = reportService.searchPlans(null);
		PdfReportGenerator pdf = new PdfReportGenerator();
		pdf.generatePdf(records, response);
	}
	
}
