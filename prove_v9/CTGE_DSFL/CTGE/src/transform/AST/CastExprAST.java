package transform.AST;

import transform.CodeGeneration.Visitor;

public class CastExprAST extends ExprAST {
	public TypeListAST t;
	public ExprAST e;
	
	public CastExprAST(TypeListAST type, ExprAST expr) {
		t = type;
		e = expr;
		t.parent = e.parent = this;
	}
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitCastExprAST(this, o);
	}

}
