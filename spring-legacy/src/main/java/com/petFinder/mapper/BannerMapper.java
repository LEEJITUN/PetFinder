package com.petFinder.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.AttachVO;





@Mapper
public interface BannerMapper {

	
	/* SELECT - ��� ��ȸ*/
	List<AttachVO> selectBanner();
	
	/* SELECT - �Ű� �Խù� �� ��õ���� ���� ���� 3���� �Խù� ID*/
	List<AttachVO> selectBestOfReportBoard();

	/* INSERT - ��� ����*/
	void insertBanner(List<AttachVO> attachList);

	/* DELETE - ��� ������ all ���� */
	void deleteBanner();
	
}
