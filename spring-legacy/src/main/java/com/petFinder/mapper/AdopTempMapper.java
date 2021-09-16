package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;

/**
 * @title   : 반려동물 입양|임보 Mapper
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/


@Mapper
public interface AdopTempMapper {
	
	/* INSERT - 입양|임보 글 한개 등록하기*/
	void insertBoard(ComBoardVO comBoardVO);
	
	/* SELECT - 다음 insert할 글번호 가져오기 */
	int selectNextNumber();

	
	int deleteBoard();

	/* SELECT - 게시글 전체 가져오기 */
	List<ComBoardVO> selectBoard();
}
