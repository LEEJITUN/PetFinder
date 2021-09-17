package com.petFinder.service;
/**
 * @title   : �ݷ����� �߰� �Ű� Service 
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title   : �ݷ����� �Ծ�|�Ӻ� Service
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

	// ���⵿�� �߰� �Ű� ���
	@Transactional
	public void insertFindReport(PetVO petVO,ReportBoardVO reportBoardVO, List<MultipartFile> files, String filePorder,String fileType) throws IllegalStateException, IOException {

		// ���⵿�� �߰� ������ ���� 
		petFindMapper.insertFindReort(petVO);
		
		// ���⵿�� �߰� �Խù� ����
		petFindMapper.insertFindReortBoard(reportBoardVO);
		
		if(!files.get(0).getOriginalFilename().equals("")) {
			// ���⵿�� �߰� �� ���� �ǹ� ����  ����
			List<AttachVO> attachList = attachFile.uploadFilesAndGetAttachList(files,reportBoardVO.getReportId(),filePorder);
		
			// ���⵿�� �߰�,�н� �Ű� ���� ����
			reportAttachMapper.insertReportAttach(attachList);			
			attachList = setFileType(attachList,fileType);
		}
		
	}

	// ���� Ÿ�� ó��
	public List<AttachVO> setFileType(List<AttachVO> attachList,String fileType){
		
		for (AttachVO attachVO : attachList) {
			attachVO.setFiletype(fileType);
		}
		
		return attachList;
	}
	
	// ���⵿�� �Ű� ��ȸ ���
	public List<ReportBoardVO> selectAllFindReport() {
		return petFindMapper.selectAllFindReport();
	}
	
	// �۹�ȣ 
	public int selectBoardNumber (String boardReportType) {
		return petFindMapper.selectBoardNumber(boardReportType);
	}
	

}
