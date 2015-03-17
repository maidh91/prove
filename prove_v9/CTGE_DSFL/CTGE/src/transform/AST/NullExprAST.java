package transform.AST;

import transform.CodeGeneration.Visitor;

public class NullExprAST extends ExprAST {

	public NullExprAST() {
	}
	
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitNullExprAST(this, o);
	}

}
