//
package transform.AST;

import transform.CodeGeneration.Visitor;;

public class ProgramAST
        extends AST
{
    public DeclarationListAST dl;
    
    public ProgramAST(DeclarationListAST decll)
    {
        this.dl = decll;
        this.dl.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitProgramAST(this, o);
    }
}
