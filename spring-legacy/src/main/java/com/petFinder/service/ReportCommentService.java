package com.petFinder.service;
/**
 * @title   : �ݷ����� �߰� �Ű� Service 
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import java.io.IOException;
import java.util.ArrayList;
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
import com.petFinder.domain.Criteria;
import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardCommentVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.mapper.BannerMapper;
import com.petFinder.mapper.ReportCommentMapper;
import com.petFinder.mapper.PetFindMapper;
import com.petFinder.mapper.PetLostMapper;
import com.petFinder.mapper.ReportAttachMapper;




@Service
@Transactional
public class ReportCommentService {
 
	@Autowired
	private ReportCommentMapper reportCommentMapper;
	
	
	// ��� index ��ȸ
	public int selectCommentIndex(ReportBoardCommentVO reportBoardCommentVO) {
		return reportCommentMapper.selectCommentIndex(reportBoardCommentVO);
	}

	// ��� �ۼ�
	public void insertComment(ReportBoardCommentVO reportBoardCommentVO) {
		reportCommentMapper.insertComment(reportBoardCommentVO);
	}

	// ��� ��ü ��ȸ
	public List<ReportBoardCommentVO> selectComments(String reportId) {
		
		return reportCommentMapper.selectComments(reportId);
	}

	// ��� ����
	public void updateComment(ReportBoardCommentVO reportBoardCommentVO) {
		reportCommentMapper.updateComment(reportBoardCommentVO);
	}

	// ��� ����
	public void deleteComment(String commentId) {
		reportCommentMapper.deleteComment(commentId);
		
	}

	// ��� +1 ������Ʈ
	public void updateReSeqPlusOne(ReportBoardCommentVO reportBoardCommentVO) {
		
		// ����� ���� ���۰� ���� �۱׷� �ȿ���
		//  ������ �������� ū �۵��� ������ 1�� ����(UPDATE)
		reportCommentMapper.updateReSeqPlusOne(reportBoardCommentVO);
		
		// insert�� ��� re������ ����
		reportBoardCommentVO.setCommentLev(reportBoardCommentVO.getCommentLev() + 1);
		reportBoardCommentVO.setCommentSeq(reportBoardCommentVO.getCommentSeq() + 1);
		
		// ��� insert �ϱ�
		reportCommentMapper.insertComment(reportBoardCommentVO);	
	}

	
}
