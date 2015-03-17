package transform.AST;

import org.antlr.runtime.*;


import transform.CodeGeneration.Visitor;

public class CharLiteralAST extends LiteralAST{
		
	public CharLiteralAST(Token t){
		literal  = t;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCharLiteralAST(this,o);
	}
}