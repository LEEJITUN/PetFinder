package com.petFinder.mapper;
import java.util.List;

/**
 * @title   : �ݷ����� �н� �Ű� Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;

@Mapper
public interface PetLostMapper {

	/* INSERT - �н� �Ű� ��� */
	void insertLostReport(PetVO petVO);
	
	/* UPDATE - �н� �Ű� ���� */
	void updateLostReport(PetVO petVO);

	/* UPDATE - �н� �Ű� ���� */
	void deleteLostReport(String reportId);

	/* SELECT - �н� �Ű� ��ü ��ȸ */
	List<ReportBoardVO> selectAllLostReport();
	
	/* SELECT - �ش� �н� �Ű� ��ü ��ȸ */
	ReportBoardVO selectLostReport(String reportId);

}
