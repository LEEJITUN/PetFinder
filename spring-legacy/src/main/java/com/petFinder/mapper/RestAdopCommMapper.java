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

	
}
