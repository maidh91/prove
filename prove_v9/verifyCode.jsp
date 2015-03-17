<%
	String info_1 = "VALID - Your C code is right with our verification."; 	// WHY verify code to be right
	String info_2 = "INVALID - Your C code is wrong with our verification." ;		// WHY verify code to be wrong
	String info_3 = "SYNTAX ERROR - Your C code has syntax errors." ;		// Caduceus notice syntax error
	String info_4 = "RUNTIME ERROR - Internal server error." ;		// Command lines can't run
	String info_5 = "UNKNOWN - We don't know your C code is right or wrong." ; 	// Wrong at pre, post, or may be code; so WHY can't run
	
	String submitCode = "";
	String answer = "";
	
	String stdCode = prototype;
	int id_num = Integer.parseInt(id); //id_num is the id number of the problem
	
	
	int count = Integer.parseInt(request.getParameter("count"));
	int j = 0;
	for (j = 0; j < count; j++)
	{
		submitCode = request.getParameter("code" + String.valueOf(j));
		stdCode = stdCode.replaceFirst ("//TODO", submitCode);
	}
	
	String stdCode2 = stdCode;
	
	int strFirst = stdCode2.indexOf("#");
	int strEnd = stdCode2.indexOf("\n");
	if (strFirst != -1)
	{
		String strPrag = stdCode2.substring(strFirst, strEnd);
		stdCode2 = stdCode2.replace(strPrag, "");
	}
	
	while (true)
	{
		strFirst = stdCode2.indexOf("/*");
		strEnd = stdCode2.indexOf("*/");
		if (strFirst == -1 || strEnd == -1)
			break;
		String comment = stdCode2.substring(strFirst, strEnd+4);
		stdCode2 = stdCode2.replace(comment, "");
	}	
	
	String newCode = stdCode;
	
	newCode = newCode.replaceAll ("&", "&amp;");
	newCode = newCode.replaceAll ("<", "&lt;");
	
	//Write to file xml
			
	String folderName = id + "_" + String.valueOf(System.currentTimeMillis());
	
	String newDir = base_url + codePath + folderName + "/";
	String newDirPath = application.getRealPath(newDir);
	File fileDir = new File(newDirPath);
	fileDir.mkdir();
	
	// Select file type
	
	String type_output = "";
	
	switch (type_code)
	{
		case 1: type_output = ".c";
				break;
		case 2: type_output = ".java";
				break;
		case 3: type_output = ".vb";
				break;
	}
	
	// Write to file code
	
	// file for GCC to run and has the role as student.c
	String fileoutput_c = base_url + newDir + "student" + type_output;
	String output2_c = application.getRealPath(fileoutput_c);
	try {   
		FileOutputStream file_c = new FileOutputStream(output2_c);
		PrintWriter prw = new PrintWriter(file_c);		
		prw.println(stdCode2);		
		prw.close();
	} catch(IOException e) {}

	String folderPath = output2_c.substring(0, output2_c.lastIndexOf("/"));

	String fileoutput_xml = base_url + codePath + id + "_" + tempAuth[0] + ".xml";
	String output_xml = application.getRealPath(fileoutput_xml);

	if (id_num < 31 || id_num > 32)
	{
		// file for WHY to run
		fileoutput_c = base_url + newDir + "std_why" + type_output;
		String output_c = application.getRealPath(fileoutput_c);
		try {   
			FileOutputStream file_c = new FileOutputStream(output_c);
			PrintWriter prw = new PrintWriter(file_c);			
			prw.println(stdCode);		
			prw.close();
		} catch(IOException e) {}
	
		// file has the role as solution.c - the solution which teacher give for this exercise
		fileoutput_c = base_url + newDir + "solution" + type_output;
		String solution_c = application.getRealPath(fileoutput_c);
		try {   
			FileOutputStream file_c = new FileOutputStream(solution_c);
			PrintWriter prw = new PrintWriter(file_c);		
			prw.println(teacher_solution);		
			prw.close();
		} catch(IOException e) {}
	
		// file has the role as var_init.c
		fileoutput_c = base_url + newDir + "var_init" + type_output;
		String var_init_c = application.getRealPath(fileoutput_c);
		try {   
			FileOutputStream file_c = new FileOutputStream(var_init_c);
			PrintWriter prw = new PrintWriter(file_c);		
			prw.println(var_init);		
			prw.close();
		} catch(IOException e) {}
	
		// Model checking result
		String file_exec = base_url + newDir + "execution";
		String exec_htm = application.getRealPath(file_exec);
	
		String relativeWhy = base_url + "shellscript/./run_script.sh";

		String realWhy = getServletContext().getRealPath(relativeWhy);
	
		String cmd = realWhy + " " + folderPath;
		String fileResult = base_url + newDir + "result.txt";
		String inputfile = application.getRealPath(fileResult);
	
		int result = parseWhy(cmd, inputfile, newCode, output_xml);
	
		String relativeMC = base_url + "shellscript/./run_model_linux.sh";
		String realMC = getServletContext().getRealPath(relativeMC);
	
		String folderMC = base_url + "modelchecking" ;
		folderMC = getServletContext().getRealPath(folderMC);
	
		String cmdMC = realMC + " " + folderPath + " " + folderMC;
	
		// Show result for student
		switch (result)
		{
			case 1: answer = "<br/><pre>" + stdCode2 + "</pre><br/>";
					answer += "<br /><b><font color='#006600'>" + info_1 + "</font></b><br />";
					break;
			case 2: if (!var_init.equals(""))
						answer = modelChecking (cmdMC, exec_htm, answer, stdCode2);
					else
						answer = "nothing";
					if (answer.equals("nothing"))
					{
						answer = "<br /><b><font color='#FF0000'>" + info_2 + "</font></b><br />";
						answer += "<br/><pre>" + stdCode2 + "</pre><br/>";
						answer += "<br /><b><font color='#0046F3'>" + "CANNOT FIND COUNTER-EXAMPLE" + "</font></b><br />";
					}
					break;
			case 3: answer = "<br /><b><font color='#FF0000'>" + info_3 + "</font></b><br />";
					answer += "<br/><pre>" + stdCode2 + "</pre><br/>";
					File f = new File(inputfile);
					FileInputStream fis = new FileInputStream(f);
					BufferedInputStream bis = new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);
					String temp = dis.readLine();
					while (dis.available() != 0)
					{
						temp = dis.readLine();
						answer += "<b><font color='#006600'>"+temp+"</font></b><br />";
					}
					break;
			case 4: answer = "<br /><b><font color='#FF0000'>" + info_4 + "</font></b><br />";
					break;	
			case 5: if (!var_init.equals(""))
						answer = modelChecking (cmdMC, exec_htm, answer, stdCode2);
					else
						answer = "nothing";
					if (answer.equals("nothing"))
					{
						answer = "<br /><b><font color='#FF0000'>" + info_5 + "</font></b><br />";
						answer += "<br/><pre>" + stdCode2 + "</pre><br/>";
						answer += "<br /><b><font color='#0046F3'>" + "CANNOT FIND COUNTER-EXAMPLE" + "</font></b><br />";
					}
					break;
		}
		//answer += " !!! DEBUG !!! " +result;
	}
	else	// algorithm checking: sorting algorithms
	{
		String relativeAC = base_url + "shellscript/./run_algorithm_checking.sh";
		String realAC = getServletContext().getRealPath(relativeAC);
	
		String folderAC = base_url + "algorithmchecking" ;
		folderAC = getServletContext().getRealPath(folderAC);
	
		String cmdAC = realAC + " " + folderPath + " " + folderAC;

		String fileResult = base_url + newDir + "sortname.txt";
		String inputFile = application.getRealPath(fileResult);

		String result = algorithmChecking (cmdAC, inputFile, newCode, output_xml);
		if (result.equals("error"))
		{
			answer = "<br /><b><font color='#FF0000'>" + info_4 + "</font></b><br />";
		}
		else
		{
			answer = "<br/><pre>" + newCode + "</pre><br/>";
			answer += "<br /><b><font color='#006600'>" + result + "</font></b><br />";
		}
	}
	
	//answer += " !!! DEBUG modelchecking !!! " + result;
	out.println (answer);

	// delete temporary folder
	
	String delScript = base_url + "shellscript/./clear_all.sh";
	String realDel = getServletContext().getRealPath(delScript);
	String cmdDel = realDel + " " + folderPath;
	
	//deleteFolder(cmdDel);

%>
