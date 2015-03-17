package transform.AST;

import transform.CodeGeneration.Visitor;

public class WCharTTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitWCharTTypeAST(this,o);
	}
}