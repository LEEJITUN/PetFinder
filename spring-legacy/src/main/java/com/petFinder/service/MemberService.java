package com.petFinder.service;
/**
 * @title   : 회원정보 Service
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

@Service // @component 계열 어노테이션
@Transactional
public class MemberService {
	
	@Autowired 
	private MemberMapper memberMapper;
	
	@Autowired 
	private PetMapper petMapper;
	
	

	/* INSERT - 회원가입(회원,펫) */
	@Transactional
	public void registerMember(MemberVO memberVo){
		
		String petYN = memberVo.getMemberPetYN();
		// 처음 가입시 경고 값 설정 (경고 = 0)
		memberVo.setMemberWaring(0);
		
		//======== INSERT - (Member,Pet) =========
		memberMapper.insertMember(memberVo);
		
		// 회원의 펫을 소유할 경우 
		if(petYN.equals("Y")) {
			petMapper.insertPet(memberVo.getPetVO());
		}
	}
	

	/* DELETE - 해당 아이디의 회원정보 삭제 */
	public void deleteMemberById(String id) {
		memberMapper.deleteMemberById(id);
	}
	
	
	/* UPDATE - 해당 아이디의 회원정보 업데이트 */
	public void updateMemberById(MemberVO memberVo) {
		
		String petYN = memberVo.getMemberPetYN();
		
		//======== UPDATE - (Member,Pet) =========
		memberMapper.updateMemberById(memberVo);
		
		// 회원의 펫을 소유할 경우 
		if(petYN.equals("Y")) {
			petMapper.updatePet(memberVo.getPetVO());
		}		
	}
	
	
	/* SELECT - 해당 아이디의 회원정보 조회 */
	public MemberVO selectMemberById(String memberId) {
		return memberMapper.selectMemberById(memberId);
	}

	
	/////////////////////안한거////////////////////////
	

	
	// SELECT - 모든 회원정보
	public List<MemberVO> getMembers(){
		return memberMapper.getMembers();
	}
	
	// SELECT - 해당 아이디의 회원ROW수
	public List<MemberVO> getCountById(String id){
		return memberMapper.getCountById(id);
	}
	
	



}
