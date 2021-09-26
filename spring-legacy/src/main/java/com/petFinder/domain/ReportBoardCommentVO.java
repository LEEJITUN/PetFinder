package com.petFinder.domain;
/**
 * @title   : 신고 게시판 댓글 정보 클래스
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

	// 댓글 index (reportId_commentNum)
	private String commentId; 
	// 게시물 Id
	private String reportId; 
	// 댓글 num
	private int commentNum; 
	// 게시물 type
	private String boardReportType;
	// 회원 Id
	private String memberId; 
	// 회원 닉네임
	private String memberNickName; 
	// 댓글 내용
	private String commentContent; 
	
	// 댓글 등록일
	private Date commentRegDate;
	// 댓글 수정일
	private Date commentUpDate;
	
	private String commentDateString;
	
	// 순서
	private int commentRef;
	private int commentLev;
	private int commentSeq;
	
	// 프로필 사진 
	private MemberProfileVO memberProfileVO;
}