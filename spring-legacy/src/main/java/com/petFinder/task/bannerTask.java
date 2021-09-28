package com.petFinder.task;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.petFinder.domain.AttachVO;
import com.petFinder.service.BannerService;

@Component
public class bannerTask {
	
	@Autowired
	private BannerService bannerService;
	
	@Scheduled(cron = "0 * * * * *")  // 1분마다 (cron = "0 * * * * *")
	public void checkbanner() throws Exception {
		System.out.println("====================================");
		System.out.println("checkbanner() task run.......");
		System.out.println("====================================");
		
		// 배너 테이블 삭제
		bannerService.deleteBanner();
		
		
		// 가장 추천이 많은 게시물 3 조회
		List<AttachVO> AttachList = bannerService.selectBestOfReportBoard();
		
		if(AttachList != null) {
				
			// 배너 테이블 인서트
			bannerService.insertBanner(AttachList);
		
			
		}
		
		
		
	} // checkbanner
	
	
}
