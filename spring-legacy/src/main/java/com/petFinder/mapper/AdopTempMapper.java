package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;

/**
 * @title   : �ݷ����� �Ծ�|�Ӻ� Mapper
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/


@Mapper
public interface AdopTempMapper {
	
	/* INSERT - �Ծ�|�Ӻ� �� �Ѱ� ����ϱ�*/
	void insertBoard(ComBoardVO comBoardVO);
	
	/* SELECT - ���� insert�� �۹�ȣ �������� */
	int selectNextNumber();

	/* SELECT - ��ü �Խñ� ���� �������� */
	List<ComBoardVO> selectBoard();
	
	/* SELECT - ����¡���� �Խñ� ���� �������� */
	List<ComBoardVO> selectBoardsWithPaging(Criteria cri);
	
	/* DELETE - ��ü �� ����*/
	int deleteAll();
	
	/* SELECT - ��ü �۰��� �������� */
	int selectTotalCount();
	
	/* SELECT - �� ��ȣ�� �ش��ϴ� �� �Ѱ� ��������*/
	ComBoardVO selectBoardNum(int num);
	
	/* UPDATE - */
	void updateBoardReadCount(int num);
}
