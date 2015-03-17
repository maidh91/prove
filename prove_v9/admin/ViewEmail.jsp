<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="../functions.jsp"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Email list</title>
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
%>
</head>
<body>

<a id="back" align="left" class="button" href="ViewStudent.jsp"><span>Go back</span></a>
<br /><br />
<fieldset>
<LEGEND ACCESSKEY=I>Students' email</LEGEND>
<center>
<%
	File fxml = new File("database/tester.xml");
	String stuFileName = fxml.getPath();
	String stuFile = application.getRealPath(stuFileName );
	
	try {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(new File(stuFile));

		Element root = (Element) doc.getRootElement();
		List list = root.getChildren();
		Iterator iter = list.iterator();
		
		String fullname = null;
		String email = null;
		
		while (iter.hasNext()){
			Element element = (Element) iter.next();
			fullname = element.getChildText("name");
			email = element.getChildText("email");
			out.println("\"" + fullname + "\"" + " &lt;" + email + ">," + "<br />");
		}
	}
	catch (JDOMException e) {} 
	catch (IOException e1) {}			
%>
</center>
</fieldset>
<br /><br />
<%@ include file="footer.jsp" %>

</body>
</html>
<%	}
%>