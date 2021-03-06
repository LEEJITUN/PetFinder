package com.petFinder.domain;
/**
 * @title   : 반려동물 정보 , 신고 반려동물 정보 클래스
 * @author  : JIYUN
 * @date    : 2021.09.13 
 * @version : 1.0 
 **/

import java.util.Date;
import java.util.List;

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
public class PetVO {

	// 등록번호
	private String petRegisterNumber; 
	// 반려동물 이름
	private String petName;
	// 반려동물 종류
	private String petKind; // D: 강아지, C:고양이, O: 기타
	// 반려동물 상세 품종
	private String petDetailKind;
	// 반려동물 생일
	private String petBirthday;
	// 반려동물 성별
	private String petGender; // M: 남자, F:여자
	// 반려동물 사이즈
	private String petSize; // S: 소형, M: 중형, L: 대형
	// 반려동물 색상
	private String petColor; // H: 화이트, B: 블랙, C:브라운
	// 반려동물 털 길이
	private String petCoatLength; // S: 단모, L:장모, C: 곱슬
	
	// 회원ID (FK)
	private String memberId;
	
	/***************반려 동물 분실 신고****************/
	// 신고 id
	private String reportId;
	// 반려동물 사진
	private String petAttachUuid;
	// 반려동물 분실, 발견 시도
	private String sido;
	// 반려동물 분실, 발견 시군구
	private String sigungu;
	// 반려동물 분실,  법정동
	private String  bname;
	// 반려동물 분실, 기본 발견 주소
	private String address;
	
	// 반려동물 특징
	private String petCharacter;
	
	// 발견 날짜
	private String findPetDate;
	
	// 잃어버린 날짜 
	private String lostPetDate;
	
	// 분실,발견 이미지 파일
	private List<AttachVO> attachList;
	
	
}
