package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.AttachVO;

@Mapper
public interface ReportAttachMapper {

	/* INSERT - 신고 관련 파일 저장 */
	void insertReportAttach(List<AttachVO> attachList);

}
