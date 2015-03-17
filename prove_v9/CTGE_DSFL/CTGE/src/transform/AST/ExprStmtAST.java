package transform.AST;

import transform.CodeGeneration.Visitor;

public class ExprStmtAST
        extends OneStmtAST
{
    public ExprAST e;
    public String line_str;
    
    public ExprStmtAST(ExprAST ex)
    {
        this.e = ex;
        this.e.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitExprStmtAST(this, o);
    }
}
