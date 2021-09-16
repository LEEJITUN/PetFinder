/* 하이
 * 
 */
package com.petFinder.controller;
/**
 * @title   : 커뮤니티 게시판 Controller
 * @author  : HYEPIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petFinder.service.CommunityService;


@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;


	@GetMapping("/commuBoardList")
	public String commuBoardList() {
		
		return "community/commuBoardList";
	}
	
	@GetMapping("/commuBoardWrite")
	public String commuBoardWrite() {
		
		return "community/commuBoardWrite";
	}
	
	@GetMapping("/commuBoardContent")
	public String commuBoardContent() {
		
		return "community/commuBoardContent";
	}
   

   
}