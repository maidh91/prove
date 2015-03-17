package transform.CodeGeneration;

import transform.AST.ArrayInitializerAST;
import transform.AST.ArrayInitializerListAST;
import transform.AST.ArrayTypeAST;
import transform.AST.AssiStmtAST;
import transform.AST.BinExprAST;
import transform.AST.BoolLiteralAST;
import transform.AST.BoolTypeAST;
import transform.AST.BreakStmtAST;
import transform.AST.CallExprAST;
import transform.AST.CallStmtAST;
import transform.AST.CaseStmtAST;
import transform.AST.CastExprAST;
import transform.AST.CastInt2FloatAST;
import transform.AST.CharLiteralAST;
import transform.AST.CharTypeAST;
import transform.AST.CompStmtAST;
import transform.AST.CompilationException;
import transform.AST.ContStmtAST;
import transform.AST.DeclarationListAST;
import transform.AST.DeclarationStmtAST;
import transform.AST.DefaultStmtAST;
import transform.AST.DefineDirectiveAST;
import transform.AST.DoStmtAST;
import transform.AST.DoubleTypeAST;
import transform.AST.EleExprAST;
import transform.AST.EmptyArrayInitializerAST;
import transform.AST.EmptyArrayInitializerListAST;
import transform.AST.EmptyDeclarationListAST;
import transform.AST.EmptyExprListAST;
import transform.AST.EmptyIntLiteralListAST;
import transform.AST.EmptyParaListAST;
import transform.AST.EmptyProcDeclPartAST;
import transform.AST.EmptyStmtListAST;
import transform.AST.EmptyTypeListAST;
import transform.AST.EmptyVarDeclPartAST;
import transform.AST.EmptyVarInitializerListAST;
import transform.AST.ExprListAST;
import transform.AST.ExprStmtAST;
import transform.AST.FloatLiteralAST;
import transform.AST.FloatTypeAST;
import transform.AST.ForInitAST;
import transform.AST.ForStmtAST;
import transform.AST.FuncDeclAST;
import transform.AST.GlobalVarDeclPartAST;
import transform.AST.IfThenElseStmtAST;
import transform.AST.IfThenStmtAST;
import transform.AST.IntLiteralAST;
import transform.AST.IntLiteralListAST;
import transform.AST.IntTypeAST;
import transform.AST.LongTypeAST;
import transform.AST.NullExprAST;
import transform.AST.ParaAST;
import transform.AST.ParaListAST;
import transform.AST.PointerTypeAST;
import transform.AST.ProcDeclAST;
import transform.AST.ProcDeclPartAST;
import transform.AST.ProgramAST;
import transform.AST.RepeatStmtAST;
import transform.AST.RetStmtAST;
import transform.AST.ShortTypeAST;
import transform.AST.SignedTypeAST;
import transform.AST.StmtListAST;
import transform.AST.StringLiteralAST;
import transform.AST.SwitchStmtAST;
import transform.AST.TernaryExprAST;
import transform.AST.TypeListAST;
import transform.AST.UnaryExprAST;
import transform.AST.UnsignedTypeAST;
import transform.AST.VarDeclAST;
import transform.AST.VarDeclPartAST;
import transform.AST.VarExprAST;
import transform.AST.VarInitializerAST;
import transform.AST.VarInitializerListAST;
import transform.AST.VoidTypeAST;
import transform.AST.WCharTTypeAST;
import transform.AST.WhileStmtAST;

public interface Visitor
{
    
    // Programs
    public abstract Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException;
    
    // DeclarationList
    public abstract Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o)
            throws CompilationException;
    
    // Variable/constant declaration
    public abstract Object visitVarDeclPartAST(VarDeclPartAST ast, Object o) throws CompilationException;
    
    public abstract Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyVarDeclPartAST(EmptyVarDeclPartAST ast, Object o) throws CompilationException;
    
    // Type
    public abstract Object visitIntTypeAST(IntTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitFloatTypeAST(FloatTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitDoubleTypeAST(DoubleTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitBoolTypeAST(BoolTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitArrayTypeAST(ArrayTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitVoidTypeAST(VoidTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCharTypeAST(CharTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitWCharTTypeAST(WCharTTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitPointerTypeAST(PointerTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitSignedTypeAST(SignedTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitLongTypeAST(LongTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitShortTypeAST(ShortTypeAST ast, Object o) throws CompilationException;
    
    public abstract Object visitTypeListAST(TypeListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyTypeListAST(EmptyTypeListAST ast, Object o) throws CompilationException;
    
    // Procedure/function declaration
    public abstract Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyProcDeclPartAST(EmptyProcDeclPartAST ast, Object o) throws CompilationException;
    
    public abstract Object visitProcDeclAST(ProcDeclAST ast, Object o) throws CompilationException;
    
    public abstract Object visitFuncDeclAST(FuncDeclAST ast, Object o) throws CompilationException;
    
    public abstract Object visitParaAST(ParaAST ast, Object o) throws CompilationException;
    
    public abstract Object visitParaListAST(ParaListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyParaListAST(EmptyParaListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitGlobalVarDeclPartAST(GlobalVarDeclPartAST ast, Object o) throws CompilationException;
    
    // statement
    public abstract Object visitStmtListAST(StmtListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCompStmtAST(CompStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitAssiStmtAST(AssiStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitForStmtAST(ForStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitIfThenStmtAST(IfThenStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitRepeatStmtAST(RepeatStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitWhileStmtAST(WhileStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitDoStmtAST(DoStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCallStmtAST(CallStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitSwitchStmtAST(SwitchStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCaseStmtAST(CaseStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitDefaultStmtAST(DefaultStmtAST ast, Object o) throws CompilationException;
    
    public abstract Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException;
    
    // expression
    public abstract Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitTernaryExprAST(TernaryExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyExprListAST(EmptyExprListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitIntLiteralListAST(IntLiteralListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitEmptyIntLiteralListAST(EmptyIntLiteralListAST ast, Object o)
            throws CompilationException;
    
    public abstract Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException;
    
    public abstract Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException;
    
    public abstract Object visitStringLiteralAST(StringLiteralAST ast, Object o) throws CompilationException;
    
    public abstract Object visitBoolLiteralAST(BoolLiteralAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCharLiteralAST(CharLiteralAST ast, Object o) throws CompilationException;
    
    public abstract Object visitNullExprAST(NullExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCastExprAST(CastExprAST ast, Object o) throws CompilationException;
    
    public abstract Object visitCastInt2FloatAST(CastInt2FloatAST ast, Object o) throws CompilationException;
    
    // initializer
    public abstract Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException;
    
    public abstract Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o) throws CompilationException;
    
    public abstract Object visitVarInitializerListAST(VarInitializerListAST ast, Object o) throws CompilationException;
    
    public abstract Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o)
            throws CompilationException;
    
    public abstract Object visitEmptyVarInitializerListAST(EmptyVarInitializerListAST ast, Object o)
            throws CompilationException;
    
    public abstract Object visitEmptyArrayInitializerListAST(EmptyArrayInitializerListAST ast, Object o)
            throws CompilationException;
    
    public abstract Object visitEmptyArrayInitializerAST(EmptyArrayInitializerAST ast, Object o)
            throws CompilationException;
    
    public abstract Object visitForInitAST(ForInitAST ast, Object o) throws CompilationException;
    
    public abstract Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) throws CompilationException;
    
}
