package transform.AST;

import transform.CodeGeneration.Visitor;

public class BoolTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitBoolTypeAST(this,o);
	}
}