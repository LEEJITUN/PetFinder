package com.petFinder.mapper;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.17
 * @version : 1.2 
 **/

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;

@Mapper
public interface CommunityMapper {

	/* SELECT - �Խñ� ��ȣ ��ȸ */
	int selectBoardNumber();

	/* SELECT - �Խñ� ��ü �������� */
	List<ComBoardVO> selectBoardList();
	
	/* INSERT - Ŀ�´�Ƽ �Խñ� �ۼ� */
	void insertBoard(ComBoardVO comBoardVO);

	/* SELECT - �۹�ȣ�� �ش��ϴ� �� �Ѱ� �������� */
	ComBoardVO selectBoardContent(String boardId);

	/* UPDATE - �۹�ȣ�� �ش��ϴ� �� �����ϱ� */
	void updateBoardModify(ComBoardVO comBoardVO);
}
