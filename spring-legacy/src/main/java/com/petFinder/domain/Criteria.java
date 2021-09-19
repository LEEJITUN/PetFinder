package com.petFinder.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	private int pageNum;	// ������ ��ȣ
	private int amount;	// �� �������� �۰���
	
	private int startRow; // ������ ��ȣ
	
	private String type = "";	// �˻�����
	private String keyword = "";	// �˻���
	
	
	public Criteria () {
		this(1, 10); // �⺻���� 1������ ��õ, ���������� �� 10���� ������
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		
		// ���� ���ȣ(mysql�� limit���� �������ȣ) ���ϱ� 
		
		// �� �������� �۰��� (amount)�� 10���� �� �� 
		// 1������ - > 0
		// 2������ -> 10
		// 3������ -> 20
		// 4������ -> 30
		// ...
		

		
		startRow = (pageNum-1) & amount;
	}

}
