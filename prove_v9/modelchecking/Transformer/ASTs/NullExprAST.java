package Transformer.ASTs;

public class NullExprAST extends ExprAST {

	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitNullExprAST(this, o);
	}

}
