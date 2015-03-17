/**
 * 
 */
package transform.CodeGeneration;

import org.antlr.runtime.CommonToken;

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
import transform.AST.DeclarationAST;
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
import transform.AST.ExprAST;
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
import transform.AST.InitializerAST;
import transform.AST.IntLiteralAST;
import transform.AST.IntLiteralListAST;
import transform.AST.IntTypeAST;
import transform.AST.LongTypeAST;
import transform.AST.NullExprAST;
import transform.AST.OneStmtAST;
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
import transform.AST.TypeAST;
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
public class CopyASTVisitor
        implements Visitor
{
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitProgramAST(Transformer.ASTs.ProgramAST, java.lang.Object)
     */
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        try {
            ProgramAST p = (ProgramAST) ast.clone();
            p.dl = (DeclarationListAST) ast.dl.visit(this, null);
            
            return p;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitDeclarationListAST(Transformer.ASTs.DeclarationListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        try {
            DeclarationListAST dl = (DeclarationListAST) ast.clone();
            dl.d = (DeclarationAST) ast.d.visit(this, null);
            dl.dl = (DeclarationListAST) ast.dl.visit(this, null);
            
            return dl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyDeclarationListAST(Transformer.ASTs.EmptyDeclarationListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o) throws CompilationException
    {
        try {
            EmptyDeclarationListAST edl = (EmptyDeclarationListAST) ast.clone();
            if (ast.d != null) {
                edl.d = (DeclarationAST) ast.d.visit(this, null);
            }
            if (ast.dl != null) {
                edl.dl = (DeclarationListAST) ast.dl.visit(this, null);
            }
            
            return edl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitVarDeclPartAST(Transformer.ASTs.VarDeclPartAST, java.lang.Object)
     */
    @Override
    public Object visitVarDeclPartAST(VarDeclPartAST ast, Object o) throws CompilationException
    {
        try {
            VarDeclPartAST vdp = (VarDeclPartAST) ast.clone();
            vdp.v = (VarDeclAST) ast.v.visit(this, null);
            if (ast.vp != null) {
                vdp.vp = (VarDeclPartAST) ast.vp.visit(this, null);
            }
            
            return vdp;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitVarDeclAST(Transformer.ASTs.VarDeclAST, java.lang.Object)
     */
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        try {
            VarDeclAST vd = (VarDeclAST) ast.clone();
            vd.t = (TypeAST) ast.t.visit(this, null);
            vd.init = (InitializerAST) ast.init.visit(this, null);
            
            return vd;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyVarDeclPartAST(Transformer.ASTs.EmptyVarDeclPartAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyVarDeclPartAST(EmptyVarDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitIntTypeAST(Transformer.ASTs.IntTypeAST, java.lang.Object)
     */
    @Override
    public Object visitIntTypeAST(IntTypeAST ast, Object o) throws CompilationException
    {
        try {
            IntTypeAST it = (IntTypeAST) ast.clone();
            
            return it;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitFloatTypeAST(Transformer.ASTs.FloatTypeAST, java.lang.Object)
     */
    @Override
    public Object visitFloatTypeAST(FloatTypeAST ast, Object o) throws CompilationException
    {
        try {
            FloatTypeAST ft = (FloatTypeAST) ast.clone();
            
            return ft;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitDoubleTypeAST(Transformer.ASTs.DoubleTypeAST, java.lang.Object)
     */
    @Override
    public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o) throws CompilationException
    {
        try {
            DoubleTypeAST dt = (DoubleTypeAST) ast.clone();
            
            return dt;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitBoolTypeAST(Transformer.ASTs.BoolTypeAST, java.lang.Object)
     */
    @Override
    public Object visitBoolTypeAST(BoolTypeAST ast, Object o) throws CompilationException
    {
        try {
            BoolTypeAST bt = (BoolTypeAST) ast.clone();
            
            return bt;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitArrayTypeAST(Transformer.ASTs.ArrayTypeAST, java.lang.Object)
     */
    @Override
    public Object visitArrayTypeAST(ArrayTypeAST ast, Object o) throws CompilationException
    {
        try {
            ArrayTypeAST at = (ArrayTypeAST) ast.clone();
            at.type = (TypeAST) ast.type.visit(this, null);
            at.el = (ExprListAST) ast.el.visit(this, null);
            
            return at;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitVoidTypeAST(Transformer.ASTs.VoidTypeAST, java.lang.Object)
     */
    @Override
    public Object visitVoidTypeAST(VoidTypeAST ast, Object o) throws CompilationException
    {
        try {
            VoidTypeAST vt = (VoidTypeAST) ast.clone();
            
            return vt;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCharTypeAST(Transformer.ASTs.CharTypeAST, java.lang.Object)
     */
    @Override
    public Object visitCharTypeAST(CharTypeAST ast, Object o) throws CompilationException
    {
        try {
            CharTypeAST ct = (CharTypeAST) ast.clone();
            
            return ct;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitWCharTTypeAST(Transformer.ASTs.WCharTTypeAST, java.lang.Object)
     */
    @Override
    public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitPointerTypeAST(Transformer.ASTs.PointerTypeAST, java.lang.Object)
     */
    @Override
    public Object visitPointerTypeAST(PointerTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitSignedTypeAST(Transformer.ASTs.SignedTypeAST, java.lang.Object)
     */
    @Override
    public Object visitSignedTypeAST(SignedTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitUnsignedTypeAST(Transformer.ASTs.UnsignedTypeAST, java.lang.Object)
     */
    @Override
    public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitLongTypeAST(Transformer.ASTs.LongTypeAST, java.lang.Object)
     */
    @Override
    public Object visitLongTypeAST(LongTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitShortTypeAST(Transformer.ASTs.ShortTypeAST, java.lang.Object)
     */
    @Override
    public Object visitShortTypeAST(ShortTypeAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitTypeListAST(Transformer.ASTs.TypeListAST, java.lang.Object)
     */
    @Override
    public Object visitTypeListAST(TypeListAST ast, Object o) throws CompilationException
    {
        try {
            TypeListAST tl = (TypeListAST) ast.clone();
            tl.t = (TypeAST) ast.t.visit(this, null);
            tl.tl = (TypeListAST) ast.tl.visit(this, null);
            
            return tl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyTypeListAST(Transformer.ASTs.EmptyTypeListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyTypeListAST(EmptyTypeListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitProcDeclPartAST(Transformer.ASTs.ProcDeclPartAST, java.lang.Object)
     */
    @Override
    public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyProcDeclPartAST(Transformer.ASTs.EmptyProcDeclPartAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyProcDeclPartAST(EmptyProcDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitProcDeclAST(Transformer.ASTs.ProcDeclAST, java.lang.Object)
     */
    @Override
    public Object visitProcDeclAST(ProcDeclAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitFuncDeclAST(Transformer.ASTs.FuncDeclAST, java.lang.Object)
     */
    @Override
    public Object visitFuncDeclAST(FuncDeclAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitParaAST(Transformer.ASTs.ParaAST, java.lang.Object)
     */
    @Override
    public Object visitParaAST(ParaAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitParaListAST(Transformer.ASTs.ParaListAST, java.lang.Object)
     */
    @Override
    public Object visitParaListAST(ParaListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyParaListAST(Transformer.ASTs.EmptyParaListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyParaListAST(EmptyParaListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitGlobalVarDeclPartAST(Transformer.ASTs.GlobalVarDeclPartAST,
     * java.lang.Object)
     */
    @Override
    public Object visitGlobalVarDeclPartAST(GlobalVarDeclPartAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitStmtListAST(Transformer.ASTs.StmtListAST, java.lang.Object)
     */
    @Override
    public Object visitStmtListAST(StmtListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyStmtListAST(Transformer.ASTs.EmptyStmtListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCompStmtAST(Transformer.ASTs.CompStmtAST, java.lang.Object)
     */
    @Override
    public Object visitCompStmtAST(CompStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitAssiStmtAST(Transformer.ASTs.AssiStmtAST, java.lang.Object)
     */
    @Override
    public Object visitAssiStmtAST(AssiStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitForStmtAST(Transformer.ASTs.ForStmtAST, java.lang.Object)
     */
    @Override
    public Object visitForStmtAST(ForStmtAST ast, Object o) throws CompilationException
    {
        try {
            ForStmtAST fs = (ForStmtAST) ast.clone();
            fs.e1 = (ForInitAST) ast.e1.visit(this, null);
            fs.e2 = (ExprAST) ast.e2.visit(this, null);
            fs.e3 = (ExprListAST) ast.e3.visit(this, null);
            fs.o = (OneStmtAST) ast.o.visit(this, null);
            
            return fs;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitIfThenStmtAST(Transformer.ASTs.IfThenStmtAST, java.lang.Object)
     */
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o) throws CompilationException
    {
        try {
            IfThenStmtAST its = (IfThenStmtAST) ast.clone();
            its.e = (ExprAST) ast.e.visit(this, null);
            if (ast.s != null) {
                its.s = (OneStmtAST) ast.s.visit(this, null);
            }
            
            return its;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitIfThenElseStmtAST(Transformer.ASTs.IfThenElseStmtAST,
     * java.lang.Object)
     */
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o) throws CompilationException
    {
        try {
            IfThenElseStmtAST ites = (IfThenElseStmtAST) ast.clone();
            ites.e = (ExprAST) ast.e.visit(this, null);
            ites.s1 = (OneStmtAST) ast.s1.visit(this, null);
            ites.s2 = (OneStmtAST) ast.s2.visit(this, null);
            
            return ites;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitRepeatStmtAST(Transformer.ASTs.RepeatStmtAST, java.lang.Object)
     */
    @Override
    public Object visitRepeatStmtAST(RepeatStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitWhileStmtAST(Transformer.ASTs.WhileStmtAST, java.lang.Object)
     */
    @Override
    public Object visitWhileStmtAST(WhileStmtAST ast, Object o) throws CompilationException
    {
        try {
            WhileStmtAST ws = (WhileStmtAST) ast.clone();
            ws.e = (ExprAST) ast.e.visit(this, null);
            ws.o = (OneStmtAST) ast.o.visit(this, null);
            
            return ws;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitDoStmtAST(Transformer.ASTs.DoStmtAST, java.lang.Object)
     */
    @Override
    public Object visitDoStmtAST(DoStmtAST ast, Object o) throws CompilationException
    {
        try {
            DoStmtAST ds = (DoStmtAST) ast.clone();
            ds.e = (ExprAST) ast.e.visit(this, null);
            if (ast.o != null) {
                ds.o = (OneStmtAST) ast.o.visit(this, null);
            }
            return ds;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitBreakStmtAST(Transformer.ASTs.BreakStmtAST, java.lang.Object)
     */
    @Override
    public Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitContStmtAST(Transformer.ASTs.ContStmtAST, java.lang.Object)
     */
    @Override
    public Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitRetStmtAST(Transformer.ASTs.RetStmtAST, java.lang.Object)
     */
    @Override
    public Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException
    {
        try {
            RetStmtAST rs = (RetStmtAST) ast.clone();
            rs.e = (ExprAST) ast.e.visit(this, null);
            
            return rs;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCallStmtAST(Transformer.ASTs.CallStmtAST, java.lang.Object)
     */
    @Override
    public Object visitCallStmtAST(CallStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitExprStmtAST(Transformer.ASTs.ExprStmtAST, java.lang.Object)
     */
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        try {
            ExprStmtAST ex = (ExprStmtAST) ast.clone();
            ex.e = (ExprAST) ast.e.visit(this, null);
            
            return ex;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitSwitchStmtAST(Transformer.ASTs.SwitchStmtAST, java.lang.Object)
     */
    @Override
    public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCaseStmtAST(Transformer.ASTs.CaseStmtAST, java.lang.Object)
     */
    @Override
    public Object visitCaseStmtAST(CaseStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitDefaultStmtAST(Transformer.ASTs.DefaultStmtAST, java.lang.Object)
     */
    @Override
    public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitDeclarationStmtAST(Transformer.ASTs.DeclarationStmtAST,
     * java.lang.Object)
     */
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        try {
            DeclarationStmtAST ds = (DeclarationStmtAST) ast.clone();
            ds.dl = (DeclarationListAST) ast.dl.visit(this, null);
            
            return ds;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitBinExprAST(Transformer.ASTs.BinExprAST, java.lang.Object)
     */
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        try {
            BinExprAST be = (BinExprAST) ast.clone();
            be.e1 = (ExprAST) ast.e1.visit(this, null);
            be.e2 = (ExprAST) ast.e2.visit(this, null);
            // be.e1.parent = be.e2.parent = be;
            
            return be;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitUnaryExprAST(Transformer.ASTs.UnaryExprAST, java.lang.Object)
     */
    @Override
    public Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitTernaryExprAST(Transformer.ASTs.TernaryExprAST, java.lang.Object)
     */
    @Override
    public Object visitTernaryExprAST(TernaryExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitVarExprAST(Transformer.ASTs.VarExprAST, java.lang.Object)
     */
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        try {
            VarExprAST ve = (VarExprAST) ast.clone();
            ve.name = new CommonToken(ast.name);
            
            return ve;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEleExprAST(Transformer.ASTs.EleExprAST, java.lang.Object)
     */
    @Override
    public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCallExprAST(Transformer.ASTs.CallExprAST, java.lang.Object)
     */
    @Override
    public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitExprListAST(Transformer.ASTs.ExprListAST, java.lang.Object)
     */
    @Override
    public Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException
    {
        try {
            ExprListAST el = (ExprListAST) ast.clone();
            el.e = (ExprAST) ast.e.visit(this, null);
            if (ast.l != null) {
                el.l = (ExprListAST) ast.l.visit(this, null);
            }
            return el;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyExprListAST(Transformer.ASTs.EmptyExprListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyExprListAST(EmptyExprListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitIntLiteralListAST(Transformer.ASTs.IntLiteralListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitIntLiteralListAST(IntLiteralListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyIntLiteralListAST(Transformer.ASTs.EmptyIntLiteralListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyIntLiteralListAST(EmptyIntLiteralListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitIntLiteralAST(Transformer.ASTs.IntLiteralAST, java.lang.Object)
     */
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
        try {
            IntLiteralAST il = (IntLiteralAST) ast.clone();
            
            return il;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitFloatLiteralAST(Transformer.ASTs.FloatLiteralAST, java.lang.Object)
     */
    @Override
    public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException
    {
        try {
            FloatLiteralAST fl = (FloatLiteralAST) ast.clone();
            
            return fl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitStringLiteralAST(Transformer.ASTs.StringLiteralAST,
     * java.lang.Object)
     */
    @Override
    public Object visitStringLiteralAST(StringLiteralAST ast, Object o) throws CompilationException
    {
        try {
            StringLiteralAST sl = (StringLiteralAST) ast.clone();
            
            return sl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitBoolLiteralAST(Transformer.ASTs.BoolLiteralAST, java.lang.Object)
     */
    @Override
    public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o) throws CompilationException
    {
        try {
            BoolLiteralAST bl = (BoolLiteralAST) ast.clone();
            
            return bl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCharLiteralAST(Transformer.ASTs.CharLiteralAST, java.lang.Object)
     */
    @Override
    public Object visitCharLiteralAST(CharLiteralAST ast, Object o) throws CompilationException
    {
        try {
            CharLiteralAST cl = (CharLiteralAST) ast.clone();
            
            return cl;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitNullExprAST(Transformer.ASTs.NullExprAST, java.lang.Object)
     */
    @Override
    public Object visitNullExprAST(NullExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCastExprAST(Transformer.ASTs.CastExprAST, java.lang.Object)
     */
    @Override
    public Object visitCastExprAST(CastExprAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitCastInt2FloatAST(Transformer.ASTs.CastInt2FloatAST,
     * java.lang.Object)
     */
    @Override
    public Object visitCastInt2FloatAST(CastInt2FloatAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitVarInitializerAST(Transformer.ASTs.VarInitializerAST,
     * java.lang.Object)
     */
    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException
    {
        try {
            VarInitializerAST vi = (VarInitializerAST) ast.clone();
            vi.e = (ExprAST) ast.e.visit(this, null);
            
            return vi;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitArrayInitializerAST(Transformer.ASTs.ArrayInitializerAST,
     * java.lang.Object)
     */
    @Override
    public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o) throws CompilationException
    {
        try {
            ArrayInitializerAST ai = (ArrayInitializerAST) ast.clone();
            ai.v = (VarInitializerListAST) ast.v.visit(this, null);
            
            return ai;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitVarInitializerListAST(Transformer.ASTs.VarInitializerListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o) throws CompilationException
    {
        try {
            VarInitializerListAST vil = (VarInitializerListAST) ast.clone();
            ast.v = (VarInitializerAST) ast.v.visit(this, null);
            if (ast.vl != null) {
                ast.vl = (VarInitializerListAST) ast.vl.visit(this, null);
            }
            
            return vil;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitArrayInitializerListAST(Transformer.ASTs.ArrayInitializerListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o) throws CompilationException
    {
        try {
            ArrayInitializerListAST ail = (ArrayInitializerListAST) ast.clone();
            ail.a = (ArrayInitializerAST) ast.a.visit(this, null);
            if (ast.al != null) {
                ail.al = (ArrayInitializerListAST) ast.al.visit(this, null);
            }
            return ail;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * visitor.Visitor#visitEmptyVarInitializerListAST(Transformer.ASTs.EmptyVarInitializerListAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyVarInitializerListAST(EmptyVarInitializerListAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * visitor.Visitor#visitEmptyArrayInitializerListAST(Transformer.ASTs.EmptyArrayInitializerListAST
     * , java.lang.Object)
     */
    @Override
    public Object visitEmptyArrayInitializerListAST(EmptyArrayInitializerListAST ast, Object o)
            throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitEmptyArrayInitializerAST(Transformer.ASTs.EmptyArrayInitializerAST,
     * java.lang.Object)
     */
    @Override
    public Object visitEmptyArrayInitializerAST(EmptyArrayInitializerAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitForInitAST(Transformer.ASTs.ForInitAST, java.lang.Object)
     */
    @Override
    public Object visitForInitAST(ForInitAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see visitor.Visitor#visitDefineDirectiveAST(Transformer.ASTs.DefineDirectiveAST,
     * java.lang.Object)
     */
    @Override
    public Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) throws CompilationException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
