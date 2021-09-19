package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;

/**
 * @title   : �ݷ����� �Ծ�|�Ӻ� Mapper
 * @author  : SUMIN
 * @date    : 2021.09.17 
 * @version : 1.2
 **/


@Mapper
public interface AdopTempMapper {
	
	/* INSERT - �Ծ�|�Ӻ� �� �Ѱ� ����ϱ�*/
	void insertBoard(ComBoardVO comBoardVO);
	
	/* SELECT - ���� insert�� �۹�ȣ �������� */
	int selectNextNumber();

	/* SELECT - ��ü �Խñ� ���� �������� */
	List<ComBoardVO> selectBoards();
	
	/* SELECT - ����¡���� �Խñ� ���� �������� */
	List<ComBoardVO> selectBoardsWithPaging(Criteria cri);
	
	/* SELECT - ��ü �۰��� �������� */
	int selectTotalCount();
	
	/* SELECT - �� ��ȣ�� �ش��ϴ� �� �Ѱ� ��������*/
	ComBoardVO selectBoardContent(String boardId);
	
	/* UPDATE - �۹�ȣ�� �ش��ϴ� ���� ��ȸ�� 1 ������Ű */
	void updateBoardReadCount(String boardId);
	
	/* UPDATE - �۹�ȣ�� �ش��ϴ� �� �����ϱ� */
	void updateBoardModify(ComBoardVO comBoardVO);

	
	/* DELETE - �� ��ȣ�� �ش��ϴ� �� �Ѱ� �����ϱ�*/
	void deleteBoard(String boardId);
}
