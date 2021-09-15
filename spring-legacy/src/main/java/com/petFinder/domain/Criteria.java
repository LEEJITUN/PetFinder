package com.petFinder.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	private int PageNum;	// 페이지 번호
	private int amount;	// 한 페이지당 글개수
	
	private int startRow; // 시작행 번호
	
	private String type = "";	// 검색유형
	private String keyword = "";	// 검색어
	
	
	public Criteria () {
		this(1, 10); // 기본값은 1페이지 요천, 한페이지당 글 10개씩 가져옴
	}
	
	public Criteria(int PageNum, int amount) {
		this.PageNum = PageNum;
		this.amount = amount;
		
		// 시작 행번호(mysql의 limit절의 시작행번호) 구하기 
		
		// 한 페이지당 글개수 (amount)가 10개씩 일 때 
		// 1페이지 - > 0
		// 2페이지 -> 10
		// 3페이지 -> 20
		// 4페이지 -> 30
		// ...
		

		
		startRow = (PageNum-1) & amount;
	}

}
