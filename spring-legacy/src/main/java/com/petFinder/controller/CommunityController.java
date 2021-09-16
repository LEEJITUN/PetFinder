package com.petFinder.controller;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Controller
 * @author  : HYEPIN
 * @date    : 2021.09.16
 * @version : 1.0 
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.service.CommunityService;


@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;

	/* community - �Խ��� */
	@GetMapping("/commuBoardList")
	public String commuBoardList() {
		System.out.println("commuBoardList ȣ��...");
		return "community/commuBoardList";
	}
	
	
	/* community - �۾��� */
	@GetMapping("/commuBoardWrite")
	public String commuBoardWrite() {
		
		return "community/commuBoardWrite";
	}
	
	@PostMapping("/commuBoardWrite")
	public String commuBoardWrite(ComBoardVO comBoardVO) {
		
		communityService.insertBoard(comBoardVO);
		
		return "community/commuBoardContent";
	}
	
	
	/* community - �Խñ� */
	@GetMapping("/commuBoardContent")
	public String commuBoardContent() {
		
		return "community/commuBoardContent";
	}
	
	
	/* community - �ۼ��� */
	@GetMapping("/commuBoardModify")
	public String commuBoardModify() {
		
		return "community/commuBoardModify";
	}
	
	@PostMapping("/commuBoardModify")
	public String commuBoardModify(ComBoardVO comBoardVO) {
		
		return "community/commuBoardContent";
	}
	
	
	// community - ���
	@GetMapping("/commuBoardReplyWrite")
	public String commuBoardReplyWrite() {
		
		return "community/commuBoardList";
	}
	
   

   
}