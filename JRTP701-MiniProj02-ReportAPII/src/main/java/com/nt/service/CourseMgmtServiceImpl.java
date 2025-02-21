package com.nt.service;

import java.util.List;
import java.util.Set;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public class CourseMgmtServiceImpl implements ICourseMgmtService {

	@Override
	public Set<String> showAllCourseCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> ShowAllTrainingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> ShowAllFaculties() {
		// TODO Auto-generated method stub
		return null;
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
