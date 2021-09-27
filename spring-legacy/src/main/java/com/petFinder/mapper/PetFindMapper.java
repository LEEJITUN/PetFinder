package com.petFinder.mapper;
import java.util.List;

/**
 * @title   : 반려동물 발견 신고 Mapper
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

	/* INSERT - 유기동물 신고 데이터 등록 */
	void insertFindReport(PetVO petVO);

	/* INSERT - 유기동물 신고 게시물 등록 */
	void insertFindReportBoard(ReportBoardVO reportBoardVO);

	/* SELECT - 유기동물 신고 전체 조회 */
	List<ReportBoardVO> selectAllFindReport(Criteria cri);

	/* SELECT - 게시물 번호 생성 조회 */
	int selectBoardNumber(String boardReportType);

	/* SELECT - 해당 게시물 조회 */
	ReportBoardVO selectFindReport(String reportId);

	/* DELETE - 해당 게시물 삭제 */
	void deleteFindReportBoard(String reportId);
	
	/* DELETE - 해당 신고 삭제 */
	void deleteFindReport(String reportId);

	/* UPDATE - 해당 신고 게시물 수정 */
	void updateFindReportBoard(ReportBoardVO reportBoardVO);

	/* UPDATE - 해당 신고 데이터 수정 */
	void updateFindReport(PetVO petVO);
	
	/* UPDATE - 해당 신고 게시물 조회수 증가 */
	void updateReportReadCunt(String reportId);

	/* SELECT - 해당 글 카운트*/
	int selectTotalCountBySearch(Criteria cri);


}
