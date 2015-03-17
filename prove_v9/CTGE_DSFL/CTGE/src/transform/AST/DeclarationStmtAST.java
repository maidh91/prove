package transform.AST;

import transform.CodeGeneration.Visitor;

public class DeclarationStmtAST
        extends OneStmtAST
{
    public DeclarationListAST dl;
    
    public DeclarationStmtAST(DeclarationListAST decl)
    {
        this.dl = decl;
        this.dl.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitDeclarationStmtAST(this, o);
    }
}
