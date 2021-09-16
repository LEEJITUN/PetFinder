package com.petFinder.mapper;
/**
 * @title   : 커뮤니티 게시판 Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.16 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;

@Mapper
public interface CommunityMapper {
	
	/* INSERT - 커뮤니티 게시글 작성 */
	void insertBoard(ComBoardVO comBoardVO);

	/* SELECT - 게시글 번호 조회 */
	int selectBoardNumber();
}
