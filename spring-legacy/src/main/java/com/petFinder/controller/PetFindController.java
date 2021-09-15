package com.petFinder.controller;
/**
 * @title   : 반려동물 발견 신고 Controller
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/petFindReport/*")
public class PetFindController {
	


	@GetMapping("/findReportPetList")
	public String findReportPetList() {
		
		return "petFindReport/findReportPetList";
	}
	
	@GetMapping("/findReportPetWrite")
	public String findReportPetWrite() {
		
		return "petFindReport/findReportPetWrite";
	}
	
	@GetMapping("/findReportPetContent")
	public String findReportPetContent() {
		
		return "petFindReport/findReportPetContent";
	}
   
   

   
}