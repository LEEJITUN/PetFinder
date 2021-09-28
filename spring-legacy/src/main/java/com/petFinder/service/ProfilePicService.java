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

	
	/* ���� ���� ������ ���� ��ȸ*/
	public MemberProfileVO getProfilePic(String id) {
		return profilePicMapper.selectProfilePic(id);
	}
	
	// ������ ���� ���
	@Transactional
	public MemberProfileVO insertProfilePic(MultipartFile file, String memberId) throws IllegalStateException, IOException {
		
		// �ǹ� ���� ���
		MemberProfileVO memberProfileVO = attachFileService.uploadProfilePic(file,memberId);
		
		System.out.println("memberProfileVO : " + memberProfileVO.getFilename());
		
		// ���� �� ������ ����
		MemberProfileVO  isProfile = profilePicMapper.selectProfilePic(memberId);
		
		if(isProfile == null){			
			// DB ���
			profilePicMapper.insertProfilePic(memberProfileVO);
		}else {
			
			// �ǹ� ���� ���� 
			attachFileService.deleteProfileFile(isProfile);
			
			// ������Ʈ
			profilePicMapper.updateProfilePic(memberProfileVO);
		}
		
		return memberProfileVO;
		
	}

	// ������ ���� ����
	public void deleteProfilePic(String memberId) {
		
		// ���� �� ������ ����
		MemberProfileVO  isProfile = profilePicMapper.selectProfilePic(memberId);
		
		if(isProfile != null) {
			// �ǹ� ���� ���� 
			attachFileService.deleteProfileFile(isProfile);
			
			profilePicMapper.deleteProfilePic(memberId);
			
		}
	}
	

	public MemberProfileVO selectProfilePic(String memberId) {
		return profilePicMapper.selectProfilePic(memberId);
	}
	
	
	
}
