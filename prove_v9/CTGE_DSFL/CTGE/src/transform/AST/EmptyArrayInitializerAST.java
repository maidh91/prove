package transform.AST;

import transform.CodeGeneration.Visitor;

public class EmptyArrayInitializerAST extends ArrayInitializerAST {
	public EmptyArrayInitializerAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyArrayInitializerAST(this,o);
	}
}
