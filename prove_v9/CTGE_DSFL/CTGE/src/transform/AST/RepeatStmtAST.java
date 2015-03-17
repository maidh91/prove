package transform.AST;

import transform.CodeGeneration.Visitor;

public class RepeatStmtAST extends OneStmtAST {
	public StmtListAST	s;
	public ExprAST		e;
	public RepeatStmtAST(StmtListAST stmt, ExprAST	exp){
		s=stmt;
		e=exp;
		s.parent=e.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitRepeatStmtAST(this,o);
	}	
}