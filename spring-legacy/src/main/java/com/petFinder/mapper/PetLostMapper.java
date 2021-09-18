package com.petFinder.mapper;
import java.util.List;

/**
 * @title   : 반려동물 분실 신고 Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;

@Mapper
public interface PetLostMapper {

	/* INSERT - 분실 신고 등록 */
	void insertLostReport(PetVO petVO);
	
	/* UPDATE - 분실 신고 수정 */
	void updateLostReport(PetVO petVO);

	/* UPDATE - 분실 신고 삭제 */
	void deleteLostReport(String reportId);

	/* SELECT - 분실 신고 전체 조회 */
	List<ReportBoardVO> selectAllLostReport();
	
	/* SELECT - 해당 분실 신고 전체 조회 */
	ReportBoardVO selectLostReport(String reportId);

}
