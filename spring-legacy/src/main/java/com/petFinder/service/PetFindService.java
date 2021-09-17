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
import com.petFinder.mapper.PetFindMapper;
import com.petFinder.mapper.ReportAttachMapper;




@Service
@Transactional
public class PetFindService {
 
	@Autowired
	private PetFindMapper petFindMapper;
	
	@Autowired
	private ReportAttachMapper reportAttachMapper;
	
	@Autowired	
	private AttachFile attachFile;

	// 유기동물 발견 신고 등록
	@Transactional
	public void insertFindReport(PetVO petVO,ReportBoardVO reportBoardVO, List<MultipartFile> files, String filePorder,String fileType) throws IllegalStateException, IOException {

		// 유기동물 발견 데이터 저장 
		petFindMapper.insertFindReort(petVO);
		
		// 유기동물 발견 게시물 저장
		petFindMapper.insertFindReortBoard(reportBoardVO);
		
		if(!files.get(0).getOriginalFilename().equals("")) {
			// 유기동물 발견 시 관련 실물 파일  저장
			List<AttachVO> attachList = attachFile.uploadFilesAndGetAttachList(files,reportBoardVO.getReportId(),filePorder);
		
			// 유기동물 발견,분실 신고 파일 저장
			reportAttachMapper.insertReportAttach(attachList);			
			attachList = setFileType(attachList,fileType);
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
	

}
