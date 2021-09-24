package com.petFinder.mapper;
/**
 * @title   : 회원정보 Mapper
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
	
	
	/* SELECT - 해당 ID의 회원 조회 */
	MemberVO selectMemberById(String memberId);
	
	/* INSERT - 회원가입 */
	void insertMember(MemberVO memberVo);
	
	/* UPDATE - 해당 ID의 회원정보 업데이트 */
	void updateMemberById(MemberVO memberVo);

	/* UPDATE - 해당 ID의 별명 업데이트 */
	void updateMemberByNic(MemberVO memberVO);
	
	/* DELETE - 해당 ID의 회원정보 삭제 */
	void deleteMemberById(String memberId);

	
	
	
	
	/////////////////////안한거////////////////////////
	
	
	
	/* SELECT - 회원조회 */
	@Select("SELECT * FROM MEMBER ORDER BY ID")
	List<MemberVO> getMembers();
	
	/* SELECT - 해당 ID의 회원정보 ROW수 */
	@Select("SELECT COUNT(*) FROM MEMBER WHERE ID = #{id}")
	List<MemberVO> getCountById(String id);


	
	
}
