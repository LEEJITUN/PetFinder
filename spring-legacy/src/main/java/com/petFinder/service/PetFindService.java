package com.petFinder.service;
/**
 * @title   : 반려동물 발견 신고 Service 
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title   : 반려동물 입양|임보 Service
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petFinder.domain.AttachVO;
import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.mapper.AttachMapper;
import com.petFinder.mapper.PetFindMapper;
import com.petFinder.mapper.PetLostMapper;
import com.petFinder.mapper.ReportAttachMapper;




@Service
@Transactional
public class PetFindService {
 
	@Autowired
	private PetFindMapper petFindMapper;
	
	@Autowired
	private PetLostMapper petLostMapper;
	
	@Autowired
	private ReportAttachMapper reportAttachMapper;
	
	@Autowired	
	private AttachFile attachFile;

	// 유기동물 발견 신고 등록
	public void insertFindReport(PetVO petVO,ReportBoardVO reportBoardVO, List<MultipartFile> files, String filePorder,String fileType) throws IllegalStateException, IOException {
		
		if(fileType.equals("F")) {
			// 유기동물 발견 데이터 저장 
			petFindMapper.insertFindReport(petVO);
		}else {
			petLostMapper.insertLostReport(petVO);
		}
		
		// 유기동물 발견 게시물 저장
		petFindMapper.insertFindReportBoard(reportBoardVO);
		
		if(!files.isEmpty()) {			
			if(!files.get(0).getOriginalFilename().equals("")) {
				// 유기동물 발견 시 관련 실물 파일  저장
				List<AttachVO> attachList = attachFile.uploadFilesAndGetAttachList(files,reportBoardVO.getReportId(),filePorder);
				attachList = setFileType(attachList,fileType);
				
				// 유기동물 발견,분실 신고 파일 저장
				reportAttachMapper.insertReportAttach(attachList);			
			}
		}
		
	}

	// 파일 타입 처리
	public List<AttachVO> setFileType(List<AttachVO> attachList,String fileType){
		
		for (AttachVO attachVO : attachList) {
			attachVO.setFiletype(fileType);
		}
		
		return attachList;
	}
	
	// 유기동물 신고 조회 목록
	public List<ReportBoardVO> selectAllFindReport() {		
		return petFindMapper.selectAllFindReport();
	}
	
	// 글번호 
	public int selectBoardNumber (String boardReportType) {
		return petFindMapper.selectBoardNumber(boardReportType);
	}

	// 해당 신고 게시물 조회
	public ReportBoardVO selectFindReport(String reportId) {
		List<AttachVO> attachList =  reportAttachMapper.selectByIdReportAttach(reportId);
		ReportBoardVO reportBoardVO = petFindMapper.selectFindReport(reportId);
		
		// 파일 데이터 셋팅
		reportBoardVO.getPetVO().setAttachList(attachList);
		return reportBoardVO;
	}

	// 해당 신고 게시물 삭제
	public void deleteFindReport(String reportId,String boardReportType) {
	
		// 신고 게시물 삭제
		petFindMapper.deleteFindReportBoard(reportId);
		if(boardReportType.equals("F")){
			// 신고 데이터 삭제
			petFindMapper.deleteFindReport(reportId);
		}else {
			petLostMapper.deleteLostReport(reportId);
		}
		// 신고 파일 데이터 삭제
		reportAttachMapper.deleteFindReport(reportId);
		
		// 신고 실물 파일 삭제
		List<AttachVO> AttachList = reportAttachMapper.selectByIdReportAttach(reportId);
		attachFile.deleteAttachFiles(AttachList);
	}

	// 해당 신고 게시물 수정 (파일 -> 삭제후 저장)
	public void updateFindReport(PetVO petVO, ReportBoardVO reportBoardVO, List<MultipartFile> files, 
			String filePorder,String fileType,List<String> delUuidList) throws IllegalStateException, IOException {
		
		// 신고 게시물 수정
		petFindMapper.updateFindReportBoard(reportBoardVO);
		// 신고 데이터 수정
		petFindMapper.updateFindReport(petVO);
		
		
		/************************** 해당 파일들 삭제 ****************************/
		
		
		List<AttachVO> delAttachList = null;
		
		if (delUuidList != null) {
			delAttachList = reportAttachMapper.getAttachesByUuids(delUuidList);
			attachFile.deleteAttachFiles(delAttachList); // 첨부파일(썸네일도 삭제) 삭제하기
			
			// 파일 삭제
			reportAttachMapper.deleteFindReport(delAttachList.get(0).getReportId());
		}
	
		/************************** 해당 파일들 수정(insert) ****************************/	
		if(!files.isEmpty()) {
			// 신고 실물 파일 수정
			List<AttachVO> attachList = attachFile.uploadFilesAndGetAttachList(files, reportBoardVO.getReportId(),filePorder);
			// 유기동물 발견,분실 신고 파일 저장
			attachList = setFileType(attachList,fileType);
			
			// 신고 파일 데이터 수정
			reportAttachMapper.insertReportAttach(attachList);
		}
	}

	
}
