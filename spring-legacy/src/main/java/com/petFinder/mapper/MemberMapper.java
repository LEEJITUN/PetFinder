package com.petFinder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.petFinder.domain.MemberVO;

@Mapper
public interface MemberMapper {
	
	/* INSERT - 회원가입 */
	void insertMember(MemberVO memberVo);
	
	/* DELETE - 해당 ID의 회원정보 삭제 */
	void deleteMemberById(String memberId);
	
	/* UPDATE - 해당 ID의 회원정보 업데이트 */
	void updateMemberById(String memberId);
	
	/* SELECT - 해당 ID의 회원 조회 */
	MemberVO selectMemberById(String memberId);
	
	
	
	
	
	
	/////////////////////안한거////////////////////////
	
	
	
	/* SELECT - 회원조회 */
	@Select("SELECT * FROM MEMBER ORDER BY ID")
	List<MemberVO> getMembers();
	
	/* SELECT - 해당 ID의 회원정보 ROW수 */
	@Select("SELECT COUNT(*) FROM MEMBER WHERE ID = #{id}")
	List<MemberVO> getCountById(String id);

	
	
}
