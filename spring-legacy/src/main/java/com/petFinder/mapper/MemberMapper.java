package com.petFinder.mapper;
/**
 * @title   : ȸ������ Mapper
 * @author  : JIYUN, HYEPIN
 * @date    : 2021.09.24 
 * @version : 1.2
 **/
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.petFinder.domain.MemberVO;

@Mapper
public interface MemberMapper {
	
	
	/* SELECT - �ش� ID�� ȸ�� ��ȸ */
	MemberVO selectMemberById(String memberId);
	
	/* INSERT - ȸ������ */
	void insertMember(MemberVO memberVo);
	
	/* UPDATE - �ش� ID�� ȸ������ ������Ʈ */
	void updateMemberById(MemberVO memberVo);

	/* UPDATE - �ش� ID�� ���� ������Ʈ */
	void updateMemberByNic(MemberVO memberVO);
	
	/* DELETE - �ش� ID�� ȸ������ ���� */
	void deleteMemberById(String memberId);

	
	
	
	
	/////////////////////���Ѱ�////////////////////////
	
	
	
	/* SELECT - ȸ����ȸ */
	@Select("SELECT * FROM MEMBER ORDER BY ID")
	List<MemberVO> getMembers();
	
	/* SELECT - �ش� ID�� ȸ������ ROW�� */
	@Select("SELECT COUNT(*) FROM MEMBER WHERE ID = #{id}")
	List<MemberVO> getCountById(String id);


	
	
}
