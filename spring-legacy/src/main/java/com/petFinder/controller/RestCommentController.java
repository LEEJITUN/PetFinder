package com.petFinder.controller;
/**
 * @title   : 반려동물 발견 신고 Controller
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
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;
import com.petFinder.domain.MemberProfileVO;
import com.petFinder.domain.PageDTO;
import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardCommentVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.service.ReportCommentService;
import com.petFinder.service.PetFindService;
import com.petFinder.service.ProfilePicService;


@RestController
@RequestMapping("/api/*")
public class RestCommentController {
	
	@Autowired
	private ReportCommentService reportCommentService;
	
	@Autowired
	private ProfilePicService profilePicService;
	

	// 댓글 쓰기 -> 댓글 작성
	@PostMapping(value = "/findReportCommentWrite",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ReportBoardCommentVO>> findReportCommentWrite(@RequestBody ReportBoardCommentVO reportBoardCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== 데이터 설정 ======
		
		// insert할 새 글번호 가져오기
		int index = reportCommentService.selectCommentIndex(reportBoardCommentVO);
		
		reportBoardCommentVO.setCommentNum(index);
		reportBoardCommentVO.setCommentId(reportBoardCommentVO.getReportId() + "_" + index);
		reportBoardCommentVO.setCommentRegDate(new Date());
		reportBoardCommentVO.setCommentRef(reportBoardCommentVO.getCommentNum());

		reportCommentService.insertComment(reportBoardCommentVO);
		
		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportBoardCommentVO.getReportId());
		
		return new ResponseEntity<List<ReportBoardCommentVO>>(ReportBoardCommentList, HttpStatus.OK);
	} 
	
	// 댓글 쓰기 -> 댓글 답글 작성
	@PostMapping(value = "/findReportCommentReply",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ReportBoardCommentVO>> findReportCommentReply(@RequestBody ReportBoardCommentVO reportBoardCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		int index = reportCommentService.selectCommentIndex(reportBoardCommentVO);
		
		reportBoardCommentVO.setCommentNum(index);
		reportBoardCommentVO.setCommentId(reportBoardCommentVO.getReportId() + "_" + index);
		reportBoardCommentVO.setCommentRegDate(new Date());

		
		reportCommentService.updateReSeqPlusOne(reportBoardCommentVO);
		
		
		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportBoardCommentVO.getReportId());
		
		return new ResponseEntity<List<ReportBoardCommentVO>>(ReportBoardCommentList, HttpStatus.OK);
	} 

	
	// 댓글 쓰기 -> 댓글 수정
	@PutMapping(value = "/findReportCommentModify",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ReportBoardCommentVO>> findReportCommentModify(@RequestBody ReportBoardCommentVO reportBoardCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== 데이터 설정 ======
		reportBoardCommentVO.setCommentUpDate(new Date());

		reportCommentService.updateComment(reportBoardCommentVO);
		
		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportBoardCommentVO.getReportId());
		
		return new ResponseEntity<List<ReportBoardCommentVO>>(ReportBoardCommentList, HttpStatus.OK);
	} 
	
	
	// 댓글 쓰기 -> 댓글 삭제
	@DeleteMapping(value = "/findReportCommentDelete",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ReportBoardCommentVO>> findReportCommentDelete(@RequestBody ReportBoardCommentVO reportBoardCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== 데이터 설정 ======
		reportBoardCommentVO.setCommentUpDate(new Date());

		reportCommentService.deleteComment(reportBoardCommentVO.getCommentId());
		
		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportBoardCommentVO.getReportId());
		
		return new ResponseEntity<List<ReportBoardCommentVO>>(ReportBoardCommentList, HttpStatus.OK);
	} 
		
		
	// 댓글 쓰기 -> 댓글 전체 조회
	@GetMapping(value = "/findReportCommentList/{reportId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ReportBoardCommentVO>> findReportCommentList(@PathVariable("reportId") String reportId) {

		List<ReportBoardCommentVO> ReportBoardCommentList = reportCommentService.selectComments(reportId);
		
		return new ResponseEntity<List<ReportBoardCommentVO>>(ReportBoardCommentList, HttpStatus.OK);
	} 
	
	///////////////////////// 프로필 삭제,취소 //////////////////////
	
	@DeleteMapping(value = "/memberPropicDelete/{memberId}",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void memberPropicDelete(@PathVariable("memberId") String memberId) 
			throws IOException {

			profilePicService.deleteProfilePic(memberId);
		
	} 
   
}