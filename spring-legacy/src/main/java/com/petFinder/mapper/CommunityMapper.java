package com.petFinder.mapper;
/**
 * @title   : 커뮤니티 게시판 Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.19
 * @version : 1.4
 **/

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;

@Mapper
public interface CommunityMapper {

	/* SELECT - 게시글 번호 조회 */
	int selectBoardNumber();

	/* SELECT - 게시글 전체 가져오기 */
	List<ComBoardVO> selectBoardList();
	
	/* SELECT - 게시글 페이징 */
	List<ComBoardVO> selectBoardsWithPaging(Criteria cri);
	
	/* SELECT - 전체 글개수 */
	int selectTotalCount();
	
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

	/* SELECT - 검색을 적용하여 해당 전체 글개수 가져오기 */
	int selectTotalCountBySearch(Criteria cri);





	
}
