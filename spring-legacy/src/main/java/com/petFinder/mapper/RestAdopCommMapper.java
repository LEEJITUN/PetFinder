package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.RestAdopCommVO;

@Mapper
public interface RestAdopCommMapper {
	
	/* UPDATE - ��õ ����õ */
	void updateadopCommBoardCheck(RestAdopCommVO restAdopCommVO);

	/* SELECT - �Խñ� ��ȣ ���� */
	int selectNextNumber(RestAdopCommVO restAdopCommVO);

	/* SELECT - ��õ ����õ ���� ����*/
	RestAdopCommVO selectTotalCount(RestAdopCommVO restAdopCommVO);

	/* SELECT - ��õ ����õ �ߴ��� ���� ��ȸ*/
	int selectCheck(RestAdopCommVO restAdopCommVO);

	/* DELETE - ��õ OR ����õ�� ������� ���� (�ش� ����) */
	int deleteCommBoard(RestAdopCommVO restAdopCommVO);

	
}
