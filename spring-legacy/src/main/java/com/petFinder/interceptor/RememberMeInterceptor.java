package com.petFinder.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RememberMeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 메소드 호출 전 실행
		
		HttpSession session = request.getSession();
		// 세션에 로그인 아이디가 있는지 확인하기
		String memberId = (String) session.getAttribute("memberId");
		
		// 세션에 아이디가 없으면 로그인 상태 유지용 쿠키정보 가져와서
		// 세션에 저장한 후 현재 호출된 컨트롤러 메소드를 호출되도록 true 리턴함.
		
		if(memberId == null){
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				
				//쿠키 이름이 "id"인 쿠키 객체 찾기 
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("memberId")) {
						memberId = cookie.getValue();
						// 로그인 인증 처리 (세션에 id값 저장)
						session.setAttribute("memberId", memberId);
					}
				}// for
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 메소드 호출 이후 실행
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 컨트롤러 메소드 호출 -> 뷰 실행까지 완료된 이후에 실행

	}

	
}
