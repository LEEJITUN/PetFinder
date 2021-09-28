package com.petFinder.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComCodeVO {

	private String codeM;
	private String codeD;
	private String str;

	
	/***********����***********/
	// ���� �ڵ�
	private String clickCode;
	
	private String sidoCode;
	// �õ�
	private String sido;
	// �ñ���
	private String sigungu;
	// ��������
	private String bname;
	
	
	public ComCodeVO(String codeM, String codeD,String clickCode) {
		this.codeM = codeM;
		this.codeD = codeD;
		this.clickCode = clickCode;
	}
}
