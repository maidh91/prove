package transform.AST;

import transform.CodeGeneration.Visitor;

public class ForInitAST
        extends AST
{
    public int type; // 1:localVarDecl, 2: expressions, 3:null
    public DeclarationListAST d;
    public ExprListAST e;
    
    public ForInitAST(int t, DeclarationListAST decl, ExprListAST expr)
    {
        this.type = t;
        this.d = decl;
        this.e = expr;
        if (this.d != null) {
            this.d.parent = this;
        }
        if (this.e != null) {
            this.e.parent = this;
        }
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitForInitAST(this, o);
    }
}
