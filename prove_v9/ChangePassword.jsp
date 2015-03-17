<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="template/header.jsp" %>
<%@include file="functions.jsp"%>

<html>
<head>
<title>Change Password</title>
<link rel="stylesheet" type="text/css" href="js_css/round-button.css">
<link rel="stylesheet" type="text/css" href="js_css/style.css">
<link rel="shortcut icon" href="./js_css/bannerImg/bklogo.ico">
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
		String xmlFileName = base_url + "database/" + fileName;
		String xmlFile = application.getRealPath(xmlFileName);
		tempAuth = checkAuth (xmlFile, myCookie.getValue());
	}
	
	if (tempAuth == null)
	{
		response.sendRedirect("index.jsp");
	}
	else 
	{
		if (tempAuth[2].equals("teacher"))
		{
			response.sendRedirect("admin/403.jsp");
		}
		
		String oldpassword = request.getParameter("oldPassword");
		String newpassword = request.getParameter("newPassword");
		String repassword = request.getParameter("rePassword");
		String status = request.getParameter("status");
		if (oldpassword == null || newpassword == null || repassword == null)
		{
%>
<script type="text/javascript" language="javascript" src="js_css/script.js"></script>
</head>
<body>

<br /><br />
<h3 style='text-align:center; color:#000000;'>Change Password</h3><br />
<form method="POST" name="chpass" id="chpass" action="ChangePassword.jsp">
  <table width="390" border="0" align="center" cellpadding="3" cellspacing="1">
	<tr><td colspan=2>
	<%
		if (status != null)
		{	if (status.equals("1"))
			{
				out.println ("<h3>Retype password is wrong !</h3>");
			}
			else if (status.equals("2"))
			{
				out.println ("<h3>Your current password is wrong !</h3>");
			}
			else if (status.equals("3"))
			{
				out.println ("<h3>Your password is changed successfully!</h3>");
			}
		}
	%>
	</td></tr>
    <tr>
      <td width="150" class="loginForm">Current password:</td>
      <td><input name="oldPassword" type="password" id="oldPassword"></td>
    </tr>
	<tr>
      <td width="150" class="loginForm">New password:</td>
      <td><input name="newPassword" type="password" id="newPassword"></td>
    </tr>
	<tr>
      <td width="150" class="loginForm">Retype password:</td>
      <td><input name="rePassword" type="password" id="rePassword"></td>
    </tr>
    <tr>
      <td align="center" colspan="2"><span class="button"><input name="Submit" type="submit" value="Submit"></span>&nbsp;&nbsp;
		  <span class="button"><input name="Reset" type="reset" value="Reset"></span></td>
    </tr>
  </table>
</form>

<br /><br /><br />
<%@ include file="template/footer.htm" %>

</body>
</html>

<%
		}
		else
		{
			int mode = 1; // che do update password
			String xmlFileName = base_url + "database/tester.xml";
			String xmlFile = application.getRealPath(xmlFileName);
			String stuId = tempAuth[0];
			int checkVal = registerXML(xmlFile, stuId, oldpassword, newpassword, mode);
			
			// 1: password hien tai bi sai
			// 3: update change password
			
			if (!repassword.equals(newpassword))
			{
				response.sendRedirect("ChangePassword.jsp?status=1");
			}
			else if (checkVal == 1)
			{
				response.sendRedirect("ChangePassword.jsp?status=2");
			}
			else
			{
				response.sendRedirect("ChangePassword.jsp?status=3");
			}
		}
	}
%>