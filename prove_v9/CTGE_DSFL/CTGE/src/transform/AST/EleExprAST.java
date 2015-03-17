package transform.AST;

import org.antlr.runtime.Token;

import transform.CodeGeneration.Visitor;

public class EleExprAST
        extends LvalueAST
{
    public Token name;
    public ExprListAST e;
    
    public EleExprAST(Token t, ExprListAST exp)
    {
        this.name = t;
        this.e = exp;
        this.e.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitEleExprAST(this, o);
    }
}
