package com.petFinder.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	private int pageNum;	// 페이지 번호
	private int amount;	// 한 페이지당 글개수
	
	private int startRow; // 시작행 번호
	
	private String type = "";	// 검색유형
	private String keyword = "";	// 검색어
	
	
	
	/*****펫 상세 검색******/
	// 반려동물 종류
	private String petKind; // D: 강아지, C:고양이, O: 기타
	// 반려동물 상세 품종
	private String petDetailKind; // 품종
	// 반려동물 성별
	private String petGender; // M: 남자, F:여자
	// 반려동물 사이즈
	private String petSize; // S: 소형, M: 중형, L: 대형
	// 반려동물 색상
	private String petColor; // H: 화이트, B: 블랙, C:브라운
	// 반려동물 털 길이
	private String petCoatLength; // S: 단모, L:장모, C: 곱슬
	
	// 반려동물 분실, 발견 시도
	private String sido;
	// 반려동물 분실, 발견 시군구
	private String sigungu;
	// 반려동물 분실,  법정동
	private String  bname;
	
	// 잃어버린 날짜 
	private String inputDate;
	
	public Criteria () {
		this(1, 12); // 기본값은 1페이지 요천, 한페이지당 글 10개씩 가져옴
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		
		// 시작 행번호(mysql의 limit절의 시작행번호) 구하기 
		
		// 한 페이지당 글개수 (amount)가 10개씩 일 때 
		// 1페이지 - > 0
		// 2페이지 -> 10
		// 3페이지 -> 20
		// 4페이지 -> 30
		// ...

	}

}
