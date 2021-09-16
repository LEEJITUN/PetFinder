package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;

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

	
	int deleteBoard();

	/* SELECT - �Խñ� ��ü �������� */
	List<ComBoardVO> selectBoard();
}
