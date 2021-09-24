package com.petFinder.controller;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Controller
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

	/* community - �Խ��� */
	@GetMapping("/commuBoardList")
	public String commuBoardList(Criteria cri, Model model) {		
		System.out.println("commuBoardList ȣ��...");
		
		List<ComBoardVO> boardList = communityService.selectBoardPaging(cri); // ����¡
		
		int totalCount = communityService.selectTotalCountBySearch(cri); // �˻��� ����� ��ü �۰���
		
		PageDTO pageDTO = new PageDTO(totalCount, cri); // ���������(Pagination) ȭ�� ���鶧 �ʿ��� ����
		
		model.addAttribute("commuList",boardList);
		model.addAttribute("pageMaker", pageDTO);
		
		return "community/commuBoardList";
	} 
	
	
	/* community - �۾��� */
	@GetMapping("/commuBoardWrite")
	public String commuBoardWrite(@ModelAttribute("pageNum") String pageNum) {
		System.out.println("commuBoardWrite ȣ��...");
		
		return "community/commuBoardWrite";
	}
	
	@PostMapping("/commuBoardWrite")
	public String commuBoardWrite(String boardId, ComBoardVO comBoardVO, RedirectAttributes rttr, String pageNum) {
		
		communityService.insertBoardWrite(comBoardVO);
		rttr.addAttribute("boardId", comBoardVO.getBoardId());
		rttr.addAttribute("pageNum", 1);
				
		return "redirect:/community/commuBoardContent";
	} 
	
	
	/* community - �Խñ� */
	@GetMapping("/commuBoardContent")
	public String commuBoardContent(String boardId, @ModelAttribute("pageNum") String pageNum, Model model) {
		System.out.println("commuBoardContent ȣ��...");
		
		// ��ȸ�� ��
		communityService.updateBoardReadCount(boardId);
		
		// �Խñ� 1�� ��������
		ComBoardVO boardContent = communityService.selectBoardContent(boardId);
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(boardId);
		
		model.addAttribute("commuContent",boardContent);
		model.addAttribute("commentList", RestAdopCommCommenList);

		return "community/commuBoardContent";
	}
	
	
	/* community - �ۼ��� */
	@GetMapping("/commuBoardModify")
	public String commuBoardModify(String boardId, @ModelAttribute("pageNum") String pageNum, Model model) {
		System.out.println("commuBoardModify ȣ��...");

		ComBoardVO boardContent = communityService.selectBoardContent(boardId);
		
		model.addAttribute("commuContent", boardContent);
		System.out.println("pageNum..." + pageNum);
		return "community/commuBoardModify";
	}
	
	@PostMapping("/commuBoardModify")
	public String commuBoardModify(String boardId, ComBoardVO comBoardVO, String pageNum, RedirectAttributes rttr) {
		
		communityService.updateBoardModify(comBoardVO);
		System.out.println("commuBoardModify ����..." + comBoardVO.getBoardTitle());

		rttr.addAttribute("boardId", comBoardVO.getBoardId());
		rttr.addAttribute("pageNum", pageNum);
		
		return "redirect:/community/commuBoardContent";
	}

	
	/* community - ���� */
	@GetMapping("/commuBoardRemove")
	public String commuBoardRemove(String boardId, String pageNum) {
		System.out.println("commuBoardRemove ȣ��...");
		
		communityService.deleteBoardContent(boardId);
		
		return "redirect:/community/commuBoardList?pageNum=" + pageNum;
	}

	
	/* community - ��� */
	@GetMapping("/commuBoardReplyWrite")
	public String commuBoardReplyWrite() {
		System.out.println("commuBoardReplyWrite ȣ��...");
		
		return "community/commuBoardList";
	}
	
   

   
}