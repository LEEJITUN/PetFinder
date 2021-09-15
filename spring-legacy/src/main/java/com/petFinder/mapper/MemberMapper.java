package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.petFinder.domain.MemberVO;

@Mapper
public interface MemberMapper {
	
	/* INSERT - ȸ������ */
	void insertMember(MemberVO memberVo);
	
	/* DELETE - �ش� ID�� ȸ������ ���� */
	void deleteMemberById(String memberId);
	
	/* UPDATE - �ش� ID�� ȸ������ ������Ʈ */
	void updateMemberById(String memberId);
	
	/* SELECT - �ش� ID�� ȸ�� ��ȸ */
	MemberVO selectMemberById(String memberId);
	
	
	
	
	
	
	/////////////////////���Ѱ�////////////////////////
	
	
	
	/* SELECT - ȸ����ȸ */
	@Select("SELECT * FROM MEMBER ORDER BY ID")
	List<MemberVO> getMembers();
	
	/* SELECT - �ش� ID�� ȸ������ ROW�� */
	@Select("SELECT COUNT(*) FROM MEMBER WHERE ID = #{id}")
	List<MemberVO> getCountById(String id);

	
	
}
