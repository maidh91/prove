package transform.AST;

import transform.CodeGeneration.Visitor;

public class ForStmtAST
        extends OneStmtAST
{
    // public Token name;
    public ForInitAST e1;
    public ExprAST e2;
    public ExprListAST e3;
    public OneStmtAST o;
    public String s2 = "";
    
    public ForStmtAST(ForInitAST fi, ExprAST exp2, ExprListAST exp3, OneStmtAST one)
    {
        // name=t;
        this.e1 = fi;
        this.e2 = exp2;
        this.e3 = exp3;
        this.o = one;
        if (this.e1 != null) {
            this.e1.parent = this;
        }
        if (this.e2 != null) {
            this.e2.parent = this;
        }
        if (this.e3 != null) {
            this.e3.parent = this;
        }
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitForStmtAST(this, o);
    }
}
