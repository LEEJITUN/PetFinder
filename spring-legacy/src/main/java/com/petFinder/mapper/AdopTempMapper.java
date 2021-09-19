package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;

/**
 * @title   : 반려동물 입양|임보 Mapper
 * @author  : SUMIN
 * @date    : 2021.09.17 
 * @version : 1.2
 **/


@Mapper
public interface AdopTempMapper {
	
	/* INSERT - 입양|임보 글 한개 등록하기*/
	void insertBoard(ComBoardVO comBoardVO);
	
	/* SELECT - 다음 insert할 글번호 가져오기 */
	int selectNextNumber();

	/* SELECT - 전체 게시글 내용 가져오기 */
	List<ComBoardVO> selectBoards();
	
	/* SELECT - 페이징으로 게시글 내용 가져오기 */
	List<ComBoardVO> selectBoardsWithPaging(Criteria cri);
	
	/* SELECT - 전체 글개수 가져오기 */
	int selectTotalCount();
	
	/* SELECT - 글 번호에 해당하는 글 한개 가져오기*/
	ComBoardVO selectBoardContent(String boardId);
	
	/* UPDATE - 글번호에 해당하는 글의 조회수 1 증가시키 */
	void updateBoardReadCount(String boardId);
	
	/* UPDATE - 글번호에 해당하는 글 수정하기 */
	void updateBoardModify(ComBoardVO comBoardVO);

	
	/* DELETE - 글 번호에 해당하는 글 한개 삭제하기*/
	void deleteBoard(String boardId);
}
