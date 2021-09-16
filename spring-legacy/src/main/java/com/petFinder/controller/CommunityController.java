package com.petFinder.controller;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Controller
 * @author  : HYEPIN
 * @date    : 2021.09.16
 * @version : 1.0 
 **/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String commuBoardList(Model model) {
		System.out.println("commuBoardList ȣ��...");
		
		List<ComBoardVO> boardList = communityService.selectBoardList();
		model.addAttribute("commuList",boardList);
		
		return "community/commuBoardList";
	}
	
	
	/* community - �۾��� */
	@GetMapping("/commuBoardWrite")
	public String commuBoardWrite() {
		System.out.println("commuBoardWrite ȣ��...");
		
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
		System.out.println("commuBoardContent ȣ��...");

		return "community/commuBoardContent";
	}
	
	
	/* community - �ۼ��� */
	@GetMapping("/commuBoardModify")
	public String commuBoardModify() {
		System.out.println("commuBoardModify ȣ��...");
		
		return "community/commuBoardModify";
	}
	
	@PostMapping("/commuBoardModify")
	public String commuBoardModify(ComBoardVO comBoardVO) {
				
		return "community/commuBoardContent";
	}
	
	
	/* community - ��� */
	@GetMapping("/commuBoardReplyWrite")
	public String commuBoardReplyWrite() {
		System.out.println("commuBoardReplyWrite ȣ��...");
		
		return "community/commuBoardList";
	}
	
   

   
}