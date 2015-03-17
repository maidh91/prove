<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Software verification</title>
<%@include file="functions.jsp"%>
</head>
<body>
<%
String svn_script = getServletContext().getRealPath(base_url + "shellscript/./svn_update.sh");
String svn_home = getServletContext().getRealPath(base_url);
String svn_result = getServletContext().getRealPath(base_url + "shellscript/svn_result");
out.println(updateSVN(svn_script + " " + svn_home + " " + svn_result, svn_result));
%>
</body>
</html>
