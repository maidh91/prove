<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="../functions.jsp"%>
<%@include file="header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin users list</title>
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

<h2>Admin users list</h2>
<a id="addbutton" align="left" class="button" href="AddUser.jsp"><span>Add new user</span></a>
<br /><br />
<%
	File fxml = new File("database/users.xml");
	String userFileName = fxml.getPath();
	String userFile = application.getRealPath(userFileName );
	
	SAXBuilder saxBuilder = new SAXBuilder();
	Document doc = saxBuilder.build(new File(userFile));

	String id = "";
	int i = 0;
	List list = doc.getRootElement().getChildren();
	Iterator iter = list.iterator();
	if (iter.hasNext()){
%>
<table width="100%" border="1" cellpadding ="3" cellspacing="0">
<tr>
	<th class="index" align="center">#</th>
	<th class="index" align="center">User Name</th>
	<th class="index" align="center">Edit</th>
	<th class="index" align="center">Delete</th>
</tr>
	<%
	}
	while (iter.hasNext()){
		Element element = (Element) iter.next();
		List rptList  = element.getChildren();
		Iterator listIter = rptList.iterator();
		Element childNode = null;
		childNode = (Element) listIter.next();
		id = childNode.getText();
		if (Integer.parseInt(id) == 0) continue;
		i++;
		childNode = (Element) listIter.next();
	%>
<tr>
<td align="center"><%= i%></td>
<td align="center"><%= childNode.getText() %></td>
<td align="center">
<a href="ActionUser.jsp?do=edit&id=<%=id%>"><img src="images/Edit.png" class="icon"/></a></td>
<td align="center">
<a href="ActionUser.jsp?do=delete&id=<%=id%>" onclick="return confirm('Are you sure to delete this user profile ?');"><img src="images/Delete.png" class="icon"/></a></td>
<%	}
	if (i == 0) out.println ("<h3><center>You have no admin user.</center><h3>");
%>
</table>
<br /><br /><br />
<%@ include file="footer.jsp" %>

</body>
</html>
<%	}
%>