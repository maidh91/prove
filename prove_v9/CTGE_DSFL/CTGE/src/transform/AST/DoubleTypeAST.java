package transform.AST;

import transform.CodeGeneration.Visitor;

public class DoubleTypeAST extends PrimTypeAST {

	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitDoubleTypeAST(this,o);
	}

}
