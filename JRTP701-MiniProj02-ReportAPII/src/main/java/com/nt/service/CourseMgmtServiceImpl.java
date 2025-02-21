package com.nt.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.nt.entity.CourseDetails;
import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.repository.ICourseDetailsRepository;

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
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res) {
		

	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) {
		List<SearchResults> listResults=showCoursesByFilters(inputs);
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");

	}

}
