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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// 리스트로 보여주기
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
	
	// 내용 상세보기
	@GetMapping("/adopTempBoardContent")
	public String adopTempBoardContent(String boardId, @ModelAttribute("pageNum") String pageNum, Model model) {
		
		System.out.println("adopTempBoardContent 호출...");
		
		// 조회수 1 증가시키기 
		adopTempService.updateBoardReadCount(boardId);
		
		// 글 한개 가져오기
		ComBoardVO comBoardVO = adopTempService.selectBoard(boardId);
		
		model.addAttribute("board", comBoardVO);
	
	      return "adopTemp/adopTempBoardContent";
	}
	
	// 새로운 주글쓰기 폼 화면 요청
	@GetMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite(@ModelAttribute("pageNum") String pageNum) {
		System.out.println("adopTempBoardWrite 호출...");
		
		return "adopTemp/adopTempBoardWrite";
	}
	
	@PostMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite(String boardId, ComBoardVO comBoardVO, RedirectAttributes rttr, String pageNum) {
		
		
		adopTempService.insertBoard(comBoardVO);
		rttr.addAttribute("boardId", comBoardVO.getBoardId());
		rttr.addAttribute("pageNum", pageNum);
		
		 return "redirect:/adopTemp/adopTempBoardContent";
	}
	
	@GetMapping("/adopTempBoardRemove")
	public String adopTempBoardRemove(String boardId, String pageNum) {
		System.out.println("adopTempBoardRemove 호출...");
		// adopTemp 테이블 내용 삭제
		adopTempService.deleteBoard(boardId);
		
		// 글목록으로 리다이렉트 이동
		return "redirect:/adopTemp/adopTempBoardList?pageNum=" + pageNum;
		
	}
}