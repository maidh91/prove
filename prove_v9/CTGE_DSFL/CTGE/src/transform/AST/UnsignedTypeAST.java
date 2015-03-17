package transform.AST;

import transform.CodeGeneration.Visitor;

public class UnsignedTypeAST extends PrimTypeAST {

	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitUnsignedTypeAST(this,o);
	}

}