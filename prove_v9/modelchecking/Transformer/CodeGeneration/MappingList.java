package Transformer.CodeGeneration;

import java.io.*;
import java.util.*;

public class MappingList implements Serializable {
	List<MappingNode> list;
	MappingNode currentNode;
	InitCode intCode;
	String outputMapping;
	
	public MappingList(String mappingName) {
		list = new ArrayList<MappingNode>();		
		outputMapping = mappingName;
	}
	
	public void createNode() {
		currentNode = new MappingNode();
	}
	
	public void store_line(int line_c, int line_prom) {
		currentNode.line_in_C = line_c;
		currentNode.line_in_Promela = line_prom;
	}
	
	public void store_str(String str) {
		currentNode.str_of_lines.add(str);
	}
	
	public void store_line_else(int l_else) {
		currentNode.line_else = l_else;
	}
	
	public void store_line_end_block(int line_start_block, int l_end_block) {
		int i;
		for (i=0; i<list.size(); i++) {			
			if (list.get(i).line_in_Promela == line_start_block) {
				if (list.get(i).line_end_block != -1)
					System.out.println("ERROR at line_end_block");
				list.get(i).line_end_block = l_end_block;
				break;
			}
		}
	}
	
	public void finish() {
		list.add(currentNode);
		currentNode = null;
	}
	
	public MappingNode getCurrentNode() {
		return currentNode;
	}
	
	// Structure of MappingNode
	//public int line_in_C;				// line in code C
	//public int line_in_Promela;		// line in code Promela
	//public List<String> str_of_lines;	// array of string of 1 line, if size = 1 -> line is ExprStatement
	
	public String printMappingList() {		
		String result = "";		
		int i=0;
		result += "List Name Variable: ";
		for (i=0; i<=intCode.currentIndex; i++) {
			result += intCode.name_variable[i] + ", ";
		}
		result += "\r\n";
		for (i=0; i<list.size(); i++) {
			result += "List[" + i + "]:\r\n";
			result += "\tC: " + list.get(i).line_in_C + "\r\n";
			result += "\tP: " + list.get(i).line_in_Promela + "\r\n";
			if (list.get(i).line_else != -1) {
				result += "\tE: " + list.get(i).line_else + " (line of ElseStmt)\r\n";
			}
			if (list.get(i).line_end_block != -1) {
				result += "\tEnd: " + list.get(i).line_end_block + " (line of end block: if, for, while, do)\r\n";
			}
			int j=0;
			for (j=0; j<list.get(i).str_of_lines.size(); j++) {
				result += "\tS: " + list.get(i).str_of_lines.get(j);
			}			
			result += "\r\n";
		}		
		return result;
	}
	
	public void writeToDisk() {
		try {
			// Use a FileOutputStream to send data to a file
			// called outputMapping
			FileOutputStream f_out = new FileOutputStream (outputMapping);
			
			// Use an ObjectOutputStream to send object data to the
			// FileOutputStream for writing to disk.
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
			
			// Pass our object to the ObjectOutputStream's
			// writeObject() method to cause it to be written out to disk.
			obj_out.writeObject (this);
		} catch (Exception o) {}
	}
	
	public int getLineC(int line_promela) {
		for (int i=0; i<list.size(); i++)
			if (list.get(i).line_in_Promela == line_promela)
				return list.get(i).line_in_C;
		return -1;
	}
	
	public int getLineElse(int line_promela) {
		for (int i=0; i<list.size(); i++)
			if (list.get(i).line_in_Promela == line_promela)
				return list.get(i).line_else;
		return -1;
	}
	
	public List<String> getLineStr(int line_promela) {	
		for (int i=0; i<list.size(); i++)
			if (list.get(i).line_in_Promela == line_promela)
				return list.get(i).str_of_lines;			
		return null;
	}
	
	public int getLineEndBlock(int line_promela) {	
		for (int i=0; i<list.size(); i++)
			if (list.get(i).line_in_Promela == line_promela)
				return list.get(i).line_end_block;
		return -1;
	}
	
	public InitCode getListVarName() {
		return intCode;
	}
	
	public void addInitCode(InitCode init_code) {
		intCode = init_code;
	}
}
