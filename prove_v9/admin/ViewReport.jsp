<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@page import="java.servlet.http.*"%>
<%@page import="java.util.Properties.*"%>
<%@include file="header.jsp" %>
<%@include file="../functions.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Reports</title>
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
		Properties stuProp = new Properties();
		Properties exProp = new Properties();
		ArrayList stdid_array = new ArrayList();
		
		try {
			File stuXml = new File("database/tester.xml");
			String stuFileName = stuXml.getPath();
			String stuFile = application.getRealPath(stuFileName );
		
			SAXBuilder stuSaxBuilder = new SAXBuilder();
			Document stuDoc = stuSaxBuilder.build(new File(stuFile));
			
			File exXml = new File("database/exercises.xml");
			String exFileName = exXml.getPath();
			String exFile = application.getRealPath(exFileName );
		
			SAXBuilder exSaxBuilder = new SAXBuilder();
			Document exDoc = stuSaxBuilder.build(new File(exFile));
%>
</head>
<body>

<h2>Student's report list</h2>
<form id="viewlist" action="ViewReport.jsp" method="post" >
<center><table width="70%">
	<tr>
		<td align="center">
			<fieldset>
				<LEGEND ACCESSKEY=S>Statistic</LEGEND>
						<br />
						<span class="button"><a onclick="alert('This option is not complete.'); return false;" ><button>Get stats</button></a></span>
						<br />
			</fieldset>
		</td>
		<td align="center">
			<fieldset>
				<LEGEND ACCESSKEY=I>Select All</LEGEND>
						<br />
						<span class="button"><a href="ViewReport.jsp?item=all"><button>Get all data</button></a></span>
						<br />
			</fieldset>
		</td>
		<td>
			<fieldset>
				<LEGEND ACCESSKEY=I>Student ID</LEGEND>
					<div class="label"> Choose student ID:</div>
					
					<select name="stdId" id="stdId">
					<option value="%">N/A</option>
<%
		List stuList = stuDoc.getRootElement().getChildren();
		Iterator iter = stuList.iterator();
		while (iter.hasNext()){
			Element element = (Element) iter.next();
			stdid_array.add(element.getChildText("stdid"));
			stuProp.setProperty(element.getChildText("stdid"), element.getChildText("name"));
%>
		<option value="<%= element.getChildText("stdid") %>"><%= element.getChildText("stdid") %></option>
<% 		}
%>
					</select>
					
					<span class="button"><input type="submit" value ="Get data" /></span>
			</fieldset>
		</td>
		<td>
			<fieldset>
				<LEGEND ACCESSKEY=I>Exercise ID</LEGEND>
					<div class="label"> Choose exercise ID:</div>
					
					<select name="exid" id="exid">
					<option value="%">N/A</option>
<%
		List exList = exDoc.getRootElement().getChildren();
		iter = exList.iterator();
		while (iter.hasNext()){
			Element element = (Element) iter.next();
			exProp.setProperty(element.getChildText("exid"), element.getChildText("problem"));
%>
		<option value="<%= element.getChildText("exid") %>"><%= element.getChildText("exid") %></option>
<% 		}
	}
	catch (JDOMException e) {} 
	catch (IOException e1) {}
%>
					</select>
					
					<span class="button"><input type="submit" value ="Get data" /></span>
			</fieldset>
		</td>
	</tr>
