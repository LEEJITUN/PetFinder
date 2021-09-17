package com.petFinder.service;

import java.util.Date;
import java.util.List;

/**
 * @title   : �ݷ����� �Ծ�|�Ӻ� Service 
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title   : �ݷ����� �Ծ�|�Ӻ� Service
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
		
		// INSERT�� ���۹�ȣ ��������
		int num = adopTempMapper.selectNextNumber();
		
		// �Խù� Ÿ�� ID �����
		comBoardVO.setBoardTypeId("ADOP");
		// �Խù� ID �����(1,2,3,4,...)
		// �Խù� �⺻ ������
		comBoardVO.setBoardId("ADOP_" + num);
		comBoardVO.setBoardNum(num);
		comBoardVO.setBoardReportCount(0);
		comBoardVO.setBoardReadCount(0);
		comBoardVO.setBoardgood(0);
		comBoardVO.setBoardNotgood(0);
		comBoardVO.setBoardRegDate(new Date());
		
//		adopTempMapper.selectBoardNumber();

		// INSERT �� ������ ó��	
		adopTempMapper.insertBoard(comBoardVO);
	}
	
	// ��ü �Խñ� ���� ��������
	public List<ComBoardVO> selectBoard() {
		return adopTempMapper.selectBoard();
	}
	
	// ����¡���� �� ��������
	public List<ComBoardVO> selectBoard(Criteria cri) {
		// ���� ���ȣ (MySQL�� LIMIT���� �������ȣ) ���ϱ�
		
		// �� �������� �۰���(amount)�� 10�����϶�
		// 1 ������ -> 0
		// 2 ������ -> 10
		// 3 ������ -> 20
		// 4 ������ -> 30
		// ...
		int startRow = (cri.getPageNum()-1) * cri.getAmount();
		cri.setStartRow(startRow); // �������ȣ ����
		
		return adopTempMapper.selectBoardsWithPaging(cri);
	}
		
	public int deleteAll() {
		return adopTempMapper.deleteAll();
	}

	public int selectTotalCount() {
		return adopTempMapper.selectTotalCount();
	}
	
	public ComBoardVO selectBoardNum(int num) {
		return adopTempMapper.selectBoardNum(num);
	}
}
