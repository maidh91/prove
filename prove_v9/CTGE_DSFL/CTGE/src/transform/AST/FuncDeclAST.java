package transform.AST;

import org.antlr.runtime.Token;

import transform.CodeGeneration.Visitor;

public class FuncDeclAST
        extends DeclarationAST
{
    public Token name;
    public ParaListAST para;
    public CompStmtAST c;
    public TypeAST retType;
    
    public FuncDeclAST(Token n, ParaListAST pl, TypeAST rt, CompStmtAST comp)
    {
        this.name = n;
        this.para = pl;
        this.retType = rt;
        this.c = comp;
        // para.parent = ret.parent = c.parent = this;
        this.para.parent = this.retType.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitFuncDeclAST(this, o);
    }
}
