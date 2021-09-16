package com.petFinder.service;

import java.io.IOException;

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


	// 프로필 사진 등록
	@Transactional
	public void insertProfilePic(MultipartFile file, String memberId) throws IllegalStateException, IOException {
		
		// 실물 파일 등록
		MemberProfileVO memberProfileVO = attachFileService.uploadProfilePic(file,memberId);
		
		System.out.println("upload" + memberProfileVO.getUploadpath());
		// DB 등록
		profilePicMapper.insertProfilePic(memberProfileVO);
		
	}
	
	
	
	
}
