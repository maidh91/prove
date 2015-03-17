package transform.AST;

import transform.CodeGeneration.Visitor;

public class StmtListAST extends AST{
	public OneStmtAST	o;
	public StmtListAST	s;
	public StmtListAST() {
		o = null;
		s = null;
	}
	public StmtListAST(OneStmtAST one, StmtListAST stmt){
		o=one;
		s=stmt;
		s.parent=o.parent=this;		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitStmtListAST(this,o);
	}	
}