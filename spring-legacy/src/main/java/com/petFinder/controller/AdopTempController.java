package com.petFinder.controller;

import java.util.List;

/**
 * @title   : 반려동물 입양|임보 Controller 
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.service.AdopTempService;
import com.petFinder.service.AttachFile;


@Controller
@RequestMapping("/adopTemp/*")
public class AdopTempController {
	
	@Autowired
	private AdopTempService adopTempService;
	
	@GetMapping("/adopTempBoardList")
	public String adopTempBoardList(Model model) {
		System.out.println("adopTempBoardList 호출...");
				
		List<ComBoardVO> boardlist = adopTempService.selectBoard();
		
		model.addAttribute("adopTempList", boardlist);
		
		return "adopTemp/adopTempBoardList";
	}
	
	@PostMapping("/adopTempBoardList") 
	public String adopTempBoardList(ComBoardVO comBoardVO) {

		return null;
	}
	
	@GetMapping("/adopTempBoardContent")
	public String adopTempBoardContent() {
		
		return "adopTemp/adopTempBoardContent";
	}
	
	@GetMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite() {
		
		return "adopTemp/adopTempBoardWrite";
	}
	
	@PostMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite(ComBoardVO comBoardVO) {
		
		adopTempService.insertBoard(comBoardVO);
		
		return "adopTemp/adopTempBoardList";
	}
	
	
   
}