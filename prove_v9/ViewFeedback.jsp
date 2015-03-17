<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="functions.jsp"%>
<%@include file="template/header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Feedback</title>
<link rel="stylesheet" type="text/css" href="./js_css/style.css" />
<link rel="stylesheet" type="text/css" href="./js_css/round-button.css" />
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
%>
<script type="text/javascript" language="javascript" src="js_css/script.js"></script>
</head>
<body>

<h2>List of your reports</h2>
<a id="addbutton" align="left" class="button" href="AddFeedback.jsp"><span>Add new report</span></a>
<br /><br />
<%
	String reportFileName = base_url + feedbackPath + tempAuth[0] + ".xml";
	String reportFile = application.getRealPath(reportFileName);
	
	File f = new File(reportFile);
	if (!(f.exists() && f.isFile()))
	{
		out.println ("<h3><center>You have no report.</center><h3>");
	}
	else
	{
	SAXBuilder saxBuilder = new SAXBuilder();
	Document doc = saxBuilder.build(new File(reportFile));

	String id = "";
	List list = doc.getRootElement().getChildren();
	Iterator iter = list.iterator();
	if (!iter.hasNext())
	{
		out.println ("<h3><center>You have no report.</center><h3>");
	}
	while (iter.hasNext()){
%>
<table width="100%" border="1" cellpadding ="3" cellspacing="0">
<%
		Element element = (Element) iter.next();
		List rptList  = element.getChildren();
		Iterator listIter = rptList.iterator();
		Element childNode = null;
		childNode = (Element) listIter.next();
		id = childNode.getText();
		childNode = (Element) listIter.next();
%>
<tr><td class="index" align="left" width="15%">Exercise ID : </td><td width="85%"><%= childNode.getText() %></td></tr>
<%		childNode = (Element) listIter.next();
%>
<tr><td class="index" align="left" width="15%">Student code : </td><td width="85%"><pre><%= childNode.getText() %></pre></td></tr>
<%		childNode = (Element) listIter.next();
%>
<tr><td class="index" align="left" width="15%">Desired Result : </td><td width="85%"><%= childNode.getText() %></td></tr>
<%		childNode = (Element) listIter.next();
%>
<tr><td class="index" align="left" width="15%">Web Result : </td><td width="85%"><%= childNode.getText() %></td></tr>
<%		childNode = (Element) listIter.next();
%>
<tr><td class="index" align="left" width="15%">Comment : </td><td width="85%"><%= childNode.getText()%></td></tr>
</table>
<table width="100%" border="0" cellpadding ="3" cellspacing="0">
<tr><td>
<a id="editbutton" align="right" class="button" href="ActionFeedback.jsp?do=edit&id=<%=id%>"><span>Edit</span></a>
<a id="delbutton" align="right" class="button" href="ActionFeedback.jsp?do=delete&id=<%=id%>" onclick="return confirm('Are you sure to delete this file ?');"><span>Delete</span></a>
</td></tr>
</table><br /><br />
<%		}
	}
%>

<br /><br /><br />
<%@ include file="template/footer.htm" %>

</body>
</html>
<%	}	%>