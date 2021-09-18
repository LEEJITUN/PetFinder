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

	// ���⵿�� �߰� �Ű� ���
	public void insertFindReport(PetVO petVO,ReportBoardVO reportBoardVO, List<MultipartFile> files, String filePorder,String fileType) throws IllegalStateException, IOException {
		
		if(fileType.equals("F")) {
			// ���⵿�� �߰� ������ ���� 
			petFindMapper.insertFindReport(petVO);
		}else {
			petLostMapper.insertLostReport(petVO);
		}
		
		// ���⵿�� �߰� �Խù� ����
		petFindMapper.insertFindReportBoard(reportBoardVO);
		
		if(!files.isEmpty()) {			
			if(!files.get(0).getOriginalFilename().equals("")) {
				// ���⵿�� �߰� �� ���� �ǹ� ����  ����
				List<AttachVO> attachList = attachFile.uploadFilesAndGetAttachList(files,reportBoardVO.getReportId(),filePorder);
				attachList = setFileType(attachList,fileType);
				
				// ���⵿�� �߰�,�н� �Ű� ���� ����
				reportAttachMapper.insertReportAttach(attachList);			
			}
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

	// �ش� �Ű� �Խù� ��ȸ
	public ReportBoardVO selectFindReport(String reportId) {
		List<AttachVO> attachList =  reportAttachMapper.selectByIdReportAttach(reportId);
		ReportBoardVO reportBoardVO = petFindMapper.selectFindReport(reportId);
		
		// ���� ������ ����
		reportBoardVO.getPetVO().setAttachList(attachList);
		return reportBoardVO;
	}

	// �ش� �Ű� �Խù� ����
	public void deleteFindReport(String reportId,String boardReportType) {
	
		// �Ű� �Խù� ����
		petFindMapper.deleteFindReportBoard(reportId);
		if(boardReportType.equals("F")){
			// �Ű� ������ ����
			petFindMapper.deleteFindReport(reportId);
		}else {
			petLostMapper.deleteLostReport(reportId);
		}
		// �Ű� ���� ������ ����
		reportAttachMapper.deleteFindReport(reportId);
		
		// �Ű� �ǹ� ���� ����
		List<AttachVO> AttachList = reportAttachMapper.selectByIdReportAttach(reportId);
		attachFile.deleteAttachFiles(AttachList);
	}

	// �ش� �Ű� �Խù� ���� (���� -> ������ ����)
	public void updateFindReport(PetVO petVO, ReportBoardVO reportBoardVO, List<MultipartFile> files, 
			String filePorder,String fileType,List<String> delUuidList) throws IllegalStateException, IOException {
		
		// �Ű� �Խù� ����
		petFindMapper.updateFindReportBoard(reportBoardVO);
		// �Ű� ������ ����
		petFindMapper.updateFindReport(petVO);
		
		
		/************************** �ش� ���ϵ� ���� ****************************/
		
		
		List<AttachVO> delAttachList = null;
		
		if (delUuidList != null) {
			delAttachList = reportAttachMapper.getAttachesByUuids(delUuidList);
			attachFile.deleteAttachFiles(delAttachList); // ÷������(����ϵ� ����) �����ϱ�
			
			// ���� ����
			reportAttachMapper.deleteFindReport(delAttachList.get(0).getReportId());
		}
	
		/************************** �ش� ���ϵ� ����(insert) ****************************/	
		if(!files.isEmpty()) {
			// �Ű� �ǹ� ���� ����
			List<AttachVO> attachList = attachFile.uploadFilesAndGetAttachList(files, reportBoardVO.getReportId(),filePorder);
			// ���⵿�� �߰�,�н� �Ű� ���� ����
			attachList = setFileType(attachList,fileType);
			
			// �Ű� ���� ������ ����
			reportAttachMapper.insertReportAttach(attachList);
		}
	}

	
}
