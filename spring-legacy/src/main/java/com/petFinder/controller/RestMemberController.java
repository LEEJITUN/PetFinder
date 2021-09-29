package com.petFinder.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petFinder.domain.MemberVO;
import com.petFinder.service.MemberService;


@RestController
@RequestMapping("/api/*")
public class RestMemberController {
	
	@Autowired
	private MemberService memberService;

	// 아이디 중복 조회
	@GetMapping(value = "/selectMemberId/{memberId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<MemberVO> selectMemberId(@PathVariable("memberId") String memberId) {

		MemberVO memberVo = memberService.selectId(memberId);

		return new ResponseEntity<MemberVO>(memberVo, HttpStatus.OK);
	} 
   
	

}