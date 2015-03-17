package transform.AST;

import transform.CodeGeneration.Visitor;

public class IfThenStmtAST extends OneStmtAST {
	public ExprAST		e;
	public OneStmtAST	s;
	public String line_str = "";
	
	public IfThenStmtAST(ExprAST exp,OneStmtAST one){
		e=exp;
		s=one;
		e.parent=s.parent=this;		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitIfThenStmtAST(this,o);
	}	
}