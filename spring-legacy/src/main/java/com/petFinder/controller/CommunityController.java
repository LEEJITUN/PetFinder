package com.petFinder.controller;
/**
 * @title   : 커뮤니티 게시판 Controller
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

	/* community - 게시판 */
	@GetMapping("/commuBoardList")
	public String commuBoardList() {
		System.out.println("commuBoardList 호출...");
		return "community/commuBoardList";
	}
	
	
	/* community - 글쓰기 */
	@GetMapping("/commuBoardWrite")
	public String commuBoardWrite() {
		
		return "community/commuBoardWrite";
	}
	
	@PostMapping("/commuBoardWrite")
	public String commuBoardWrite(ComBoardVO comBoardVO) {
		
		communityService.insertBoard(comBoardVO);
		
		return "community/commuBoardContent";
	}
	
	
	/* community - 게시글 */
	@GetMapping("/commuBoardContent")
	public String commuBoardContent() {
		
		return "community/commuBoardContent";
	}
	
	
	/* community - 글수정 */
	@GetMapping("/commuBoardModify")
	public String commuBoardModify() {
		
		return "community/commuBoardModify";
	}
	
	@PostMapping("/commuBoardModify")
	public String commuBoardModify(ComBoardVO comBoardVO) {
		
		return "community/commuBoardContent";
	}
	
	
	// community - 답글
	@GetMapping("/commuBoardReplyWrite")
	public String commuBoardReplyWrite() {
		
		return "community/commuBoardList";
	}
	
   

   
}