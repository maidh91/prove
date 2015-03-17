<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@page import="java.servlet.http.*"%>
<%
	Cookie cookie = new Cookie("svgroup", "");
	cookie.setPath("/");
	cookie.setMaxAge(0);
	response.addCookie(cookie);
	
	response.sendRedirect("Login.jsp");
%>