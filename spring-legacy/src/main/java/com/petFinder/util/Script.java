package com.petFinder.util;

public class Script {
	
	/* 특정 경로 이동 */
	public static String href(String alertMessage, String locationPath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+alertMessage+"');");
		sb.append("location.href='" + locationPath + "';");
		sb.append("</script>");
		
		return sb.toString();
	}
	
	/*  뒤로가기 */
	public static String back(String alertMessage) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+alertMessage+"');");
		sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}


}
