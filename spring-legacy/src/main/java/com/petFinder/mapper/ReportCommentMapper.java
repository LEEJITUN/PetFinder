package com.petFinder.mapper;

import java.util.List;

import com.petFinder.domain.ReportBoardCommentVO;

public interface ReportCommentMapper {

	/* SELECT - ´ñ±Û Á¶È¸*/
	List<ReportBoardCommentVO> selectComments(String reportId);

	/* SELECT -  ´ñ±Û ¹øÈ£ Á¶È¸*/
	int selectCommentIndex(ReportBoardCommentVO reportBoardCommentVO);

	/**/
	void updateReSeqPlusOne(String commentRef, String commentSeq);

	/* INSERT - ´ñ±Û ÀÛ¼º*/
	void insertComment(ReportBoardCommentVO reportBoardCommentVO);

}
