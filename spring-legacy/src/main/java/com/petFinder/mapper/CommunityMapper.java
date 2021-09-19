package com.petFinder.mapper;
/**
 * @title   : 커뮤니티 게시판 Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.18
 * @version : 1.3
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
	void insertBoardWrite(ComBoardVO comBoardVO);

	/* SELECT - 글번호에 해당하는 글 한개 가져오기 */
	ComBoardVO selectBoardContent(String boardId);
	
	/* UPDATE - 게시글 조회수 업 */
	void updateBoardReadCount(String boardId);

	/* UPDATE - 글번호에 해당하는 글 수정하기 */
	void updateBoardModify(ComBoardVO comBoardVO); // 글제목, 글내용 수정

	/* DELETE - 글번호에 해당하는 글 삭제하기 */
	void deleteBoardContent(String boardId);

	
}
