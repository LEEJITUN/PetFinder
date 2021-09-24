package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.MemberProfileVO;

@Mapper
public interface ProfilePicMapper {

	/* INSERT - 프로필 파일 등록 */
	void insertProfilePic(MemberProfileVO memberProfileVO);

	/*SELECT - 나의정보 프로필 사진 조회*/
	MemberProfileVO selectProfilePic(String id);

}
