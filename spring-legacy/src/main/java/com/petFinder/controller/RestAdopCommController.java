package com.petFinder.controller;
/**
 * @title   : 추천 | 비추천 | 신고 댓글 Controller
 * @author  : SUMIN
 * @date    : 2021.09.26 
 * @version : 1.4
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

import com.petFinder.domain.Criteria;
import com.petFinder.domain.PageDTO;
import com.petFinder.domain.RestAdopCommCommentVO;
import com.petFinder.domain.RestAdopCommVO;
import com.petFinder.service.RestAdopCommService;


@RestController
@RequestMapping("/api/*")
public class RestAdopCommController {
	
	@Autowired
	private RestAdopCommService restAdopCommService;

	// =================== 추천 비추천 ===================
	
	@PutMapping(value = "/adopCommBoardCheck",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RestAdopCommVO> adopCommBoardCheck(@RequestBody RestAdopCommVO restAdopCommVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {
			
		int checkNum = restAdopCommService.selectCheck(restAdopCommVO);
		int waringcheckNum = restAdopCommService.selectCheck(restAdopCommVO);
		
		// 추천과 비추천 중 하나라도 하지 않았을 경우 업데이트 (해당 유저)
		if(checkNum == 0) {
			// 신고 업데이트
			restAdopCommService.updateadopCommBoardCheck(restAdopCommVO);
		}else{ // 신고 취소를 했을 경우 , 추천,비추천값이 없을 경우
				restAdopCommService.deleteCommBoard(restAdopCommVO);
		}
		
		// 추천 비추천 조회(count)
		RestAdopCommVO restAdopCommCount = restAdopCommService.selectTotalCount(restAdopCommVO);

	
		return new ResponseEntity<RestAdopCommVO>(restAdopCommCount, HttpStatus.OK);
	} 
	
	// ===================== 신고 =====================

	
	@PutMapping(value = "/adopTempBoardWaring",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })			
	public ResponseEntity<RestAdopCommVO> adopTempBoardWaring(@RequestBody RestAdopCommVO restAdopCommVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {
		
		int checkNum = restAdopCommService.selectWaring(restAdopCommVO);
		
		int goodcheckNum = restAdopCommService.selectCheck(restAdopCommVO);
		
		// 이미 신고를 했을 경우 or 추천,비추천에 값이 있을 경우
		if(checkNum == 0) {
			// 신고 업데이트
			restAdopCommService.updateadopWaringCheck(restAdopCommVO);
		}else{ // 신고 취소를 했을 경우 , 추천,비추천값이 없을 경우
				restAdopCommService.deleteWaringCheck(restAdopCommVO);
		}
		
		// 신고 조회(count)
		RestAdopCommVO restAdopWaringCount = restAdopCommService.selectComment(restAdopCommVO);
		
		
		return new ResponseEntity<RestAdopCommVO>(restAdopWaringCount, HttpStatus.OK);
	}
	
	// 추천, 신고 조회
	@GetMapping(value = "/boardWaringAndGood/{boardId}/{memberId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RestAdopCommVO> boardWaringAndGood(@PathVariable("boardId") String boardId,@PathVariable("memberId") String memberId) {

		RestAdopCommVO restAdopCommVO = new RestAdopCommVO();
		restAdopCommVO.setMemberId(memberId);
		restAdopCommVO.setBoardId(boardId);
		
		RestAdopCommVO returltVO = restAdopCommService.getBoardWaringAndGood(restAdopCommVO);
		
		return new ResponseEntity<RestAdopCommVO>(returltVO, HttpStatus.OK);
	}
	
	// =================== 댓글 답댓글 ===================	
	
	// 댓글 쓰기 -> 댓글 작성
	@PostMapping(value = "/adopCommCommentWrite",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentWrite(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== 데이터 설정 ======
		
		// insert할 새 글번호 가져오기
		int index = restAdopCommService.selectCommentIndex(restAdopCommCommentVO);
		
		restAdopCommCommentVO.setBoardNum(index);
		restAdopCommCommentVO.setCommentId(restAdopCommCommentVO.getBoardId() + "_" + index);
		restAdopCommCommentVO.setCommentRegDate(new Date());
		restAdopCommCommentVO.setCommentRef(restAdopCommCommentVO.getBoardNum());

		restAdopCommService.insertComment(restAdopCommCommentVO);
		
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
	
	// =========================== 수 정 ==================================
	
	// 댓글 쓰기 -> 댓글 답글 작성
	@PostMapping(value = "/adopCommCommentReply",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentReply(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		int index = restAdopCommService.selectCommentIndex(restAdopCommCommentVO);
		
		restAdopCommCommentVO.setBoardNum(index);
		restAdopCommCommentVO.setCommentId(restAdopCommCommentVO.getBoardId() + "_" + index);
		restAdopCommCommentVO.setCommentRegDate(new Date());

		
		restAdopCommService.updateReSeqPlusOne(restAdopCommCommentVO);
		
		
		List<RestAdopCommCommentVO>  RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>( RestAdopCommCommenList, HttpStatus.OK);
	} 

	// =========================== 수 정 ==================================
	
	// 댓글 쓰기 -> 댓글 수정
	@PutMapping(value = "/adopCommCommentModify",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentModify(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== 데이터 설정 ======
		restAdopCommCommentVO.setCommentUpDate(new Date());

		restAdopCommService.updateComment(restAdopCommCommentVO);
		
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
	
	// =============================================================
	
	
	// 댓글 쓰기 -> 댓글 삭제
	@DeleteMapping(value = "/adopCommCommentDelete",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentDelete(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== 데이터 설정 ======
		restAdopCommCommentVO.setCommentUpDate(new Date());

		restAdopCommService.deleteComment(restAdopCommCommentVO.getCommentId());
		
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
		
	
	// 댓글 쓰기 -> 댓글 전체 조회
	@GetMapping(value = "/adopCommCommentList/{boardId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentList(@PathVariable("boardId") String boardId) {


		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(boardId);
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
   
}
