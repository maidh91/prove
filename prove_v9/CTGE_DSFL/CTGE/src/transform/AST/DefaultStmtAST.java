package transform.AST;

import transform.CodeGeneration.Visitor;

public class DefaultStmtAST extends OneStmtAST {
	public StmtListAST s;
	public DefaultStmtAST(StmtListAST sl){
		s=sl;
		s.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitDefaultStmtAST(this,o);
	}
}
