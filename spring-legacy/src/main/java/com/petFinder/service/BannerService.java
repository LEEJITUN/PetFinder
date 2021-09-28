package com.petFinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.AttachVO;
import com.petFinder.mapper.BannerMapper;

@Service
@Transactional
public class BannerService {

	@Autowired
	private BannerMapper bannerMapper;

	// 배너 삭제
	public void deleteBanner() {
		bannerMapper.deleteBanner();
	}

	// 신고 게시물 중 가장 추천 수가 많은 게시물 3개
	public List<AttachVO> selectBestOfReportBoard() {
		return bannerMapper.selectBestOfReportBoard();
	}

	// 배너 저장
	public void insertBanner(List<AttachVO> attachList) {
		bannerMapper.insertBanner(attachList);
		
	}
	
}
