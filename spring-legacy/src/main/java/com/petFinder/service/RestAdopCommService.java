package com.petFinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.RestAdopCommCommentVO;
import com.petFinder.domain.RestAdopCommVO;
import com.petFinder.mapper.RestAdopCommCommentMapper;
import com.petFinder.mapper.RestAdopCommMapper;



@Service
@Transactional
public class RestAdopCommService {

	@Autowired
	private RestAdopCommMapper restAdopCommMapper;
	
	@Autowired
	private RestAdopCommCommentMapper restAdopCommCommentMapper;
	
	// ================ ��õ ����õ =================
	
	/* UPDATE - ��õ ����õ */
	public void updateadopCommBoardCheck(RestAdopCommVO restAdopCommVO) {
		
		int num = restAdopCommMapper.selectNextNumber(restAdopCommVO);
		restAdopCommVO.setNum(num);
		restAdopCommMapper.updateadopCommBoardCheck(restAdopCommVO);
	}

	/* SELECT - ��õ ����õ ���� ����*/
	public RestAdopCommVO selectTotalCount(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectTotalCount(restAdopCommVO);
		
	}
	
	/* SELECT - �ش� �Խù��� ��õ or ����õ�� �ߴ��� ��ȸ */
	public int selectCheck(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectCheck(restAdopCommVO);
		
	}
	
	/* DELETE - ��õ OR ����õ�� ������� ���� (�ش� ����) */
	public int deleteCommBoard(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.deleteCommBoard(restAdopCommVO);
		
	}
	 // ================ �Ű� =================

	/* UPDATE - �Ű� */
	public void updateadopWaringCheck(RestAdopCommVO restAdopCommVO) {
		
		int num = restAdopCommMapper.selectNextNumber(restAdopCommVO);
		restAdopCommVO.setNum(num);
		restAdopCommMapper.updateadopWaringCheck(restAdopCommVO);
	}

	/* SELECT - �Ű� ���� */
	public RestAdopCommVO selectComment(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectComment(restAdopCommVO);
	}
	
	/* SELECT - �ش� �Խù��� �Ű� �ߴ��� ��ȸ */
	public int selectWaring(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectWaring(restAdopCommVO);
	}

	/* DELETE - �ش� �Խù��� �Ű� ����� ���� ��� ����(�ش� ����) */
	public int deleteWaringCheck(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.deleteWaringCheck(restAdopCommVO);
	}
	
	// ��õ,����õ,�Ű� ��ü ��ȸ
	public RestAdopCommVO getBoardWaringAndGood(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.getBoardWaringAndGood(restAdopCommVO);
	}

	// 
	public RestAdopCommVO getBoardWaringAndGoodCount(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.getBoardWaringAndGoodCount(restAdopCommVO);
	}
	
	// ================= ��� =================

	/* SELECT - ��� index ��ȸ) */
	public int selectCommentIndex(RestAdopCommCommentVO restAdopCommCommentVO) {
		return restAdopCommCommentMapper.selectCommentIndex(restAdopCommCommentVO);
	}

	// ��� �ۼ�
	public void insertComment(RestAdopCommCommentVO restAdopCommCommentVO) {
		restAdopCommCommentMapper.insertComment(restAdopCommCommentVO);
	}

	// ��� ��ü ��ȸ
	public List<RestAdopCommCommentVO> selectComments(String reportId) {
		return restAdopCommCommentMapper.selectComments(reportId);
	}

	// ��� ����
	public void updateComment(RestAdopCommCommentVO restAdopCommCommentVO) {
		restAdopCommCommentMapper.updateComment(restAdopCommCommentVO);
	}

	// ��� ����
	public void deleteComment(String commentId) {
		restAdopCommCommentMapper.deleteComment(commentId);
		
	}

	// ��� +1 ������Ʈ
	public void updateReSeqPlusOne(RestAdopCommCommentVO restAdopCommCommentVO) {
		
		// ����� ���� ���۰� ���� �۱׷� �ȿ���
		//  ������ �������� ū �۵��� ������ 1�� ����(UPDATE)
		restAdopCommCommentMapper.updateReSeqPlusOne(restAdopCommCommentVO);
		
		// insert�� ��� re������ ����
		restAdopCommCommentVO.setCommentLev(restAdopCommCommentVO.getCommentLev() + 1);
		restAdopCommCommentVO.setCommentSeq(restAdopCommCommentVO.getCommentSeq() + 1);
		
		// ��� insert �ϱ�
		restAdopCommCommentMapper.insertComment(restAdopCommCommentVO);	
	}

	
}
