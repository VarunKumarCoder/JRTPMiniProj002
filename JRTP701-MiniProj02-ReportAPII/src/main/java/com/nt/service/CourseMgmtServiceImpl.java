package com.nt.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<SearchResults> showCoursesByFilters(SearchInputs inpust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) {
		// TODO Auto-generated method stub

	}

}
