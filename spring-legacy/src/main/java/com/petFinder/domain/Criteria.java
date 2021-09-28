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
	
	
	
	/*****�� �� �˻�******/
	// �ݷ����� ����
	private String petKind; // D: ������, C:�����, O: ��Ÿ
	// �ݷ����� �� ǰ��
	private String petDetailKind; // ǰ��
	// �ݷ����� ����
	private String petGender; // M: ����, F:����
	// �ݷ����� ������
	private String petSize; // S: ����, M: ����, L: ����
	// �ݷ����� ����
	private String petColor; // H: ȭ��Ʈ, B: ��, C:����
	// �ݷ����� �� ����
	private String petCoatLength; // S: �ܸ�, L:���, C: ����
	
	// �ݷ����� �н�, �߰� �õ�
	private String sido;
	// �ݷ����� �н�, �߰� �ñ���
	private String sigungu;
	// �ݷ����� �н�,  ������
	private String  bname;
	
	// �Ҿ���� ��¥ 
	private String inputDate;
	
	public Criteria () {
		this(1, 12); // �⺻���� 1������ ��õ, ���������� �� 10���� ������
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

	}

}
