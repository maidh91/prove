package transform.AST;

import transform.CodeGeneration.Visitor;

public class VarInitializerAST
        extends InitializerAST
{
    public ExprAST e;
    
    public VarInitializerAST(ExprAST ex)
    {
        this.e = ex;
        this.e.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitVarInitializerAST(this, o);
    }
    
}
