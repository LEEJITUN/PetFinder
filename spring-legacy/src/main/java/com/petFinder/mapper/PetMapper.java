package com.petFinder.mapper;
/**
 * @title   : 반려동물 Mapper
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
	
//	/* SELECT - 해당 ID의 회원조회 */	
//	@Select("SELECT * FROM MEMBER WHERE ID = #{id}")
//	MemberVO getMemberById(String id);
//	

}
