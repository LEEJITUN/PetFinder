package com.petFinder.mapper;
import java.util.List;

/**
 * @title   : �ݷ����� �߰� �Ű� Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.Criteria;
import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;


@Mapper
public interface PetFindMapper {

	/* INSERT - ���⵿�� �Ű� ������ ��� */
	void insertFindReport(PetVO petVO);

	/* INSERT - ���⵿�� �Ű� �Խù� ��� */
	void insertFindReportBoard(ReportBoardVO reportBoardVO);

	/* SELECT - ���⵿�� �Ű� ��ü ��ȸ */
	List<ReportBoardVO> selectAllFindReport(Criteria cri);

	/* SELECT - �Խù� ��ȣ ���� ��ȸ */
	int selectBoardNumber(String boardReportType);

	/* SELECT - �ش� �Խù� ��ȸ */
	ReportBoardVO selectFindReport(String reportId);

	/* DELETE - �ش� �Խù� ���� */
	void deleteFindReportBoard(String reportId);
	
	/* DELETE - �ش� �Ű� ���� */
	void deleteFindReport(String reportId);

	/* UPDATE - �ش� �Ű� �Խù� ���� */
	void updateFindReportBoard(ReportBoardVO reportBoardVO);

	/* UPDATE - �ش� �Ű� ������ ���� */
	void updateFindReport(PetVO petVO);
	
	/* UPDATE - �ش� �Ű� �Խù� ��ȸ�� ���� */
	void updateReportReadCunt(String reportId);

	/* SELECT - �ش� �� ī��Ʈ*/
	int selectTotalCountBySearch(Criteria cri);


}
