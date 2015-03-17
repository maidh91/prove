<div id="header">
	<div id="inheader">
		<h1>
		<a href="http://www.cse.hcmut.edu.vn">Faculty of Computer Science & Engineering University of Technology</a>
		</h1>
		<div class="banner">
		  <a target="_blank" href="http://www.logigear.vn/" title="Sponsored by LogiGear">
		    <img alt="Sponsored by LogiGear" src="js_css/bannerImg/logigear.jpg" />
		  </a>
		</div>
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
			<a href="index.jsp">Home</a>
		</li>
		<li>
			<a href="http://www.cse.hcmut.edu.vn">CSE</a>
		</li>
		<li>
			<a href="mailto:thuanle@cse.hcmut.edu.vn?subject=[Prove Site]">Contact</a>
		</li>
<%
	if (myck != null)
	{
		out.println ("<li>");
		out.println ("<a href='ViewFeedback.jsp'>Feedback</a>");
		out.println ("</li>");
	}
%>
		<li>
			<a style="cursor: pointer" onclick="window.open('template/about.htm','About','resizable=1,height=500,width=500')">About</a>
		</li>
		<li>
			<a style="cursor: pointer" onClick="window.open('template/help.htm','Help','resizable=1,height=500,width=800')">Help</a>
		</li>
		<li>
<%
	if (myck != null)
	{
		// Mai H. Dinh - 15/10/2013
		// remove login
		//out.println ("<a href='ChangePassword.jsp'>Change Password</a>");
		//out.println ("<a href='Logout.jsp'>Logout</a>");
	}
	else 
	{
		out.println ("<a href='Register.jsp'>Register</a>");
		out.println ("<a href='Login.jsp'>Login</a>");
	}
%>
		</li>
	</ul>
</div>
<h1 class="title">SOFTWARE VERIFICATION</h1>
