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

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;
import com.petFinder.domain.PageDTO;
import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardCommentVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.service.ReportCommentService;
import com.petFinder.service.PetFindService;


@Controller
@RequestMapping("/petFindReport/*")
public class PetFindController {
	
	@Autowired
	PetFindService petFindService;
	
	@Autowired
	ReportCommentService reportCommentService;

	/**/
	@GetMapping("/findReportPetList")
	public String findReportPetList(Criteria cri,Model model) {

		List<ReportBoardVO> reportBoardList =  petFindService.selectAllFindReport(cri);
		
		PageDTO pageDTO = new PageDTO(reportBoardList.size(), cri); // ���������(Pagination) ȭ�鸸�� �� �ʿ��� ����
		
		model.addAttribute("reportBoardList", reportBoardList);
		model.addAttribute("pageMaker", pageDTO);
		
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
		reportBoardVO.setBoardReportType("F");
		
		// �Խù� ��ȣ ����
		int num = petFindService.selectBoardNumber(reportBoardVO.getBoardReportType());
		// �Ű� ID ���� 
		String reportId = "REPORT_F_" + num;
		
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
		
		ReportBoardVO  reportBoardVO = petFindService.selectFindReport(reportId,"F");
		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportId);
		
		// ��ȸ�� ����
		petFindService.updateReportReadCunt(reportId);
		
		model.addAttribute("reportBoardVO", reportBoardVO);
		model.addAttribute("attachList", reportBoardVO.getPetVO().getAttachList());
		model.addAttribute("commentList", ReportBoardCommentList);
		
		return "petFindReport/findReportPetContent";
	}
	
	@GetMapping("/findReportPetDelete")
	public String findReportPetDelete(String reportId) {
		
		petFindService.deleteFindReport(reportId,"F");
		
		return "redirect:/petFindReport/findReportPetList";
	}
	
	@GetMapping("/findReportPetModify")
	public String findReportPetModify(String reportId,Model model) {
		
		ReportBoardVO  reportBoardVO = petFindService.selectFindReport(reportId,"F");
		
		model.addAttribute("reportBoardVO", reportBoardVO);
		model.addAttribute("attachList", reportBoardVO.getPetVO().getAttachList());
		
		return "petFindReport/findReportPetModify";
	}
	
	@PostMapping("/findReportPetModify")
	public String findReportPetModify(PetVO petVO , ReportBoardVO reportBoardVO, List<MultipartFile> files,
			@RequestParam(name = "delfile", required = false) List<String> delUuidList) throws IllegalStateException, IOException {
		
		petFindService.updateFindReport(petVO,reportBoardVO,files,"FindeReport","F",delUuidList);
		
		return "redirect:/petFindReport/findReportPetContent?reportId=" +reportBoardVO.getReportId();
	}
   
}