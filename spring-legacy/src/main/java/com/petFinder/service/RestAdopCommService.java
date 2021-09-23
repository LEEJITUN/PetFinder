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
	
	/* UPDATE - ��õ ����õ */
	public void updateadopCommBoardCheck(RestAdopCommVO restAdopCommVO) {
		
		int num = restAdopCommMapper.selectNextNumber(restAdopCommVO);
		restAdopCommVO.setNum(num);
		restAdopCommMapper.updateadopCommBoardCheck(restAdopCommVO);
	}

	/* SELECT - ��õ ����õ ���� ����*/
	public RestAdopCommVO selectTotalCount(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectTotalCount(restAdopCommVO);
		
	}
	
	/* SELECT - �ش� �Խù��� ��õ or ����õ�� �ߴ��� ��ȸ */
	public int selectCheck(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectCheck(restAdopCommVO);
		
	}
	
	/* DELETE - ��õ OR ����õ�� ������� ���� (�ش� ����) */
	public int deleteCommBoard(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.deleteCommBoard(restAdopCommVO);
		
	}
	

}
