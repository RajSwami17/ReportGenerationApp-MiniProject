package com.report.reports;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.report.response.SearchResponse;

public class ExcelReportGenerator {

	public void generateExcel(List<SearchResponse> records,HttpServletResponse httpResponse)throws Exception
	{
		//with this we create the workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//With this we create the sheet with given name
		HSSFSheet sheet = workbook.createSheet("Plans");
		
		//with this we select the row of the given sheet
		HSSFRow headerRow = sheet.createRow(0);//row index always start from 0th index
		//it will set the cell value whatever cell value we want.
		headerRow.createCell(0).setCellValue("Sr.No");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Holder SSN");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");
		headerRow.createCell(8).setCellValue("Denial Reason");
		
		for(int i=0;i<records.size();i++)
		{
			//it will add the values to each cell as per data retrieved.
			HSSFRow dataRow = sheet.createRow(i+1);
			
			SearchResponse record = records.get(i);
			
			dataRow.createCell(0).setCellValue(i+1);
			if(record.getHolderName()!=null)
				dataRow.createCell(1).setCellValue(record.getHolderName());
			if(record.getHolderSsn()!=null)
				dataRow.createCell(2).setCellValue(record.getHolderSsn());
			if(record.getPlanName()!=null)
				dataRow.createCell(3).setCellValue(record.getPlanName());
			if(record.getPlanStatus()!=null)
				dataRow.createCell(4).setCellValue(record.getPlanStatus());
			if(record.getStartDate()!=null)
				dataRow.createCell(5).setCellValue(String.valueOf(record.getStartDate()));//here these field are date type so thats why we convert it into string.
			if(record.getEndDate()!=null)
				dataRow.createCell(6).setCellValue(String.valueOf(record.getEndDate()));
			if(record.getBenefitAmt()!=null)
				dataRow.createCell(7).setCellValue(String.valueOf(record.getBenefitAmt()));
			if(record.getDenialReason()!=null)
			dataRow.createCell(8).setCellValue(record.getDenialReason());	
		}
		workbook.write(httpResponse.getOutputStream());
		workbook.close();
	}
}
