package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.petFinder.domain.RestAdopCommCommentVO;

@Mapper
public interface RestAdopCommCommentMapper {

	/* SELECT - ��� ��ȸ*/
	List<RestAdopCommCommentVO> selectComments(String reportId);

	/* SELECT -  ��� ��ȣ ��ȸ*/
	int selectCommentIndex(RestAdopCommCommentVO restAdopCommCommentVO);

	/* INSERT - ��� �ۼ�*/
	void insertComment(RestAdopCommCommentVO restAdopCommCommentVO);

	/*UPDATE - ��� ���� */
	void updateComment(RestAdopCommCommentVO restAdopCommCommentVO);

	/*DELETE - ��� ���� */
	void deleteComment(String commentId);

	/* UPDATE - ��� +1 */
	void updateReSeqPlusOne(RestAdopCommCommentVO restAdopCommCommentVO);

}
