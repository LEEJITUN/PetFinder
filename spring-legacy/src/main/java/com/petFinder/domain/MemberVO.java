package com.petFinder.domain;
/**
 * @title   : ȸ�� ���� Ŭ����
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
public class MemberVO {
	
	// ȸ�� Id
	private String memberId;
	// ȸ�� Passwd
	private String memberPassword;
	// ȸ�� �г���
	private String memberNickName;
	// ȸ�� �̸�
	private String memberName;
	// ȸ�� ����
	private String memberGender;
	// ȸ�� ����
	private String memberBirthday;
	// ȸ�� �ڵ�����ȣ
	private String memberPhoneNumber;
	// ȸ�� email
	private String memberEmail;
	// ȸ�� �溸 ����
	private int    memberWaring;
	// ȸ�� �˸� ����
	private String memberNotice;
	// ȸ���� �ݷ����� ����
	private String memberPetYN;
	
	// �Ѹ����� ���
	private PetVO petVO;

}
