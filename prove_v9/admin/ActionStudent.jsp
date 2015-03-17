<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="header.jsp" %>
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
		response.sendRedirect("index.jsp");
	}
	else
	{
		if (!tempAuth[2].equals("admin"))
		{
			if (tempAuth[2].equals("student"))
				response.sendRedirect("../403.jsp");
			else
				response.sendRedirect("403.jsp");
		}
		
		File fxml = new File("database/tester.xml");
		String stuFileName = fxml.getPath();
		String stuFile = application.getRealPath(stuFileName );
		
		String id = request.getParameter("id");
		String actVal = request.getParameter("do");
		String act_submit = request.getParameter("actionVal");
		String stu_id = null;
		String cur_stdid = null;
		String cur_name = null;
		String cur_email = null;
		
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(stuFile));
			Element root = (Element) doc.getRootElement();
			List list = root.getChildren();
			Iterator iter = list.iterator();
			Element data = null;
			while (iter.hasNext()){
				data = (Element) iter.next();
				stu_id = data.getChildText("id");
				if (stu_id.equals(id)) break;
			}
			
			if (actVal != null && !actVal.equals("edit"))
			{
				if (actVal.equals("delete"))
				{
					root.removeContent(data);
				}
				else if (actVal.equals("reset"))
				{
					data.getChild("pwd").setText(md5(data.getChildText("stdid"))); // here
				}
				
				FileOutputStream fileOS = new FileOutputStream(stuFile);
				OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				response.sendRedirect("ViewStudent.jsp");
			}		
			else if (act_submit != null && act_submit.equals("submitted"))
			{
				String stdid_submit = request.getParameter("stdid");
				String name_submit = new String(request.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");
				String email_submit = request.getParameter("email");
			
				if (!stdid_submit.equals(data.getChildText("stdid")))
				{
					data.getChild("pwd").setText(stdid_submit);
				}
				data.getChild("stdid").setText(stdid_submit);
				data.getChild("name").setText(name_submit);
				data.getChild("email").setText(email_submit);
				
				FileOutputStream fileOS = new FileOutputStream(stuFile);
				OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				response.sendRedirect("ViewStudent.jsp");
			}
			else
			{
				cur_stdid = data.getChildText("stdid");
				cur_name = data.getChildText("name");
				cur_email = data.getChildText("email");
			}
		
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
%>
<br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit student</title>
<link rel="stylesheet" type="text/css" href="../js_css/style.css" />
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css" />
<link rel="shortcut icon" href="../js_css/bannerImg/bklogo.ico">
</head>
<body>

<h2>Edit student</h2>
<br />

<form action="ActionStudent.jsp" name="editStudent" id="editStudent" method="post">
<input name="id" type="hidden" value="<%= id%>">
<input type="hidden" name="actionVal" value="submitted" />
<table border="0" width="100%" cellpadding ="5">
<tr><td class="index" width="15%">Student ID : </td><td width="85%"><input type="text" id ="stdid" name="stdid" size="30" value="<%= cur_stdid%>" ></input></td></tr>
<tr><td class="index" width="15%">Full Name : </td><td width="85%"><input type="text" id ="fullname" name="fullname" size="30" value="<%= cur_name%>" ></input></td></tr>
<tr><td class="index" width="15%">Email : </td><td width="85%"><input type="text" id ="email" name="email" size="30" value="<%= cur_email%>" ></input></td></tr>
<tr><td colspan="2" width="30%" align="left">
<span class="button"><input type="submit" name="submit" value="Submit"></span>
<a class="button" href="" onclick="history.go(-1); return false;"><span>Cancel</span></a>
</td></tr>
</table>
</form>

<br /><br /><br />
<%@ include file="footer.jsp" %>

</body>
</html>
<%	}	%>
