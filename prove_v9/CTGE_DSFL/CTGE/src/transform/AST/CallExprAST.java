package transform.AST;

import org.antlr.runtime.*;


import transform.CodeGeneration.Visitor;

public class CallExprAST extends ExprAST {
	public Token 		name;
	public ExprListAST	e;
	public CallExprAST(Token t, ExprListAST exp){
		name=t;
		e=exp;
		e.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCallExprAST(this,o);
	}	
}
