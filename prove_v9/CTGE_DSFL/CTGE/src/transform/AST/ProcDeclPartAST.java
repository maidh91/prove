package transform.AST;

import transform.CodeGeneration.Visitor;

public class ProcDeclPartAST
        extends AST
{
    public OneProcDeclAST o;
    public ProcDeclPartAST p;
    
    public ProcDeclPartAST()
    {
        this.o = null;
        this.p = null;
    }
    
    public ProcDeclPartAST(OneProcDeclAST one, ProcDeclPartAST pro)
    {
        this.o = one;
        this.p = pro;
        this.o.parent = this.p.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitProcDeclPartAST(this, o);
    }
}
