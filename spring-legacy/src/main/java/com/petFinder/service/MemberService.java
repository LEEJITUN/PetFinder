package com.petFinder.service;
/**
 * @title   : ȸ������ Service
 * @author  : JIYUN, HYEPIN
 * @date    : 2021.09.24 
 * @version : 1.1 
 **/
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.MemberVO;
import com.petFinder.mapper.MemberMapper;
import com.petFinder.mapper.PetMapper;

@Service // @component �迭 ������̼�
@Transactional
public class MemberService {
	
	@Autowired 
	private MemberMapper memberMapper;
	
	@Autowired 
	private PetMapper petMapper;
	
	

	/* INSERT - ȸ������(ȸ��,��) */
	@Transactional
	public void registerMember(MemberVO memberVo){
		
		String petYN = memberVo.getMemberPetYN();
		// ó�� ���Խ� ��� �� ���� (��� = 0)
		memberVo.setMemberWaring(0);
		
		//======== INSERT - (Member,Pet) =========
		memberMapper.insertMember(memberVo);
		
		// ȸ���� ���� ������ ��� 
		if(petYN.equals("Y")) {
			petMapper.insertPet(memberVo.getPetVO());
		}
	}
	

	/* DELETE - �ش� ���̵��� ȸ������ ���� */
	public void deleteMemberById(String id) {
		memberMapper.deleteMemberById(id);
	}
	
	
	/* UPDATE - �ش� ���̵��� ȸ������ ������Ʈ */
	public void updateMemberById(MemberVO memberVo) {
		
		String petYN = memberVo.getMemberPetYN();
		
		//======== UPDATE - (Member,Pet) =========
		memberMapper.updateMemberById(memberVo);
		
		// ȸ���� ���� ������ ��� 
		if(petYN.equals("Y")) {
			petMapper.updatePet(memberVo.getPetVO());
		}		
	}
	
	
	/* SELECT - �ش� ���̵��� ȸ������ ��ȸ */
	public MemberVO selectMemberById(String memberId) {
		return memberMapper.selectMemberById(memberId);
	}

	
	/////////////////////���Ѱ�////////////////////////
	

	
	// SELECT - ��� ȸ������
	public List<MemberVO> getMembers(){
		return memberMapper.getMembers();
	}
	
	// SELECT - �ش� ���̵��� ȸ��ROW��
	public List<MemberVO> getCountById(String id){
		return memberMapper.getCountById(id);
	}
	
	



}
