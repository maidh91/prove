<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
<%@include file="../functions.jsp"%>
<%@include file="header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add user</title>
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
		if (act != null && act.equals("adduser"))
		{
			File fxml = new File("database/users.xml");
			String userFileName = fxml.getPath();
			String userFile = application.getRealPath(userFileName );
			
			String username = "";
			int invalid = 0;
			int id_int = 0;
			String name_submit = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
			
			try {
				SAXBuilder saxBuilder = new SAXBuilder();
				Document doc = saxBuilder.build(new File(userFile));
				Element root = (Element) doc.getRootElement();
				List list = root.getChildren();
				Iterator iter = list.iterator();
				while (iter.hasNext()){
					Element element = (Element) iter.next();
					username = element.getChildText("username");
					if (username.equals(name_submit))
					{
						invalid = 1;
						break;
					}
					id_int = Integer.parseInt(element.getChildText("userid"));
				}
				if (invalid != 1)
				{
					id_int++;
					Element newChild = new Element("user");
					Element idNode = new Element("userid");
					idNode.setText(String.valueOf(id_int));
					newChild.addContent(idNode);
					Element nameNode = new Element("username");
					nameNode.setText(name_submit);
					newChild.addContent(nameNode);
					Element pwdNode = new Element("pwd");
					pwdNode.setText(md5(name_submit));
					newChild.addContent(pwdNode);
					Element roleNode = new Element("role");
					roleNode.setText("teacher");
					newChild.addContent(roleNode);
					root.addContent(newChild);

					FileOutputStream fileOS = new FileOutputStream(userFile);
					OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
					BufferedWriter bufferFile = new BufferedWriter(writer);
					XMLOutputter outputter = new XMLOutputter();
					outputter.setFormat(Format.getPrettyFormat());
					outputter.output(doc, writer);
					bufferFile.close();
					
					response.sendRedirect("ViewUser.jsp");
				}
			}
			catch (JDOMException e) {} 
			catch (IOException e1) {}
		}
%>
</head>
<body>

<h2>Add user</h2>
<br />

<form action="AddUser.jsp" name="addUser" id="addUser" method="post">
<input name="actionVal" type="hidden" value="adduser">
<table border="0" width="100%" cellpadding ="5">
<tr><td class="index" width="15%">User Name : </td><td width="85%"><input type="text" id ="username" name="username" size="30"></input></td></tr>
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