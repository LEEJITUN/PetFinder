package com.petFinder.socketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.petFinder.domain.MemberVO;


import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

	//�α��� �� ��ü
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	// 1��1
	Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
		
	//������ ������ ���� ������
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		System.out.println("??????");
		System.out.println("session:"+ session);
		sessions.add(session);
		
		String senderEmail = getEmail(session);
		userSessionsMap.put(senderEmail , session);
	}
	
	//���Ͽ� �޼����� ��������
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String senderEmail = getEmail(session);
		//��� �������� ������ - ��ε� ĳ����
//		for (WebSocketSession sess : sessions) {
//			sess.sendMessage(new TextMessage(senderNickname + ": " +  message.getPayload()));
//		}
		
		System.out.println("handleTextMessage"+ session);
		System.out.println("message"+ message);
		
		//protocol : cmd , ����ۼ���, �Խñ� �ۼ��� , seq (reply , user2 , user1 , 12)
		String msg = message.getPayload();
		if(StringUtils.isNotEmpty(msg)) {
			String[] strs = msg.split(",");
			
			if(strs != null && strs.length == 5) {
				String cmd = strs[0];
				String caller = strs[1]; 
				String receiver = strs[2];
				String receiverEmail = strs[3];
				String seq = strs[4];
				
				//�ۼ��ڰ� �α��� �ؼ� �ִٸ�
				WebSocketSession boardWriterSession = userSessionsMap.get(receiverEmail);
				
				// ���
				if("reply".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "���� " + 
							"<a type='external' href='/member/memberInfo?memberId="+seq+"&pg=1'>" + seq + "</a> �� �Խñۿ� ����� ������ϴ�.");
					boardWriterSession.sendMessage(tmpMsg);
				
				}else if("good".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "���� " + receiver +
							 "���� �Խ��ǿ� ���ƿ並 �������ϴ�.");
					boardWriterSession.sendMessage(tmpMsg);
					
				}
			}
		}
	}
	
	//���� �����ɶ�
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//System.out.println("afterConnectionClosed " + session + ", " + status);
		userSessionsMap.remove(session.getId());
		sessions.remove(session);
	}
	
	//������ email ��������
	private String getEmail(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String loginUser = (String)httpSession.get("memberId");
		
		System.out.println("loginUser"+loginUser);
		if(loginUser == null) {
			return session.getId();
		} else {
			return loginUser;
		}
		

	}
	

}