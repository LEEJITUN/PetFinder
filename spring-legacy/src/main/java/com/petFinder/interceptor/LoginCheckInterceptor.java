package com.petFinder.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if (memberId == null) { // 로그인 안했을때
			response.sendRedirect("/member/login"); // 로그인 화면으로 리다이렉트 이동시키기
			return false;  // false를 리턴하여 해당 컨트롤러 메소드를 호출 안하게 함
		}
		
		// 로그인 했을때는 true를 리턴하여 해당 컨트롤러 메소드 호출하게 함
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
