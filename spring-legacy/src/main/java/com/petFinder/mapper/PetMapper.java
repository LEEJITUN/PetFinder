package com.petFinder.mapper;
/**
 * @title   : �ݷ����� Mapper
 * @author  : JIYUN, HYEPIN
 * @date    : 2021.09.24 
 * @version : 1.1 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;


@Mapper
public interface PetMapper {
	
	/* INSERT - Pet */
	void insertPet(PetVO petVo);

	/* UPDATE - Pet */
	void updatePet(PetVO petVO);
	
//	/* SELECT - �ش� ID�� ȸ����ȸ */	
//	@Select("SELECT * FROM MEMBER WHERE ID = #{id}")
//	MemberVO getMemberById(String id);
//	

}
