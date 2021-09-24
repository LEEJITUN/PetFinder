package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.petFinder.domain.RestAdopCommCommentVO;

@Mapper
public interface RestAdopCommCommentMapper {

	/* SELECT - ´ñ±Û Á¶È¸*/
	List<RestAdopCommCommentVO> selectComments(String reportId);

	/* SELECT -  ´ñ±Û ¹øÈ£ Á¶È¸*/
	int selectCommentIndex(RestAdopCommCommentVO restAdopCommCommentVO);

	/* INSERT - ´ñ±Û ÀÛ¼º*/
	void insertComment(RestAdopCommCommentVO restAdopCommCommentVO);

	/*UPDATE - ´ñ±Û ¼öÁ¤ */
	void updateComment(RestAdopCommCommentVO restAdopCommCommentVO);

	/*DELETE - ´ñ±Û »èÁ¦ */
	void deleteComment(String commentId);

	/* UPDATE - ´ä±Û +1 */
	void updateReSeqPlusOne(RestAdopCommCommentVO restAdopCommCommentVO);

}
