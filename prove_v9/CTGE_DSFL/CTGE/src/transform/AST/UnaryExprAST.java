package transform.AST;

import org.antlr.runtime.Token;

import transform.CodeGeneration.Visitor;

public class UnaryExprAST
        extends ExprAST
{
    public static final int UNARY_PLUS = 0; // +
    public static final int UNARY_MINUS = 1; // -
    public static final int PRE_INC = 2; // ++a
    public static final int PRE_DEC = 3; // --a
    public static final int POST_INC = 4; // a++
    public static final int POST_DEC = 5; // a--
    public static final int NOT = 6; // ~
    public static final int LOGICAL_NOT = 7; // !
    public static final int INDIRECTION = 8; // * <=== for pointer
    public static final int ADDR_OF = 9; // & <=== for pointer
    
    public Token op;
    public int opType;
    public ExprAST e;
    
    public UnaryExprAST(int o, Token t, ExprAST exp)
    {
        this.op = t;
        this.opType = o;
        this.e = exp;
        this.e.parent = this;
    }
    
    @Override
    public Object visit(Visitor v, Object o) throws CompilationException
    {
        return v.visitUnaryExprAST(this, o);
    }
}
