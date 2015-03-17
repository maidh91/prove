<%@page language="java" contentType="text/html"%>
<%@page session="true" import="java.util.*"%>
<%@include file="functions.jsp"%>
<html>
<head>
<title>Registration Form</title>
<link rel="stylesheet" type="text/css" href="js_css/round-button.css">
<link rel="stylesheet" type="text/css" href="js_css/style.css">
<link rel="shortcut icon" href="./js_css/bannerImg/bklogo.ico">
<script type="text/javascript" language="javascript" src="js_css/script.js"></script>
</head>
<body>
<%@include file="template/header.jsp" %>
<%
	String stdId = request.getParameter("stdId");
	String oldpassword = request.getParameter("oldPassword");
	String newpassword = request.getParameter("newPassword");
	String repassword = request.getParameter("rePassword");
	String status = request.getParameter("status");
	if (stdId == null || oldpassword == null || newpassword == null || repassword == null)
	{
%>
<br /><br />
<h3 style='text-align:center; color:#000000;'>Registration Form</h3><br />
<form method="POST" name="register" id="register" action="Register.jsp">
  <table width="400" border="0" align="center" cellpadding="3" cellspacing="1">
    <tr><td colspan=2 align="center">
	<%
		if (status != null)
		{	if (status.equals("1"))
			{
				out.println ("<h3>Retype password is wrong !</h3>");
			}
			else if (status.equals("2"))
			{
				out.println ("<h3>New password must be different from the current !</h3>");
			}
			else if (status.equals("3"))
			{
				out.println ("<h3>You are not in Alpha test team !</h3>");
			}
			else if (status.equals("4"))
			{
				out.println ("<h3>Your current password is wrong !</h3>");
			}
			else if (status.equals("5"))
			{
				out.println ("<h3>Register successfully !</h3>");
			}
		}
	%>
	</td></tr>
	<tr>
      <td width="150" class="loginForm">Student Id:</td>
      <td>
<%
	  if (stdId == null)
			out.println ("<input size='30' name='stdId' type='text' id='stdId'></td>");
	  else
			out.println ("<input size='30' name='stdId' type='text' id='stdId'>"+stdId+"</input></td>");
%>
    </tr>
    <tr>
      <td width="150" class="loginForm">Current password:</td>
      <td><input size="30" name="oldPassword" type="password" id="oldPassword"></td>
    </tr>
	<tr>
      <td width="150" class="loginForm">New password:</td>
      <td><input size="30" name="newPassword" type="password" id="newPassword"></td>
    </tr>
	<tr>
      <td width="150" class="loginForm">Retype password:</td>
      <td><input size="30" name="rePassword" type="password" id="rePassword"></td>
    </tr>
    <tr>
      <td width="100">&nbsp;</td>
      <td><span class="button"><input name="Submit" type="submit" value="Register"></span>&nbsp;&nbsp;
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
		int mode = 0; // che do register
		String xmlFileName = base_url + "database/tester.xml";
		String xmlFile = application.getRealPath(xmlFileName);
		int checkVal = registerXML(xmlFile, stdId, oldpassword, newpassword, mode);
		
		// checkVal
		// 0: khong la tester
		// 1: la tester nhung password hien tai bi sai hoac da register roi
		// 3: valid, update change password
		
		if (!repassword.equals(newpassword))
		{
			response.sendRedirect("Register.jsp?status=1");
		}
		else if (newpassword.equals(stdId))
		{
			response.sendRedirect("Register.jsp?status=2");
		}
		else if (checkVal == 0)
		{
			response.sendRedirect("Register.jsp?status=3");
		}
		else if (checkVal == 1)
		{
			response.sendRedirect("Register.jsp?status=4");
		}
		else
		{
			response.sendRedirect("Register.jsp?status=5");
		}
	}
%>