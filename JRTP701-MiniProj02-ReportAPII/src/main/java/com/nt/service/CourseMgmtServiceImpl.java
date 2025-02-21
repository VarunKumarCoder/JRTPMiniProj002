package com.nt.service;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.CourseDetails;
import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.repository.ICourseDetailsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service("courseService")
public class CourseMgmtServiceImpl implements ICourseMgmtService {
	@Autowired
	private ICourseDetailsRepository courseRepo;
	@Override
	public Set<String> showAllCourseCategories() {
		
		return courseRepo.getUniqueCourseCategories();
	}

	@Override
	public Set<String> ShowAllTrainingModes() {
		
		return courseRepo.getUniquetrainingModes();
	}

	@Override
	public Set<String> ShowAllFaculties() {
		return courseRepo.getUniquefacultyNames();
	}

	@Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs) {
		CourseDetails entity = new CourseDetails();
		String category=inputs.getCourseCategory();
		if(StringUtils.hasLength(category))
			entity.setCourseCategory(category);
		String facultyName=inputs.getFacultyName();
		if(StringUtils.hasLength(facultyName))
			entity.setFacultyName(facultyName);
		String trainingMode=inputs.getTrainingMode();
		if(StringUtils.hasLength(trainingMode))
			entity.setTrainingMode(trainingMode);
		LocalDateTime startDate=inputs.getStartsOn();
		if(ObjectUtils.isEmpty(startDate))
			entity.setStartDate(startDate);
		Example<CourseDetails> example=Example.of(entity);
		List<CourseDetails> listEntities=courseRepo.findAll(example);
		List<SearchResults> listResults=new ArrayList();
		listEntities.forEach(course->{
			SearchResults result=new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
		return listResults;
	}

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchResults> listResult=showCoursesByFilters(inputs);
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, res.getOutputStream());
		document.open();
		Font font=FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(30);
		font.setColor(Color.RED);
		Paragraph para=new Paragraph("Search Report Of Cources",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);
		PdfPTable table=new PdfPTable(10);
		table.setWidthPercentage(70);
		table.setWidths(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
		table.setSpacingBefore(2.0f);
		
		//prepare Heading Row Cells in the pdf table
		
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);
		Font cellFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("courseID",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("courseName",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Category",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("FacultyName",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Location",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fee",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("CourseStatus",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TrainingMode",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("AdminContact",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("StartDate",cellFont));
		table.addCell(cell);
		
		listResult.forEach(result->{
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(result.getCourseName());
			table.addCell(result.getCourseCategory());
			table.addCell(result.getFacultyName());
			table.addCell(result.getLocation());
			table.addCell(String.valueOf(result.getFee()));
			table.addCell(result.getCourseStatus());
			table.addCell(result.getTrainingMode());
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(result.getStartDate().toString());	
		});
		
		//Add table to document
		document.add(table);
		//close the document
		document.close();
	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchResults> listResults=showCoursesByFilters(inputs);
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseId");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Location");
		headerRow.createCell(3).setCellValue("CourseCategory");
		headerRow.createCell(4).setCellValue("FacultyName");
		headerRow.createCell(5).setCellValue("Fee");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("trainingMode");
		headerRow.createCell(8).setCellValue("startDate");
		headerRow.createCell(9).setCellValue("CourseStatus");
		
		int i=1;
		for(SearchResults results:listResults) {
			HSSFRow dataRow=sheet1.createRow(i);
			dataRow.createCell(0).setCellValue(results.getCourseId());
			dataRow.createCell(1).setCellValue(results.getCourseName());
			dataRow.createCell(2).setCellValue(results.getLocation());
			dataRow.createCell(3).setCellValue(results.getCourseCategory());
			dataRow.createCell(4).setCellValue(results.getFacultyName());
			dataRow.createCell(5).setCellValue(results.getFee());
			dataRow.createCell(6).setCellValue(results.getAdminContact());
			dataRow.createCell(7).setCellValue(results.getTrainingMode());
			dataRow.createCell(8).setCellValue(results.getStartDate());
			dataRow.createCell(9).setCellValue(results.getCourseStatus());
			i++;
		}
		
		ServletOutputStream outputStream=res.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

}
