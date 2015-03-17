package transform.AST;

import transform.CodeGeneration.Visitor;

public class EmptyTypeListAST extends TypeListAST {
	public EmptyTypeListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyTypeListAST(this,o);
	}
}
