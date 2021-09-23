package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.RestAdopCommVO;

@Mapper
public interface RestAdopCommMapper {
	
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

	
}
