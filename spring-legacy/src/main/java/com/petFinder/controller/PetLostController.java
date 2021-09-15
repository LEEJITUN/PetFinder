package com.petFinder.controller;
/**
 * @title   : �ݷ����� �н� �Ű� Controller
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/petLostReport/*")
public class PetLostController {
	

	@GetMapping("/lostReportPetList")
	public String lostReportPetList() {
		
		return "petLostReport/lostReportPetList";
	}
	
	@GetMapping("/lostReportPetWrite")
	public String lostReportPetWrite() {
		
		return "petLostReport/lostReportPetWrite";
	}
	
//	@GetMapping("/findReportPetContent")
//	public String findReportPetContent() {
//		
//		return "petFindReport/findReportPetContent";
//	}
   
   

   

   
}