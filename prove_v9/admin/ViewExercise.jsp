<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="../functions.jsp"%>
<%@include file="header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Exercises list</title>
<link rel="stylesheet" type="text/css" href="../js_css/style.css" />
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css" />
<link rel="shortcut icon" href="../js_css/bannerImg/bklogo.ico">
<script type="text/javascript" language="javascript" src="../js_css/script.js"></script>
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
		if (!tempAuth[2].equals("admin") && !tempAuth[2].equals("teacher"))
		{
			response.sendRedirect("../403.jsp");
		}
%>
</head>
<body>

<h2>List of exercises</h2>
<a id="addbutton" align="left" class="button" href="AddExercise.jsp"><span>Add new exercise</span></a>
<br /><br />
<%
	File fxml = new File("database/exercises.xml");
	String exFileName = fxml.getPath();
	String exFile = application.getRealPath(exFileName);
	
	SAXBuilder saxBuilder = new SAXBuilder();
	Document doc = saxBuilder.build(new File(exFile));

	String id = "";
	int i = 0;
	List list = doc.getRootElement().getChildren();
	Iterator iter = list.iterator();
	while (iter.hasNext()){
%>
<table width="100%" border="1" cellpadding ="3" cellspacing="0">
	<%
		i++;
		Element element = (Element) iter.next();
		List rptList  = element.getChildren();
		Iterator listIter = rptList.iterator();
		Element childNode = null;
		childNode = (Element) listIter.next();
		id = childNode.getText();
		childNode = (Element) listIter.next();
%>
<a name="<%=id%>" />
<tr><th align="center" colspan="2">Exercise <%= i%></th></tr>
<tr><td class="index" align="left" width="10%">Problem : </td><td width="90%"><pre><%= childNode.getText() %></pre></td></tr>
<%		childNode = (Element) listIter.next();
%>
<tr><td class="index" align="left" width="10%">Prototype : </td><td width="90%"><pre><%= childNode.getText() %></pre></td></tr>
<%		childNode = (Element) listIter.next();
		String var_init_temp = childNode.getText();
		if (var_init_temp.equals(""))
		{
			var_init_temp = " ";
		}
%>
<tr><td class="index" align="left" width="10%">Solution : </td><td width="90%"><pre><%= var_init_temp %></pre></td></tr>
<tr><td class="index" align="left" width="10%">Var Init : </td><td width="90%">
<%		childNode = (Element) listIter.next();
		var_init_temp = childNode.getText();
		String divname = "varinit" + String.valueOf(i);
		if (var_init_temp.equals(""))
		{
			var_init_temp = " ";
%>
<pre><%=  var_init_temp %></pre>
<%
		}
		else
		{
%>
<a class="button" onclick="showVarInitField('<%= divname%>');"><span>Show / Hide</span></a>
<div id="<%= divname%>" style="display: none"><pre><%=  var_init_temp %></pre></div>
<%
		}
%>

</td></tr>
</table>
<table width="100%" border="0" cellpadding ="3" cellspacing="0">
<tr><td>
<a id="editbutton" align="right" class="button" href="ActionExercise.jsp?do=edit&id=<%=id%>"><span>Edit</span></a>
<a id="delbutton" align="right" class="button" href="ActionExercise.jsp?do=delete&id=<%=id%>" onclick="return confirm('Are you sure to delete this file ?');"><span>Delete</span></a>
</td></tr>
</table><br /><br />
<%	}
	if (i == 0) out.println ("<h3><center>You have no exercise.</center><h3>");
%>

<br /><br /><br />
<%@ include file="footer.jsp" %>

</body>
</html>
<%	} 
%>