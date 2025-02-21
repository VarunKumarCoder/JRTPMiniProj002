package com.nt.service;

import java.util.List;
import java.util.Set;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseMgmtService {
	
	public Set<String> showAllCourseCategories();
	public Set<String> ShowAllTrainingModes();
	public Set<String> ShowAllFaculties();
	
	public List<SearchResults> showCoursesByFilters(SearchInputs inpust);
	
	public void generatePdfReport(SearchInputs inputs,HttpServletResponse res);
	public void generateExcelReport(SearchInputs inputs,HttpServletResponse res);
}
