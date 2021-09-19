package com.petFinder.domain;
/**
 * @title   : �Ű� �Խù� ���� Ŭ����
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
public class ReportBoardVO {
	
	// �Ű� �Խù� ID (��)REPORT_F_1 
	private String reportId;
	// �Ű� �Խù� �ѹ�
	private int    boardNum;
	// �Ű� �Խ��� Ÿ�� ID (��)REPORT_F_1 => F / �н� : L, �߰�:F
	private String boardReportType;
	// �Ű� �Խù� �۾��� ���̵�
	private String memberId;
	// �Ű� �Խù� �۾��� �г���
	private String memberNickName;
	// �Ű� �Խù� ����
	private String boardTitle;
	
	// �Ű� �Խù� ��ȸ��
	private int boardReadCount;
	// �Ű� �Խù� �Ű��
	private int boardReportCount;
	// �Ű� �Խù� ��õ�� 
	private int boardgood;
	// �Ű� �Խù� ����õ�� 
	private int boardNotgood;
	// �Ű� �Խù� �������
	private Date boardRegDate;
	
	// ��� List
	List<ComBoardCommentVO> commentList;
	
	// �߰� or �н� �Ű� ����
	private PetVO petVO;

	
}
