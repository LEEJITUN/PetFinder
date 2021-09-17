package com.petFinder.controller;

import java.util.List;

/**
 * @title   : �ݷ����� �Ծ�|�Ӻ� Controller 
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
	
	// ����Ʈ�� �����ֱ�
	@GetMapping("/adopTempBoardList")
	public String adopTempBoardList(Criteria cri, Model model) {
		
		System.out.println("adopTempBoardList ȣ��...");
				
		List<ComBoardVO> boardlist = adopTempService.selectBoards(cri);
		
		int totalCount = adopTempService.selectTotalCount(); // ��ü �۰���
		
		PageDTO pageDTO = new PageDTO(totalCount, cri); // ���������(Pagination) ȭ�鸸�� �� �ʿ��� ����
		
		// �信�� ����� �����͸� Model ��ü�� ���� -> requestScope�� �Ű���
		model.addAttribute("adopTempList", boardlist);
		model.addAttribute("pageMaker", pageDTO); // pageMaker : ������ ȭ�� ���� �� ���
		
		return "adopTemp/adopTempBoardList";
	}
	
	// ���� �󼼺���
	@GetMapping("/adopTempBoardContent")
	public String adopTempBoardContent(String boardId, @ModelAttribute("pageNum") String pageNum, Model model) {
		
		System.out.println("adopTempBoardContent ȣ��...");
		
		// ��ȸ�� 1 ������Ű�� 
		adopTempService.updateBoardReadCount(boardId);
		
		// �� �Ѱ� ��������
		ComBoardVO comBoardVO = adopTempService.selectBoard(boardId);
		
		model.addAttribute("board", comBoardVO);
	
	      return "adopTemp/adopTempBoardContent";
	}
	
	// ���ο� �ֱ۾��� �� ȭ�� ��û
	@GetMapping("/adopTempBoardWrite")
	public String adopTempBoardWrite(@ModelAttribute("pageNum") String pageNum) {
		System.out.println("adopTempBoardWrite ȣ��...");
		
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
		System.out.println("adopTempBoardRemove ȣ��...");
		// adopTemp ���̺� ���� ����
		adopTempService.deleteBoard(boardId);
		
		// �۸������ �����̷�Ʈ �̵�
		return "redirect:/adopTemp/adopTempBoardList?pageNum=" + pageNum;
		
	}
}