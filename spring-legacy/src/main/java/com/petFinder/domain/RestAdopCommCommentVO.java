package com.petFinder.domain;
/**
 * @title   : �Ծ�|�Ӻ�, Ŀ�´�Ƽ �Խ��� ��� ���� Ŭ����
 * @author  : SUMIN
 * @date    : 2021.09.24
 * @version : 1.0 
 **/

import java.util.Date;

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
public class RestAdopCommCommentVO {

	// ��� index (boardId_commentNum)
	private String commentId; 
	// �Խù� Id
	private String boardId; 
	// ��� num
	private int boardNum; 
	// �Խ��� Ÿ�� ID 
	private String boardTypeId;
	// ȸ�� Id
	private String memberId; 
	// ȸ�� �г���
	private String memberNickName; 
	// ��� ����
	private String commentContent; 
	
	// ��� �����
	private Date commentRegDate;
	// ��� ������
	private Date commentUpDate;
	
	// ����
	private int commentRef;
	private int commentLev;
	private int commentSeq;
}