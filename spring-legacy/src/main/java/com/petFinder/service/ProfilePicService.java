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


	// ������ ���� ���
	@Transactional
	public void insertProfilePic(MultipartFile file, String memberId) throws IllegalStateException, IOException {
		
		// �ǹ� ���� ���
		MemberProfileVO memberProfileVO = attachFileService.uploadProfilePic(file,memberId);
		
		System.out.println("upload" + memberProfileVO.getUploadpath());
		// DB ���
		profilePicMapper.insertProfilePic(memberProfileVO);
		
	}
	
	
	
	
}
