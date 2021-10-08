package com.petFinder.controller;
/**
 * @title   : 회원정보 Controller
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

   /* GET - 회원가입 */
   @GetMapping("/join") // /member/join
   public String join() {
      
      System.out.println("join 호출됨...");
      return "member/join";
   }
   
   /* POST - 회원가입 */
   @PostMapping("/join")
   public ResponseEntity<String> join(MemberVO memberVo, PetVO petVo, MultipartFile file) throws IllegalStateException, IOException {
	   
	   ////////////데이터 확인/////////////

	   System.out.println("file" + file);
	   
	   
	   /****************** 데이터 설정 *******************/
	   // 비밀번호 암호화 하기
	   String passwd =  memberVo.getMemberPassword();
	   String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
	   memberVo.setMemberPassword(hasedPw); // 암호화된 비밀번호로 저장
	   
	   // 연월일 구분문자("-") 제거하기
	   String birthday = memberVo.getMemberBirthday();
	   birthday = birthday.replace("-", "");
	   memberVo.setMemberBirthday(birthday);
	   
	   memberVo.setMemberWaring(0);
	   
	   memberVo.setPetVO(petVo);
	   
	   /****************** INSERT_회원가입 (member,pet) *******************/
	   memberService.registerMember(memberVo);
	   
	   // 실물 파일 처리 
	   
	   /****************** INSERT_프로필 *******************/
	   if(file.getSize() != 0) {		   
		   profilePicService.insertProfilePic(file,memberVo.getMemberId());
	   }
	   
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
	   
	   MemberVO memberVO = memberService.selectMemberById(memberId);
	   // 사용자가 입력한 id,pw가 회원정보에 있는지 조회 
	   boolean isMemebrIdPwSame =  checkIdPw(memberVO,memberPassword);
	   
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
	   session.setAttribute("memberNic",memberVO.getMemberNickName());

	   // 로그인 성공시 프로필
	   session.setAttribute("profileVO", memberVO.getMemberProfileVO());
	   
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Location", "/"); // redirect 경로 "/"로 지정
	   
	   /****************** 로그인 상태 유지 *******************/
	   if(rememberMe != null) {
		   Cookie cookie = new Cookie("memberId", memberId);
		   // 쿠키가 주어질 경로
		   cookie.setPath("/");
		   // 쿠키의 제한 시간 (60초*20 -> 20분)
		   cookie.setMaxAge(60*20);
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
   
   /* GET - 내정보 */
   @GetMapping("/memberInfo")
   public String memberInfo(String memberId,Model model) {
	   System.out.println("memberInfo 호출됨...");
	   
	   MemberVO memberVo = memberService.selectMemberById(memberId);
	   model.addAttribute("memberVo",memberVo);
	   model.addAttribute("memberProfileVO",memberVo.getMemberProfileVO());
	   
	   return "member/memberInfo";
   }  
   
   
   /* GET - 내정보 수정 */
   @GetMapping("/changeUserInfo") // /member/changeUserInfo
   public String changeUserInfo(String memberId, Model model) {
      System.out.println("changeUserInfo 호출됨...");
      
      // 해당 아이디의 정보값 불러오기
      MemberVO memberVo = memberService.selectMemberById(memberId);
      
      model.addAttribute("memberVO", memberVo);
      model.addAttribute("petVO", memberVo.getPetVO());
      
      return "member/changeUserInfo";
   }
   
   /* POST - 내정보 수정 */
   @PostMapping("/changeUserInfo")
   public ResponseEntity<String> changeUserInfo(MemberVO memberVo, PetVO petVo) throws IllegalStateException, IOException {
	     
	   
	   /****************** 데이터 설정 *******************/
	   // 비밀번호 암호화 하기
	   String passwd =  memberVo.getMemberPassword();
	   String hasedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
	   memberVo.setMemberPassword(hasedPw); // 암호화된 비밀번호로 저장
	   
	   // 연월일 구분문자("-") 제거하기
	   String birthday = memberVo.getMemberBirthday();
	   birthday = birthday.replace("-", "");
	   memberVo.setMemberBirthday(birthday);
	   
	   memberVo.setMemberWaring(0);
	   
	   memberVo.setPetVO(petVo);
	   
	   /****************** UPDATE_내정보수정 (member,pet) *******************/
	   memberService.updateMemberById(memberVo);

	   /****************** headers 설정 *******************/
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type","text/html; charset=UTF-8");
	   
	   String str = Script.href("내정보 변경완료!","/member/memberInfo?memberId=" + memberVo.getMemberId());
	  
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
   
   /* GET - 프로필 수정 */
   @GetMapping("/changeProfile") // /member/changeUserInfo
   public String changeProfile(String memberId, Model model) {
      System.out.println("changeProfile 호출됨...");
      
      // 해당 아이디의 정보값 불러오기
      MemberVO memberVo = memberService.selectMemberById(memberId);
      
      // 회원정보 프로필 사진 조회 
      //MemberProfileVO memberProfileVO = profilePicService.getProfilePic(memberId);
      
      model.addAttribute("memberVO", memberVo);
      model.addAttribute("memberProfileVO", memberVo.getMemberProfileVO());
      
      return "member/changeProfile";
   }
   
   @PostMapping("changeProfile")
   public ResponseEntity<String> changeProfile(MultipartFile file,MemberVO memberVO,HttpSession session) throws IllegalStateException, IOException {
	   
	   // 세션에 담을 프로필 사진
	   MemberProfileVO ProfileVO = new MemberProfileVO();
	   
	   /****************** UPDATE_프로필 *******************/
	   if(file.getSize() != 0) {		   
		   ProfileVO = profilePicService.insertProfilePic(file,memberVO.getMemberId());
	   }
	   
	   ProfileVO = profilePicService.selectProfilePic(memberVO.getMemberId());
			   
			   
	   /****************** UPDATE_별명 *******************/
	   memberService.updateMemberByNic(memberVO);
	   
	   /****************** headers 설정 *******************/
	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type","text/html; charset=UTF-8");
	   
	   String str = Script.href("프로필 변경완료!","/member/memberInfo?memberId=" + memberVO.getMemberId());
	   
	   // 프로필 변경 시 세션 다시 담기 
	   session.setAttribute("profileVO", ProfileVO);

	   
	   return new ResponseEntity<String>(str,headers,HttpStatus.OK);
   }
   
   
   /* GET - 비밀번호 변경 */
   @GetMapping("/changePasswd") // /member/changePasswd
   public String changePasswd() {
      System.out.println("changePasswd 호출됨...");      
      return "member/changePasswd";
   }
   
   /* POST - 비밀번호 변경 */
   @PostMapping("/changePasswd") // /member/changePasswd
   public ResponseEntity<String> changePasswd(String memberId, String memberPassword, String memberPassword2) {
		
	   // 해당 아이디의 정보값 불러오기
	   MemberVO memberVO = memberService.selectMemberById(memberId);
	   // 해당 memberId의 memberPassword값과 사용자가 입력한 passwd값 비교
	   boolean isPasswdSame = BCrypt.checkpw(memberPassword, memberVO.getMemberPassword());
	
	   // 입력한 패스워드가 같으면 비밀번호 업데이트
	   if(isPasswdSame == true) {
		   // 새로운 패스워드 암호화
		   String newPasswd = BCrypt.hashpw(memberPassword2, BCrypt.gensalt()); 
		
		   // 업데이트하기위한 세팅
		   MemberVO memVO = new MemberVO();
		   memVO.setMemberId(memberId); 
		   memVO.setMemberPassword(newPasswd); //업데이트할 값

		   memberService.updateMemberByPasswd(memVO);
		
		   /****************** headers 설정 *******************/
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type", "text/html; charset=UTF-8");
		   
		   String str = Script.href("비밀번호 변경완료!", "/member/login");

		   return new ResponseEntity<String>(str, headers, HttpStatus.OK);	
		   
	   }else {
		   
		   /****************** headers 설정 *******************/
		   HttpHeaders headers = new HttpHeaders();
		   headers.add("Content-Type", "text/html; charset=UTF-8");  
		   
		   String str = Script.href("비밀번호가 틀렸습니다", "/member/changePasswd");
		
		   return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	   }
   }   
   
   
   /* GET - 회원정보 삭제 */
   @GetMapping("/remove") // /member/remove
   public String remove() {
      System.out.println("remove 호출됨...");
      return "member/remove";
   }
   
   /* POST - 회원정보 삭제 */
   @PostMapping("/remove")
	public ResponseEntity<String> remove(String memberId, String memberPassword,
							HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	   
	   // 해당 아이디의 정보값 불러오기
	   MemberVO memberVO = memberService.selectMemberById(memberId);
	   // 해당 memberId의 memberPassword값과 사용자가 입력한 passwd값 비교
	   boolean isPasswdSame = BCrypt.checkpw(memberPassword, memberVO.getMemberPassword());

		// 입력한 패스워드가 같으면 탈퇴하기
		if(isPasswdSame == true) {
			memberService.deleteMemberById(memberId);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");  
			String str = Script.href("탈퇴되었습니다", "/");

			// 세션 비우기
			session.invalidate();
			// 로그인 상태유지용 쿠키 있으면 삭제하기
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id")) {
						cookie.setMaxAge(0); // 브라우저가 삭제할 수 있도록 0초로 설정
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				} // for
			}
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);	
			
		} else { // 비밀번호 틀린 경우
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");    
			String str = Script.href("비밀번호가 틀렸습니다", "/member/remove");
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
	}
}
   
