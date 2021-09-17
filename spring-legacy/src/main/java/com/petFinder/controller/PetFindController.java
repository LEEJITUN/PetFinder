package com.petFinder.controller;
/**
 * @title   : �ݷ����� �߰� �Ű� Controller
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
import org.springframework.web.multipart.MultipartFile;

import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.service.PetFindService;


@Controller
@RequestMapping("/petFindReport/*")
public class PetFindController {
	
	@Autowired
	PetFindService petFindService;


	@GetMapping("/findReportPetList")
	public String findReportPetList(Model model) {
		
		List<ReportBoardVO> reportBoardList =  petFindService.selectAllFindReport();
		model.addAttribute("reportBoardList", reportBoardList);
		
		return "petFindReport/findReportPetList";
	}
	
	@GetMapping("/findReportPetWrite")
	public String findReportPetWrite() {
		
		return "petFindReport/findReportPetWrite";
	}
	
	// ���⵿�� �߰� �Ű�
	@PostMapping("/findReportPetWrite")
	public String findReportPetWrite(PetVO petVO , ReportBoardVO reportBoardVO, List<MultipartFile> files) throws IllegalStateException, IOException {
		
		
		System.out.println("petVO : " +petVO.getMemberId() );
		System.out.println("petVO : " +reportBoardVO.getMemberId() );
		
		/***** ������ ���� *****/
		reportBoardVO.setBoardReportType("A");
		
		// �Խù� ��ȣ ����
		int num = petFindService.selectBoardNumber(reportBoardVO.getBoardReportType());
		// �Ű� ID ���� 
		String reportId = "REPORT_A_" + num;
		
		reportBoardVO.setReportId(reportId);
		reportBoardVO.setBoardNum(num);
		reportBoardVO.setBoardReadCount(0);
		reportBoardVO.setBoardReportCount(0);
		reportBoardVO.setBoardgood(0);
		reportBoardVO.setBoardNotgood(0);
		reportBoardVO.setBoardRegDate(new Date());
		
		// ������ ���й���("-") �����ϱ�
		String findDate = petVO.getFindPetDate();
		findDate = findDate.replace("-", "");
		petVO.setFindPetDate(findDate);
		
		petVO.setReportId(reportId);
		
		petFindService.insertFindReport(petVO,reportBoardVO,files,"FindeReport","F");
		
		return "redirect:/petFindReport/findReportPetList";
	}
	
	@GetMapping("/findReportPetContent")
	public String findReportPetContent(String reportId,Model model) {
		
		ReportBoardVO  reportBoardVO = petFindService.selectFindReport(reportId);
		
		model.addAttribute("reportBoardVO", reportBoardVO);
		model.addAttribute("attachList", reportBoardVO.getPetVO().getAttachVO());
		
		return "petFindReport/findReportPetContent";
	}
   
   

   
}