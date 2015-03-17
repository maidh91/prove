<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="header.jsp" %>
<%@include file="../functions.jsp"%>

<html>
<head>
<title>Change Password</title>
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css">
<link rel="stylesheet" type="text/css" href="../js_css/style.css">
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
			response.sendRedirect("../403.jsp");
		}
		
		String oldpassword = request.getParameter("oldPassword");
		String newpassword = request.getParameter("newPassword");
		String repassword = request.getParameter("rePassword");
		String status = request.getParameter("status");
		if (oldpassword == null || newpassword == null || repassword == null)
		{
%>
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
				out.println ("<h3>Your password is changed successfully !</h3>");
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
<%@ include file="footer.jsp" %>

</body>
</html>

<%
		}
		else
		{
			if (!repassword.equals(newpassword))
			{
				response.sendRedirect("ChangePassword.jsp?status=1");	
			}
			
			File fxml = new File("database/users.xml");
			String xmlFileName = fxml.getPath();
			String xmlFile = application.getRealPath(xmlFileName);
			String user = myCookie.getValue();
			String[] temp = user.split("~");
			user = temp[0];

			int i = 0;
			int invalid = 0;
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
						if (!pwd.equals(md5(oldpassword)))
						{
							invalid = 1;
						}
						else 
						{
							data.getChild("pwd").setText(md5(newpassword));
							
							FileOutputStream fileOS = new FileOutputStream(xmlFile);
							OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
							BufferedWriter bufferFile = new BufferedWriter(writer);
							XMLOutputter outputter = new XMLOutputter();
							outputter.setFormat(Format.getPrettyFormat());
							outputter.output(doc, writer);
							bufferFile.close();
							
							String valCookie = user+ "~" + md5(newpassword) + "~" + "admin";
							Cookie cookie = new Cookie("svgroup", valCookie);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
						break;
						
					}
				}
			} 
			catch (JDOMException e) {} 
			catch (IOException e1) {}
			
			// 1: password hien tai bi sai
			// 3: update change password

			if (invalid == 1)
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