<div id="header">
	<div id="inheader">
		<h1>
		<a href="http://www.cse.hcmut.edu.vn">Faculty of Computer Science & Engineering University of Technology</a>
		</h1>
	</div>
</div>
<%
	Cookie[] allck = request.getCookies();
	Cookie myck = null;
	if (allck != null)
	{
		for (int i = 0; i < allck.length; i++)
		{
			if (allck[i].getName().equals("svgroup"))
			{
				myck = allck[i];
				break;
			}
		}
	}
%>
<div class="navigation">
	<ul id="navlist" class="links">
		<li>
			<a href="../index.jsp" target="_blank" >Home</a>
		</li>
		<li>
			<a href="http://www.cse.hcmut.edu.vn">CSE</a>
		</li>
		<li>
			<a href="index.jsp">Admin Panel</a>
		</li>
<%
	if (myck != null)
	{
		out.println ("<li>");
		out.println ("<a href='Logout.jsp'>Logout</a>");
		out.println ("</li>");
	}
	else
	{
		out.println ("<li>");
		out.println ("<a href='Login.jsp'>Login</a>");
		out.println ("</li>");
	}
	
%>
	</ul>
</div>
<h1 class="title"><b>ADMIN PANEL</b></h1>