package Transformer.CounterExample;

import java.io.*;
import java.util.*;
import Transformer.CodeGeneration.*;

public class PrintExecution {
	MappingList mapList_original = null;
	MappingList mapList_newfloat = null;
	String execution_str = null;
	String mapObjectFileName_original = null;
	String mapObjectFileName_newfloat = null;
	String resultFileName = null;
	String executionFileName = null;
	String scaleFileName = null;
	
	public static void main(String[] args) {
		PrintExecution printExecution;
		if (args.length == 5)
			printExecution = new PrintExecution(args[0], args[1], args[2], args[3], args[4]);
		else
			return;
			//printExecution = new PrintExecution("mappingObject", "result.txt", "execution.htm");
		printExecution.print();
	}
	
	public PrintExecution() {
		execution_str = "";
		mapObjectFileName_original = "";
		resultFileName = "";
		executionFileName = "";
	}
	
	public PrintExecution(String mapFileName_org, String mapFileName_newfloat, String resFileName, String outputFileName, String scale) {
		mapObjectFileName_original = mapFileName_org;
		mapObjectFileName_newfloat = mapFileName_newfloat;
		resultFileName = resFileName;
		executionFileName = outputFileName;
		scaleFileName = scale;
	}
	
	public void print() {
		mapList_original = readObjectFromDisk(mapObjectFileName_original);
		mapList_newfloat = readObjectFromDisk(mapObjectFileName_newfloat);
		testObject();
		execution_str = traceExecution();
		writeExecutionToFile();
	}
	
