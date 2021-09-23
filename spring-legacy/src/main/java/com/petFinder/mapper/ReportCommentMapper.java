package com.petFinder.mapper;

import java.util.List;

import com.petFinder.domain.ReportBoardCommentVO;

public interface ReportCommentMapper {

	/* SELECT - ��� ��ȸ*/
	List<ReportBoardCommentVO> selectComments(String reportId);

	/* SELECT -  ��� ��ȣ ��ȸ*/
	int selectCommentIndex(ReportBoardCommentVO reportBoardCommentVO);

	/* INSERT - ��� �ۼ�*/
	void insertComment(ReportBoardCommentVO reportBoardCommentVO);

	/*UPDATE - ��� ���� */
	void updateComment(ReportBoardCommentVO reportBoardCommentVO);

	/*DELETE - ��� ���� */
	void deleteComment(String commentId);

	/* UPDATE - ��� +1 */
	void updateReSeqPlusOne(ReportBoardCommentVO reportBoardCommentVO);

}
