package com.petFinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.RestAdopCommVO;
import com.petFinder.mapper.RestAdopCommMapper;

@Service
@Transactional
public class RestAdopCommService {

	@Autowired
	private RestAdopCommMapper restAdopCommMapper;
	
	/* UPDATE - 추천 비추천 */
	public void updateadopCommBoardCheck(RestAdopCommVO restAdopCommVO) {
		
		int num = restAdopCommMapper.selectNextNumber(restAdopCommVO);
		restAdopCommVO.setNum(num);
		restAdopCommMapper.updateadopCommBoardCheck(restAdopCommVO);
	}

	/* SELECT - 추천 비추천 각각 갯수*/
	public RestAdopCommVO selectTotalCount(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectTotalCount(restAdopCommVO);
		
	}

}
