package com.petFinder.service;

import java.util.ArrayList;
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


	// 지역 조회 
	public List<ComCodeVO> sidoCoedList(ComCodeVO vo) {
		
		List<ComCodeVO> list = new ArrayList<ComCodeVO>();
		
		// 클릭 값
		if(vo.getClickCode().equals("SIDO")) {			
			list = restComSelectBoxMapper.sidoList();
		}else if(vo.getClickCode().equals("SIGUN")) {
			list = restComSelectBoxMapper.sigunguList(vo);			
		}else {
			list = restComSelectBoxMapper.bnameList(vo);
		}
		
		return list;
	}

}
