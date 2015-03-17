<%@page language="java" contentType="text/html"%>
<%@page session="true"%>
<%@include file="../functions.jsp"%>
<%	
	//
	//	Author	: 	Mai H. Dinh
	// 	Date	:	Oct 24, 2013
	//

	String module = request.getParameter("m");
	ArrayList<HashMap> rows = new ArrayList<HashMap>();
	String tab = "&nbsp&nbsp&nbsp&nbsp";
	String pathCTGE_DSFL = getServletContext().getRealPath(base_url + "/DSFL/");
	
	if(module.equals("dsfl")){
		// read standard_output_student.txt
		String path = pathCTGE_DSFL + "/standard_output_student.txt";
		ArrayList<String> studentCode = readFile(path);	
		//out.println(path);
		// read FL.txt
		path = pathCTGE_DSFL + "/FL.txt";
		ArrayList<String> studentFL = readFile(path);
		for (int i = 0; i < studentFL.size(); i++) {
			String line = studentFL.get(i);
			String[] line_splits = line.split(":");
			String code = studentCode.get(Integer.parseInt(line_splits[0]) - 1);
			HashMap row = new HashMap();
			row.put("id_fl", Integer.parseInt(line_splits[0]));	
			row.put("code", code.replace("\t", ""));	
			row.put("rank", line_splits[1]);
			rows.add(row);
		}
	} 
	else if(module.equals("fl")) {		
		// read standard_output_student.txt
		String path = pathCTGE_DSFL + "/standard_output_student.txt";
		ArrayList<String> studentCode = readFile(path);	
		for (int i = 0; i < studentCode.size(); i++) {
			HashMap row = new HashMap();
			row.put("code", studentCode.get(i).replace("\t", tab));
			row.put("order", "");
			row.put("color", "-1");
			row.put("color_origin", "-1");
			rows.add(row);
		}	
		
		String testId = request.getParameter("testId");
		
		// read testcasesandpath.txt
		path = pathCTGE_DSFL + "/testcasesandpath.txt";
		ArrayList<String> studentTestPath = readFile(path);
		for (int i = 0; i < studentTestPath.size(); i++) {
			if(i != Integer.parseInt(testId))
				continue;
			String line = studentTestPath.get(i);
			String[] line_splits = line.split(";");
			for(String split : line_splits ){
				HashMap row = rows.get(Integer.parseInt(split) - 1);
				row.put("color", "1");	
				row.put("color_origin", "1");	
			}
		}
		
		// read FL.txt
		path = pathCTGE_DSFL + "/FL.txt";
		ArrayList<String> studentFL = readFile(path);
		for (int i = 0; i < studentFL.size(); i++) {
			String line = studentFL.get(i);
			String[] line_splits = line.split(":");
			HashMap row = rows.get(Integer.parseInt(line_splits[0]) - 1);
			row.put("rank", line_splits[1]);	
		}
	}
	else if(module.equals("combotestcase")){
		// read standard_output_student.txt
		String path = pathCTGE_DSFL + "/concolicSE.txt";
		ArrayList<String> studentTestCase = readFile(path);
		for (int i = 1; i < studentTestCase.size() - 1; i++) {
			String line = studentTestCase.get(i);
			HashMap row = new HashMap();
			row.put("id", i - 1);
			row.put("text", line);
			rows.add(row);
		}	
	}
	
	// convert to json
	String json = convert2json(rows);
	out.println(json);
%>
<%!	
	private ArrayList<String> readFile(String path) {
		ArrayList<String> result = new ArrayList<String>();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);				
			while (dis.available() != 0) {
				result.add(dis.readLine());
			}
			fis.close();
			bis.close();
			dis.close();
		}
		catch (Exception e) {}
		return result;
	}

	private String convert2json(ArrayList<HashMap> rows) {      
		String json = "";
		try {
			json += "[";
			for (int i = 0; i < rows.size(); i++) {
				json += "{";
				HashMap row = rows.get(i);
				Iterator iter = row.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry mEntry = (Map.Entry) iter.next();
					json += "\"" + mEntry.getKey() + "\":\"" + mEntry.getValue() + "\",";
				}
				json = json.substring(0, json.length()-1);
				json += "},";
			}
			json = json.substring(0, json.length()-1);
			json += "]";
		}
		catch (Exception e) {}
		return json;
	}

%>
