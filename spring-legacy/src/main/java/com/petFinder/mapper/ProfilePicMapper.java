package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.MemberProfileVO;

@Mapper
public interface ProfilePicMapper {

	/* INSERT - ������ ���� ��� */
	void insertProfilePic(MemberProfileVO memberProfileVO);

}
