package com.petFinder.domain;
/**
 * @title   : 게시판 댓글 정보 클래스
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

	// 댓글 index
	private String commentIndex; 
	// 게시물 Id
	private String boardId; 
	// 회원 Id
	private String memberId; 
	// 회원 닉네임
	private String memberNickName; 
	// 댓글 내용
	private String commentContent; 
	
	// 댓글 등록일
	private String commentRegDate;
	// 댓글 수정일
	private String commentUpDate;
	
	// 순서
	private String commentRef;
	private String commentLev;
	private String commentSeq;
}