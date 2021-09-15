package com.petFinder.domain;
/**
 * @title   : �Խ��� ���� ���� Ŭ����
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

	// �Խ��� Ÿ�� ID (��) COMMU_A -> COMMU : �Խ��� ���� ī�װ�, A: ���� ī�װ� )
	private String boardTypeId; 
	// �Խ��� Ÿ�� �̸� 
	private String boardTypeName; 
	// ȸ�� Id
	private String memberId; 
	// �Խ��� ��� ��¥
	private String boardRegDate; 
	// �Խ��� ������Ʈ ��¥
	private String boardUpDate; 
}