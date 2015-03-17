package transform.AST;

import transform.CodeGeneration.Visitor;

public class ArrayTypeAST
        extends TypeAST
{
    public TypeAST type;
    public ExprListAST el;
    
    public ArrayTypeAST(TypeAST prim, ExprListAST l)
    {
        this.type = prim;
        this.el = l;
        this.type.parent = this.el.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitArrayTypeAST(this, o);
    }
}
