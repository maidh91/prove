package transform.AST;

import transform.CodeGeneration.Visitor;

public class ShortTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitShortTypeAST(this,o);
	}
}
