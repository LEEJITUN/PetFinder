package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.AttachVO;

@Mapper
public interface ReportAttachMapper {

	/* INSERT - 신고 관련 파일 등록 */
	void insertReportAttach(List<AttachVO> attachList);
	
	/* SELECT - 모든 신고 파일 조회 */
	List<AttachVO> selectAllReportAttach();
	
	/* SELECT - 해당 신고 파일 조회 */	
	List<AttachVO> selectByIdReportAttach(String reportId);
	
	/* SELECT - 해당 UUID 조회*/
	List<AttachVO> getAttachesByUuids(List<String> delUuidList);	
	
	/* DELETE - 해당 신고 파일 삭제 */
	void deleteFindReport(String reportId);

	/* UPDATE - 해당 신고 파일 수정 */
	void updateFindReport(List<AttachVO> attachList);



}
