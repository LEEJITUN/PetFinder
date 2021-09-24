package com.petFinder.controller;
/**
 * @title   : 커뮤니티 게시판 Controller
 * @author  : HYEPIN
 * @date    : 2021.09.19
 * @version : 1.4
 **/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;
import com.petFinder.domain.PageDTO;
import com.petFinder.domain.RestAdopCommCommentVO;
import com.petFinder.service.CommunityService;
import com.petFinder.service.RestAdopCommService;


@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private RestAdopCommService restAdopCommService;

	/* community - 게시판 */
	@GetMapping("/commuBoardList")
	public String commuBoardList(Criteria cri, Model model) {		
		System.out.println("commuBoardList 호출...");
		
		List<ComBoardVO> boardList = communityService.selectBoardPaging(cri); // 페이징
		
		int totalCount = communityService.selectTotalCountBySearch(cri); // 검색이 적용된 전체 글개수
		
		PageDTO pageDTO = new PageDTO(totalCount, cri); // 페이지블록(Pagination) 화면 만들때 필요한 정보
		
		model.addAttribute("commuList",boardList);
		model.addAttribute("pageMaker", pageDTO);
		
		return "community/commuBoardList";
	} 
	
	
	/* community - 글쓰기 */
	@GetMapping("/commuBoardWrite")
	public String commuBoardWrite(@ModelAttribute("pageNum") String pageNum) {
		System.out.println("commuBoardWrite 호출...");
		
		return "community/commuBoardWrite";
	}
	
	@PostMapping("/commuBoardWrite")
	public String commuBoardWrite(String boardId, ComBoardVO comBoardVO, RedirectAttributes rttr, String pageNum) {
		
		communityService.insertBoardWrite(comBoardVO);
		rttr.addAttribute("boardId", comBoardVO.getBoardId());
		rttr.addAttribute("pageNum", 1);
				
		return "redirect:/community/commuBoardContent";
	} 
	
	
	/* community - 게시글 */
	@GetMapping("/commuBoardContent")
	public String commuBoardContent(String boardId, @ModelAttribute("pageNum") String pageNum, Model model) {
		System.out.println("commuBoardContent 호출...");
		
		// 조회수 업
		communityService.updateBoardReadCount(boardId);
		
		// 게시글 1개 가져오기
		ComBoardVO boardContent = communityService.selectBoardContent(boardId);
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(boardId);
		
		model.addAttribute("commuContent",boardContent);
		model.addAttribute("commentList", RestAdopCommCommenList);

		return "community/commuBoardContent";
	}
	
	
	/* community - 글수정 */
	@GetMapping("/commuBoardModify")
	public String commuBoardModify(String boardId, @ModelAttribute("pageNum") String pageNum, Model model) {
		System.out.println("commuBoardModify 호출...");

		ComBoardVO boardContent = communityService.selectBoardContent(boardId);
		
		model.addAttribute("commuContent", boardContent);
		System.out.println("pageNum..." + pageNum);
		return "community/commuBoardModify";
	}
	
	@PostMapping("/commuBoardModify")
	public String commuBoardModify(String boardId, ComBoardVO comBoardVO, String pageNum, RedirectAttributes rttr) {
		
		communityService.updateBoardModify(comBoardVO);
		System.out.println("commuBoardModify 수정..." + comBoardVO.getBoardTitle());

		rttr.addAttribute("boardId", comBoardVO.getBoardId());
		rttr.addAttribute("pageNum", pageNum);
		
		return "redirect:/community/commuBoardContent";
	}

	
	/* community - 삭제 */
	@GetMapping("/commuBoardRemove")
	public String commuBoardRemove(String boardId, String pageNum) {
		System.out.println("commuBoardRemove 호출...");
		
		communityService.deleteBoardContent(boardId);
		
		return "redirect:/community/commuBoardList?pageNum=" + pageNum;
	}

	
	/* community - 답글 */
	@GetMapping("/commuBoardReplyWrite")
	public String commuBoardReplyWrite() {
		System.out.println("commuBoardReplyWrite 호출...");
		
		return "community/commuBoardList";
	}
	
   

   
}