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
		response.sendRedirect("ViewFeedback.jsp");
	}
	else
	{
		if (tempAuth[2].equals("teacher"))
		{
			response.sendRedirect("admin/403.jsp");
		}
		
		String act = (String) request.getParameter("actionVal");
		if (act != null && act.equals("addMess"))
		{
			String fbFileName = base_url + feedbackPath + tempAuth[0] + ".xml";
			String fbFile = application.getRealPath(fbFileName);
			File f = new File(fbFile);
			int id_int = 0;
			String code_submit = request.getParameter("stdcode");
			code_submit = code_submit.replaceAll ("&", "&amp;");
			code_submit = code_submit.replaceAll ("[ ]*<[ ]*", " &lt; ");
			code_submit = code_submit.replaceAll ("[ ]*&lt; =[ ]*", " &lt;= ");
			code_submit = code_submit.replaceAll ("&lt;= >", "&lt;=>");
			code_submit = code_submit.replaceAll ("\\n\\t* *\\r", "");
			
			String cmnt_submit = new String(request.getParameter("cmnt").getBytes("ISO-8859-1"), "UTF-8");
			cmnt_submit = cmnt_submit.replaceAll ("&", "&amp;");
			cmnt_submit = cmnt_submit.replaceAll ("[ ]*<[ ]*", " &lt; ");
			cmnt_submit = cmnt_submit.replaceAll ("[ ]*&lt; =[ ]*", " &lt;= ");
			cmnt_submit = cmnt_submit.replaceAll ("&lt;= >", "&lt;=>");
			cmnt_submit = cmnt_submit.replaceAll ("\\n\\t* *\\r", "");
			
			try {
				Document doc;
				Element root;
				if (f.exists() && f.isFile())
				{
					SAXBuilder saxBuilder = new SAXBuilder();
					doc = saxBuilder.build(new File(fbFile));
					root = (Element) doc.getRootElement();
					List list = root.getChildren();
					Iterator iter = list.iterator();
					while (iter.hasNext()){
						Element element = (Element) iter.next();
						id_int = Integer.parseInt(element.getChildText("rpid"));
					}
				}
				else
				{
					root = new Element("feedback");
					doc = new Document(root); 
				}
				id_int++;
				Element newChild = new Element("report");
				Element rpidNode = new Element("rpid");
				rpidNode.setText(String.valueOf(id_int));
				newChild.addContent(rpidNode);
				Element exidNode = new Element("exid");
				exidNode.setText(request.getParameter("exid"));
				newChild.addContent(exidNode);
				Element codeNode = new Element("stdcode");
				codeNode.setText(code_submit);
				newChild.addContent(codeNode);
				Element desNode = new Element("desresult");
				desNode.setText(request.getParameter("desresult"));
				newChild.addContent(desNode);
				Element webNode = new Element("webresult");
				webNode.setText(request.getParameter("webresult"));
				newChild.addContent(webNode);
				Element cmntNode = new Element("cmnt");
				cmntNode.setText(cmnt_submit);
				newChild.addContent(cmntNode);	
				root.addContent(newChild);

				FileOutputStream fileOS = new FileOutputStream(fbFile);
				OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				response.sendRedirect("ViewFeedback.jsp");
			}
			catch (JDOMException e) {} 
			catch (IOException e1) {}
		}
%>
<script type="text/javascript" language="javascript" src="js_css/script.js"></script>
</head>
<body>

<h2>Add feedback</h2>
* PLEASE WRITE YOUR FEEDBACK IN ENGLISH OR IN VIETNAMESE WITHOUT SIGNS. *
<br /><br />

<form action="AddFeedback.jsp" name="addMessage" id="addMessage" method="post" onSubmit="formValidate();return false;">
<input name="actionVal" type="hidden" value="addMess">
<table border="1" width="100%" cellpadding ="5">
<th colspan="2" align="center"><h3><font color="#FF0000">Add new report</font></h3></th>
<tr><td width="30%">Exercise ID <span class="star">*</span> :</td>
<td width="70%">
	<select name="exid" id="exid">
		<option>N/A</option>
<%		String id_str = "";
		String exFileName = base_url + xmlExercise;
		String exFile = application.getRealPath(exFileName);
		
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(exFile));
			Element root = (Element) doc.getRootElement();
			List list = root.getChildren();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				Element element = (Element) iter.next();
				id_str = element.getChildText("exid");
				out.println("<option>"+id_str+"</option>");
			}
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
%>
	</select>
</td></tr>
<tr><td width="30%">Student code <span class="star">*</span> : </td><td width="70%"><textarea id ="stdcode" name="stdcode" cols="80" rows="8"></textarea></td></tr>
<tr><td width="30%">Desired Result <span class="star">*</span> : </td>
<td width="70%">
<input type=radio name="desresult" value="valid">Valid &nbsp;&nbsp;
<input type=radio name="desresult" value="invalid">Invalid &nbsp;&nbsp;
<input type=radio name="desresult" value="syntax error">Syntax error
</td></tr>
<tr><td width="30%">Web Result <span class="star">*</span> : </td>
<td width="70%">
<input type=radio name="webresult" value="valid">Valid &nbsp;&nbsp;
<input type=radio name="webresult" value="invalid">Invalid &nbsp;&nbsp;
<input type=radio name="webresult" value="unknown">Unknown &nbsp;&nbsp;
<input type=radio name="webresult" value="syntax error">Syntax error &nbsp;&nbsp;
<input type=radio name="webresult" value="other">Others
</td></tr>
<tr><td width="30%">Comment : </td><td width="70%"><textarea id ="cmnt" name="cmnt" cols="80" rows="8"></textarea></td></tr>
</table>
<table border="0" width="100%" cellpadding ="5">
<tr><td colspan="2" width="30%" align="left">
Required fields are denoted by<span class="star"> *</span>
</td></tr>
<tr><td colspan="2" width="30%" align="center">
<span class="button"><input type="submit" name="submit" value="Submit"></span>
<a class="button" href="" onclick="history.go(-1); return false;"><span>Cancel</span></a>
</td></tr>
</table>
</form>

<br /><br /><br />
<%@ include file="template/footer.htm" %>

</body>
</html>
<%	}	%>
