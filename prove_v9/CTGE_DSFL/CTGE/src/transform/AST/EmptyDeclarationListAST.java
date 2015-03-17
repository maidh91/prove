package transform.AST;

import transform.CodeGeneration.Visitor;

public class EmptyDeclarationListAST extends DeclarationListAST {
	public EmptyDeclarationListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyDeclarationListAST(this,o);
	}
}