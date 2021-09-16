package com.petFinder.domain;
/**
 * @title   : ���� �Խ��� ���� Ŭ����
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/

import java.util.Date;
import java.util.List;

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
public class ComBoardVO {
	
	// �Խù� ID (��) boardTypeId + �ε����� => COMMU_A_1
	private String boardId;
	// �Խù� Num
	private int boardNum;
	// �Խ��� Ÿ�� ID 
	private String boardTypeId;
	// �Խù� �۾��� ���̵�
	private String memberId;
	// �Խù� �۾��� �г���
	private String memberNickName;
	// �Խù� ����
	private String boardTitle;
	// �Խù� ����
	private String boardContent;
	
	// �Խù� ��ȸ��
	private int boardReadCount;
	// �Խù� �Ű��
	private int boardReportCount;
	// �Խù� ��õ�� 
	private int boardgood;
	// �Խù� ����õ�� 
	private int boardNotgood;
	// �Խù� �������
	private Date boardRegDate;
	
	// ��� List
	List<ComBoardCommentVO> commentList;
	
}
