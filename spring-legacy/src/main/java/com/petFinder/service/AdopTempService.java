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
import com.petFinder.mapper.AdopTempMapper;

@Service
@Transactional
public class AdopTempService {

	@Autowired
	private AdopTempMapper adopTempMapper;

	
	public void insertBoard(ComBoardVO comBoardVO) {
		
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
	
	public int deleteBoard() {
		return adopTempMapper.deleteBoard();
	}

	public List<ComBoardVO> selectBoard() {
		
		return adopTempMapper.selectBoard();
	}

}
