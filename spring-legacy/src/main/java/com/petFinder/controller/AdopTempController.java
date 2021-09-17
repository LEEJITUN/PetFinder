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
import com.petFinder.domain.Criteria;
import com.petFinder.domain.PageDTO;
import com.petFinder.service.AdopTempService;
import com.petFinder.service.AttachFile;


@Controller
@RequestMapping("/adopTemp/*")
public class AdopTempController {
	
	@Autowired
	private AdopTempService adopTempService;
	
	@GetMapping("/adopTempBoardList")
	public String adopTempBoardList(Criteria cri, Model model) {
		
		System.out.println("adopTempBoardList 호출...");
				
		List<ComBoardVO> boardlist = adopTempService.selectBoards(cri);
		
		int totalCount = adopTempService.selectTotalCount(); // 전체 글개수
		
		PageDTO pageDTO = new PageDTO(totalCount, cri); // 페이지블록(Pagination) 화면만들 떄 필요한 정보
		
		// 뷰에서 사용할 데이터를 Model 객체에 저장 -> requestScope로 옮겨줌
		model.addAttribute("adopTempList", boardlist);
		model.addAttribute("pageMaker", pageDTO); // pageMaker : 페이지 화면 만들 때 사용
		
		return "adopTemp/adopTempBoardList";
	}
	
	// 상세보기
	@GetMapping("/adopTempBoardContent")
	public String adopTempBoardContent(String boardId, Model model) {
		
		System.out.println("adopTempBoardContent 호출...");
		
		// 조회수 1 증가시키기 
		adopTempService.updateBoardReadCount(boardId);
		
		// 글 한개 가져오기
		ComBoardVO comBoardVO = adopTempService.selectBoard(boardId);
		
		model.addAttribute("board", comBoardVO);
		
		
	      return "adopTemp/adopTempBoardContent";
	}
	
	@GetMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite() {
		System.out.println("adopTempBoardWrite 호출...");
		
		return "adopTemp/adopTempBoardWrite";
	}
	
	@PostMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite(ComBoardVO comBoardVO) {
		
		adopTempService.insertBoard(comBoardVO);
		
		return "adopTemp/adopTempBoardList";
	}

}