/**
 * 
 */
package transform.CodeGeneration;

import java.util.ArrayList;

import system.Parameter;
import system.Variable;
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

/**
 * @author Trung Hai
 * 
 */
public class VariableVisitor
        implements Visitor
{
    ArrayList<Parameter> listPara;
    ArrayList<Variable> listVar;
    
    public VariableVisitor()
    {
        this.listPara = new ArrayList<Parameter>();
        this.listVar = new ArrayList<Variable>();
    }
    
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        ast.dl.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        ast.d.visit(this, null);
        if (ast.dl != null) {
            ast.dl.visit(this, null);
        }
        return null;
    }
    
    @Override
    public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitVarDeclPartAST(VarDeclPartAST ast, Object o) throws CompilationException
    {
        ast.v.visit(this, null);
        if (ast.vp != null) {
            ast.vp.visit(this, null);
        }
        return null;
    }
    
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        String type = (String) ast.t.visit(this, null);
        String varName = ast.id.getText();
        Variable v = new Variable(type, varName);
        this.listVar.add(v);
        return null;
    }
    
    @Override
    public Object visitEmptyVarDeclPartAST(EmptyVarDeclPartAST ast, Object o) throws CompilationException
    {
        ast.v.visit(this, null);
        ast.vp.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitIntTypeAST(IntTypeAST ast, Object o) throws CompilationException
    {
        return "Int";
    }
    
    @Override
    public Object visitFloatTypeAST(FloatTypeAST ast, Object o) throws CompilationException
    {
        return "Real";
    }
    
    @Override
    public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return "Double";
    }
    
    @Override
    public Object visitBoolTypeAST(BoolTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return "Bool";
    }
    
    @Override
    public Object visitArrayTypeAST(ArrayTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitVoidTypeAST(VoidTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCharTypeAST(CharTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitPointerTypeAST(PointerTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitSignedTypeAST(SignedTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitLongTypeAST(LongTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitShortTypeAST(ShortTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitTypeListAST(TypeListAST ast, Object o) throws CompilationException
    {
        return ast.t.visit(this, null);
    }
    
    @Override
    public Object visitEmptyTypeListAST(EmptyTypeListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEmptyProcDeclPartAST(EmptyProcDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitProcDeclAST(ProcDeclAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitFuncDeclAST(FuncDeclAST ast, Object o) throws CompilationException
    {
        ast.para.visit(this, null);
        ast.c.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitParaAST(ParaAST ast, Object o) throws CompilationException
    {
        String type = (String) ast.t.visit(this, null);
        String paraName = ast.id.getText();
        Parameter p = new Parameter(type, paraName);
        this.listPara.add(p);
        
        return null;
    }
    
    @Override
    public Object visitParaListAST(ParaListAST ast, Object o) throws CompilationException
    {
        if (ast.v != null) {
            ast.v.visit(this, null);
        }
        if (ast.p != null) {
            ast.p.visit(this, null);
        }
        return null;
    }
    
    @Override
    public Object visitEmptyParaListAST(EmptyParaListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitGlobalVarDeclPartAST(GlobalVarDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitStmtListAST(StmtListAST ast, Object o) throws CompilationException
    {
        ast.o.visit(this, null);
        ast.s.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCompStmtAST(CompStmtAST ast, Object o) throws CompilationException
    {
        ast.s.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitAssiStmtAST(AssiStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitForStmtAST(ForStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o) throws CompilationException
    {
        ast.e.visit(this, null);
        ast.s.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o) throws CompilationException
    {
        ast.e.visit(this, null);
        ast.s1.visit(this, null);
        ast.s2.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitRepeatStmtAST(RepeatStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitWhileStmtAST(WhileStmtAST ast, Object o) throws CompilationException
    {
        ast.e.visit(this, null);
        ast.o.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitDoStmtAST(DoStmtAST ast, Object o) throws CompilationException
    {
        ast.e.visit(this, null);
        ast.o.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCallStmtAST(CallStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        ast.e.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCaseStmtAST(CaseStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        if (ast.dl != null) {
            ast.dl.visit(this, null);
        }
        return null;
    }
    
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        ast.e1.visit(this, null);
        return null;
    }
    
    @Override
    public Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitTernaryExprAST(TernaryExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEmptyExprListAST(EmptyExprListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitIntLiteralListAST(IntLiteralListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEmptyIntLiteralListAST(EmptyIntLiteralListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitStringLiteralAST(StringLiteralAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCharLiteralAST(CharLiteralAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitNullExprAST(NullExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCastExprAST(CastExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitCastInt2FloatAST(CastInt2FloatAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEmptyVarInitializerListAST(EmptyVarInitializerListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEmptyArrayInitializerListAST(EmptyArrayInitializerListAST ast, Object o)
            throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitEmptyArrayInitializerAST(EmptyArrayInitializerAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitForInitAST(ForInitAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ArrayList<Parameter> getListPara()
    {
        return this.listPara;
    }
    
    public ArrayList<Variable> getListVar()
    {
        return this.listVar;
    }
    
}
