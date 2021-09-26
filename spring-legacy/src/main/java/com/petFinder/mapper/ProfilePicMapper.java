package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.MemberProfileVO;

@Mapper
public interface ProfilePicMapper {


	/*SELECT - �������� ������ ���� ��ȸ*/
	MemberProfileVO selectProfilePic(String memberId);

	/* INSERT - ������ ���� ��� */
	void insertProfilePic(MemberProfileVO memberProfileVO);
	
	/* UPDATE - ������ ���� ���� */
	void updateProfilePic(MemberProfileVO memberProfileVO);
	
	/* DELETE - ������ ���� ���� */
	void deleteProfilePic(String memberId);
}
