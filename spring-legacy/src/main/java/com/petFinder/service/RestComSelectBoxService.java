package com.petFinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.ComCodeVO;
import com.petFinder.mapper.RestComSelectBoxMapper;

@Service
@Transactional
public class RestComSelectBoxService {

	@Autowired
	private RestComSelectBoxMapper restComSelectBoxMapper;
	
	
	// 코드 조회
	public List<ComCodeVO> selectCoedStrList(String codeM) {
		return restComSelectBoxMapper.selectCoedStrList(codeM);
	}

}
