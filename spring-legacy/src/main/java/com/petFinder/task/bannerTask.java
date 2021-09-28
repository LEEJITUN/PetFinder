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
	
	@Scheduled(cron = "0 * * * * *")  // 1�и��� (cron = "0 * * * * *")
	public void checkbanner() throws Exception {
		System.out.println("====================================");
		System.out.println("checkbanner() task run.......");
		System.out.println("====================================");
		
		// ��� ���̺� ����
		bannerService.deleteBanner();
		
		
		// ���� ��õ�� ���� �Խù� 3 ��ȸ
		List<AttachVO> AttachList = bannerService.selectBestOfReportBoard();
		
		if(AttachList != null) {
				
			// ��� ���̺� �μ�Ʈ
			bannerService.insertBanner(AttachList);
		
			
		}
		
		
		
	} // checkbanner
	
	
}
