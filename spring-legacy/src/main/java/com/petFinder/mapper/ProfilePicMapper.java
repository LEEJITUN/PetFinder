package com.petFinder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.MemberProfileVO;

@Mapper
public interface ProfilePicMapper {


	/*SELECT - 나의정보 프로필 사진 조회*/
	MemberProfileVO selectProfilePic(String memberId);

	/* INSERT - 프로필 파일 등록 */
	void insertProfilePic(MemberProfileVO memberProfileVO);
	
	/* UPDATE - 프로필 파일 수정 */
	void updateProfilePic(MemberProfileVO memberProfileVO);
	
	/* DELETE - 프로필 파일 삭제 */
	void deleteProfilePic(String memberId);
}
