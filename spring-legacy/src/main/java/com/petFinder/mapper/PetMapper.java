package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;


@Mapper
public interface PetMapper {
	
	/* INSERT - Pet */
	void insertPet(PetVO petVo);
	
//	/* SELECT - �ش� ID�� ȸ����ȸ */	
//	@Select("SELECT * FROM MEMBER WHERE ID = #{id}")
//	MemberVO getMemberById(String id);
//	

}
