<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="../functions.jsp"%>

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
	
	String user = request.getParameter("user");
	String password = request.getParameter("password");
	String status = request.getParameter("status");
	if (user == null || password == null)
	{
%>

<html>
<head>
<title>Login Form</title>
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css">
<link rel="stylesheet" type="text/css" href="../js_css/style.css">
<link rel="shortcut icon" href="../js_css/bannerImg/bklogo.ico">
</head>
<body>
<%@include file="header.jsp" %>

<br /><br />
<h3 style='text-align:center; color:#000000;'>Login Form</h3><br />
<form method="POST" name="login" id="login" action="Login.jsp">
  <table width="300" border="0" align="center" cellpadding="3" cellspacing="1">
    <tr><td colspan=2>
<%
	if (status != null && status.equals("fail"))
	{
		out.println ("<h3>Wrong username or password !</h3>");
	}
%>
	</td></tr>
	<tr>
      <td width="100" class="loginForm">Username:</td>
      <td>
<%
	  if (user == null)
			out.println ("<input name='user' type='text' id='user'></td>");
	  else
			out.println ("<input name='user' type='text' id='user'>"+user+"</input></td>");
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
<%@ include file="footer.jsp" %>

</body>
</html>

<%
	}
	else
	{
		File fxml = new File("database/users.xml");
		String xmlFileName = fxml.getPath();
		String xmlFile = application.getRealPath(xmlFileName);
		
		int i = 0;
		int valid = 0;
		String username, pwd;
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(xmlFile));
			Element e = doc.getRootElement();
			List lst = e.getChildren();
			for (i = 0; i < lst.size(); i++)
			{
				Element data = (Element)lst.get(i);
				username = data.getChildText("username");
				if (user.equals(username))
				{
					pwd = data.getChildText("pwd");
					if (pwd.equals(md5(password)))
					{
						valid = 1;
					}		
				}
			}
		} 
		catch (JDOMException e) {} 
		catch (IOException e1) {}

		if (valid == 1)
		{
			String valCookie = null;
			if (user.equals("admin"))
			{
				valCookie = user+ "~" + md5(password) + "~" + "admin";
			}
			else
			{
				valCookie = user+ "~" + md5(password) + "~" + "teacher";
			}
			Cookie cookie = new Cookie("svgroup", valCookie);
			cookie.setPath("/");
			response.addCookie(cookie);
			response.sendRedirect("index.jsp");
		}
		else
		{
			response.sendRedirect("Login.jsp?status=fail");
		}
	}
%>

