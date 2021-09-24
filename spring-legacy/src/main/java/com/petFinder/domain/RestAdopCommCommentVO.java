package com.petFinder.domain;
/**
 * @title   : 입양|임보, 커뮤니티 게시판 댓글 정보 클래스
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

	// 댓글 index (boardId_commentNum)
	private String commentId; 
	// 게시물 Id
	private String boardId; 
	// 댓글 num
	private int boardNum; 
	// 게시판 타입 ID 
	private String boardTypeId;
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
	
	// 순서
	private int commentRef;
	private int commentLev;
	private int commentSeq;
}