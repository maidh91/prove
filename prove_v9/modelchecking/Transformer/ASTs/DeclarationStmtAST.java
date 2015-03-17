package Transformer.ASTs;

public class DeclarationStmtAST extends OneStmtAST {
	public DeclarationListAST dl;
	public DeclarationStmtAST(DeclarationListAST decl) {
		dl = decl;
		dl.parent = this;
	}
	public Object visit(Visitor v,Object o) throws CompilationException{
		return v.visitDeclarationStmtAST(this,o);
	}
}
