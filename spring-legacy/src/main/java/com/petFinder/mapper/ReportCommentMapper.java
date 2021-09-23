package com.petFinder.mapper;

import java.util.List;

import com.petFinder.domain.ReportBoardCommentVO;

public interface ReportCommentMapper {

	/* SELECT - ´ñ±Û Á¶È¸*/
	List<ReportBoardCommentVO> selectComments(String reportId);

	/* SELECT -  ´ñ±Û ¹øÈ£ Á¶È¸*/
	int selectCommentIndex(ReportBoardCommentVO reportBoardCommentVO);

	/* INSERT - ´ñ±Û ÀÛ¼º*/
	void insertComment(ReportBoardCommentVO reportBoardCommentVO);

	/*UPDATE - ´ñ±Û ¼öÁ¤ */
	void updateComment(ReportBoardCommentVO reportBoardCommentVO);

	/*DELETE - ´ñ±Û »èÁ¦ */
	void deleteComment(String commentId);

	/* UPDATE - ´ä±Û +1 */
	void updateReSeqPlusOne(ReportBoardCommentVO reportBoardCommentVO);

}
