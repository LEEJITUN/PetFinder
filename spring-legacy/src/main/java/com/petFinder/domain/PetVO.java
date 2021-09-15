package com.petFinder.domain;
/**
 * @title   : 반려동물 정보 , 신고 반려동물 정보 클래스
 * @author  : JIYUN
 * @date    : 2021.09.13 
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
public class PetVO {

	// 등록번호
	private String petRegisterNumber; 
	// 반려동물 이름
	private String petName;
	// 반려동물 종류
	private String petKind;
	// 반려동물 상세 품종
	private String petDetailKind;
	// 반려동물 생일
	private String petBirthday;
	// 반려동물 성별
	private String petGender;
	// 반려동물 사이즈
	private String petSize;
	// 반려동물 색상
	private String petColor;
	// 반려동물 털 길이
	private String petCoatLength;
	
	// 회원ID (FK)
	private String memberId;
	
	/***************반려 동물 분실 신고****************/
	// 신고 id
	private String reportId;
	// 반려동물 사진
	private String petAttachUuid;
	// 반려동물 잃어버린 지역
	private String petLocationM;
	// 반려동물 잃어버린 동네
	private String petLocationD;
	// 반려동물 특징
	private String petCharacter;
	
	
	
}
