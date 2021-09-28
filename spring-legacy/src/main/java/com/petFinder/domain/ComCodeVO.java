package com.petFinder.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComCodeVO {

	private String codeM;
	private String codeD;
	private String str;

	
	/***********지역***********/
	// 선택 코드
	private String clickCode;
	
	private String sidoCode;
	// 시도
	private String sido;
	// 시군구
	private String sigungu;
	// 법동네임
	private String bname;
	
	
	public ComCodeVO(String codeM, String codeD,String clickCode) {
		this.codeM = codeM;
		this.codeD = codeD;
		this.clickCode = clickCode;
	}
}
