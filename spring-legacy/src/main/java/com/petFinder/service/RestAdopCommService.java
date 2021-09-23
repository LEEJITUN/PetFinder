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
	
	/* SELECT - 해당 게시물에 추천 or 비추천을 했는지 조회 */
	public int selectCheck(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectCheck(restAdopCommVO);
		
	}
	
	/* DELETE - 추천 OR 비추천이 있을경우 삭제 (해당 유저) */
	public int deleteCommBoard(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.deleteCommBoard(restAdopCommVO);
		
	}
	

}
