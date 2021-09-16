package com.petFinder.domain;
/**
 * @title   : �ݷ����� ���� , �Ű� �ݷ����� ���� Ŭ����
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

	// ��Ϲ�ȣ
	private String petRegisterNumber; 
	// �ݷ����� �̸�
	private String petName;
	// �ݷ����� ����
	private String petKind; // D: ������, C:�����, O: ��Ÿ
	// �ݷ����� �� ǰ��
	private String petDetailKind;
	// �ݷ����� ����
	private String petBirthday;
	// �ݷ����� ����
	private String petGender; // M: ����, F:����
	// �ݷ����� ������
	private String petSize; // S: ����, M: ����, L: ����
	// �ݷ����� ����
	private String petColor; // H: ȭ��Ʈ, B: ��, C:����
	// �ݷ����� �� ����
	private String petCoatLength; // S: �ܸ�, L:���, C: ����
	
	// ȸ��ID (FK)
	private String memberId;
	
	/***************�ݷ� ���� �н� �Ű�****************/
	// �Ű� id
	private String reportId;
	// �ݷ����� ����
	private String petAttachUuid;
	// �ݷ����� �Ҿ���� ����
	private String petLocationM;
	// �ݷ����� �Ҿ���� ����
	private String petLocationD;
	// �ݷ����� Ư¡
	private String petCharacter;
	
	
	
}
