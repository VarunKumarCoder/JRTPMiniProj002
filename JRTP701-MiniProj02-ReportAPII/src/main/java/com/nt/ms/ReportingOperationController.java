package com.nt.ms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.service.ICourseMgmtService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
public class ReportingOperationController {
		@Autowired
	private ICourseMgmtService service;
		
		@GetMapping("/courses")
		public ResponseEntity<?> fetchCourseCategories(){
			try {
				Set<String> courseInfo=service.showAllCourseCategories();
				return new ResponseEntity<Set<String>>(courseInfo, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		@GetMapping("/training-modes")
		public ResponseEntity<?> fetchAllTrainingModes(){
			try {
				Set<String> trainingModeInfo=service.ShowAllTrainingModes();
				return new ResponseEntity<Set<String>>(trainingModeInfo,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@GetMapping("/faculties")
		public ResponseEntity<?> fetchFaculties(){
			try {
				Set<String> faculties=service.ShowAllFaculties();
				return new ResponseEntity<Set<String>>(faculties,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@PostMapping("/search")
		public ResponseEntity<?> fetchCoursesByFilters(@RequestBody SearchInputs inputs){
			try {
				List<SearchResults> list=service.showCoursesByFilters(inputs);
				return new ResponseEntity<List<SearchResults>>(list, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@PostMapping("/pdf-report")
		public void showPdfReport(@RequestBody SearchInputs inputs,HttpServletResponse res) {
			try {
				//set the response content type
				res.setContentType("application/pdf");
				//set the content-disposition header to response content going to browser as downloadable file
				res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
				service.generatePdfReport(inputs, res);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		@PostMapping("/excel-report")
		public void showexcelReport(@RequestBody SearchInputs inputs,HttpServletResponse res) {
			try {
				//set the response content type
				res.setContentType("application/vnd.ms-excel");
				//set the content-disposition header to response content going to browser as downlodeble file
				res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
				service.generatePdfReport(inputs, res);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		@GetMapping("/all-excel-report")
		public void showExcelReportAllData(HttpServletResponse res) {
			try {
				res.setContentType("application/vnd.ms-excel");
				res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
				service.generateXlsReportAll(res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@GetMapping("/all-pdf-report")
		public void showPdfReportAllData(HttpServletResponse res) {
			try {
				res.setContentType("application/pdf");
				res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
				service.generatePdfReportAll(res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
}
