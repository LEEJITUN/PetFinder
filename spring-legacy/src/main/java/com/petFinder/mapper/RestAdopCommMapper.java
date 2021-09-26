package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.RestAdopCommVO;

@Mapper
public interface RestAdopCommMapper {
	
	// ================ 추천 비추천 =================
	
	/* UPDATE - 추천 비추천 */
	void updateadopCommBoardCheck(RestAdopCommVO restAdopCommVO);

	/* SELECT - 게시글 번호 생성 */
	int selectNextNumber(RestAdopCommVO restAdopCommVO);

	/* SELECT - 추천 비추천 각각 갯수*/
	RestAdopCommVO selectTotalCount(RestAdopCommVO restAdopCommVO);

	/* SELECT - 추천 비추천 했는지 유무 조회*/
	int selectCheck(RestAdopCommVO restAdopCommVO);

	/* DELETE - 추천 OR 비추천이 있을경우 삭제 (해당 유저) */
	int deleteCommBoard(RestAdopCommVO restAdopCommVO);
	
	 // ================ 신고 =================

//	/* UPDATE - 신고 */
//	void updateadopWaringCheck(RestAdopCommVO restAdopCommVO);
//	
//	/* SELECT - 신고 갯수*/
//	RestAdopCommVO selectComment(RestAdopCommVO restAdopCommVO);
//
//	/* SELECT - 해당 게시물에 신고를 했는지 조회 */
//	int selectWaring(RestAdopCommVO restAdopCommVO);
//
//	/* DELETE - 해당 게시물에 신고 취소흘 했을 경우 삭제(해당 유저) */
//	int deleteWaringCheck(RestAdopCommVO restAdopCommVO);

	
}
