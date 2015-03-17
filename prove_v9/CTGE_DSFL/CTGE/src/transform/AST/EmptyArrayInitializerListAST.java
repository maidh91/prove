package transform.AST;

import transform.CodeGeneration.Visitor;

public class EmptyArrayInitializerListAST extends ArrayInitializerListAST {
	public EmptyArrayInitializerListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyArrayInitializerListAST(this,o);
	}
}
