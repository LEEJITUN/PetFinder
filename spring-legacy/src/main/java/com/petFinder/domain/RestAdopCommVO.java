package com.petFinder.domain;
/**
 * @title   : 게시판 추천 비추천 신고
 * @author  : SUMIN
 * @date    : 2021.09.23 
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
public class RestAdopCommVO {
	
	// 조회수 횟수 num
	private int num;
	private  String boardId;
	private String memberId;
	private String goodOrNot;
	private int good;
	private int notGood;
	private String waring;
	
}
