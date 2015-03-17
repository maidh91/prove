package transform.AST;

import transform.CodeGeneration.Visitor;

public class VarInitializerListAST
        extends InitializerAST
{
    public VarInitializerAST v;
    public VarInitializerListAST vl;
    
    public VarInitializerListAST()
    {
        this.v = null;
        this.vl = null;
    }
    
    public VarInitializerListAST(VarInitializerAST vx, VarInitializerListAST vlx)
    {
        this.v = vx;
        this.vl = vlx;
        this.v.parent = this.vl.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitVarInitializerListAST(this, o);
    }
}
