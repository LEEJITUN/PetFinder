package com.petFinder.mapper;
/**
 * @title   : �ݷ����� �н� �Ű� Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;

@Mapper
public interface PetLostMapper {

	/* INSERT - �н� �Ű� ��� */
	void insertLostReport(PetVO petVO);
	
	/* UPDATE - �н� �Ű� ���� */
	void updateLostReport(PetVO petVO);

	/* UPDATE - �н� �Ű� ���� */
	void deleteLostReport(String reportId);

}
