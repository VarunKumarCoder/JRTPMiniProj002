package com.nt.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.ICourseMgmtService;

@RestController
@RequestMapping("/reporting/api")
public class ReportingOperationController {
		@Autowired
	private ICourseMgmtService service;
		
		
}
