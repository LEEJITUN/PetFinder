package com.petFinder.domain;
/**
 * @title   : 신고 게시물 정보 클래스
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
	
	// 신고 게시물 ID (예)REPORT_A_1 
	private String boardReportyId;
	// 신고 게시판 타입 ID (예)REPORT_A_1 => A / 분실 : A, 발견:B
	private String boardReportType;
	// 신고 게시물 글쓴이 아이디
	private String memberId;
	// 신고 게시물 글쓴이 닉네임
	private String memberNickName;
	// 신고 게시물 제목
	private String boardTitle;
	// 신고 게시물 내용
	private String boardContent;
	
	// 신고 게시물 조회수
	private int boardReadCount;
	// 신고 게시물 신고수
	private int boardReportCount;
	// 신고 게시물 추천수 
	private int boardgood;
	// 신고 게시물 비추천수 
	private int boardNotgood;
	// 신고 게시물 등록일자
	private Date boardRegDate;
	
	// 댓글 List
	List<ComBoardCommentVO> commentList;
	
}
