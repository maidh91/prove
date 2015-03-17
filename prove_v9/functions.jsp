<%@page import="java.io.*"  %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.lang.String" %>
<%@page import="java.nio.charset.Charset" %>
<%@page import="java.security.*" %>
<%@page import="java.servlet.http.*"%>
<%@page import="java.servlet.ServletContext .*"%>
<%@page import="org.jdom.*" %>
<%@page import="org.jdom.input.SAXBuilder" %>
<%@page import="org.jdom.output.XMLOutputter" %>
<%@page import="org.jdom.output.Format" %>
<%@include file="configs.jsp" %>

<%
	String[] arrayUrl = getUrl(request.getServletPath());
	String base_url = arrayUrl[0];
%>
<%!	
	public String md5(String code)
	{      
		String result = "";
		try {
			MessageDigest mymd5 = MessageDigest.getInstance("MD5");
			
			mymd5.update(code.getBytes());
			
			byte[] digest = mymd5.digest();
			StringBuffer hexStr = new StringBuffer();
			
			for (int i = 0; i < digest.length; i++) {
				code = Integer.toHexString(0xFF & digest[i]);
				if (code.length() < 2) {
					code = "0" + code;
				}
		
				hexStr.append(code);
			}
			result = hexStr.toString();
		}
		catch (NoSuchAlgorithmException e) {}
		return result;
	}
	
	// authentication 
	// format: username~password~role
	// if wrong return null
	public String splitAuth (String strCookie)
	{
		String[] temp = strCookie.split("~");
		String specFile = "";
		String tabName = "";
		String role = temp[2];
		if (role.equals("student"))
		{
			specFile = "tester.xml";
		}
		else
		{
			specFile = "users.xml";
		}
		return specFile;
	}
	
	public String[] checkAuth (String url, String strCookie)
	{
		String[] temp = strCookie.split("~");
		String tabName = "";
		if (temp[2].equals("student"))
		{
			tabName = "stdid";
		}
		else
		{
			tabName = "username";
		}
		int i = 0;
		int valid = 0;
		String username, pwd, rl;
		String user = temp[0];
		String password = temp[1];
		String role = temp[2];
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(url));
			Element e = doc.getRootElement();
			List lst = e.getChildren();
			for (i = 0; i < lst.size(); i++)
			{
				Element data = (Element)lst.get(i);
				username = data.getChildText(tabName);
				if (user.equals(username))
				{
					pwd = data.getChildText("pwd");
					if (pwd.equals(password))
					{
						if (!temp[2].equals("student"))
						{
							rl = data.getChildText("role");
							if (!rl.equals(role))
							{
								temp[2] = rl;
							}
						}
						valid = 1;
					}		
				}
			}
		} 
		catch (JDOMException e) {} 
		catch (IOException e1) {}

		if (valid == 1)
		{
			return temp;
		}
		else
		{
			return null;
		}
	}
	
	public int checkXML(String url, String stid, String passwd)
	{      
		int i = 0;
		String stdid, pwd;
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(url));
			Element e = doc.getRootElement();
			List lst = e.getChildren();
			for (i = 0; i < lst.size(); i++)
			{
				Element data = (Element)lst.get(i);
				stdid = data.getChildText("stdid");
				if (stid.equals(stdid))
				{
					pwd = data.getChildText("pwd");
					if (!pwd.equals(stdid))
					{
						if (pwd.equals(md5(passwd)))
						{
							return 3;
						}
						else
							return 2;
					}
					else
						return 1;
				}
			}
			return 0;
		} 
		catch (JDOMException e) {} 
		catch (IOException e1) {}
		return -1;
	}
	public int registerXML(String url, String stid, String opwd, String npwd, int mode)
	{      
		int i = 0;
		String stdid, pwd;
		if (npwd.equals(stid))
		{
			return -1;
		}
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(url));
			Element e = doc.getRootElement();
			List lst = e.getChildren();
			for (i = 0; i < lst.size(); i++)
			{
				Element data = (Element)lst.get(i);
				stdid = data.getChildText("stdid");
				if (stid.equals(stdid))
				{
					pwd = data.getChildText("pwd");
					if ((pwd.equals(stdid) && pwd.equals(opwd) && mode == 0) || (pwd.equals(md5(opwd)) && mode == 1))
					{
						data.getChild("pwd").setText(md5(npwd));
						 
						FileWriter writer = new FileWriter(url);
						BufferedWriter bufferFile = new BufferedWriter(writer);
						XMLOutputter outputter = new XMLOutputter();
						outputter.setFormat(Format.getPrettyFormat());
						outputter.output(doc, writer);
						bufferFile.close(); 
						
						return 3;
					}
					else
						return 1;
				}
			}
			return 0;
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
		return -1;
	}
	public String[] getUrl(String servletPath)
	{		
		String base_url, fileRequest;
		fileRequest = servletPath.substring(servletPath.lastIndexOf('/')+1,servletPath.length());
		base_url = servletPath.replace(fileRequest,"");
		
		String[] array = new String[2];
		array[0] = base_url;
		array[1] = fileRequest;
		return array;
	}

	public String parseTestMatrix(String cmd, String fileErrorCode) 
	{
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(cmd) ;
			pr.waitFor() ;
		
		} catch (Exception e) {}
		
		File codeErrorFile = new File(fileErrorCode);
		FileInputStream fisErrorCode = null;
		BufferedInputStream bisErrorCode = null;
		DataInputStream disErrorCode = null;
		String answer = "<br /><b><font color='#FF0000'>INVALID - Your C code is wrong with our verification.</font></b><br />";

		try {
			fisErrorCode = new FileInputStream(codeErrorFile);
			bisErrorCode = new BufferedInputStream(fisErrorCode);
			disErrorCode = new DataInputStream(bisErrorCode);

			String temp;
			StringBuilder error_code = new StringBuilder();
			while (disErrorCode.available() != 0) 
			{
				temp = disErrorCode.readLine();
				error_code.append(temp + "\n");
			}

			if(error_code.toString().length() == 0)
				answer = "nothing";
			else
				answer += error_code.toString();
			fisErrorCode.close();
			bisErrorCode.close();
			disErrorCode.close();
			
			return answer;
		}
		catch (FileNotFoundException e) {}
		catch (IOException ex) {}
		
		return ("nothing");
	}

	public int parseWhy(String cmd, String inputFile, String stCode, String outputFile) 
	{
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(cmd) ;
			pr.waitFor() ;
		
		} catch (Exception e) {}
	
		File file = new File(inputFile);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		String temp;
		int startIndex, endIndex;
		String total = "0";
		String valid = "1";
		int unknown = 1;
		int result_code = 0;
		
		File f = new File(outputFile);
		
		int id_int = 0;
		
		try {
			
			Document doc;
			Element root;
							
			if (f.exists() && f.isFile())
			{
				SAXBuilder saxBuilder = new SAXBuilder();
				doc = saxBuilder.build(new File(outputFile));
				root = (Element) doc.getRootElement();
				List list = root.getChildren();
				Iterator iter = list.iterator();
				while (iter.hasNext()){
					Element element = (Element) iter.next();
					id_int = Integer.parseInt(element.getChildText("logid"));
				}
			}
			else
			{
				root = new Element("logs");
				doc = new Document(root); 
			}
			id_int++; 
			
			Element newNode = new Element("solution");
			Element logidNode = new Element("logid");
			logidNode.setText(String.valueOf(id_int));
			newNode.addContent(logidNode);
			Element codeNode = new Element("code");
			codeNode.setText(stCode);
			newNode.addContent(codeNode);
			
			try
			{
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);

				while (dis.available() != 0) 
				{
					temp = dis.readLine();
					
					if (temp.startsWith ("syntaxerror"))
					{
						Element resultNode = new Element("result");
						resultNode.setText("SYNTAX ERROR");
						newNode.addContent(resultNode);
						return (3);
					}
					else if (temp.startsWith ("Running"))
					{
						endIndex = temp.indexOf("on") - 1;
						Element proverNode = new Element("prover");
						proverNode.setText(temp.substring(8, endIndex));
						newNode.addContent(proverNode);
					}
					else if (temp.startsWith ("total"))
					{
						unknown = 0;
						total = temp.substring(12, temp.length());
						Element totalNode = new Element("total");
						totalNode.setText(total);
						newNode.addContent(totalNode);
						
						temp = dis.readLine();
						endIndex = temp.indexOf("(") - 1;
						valid = temp.substring(12, endIndex);
						Element validNode = new Element("valid");
						validNode.setText(valid);
						newNode.addContent(validNode);
					
						temp = dis.readLine();
						Element invalidNode = new Element("invalid");
						invalidNode.setText(temp.substring(12, endIndex));
						newNode.addContent(invalidNode);
						temp = dis.readLine();
						Element unknownNode = new Element("unknown");
						unknownNode.setText(temp.substring(12, endIndex));
						newNode.addContent(unknownNode);
						temp = dis.readLine();
						Element timeoutNode = new Element("timeout");
						timeoutNode.setText(temp.substring(12, endIndex));
						newNode.addContent(timeoutNode);
						temp = dis.readLine();
						Element failureNode = new Element("failure");
						failureNode.setText(temp.substring(12, endIndex));
						newNode.addContent(failureNode);
						
						break;
					}
				}

				fis.close();
				bis.close();
				dis.close();

			}
			catch (FileNotFoundException e)
			{
				Element resultNode = new Element("result");
				resultNode.setText("NOT RUN CMD");
				newNode.addContent(resultNode);
				result_code = 4;
			}			
			catch (IOException e) {}
			
			if (result_code != 4)
			{
				if (unknown == 1)
				{
					Element resultNode = new Element("result");
					resultNode.setText("UNKNOWN");
					newNode.addContent(resultNode);
					result_code = 5;
				}
				else if (total.equals(valid))
				{
					result_code = 1;
					Element resultNode = new Element("result");
					resultNode.setText("TRUE");
					newNode.addContent(resultNode);
				}
				else
				{
					result_code = 2;
					Element resultNode = new Element("result");
					resultNode.setText("FALSE");
					newNode.addContent(resultNode);
				}
			}
			
			root.addContent(newNode);
			
			FileWriter writer = new FileWriter(outputFile);
			BufferedWriter bufferFile = new BufferedWriter(writer);
			XMLOutputter outputter = new XMLOutputter();
			outputter.setFormat(Format.getPrettyFormat());
			outputter.output(doc, writer);
			bufferFile.close();
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
		
		return (result_code);

	}
	
	public String modelChecking(String cmdMC, String exec_htm, String answer, String stdCode) 
	{
		// ex : .../run_model_linux.sh .../4_1380007072066 .../modelchecking
		// output : .../4_1380007072066/execution
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(cmdMC) ;
			pr.waitFor() ;
		
		} catch (Exception e) {}
		
		File file_exec = new File(exec_htm);
		
		if (!file_exec.exists() || !file_exec.isFile())
		{
			answer = "nothing";
		}
		else
		{
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;
			
			try
			{
				fis = new FileInputStream(file_exec);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
				answer = "<br /><b><font color='#FF0000'>INVALID - Your C code is wrong with our verification.</font></b><br />";
				
				while (dis.available() != 0) 
				{
					answer += dis.readLine() + "\n";
				}
				
				fis.close();
				bis.close();
				dis.close();
			}
			catch (FileNotFoundException e) {}			
			catch (IOException e) {}
		}
		
		//answer += " !!! DEBUG modelchecking !!! " + cmdMC + " !!! exec " + exec_htm;
		return answer;
	}

	public String algorithmChecking(String cmd, String inputFile, String stCode, String outputFile) 
	{
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(cmd) ;
			pr.waitFor() ;
		
		} catch (Exception e) {}
	
		File file = new File(inputFile);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		String temp = "";
		
		File f = new File(outputFile);
		
		int id_int = 0;
		
		try {
			
			Document doc;
			Element root;
							
			if (f.exists() && f.isFile())
			{
				SAXBuilder saxBuilder = new SAXBuilder();
				doc = saxBuilder.build(new File(outputFile));
				root = (Element) doc.getRootElement();
				List list = root.getChildren();
				Iterator iter = list.iterator();
				while (iter.hasNext()){
					Element element = (Element) iter.next();
					id_int = Integer.parseInt(element.getChildText("logid"));
				}
			}
			else
			{
				root = new Element("logs");
				doc = new Document(root); 
			}
			id_int++; 
			
			Element newNode = new Element("solution");
			Element logidNode = new Element("logid");
			logidNode.setText(String.valueOf(id_int));
			newNode.addContent(logidNode);
			Element codeNode = new Element("code");
			codeNode.setText(stCode);
			newNode.addContent(codeNode);
			
			try
			{
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);

				if (dis.available() != 0) 
				{
					temp = dis.readLine();
					Element resultNode = new Element("result");
					if (!temp.equals("False"))
					{
						resultNode.setText(temp);
						temp = "Your " + temp + " algorithm is right.";
					}	
					else
					{
						resultNode.setText("FALSE");
						temp = "Your algorithm is wrong in our verification.";
					}
					newNode.addContent(resultNode);
				}

				fis.close();
				bis.close();
				dis.close();

			}
			catch (FileNotFoundException e)
			{
				Element resultNode = new Element("result");
				resultNode.setText("NOT RUN CMD");
				newNode.addContent(resultNode);
				temp = "ERROR";
			}			
			catch (IOException e) {}
			
			root.addContent(newNode);
			
			FileWriter writer = new FileWriter(outputFile);
			BufferedWriter bufferFile = new BufferedWriter(writer);
			XMLOutputter outputter = new XMLOutputter();
			outputter.setFormat(Format.getPrettyFormat());
			outputter.output(doc, writer);
			bufferFile.close();
		}
		catch (JDOMException e) {} 
		catch (IOException e1) {}
		
		return (temp);
	}
	
	public int deleteFolder(String cmdDel) 
	{
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(cmdDel) ;
			pr.waitFor() ;
		
		} catch (Exception e) {}
		
		return 1;
	}
	
	public String updateSVN(String cmdUpdate, String result) 
	{
		String answer = "";
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(cmdUpdate) ;
			pr.waitFor();
			answer += "done script<br/>";
		} catch (Exception e) {
			answer += e + "error script<br/>";
		}
		
		File file_exec = new File(result);
		if (!file_exec.exists() || !file_exec.isFile())
		{
			answer += "nothing<br/>";
		}
		else
		{
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;
			try
			{
				fis = new FileInputStream(file_exec);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);				
				while (dis.available() != 0) 
				{
					answer += dis.readLine() + "<br/>";
				}
				fis.close();
				bis.close();
				dis.close();
			}
			catch (FileNotFoundException e) {}			
			catch (IOException e) {}
		}
		
		return answer;
	}
	
	public int runScript(String path) 
	{
		Runtime run = Runtime.getRuntime() ;
		try {
			Process pr = run.exec(path) ;
			pr.waitFor();
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
%>
