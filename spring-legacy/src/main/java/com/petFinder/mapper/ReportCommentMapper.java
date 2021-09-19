package com.petFinder.mapper;

import java.util.List;

import com.petFinder.domain.ReportBoardCommentVO;

public interface ReportCommentMapper {

	/* SELECT - ��� ��ȸ*/
	List<ReportBoardCommentVO> selectComments(String reportId);

	/* SELECT -  ��� ��ȣ ��ȸ*/
	int selectCommentIndex(ReportBoardCommentVO reportBoardCommentVO);

	/**/
	void updateReSeqPlusOne(String commentRef, String commentSeq);

	/* INSERT - ��� �ۼ�*/
	void insertComment(ReportBoardCommentVO reportBoardCommentVO);

}
