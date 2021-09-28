package com.petFinder.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.AttachVO;





@Mapper
public interface BannerMapper {

	
	/* SELECT - 배너 조회*/
	List<AttachVO> selectBanner();
	
	/* SELECT - 신고 게시물 중 추천수가 가장 많은 3개의 게시물 ID*/
	List<AttachVO> selectBestOfReportBoard();

	/* INSERT - 배너 저장*/
	void insertBanner(List<AttachVO> attachList);

	/* DELETE - 배너 데이터 all 삭제 */
	void deleteBanner();
	
}
