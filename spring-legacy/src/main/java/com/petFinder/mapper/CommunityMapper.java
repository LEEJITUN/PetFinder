package com.petFinder.mapper;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.18
 * @version : 1.3
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
	void insertBoardWrite(ComBoardVO comBoardVO);

	/* SELECT - �۹�ȣ�� �ش��ϴ� �� �Ѱ� �������� */
	ComBoardVO selectBoardContent(String boardId);
	
	/* UPDATE - �Խñ� ��ȸ�� �� */
	void updateBoardReadCount(String boardId);

	/* UPDATE - �۹�ȣ�� �ش��ϴ� �� �����ϱ� */
	void updateBoardModify(ComBoardVO comBoardVO); // ������, �۳��� ����

	/* DELETE - �۹�ȣ�� �ش��ϴ� �� �����ϱ� */
	void deleteBoardContent(String boardId);

	
}
