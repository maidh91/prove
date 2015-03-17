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

public class DoNothingVisitor
        implements Visitor
{
    
    // ProgramAST
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // DeclarationListAST
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // EmptyDeclarationListAST
    @Override
    public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // VarDeclPartAST
    @Override
    public Object visitVarDeclPartAST(VarDeclPartAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // EmptyVarDeclPartAST
    @Override
    public Object visitEmptyVarDeclPartAST(EmptyVarDeclPartAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // VarDeclAST
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // ArrayTypeAST
    @Override
    public Object visitArrayTypeAST(ArrayTypeAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // EmptyIntLiteralListAST
    @Override
    public Object visitEmptyIntLiteralListAST(EmptyIntLiteralListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // IntLiteralListAST
    @Override
    public Object visitIntLiteralListAST(IntLiteralListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // EmptyExprListAST
    @Override
    public Object visitEmptyExprListAST(EmptyExprListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // ExprListAST
    @Override
    public Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // ProcDeclPartAST
    @Override
    public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // EmptyProDeclPartAST
    @Override
    public Object visitEmptyProcDeclPartAST(EmptyProcDeclPartAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // FuncDeclAST
    @Override
    public Object visitFuncDeclAST(FuncDeclAST fAst, Object o) throws CompilationException
    {
        return null;
    }
    
    // ProcDeclAST
    @Override
    public Object visitProcDeclAST(ProcDeclAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // GlobalVarDeclPartAST
    @Override
    public Object visitGlobalVarDeclPartAST(GlobalVarDeclPartAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // ParaAST
    @Override
    public Object visitParaAST(ParaAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // ParaListAST
    @Override
    public Object visitParaListAST(ParaListAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // EmptyParaListAST
    @Override
    public Object visitEmptyParaListAST(EmptyParaListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // BoolTypeAST
    @Override
    public Object visitBoolTypeAST(BoolTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // IntTypeAST
    @Override
    public Object visitIntTypeAST(IntTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // VoidTypeAST
    @Override
    public Object visitVoidTypeAST(VoidTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // FloatTypeAST
    @Override
    public Object visitFloatTypeAST(FloatTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // DoubleTypeAST
    @Override
    public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CharTypeAST
    @Override
    public Object visitCharTypeAST(CharTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // WCharTTypeAST
    @Override
    public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // SignedTypeAST
    @Override
    public Object visitSignedTypeAST(SignedTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // TypeListAST
    @Override
    public Object visitTypeListAST(TypeListAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // EmptyTypeListAST
    @Override
    public Object visitEmptyTypeListAST(EmptyTypeListAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // UnsignedTypeAST
    @Override
    public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // LongTypeAST
    @Override
    public Object visitLongTypeAST(LongTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // ShortTypeAST
    @Override
    public Object visitShortTypeAST(ShortTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // PointerTypeAST
    @Override
    public Object visitPointerTypeAST(PointerTypeAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // StmtListAST
    @Override
    public Object visitStmtListAST(StmtListAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // EmptyStmtListAST
    @Override
    public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    // CompStmtAST
    @Override
    public Object visitCompStmtAST(CompStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // AssiStmtAST
    @Override
    public Object visitAssiStmtAST(AssiStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // IfThenElseStmtAST
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // IfThenStmtAST
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // RepeatStmtAST
    @Override
    public Object visitRepeatStmtAST(RepeatStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // RetStmtAST
    @Override
    public Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CallStmtAST
    @Override
    public Object visitCallStmtAST(CallStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // ForStmtAST
    @Override
    public Object visitForStmtAST(ForStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // WhileStmtAST
    @Override
    public Object visitWhileStmtAST(WhileStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // DoStmtAST
    @Override
    public Object visitDoStmtAST(DoStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // SwitchStmtAST
    @Override
    public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CaseStmtAST
    @Override
    public Object visitCaseStmtAST(CaseStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // DefaultStmtAST
    @Override
    public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // DefaultStmtAST
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // BreakStmtAST
    @Override
    public Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CompStmtAST
    public Object visitCompStatAST(CompStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // ContStmtAST
    @Override
    public Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // LiteralAST
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // FloatLiteralAST
    @Override
    public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // BoolLiteralAST
    @Override
    public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // StringLiteralAST
    @Override
    public Object visitStringLiteralAST(StringLiteralAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CharLiteralAST
    @Override
    public Object visitCharLiteralAST(CharLiteralAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // UnaryExprAST
    @Override
    public Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // TernaryExprAST
    @Override
    public Object visitTernaryExprAST(TernaryExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // EleExprAST
    @Override
    public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CallExprAST
    @Override
    public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // NullExprAST
    @Override
    public Object visitNullExprAST(NullExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CastExprAST
    @Override
    public Object visitCastExprAST(CastExprAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // CastInt2FloatAST
    @Override
    public Object visitCastInt2FloatAST(CastInt2FloatAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    // initializer
    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    @Override
    public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    @Override
    public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    @Override
    public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o) throws CompilationException
    {
        
        return null;
    }
    
    @Override
    public Object visitEmptyVarInitializerListAST(EmptyVarInitializerListAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    @Override
    public Object visitEmptyArrayInitializerListAST(EmptyArrayInitializerListAST ast, Object o)
            throws CompilationException
    {
        return null;
    }
    
    @Override
    public Object visitEmptyArrayInitializerAST(EmptyArrayInitializerAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    @Override
    public Object visitForInitAST(ForInitAST ast, Object o) throws CompilationException
    {
        return null;
    }
    
    @Override
    public Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) throws CompilationException
    {
        return null;
    }
}
