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
	String inputSolutionCode;
	String inputVarInitCode;
	String outputVerify_c;
	String outputVerify_spin;
	String outputMapping;

	public Transform() {
		studentTree = null;
		inputStudentCode = inputSolutionCode = inputVarInitCode = outputVerify_c = outputVerify_spin = outputMapping = null;
	}
	
	public Transform(String student_c, String solution_c, String var_init_spin, 
					String verify_c, String verify_spin, String map_object) {		
		inputStudentCode = student_c;
		inputSolutionCode = solution_c;
		inputVarInitCode = var_init_spin;
		outputVerify_c = verify_c;
		outputVerify_spin = verify_spin;
		outputMapping = map_object;
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

	public void printStudentTree() {
		try {
			System.out.println("AST Tree of this file: " + inputStudentCode);
			Visitor walkerPrint = new AstPrinter();
			studentTree.visit(walkerPrint, null);
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	public void run() {
		try {
		
			System.out.println("Transformer is running ...");
			// Create AST Tree from student's code
			studentTree = createASTFromFile(inputStudentCode);		
	
			printStudentTree();
			
			// Create outputVerify_c
			Visitor walkerC = new PrettyOutputVisitor(outputVerify_c, false);
			studentTree.visit(walkerC, "output_line");
	
			// Create outputVerify_spin		
			Visitor walkerPromela = new CodeGenVisitor(outputVerify_spin, outputMapping, false);
			walkerPromela.setSolutionVarInitName(inputSolutionCode, inputVarInitCode);
			studentTree.visit(walkerPromela, "");
			
			// Create outputMapping
			walkerPromela.printMappingLine();
			
			System.out.println("...\nDone !!!");
		
		}
		catch (Exception e) {
			e.printStackTrace();			
		}		
	}
	
	public static void main(String[] args) {			
		Transform transformer;
		if (args.length != 6)
			transformer = new Transform("student.c", "solution.c", "var_init.c", "verify.c", "verify.spin", "mappingObject");
		else
			transformer = new Transform(args[0], args[1], args[2], args[3], args[4], args[5]);
		transformer.run();	
	}
}
