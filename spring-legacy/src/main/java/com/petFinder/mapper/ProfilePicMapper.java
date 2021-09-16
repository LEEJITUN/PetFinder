package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.MemberProfileVO;

@Mapper
public interface ProfilePicMapper {

	/* INSERT - 프로필 파일 등록 */
	void insertProfilePic(MemberProfileVO memberProfileVO);

}
