package com.petFinder.domain;
/**
 * @title   : 회원 정보 클래스
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberVO {
	
	// 회원 Id
	private String memberId;
	// 회원 Passwd
	private String memberPassword;
	// 회원 닉네임
	private String memberNickName;
	// 회원 이름
	private String memberName;
	// 회원 성별
	private String memberGender;
	// 회원 생일
	private String memberBirthday;
	// 회원 핸드폰번호
	private String memberPhoneNumber;
	// 회원 email
	private String memberEmail;
	// 회원 경보 점수
	private int    memberWaring;
	// 회원 알림 설정
	private String memberNotice;
	// 회원의 반려동물 유무
	private String memberPetYN;
	
	// 한마리일 경우
	private PetVO petVO;

}
