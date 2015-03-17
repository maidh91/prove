<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.util.regex.*" %>
<%@ page import="java.servlet.http.*"%>
<%@include file="functions.jsp"%>
<%@include file="template/header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Code verification</title>
		<link rel="stylesheet" type="text/css" href="./js_css/style.css" />
		<link rel="stylesheet" type="text/css" href="./js_css/round-button.css" />
		<link rel="shortcut icon" href="./js_css/bannerImg/bklogo.ico">
		<%
			String id = request.getParameter("id"); //get problem's id number if exists
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
	
			if (tempAuth == null || id == null)
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
	<%!
		String prototype = "";
		public int countSubString(String text, String search)
		{
			int count;
			Matcher m = Pattern.compile(search).matcher(text);				
			for (count = 0; m.find(); count++);
			return count;
		}
	%>
	<% 
		String filename = base_url + xmlExercise;
		String file = application.getRealPath(filename);
		String problem = null;
		String prototype = null;
		String teacher_solution = null;
		String var_init = null;
		Element element = null;
		int isExercise = 0;
	
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(file));
			Element root = (Element) doc.getRootElement();
			List list = root.getChildren();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				element = (Element) iter.next();
				String id_str = element.getChildText("exid");
				if (id.equals(id_str)) 
				{
					isExercise = 1;
					break;
				}
			}
			if (isExercise == 1)
			{
				problem = element.getChildText("problem");
				problem = problem.replaceAll ("&amp;", "&");
				problem = problem.replaceAll ("&lt;", "<");
			
				prototype = element.getChildText("prototype");
				prototype = prototype.replaceAll ("&amp;", "&");
				prototype = prototype.replaceAll ("&lt;", "<");
			
				teacher_solution = element.getChildText("solution");
				teacher_solution = teacher_solution.replaceAll ("&amp;", "&");
				teacher_solution = teacher_solution.replaceAll ("&lt;", "<");
			
				var_init = element.getChildText("varinit");
				var_init = var_init.replaceAll ("&amp;", "&");
				var_init = var_init.replaceAll ("&lt;", "<");
			}
			else
			{
	%>
				<jsp:forward page="index.jsp"/>
	<%			
			}
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
	%>

	<body onload="loadTextArea('count');">
		<center>
			<h2>Exercise ID : <%= id%></h2>
		</center>
		<div class="bl">
			<div class="br">
				<div class="tl">
	  				<div class="tr">
						Problem
					</div>
				</div>
			</div>
		</div>
		<p>
			<table class="text" width="100%" height="80" border="1" cellpadding ="3" cellspacing="0">
				<tr>
					<td valign=top><pre><%= problem%></td>
				</tr>
			</table>
		</p>
		<br />
		<table width="60%" border="0" cellpadding ="3" cellspacing="0">
			<tr>
				<td>
					<div id="errorVerifyCode">
						<div class="bl">
							<div class="br">
								<div class="tl">
									<a name="stdcode"/>
				  					<div class="tr">
										Student code
									</div>
								</div>
							</div>
						</div>
				</td>
			</tr>
		</table>
			<div id="codefield">
				<p>
					<form name="formCode" method="post" action="Student.jsp?id=<%= id%>#verifycode">
						<table width="100%" border="1" cellpadding ="3" cellspacing="0">
							<tr>
								<td style="cursor:default">
									<% 
										String formatCode = prototype;
										int first, end;
										while (true) {
											first = formatCode.indexOf("#");
											end = formatCode.indexOf("\n");
											if (first == -1) {
												break;
											}
											String prag = formatCode.substring(first, end+1);
											formatCode = formatCode.replace(prag, "");
										}
										

										while (true) {
											first = formatCode.indexOf("/*");
											end = formatCode.indexOf("*/");
											if (first == -1 || end == -1)
												break;
											String comment = formatCode.substring(first, end+4);
											formatCode = formatCode.replace(comment, "");
										}	

										int count_todo = countSubString(formatCode, "//TODO");

										int i = 0;
										String fName = "";
										String code = "";
										String cpCode = formatCode;

										for (i = 0; i < count_todo; i++) {	
											fName = "code" + String.valueOf(i);
											code = request.getParameter(fName);
											if (code != null)
												formatCode = formatCode.replaceFirst ("//TODO", "<textarea class='code' onfocus='focusTextArea(this);' onkeydown='dynamicTextArea(0,this,event);'onkeyup='dynamicTextArea(1,this,event);' name="+fName+" cols='120' rows='1' id="+fName+" >" + code + "</textarea>");
											else
												formatCode = formatCode.replaceFirst ("//TODO", "<textarea class='code' onfocus='focusTextArea(this);' onkeydown='dynamicTextArea(0,this,event);' onkeyup='dynamicTextArea(1,this,event);' name="+fName+" cols='120' rows='1' id="+fName+" >// Enter your code here.</textarea>");	
										}
									%>
									<pre id="stCode"><%= formatCode%></pre>
									<input type="hidden" id="count" name="count" value="<%= count_todo%>" />
									<input type="hidden" id="actVal" name="actVal" value="submitted" />
								</td>
							</tr>
						</table>
						<br/>
						<br/>
						<input type="hidden" name="id" id="id" value="<%= id%>" />
						<a class="button" onclick="document.getElementById('actVal').value='ctge_dsfl'; document.formCode.submit(); return false;">
							<span>Solve by Fault Localization</span>
						</a>
						<a class="button" onclick="document.getElementById('actVal').value='grouptesting'; document.formCode.submit(); return false;">
							<span>Solve by Group Testing</span>
						</a>
						<a class="button" onclick="document.formCode.submit(); return false;">
							<span>Solve by Model Checking</span>
						</a>
						<a class="button" href="">
							<span>Reset</span>
						</a>
						<%
							String val = request.getParameter("actVal");
							if (val != null && val.equals("submitted")) {
								%>
								<a class="button" onclick="showResultField();">
									<span>See result</span>
								</a>
								<%
							} else if (val != null && val.equals("ctge_dsfl")) {
								%>
								<a class="button" onclick="showResultField();">
									<span>See result</span>
								</a>
								<%
							} else if (val != null && val.equals("grouptesting")) {
								%>
								<a class="button" onclick="showResultField();">
									<span>See result</span>
								</a>
								<%
							}
						%>
					</div>

					<div id="verifyresult" style="display: none">
						<a name="verifycode">
							<%
							if (val != null && val.equals("submitted")) {
							%>	
							<%@include file="verifyCode.jsp"%>
								<script type="text/javascript" language="javascript">
								showResultField();
								</script>
							<%
							} else if (val != null && val.equals("ctge_dsfl")) {
							%>	
							<%@include file="testCode.jsp"%>
								<script type="text/javascript" language="javascript">
								showResultField();
								</script>
							<%
							} else if (val != null && val.equals("grouptesting")) {
							%>	
							<%@include file="GroupTestingCode.jsp"%>
								<script type="text/javascript" language="javascript">
								showResultField();
								</script>
							<%
							}
							%>
							<br/>
							<br/>
							<br/>
						<a class="button" onclick="showCodeField(); ">
							<span>Go back</span>
						</a>
					</div>
			</div>
					</form>
				</p>
		<br />
		<br />
		<br />
		<%@ include file="template/footer.htm" %>
	</body>
</html>
<%	}	%>
