package com.petFinder.domain;
/**
 * @title   : 게시판 관리 정보 클래스
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
public class BoardConfigVO {

	// 게시판 타입 ID (예) COMMU_A -> COMMU : 게시판 상위 카테고리, A: 하위 카테고리 )
	private String boardTypeId; 
	// 게시판 타입 이름 
	private String boardTypeName; 
	// 회원 Id
	private String memberId; 
	// 게시판 등록 날짜
	private String boardRegDate; 
	// 게시판 업데이트 날짜
	private String boardUpDate; 
}