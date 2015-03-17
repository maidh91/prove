package transform.AST;

import transform.CodeGeneration.Visitor;

public class ArrayInitializerAST extends InitializerAST {
	public VarInitializerListAST v;
	
	public ArrayInitializerAST() {
		v = null;
	}
	
	public ArrayInitializerAST(VarInitializerListAST vx) {
		v = vx;
		v.parent = this;
	}
	
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitArrayInitializerAST(this,o);
	}

}
