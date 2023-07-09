package com.report.reports;

import java.awt.Color;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.report.response.SearchResponse;

public class PdfReportGenerator 
{
	public void generatePdf(List<SearchResponse> records,HttpServletResponse httpResponse)throws Exception
	{
		//it will create the document	
		Document document = new Document();
		
		PdfWriter writer = PdfWriter.getInstance(document, httpResponse.getOutputStream());
		
		Font font = new Font(Font.HELVETICA,16,Font.BOLDITALIC,Color.RED);
		
		document.open();
		
		Paragraph para = new Paragraph("Eligibility Details",font);
		para.setAlignment(Element.ALIGN_CENTER);
		document.add(para);
		
		PdfPTable table = new PdfPTable(9);
		
		table.addCell("Sr.No");
		table.addCell("Holder Name");
		table.addCell("Holder SSN");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		table.addCell("Denial Reason");	
		int sno = 1;
		for(SearchResponse record : records)
		{
			table.addCell(String.valueOf(sno));
			table.addCell(record.getHolderName());
			table.addCell(String.valueOf(record.getHolderSsn()));
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
			table.addCell(String.valueOf(record.getStartDate()));
			table.addCell(String.valueOf(record.getEndDate()));
			table.addCell(String.valueOf(record.getBenefitAmt()));
			table.addCell(record.getDenialReason());	
			sno++;
		}
		table.setWidthPercentage(100);
		document.add(table);
		document.close();
		writer.close();
	}
}
