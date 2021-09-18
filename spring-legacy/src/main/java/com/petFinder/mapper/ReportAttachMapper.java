package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.AttachVO;

@Mapper
public interface ReportAttachMapper {

	/* INSERT - �Ű� ���� ���� ��� */
	void insertReportAttach(List<AttachVO> attachList);
	
	/* SELECT - ��� �Ű� ���� ��ȸ */
	List<AttachVO> selectAllReportAttach();
	
	/* SELECT - �ش� �Ű� ���� ��ȸ */	
	List<AttachVO> selectByIdReportAttach(String reportId);
	
	/* SELECT - �ش� UUID ��ȸ*/
	List<AttachVO> getAttachesByUuids(List<String> delUuidList);	
	
	/* DELETE - �ش� �Ű� ���� ���� */
	void deleteFindReport(String reportId);

	/* UPDATE - �ش� �Ű� ���� ���� */
	void updateFindReport(List<AttachVO> attachList);



}
