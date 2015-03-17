package transform.CodeGeneration;

import transform.AST.BreakStmtAST;
import transform.AST.CaseStmtAST;
import transform.AST.CompStmtAST;
import transform.AST.CompilationException;
import transform.AST.ContStmtAST;
import transform.AST.DeclarationListAST;
import transform.AST.DeclarationStmtAST;
import transform.AST.DefaultStmtAST;
import transform.AST.DoStmtAST;
import transform.AST.ExprStmtAST;
import transform.AST.ForStmtAST;
import transform.AST.FuncDeclAST;
import transform.AST.IfThenElseStmtAST;
import transform.AST.IfThenStmtAST;
import transform.AST.ProgramAST;
import transform.AST.RetStmtAST;
import transform.AST.StmtListAST;
import transform.AST.SwitchStmtAST;
import transform.AST.WhileStmtAST;
import transform.DependenceGraph.MappingNode;
import transform.DependenceGraph.MappingTable;

/***********************************************************************************
 * Visitor duyet qua cay AST de tao ra MappingTable
 ***********************************************************************************/
public class Ast2MappingTableVisitor
        extends DoNothingVisitor
{
    MappingTable mapTable;
    
    public Ast2MappingTableVisitor()
    {
        this.mapTable = new MappingTable();
    }
    
    public MappingTable getMappingTable()
    {
        return this.mapTable;
    }
    
    public void println(Object o)
    {
        System.out.println(o);
    }
    
    // ProgramAST
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        ast.dl.visit(this, o);
        return null;
    }
    
    // DeclarationListAST
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        ast.d.visit(this, o);
        ast.dl.visit(this, o);
        return null;
    }
    
    // DeclarationStmtAST
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(ast.line, ast));
        ast.dl.visit(this, o);
        return null;
    }
    
    // FuncDeclAST
    @Override
    public Object visitFuncDeclAST(FuncDeclAST fAst, Object o) throws CompilationException
    {
        fAst.c.visit(this, o);
        return null;
    }
    
    // CompStmtAST
    @Override
    public Object visitCompStmtAST(CompStmtAST cAst, Object o) throws CompilationException
    {
        cAst.s.visit(this, o);
        return null;
    }
    
    // StmtListAST
    @Override
    public Object visitStmtListAST(StmtListAST sAst, Object o) throws CompilationException
    {
        sAst.o.visit(this, o);
        sAst.s.visit(this, o);
        return null;
    }
    
    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(ast.line, ast));
        ast.e.visit(this, o);
        return null;
    }
    
    // IfThenStmtAST
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST iAst, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(iAst.line, iAst));
        iAst.e.visit(this, o);
        iAst.s.visit(this, o);
        return null;
    }
    
    // IfThenElseStmtAST
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST iAst, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(iAst.line, iAst));
        iAst.e.visit(this, o);
        iAst.s1.visit(this, o);
        iAst.s2.visit(this, o);
        return null;
    }
    
    // ForStmtAST
    @Override
    public Object visitForStmtAST(ForStmtAST fAst, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(fAst.line, fAst));
        if (fAst.e1 != null) {
            fAst.e1.visit(this, o);
        }
        if (fAst.e2 != null) {
            fAst.e2.visit(this, o);
        }
        if (fAst.e3 != null) {
            fAst.e3.visit(this, o);
        }
        fAst.o.visit(this, o);
        return null;
    }
    
    // WhileStmtAST
    @Override
    public Object visitWhileStmtAST(WhileStmtAST wAst, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(wAst.line, wAst));
        wAst.e.visit(this, o);
        wAst.o.visit(this, o);
        return null;
    }
    
    // DoStmtAST
    @Override
    public Object visitDoStmtAST(DoStmtAST wAst, Object o) throws CompilationException
    {
        this.mapTable.addMappingNode(new MappingNode(wAst.line, wAst));
        wAst.o.visit(this, o);
        wAst.e.visit(this, o);
        return null;
    }
    
    // RetStmtAST
    @Override
    public Object visitRetStmtAST(RetStmtAST rAst, Object o) throws CompilationException
    {
        if (rAst.e != null) {
            this.mapTable.addMappingNode(new MappingNode(rAst.line, rAst));
            rAst.e.visit(this, o);
        }
        return null;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////
    // chua xu ly cac cau lenh ben duoi
    // BreakStmtAST
    @Override
    public Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException
    {
        this.println("BreakStmtAST: " + ast.line);
        return null;
    }
    
    // ContStmtAST
    @Override
    public Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException
    {
        this.println("ContStmtAST: " + ast.line);
        return null;
    }
    
    // SwitchStmtAST
    @Override
    public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o) throws CompilationException
    {
        this.println("SwitchStmtAST: " + sAst.line);
        sAst.e.visit(this, o);
        sAst.o.visit(this, o);
        return null;
    }
    
    // CaseStmtAST
    @Override
    public Object visitCaseStmtAST(CaseStmtAST cAst, Object o) throws CompilationException
    {
        this.println("CaseStmtAST: " + cAst.line);
        cAst.e.visit(this, o);
        cAst.s.visit(this, o);
        return null;
    }
    
    // DefaultStmtAST
    @Override
    public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o) throws CompilationException
    {
        this.println("DefaultStmtAST: " + dAst.line);
        dAst.s.visit(this, o);
        return null;
    }
    //
    ///////////////////////////////////////////////////////////////////////////////////
}
