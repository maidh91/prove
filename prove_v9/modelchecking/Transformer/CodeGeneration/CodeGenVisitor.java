package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class CodeGenVisitor extends DoNothingVisitor {
	Emitter em;
	Frame frm;
	Switch_Case switch_case = null;		
	String tab = "\t";
	int scope = 0;
	int line = 1;
	boolean outputLine = false;	
	MappingList mapList = null;
	private InitCode init_code = null;
	private InputGeneration inputGen = null;
	String inputSolutionCode;
	String inputVarInitCode;
		
	public CodeGenVisitor(String outputVerify_spin, String outputMapping, boolean debug) throws CompilationException {
		em = new Emitter();
		frm = new Frame();
		mapList = new MappingList(outputMapping);
		em.setFilename(outputVerify_spin);
		inputGen = new InputGeneration();
		
		if (debug == false)
			em.print_to_console = false;
		else
			System.out.println("\n\nOUTPUT_Promela: ("+outputVerify_spin+", "+outputMapping+")");
	}
	
	public void setSolutionVarInitName(String solutionName, String varInitName) {
		inputSolutionCode = solutionName;
		inputVarInitCode = varInitName;
	}
	
	public void setModeOutputLine(boolean on) {
		outputLine = on;
	}
	
	public String newline() {
		String newline_str = "\r\n";
		if (!inLineSwitch)
			line++;
		return newline_str;
	}
	
	private int getKey() {
		return frm.getNewLabel();
	}
	
	public void inScope() {
		scope++;
	}
	
	public void outScope() {
		em.deleteScope(scope);
		scope--;
	}
	
	public void indentString() {
		String indent = "";
		for (int i = 0; i < scope; i++)
			indent += tab;
		String print_line;
		if (line < 10)
			print_line = "  " + line;
		else if (line < 100)
			print_line = " " + line;
		else
			print_line = "" + line;
				
		if (!outputLine)
			em.printout(indent);
		else if (inLineSwitch)
			return;
		else
			em.printout(print_line + ":  " + indent);
		//em.printout(indent);
	}

	public void printMappingLine() {
			if (em.print_to_console) {
				String result = mapList.printMappingList();
				System.out.println("Result Mapping line:\r\n" + result);
			}
			mapList.writeToDisk();
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {		
		String option = (String) o;
		if (option != null) {
			if (option.equals("output_line"))
				setModeOutputLine(true);
			else
				setModeOutputLine(false);			
		}		
		ast.dl.visit(this, o);
		inputGen.printList();
		inputGen.sortList();
		inputGen.printList();
		inputGen.printFile("sample.txt");
		em.printToPromela();
		return null;
	}
	
	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, o);
		ast.dl.visit(this, o);
		return null;
	}
	
	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {		
		indentString();
		if (ast.t instanceof ArrayTypeAST) {
			ast.t.visit(this, ast.id.getText());
			return null;
		}
		ast.t.visit(this, o);

/* result may be equals to:
"int";
"bool";
"byte"
"signed"
"unsigned"
"short"
*/
		em.printout(" ");
		em.store_var = true;
		em.printout(em.emitVAR(ast.id.getText(), scope));
		em.store_var = false;
		if (ast.init != null) {
			em.printout(" = ");
			ast.init.visit(this, o);
		}
		em.printout(";" + newline());
		return null;
	}
	
	// PointerTypeAST
	public Object visitPointerTypeAST(PointerTypeAST ast, Object o)
			throws CompilationException {
		
		return null;
	}
	
	// IntTypeAST
	public Object visitIntTypeAST(IntTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("int");
		return "int";
	}
	
	// initializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, o);
		return null;
	}
	
	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {		
		em.printout(ast.literal.getText());		
		return new SymEntry(ast.literal.getText());
	}

	// BoolTypeAST
	public Object visitBoolTypeAST(BoolTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("bool");
		return "bool";
	}

	// VoidTypeAST
	public Object visitVoidTypeAST(VoidTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		return "voidType";
	}
	
	// FloatTypeAST
	public Object visitFloatTypeAST(FloatTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("float");
		return "int";
	}
	
	// CharTypeAST
	public Object visitCharTypeAST(CharTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("byte");
		return "byte";
	}
	
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	//StringLiteralAST
	public Object visitStringLiteralAST(StringLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	//CharLiteralAST
	public Object visitCharLiteralAST(CharLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	// ArrayTypeAST
	public Object visitArrayTypeAST(ArrayTypeAST ast, Object o)
			throws CompilationException {
		String id = (String) o;
		if (id != null) {
			// this ArrayType belong to VarDecl
			ast.type.visit(this, o);
			em.printout(" ");
			em.store_var = true;
			em.printout(em.emitVAR(id, scope));
			em.store_var = false;
			ast.el.visit(this, "arraytype");		
			em.printout(";" + newline());
		}
		else {
			// this ArrayType belong to ParaAST
			return ast.type.visit(this, "var_function");
		}
		return null;
	}
	
	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		ast.dl.visit(this, o);
		return null;
	}
	
	/*/ ProcDeclPartAST
	public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o)
			throws CompilationException {
		ast.o.visit(this, o);
		ast.p.visit(this, o);
		return null;
	}*/
/*
VoidTypeAST
IntTypeAST -> int
CharTypeAST -> byte
ShortTypeAST -> short
LongTypeAST
UnsignedTypeAST
SignedTypeAST
WCharTTypeAST
DoubleTypeAST
BoolTypeAST
FloatTypeAST
*/	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
				
		em.setFilter(true, true);		
		
		String returnType = (String) fAst.ret.visit(this, "function");		
		em.printout(" " + em.emitVAR(fAst.name.getText(), -1));
		em.printout("(");
		
		// Create init code for spin
		init_code = new InitCode(em.emitVAR(fAst.name.getText(), -1), returnType, tab);
		init_code.setSolutionVarInitName(inputSolutionCode, inputVarInitCode);

		fAst.para.visit(this, "var_function");

		try {
			frm.enterScope(true);
			if (returnType != null && !returnType.equals("voidType")) {
				em.printout("chan c");
			}
			em.printout(")");
			String firstLine = em.setFilter(false, false);			
			
			String[] list_array = init_code.getArrayVar();
			int i;
			for (i=0; i<list_array.length; i++) {
				em.printout(list_array[i]);
				em.printout(newline());
			}
			em.printout(firstLine);
			
			em.printout(newline());
			em.printout("{" + newline());
			indentString();	em.printout("lb_" + frm.getStartLabel() + ": skip;" + newline());
			inScope();			
			fAst.c.visit(this, "block_function");
			indentString();	em.printout("lb_" + frm.getEndLabel() + ": skip;"+newline());	
			em.printout("}" + newline());
			frm.exitScope();
		} catch (Exception e) {}
				
		mapList.addInitCode(init_code);
		em.printout("\r\n");
		em.printout(init_code.createInitCode());
		em.printout("\r\n");
			
		return null;
	}

	// ParaListAST
	public Object visitParaListAST(ParaListAST ast, Object o)
			throws CompilationException {		
		ast.v.visit(this, "var_function");
		if (!(ast.v.t instanceof ArrayTypeAST))
			em.printout("; ");
		ast.p.visit(this, "var_function");
		return null;
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST ast, Object o)
			throws CompilationException{
		if (ast.t instanceof ArrayTypeAST) {
			em.setNoPrint(true);		
			String type = (String) ast.t.visit(this, null);
			init_code.addType(type+"[]");
			em.store_var = true;
			em.printout(em.emitVAR(ast.id.getText(), scope+1));
			em.store_var = false;
			init_code.addName(em.emitVAR(ast.id.getText(), scope+1));
			em.setNoPrint(false);
			return null;
		}
		String type = (String) ast.t.visit(this, "var_function");
		init_code.addType(type);
		em.store_var = true;
		em.printout(" " + em.emitVAR(ast.id.getText(), scope+1));
		em.store_var = false;
		init_code.addName(em.emitVAR(ast.id.getText(), scope+1));
		return em.emitVAR(ast.id.getText(), scope+1);
	}

	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {
		String checkBlock = (String) o;
		if (checkBlock != null && checkBlock.equals("switch")) {
			ast.s.visit(this, "switch");
		}
		else if (checkBlock != null && checkBlock.equals("block_function"))
			ast.s.visit(this, "block_function");	
		else {			
			//indentString();
			//em.printout("{" + newline());		
			ast.s.visit(this, "block_comp");
		}
		return null;
	}

	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		String checkBlock = (String) o;		
		if (checkBlock != null && checkBlock.equals("block_comp"))
			inScope();
		if (checkBlock != null && (checkBlock.equals("switch") || checkBlock.equals("case")))
		{
			ast.o.visit(this, o);
			ast.s.visit(this, o);
			return null;
		}
		ast.o.visit(this, null);
		ast.s.visit(this, "statement");		
		return null;
	}
	
	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		String checkBlock = (String) o;
		if (checkBlock != null && checkBlock.equals("block_function")) {
			outScope();
		}
		/*if (checkBlock != null && checkBlock.equals("block_comp")) {			
			//indentString();
			//em.printout("}" + newline());
		}*/
		if (checkBlock != null && checkBlock.equals("statement")) {
			outScope();
			if (scope == 0)
				return null;
			//indentString();
			//em.printout("}" + newline());			
		}
		return null;
	}	
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		em.printout(em.emitVAR(ast.name.getText(), scope));
		return em.emitVAR(ast.name.getText(), scope);
	}

	// EleExprAST
	//public Token			name;
	//public ExprListAST	e;
	public Object visitEleExprAST(EleExprAST ast, Object o)
			throws CompilationException {
		em.printout(em.emitVAR(ast.name.getText(), scope));		
		ast.e.visit(this, "arraytype");
		return null;
	}

	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {		
		indentString();
		if (!(ast.e instanceof TernaryExprAST)) {
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str(ast.line_str);
			mapList.finish();
			em.printout("lb_" + getKey() + ": ");
		}
		
		String short_if = (String) ast.e.visit(this, o);
		if (short_if != null && short_if.equals("short_if"))
			return null;		
		em.printout(";" + newline());
		return null;
	}
	
	// UnaryExprAST	
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object entry;
		if (ast.opType == UnaryExprAST.POST_INC || ast.opType == UnaryExprAST.POST_DEC) {
			// dau x++, x--
			entry = ast.e.visit(this, o);
			em.printout(ast.op.getText());
		}
		else {
			em.printout(ast.op.getText());
			entry = ast.e.visit(this, o);
		}
		if (entry instanceof SymEntry) {
			String value = ((SymEntry) entry).getValue();
			if (value != null) {
				int val = Integer.parseInt(value);				
				if (ast.opType == UnaryExprAST.UNARY_PLUS) {
					return new SymEntry(value);
				}
				else if (ast.opType == UnaryExprAST.UNARY_MINUS) {
					int result = 0 - val;
					return new SymEntry(String.valueOf(result));
				}
			}
		}
		
		return null;
	}
	
	// BinExprAST
	// += PLUS_ASSIGN = 1;
	// -= MINUS_ASSIGN = 2;
	// *= STAR_ASSIGN = 3;
	// /= DIV_ASSIGN = 4;
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		String checkAssign = (String) o;
		boolean checkAssgn;		
		
		if (checkAssign != null && checkAssign.equals("assign"))
			checkAssgn = true;
		else
			checkAssgn = false;
				
		if ("ifthen".equals((String) o)) {
			em.setNoPrint(true);
			if (ast.e1 instanceof VarExprAST && ast.e2 instanceof IntLiteralAST) {
				// Truong hop x <, >, <=, >=, ==, != Constant Integer
				String name = (String) ast.e1.visit(this, null);
				if (init_code.isParaVar(name))
					inputGen.addConstant(Integer.parseInt(((IntLiteralAST) ast.e2).literal.getText()));
			}
			else if (ast.e2 instanceof VarExprAST && ast.e1 instanceof IntLiteralAST) {
				// Truong hop x <, >, <=, >=, ==, != Constant Integer
				String name = (String) ast.e2.visit(this, null);
				if (init_code.isParaVar(name))
					inputGen.addConstant(Integer.parseInt(((IntLiteralAST) ast.e1).literal.getText()));
			}
			else if (ast.e1 instanceof VarExprAST) {
				String name = (String) ast.e1.visit(this, null);
				if (init_code.isParaVar(name)) {
					Object entry = ast.e2.visit(this, null);
					if (entry instanceof SymEntry) {
						String value = ((SymEntry) entry).getValue();
						if (value != null)
							inputGen.addConstant(Integer.parseInt(value));
					}
				}
			}
			else if (ast.e2 instanceof VarExprAST) {
				String name = (String) ast.e2.visit(this, null);
				if (init_code.isParaVar(name)) {
					Object entry = ast.e1.visit(this, null);
					if (entry instanceof SymEntry) {
						String value = ((SymEntry) entry).getValue();
						if (value != null)
							inputGen.addConstant(Integer.parseInt(value));
					}
				}
			}
						
			em.setNoPrint(false);
		}

		Object entry1, entry2;
		if (ast.opType <= BinExprAST.DIV_ASSIGN && ast.opType >= BinExprAST.PLUS_ASSIGN) {
			// PLUS_ASSIGN = 1 => DIV_ASSIGN = 4
			ast.e1.visit(this, null);
			em.printout(" = ");
			ast.e1.visit(this, null);
			switch (ast.opType) {
				case BinExprAST.PLUS_ASSIGN:
					em.printout(" + ");					
					break;
				case BinExprAST.MINUS_ASSIGN:
					em.printout(" - ");					
					break;
				case BinExprAST.STAR_ASSIGN:
					em.printout(" * ");					
					break;
				case BinExprAST.DIV_ASSIGN:
					em.printout(" / ");					
					break;
			}
			ast.e2.visit(this, null);
		}
		else if (ast.opType >= BinExprAST.PLUS && ast.opType <= BinExprAST.MOD) {
			// PLUS = 24 => MOD = 28
			// opType: + - * / %
			if (!checkAssgn)
				em.printout("(");
			entry1 = ast.e1.visit(this, null);
			em.printout(" " + ast.op.getText() + " ");			
			entry2 = ast.e2.visit(this, null);
			if (!checkAssgn)
				em.printout(")");
			if (entry1 instanceof SymEntry && entry2 instanceof SymEntry) {
				String value1 = ((SymEntry) entry1).getValue();
				String value2 = ((SymEntry) entry2).getValue();
				if (value1 != null && value2 != null) {
					int val1 = Integer.parseInt(value1);
					int val2 = Integer.parseInt(value2);					
					if (ast.opType == BinExprAST.PLUS)
						return new SymEntry(String.valueOf(val1+val2));
					else if (ast.opType == BinExprAST.MINUS)
						return new SymEntry(String.valueOf(val1-val2));
					else if (ast.opType == BinExprAST.STAR)
						return new SymEntry(String.valueOf(val1*val2));
					else if (ast.opType == BinExprAST.DIV)
						return new SymEntry(String.valueOf(val1/val2));
					else
						return new SymEntry(String.valueOf(val1%val2));
				}
			}
		}
		else {
			ast.e1.visit(this, null);
			em.printout(" " + ast.op.getText() + " ");
			if (ast.opType == 0) // phep gan
				ast.e2.visit(this, "assign");
			else
				ast.e2.visit(this, null);
		}
		return null;
	}

	// TernaryExprAST
	// x == 0 ? y = 0 : y = 1
	public Object visitTernaryExprAST(TernaryExprAST ast, Object o)
			throws CompilationException {
		em.printout("if" + newline());

		int line_start_block = line; // line in promela code;
		mapList.createNode();
		mapList.store_line(ast.l1, line);
		mapList.store_line_else(ast.l3-1);
		mapList.store_str("if\n");
		mapList.store_str(ast.s1);
		mapList.finish();			
		
		indentString(); em.printout(":: ");
		ast.e1.visit(this, "ifthen");
		em.printout(" -> " + newline());
		inScope();
			//indentString(); em.printout("{" + newline());
			//inScope();				
				mapList.createNode();
				mapList.store_line(ast.l2, line);				
				mapList.store_str(ast.s2);
				mapList.finish();
				indentString();
				em.printout("lb_" + getKey() + ": ");
				ast.e2.visit(this, o);
				em.printout(";"+newline());
			//outScope();
			//indentString(); em.printout("}" + newline());		
		outScope();
		indentString(); em.printout(":: else -> " + newline());
		inScope();
			//indentString(); em.printout("{" + newline());
			//inScope();				
				mapList.createNode();
				mapList.store_line(ast.l3, line);				
				mapList.store_str(ast.s3);
				mapList.finish();
				indentString();
				em.printout("lb_" + getKey() + ": ");
				ast.e3.visit(this, o);
				em.printout(";"+newline());
			//outScope();
			//indentString(); em.printout("}" + newline());		
		outScope();
		indentString(); em.printout("fi;"+newline());
		mapList.store_line_end_block(line_start_block, line);
		return "short_if";
	}

	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {

		indentString(); em.printout("if" + newline());		

		int line_start_block = line; // line in promela code;		
		mapList.createNode();
		mapList.store_line(ast.line, line);
		mapList.store_str("if\n");
		mapList.store_str(ast.line_str);
		mapList.finish();
		
		indentString(); em.printout(":: ");
		ast.e.visit(this, "ifthen");
		em.printout(" -> " + newline());
		//inScope();
		if (ast.s instanceof CompStmtAST)
			ast.s.visit(this, o);
		else {
			//indentString(); em.printout("{" + newline());
			inScope();
			ast.s.visit(this, o);
			outScope();
			//indentString(); em.printout("}" + newline());
		}
		//outScope();
		indentString(); em.printout(":: else -> skip;" + newline());
		indentString(); em.printout("fi;" + newline());		
		mapList.store_line_end_block(line_start_block, line);
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {
		
		indentString(); em.printout("if" + newline());	
		
		int line_start_block = line; // line in promela code;		
		mapList.createNode();
		mapList.store_line(ast.line, line);
		mapList.store_line_else(ast.line_else);
		mapList.store_str("if\n");
		mapList.store_str(ast.line_str);
		mapList.finish();
		indentString(); em.printout(":: ");
		ast.e.visit(this, "ifthen");
		em.printout(" -> " + newline());
		//inScope();
		if (ast.s1 instanceof CompStmtAST)
			ast.s1.visit(this, o);
		else {
			//indentString(); em.printout("{" + newline());
			inScope();
			ast.s1.visit(this, o);
			outScope();
			//indentString(); em.printout("}" + newline());
		}
		//outScope();
		indentString(); em.printout(":: else -> " + newline());
		//inScope();
		if (ast.s2 instanceof CompStmtAST)
			ast.s2.visit(this, o);
		else {
			//indentString(); em.printout("{" + newline());
			inScope();
			ast.s2.visit(this, o);
			outScope();
			//indentString(); em.printout("}" + newline());
		}
		//outScope();
		indentString(); em.printout("fi;" + newline());		
		mapList.store_line_end_block(line_start_block, line);
		return null;
	}

	boolean inLineSwitch = false;
	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o)
			throws CompilationException {
		
		inLineSwitch = true;
		em.setNoPrint(true);
		String name_var = (String) ast.e.visit(this, null);
		em.setNoPrint(false);

		// create temp object to store info of each case
		switch_case = new Switch_Case();
		switch_case.lbOut = getKey();
		
		// Visit each case
		em.setCaseOn(true);
		ast.o.visit(this, "switch");
		switch_case.setStrCase(em.getSwitch());
		em.setCaseOn(false);
		inLineSwitch = false;
		
		// Print if ..... fi;
		indentString(); em.printout("if" + newline());
		for (int i = 0; i <= switch_case.current_case; i++)			
			if (switch_case.pos_default != i) {
				indentString();
				em.printout(":: " + name_var + " == " + switch_case.value_case[i] + " -> goto lb_" + switch_case.lb_case[i] + ";" + newline());
			}
		indentString();
		if (switch_case.pos_default != -1)			
			em.printout(":: else -> goto lb_" + switch_case.lb_default + ";" + newline());
		else
			em.printout(":: else -> goto lb_" + switch_case.lbOut + ";"+newline());
		indentString();
		em.printout("fi;"+newline());

		// Print lable of each case, default		
		for (int i = 0; i <= switch_case.current_case; i++) {
			indentString();
			em.printout("lb_" + switch_case.lb_case[i] + ": skip;" + newline());
			inScope();
			String[] array_case = switch_case.str_case[i].split(";");
			for (int j = 0; j < array_case.length; j++) {
				array_case[j] = array_case[j].trim();
				if (array_case[j].equals("")) continue;
				indentString(); em.printout(array_case[j] + ";" + newline());
			}
			outScope();
		}
		indentString();
		em.printout("lb_" + switch_case.lbOut + ": skip;" + newline());		
		
		//System.out.println("Test switch:\n"); // Debug
		//switch_case.print();		
		
		return null;
	}

	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST ast, Object o)
			throws CompilationException {
	//public ExprAST e;
	//public StmtListAST s;		
		switch_case.setStrCase(em.getSwitch());
		
		em.setNoPrint(true);
		String value_case = (String) ast.e.visit(this, null);
		em.setNoPrint(false);
		int value = Integer.parseInt(value_case);
		switch_case.setValLabel(value, getKey());
				
		ast.s.visit(this, "case");
		return null;
	}
	
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o)
			throws CompilationException {
	//public StmtListAST s;
		switch_case.setStrCase(em.getSwitch());
		switch_case.setValLabelOfDefault(getKey());
		ast.s.visit(this, o);
		return null;
	}	

	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		String checkCase = (String) o;
		if (checkCase != null && checkCase.equals("case")) {
			switch_case.setBreak();
		}
		else {
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str("break;\r\n");
			mapList.finish();

			indentString();
			em.printout("lb_" + getKey() + ": printf(\"break\\n\"); break;" + newline());
		}
		return null;
	}	

	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		boolean tmp = inLineFor;
		inLineFor = false;
		try {
			frm.enterLoop();
			indentString();
			em.printout("lb_" + frm.getContinueLabel() + ":"+newline());
			indentString(); em.printout("do" + newline());

			int line_start_block = line; // line in promela code;
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str("while\n");
			mapList.store_str(ast.line_str);
			mapList.finish();
			
			indentString(); em.printout(":: ");
			ast.e.visit(this, o);
			em.printout(" -> " + newline());
			if (!(ast.o instanceof CompStmtAST))
				inScope();
			ast.o.visit(this, o);
			if (!(ast.o instanceof CompStmtAST))
				outScope();
			indentString(); em.printout(":: else -> break;" + newline());
			indentString(); em.printout("od;"+newline());
			mapList.store_line_end_block(line_start_block, line);
			indentString();
			em.printout("lb_" + frm.getBreakLabel() + ": skip;"+newline());
			frm.exitLoop();
		} catch (Exception e) {}
		inLineFor = tmp;
		return null;
	}
	
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		try {
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str("continue;\r\n");
			mapList.finish();

			indentString();
			if (inLineFor)
				em.printout("printf(\"continue\\n\"); goto lb_" + frm.getjumpLabel() + ";" + newline());
			else
				em.printout("printf(\"continue\\n\"); goto lb_" + frm.getContinueLabel() + ";" + newline());
		} catch (Exception e) {}
		return null;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {
/*
lbbegin: 
do
:: 1 ->
	statement…;
	if
	:: expression -> skip;
	:: else -> break;
	fi;
od;
lbend:
*/		
		boolean tmp = inLineFor;
		inLineFor = false;
		try {
			frm.enterLoop();
			indentString();
			em.printout("lb_" + frm.getContinueLabel() + ":" + newline());
			indentString(); em.printout("do" + newline());
			indentString(); em.printout(":: 1 ->" + newline());
				if (!(ast.o instanceof CompStmtAST)) inScope();			
				ast.o.visit(this, o);
				if (!(ast.o instanceof CompStmtAST)) outScope();
				inScope();
				indentString(); em.printout("if" + newline());

				int line_start_block = line; // line in promela code;				
				mapList.createNode();
				mapList.store_line(ast.line, line);
				mapList.store_str("do\n");
				mapList.store_str(ast.line_str);
				mapList.finish();
			
				indentString(); em.printout(":: ");
				ast.e.visit(this, o);
				em.printout(" -> skip;" + newline());
				indentString(); em.printout(":: else -> break;"+newline());
				indentString(); em.printout("fi;" + newline());
				outScope();
			indentString(); em.printout("od;" + newline());			
			mapList.store_line_end_block(line_start_block, line);
			indentString();
			em.printout("lb_" + frm.getBreakLabel() + ": skip;"+newline());
			frm.exitLoop();
		} catch (Exception e) {}
		inLineFor = tmp;
		return null;
	}

	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		String check = (String) o;
		if (check != null && check.equals("for")) {
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str(ast.line_str);
			mapList.finish();
	
			indentString();
			em.printout("lb_" + getKey() + ": ");
			ast.e.visit(this, "for2");
			ast.l.visit(this, "for2");
		}
		if (check != null && check.equals("for2")) {		
			em.printout(";" + newline());
			
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str(ast.line_str);
			mapList.finish();
	
			indentString();
			em.printout("lb_" + getKey() + ": ");
			ast.e.visit(this, "for2");
			ast.l.visit(this, "for2");
		}
		if (check != null && check.equals("arraytype")) {
			em.printout("[");
			ast.e.visit(this, o);
			em.printout("]");
			ast.l.visit(this, o);
		}
		
		return null;
	}
	
	boolean inLineFor = false;
	// ForStmtAST
	//public ForInitAST		e1;
	//public ExprAST			e2;
	//public ExprListAST		e3;
	//public OneStmtAST		o;
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
/*
expression1;
lbbegin: 
do
:: expression2 ->
	statement…;
	jump: skip;
	expression3;
:: else -> break;
od;
lbend:
*/
		inLineFor = true;
		try {			
			ast.e1.visit(this, "for");
			if (ast.e1.type == 2)
				em.printout(";"+newline());
			frm.enterLoop();
			indentString();
			em.printout("lb_" + frm.getContinueLabel() + ":" + newline());
			indentString(); em.printout("do" + newline());
			
			int line_start_block = line; // line in promela code;			
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str("for\n");
			mapList.store_str(ast.s2);
			mapList.finish();
			
			indentString(); em.printout(":: ");
			if (ast.e2 != null) {
				ast.e2.visit(this, o);				
			}
			else
				em.printout("1 ");
			em.printout(" -> " + newline());			
			//inScope();
				if (!(ast.o instanceof CompStmtAST)) inScope();
				ast.o.visit(this, o);			
				if (!(ast.o instanceof CompStmtAST)) outScope();
				inScope();
				indentString();
				em.printout("lb_" + frm.getjumpLabel() + ": skip;" + newline());
				ast.e3.visit(this, "for");
				em.printout(";"+newline());				
				outScope();
			//outScope();
			indentString(); em.printout(":: else -> break;" + newline());
			indentString(); em.printout("od;"+newline());			
			mapList.store_line_end_block(line_start_block, line);
			indentString();
			em.printout("lb_" + frm.getBreakLabel() + ": skip;" + newline());
			frm.exitLoop();
		} catch (Exception e) {}
		inLineFor = false;
		return null;
	}
	
	//ForInitAST
	//public int type; //1:localVarDecl, 2: expressions, 3:null
	//public DeclarationListAST d;
	//public ExprListAST e;
	public Object visitForInitAST(ForInitAST ast, Object o)
			throws CompilationException {
		if (ast.type == 1) {
			ast.d.visit(this, o);
		}
		if (ast.type == 2) {
			ast.e.visit(this, o);
		}
		return null;
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		try {
			mapList.createNode();
			mapList.store_line(ast.line, line);
			mapList.store_str(ast.line_str);
			mapList.finish();
			indentString();
			em.printout("c ! ");
			ast.e.visit(this, o);
			em.printout("; goto lb_" + frm.getEndLabel() + ";"+newline());
		} catch (Exception e) {}
		return null;
	}
	
	//extended grammar:
	// TypeListAST
	//TypeAST t;
	//TypeListAST tl;	
	public Object visitTypeListAST(TypeListAST ast, Object o)
			throws CompilationException {
		String check = (String) o;
		if (check != null && check.equals("function")) {
			// typeList of returnType function
			String ret = (String) ast.t.visit(this, o);				
			//em.printout(" ");
			String ret2 = (String) ast.tl.visit(this, o);
			if (ret.equals("unsigned") || ret.equals("signed"))			
				return ret2;		
			else			
				return ret;
		}
		else if (check != null && check.equals("var_function")) {
			//typeList of variable of function
			String ret = (String) ast.t.visit(this, null);
			//em.printout(" ");
			String ret2 = (String) ast.tl.visit(this, null);
			if (ret.equals("unsigned") || ret.equals("signed"))			
				return ret2;		
			else			
				return ret;
		}
		else {
			//System.out.println(" typeList of normal declaration");
			// typeList of normal declaration
			ast.t.visit(this, o);
			//em.printout(" ");
			ast.tl.visit(this, o);
			return null;
		}
	}

	//////////////////////////////////////////////////////////////////
	// DoubleTypeAST
	public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("double");
		return "int";
	}
	
	// WCharTTypeAST
	public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("wchar_t");
		return null;
	}
	
	// SignedTypeAST
	public Object visitSignedTypeAST(SignedTypeAST ast, Object o)
			throws CompilationException {
		//em.printout("signed");
		return "signed";
	}

	// UnsignedTypeAST
	public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o)
			throws CompilationException {
		//em.printout("unsigned");
		return "unsigned";
	}
	
	// LongTypeAST
	public Object visitLongTypeAST(LongTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("long");
		return "int";
	}
	
	// ShortTypeAST
	public Object visitShortTypeAST(ShortTypeAST ast, Object o)
			throws CompilationException {
		String checkFunction = (String) o;
		if (checkFunction != null && checkFunction.equals("function"))
			em.printout("proctype");
		else
			em.printout("short");
		return "short";
	}
	//////////////////////////////////////////////////////////////////
	
	//DefineDirectiveAST #define
	//public Token id;
	//public ExprAST l;
	
	public Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) 
		throws CompilationException
	{		
		indentString();
		em.printout("#define ");
		em.printout(em.emitVAR(ast.id.getText(), scope));
		em.printout(" ");
		ast.l.visit(this, o);
		em.printout(newline());		
		return null;
	}
	
	// CallExprAST
	//public Token 		name;
	//public ExprListAST	e;
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {
		
		return null;
	}
	
}
