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
		// ��Ʈ�ѷ� �޼ҵ� ȣ�� �� ����
		
		HttpSession session = request.getSession();
		// ���ǿ� �α��� ���̵� �ִ��� Ȯ���ϱ�
		String memberId = (String) session.getAttribute("memberId");
		
		// ���ǿ� ���̵� ������ �α��� ���� ������ ��Ű���� �����ͼ�
		// ���ǿ� ������ �� ���� ȣ��� ��Ʈ�ѷ� �޼ҵ带 ȣ��ǵ��� true ������.
		
		if(memberId == null){
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				
				//��Ű �̸��� "id"�� ��Ű ��ü ã�� 
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("memberId")) {
						memberId = cookie.getValue();
						// �α��� ���� ó�� (���ǿ� id�� ����)
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
		// ��Ʈ�ѷ� �޼ҵ� ȣ�� ���� ����
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// ��Ʈ�ѷ� �޼ҵ� ȣ�� -> �� ������� �Ϸ�� ���Ŀ� ����

	}

	
}
