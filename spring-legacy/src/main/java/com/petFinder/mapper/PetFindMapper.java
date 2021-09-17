package com.petFinder.mapper;
import java.util.List;

/**
 * @title   : �ݷ����� �߰� �Ű� Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;


@Mapper
public interface PetFindMapper {

	/* INSERT - ���⵿�� �Ű� ������ ���*/
	void insertFindReort(PetVO petVO);

	/* INSERT - ���⵿�� �Ű� �Խù� ��� */
	void insertFindReortBoard(ReportBoardVO reportBoardVO);

	/* SELECT - ���⵿�� �Ű� ��ü ��ȸ*/
	List<ReportBoardVO> selectAllFindReport();

	/* SELECT - �Խù� ��ȣ ����*/
	int selectBoardNumber(String boardReportType);


	


}
