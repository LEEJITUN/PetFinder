package com.petFinder.mapper;
import java.util.List;

/**
 * @title   : 반려동물 발견 신고 Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardVO;


@Mapper
public interface PetFindMapper {

	/* INSERT - 유기동물 신고 데이터 등록*/
	void insertFindReort(PetVO petVO);

	/* INSERT - 유기동물 신고 게시물 등록 */
	void insertFindReortBoard(ReportBoardVO reportBoardVO);

	/* SELECT - 유기동물 신고 전체 조회*/
	List<ReportBoardVO> selectAllFindReport();

	/* SELECT - 게시물 번호 생성*/
	int selectBoardNumber(String boardReportType);


	


}
