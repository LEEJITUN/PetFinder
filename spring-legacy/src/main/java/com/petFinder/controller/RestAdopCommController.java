package com.petFinder.controller;
/**
 * @title   : ��õ | ����õ | �Ű� ��� Controller
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

	// =================== ��õ ����õ ===================
	
	@PutMapping(value = "/adopCommBoardCheck",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RestAdopCommVO> adopCommBoardCheck(@RequestBody RestAdopCommVO restAdopCommVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {
			
		int checkNum = restAdopCommService.selectCheck(restAdopCommVO);
		int waringcheckNum = restAdopCommService.selectCheck(restAdopCommVO);
		
		// ��õ�� ����õ �� �ϳ��� ���� �ʾ��� ��� ������Ʈ (�ش� ����)
		if(checkNum == 0) {
			// �Ű� ������Ʈ
			restAdopCommService.updateadopCommBoardCheck(restAdopCommVO);
		}else{ // �Ű� ��Ҹ� ���� ��� , ��õ,����õ���� ���� ���
				restAdopCommService.deleteCommBoard(restAdopCommVO);
		}
		
		// ��õ ����õ ��ȸ(count)
		RestAdopCommVO restAdopCommCount = restAdopCommService.selectTotalCount(restAdopCommVO);

	
		return new ResponseEntity<RestAdopCommVO>(restAdopCommCount, HttpStatus.OK);
	} 
	
	// ===================== �Ű� =====================

	
	@PutMapping(value = "/adopTempBoardWaring",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })			
	public ResponseEntity<RestAdopCommVO> adopTempBoardWaring(@RequestBody RestAdopCommVO restAdopCommVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {
		
		int checkNum = restAdopCommService.selectWaring(restAdopCommVO);
		
		int goodcheckNum = restAdopCommService.selectCheck(restAdopCommVO);
		
		// �̹� �Ű� ���� ��� or ��õ,����õ�� ���� ���� ���
		if(checkNum == 0) {
			// �Ű� ������Ʈ
			restAdopCommService.updateadopWaringCheck(restAdopCommVO);
		}else{ // �Ű� ��Ҹ� ���� ��� , ��õ,����õ���� ���� ���
				restAdopCommService.deleteWaringCheck(restAdopCommVO);
		}
		
		// �Ű� ��ȸ(count)
		RestAdopCommVO restAdopWaringCount = restAdopCommService.selectComment(restAdopCommVO);
		
		
		return new ResponseEntity<RestAdopCommVO>(restAdopWaringCount, HttpStatus.OK);
	}
	
	// ��õ, �Ű� ��ȸ
	@GetMapping(value = "/boardWaringAndGood/{boardId}/{memberId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RestAdopCommVO> boardWaringAndGood(@PathVariable("boardId") String boardId,@PathVariable("memberId") String memberId) {

		RestAdopCommVO restAdopCommVO = new RestAdopCommVO();
		restAdopCommVO.setMemberId(memberId);
		restAdopCommVO.setBoardId(boardId);
		
		RestAdopCommVO returltVO = restAdopCommService.getBoardWaringAndGood(restAdopCommVO);
		
		return new ResponseEntity<RestAdopCommVO>(returltVO, HttpStatus.OK);
	}
	
	// =================== ��� ���� ===================	
	
	// ��� ���� -> ��� �ۼ�
	@PostMapping(value = "/adopCommCommentWrite",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentWrite(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== ������ ���� ======
		
		// insert�� �� �۹�ȣ ��������
		int index = restAdopCommService.selectCommentIndex(restAdopCommCommentVO);
		
		restAdopCommCommentVO.setBoardNum(index);
		restAdopCommCommentVO.setCommentId(restAdopCommCommentVO.getBoardId() + "_" + index);
		restAdopCommCommentVO.setCommentRegDate(new Date());
		restAdopCommCommentVO.setCommentRef(restAdopCommCommentVO.getBoardNum());

		restAdopCommService.insertComment(restAdopCommCommentVO);
		
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
	
	// =========================== �� �� ==================================
	
	// ��� ���� -> ��� ��� �ۼ�
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

	// =========================== �� �� ==================================
	
	// ��� ���� -> ��� ����
	@PutMapping(value = "/adopCommCommentModify",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentModify(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== ������ ���� ======
		restAdopCommCommentVO.setCommentUpDate(new Date());

		restAdopCommService.updateComment(restAdopCommCommentVO);
		
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
	
	// =============================================================
	
	
	// ��� ���� -> ��� ����
	@DeleteMapping(value = "/adopCommCommentDelete",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentDelete(@RequestBody RestAdopCommCommentVO restAdopCommCommentVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {

		// ===== ������ ���� ======
		restAdopCommCommentVO.setCommentUpDate(new Date());

		restAdopCommService.deleteComment(restAdopCommCommentVO.getCommentId());
		
		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(restAdopCommCommentVO.getBoardId());
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
		
	
	// ��� ���� -> ��� ��ü ��ȸ
	@GetMapping(value = "/adopCommCommentList/{boardId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<RestAdopCommCommentVO>> adopCommCommentList(@PathVariable("boardId") String boardId) {


		List<RestAdopCommCommentVO> RestAdopCommCommenList = restAdopCommService.selectComments(boardId);
		
		return new ResponseEntity<List<RestAdopCommCommentVO>>(RestAdopCommCommenList, HttpStatus.OK);
	} 
   
}
