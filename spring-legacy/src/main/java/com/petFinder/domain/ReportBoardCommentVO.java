package com.petFinder.domain;
/**
 * @title   : �Ű� �Խ��� ��� ���� Ŭ����
 * @author  : JIYUN
 * @date    : 2021.09.15 
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
public class ReportBoardCommentVO {

	// ��� index (reportId_commentNum)
	private String commentId; 
	// �Խù� Id
	private String reportId; 
	// ��� num
	private int commentNum; 
	// �Խù� type
	private String boardReportType;
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
	
	private String commentDateString;
	
	// ����
	private int commentRef;
	private int commentLev;
	private int commentSeq;
	
	// ������ ���� 
	private MemberProfileVO memberProfileVO;
}