</table></center>
</form>
<fieldset>
<LEGEND ACCESSKEY=I>RESULT</LEGEND>
<div id='icontent' class = 'content'>
<div id="stats">
<div class="incontent" id="incontent">
<%
	String mode = request.getParameter("item");
	String studentId = request.getParameter("stdId");
	String exerciseId = request.getParameter("exid");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// Search in STUDENT ID ////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	if (studentId != null && !studentId.equals("%"))
	{
		File fxml = new File(feedbackPath + studentId + ".xml");
		String reportFileName = fxml.getPath();
		String reportFile = application.getRealPath(reportFileName);
		
		int count = 0;
		
		File f = new File(reportFile);
		if (!(f.exists() && f.isFile()))
		{
			out.println ("There are <b>" + count + "</b> results in student <b>" + studentId + " - " + stuProp.getProperty(studentId) + "</b><br/><br/>");
		}
		else
		{
%>
<table width="100%" border="1" cellpadding ="3" cellspacing="0">
	<tr>
		<th class="index" align="center">#</th>
		<th class="index" align="center">ExID</th>
		<th class="index" align="center">Problem</th>
		<th class="index" align="center">Student Code</th>
		<th class="index" align="center">Desired Result</th>
		<th class="index" align="center">Web Result</th>
		<th class="index" align="center">Comment</th>
	</tr>
<%
			try {
				SAXBuilder saxBuilder = new SAXBuilder();
				Document doc = saxBuilder.build(new File(reportFile));
				Element root = (Element) doc.getRootElement();
				List list = root.getChildren();
				Iterator iter = list.iterator();
				
				while (iter.hasNext())
				{
					Element element = (Element) iter.next();
					count++;
					String exid_print = element.getChildText("exid");
					String exProblem = exProp.getProperty(exid_print);
					String sub_exProblem = exProblem.substring(0, exProblem.indexOf(".") + 1);
%>
	<tr>
		<td align="center"><%= count%></td>
		<td align="center"><%= exid_print %></td>
		<td align="left"><%= sub_exProblem %></td>
		<td align="left"><pre><%= element.getChildText("stdcode") %></pre></td>
		<td align="center"><%= element.getChildText("desresult") %></td>
		<td align="center"><%= element.getChildText("webresult") %></td>
		<td align="left"><%= element.getChildText("cmnt") %></td>
	</tr>
<%
				}
				
				out.println ("There are <b>" + count + "</b> results in student <b>" + studentId + " - " + stuProp.getProperty(studentId) + "</b><br/><br/>");
			}
			catch (JDOMException e) {} 
			catch (IOException e1) {}
%>
</table>
<%
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// Search in EXERCISE ID ///////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	if (exerciseId != null && !exerciseId.equals("%"))
	{
		String exProblem = exProp.getProperty(exerciseId);
		exProblem = exProblem.substring(0, exProblem.indexOf(".")+1);
%>
There are <b><span id="noticefield"></span></b> results in exercise <b><%= exerciseId%> - <%= exProblem%></b><br/><br/>
<%
		int num = stdid_array.size();
		int i = 0;
		int count = 0;
		while(i<num)
		{
			String fileName = String.valueOf(stdid_array.get(i));
			File fxml = new File(feedbackPath + fileName + ".xml");
			String reportFileName = fxml.getPath();
			String reportFile = application.getRealPath(reportFileName);

			File f = new File(reportFile);
			if (f.exists() && f.isFile())
			{
				try {
					SAXBuilder saxBuilder = new SAXBuilder();
					Document doc = saxBuilder.build(new File(reportFile));
					Element root = (Element) doc.getRootElement();
					List list = root.getChildren();
					Iterator iter = list.iterator();
					
					while (iter.hasNext())
					{
						Element element = (Element) iter.next();
						String exid_print = element.getChildText("exid");
						if (exid_print.equals(exerciseId))
						{
							if (count == 0)
							{
%>
<table width="100%" border="1" cellpadding ="3" cellspacing="0">
	<tr>
		<th class="index" align="center">#</th>
		<th class="index" align="center">Student ID</th>
		<th class="index" align="center">Student Name</th>
		<th class="index" align="center">Student Code</th>
		<th class="index" align="center">Desired Result</th>
		<th class="index" align="center">Web Result</th>
		<th class="index" align="center">Comment</th>
	</tr>
<%							}
							count++;
							String studentName = stuProp.getProperty(fileName);
%>
	<tr>
		<td align="center"><%= count%></td>
		<td align="center"><%= fileName %></td>
		<td align="left"><%= studentName %></td>
		<td align="left"><pre><%= element.getChildText("stdcode") %></pre></td>
		<td align="center"><%= element.getChildText("desresult") %></td>
		<td align="center"><%= element.getChildText("webresult") %></td>
		<td align="left"><%= element.getChildText("cmnt") %></td>
	</tr>
<%
						}
					}
					
				}
				catch (JDOMException e) {} 
				catch (IOException e1) {}
				if (count == 0)
				{
%>
</table>
<%	
				}
			}
			i++;
		}
		out.println ("<script> noticefield.innerHTML = " + count + "; </script>");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// Search in ALL ///////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	if (mode != null && mode.equals("all"))
	{
%>
There are <b><span id="noticefield"></span></b> results in <b>all reports.</b><br/><br/>
<%
		int num = stdid_array.size();
		int i = 0;
		int count = 0;
		while(i<num)
		{
			String fileName = String.valueOf(stdid_array.get(i));
			File fxml = new File(feedbackPath + fileName + ".xml");
			String reportFileName = fxml.getPath();
			String reportFile = application.getRealPath(reportFileName);

			File f = new File(reportFile);
			if (f.exists() && f.isFile())
			{
				try {
					SAXBuilder saxBuilder = new SAXBuilder();
					Document doc = saxBuilder.build(new File(reportFile));
					Element root = (Element) doc.getRootElement();
					List list = root.getChildren();
					Iterator iter = list.iterator();
					
					while (iter.hasNext())
					{
						Element element = (Element) iter.next();
						if (count == 0)
						{
%>
<table width="100%" border="1" cellpadding ="3" cellspacing="0">
	<tr>
		<th class="index" align="center">#</th>
		<th class="index" align="center">Student ID</th>
		<th class="index" align="center">Student Name</th>
		<th class="index" align="center">Ex ID</th>
		<th class="index" align="center">Problem</th>
		<th class="index" align="center">Student Code</th>
		<th class="index" align="center">Desired Result</th>
		<th class="index" align="center">Web Result</th>
		<th class="index" align="center">Comment</th>
	</tr>
<%						}

						count++;
						String studentName = stuProp.getProperty(fileName);
						String exProblem = exProp.getProperty(element.getChildText("exid"));
						exProblem = exProblem.substring(0, exProblem.indexOf(".") + 1);
%>
	<tr>
		<td align="center"><%= count%></td>
		<td align="center"><%= fileName %></td>
		<td align="left"><%= studentName %></td>
		<td align="left"><%= element.getChildText("exid") %></td>
		<td align="left"><%= exProblem %></td>
		<td align="left"><pre><%= element.getChildText("stdcode") %></pre></td>
		<td align="center"><%= element.getChildText("desresult") %></td>
		<td align="center"><%= element.getChildText("webresult") %></td>
		<td align="left"><%= element.getChildText("cmnt") %></td>
	</tr>
<%						
					}
				}
				catch (JDOMException e) {} 
				catch (IOException e1) {}
			}
			if (count == 0)
			{
%>
</table>
<%	
			}
			i++;
		}
		out.println ("<script> noticefield.innerHTML = " + count + "; </script>");
	}
%>
</div>
</div>
</div>
</fieldset>
</body>
<%
	}
%>
</html>
