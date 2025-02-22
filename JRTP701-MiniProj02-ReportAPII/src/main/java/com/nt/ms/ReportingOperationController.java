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
		
		
}
