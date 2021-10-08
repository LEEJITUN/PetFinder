package com.petFinder.controller;
/**
 * @title   : ȸ������ Controller
 * @author  : JIYUN, HYEPIN
 * @date    : 2021.09.24 
 * @version : 1.3
 **/
import java.io.IOException;
import java.util.List;

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

	@Autowired
	private AttachFile attachFileService;

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

	   // �α��� ������ ������
	   session.setAttribute("profileVO", memberVO.getMemberProfileVO());
	   
	   
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
   
   /* GET - ������ */
   @GetMapping("/memberInfo")
   public String memberInfo(String memberId,Model model) {
	   System.out.println("memberInfo ȣ���...");
	   
	   MemberVO memberVo = memberService.selectMemberById(memberId);
	   model.addAttribute("memberVo",memberVo);
	   model.addAttribute("memberProfileVO",memberVo.getMemberProfileVO());
	   
	   return "member/memberInfo";
   }  
   
   
   /* GET - ������ ���� */
   @GetMapping("/changeUserInfo") // /member/changeUserInfo
   public String changeUserInfo(String memberId, Model model) {
      System.out.println("changeUserInfo ȣ���...");
      
      // �ش� ���̵��� ������ �ҷ�����
      MemberVO memberVo = memberService.selectMemberById(memberId);
      
      model.addAttribute("memberVO", memberVo);
      model.addAttribute("petVO", memberVo.getPetVO());
      
      return "member/changeUserInfo";
   }
   
   /* POST - ������ ���� */
   @PostMapping("/changeUserInfo")
   public ResponseEntity<String> changeUserInfo(MemberVO memberVo, PetVO petVo) throws IllegalStateException, IOException {
	     
	   
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
	   
	   String str = Script.href("������ ����Ϸ�!","/member/memberInfo?memberId=" + memberVo.getMemberId());
	  
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
   
   /* GET - ������ ���� */
   @GetMapping("/changeProfile") // /member/changeUserInfo
   public String changeProfile(String memberId, Model model) {
      System.out.println("changeProfile ȣ���...");
      
      // �ش� ���̵��� ������ �ҷ�����
      MemberVO memberVo = memberService.selectMemberById(memberId);
      
      // ȸ������ ������ ���� ��ȸ 
      //MemberProfileVO memberProfileVO = profilePicService.getProfilePic(memberId);
      
      model.addAttribute("memberVO", memberVo);
      model.addAttribute("memberProfileVO", memberVo.getMemberProfileVO());
      
      return "member/changeProfile";
   }
   
   @PostMapping("changeProfile")
   public ResponseEntity<String> changeProfile(MultipartFile file,MemberVO memberVO,HttpSession session) throws IllegalStateException, IOException {
	   
	   // ���ǿ� ���� ������ ����
	   MemberProfileVO ProfileVO = new MemberProfileVO();
	   
	   /****************** UPDATE_������ *******************/
	   if(file.getSize() != 0) {		   
		   ProfileVO = profilePicService.insertProfilePic(file,memberVO.getMemberId());
	   }
	   
	   ProfileVO = profilePicService.selectProfilePic(memberVO.getMemberId());
			   
			   
	   /****************** UPDATE_���� *******************/
	   memberService.updateMemberByNic(memberVO);
	   
	   /****************** headers ���� *******************/
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type","text/html; charset=UTF-8");
	   
	   String str = Script.href("������ ����Ϸ�!","/member/memberInfo?memberId=" + memberVO.getMemberId());
	   
	   // ������ ���� �� ���� �ٽ� ��� 
	   session.setAttribute("profileVO", ProfileVO);

	   
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
   
   /* GET - ��й�ȣ ���� */
   @GetMapping("/changePasswd") // /member/changePasswd
   public String changePasswd() {
      System.out.println("changePasswd ȣ���...");      
      return "member/changePasswd";
   }
   
   /* POST - ��й�ȣ ���� */
   @PostMapping("/changePasswd") // /member/changePasswd
   public ResponseEntity<String> changePasswd(String memberId, String memberPassword, String memberPassword2) {
		
	   // �ش� ���̵��� ������ �ҷ�����
	   MemberVO memberVO = memberService.selectMemberById(memberId);
	   // �ش� memberId�� memberPassword���� ����ڰ� �Է��� passwd�� ��
	   boolean isPasswdSame = BCrypt.checkpw(memberPassword, memberVO.getMemberPassword());
	
	   // �Է��� �н����尡 ������ ��й�ȣ ������Ʈ
	   if(isPasswdSame == true) {
		   // ���ο� �н����� ��ȣȭ
		   String newPasswd = BCrypt.hashpw(memberPassword2, BCrypt.gensalt()); 
		
		   // ������Ʈ�ϱ����� ����
		   MemberVO memVO = new MemberVO();
		   memVO.setMemberId(memberId); 
		   memVO.setMemberPassword(newPasswd); //������Ʈ�� ��

		   memberService.updateMemberByPasswd(memVO);
		
		   /****************** headers ���� *******************/
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type", "text/html; charset=UTF-8");
		   
		   String str = Script.href("��й�ȣ ����Ϸ�!", "/member/login");

		   return new ResponseEntity<String>(str, headers, HttpStatus.OK);	
		   
	   }else {
		   
		   /****************** headers ���� *******************/
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type", "text/html; charset=UTF-8");  
		   
		   String str = Script.href("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/member/changePasswd");
		
		   return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	   }
   }   
   
   
   /* GET - ȸ������ ���� */
   @GetMapping("/remove") // /member/remove
   public String remove() {
      System.out.println("remove ȣ���...");
      return "member/remove";
   }
   
   /* POST - ȸ������ ���� */
   @PostMapping("/remove")
	public ResponseEntity<String> remove(String memberId, String memberPassword,
							HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	   
	   // �ش� ���̵��� ������ �ҷ�����
	   MemberVO memberVO = memberService.selectMemberById(memberId);
	   // �ش� memberId�� memberPassword���� ����ڰ� �Է��� passwd�� ��
	   boolean isPasswdSame = BCrypt.checkpw(memberPassword, memberVO.getMemberPassword());

		// �Է��� �н����尡 ������ Ż���ϱ�
		if(isPasswdSame == true) {
			memberService.deleteMemberById(memberId);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");  
			String str = Script.href("Ż��Ǿ����ϴ�", "/");

			// ���� ����
			session.invalidate();
			// �α��� ���������� ��Ű ������ �����ϱ�
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id")) {
						cookie.setMaxAge(0); // �������� ������ �� �ֵ��� 0�ʷ� ����
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				} // for
			}
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);	
			
		} else { // ��й�ȣ Ʋ�� ���
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");    
			String str = Script.href("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/member/remove");
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
	}
}
   
