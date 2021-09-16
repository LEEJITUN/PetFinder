package com.petFinder.mapper;
/**
 * @title   : 커뮤니티 게시판 Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.16 
 * @version : 1.0 
 **/

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;

@Mapper
public interface CommunityMapper {

	/* SELECT - 게시글 번호 조회 */
	int selectBoardNumber();

	/* SELECT - 게시글 전체 가져오기 */
	List<ComBoardVO> selectBoardList();
	
	/* INSERT - 커뮤니티 게시글 작성 */
	void insertBoard(ComBoardVO comBoardVO);

	/* SELECT - 게시글 한개 가져오기 */

}
