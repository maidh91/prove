package transform.AST;

import org.antlr.runtime.Token;

import transform.CodeGeneration.Visitor;

public class VarExprAST
        extends LvalueAST
{
    public Token name;
    
    public VarExprAST(Token t)
    {
        this.name = t;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitVarExprAST(this, o);
    }
}
