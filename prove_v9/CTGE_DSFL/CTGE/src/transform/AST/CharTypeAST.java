package transform.AST;

import transform.CodeGeneration.Visitor;

public class CharTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCharTypeAST(this,o);
	}
}