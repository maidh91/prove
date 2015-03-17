<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="../functions.jsp"%>
<%@include file="header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add student</title>
<link rel="stylesheet" type="text/css" href="../js_css/style.css" />
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css" />
<link rel="shortcut icon" href="../js_css/bannerImg/bklogo.ico">
<%	Cookie[] allCookies = request.getCookies();
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
		
		String act = (String) request.getParameter("actionVal");
		if (act != null && act.equals("addstudent"))
		{
			File fxml = new File("database/tester.xml");
			String stuFileName = fxml.getPath();
			String stuFile = application.getRealPath(stuFileName);
			
			String stuId = "";
			int invalid = 0;
			int id_int = 0;
			String stdid_submit = request.getParameter("stdid");
			String name_submit = new String(request.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");
			String email_submit = request.getParameter("email");
			
			try {
				SAXBuilder saxBuilder = new SAXBuilder();
				Document doc = saxBuilder.build(new File(stuFile));
				Element root = (Element) doc.getRootElement();
				List list = root.getChildren();
				Iterator iter = list.iterator();
				while (iter.hasNext()){
					Element element = (Element) iter.next();
					stuId = element.getChildText("stdid");
					if (stuId.equals(stdid_submit))
					{
						invalid = 1;
						break;
					}
					id_int = Integer.parseInt(element.getChildText("id"));
				}
				if (invalid != 1)
				{
					id_int++;
					Element newChild = new Element("student");
					Element idNode = new Element("id");
					idNode.setText(String.valueOf(id_int));
					newChild.addContent(idNode);
					Element stdidNode = new Element("stdid");
					stdidNode.setText(stdid_submit);
					newChild.addContent(stdidNode);
					Element nameNode = new Element("name");
					nameNode.setText(name_submit);
					newChild.addContent(nameNode);
					Element emailNode = new Element("email");
					emailNode.setText(email_submit);
					newChild.addContent(emailNode);
					Element pwdNode = new Element("pwd");
					pwdNode.setText(stdid_submit);
					newChild.addContent(pwdNode);
					root.addContent(newChild);

					FileOutputStream fileOS = new FileOutputStream(stuFile);
					OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
					BufferedWriter bufferFile = new BufferedWriter(writer);
					XMLOutputter outputter = new XMLOutputter();
					outputter.setFormat(Format.getPrettyFormat());
					outputter.output(doc, writer);
					bufferFile.close();
					
					response.sendRedirect("ViewStudent.jsp");
				}
			}
			catch (JDOMException e) {} 
			catch (IOException e1) {}
		}
%>
</head>
<body>

<h2>Add student</h2>
<br />

<form action="AddStudent.jsp" name="addStudent" id="addStudent" method="post">
<input name="actionVal" type="hidden" value="addstudent">
<table border="0" width="100%" cellpadding ="5">
<tr><td class="index" width="15%">Student ID : </td><td width="85%"><input type="text" id ="stdid" name="stdid" size="30"></input></td></tr>
<tr><td class="index" width="15%">Full Name : </td><td width="85%"><input type="text" id ="fullname" name="fullname" size="30"></input></td></tr>
<tr><td class="index" width="15%">Email : </td><td width="85%"><input type="text" id ="email" name="email" size="30"></input></td></tr>
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
<%	}
%>