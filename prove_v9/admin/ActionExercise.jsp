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
		if (!tempAuth[2].equals("admin") && !tempAuth[2].equals("teacher"))
		{
			response.sendRedirect("../403.jsp");
		}
		
		File fxml = new File("database/exercises.xml");
		String exFileName = fxml.getPath();
		String exFile = application.getRealPath(exFileName);
			
		String id = request.getParameter("id");
		String actVal = request.getParameter("do");
		String act_submit = request.getParameter("actionVal");
		String exercise_id = null;
		String cur_problem = null;
		String cur_prototype = null;
		String cur_solution = null;
		String cur_varinit = null;
		
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(exFile));
			Element root = (Element) doc.getRootElement();
			List list = root.getChildren();
			Iterator iter = list.iterator();
			Element data = null;
			while (iter.hasNext()){
				data = (Element) iter.next();
				exercise_id = data.getChildText("exid");
				if (exercise_id.equals(id)) break;
			}
			
			if (actVal != null && actVal.equals("delete"))
			{
				root.removeContent(data);
				
				FileWriter writer = new FileWriter(exFile);
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				int id_int = Integer.parseInt(id) - 1;
				
				response.sendRedirect("ViewExercise.jsp#" + id_int);
			}	
			else if (act_submit != null && act_submit.equals("submitted"))
			{
				String prototype_submit = request.getParameter("prototype");
				prototype_submit = prototype_submit.replaceAll ("&", "&amp;");
				prototype_submit = prototype_submit.replaceAll ("[ ]*<[ ]*", " &lt; ");
				prototype_submit = prototype_submit.replaceAll ("[ ]*&lt; =[ ]*", " &lt;= ");
				prototype_submit = prototype_submit.replaceAll ("&lt;= >", "&lt;=>");
				prototype_submit = prototype_submit.replaceAll ("\\n\\t* *\\r", "");
				
				String problem_submit = request.getParameter("problem");
				problem_submit = problem_submit.replaceAll ("&", "&amp;");
				problem_submit = problem_submit.replaceAll ("[ ]*<[ ]*", " &lt; ");
				problem_submit = problem_submit.replaceAll ("[ ]*&lt; =[ ]*", " &lt;= ");
				problem_submit = problem_submit.replaceAll ("&lt;= >", "&lt;=>");
				problem_submit = problem_submit.replaceAll ("\\n\\t* *\\r", "");
				
				String solution_submit = request.getParameter("solution");
				solution_submit = solution_submit.replaceAll ("&", "&amp;");
				solution_submit = solution_submit.replaceAll ("[ ]*<[ ]*", " &lt; ");
				solution_submit = solution_submit.replaceAll ("[ ]*&lt; =[ ]*", " &lt;= ");
				solution_submit = solution_submit.replaceAll ("&lt;= >", "&lt;=>");
				solution_submit = solution_submit.replaceAll ("\\n\\t* *\\r", "");
				
				String var_init_submit = request.getParameter("var_init");
				var_init_submit = var_init_submit.replaceAll ("&", "&amp;");
				var_init_submit = var_init_submit.replaceAll ("[ ]*<[ ]*", " &lt; ");
				var_init_submit = var_init_submit.replaceAll ("[ ]*&lt; =[ ]*", " &lt;= ");
				var_init_submit = var_init_submit.replaceAll ("&lt;= >", "&lt;=>");
				var_init_submit = var_init_submit.replaceAll ("\\n\\t* *\\r", "");
			
				data.getChild("problem").setText(problem_submit);
				data.getChild("prototype").setText(prototype_submit);
				data.getChild("solution").setText(solution_submit);
				data.getChild("varinit").setText(var_init_submit);
				
				FileWriter writer = new FileWriter(exFile);
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				response.sendRedirect("ViewExercise.jsp#" + id);
			}
			else
			{
				cur_problem = data.getChildText("problem");
				cur_problem = cur_problem.replaceAll ("&amp;", "&");
				cur_problem = cur_problem.replaceAll ("&lt;", "<");
				cur_prototype = data.getChildText("prototype");
				cur_prototype = cur_prototype.replaceAll ("&amp;", "&");
				cur_prototype = cur_prototype.replaceAll ("&lt;", "<");
				cur_solution = data.getChildText("solution");
				cur_solution = cur_solution.replaceAll ("&amp;", "&");
				cur_solution = cur_solution.replaceAll ("&lt;", "<");
				cur_varinit = data.getChildText("varinit");
				cur_varinit = cur_varinit.replaceAll ("&amp;", "&");
				cur_varinit = cur_varinit.replaceAll ("&lt;", "<");
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
<title>Edit exercise</title>
<link rel="stylesheet" type="text/css" href="../js_css/style.css" />
<link rel="stylesheet" type="text/css" href="../js_css/round-button.css" />
<link rel="shortcut icon" href="../js_css/bannerImg/bklogo.ico">
</head>
<body>

<h2>Edit exercise</h2>
<br />

<form action="ActionExercise.jsp" name="editExercise" id="editExercise" method="post">
<input name="id" type="hidden" value="<%= id%>">
<input type="hidden" name="actionVal" value="submitted" />
<table border="1" width="100%" cellpadding ="5">
<th colspan="2" align="center"><h3><font color="#FF0000">Edit an exercise</font></h3></th>
<tr><td class="index" width="15%">Problem : </td><td width="85%"><textarea id ="problem" name="problem" cols="100" rows="8"><%= cur_problem%></textarea></td></tr>
<tr><td class="index" width="15%">Prototype : </td><td width="85%"><textarea id ="prototype" name="prototype" cols="100" rows="8"><%= cur_prototype%></textarea></td></tr>
<tr><td class="index" width="15%">Solution : </td><td width="85%"><textarea id ="solution" name="solution" cols="100" rows="8"><%= cur_solution%></textarea></td></tr>
<tr><td class="index" width="15%">Var Init : </td><td width="85%"><textarea id ="var_init" name="var_init" cols="100" rows="15"><%= cur_varinit%></textarea></td></tr>
</table>
<table border="0" width="100%" cellpadding ="5">
<tr><td colspan="2" width="30%" align="center">
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