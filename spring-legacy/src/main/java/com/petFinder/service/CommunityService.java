package com.petFinder.service;
/**
 * @title   : 커뮤니티 게시판 Service
 * @author  : HYEPIN
 * @date    : 2021.09.19
 * @version : 1.4
 **/

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;
import com.petFinder.mapper.CommunityMapper;


@Service
@Transactional
public class CommunityService {

	@Autowired
	private CommunityMapper communityMapper;
	
	/* SELECT - 게시글 전체 가져오기 */
	public List<ComBoardVO> selectBoardList() {
		return communityMapper.selectBoardList();		
	}
	
	/* SELECT - 페이징으로 글 가져오기 */
	public List<ComBoardVO> selectBoardPaging(Criteria cri) {
		// 시작 행번호 (MySQL의 LIMIT절의 시작행번호) 구하기
		
		// 한 페이지당 글개수(amount)가 10개씩일때
		// 1 페이지 -> 0
		// 2 페이지 -> 10
		// 3 페이지 -> 20
		// 4 페이지 -> 30
		// ...
		int startRow = (cri.getPageNum()-1) * cri.getAmount();
		cri.setStartRow(startRow); // 시작행번호 설정
		
		return communityMapper.selectBoardsWithPaging(cri);
	}
	
	/* SELECT - 검색이 적용된 전체 글개수*/
	public int selectTotalCount() {
		return communityMapper.selectTotalCount();
	}

	/* INSERT - 커뮤니티 게시글 작성 */
	public void insertBoardWrite(ComBoardVO comBoardVO) {
		
		int num = communityMapper.selectBoardNumber();
		
		// 게시물 타입 ID 만들기
		comBoardVO.setBoardTypeId("COMM");
		// 게시물 ID 만들기 (1,2,3,4,...)
		// 게시물 기본 설정값
		comBoardVO.setBoardId("COMM_" + num);
		comBoardVO.setBoardNum(num);
		comBoardVO.setBoardReportCount(0);
		comBoardVO.setBoardReadCount(0);
		comBoardVO.setBoardgood(0);
		comBoardVO.setBoardNotgood(0);
		comBoardVO.setBoardRegDate(new Date());
		
		// INSERT시 데이터 처리
		communityMapper.insertBoardWrite(comBoardVO);
	}

	/* SELECT - 게시글 하나 가져오기 */
	public ComBoardVO selectBoardContent(String boardId) {	
		return communityMapper.selectBoardContent(boardId);
	}
	
	/* UPDATE - 게시글 조회수 업 */
	public void updateBoardReadCount(String boardId) {
		communityMapper.updateBoardReadCount(boardId);
	}	

	/* UPDATE - 게시글 수정하기 */
	public void updateBoardModify(ComBoardVO comBoardVO) {		
		communityMapper.updateBoardModify(comBoardVO);
	}

	/* DELETE - 게시글 삭제하기 */
	public void deleteBoardContent(String boardId) {
		communityMapper.deleteBoardContent(boardId);	
	}

	public int selectTotalCountBySearch(Criteria cri) {
		return communityMapper.selectTotalCountBySearch(cri);
	}






	



}
