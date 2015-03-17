package Transformer.CodeGeneration;

import java.io.*;
import java.util.*;

public class InitCode implements Serializable {
	public int currentIndex = -1;
	public String[] type;
	public String[] name_variable;
	String returnType = null;
	String tab;
	String nameFunction = null;
	String inputSolutionCode;
	String inputVarInitCode;

	int maxArrayVariableElement = 10;
	
	public InitCode(String nameFunc, String ret, String tb) {
		type = new String[10];
		name_variable = new String[10];		
		nameFunction = nameFunc;
		returnType = ret;
		tab = tb;
		maxArrayVariableElement = 10;
	}
	
	public void setSolutionVarInitName(String solutionName, String varInitName) {
		inputSolutionCode = solutionName;
		inputVarInitCode = varInitName;
	}
	
	public void addType(String t) {
		currentIndex++;
		type[currentIndex] = new String(t);
	}
	
	public void addName(String n) {
		name_variable[currentIndex] = new String(n);		
	}
	
	public String[] getArrayVar() {
		int i, j=0;
		for (i=0; i<=currentIndex; i++) {
			if (type[i].endsWith("[]"))
				j++;
		}
		String[] listarray = new String[j];		
		j=0;
		for (i=0; i<=currentIndex; i++) {
			if (type[i].endsWith("[]")) {
				listarray[j] = "";
				listarray[j] += type[i].replace("[]", "");
				listarray[j] += " " + name_variable[i] + "[" + maxArrayVariableElement + "];";
				j++;
			}
		}
		return listarray;
	}
	
	public boolean isParaVar(String paraName) {
		System.out.println(paraName);
		int i=0;
		for (i=0; i<=currentIndex; i++) {
			if (name_variable[i].equals(paraName))
				return true;
		}
		return false;
	}
	
	public String createInitCode() {		
		// Declare all variable
		String code = "";
		code += "#include \"" + inputVarInitCode + "\"\r\n";
		//code += "#include <math.h> \r\n";
		code += "init {\r\n";
		code += tab;
		int i;
		for (i=0; i<=currentIndex; i++) {
			if (type[i].endsWith("[]"))
				continue;
			code += type[i] + " " + name_variable[i] + "; ";		
		}
		code += "\r\n";
		code += tab + "chan c = [1] of {" + returnType + "};\r\n";		
		
		// Generate set of input
		code += "\r\n";
		/*code += tab + "int n=9999;\r\n";
		code += tab + "int i=1;\r\n";
		code += tab + "c_code {srandom(time(NULL));};\r\n";*/
		code += tab + "var_init("; //var_init(a,b,c,d,i, n);
		code += name_variable[0];
		for (i=1; i<=currentIndex; i++)
			code += ", " + name_variable[i];
		code += ");\r\n";
		
		/*
		code += tab + "do\r\n";
		code += tab + ":: break;\r\n";
		for (i=0; i<=currentIndex; i++) {
			code += tab + ":: " + name_variable[i] + " = " + name_variable[i] + " + 1;\r\n";
			code += tab + ":: " + name_variable[i] + " = " + name_variable[i] + " - 1;\r\n";
		}
		code += tab + "od;\r\n\r\n";
		*/
		
		// Get obtained Result
		code += "\r\n" + tab + returnType + " obtainedResult;\r\n";
		code += tab + "run " + nameFunction + "(";
		for (i=0; i<=currentIndex; i++) {
			if (type[i].endsWith("[]"))
				continue;
			code += name_variable[i] + ", ";
		}
		code += "c);\r\n";		
		code += tab + "c?obtainedResult;\r\n\r\n";
		
		// Get desired Result of teacher
		code += tab + returnType + " desiredResult;\r\n";
		
		/*code += tab + "run m_solution(";
		for (i=0; i<=currentIndex; i++)
			code += name_variable[i] + ", ";
		code += "c2);\r\n";
		code += tab + "c2?desiredResult;\r\n\r\n";*/
				
		// nameFunction = m_<orginial_name>
		String rawNameFunction = nameFunction.substring(2);
		
		code += tab + "c_code {\r\n";
		code += tab + tab + "Pinit-> desiredResult = " + rawNameFunction + "(";
		if (type[0].endsWith("[]"))
			code += "now." + name_variable[0];
		else
			code += "Pinit->" + name_variable[0];
		for (i=1; i<=currentIndex; i++) {
			if (type[i].endsWith("[]"))
				code += ", now." + name_variable[i];
			else
				code += ", Pinit->" + name_variable[i];
		}
		code += ");\r\n";
		code += tab + "};\r\n";
		
		// Check if code contain float
		File fileCheckFloat = new File("isFloatSol");
		if (fileCheckFloat.exists()) {
			File fileScale_Student = new File("student_scale.txt");
			File fileScale_Solution = new File("solution_scale.txt");
			boolean checkFloat = Boolean.parseBoolean(readFirstLine(fileCheckFloat));
			if (checkFloat && fileScale_Student.exists() && fileScale_Solution.exists()) {
				int scale_student = Integer.parseInt(readFirstLine(fileScale_Student));
				int scale_solution = Integer.parseInt(readFirstLine(fileScale_Solution));
				int value_scale_student=1;
				int value_scale_solution=1;
				for (int ii=1; ii<=scale_student; ii++)
					value_scale_student = 10*value_scale_student;
				for (int ii=1; ii<=scale_solution; ii++)
					value_scale_solution = 10*value_scale_solution;
				code += tab + "obtainedResult = obtainedResult * " + value_scale_student + ";\r\n";
				code += tab + "desiredResult = desiredResult * " + value_scale_solution + ";\r\n";				
			}
			else
				System.out.println("ERROR2");
		}
		else 
			System.out.println("ERROR");

		// Verify two result
		code += tab + "assert (obtainedResult == desiredResult )\r\n";
		code += "}\r\n";
		
		code += "\r\nc_code {\\#include \"" + inputSolutionCode + "\"};\r\n";
		code += "\r\nc_code {\\#include \"createdomain.h\"};\r\n";
		return code;
	}
	
	public String readFirstLine(File file) {
		String result = "";
		try{
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			result = br.readLine().trim();
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return result;
	}
}
