package com.petFinder.controller;
/**
 * @title   : 반려동물 분실 신고 Controller
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardCommentVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.service.PetFindService;
import com.petFinder.service.PetLostService;
import com.petFinder.service.ReportCommentService;


@Controller
@RequestMapping("/petLostReport/*")
public class PetLostController {
	
	@Autowired
	PetLostService petLostService;
	
	@Autowired
	PetFindService petFindService;
	
	@Autowired
	ReportCommentService reportCommentService;
	
	@GetMapping("/lostReportPetWrite")
	public String lostReportPetWrite() {
		
		return "petLostReport/lostReportPetWrite";
	}
	
	// 유기동물 분실 신고
	@PostMapping("/lostReportPetWrite")
	public String lostReportPetWrite(PetVO petVO , ReportBoardVO reportBoardVO, List<MultipartFile> files) throws IllegalStateException, IOException {
		
		
		System.out.println("petVO : " +petVO.getMemberId() );
		System.out.println("petVO : " +reportBoardVO.getMemberId() );
		
		/***** 데이터 셋팅 *****/
		reportBoardVO.setBoardReportType("L");
		
		// 게시물 번호 생성
		int num = petFindService.selectBoardNumber(reportBoardVO.getBoardReportType());
		// 신고 ID 생성 
		String reportId = "REPORT_L_" + num;
		
		reportBoardVO.setReportId(reportId);
		reportBoardVO.setBoardNum(num);
		reportBoardVO.setBoardReadCount(0);
		reportBoardVO.setBoardReportCount(0);
		reportBoardVO.setBoardgood(0);
		reportBoardVO.setBoardNotgood(0);
		reportBoardVO.setBoardRegDate(new Date());
		
		// 연월일 구분문자("-") 제거하기
		String findDate = petVO.getLostPetDate();
		findDate = findDate.replace("-", "");
		petVO.setFindPetDate(findDate);
		
		petVO.setReportId(reportId);
		
		petFindService.insertFindReport(petVO,reportBoardVO,files,"LostReport","L");
		
		return "redirect:/petFindReport/findReportPetList";
	}
	
	@GetMapping("/lostReportPetContent")
	public String lostReportPetContent(String reportId, Model model) {
		
		ReportBoardVO  reportBoardVO = petFindService.selectFindReport(reportId,"L");
		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportId);
		
		// 조회수 증가
		petFindService.updateReportReadCunt(reportId);
		
		model.addAttribute("reportBoardVO", reportBoardVO);
		model.addAttribute("attachList", reportBoardVO.getPetVO().getAttachList());
		model.addAttribute("commentList", ReportBoardCommentList);
		
		return "petLostReport/lostReportPetContent";
	}
   
	@GetMapping("/lostReportPetDelete")
	public String lostReportPetDelete(String reportId) {
		
		petFindService.deleteFindReport(reportId,"L");
		
		return "redirect:/petFindReport/findReportPetList";
	}
   
	@GetMapping("/lostReportPetModify")
	public String lostReportPetModify(String reportId,Model model) {
		
		ReportBoardVO  reportBoardVO = petFindService.selectFindReport(reportId,"L");
		
		model.addAttribute("reportBoardVO", reportBoardVO);
		model.addAttribute("attachList", reportBoardVO.getPetVO().getAttachList());
		
		return "petLostReport/lostReportPetModify";
	}
	
	@PostMapping("/lostReportPetModify")
	public String lostReportPetModify(PetVO petVO , ReportBoardVO reportBoardVO, List<MultipartFile> files,
			@RequestParam(name = "delfile", required = false) List<String> delUuidList) throws IllegalStateException, IOException {
		
		petFindService.updateFindReport(petVO,reportBoardVO,files,"FindeReport","L",delUuidList);
		
		return "redirect:/petLostReport/lostReportPetContent?reportId=" +reportBoardVO.getReportId();
	}
	
}