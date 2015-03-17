package transform.AST;

import transform.CodeGeneration.Visitor;

public class EmptyProcDeclPartAST extends ProcDeclPartAST {
	public EmptyProcDeclPartAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyProcDeclPartAST(this,o);
	}
}