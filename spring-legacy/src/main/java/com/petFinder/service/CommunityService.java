package com.petFinder.service;
/**
 * @title   : 커뮤니티 게시판 Service
 * @author  : HYEPIN
 * @date    : 2021.09.17
 * @version : 1.1 
 **/

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.ComBoardVO;
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

	/* INSERT - 커뮤니티 게시글 작성 */
	public void insertBoard(ComBoardVO comBoardVO) {
		
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
		communityMapper.insertBoard(comBoardVO);
	}

	/* SELECT - 게시글 하나 가져오기 */
	public ComBoardVO selectBoardContent(String boardId) {	
		return communityMapper.selectBoardContent(boardId);
	}


	



}
