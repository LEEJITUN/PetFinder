package com.petFinder.controller;
import java.io.IOException;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.petFinder.domain.MemberProfileVO;
import com.petFinder.domain.MemberVO;
import com.petFinder.domain.PetVO;
import com.petFinder.service.AttachFile;
import com.petFinder.service.MemberService;
import com.petFinder.service.ProfilePicService;
import com.petFinder.util.Script;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ProfilePicService profilePicService;


   /* GET - ȸ������ */
   @GetMapping("/join") // /member/join
   public String join() {
      
      System.out.println("join ȣ���...");
      return "member/join";
   }
   
   /* POST - ȸ������ */
   @PostMapping("/join")
   public ResponseEntity<String> join(MemberVO memberVo, PetVO petVo, MultipartFile file) throws IllegalStateException, IOException {
	   
	   ////////////������ Ȯ��/////////////

	   System.out.println("file" + file);
	   
	   
	   /****************** ������ ���� *******************/
	   // ��й�ȣ ��ȣȭ �ϱ�
	   String passwd =  memberVo.getMemberPassword();
	   String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
	   memberVo.setMemberPassword(hasedPw); // ��ȣȭ�� ��й�ȣ�� ����
	   
	   // ������ ���й���("-") �����ϱ�
	   String birthday = memberVo.getMemberBirthday();
	   birthday = birthday.replace("-", "");
	   memberVo.setMemberBirthday(birthday);
	   
	   memberVo.setMemberWaring(0);
	   
	   memberVo.setPetVO(petVo);
	   
	   /****************** INSERT_ȸ������ (member,pet) *******************/
	   memberService.registerMember(memberVo);
	   
	   // �ǹ� ���� ó�� 
	   
	   /****************** INSERT_������ *******************/
	   if(file.getSize() != 0) {		   
		   profilePicService.insertProfilePic(file,memberVo.getMemberId());
	   }
	   
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
	   
	   MemberVO memberVO = memberService.selectMemberById(memberId);
	   // ����ڰ� �Է��� id,pw�� ȸ�������� �ִ��� ��ȸ 
	   boolean isMemebrIdPwSame =  checkIdPw(memberVO,memberPassword);
	   
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
	   session.setAttribute("memberNic",memberVO.getMemberNickName());
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Location", "/"); // redirect ��� "/"�� ����
	   
	   /****************** �α��� ���� ���� *******************/
	   if(rememberMe != null) {
		   Cookie cookie = new Cookie("memberId", memberId);
		   // ��Ű�� �־��� ���
		   cookie.setPath("/");
		   // ��Ű�� ���� �ð� (60��*20 -> 20��)
		   cookie.setMaxAge(60*20);
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
   
   
   /* GET - ������ ���� */
   @GetMapping("/changeUserInfo") // /member/memberInfo
   public String changeUserInfo(String memberId, Model model) {
      System.out.println("changeUserInfo ȣ���...");
      
      // �ش� ���̵��� ������ �ҷ�����
      MemberVO memberVo = memberService.selectMemberById(memberId);
      
      model.addAttribute("memberVO", memberVo);
      
      return "member/changeUserInfo";
   }
   
   /* POST - ������ ���� */
   @PostMapping("/changeUserInfo")
   public ResponseEntity<String> memberInfo(MemberVO memberVo, PetVO petVo) throws IllegalStateException, IOException {
	     
	   
	   /****************** ������ ���� *******************/
	   // ��й�ȣ ��ȣȭ �ϱ�
	   String passwd =  memberVo.getMemberPassword();
	   String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
	   memberVo.setMemberPassword(hasedPw); // ��ȣȭ�� ��й�ȣ�� ����
	   
	   // ������ ���й���("-") �����ϱ�
	   String birthday = memberVo.getMemberBirthday();
	   birthday = birthday.replace("-", "");
	   memberVo.setMemberBirthday(birthday);
	   
	   memberVo.setMemberWaring(0);
	   
	   memberVo.setPetVO(petVo);
	   
	   /****************** UPDATE_���������� (member,pet) *******************/
	   memberService.updateMemberById(memberVo);

	   
	   /****************** headers ���� *******************/
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type","text/html; charset=UTF-8");
	   
	   String str = Script.href("������ ����Ϸ�!","/");
	  
	   
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
}
   
