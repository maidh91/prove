package transform.AST;

import transform.CodeGeneration.Visitor;

public class CaseStmtAST extends OneStmtAST {
	public ExprAST e;
	public StmtListAST s;
	public CaseStmtAST(ExprAST expr,StmtListAST sl){
		//name=t;
		e=expr;
		s=sl;
		e.parent= this;
		s.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCaseStmtAST(this,o);
	}
}
