package com.petFinder.domain;
/**
 * @title   : 공통 게시판 정보 클래스
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
	
	// 게시물 ID (예) boardTypeId + 인덱스값 => COMMU_A_1
	private String boardId;
	// 게시물 Num
	private int boardNum;
	// 게시판 타입 ID 
	private String boardTypeId;
	// 게시물 글쓴이 아이디
	private String memberId;
	// 게시물 글쓴이 닉네임
	private String memberNickName;
	// 게시물 제목
	private String boardTitle;
	// 게시물 내용
	private String boardContent;
	
	// 게시물 조회수
	private int boardReadCount;
	// 게시물 신고수
	private int boardReportCount;
	// 게시물 추천수 
	private int boardgood;
	// 게시물 비추천수 
	private int boardNotgood;
	// 게시물 등록일자
	private Date boardRegDate;
	
	// 댓글 List
	List<ComBoardCommentVO> commentList;
	
}
