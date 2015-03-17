package transform.AST;

import org.antlr.runtime.Token;


import transform.CodeGeneration.Visitor;

public class ParaAST
        extends AST
{
    public Token id;
    public TypeAST t;
    public boolean ref; // true if pass by reference, false otherwise
    
    public ParaAST(Token i, TypeAST type, boolean r)
    {
        this.id = i;
        this.t = type;
        this.ref = r;
        this.t.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitParaAST(this, o);
    }
}
