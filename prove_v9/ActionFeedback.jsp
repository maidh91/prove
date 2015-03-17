<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@ page import="java.servlet.http.*"%>
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
		
		String fbFileName = base_url + feedbackPath + tempAuth[0] + ".xml";
		String fbFile = application.getRealPath(fbFileName);
		String id = request.getParameter("id");
		String actVal = request.getParameter("do");
		String act_submit = request.getParameter("actionVal");
		String report_id = null;
		String cur_exid = null;
		String cur_desresult = null;
		String cur_webresult = null;
		String cur_stdcode = null;
		String cur_cmnt = null;
		
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(fbFile));
			Element root = (Element) doc.getRootElement();
			List list = root.getChildren();
			Iterator iter = list.iterator();
			Element data = null;
			while (iter.hasNext()){
				data = (Element) iter.next();
				report_id = data.getChildText("rpid");
				if (report_id.equals(id)) break;
			}
			
			if (actVal != null && actVal.equals("delete"))
			{
				root.removeContent(data);
				
				FileOutputStream fileOS = new FileOutputStream(fbFile);
				OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				response.sendRedirect("ViewFeedback.jsp");
			}	
			else if (act_submit != null && act_submit.equals("submitted"))
			{
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
			
				data.getChild("exid").setText(request.getParameter("exid"));
				data.getChild("stdcode").setText(code_submit);
				data.getChild("desresult").setText(request.getParameter("desresult"));
				data.getChild("webresult").setText(request.getParameter("webresult"));
				data.getChild("cmnt").setText(cmnt_submit);
				
				FileOutputStream fileOS = new FileOutputStream(fbFile);
				OutputStreamWriter writer = new OutputStreamWriter(fileOS, "UTF-8");
				BufferedWriter bufferFile = new BufferedWriter(writer);
				XMLOutputter outputter = new XMLOutputter();
				outputter.setFormat(Format.getPrettyFormat());
				outputter.output(doc, writer);
				bufferFile.close();
				
				response.sendRedirect("ViewFeedback.jsp");
			}
			else
			{
				cur_exid = data.getChildText("exid");
				cur_desresult = data.getChildText("desresult");
				cur_webresult = data.getChildText("webresult");
				cur_stdcode = data.getChildText("stdcode");
				cur_stdcode = cur_stdcode.replaceAll ("&amp;", "&");
				cur_stdcode = cur_stdcode.replaceAll ("&lt;", "<");
				cur_cmnt = data.getChildText("cmnt");
				cur_cmnt = cur_cmnt.replaceAll ("&amp;", "&");
				cur_cmnt = cur_cmnt.replaceAll ("&lt;", "<");
			}
		
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
		
		String exFileName = base_url + xmlExercise;
		String exFile = application.getRealPath(exFileName);
		int total = 0;
		
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(exFile));
			Element root = (Element) doc.getRootElement();
			List list = root.getChildren();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				Element element = (Element) iter.next();
				total++;
			}
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
%>
<%@include file="template/header.jsp" %><br />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Feedback</title>
<link rel="stylesheet" type="text/css" href="./js_css/style.css" />
<link rel="stylesheet" type="text/css" href="./js_css/round-button.css" />
<link rel="shortcut icon" href="./js_css/bannerImg/bklogo.ico">
<script type="text/javascript" language="javascript" src="js_css/script.js"></script>
</head>
<body onLoad="load_radio_button('<%= cur_desresult%>', '<%= cur_webresult%>', '<%= cur_exid%>', '<%= total%>');">

<h2>Edit feedback</h2>
<br />

<form action="ActionFeedback.jsp" name="editMessage" id="editMessage" method="post" onSubmit="formValidate();return false;">
<input name="id" type="hidden" value="<%= id%>">
<input type="hidden" name="actionVal" value="submitted" />
<table border="1" width="100%" cellpadding ="5">
<th colspan="2" align="center"><h3><font color="#FF0000">Edit a report</font></h3></th>
<tr><td width="30%">Exercise ID <span class="star">*</span> :</td>
<td width="70%">
	<select name="exid" id="exid">
		<option>N/A</option>
<%		String id_str = "";
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
<tr><td width="30%">Student code <span class="star">*</span> : </td><td width="70%"><textarea id ="stdcode" name="stdcode" cols="80" rows="8"><%= cur_stdcode%></textarea></td></tr>
<tr><td width="30%">Desired Result <span class="star">*</span> : </td>
<td width="70%">
<input type=radio name="desresult" id="desresult" value="valid">Valid &nbsp;&nbsp;
<input type=radio name="desresult" id="desresult" value="invalid">Invalid &nbsp;&nbsp;
<input type=radio name="desresult" id="desresult" value="syntax error">Syntax error
</td></tr>
<tr><td width="30%">Web Result <span class="star">*</span> : </td>
<td width="70%">
<input type=radio name="webresult" id="webresult" value="valid">Valid &nbsp;&nbsp;
<input type=radio name="webresult" id="webresult" value="invalid">Invalid &nbsp;&nbsp;
<input type=radio name="webresult" id="webresult" value="unknown">Unknown &nbsp;&nbsp;
<input type=radio name="webresult" id="webresult" value="syntax error">Syntax error &nbsp;&nbsp;
<input type=radio name="webresult" id="webresult" value="other">Others
</td></tr>
<tr><td width="30%">Comment : </td><td width="70%"><textarea id ="cmnt" name="cmnt" cols="80" rows="8"><%= cur_cmnt%></textarea></td></tr>
</table>
<table border="0" width="100%" cellpadding ="5">
<tr><td colspan="2" width="30%" align="left">
Required fields are denoted by<span class="star"> *</span>
</td></tr>
<tr><td colspan="2" width="30%" align="center">
<span class="button"><input type="submit" name="submit" value="Update"></span>
<a class="button" href="" onclick="history.go(-1); return false;"><span>Cancel</span></a>
</td></tr>
</table>
</form>

<br /><br /><br />
<%@ include file="template/footer.htm" %>

</body>
</html>
<%	}	%>