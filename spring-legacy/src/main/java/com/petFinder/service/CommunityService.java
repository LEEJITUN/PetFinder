package com.petFinder.service;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Service
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
	
	/* SELECT - �Խñ� ��ü �������� */
	public List<ComBoardVO> selectBoardList() {
		return communityMapper.selectBoardList();		
	}
	
	/* SELECT - ����¡���� �� �������� */
	public List<ComBoardVO> selectBoardPaging(Criteria cri) {
		// ���� ���ȣ (MySQL�� LIMIT���� �������ȣ) ���ϱ�
		
		// �� �������� �۰���(amount)�� 10�����϶�
		// 1 ������ -> 0
		// 2 ������ -> 10
		// 3 ������ -> 20
		// 4 ������ -> 30
		// ...
		int startRow = (cri.getPageNum()-1) * cri.getAmount();
		cri.setStartRow(startRow); // �������ȣ ����
		
		return communityMapper.selectBoardsWithPaging(cri);
	}
	
	/* SELECT - �˻��� ����� ��ü �۰���*/
	public int selectTotalCount() {
		return communityMapper.selectTotalCount();
	}

	/* INSERT - Ŀ�´�Ƽ �Խñ� �ۼ� */
	public void insertBoardWrite(ComBoardVO comBoardVO) {
		
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
		communityMapper.insertBoardWrite(comBoardVO);
	}

	/* SELECT - �Խñ� �ϳ� �������� */
	public ComBoardVO selectBoardContent(String boardId) {	
		return communityMapper.selectBoardContent(boardId);
	}
	
	/* UPDATE - �Խñ� ��ȸ�� �� */
	public void updateBoardReadCount(String boardId) {
		communityMapper.updateBoardReadCount(boardId);
	}	

	/* UPDATE - �Խñ� �����ϱ� */
	public void updateBoardModify(ComBoardVO comBoardVO) {		
		communityMapper.updateBoardModify(comBoardVO);
	}

	/* DELETE - �Խñ� �����ϱ� */
	public void deleteBoardContent(String boardId) {
		communityMapper.deleteBoardContent(boardId);	
	}

	public int selectTotalCountBySearch(Criteria cri) {
		return communityMapper.selectTotalCountBySearch(cri);
	}






	



}
