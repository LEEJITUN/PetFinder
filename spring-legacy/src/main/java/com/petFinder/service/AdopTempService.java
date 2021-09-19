package com.petFinder.service;

import java.util.Date;
import java.util.List;

/**
 * @title   : 반려동물 입양|임보 Service 
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title   : 반려동물 입양|임보 Service
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;
import com.petFinder.mapper.AdopTempMapper;

@Service
@Transactional
public class AdopTempService {

	@Autowired
	private AdopTempMapper adopTempMapper;

	
	public void insertBoard(ComBoardVO comBoardVO) {
		
		/* INSERT - 커뮤니티 게시글 작성 */
		int num = adopTempMapper.selectNextNumber();
		
		// 게시물 타입 ID 만들기
		comBoardVO.setBoardTypeId("ADOP");
		// 게시물 ID 만들기(1,2,3,4,...)
		// 게시물 기본 설정값
		comBoardVO.setBoardId("ADOP_" + num);
		comBoardVO.setBoardNum(num);
		comBoardVO.setBoardReportCount(0);
		comBoardVO.setBoardReadCount(0);
		comBoardVO.setBoardgood(0);
		comBoardVO.setBoardNotgood(0);
		comBoardVO.setBoardRegDate(new Date());
		
//		adopTempMapper.selectBoardNumber();

		// INSERT 시 데이터 처리	
		adopTempMapper.insertBoard(comBoardVO);
	}
	
	/* SELECT - 게시글 전체 가져오기 */
	public List<ComBoardVO> selectBoards() {
		return adopTempMapper.selectBoards();
	}
	
	// 페이징으로 글 가져오기
	public List<ComBoardVO> selectBoards(Criteria cri) {
		// 시작 행번호 (MySQL의 LIMIT절의 시작행번호) 구하기
		
		// 한 페이지당 글개수(amount)가 10개씩일때
		// 1 페이지 -> 0
		// 2 페이지 -> 10
		// 3 페이지 -> 20
		// 4 페이지 -> 30
		// ...
		int startRow = (cri.getPageNum()-1) * cri.getAmount();
		cri.setStartRow(startRow); // 시작행번호 설정
		
		return adopTempMapper.selectBoardsWithPaging(cri);
	}
	
	public void deleteBoard(String boardId) {
		adopTempMapper.deleteBoard(boardId);
	}

	public int selectTotalCount() {
		return adopTempMapper.selectTotalCount();
	}
	
	/* SELECT - 게시글 하나 가져오기 */
	public ComBoardVO selectBoardContent(String boardId) {
		return adopTempMapper.selectBoardContent(boardId);
	}
	
	public void updateBoardReadCount(String boardId) {
		adopTempMapper.updateBoardReadCount(boardId);
	}
	
	/* UPDATE - 게시글 수정하기 */
	public void updateBoardModify(ComBoardVO comBoardVO) {		
		adopTempMapper.updateBoardModify(comBoardVO);
	}
}
