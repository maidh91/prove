<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@include file="template/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Software verification</title>
<link rel="stylesheet" type="text/css" href="./js_css/style.css" />
<link rel="stylesheet" type="text/css" href="./js_css/round-button.css" />
<link rel="shortcut icon" href="./js_css/bannerImg/bklogo.ico">
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
	String[] tempAuth = null;
	
	if (myCookie != null)
	{
		String fileName = splitAuth(myCookie.getValue());
		String xmlFileName = base_url + "database/" + fileName;
		String xmlFile = application.getRealPath(xmlFileName);
		tempAuth = checkAuth (xmlFile, myCookie.getValue());
	}
	else
	{
		// Mai H. Dinh - 15/10/2013
		// remove login
		String valCookie = "provegroup~f56555ae89c0cb710cbe6e8cae5ce4d3~student";
		Cookie cookie = new Cookie("svgroup", valCookie);
		cookie.setPath("/");
		response.addCookie(cookie);
		response.sendRedirect("index.jsp");
		// end remove login
	}
	
	if (tempAuth == null)
	{
		out.println ("<h2 align=center>In order to login, please register and change your default password.</h2>");
		out.println ("<h2 align=center>You did not log in!!! Please <a href=Register.jsp>register</a> or <a href=Login.jsp>log in</a>");
	}
	else 
	{
		if (tempAuth[2].equals("teacher"))
		{
			response.sendRedirect("admin/403.jsp");
		}
%>
</head>
<body>
<table style="border-style:outset; border-color:#006600;" width="100%" border="3" cellpadding ="3" cellspacing="0">
<tr><td style="cursor:default">
<h3>Introduction</h3>
<p>
<b>Software Verification</b> is one of the major projects of the Faculty of computer Science and Engineering. The project is on verifying the correctness of the software based on logic, not execute it or use testing. The project is for education and training, offer the tool to help student learn programming.
</p>
</td></tr>
</table>
<h2>List of exercise</h2>
<div style="margin-left:60px">
<%

	String stdUrl = "";
	String id_str = "";
	
	int count = 0;
	String exFileName = base_url + xmlExercise;
	String exFile = application.getRealPath(exFileName);
	
	try {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(new File(exFile));
		Element root = (Element) doc.getRootElement();
		List list = root.getChildren();
		Iterator iter = list.iterator();
%>
<table width="100%" border="0" cellpadding ="3" cellspacing="0">
<%
		while (iter.hasNext()){
			Element element = (Element) iter.next();
			id_str = element.getChildText("exid");
			count++;
			stdUrl = urlViewExercise+"?id="+id_str;
			String exLink = "<a href=" + stdUrl + ">Exercise " + count + "</a>";
			String prob = element.getChildText("problem");
			String subProblem = prob.substring(0, prob.indexOf(".") + 1) + " ...";
%>
	<tr>
		<td align="left" width="20%" ><%= exLink%></td>
		<td align="left"><%= subProblem %></td>
	</tr>
<%
		}
	}
	catch (JDOMException e) {} 
	catch (IOException e1) {}
%>
</table>
</div>
<br /><br /><br /><br />
<%@ include file="template/footer.htm" %>

</body>
</html>
<%	}	%>

