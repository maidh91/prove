<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@include file="functions.jsp"%>

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
	
	if (myCookie != null)
	{
		response.sendRedirect("index.jsp");
	}
	
	String stdId = request.getParameter("stdId");
	String password = request.getParameter("password");
	String status = request.getParameter("status");
	if (stdId == null || password == null)
	{
%>

<html>
<head>
<title>Login Form</title>
<link rel="stylesheet" type="text/css" href="js_css/round-button.css">
<link rel="stylesheet" type="text/css" href="js_css/style.css">
<link rel="shortcut icon" href="./js_css/bannerImg/bklogo.ico">
<script type="text/javascript" language="javascript" src="js_css/script.js"></script>
</head>
<body>
<%@include file="template/header.jsp" %>

<br /><br />
<h3 style='text-align:center; color:#000000;'>Login Form</h3><br />
<form method="POST" name="login" id="login" action="Login.jsp">
  <table width="300" border="0" align="center" cellpadding="3" cellspacing="1">
	<tr><td colspan=2>
<%
	if (status != null)
	{
		if (status.equals("0"))
		{
			out.println ("<h3><center>You are not in Alpha test team.</center></h3>");
		}
		else if (status.equals("1"))
		{
			out.println ("<h3><center>You have not registered yet.</center></h3>");
		}
		else if (status.equals("2"))
		{
			out.println ("<h3><center>Wrong password !</center></h3>");
		}
	}
%>
	</td></tr>
    <tr>
      <td width="100" class="loginForm">Student Id:</td>
      <td>
<%
	  if (stdId == null)
			out.println ("<input name='stdId' type='text' id='stdId'></td>");
	  else
			out.println ("<input name='stdId' type='text' id='stdId'>"+stdId+"</input></td>");
%>
    </tr>
    <tr>
      <td width="100" class="loginForm">Password:</td>
      <td><input name="password" type="password" id="password"></td>
    </tr>
    <tr>
      <td width="100">&nbsp;</td>
      <td><span class="button"><input name="Submit" type="submit" value="Login"></span></td>
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
		String xmlFileName = base_url + "database/tester.xml";
		String xmlFile = application.getRealPath(xmlFileName);
		int checkVal = checkXML(xmlFile, stdId, password);
		
		// 0: khong la tester
		// 1: la tester nhung chua register
		// 2: la tester, da register nhung password ko dung
		// 3: valid, cap quyen vao.
		
		if (checkVal == 3)
		{
			String valCookie = stdId+ "~" + md5(password) + "~" + "student";
			Cookie cookie = new Cookie("svgroup", valCookie);
			cookie.setPath("/");
			response.addCookie(cookie);
			response.sendRedirect("index.jsp");
		}
		else
		{
			response.sendRedirect("Login.jsp?status=" + checkVal);
		}
		
	}
%>

