package com.petFinder.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComCodeVO;

@Mapper
public interface RestComSelectBoxMapper {

	/* SELECT - 코드 조회 */
	List<ComCodeVO> selectCoedStrList(String codeM);

	List<ComCodeVO> sidoList();

	List<ComCodeVO> sigunguList(ComCodeVO vo);

	List<ComCodeVO> bnameList(ComCodeVO vo);

	
}
