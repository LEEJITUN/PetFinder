package com.petFinder.mapper;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.16 
 * @version : 1.0 
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

	/* SELECT - �Խñ� �Ѱ� �������� */

}
