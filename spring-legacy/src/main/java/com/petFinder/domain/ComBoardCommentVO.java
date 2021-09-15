package com.petFinder.domain;
/**
 * @title   : �Խ��� ��� ���� Ŭ����
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
public class ComBoardCommentVO {

	// ��� index
	private String commentIndex; 
	// �Խù� Id
	private String boardId; 
	// ȸ�� Id
	private String memberId; 
	// ȸ�� �г���
	private String memberNickName; 
	// ��� ����
	private String commentContent; 
	
	// ��� �����
	private String commentRegDate;
	// ��� ������
	private String commentUpDate;
	
	// ����
	private String commentRef;
	private String commentLev;
	private String commentSeq;
}