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

	// ��� ����
	public void deleteBanner() {
		bannerMapper.deleteBanner();
	}

	// �Ű� �Խù� �� ���� ��õ ���� ���� �Խù� 3��
	public List<AttachVO> selectBestOfReportBoard() {
		return bannerMapper.selectBestOfReportBoard();
	}

	// ��� ����
	public void insertBanner(List<AttachVO> attachList) {
		bannerMapper.insertBanner(attachList);
		
	}
	
}
