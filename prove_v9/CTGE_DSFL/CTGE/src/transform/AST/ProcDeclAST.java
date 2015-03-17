package transform.AST;

import org.antlr.runtime.Token;

import transform.CodeGeneration.Visitor;

public class ProcDeclAST
        extends OneProcDeclAST
{
    public Token name;
    public ParaListAST para;
    public CompStmtAST c;
    
    public ProcDeclAST(Token n, ParaListAST pl, CompStmtAST comp)
    {
        this.name = n;
        this.para = pl;
        this.c = comp;
        this.para.parent = this.c.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitProcDeclAST(this, o);
    }
}
