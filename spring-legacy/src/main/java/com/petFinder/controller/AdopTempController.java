package com.petFinder.controller;

/**
 * @title   : 반려동물 입양|임보 Controller kk
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petFinder.service.AdopTempService;
import com.petFinder.service.AttachFile;


@Controller
@RequestMapping("/adopTemp/*")
public class AdopTempController {
	
	@Autowired
	private AdopTempService adopTempService;
	


	@GetMapping("/adopTempBoardList")
	public String adopTempBoardList() {
		
		return "adopTemp/adopTempBoardList";
	}
	
	@GetMapping("/adopTempBoardContent")
	public String adopTempBoardContent() {
		
		return "adopTemp/adopTempBoardContent";
	}
	
	@GetMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite() {
		
		return "adopTemp/adopTempBoardWrite";
	}
   

   
}