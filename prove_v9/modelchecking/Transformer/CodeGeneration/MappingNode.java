package Transformer.CodeGeneration;

import java.io.*;
import java.util.*;

public class MappingNode implements Serializable {
	
	// line in code C
	public int line_in_C;
	
	// line in code Promela
	public int line_in_Promela;
	
	// array of string of 1 line, if size = 1 -> line is ExprStatement
	public List<String> str_of_lines;
	
	// if this is line of IfStmt
	public int line_else; // line in C code
	
	// if this is line of IfStmt, LoopStmt (for, do, while)
	public int line_end_block; // line in Promela code
	
	public MappingNode() {
		line_in_C = line_in_Promela = line_else = line_end_block = -1;
		str_of_lines = new ArrayList<String>();
	}
}