package transform.AST;

import transform.CodeGeneration.Visitor;

public class IfThenElseStmtAST extends OneStmtAST {
	public ExprAST		e;
	public OneStmtAST	s1;
	public OneStmtAST	s2;
	public String line_str = "";
	public int line_else = -1;
	
	public IfThenElseStmtAST(ExprAST exp, OneStmtAST stmt1, OneStmtAST stmt2){
		e=exp;
		s1=stmt1;
		s2=stmt2;
		e.parent=s1.parent=s2.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitIfThenElseStmtAST(this,o);
	}	
}