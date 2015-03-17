package Transformer.ASTs;

public class ExprStmtAST extends OneStmtAST{
	public ExprAST e;
	public String line_str;
	
	public ExprStmtAST(ExprAST ex) {
		e = ex;
		e.parent = this;
	}
	
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitExprStmtAST(this,o);
	}
}
