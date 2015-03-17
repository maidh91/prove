<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="header.jsp" %>
<%@include file="../functions.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="../js_css/style.css" />
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css" />
<link rel="shortcut icon" href="../js_css/bannerImg/bklogo.ico">
<%
	Cookie[] allCookies = request.getCookies();
	Cookie myCookie = null;
	if (allCookies != null)
	{
		for (int i = 0; i < allCookies.length; i++)
		{
			if (allCookies[i].getName().equals("svgroup"))
			{
				myCookie = allCookies[i];
				break;
			}
		}
	}
	String[] tempAuth = null;
	
	if (myCookie != null)
	{
		String fileName = splitAuth(myCookie.getValue());
		File fxml = new File("database/" + fileName);
		String xmlFileName = fxml.getPath();
		String xmlFile = application.getRealPath(xmlFileName);
		tempAuth = checkAuth (xmlFile, myCookie.getValue());
	}
	
	if (tempAuth == null)
	{
		response.sendRedirect("Login.jsp");
	}
	else 
	{
		if (!tempAuth[2].equals("admin") && !tempAuth[2].equals("teacher"))
		{
			response.sendRedirect("403.jsp");
		}
%>
</head>
<body>

<h2>Main menu</h2>
<span class="menuicon"><a href="ViewExercise.jsp"><img src="images/Exercise.png" class="icon"/><br/><h4>Manage Exercises</h4></a></span>
<span class="menuicon"><a href="ChangePassword.jsp"><img src="images/Key.png" class="icon"/><br/><h4>Change Password</h4></a></span>
<span class="menuicon"><a href="ViewReport.jsp"><img src="images/Feedback.png" class="icon"/><br/><h4>View Feedback</h4></a></span>
<%
		if (tempAuth[2].equals("admin"))
		{
%>
<span class="menuicon"><a href="ViewStudent.jsp"><img src="images/User.png" class="icon"/><br/><h4>Manage Users</h4></a></span>
<span class="menuicon"><a href="ViewUser.jsp"><img src="images/Admin.png" class="icon"/><br/><h4>Manage Admin Users</h4></a></span>
<%
		}
	}
%>
</html>

