package transform.AST;

import org.antlr.runtime.*;


import transform.CodeGeneration.Visitor;

public class BreakStmtAST extends OneStmtAST {
	public Token t;
	public BreakStmtAST(Token b){
		t = b;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitBreakStmtAST(this,o);
	}	
}