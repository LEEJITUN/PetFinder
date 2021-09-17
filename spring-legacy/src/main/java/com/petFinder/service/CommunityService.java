package com.petFinder.service;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Service
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
	
	/* SELECT - �Խñ� ��ü �������� */
	public List<ComBoardVO> selectBoardList() {
		return communityMapper.selectBoardList();		
	}

	/* INSERT - Ŀ�´�Ƽ �Խñ� �ۼ� */
	public void insertBoard(ComBoardVO comBoardVO) {
		
		int num = communityMapper.selectBoardNumber();
		
		// �Խù� Ÿ�� ID �����
		comBoardVO.setBoardTypeId("COMM");
		// �Խù� ID ����� (1,2,3,4,...)
		// �Խù� �⺻ ������
		comBoardVO.setBoardId("COMM_" + num);
		comBoardVO.setBoardNum(num);
		comBoardVO.setBoardReportCount(0);
		comBoardVO.setBoardReadCount(0);
		comBoardVO.setBoardgood(0);
		comBoardVO.setBoardNotgood(0);
		comBoardVO.setBoardRegDate(new Date());
		
		// INSERT�� ������ ó��
		communityMapper.insertBoard(comBoardVO);
	}

	/* SELECT - �Խñ� �ϳ� �������� */
	public ComBoardVO selectBoardContent(String boardId) {	
		return communityMapper.selectBoardContent(boardId);
	}


	



}
