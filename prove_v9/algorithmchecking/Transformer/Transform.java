package Transformer;

import java.io.*;
import java.util.*;
import org.antlr.runtime.*;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;

public class Transform {

	/**
	 * @param args
	 */
	AST studentTree;
	String inputStudentCode;
	String outputVerify_c;
	
	public Transform() {
		studentTree = null;
		inputStudentCode = outputVerify_c = null;
	}
	
	public Transform(String student_c, String verify_c) {		
		inputStudentCode = student_c;
		outputVerify_c = verify_c;
	}
	
	public AST createASTFromFile(String filename) {
		try {
			File inputFile = new File(filename);
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader buff = new BufferedReader(fileReader);		
			ANTLRReaderStream input = new ANTLRReaderStream(buff);				
			CPPLexer lexer = new CPPLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CPPParser parser = new CPPParser(tokens);
			return parser.parse();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public void run() {
		try {
			// Create AST Tree from student's code
			studentTree = createASTFromFile(inputStudentCode);		
	
			// Create outputVerify_c
			Visitor walkerC = new PrettyOutputVisitor(outputVerify_c, false);
			studentTree.visit(walkerC, "output_line");
		} catch (Exception e) {
			e.printStackTrace();			
		}	
	}
	
	public static void main(String[] args) {			
		Transform transformer;
		if (args.length != 6)
			transformer = new Transform("student.c", "verify.c");
		else
			transformer = new Transform(args[0], args[1]);
		transformer.run();	
	}
}
