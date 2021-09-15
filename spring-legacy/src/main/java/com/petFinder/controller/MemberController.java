package com.petFinder.controller;
/**
 * @title   : 회원정보 Controller
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petFinder.domain.MemberVO;
import com.petFinder.domain.PetVO;
import com.petFinder.service.MemberService;
import com.petFinder.util.Script;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
   /* GET - 회원가입 */
   @GetMapping("/join") // /member/join
   public String join() {
      
      System.out.println("join 호출됨...");
      return "member/join";
   }
   
   /* POST - 회원가입 */
   @PostMapping("/join")
   public ResponseEntity<String> join(MemberVO memberVo, PetVO petVo) {
	   
	   
	   /****************** 데이터 설정 *******************/
	   // 비밀번호 암호화 하기
	   String passwd =  memberVo.getMemberPassword();
	   String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
	   memberVo.setMemberPassword(hasedPw); // 암호화된 비밀번호로 저장
	   
	   // 연월일 구분문자("-") 제거하기
	   String birthday = memberVo.getMemberBirthday();
	   birthday = birthday.replace("-", "");
	   memberVo.setMemberBirthday(birthday);
	   
	   memberVo.setPetVO(petVo);
	   
	   /****************** INSERT_회원가입 *******************/
	   memberService.registerMember(memberVo);
	   
	   
	   /****************** headers 설정 *******************/
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type","text/html; charset=UTF-8");
	   
	   String str = Script.href("회원가입 성공!","/member/login");
	  
	   
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
   /* GET - 로그인 */
   @GetMapping("/login")
   public String login() {
	   return "member/login";
   }
   
   
   /* POST - 로그인 */
   @PostMapping("/login")
   public ResponseEntity<String> login(String memberId, String memberPassword, String rememberMe, HttpSession session, HttpServletResponse response) {
	  
	   /****************** 로그인 성공/실패 설정 *******************/
	   // 로그인이 실패했을 경우 보낼 메세지 
	   String message = "";
	   
	   // 사용자가 입력한 id,pw가 회원정보에 있는지 조회 
	   boolean isMemebrIdPwSame =  checkIdPw(memberService.selectMemberById(memberId),memberPassword);
	   
	   // 로그인 실패 (회원정보에 존재하지 않을경우)
	   if(!isMemebrIdPwSame) {
		   message = "존재하지 않는 아이디거나 비밀번호가 일치하지 않습니다.";
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type","text/html; charset=UTF-8");
		   
		   String str = Script.back(message);
		   
		   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
	   }
	   
	   // 로그인 성공 
	   session.setAttribute("memberId", memberId);
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Location", "/"); // redirect 경로 "/"로 지정
	   
	   /****************** 로그인 상태 유지 *******************/
	   if(rememberMe != null) {
		   Cookie cookie = new Cookie("memberId", memberId);
		   // 쿠키가 주어질 경로
		   cookie.setPath("/");
		   // 쿠키의 제한 시간 (60초*30 -> 30분)
		   cookie.setMaxAge(60*30);
		   // 응답 객체에 쿠키 추가 -> 최종 응답 시 쿠키를 클라이언트에 전송
		   response.addCookie(cookie);
	   }
	   
	   return new ResponseEntity<String>(headers,HttpStatus.FOUND);
   }
   
   
	/* 해당 ID,PW가 맞는지 확인 메소드*/
	public boolean checkIdPw(MemberVO memberVo, String memberPassword) {
		
		if(memberVo != null) {			
			// 사용자가 입력한 비밀번호와 DB에 있는 비밀번호가 맞는지 비교 
			boolean isPasswdSame =  BCrypt.checkpw(memberPassword, memberVo.getMemberPassword());
			
			// id가 존재하고, 사용자가 입력한 비밀번호가 맞으면 true로 return
			if(memberVo != null && isPasswdSame){
				return true;
			}
		}
		
		return false;
	}
   
   
   /* GET - 로그아웃 */
   @GetMapping("/logout")
   public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	   
	   // 세션 비우기
	   session.invalidate();
	   
	   // 로그인 상태유지용 쿠키 있으면 삭제
	   Cookie[] cookies = request.getCookies();
	   
	   if(cookies != null) {
		   for(Cookie cookie : cookies) {
			   if(cookie.getName().equals("memberId")) {
				   // 삭제 유도 방식 - 다시 덮어씀으로 없앰
				   // 브라우저가 삭제 할 수 있도록 0으로 설정
				   cookie.setMaxAge(0);
				   cookie.setPath("/");
				   
				   response.addCookie(cookie);
			   }
		   }
	   }
	   // 홈 화면으로 리다이렉트 이동
	   return "redirect:/";
   }
   
}