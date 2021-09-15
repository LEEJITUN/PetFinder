package com.petFinder.controller;
/**
 * @title   : ȸ������ Controller
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
	
   /* GET - ȸ������ */
   @GetMapping("/join") // /member/join
   public String join() {
      
      System.out.println("join ȣ���...");
      return "member/join";
   }
   
   /* POST - ȸ������ */
   @PostMapping("/join")
   public ResponseEntity<String> join(MemberVO memberVo, PetVO petVo) {
	   
	   
	   /****************** ������ ���� *******************/
	   // ��й�ȣ ��ȣȭ �ϱ�
	   String passwd =  memberVo.getMemberPassword();
	   String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
	   memberVo.setMemberPassword(hasedPw); // ��ȣȭ�� ��й�ȣ�� ����
	   
	   // ������ ���й���("-") �����ϱ�
	   String birthday = memberVo.getMemberBirthday();
	   birthday = birthday.replace("-", "");
	   memberVo.setMemberBirthday(birthday);
	   
	   memberVo.setPetVO(petVo);
	   
	   /****************** INSERT_ȸ������ *******************/
	   memberService.registerMember(memberVo);
	   
	   
	   /****************** headers ���� *******************/
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type","text/html; charset=UTF-8");
	   
	   String str = Script.href("ȸ������ ����!","/member/login");
	  
	   
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
   /* GET - �α��� */
   @GetMapping("/login")
   public String login() {
	   return "member/login";
   }
   
   
   /* POST - �α��� */
   @PostMapping("/login")
   public ResponseEntity<String> login(String memberId, String memberPassword, String rememberMe, HttpSession session, HttpServletResponse response) {
	  
	   /****************** �α��� ����/���� ���� *******************/
	   // �α����� �������� ��� ���� �޼��� 
	   String message = "";
	   
	   // ����ڰ� �Է��� id,pw�� ȸ�������� �ִ��� ��ȸ 
	   boolean isMemebrIdPwSame =  checkIdPw(memberService.selectMemberById(memberId),memberPassword);
	   
	   // �α��� ���� (ȸ�������� �������� �������)
	   if(!isMemebrIdPwSame) {
		   message = "�������� �ʴ� ���̵�ų� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type","text/html; charset=UTF-8");
		   
		   String str = Script.back(message);
		   
		   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
	   }
	   
	   // �α��� ���� 
	   session.setAttribute("memberId", memberId);
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Location", "/"); // redirect ��� "/"�� ����
	   
	   /****************** �α��� ���� ���� *******************/
	   if(rememberMe != null) {
		   Cookie cookie = new Cookie("memberId", memberId);
		   // ��Ű�� �־��� ���
		   cookie.setPath("/");
		   // ��Ű�� ���� �ð� (60��*30 -> 30��)
		   cookie.setMaxAge(60*30);
		   // ���� ��ü�� ��Ű �߰� -> ���� ���� �� ��Ű�� Ŭ���̾�Ʈ�� ����
		   response.addCookie(cookie);
	   }
	   
	   return new ResponseEntity<String>(headers,HttpStatus.FOUND);
   }
   
   
	/* �ش� ID,PW�� �´��� Ȯ�� �޼ҵ�*/
	public boolean checkIdPw(MemberVO memberVo, String memberPassword) {
		
		if(memberVo != null) {			
			// ����ڰ� �Է��� ��й�ȣ�� DB�� �ִ� ��й�ȣ�� �´��� �� 
			boolean isPasswdSame =  BCrypt.checkpw(memberPassword, memberVo.getMemberPassword());
			
			// id�� �����ϰ�, ����ڰ� �Է��� ��й�ȣ�� ������ true�� return
			if(memberVo != null && isPasswdSame){
				return true;
			}
		}
		
		return false;
	}
   
   
   /* GET - �α׾ƿ� */
   @GetMapping("/logout")
   public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	   
	   // ���� ����
	   session.invalidate();
	   
	   // �α��� ���������� ��Ű ������ ����
	   Cookie[] cookies = request.getCookies();
	   
	   if(cookies != null) {
		   for(Cookie cookie : cookies) {
			   if(cookie.getName().equals("memberId")) {
				   // ���� ���� ��� - �ٽ� ������� ����
				   // �������� ���� �� �� �ֵ��� 0���� ����
				   cookie.setMaxAge(0);
				   cookie.setPath("/");
				   
				   response.addCookie(cookie);
			   }
		   }
	   }
	   // Ȩ ȭ������ �����̷�Ʈ �̵�
	   return "redirect:/";
   }
   
}