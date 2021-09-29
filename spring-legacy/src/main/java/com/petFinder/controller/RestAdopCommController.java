package com.petFinder.controller;
/**
 * @title   : 추천 | 비추천 | 신고 댓글 Controller
 * @author  : SUMIN, JIYUN
 * @date    : 2021.09.26 
 * @version : 1.7
 **/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petFinder.domain.RestAdopCommCommentVO;
import com.petFinder.domain.RestAdopCommVO;
import com.petFinder.service.AdopTempService;
import com.petFinder.service.CommunityService;
import com.petFinder.service.PetFindService;
import com.petFinder.service.RestAdopCommService;
import com.petFinder.util.Script;


@RestController
@RequestMapping("/api/*")
public class RestAdopCommController {
	
	@Autowired
	private RestAdopCommService restAdopCommService;

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private AdopTempService adopTempService;
	
	@Autowired
	private PetFindService petFindService;

	
	
	// =================== 추천 비추천 ===================
	
	@PutMapping(value = "/adopCommBoardCheck",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RestAdopCommVO> adopCommBoardCheck(@RequestBody RestAdopCommVO restAdopCommVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {
			
		int checkNum = restAdopCommService.selectCheck(restAdopCommVO);
		
		
		if(checkNum == 0) { // 추천과 비추천 중 하나라도 하지 않았을 경우 업데이트 (해당 유저)
			restAdopCommService.updateadopCommBoardCheck(restAdopCommVO);
		}else{ // 추천,비추천을 취소했을 경우
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
		
		if(checkNum == 0) {
			// 신고 업데이트
			restAdopCommService.updateadopWaringCheck(restAdopCommVO);
		}else{ // 신고 취소를 했을 경우
			restAdopCommService.deleteWaringCheck(restAdopCommVO);
		}
		
		// 신고 조회(count)
		RestAdopCommVO restAdopWaringCount = restAdopCommService.selectComment(restAdopCommVO);
		
		// 신고 카운트가 10이상이면 게시물 삭제
		if(restAdopWaringCount.getWaringCount() > 1) {

			String type = restAdopCommVO.getBoardType();
			
			// 어떤 게시물인지 타입별로 삭제
			if(type.equals("COMM")) {
				communityService.deleteBoardContent(restAdopCommVO.getBoardId());
				
			}else if(type.equals("ADOP")) {
				adopTempService.deleteBoard(restAdopCommVO.getBoardId());
				
			}else {
				petFindService.deleteFindReport(restAdopCommVO.getBoardId(),type);
			}
			
			restAdopCommService.deletecomm(restAdopCommVO);
			
			String str = "신고로 삭제된 글입니다";
			restAdopWaringCount.setStr(str);
			
		}
		
		
		
		return new ResponseEntity<RestAdopCommVO>(restAdopWaringCount, HttpStatus.OK);
	}
	
	// 추천, 신고 조회
	@GetMapping(value = "/boardWaringAndGood/{boardId}/{memberId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommVO>> boardWaringAndGood(@PathVariable("boardId") String boardId,@PathVariable("memberId") String memberId) {
		
		RestAdopCommVO restAdopCommVO = new RestAdopCommVO();
		restAdopCommVO.setMemberId(memberId);
		restAdopCommVO.setBoardId(boardId);
		
		RestAdopCommVO returltVO1 = restAdopCommService.getBoardWaringAndGood(restAdopCommVO);
		RestAdopCommVO returltVO2 = restAdopCommService.getBoardWaringAndGoodCount(restAdopCommVO);
		
		List<RestAdopCommVO> list = new ArrayList<RestAdopCommVO>();
		
		list.add(returltVO1);
		list.add(returltVO2);
		
		return new ResponseEntity<List<RestAdopCommVO>>(list, HttpStatus.OK);
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
