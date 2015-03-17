package transform.AST;

import org.antlr.runtime.Token;

import transform.CodeGeneration.Visitor;

public class VarDeclAST
        extends DeclarationAST
{
    public Token id;
    public TypeAST t;
    public InitializerAST init;
    
    public VarDeclAST()
    {
    }
    
    public VarDeclAST(Token tk, TypeAST type, InitializerAST i)
    {
        this.id = tk;
        this.t = type;
        this.init = i;
        this.t.parent = this;
        if (this.init != null) {
            this.init.parent = this;
        }
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitVarDeclAST(this, o);
    }
}
