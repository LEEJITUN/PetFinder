package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.AttachVO;

@Mapper
public interface ReportAttachMapper {

	/* INSERT - �Ű� ���� ���� ���� */
	void insertReportAttach(List<AttachVO> attachList);

}
