package com.petFinder.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petFinder.domain.MemberProfileVO;
import com.petFinder.mapper.ProfilePicMapper;

@Service
@Transactional
public class ProfilePicService {

	@Autowired
	private ProfilePicMapper profilePicMapper;
	
	@Autowired
	private AttachFile attachFileService;

	
	/* 나의 정보 프로필 사진 조회*/
	public MemberProfileVO getProfilePic(String id) {
		return profilePicMapper.selectProfilePic(id);
	}
	
	// 프로필 사진 등록
	@Transactional
	public MemberProfileVO insertProfilePic(MultipartFile file, String memberId) throws IllegalStateException, IOException {
		
		// 실물 파일 등록
		MemberProfileVO memberProfileVO = attachFileService.uploadProfilePic(file,memberId);
		
		System.out.println("memberProfileVO : " + memberProfileVO.getFilename());
		
		// 삭제 할 프로필 사진
		MemberProfileVO  isProfile = profilePicMapper.selectProfilePic(memberId);
		
		if(isProfile == null){			
			// DB 등록
			profilePicMapper.insertProfilePic(memberProfileVO);
		}else {
			
			// 실물 파일 삭제 
			attachFileService.deleteProfileFile(isProfile);
			
			// 업데이트
			profilePicMapper.updateProfilePic(memberProfileVO);
		}
		
		return memberProfileVO;
		
	}

	// 프로필 사진 삭제
	public void deleteProfilePic(String memberId) {
		
		// 삭제 할 프로필 사진
		MemberProfileVO  isProfile = profilePicMapper.selectProfilePic(memberId);
		
		if(isProfile != null) {
			// 실물 파일 삭제 
			attachFileService.deleteProfileFile(isProfile);
			
			profilePicMapper.deleteProfilePic(memberId);
			
		}
	}
	

	public MemberProfileVO selectProfilePic(String memberId) {
		return profilePicMapper.selectProfilePic(memberId);
	}
	
	
	
}
