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
		
		if (memberId == null) { // �α��� ��������
			response.sendRedirect("/member/login"); // �α��� ȭ������ �����̷�Ʈ �̵���Ű��
			return false;  // false�� �����Ͽ� �ش� ��Ʈ�ѷ� �޼ҵ带 ȣ�� ���ϰ� ��
		}
		
		// �α��� �������� true�� �����Ͽ� �ش� ��Ʈ�ѷ� �޼ҵ� ȣ���ϰ� ��
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