	private void writeExecutionToFile() {		
		try{
			FileWriter fstream = new FileWriter(executionFileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(execution_str);
			out.close();
		}catch (Exception e){}
	}
	
	private MappingList readObjectFromDisk(String mapObjectFileName) {
		try {
			// Read from disk using FileInputStream.
			FileInputStream f_in = new FileInputStream (mapObjectFileName);
			
			// Read object using ObjectInputStream.
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
			
			// Read an object.
			Object obj = obj_in.readObject();
			
			// Cast object to MappingList type
			return (MappingList) obj;
							
		} catch (Exception e) {System.out.println(e.toString()); return null;}
	}
			
	public void testObject() {
		System.out.println(mapList_original.printMappingList());
	}
	
	private String indentString(int scope, String tab) {
		String indent = "";
		for (int i = 0; i < scope; i++)
			indent += tab;
		return indent;
	}
	
	private String createJS() {
		String js = "";
		String newline = "\r\n";
		String tab = "\t";
		js += newline + "<script>" + newline;
		js += "function show(id) {" + newline;
		js += tab + "var name_img = 'img';" + newline;
		js += tab + "if (document.getElementById(id).style.display == 'none') {" + newline;
		js += tab + tab + "document.getElementById(id).style.display = 'block';" + newline;
		js += tab + tab + "document.getElementById(name_img+id).src = 'minus.gif';" + newline;
		js += tab + "}" + newline;
		js += tab + "else {" + newline;
		js += tab + tab + "document.getElementById(id).style.display = 'none';" + newline;
		js += tab + tab + "document.getElementById(name_img+id).src = 'plus.gif';" + newline;
		js += tab + "}" + newline;
		js += "}" + newline;
		js += "</script>" + newline;
		return js;
	}
	
	private String inputTag(int id) {
		// display image button plus or minus
		//return "<input type='image' src='plus.gif' onclick='show(\""+id+"\");' id='img"+id+"' />";
		return "<a href='#' onclick='show(\"" + id + "\"); return false;' id='img" + id + "' class='buttonplus'>&nbsp;&nbsp;&nbsp;&nbsp;</a>";
	}
	
	private String openDiv(int id) {
		// openDiv1 + String.valueOf(id) + openDiv2 + ....... + endDiv
		//String openDiv1 = "<div style='display:none;' id='";
		String openDiv1 = "<div class='hidecode' id='";
		String openDiv2 = "'>\r\n";
		return openDiv1 + id + openDiv2;
	}	
	
    private String readFileAsString(String filePath) {
		try {
			StringBuffer fileData = new StringBuffer(1000);
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			char[] buf = new char[1024];
			int numRead=0;
			while((numRead=reader.read(buf)) != -1){
				fileData.append(buf, 0, numRead);
			}
			reader.close();
			return fileData.toString();
		} catch (Exception e) {
			return "NONE";
		}
    }

	private String traceExecution() {
		if (mapList_original == null || mapList_newfloat == null)
			return "";
		
		CounterExampleGeneration generator = new CounterExampleGeneration(new File(resultFileName));
		List<Entry> lst = generator.GenerateCounterExample();
		
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i).getLine() + "\t");
			System.out.println(lst.get(i).getCode());
		}
		
		String execution = "";
		String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\t";
		String newline = "<br>\r\n";
		String endDiv = "</div>\r\n";
		String[] loop = new String[10];
		int[] num_loop = new int[10];
		for (int k=0; k<10; k++) {
			loop[k] = ""; num_loop[k] = 1;
		}
		int index_loop = -1;
		int scope = 0;
		Stack<Integer> endBlock = new Stack<Integer>();
		
		//execution += createJS();
		//execution += "\r\n<link rel='stylesheet' type='text/css' href='show.css'>\r\n";
		//execution += "<script type='text/javascript' src='show.js'></script>\r\n";
		execution += "\r\n";
		execution += "<div id='verifycode'>\r\n";
		execution += "<pre>\r\n";
		String content_file = readFileAsString("verify.c");
		execution += content_file.replace("\t", "   ")+newline;
		execution += "</pre>\r\n";
		execution += "</div>\r\n";
		execution += "<div id='counterexample'>\r\n";
		execution += newline+"COUNTER EXAMPLE:"+newline+newline;


		//*******************************************************************
		// Modify code in here when the student code include float
		boolean code_contain_float = false;
		float degree = -1, level = -1;
		if (scaleFileName != null) {
			try{
				FileInputStream fstream = new FileInputStream(scaleFileName);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				//Read File Line By Line
				int line = 0;		
				while ((strLine = br.readLine()) != null)   {
					// Print the content on the console
					if (strLine.equals("")) continue;
					//System.out.println(strLine);
					if (line == 0) {
						if (strLine.trim().equals("true"))
							code_contain_float = true;
						else
							break;
					}
					else if (line == 1) {
						degree = Float.parseFloat(strLine);
					}
					else if (line == 2)
						level = Float.parseFloat(strLine);
					line++;
				}
				System.out.println(line);
				in.close();
			}catch (Exception e){
				System.err.println("Error: " + e.getMessage());
			}
		}
		//*******************************************************************
	
		MappingList mapList = mapList_newfloat;		
		
		List<String> listName = new ArrayList<String>();
		InitCode intCode = mapList.getListVarName();		
		for (int i=0; i<=intCode.currentIndex; i++) {	
			String newname = intCode.name_variable[i];
			listName.add(newname);			
		}	
		listName.add("obtainedResult");
		listName.add("desiredResult");		
		List<Variable> lstVariable = generator.GetListOfVariables(listName);
		
		double obtainedValue = (double) Integer.parseInt(lstVariable.get(lstVariable.size()-2).getVariableValue());
		double desiredValue = (double) Integer.parseInt(lstVariable.get(lstVariable.size()-1).getVariableValue());
		
		int j;
		for (j = 0; j < lstVariable.size()-2; j++) {
			execution += "Value of input:"+newline;
			String tmpVarName = lstVariable.get(j).getVariableName();

			System.out.println("xxxxVarGet: " + tmpVarName);	
			if (code_contain_float) {
				
				int pos1 = tmpVarName.lastIndexOf("_i");
				int pos2 = tmpVarName.lastIndexOf("_f");
				int pos3 = tmpVarName.lastIndexOf("_d");
				int pos = 1;
				//if (pos1 != -1) pos = pos1;
				//else if (pos2 != -1) pos = pos2;
				//else if (pos3 != -1) pos = pos3;
				if (pos1 >= pos2 && pos1 >= pos3)
					pos = pos1;	
				else if (pos2 >= pos3)
					pos = pos2;
				else 	
					pos = pos3;
						
	
				System.out.println("xxxPos: " + pos);							
				tmpVarName = tmpVarName.substring(0, pos);
				System.out.println("xxxVarNameAfter: " + tmpVarName);		
			}

			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");	
			double valueOfInput = (double) Integer.parseInt(lstVariable.get(j).getVariableValue());
			if (code_contain_float) {
				valueOfInput = valueOfInput / Math.pow(10, degree);
				obtainedValue = obtainedValue / Math.pow(10, degree);
				desiredValue = desiredValue / Math.pow(10, degree);
				execution += tab + tmpVarName.substring(tmpVarName.indexOf('_') + 1) + " = " + valueOfInput + newline;
			}
			else {
				int value = (int) valueOfInput;
				execution += tab + tmpVarName.substring(tmpVarName.indexOf('_') + 1) + " = " + value + newline;
			}
			
		}
		

		if (!code_contain_float) {
			int value1 = (int) obtainedValue;
			int value2 = (int) desiredValue;
			execution += "Obtained Result: = " + value1 + newline;
			execution += "Desired Result: = " + value2 + newline+newline;
		}
		else {
			//TH xuat obtain va desire khi co so float
			//execution += "Obtained Result: = " + obtainedValue + newline;
			//execution += "Desired Result: = " + desiredValue + newline+newline;			
		}

		mapList = mapList_original;
		
		for (int i=0; i<lst.size(); i++) {
			//if (lst.get(i).getCode().equals("1"))
				//continue;
			if (lst.get(i).getCode().startsWith("-end-")) {
				while (true) {
					if (!endBlock.empty()) {
						scope--;
						endBlock.pop();
						execution += indentString(scope, "\t") + endDiv;
					}
					else
						break;
				}
			}
				
			int line_c, line_promela, line_end_block;
			List<String> line_str;
			line_promela = lst.get(i).getLine();
			line_c = mapList.getLineC(line_promela);
			if (line_c == -1)
				continue;
			line_str = mapList.getLineStr(line_promela);
			line_end_block = mapList.getLineEndBlock(line_promela);

			if (line_str.size() == 1) {
				// ExprStmt
				while (true) {
					if (!endBlock.empty() && line_promela >= endBlock.peek()) {
						scope--;
						endBlock.pop();
						execution += indentString(scope, "\t") + endDiv;
					}
					else
						break;
				}
				execution += indentString(endBlock.size(), tab) + "Line " + line_c + ": " + line_str.get(0).trim() + newline;
			}
			else {
				if (line_str.get(0).startsWith("if")) {
					// IfStmt
					while (true) {
						if (!endBlock.empty() && line_promela >= endBlock.peek()) {
							scope--;
							endBlock.pop();
							execution += indentString(scope, "\t") + endDiv;
						}
						else
							break;
					}
					endBlock.push(line_end_block);
					execution += indentString(scope, tab) + "Line " + line_c + ": If statement " + inputTag(i) + newline;
					execution += indentString(scope, "\t") + openDiv(i);
					scope++;
					
					if (!lst.get(i).getCode().equals("else"))
						execution += indentString(scope, tab) + "Line " + line_c + ": [" + line_str.get(1) + "] = true" + newline;
					else {
						execution += indentString(scope, tab) + "Line " + line_c + ": [" + line_str.get(1) + "] = false" + newline;
						if (mapList.getLineElse(line_promela) != -1)
							execution += indentString(scope, tab) + "Line " + mapList.getLineElse(line_promela) + ": else" + newline;
					}					
				}
				else {					
					// LoopStmt
					while (true) {
						if (!endBlock.empty() && line_promela >= endBlock.peek()) {
							scope--;
							endBlock.pop();
							execution += indentString(scope, "\t") + endDiv;
						}
						else
							break;
					}
					if (endBlock.empty() || (!endBlock.empty() && line_end_block != endBlock.peek())) {
						endBlock.push(line_end_block);
						execution += indentString(scope, tab) + "Line " + line_c + ": "+line_str.get(0).trim()+" statement " + inputTag(i) + newline;
						execution += indentString(scope, "\t") + openDiv(i);
						scope++;
					}
					
					if (!lst.get(i).getCode().equals("else"))
						execution += indentString(scope, tab) + "Line " + line_c + ": [" + line_str.get(1) + "] = true" + newline;
					else {
						execution += indentString(scope, tab) + "Line " + line_c + ": [" + line_str.get(1) + "] = false" + newline;
						scope--;
						endBlock.pop();
						execution += indentString(scope, "\t") + endDiv;
					}
				}
			}
		}
		execution += endDiv+newline;
		return execution;
	}
}
