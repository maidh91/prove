package transform.CodeGeneration;

import system.Path;
import transform.AST.*;

public class FormalPathPrintVisitor
        extends PrettyOutputVisitor
{
    public FormalPathPrintVisitor(String pathCondFile, boolean debug) throws CompilationException
    {
        super(pathCondFile, debug);
    }
    
    // VarDeclAST
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        this.indentString();
        if (ast.t instanceof ArrayTypeAST) {
            ast.t.visit(this, ast.id.getText());
            return null;
        }
        // ast.t.visit(this, o); not print type in path
        this.get_EM().printout("(");
        this.get_EM().printout(ast.id.getText());
        if (ast.init != null) {
            this.get_EM().printout(" = ");
            ast.init.visit(this, o);
        }
        this.get_EM().printout(")");
        return null;
    }
    
    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        this.get_EM().printout(ast.name.getText());
        return ast.name.getText();
    }
    
    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        this.indentString();
        if (!(ast.e instanceof TernaryExprAST)) {
            // ast.line = this.line;
            this.get_EM().setFilter(true);
        }
        String short_if = (String) ast.e.visit(this, o);
        if ((short_if != null) && short_if.equals("short_if")) {
            return null;
        }
        // this.get_EM().printout(this.newline());
        if (!(ast.e instanceof TernaryExprAST)) {
            ast.line_str = this.get_EM().setFilter(false);
        }
        return null;
    }
    
    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        // String checkAssign = (String) o;
        // boolean checkAssgn = false;
        // if ((checkAssign != null) && checkAssign.equals("assign")) {
        // checkAssgn = true;
        // }
        
        if ((ast.opType >= 24) && (ast.opType <= 28)) {
            // opType: + - * / %
            // if (!checkAssgn) {
            this.get_EM().printout("(");
            // }
            ast.e1.visit(this, null);
            this.get_EM().printout(" " + ast.op.getText() + " ");
            ast.e2.visit(this, null);
            // if (!checkAssgn) {
            this.get_EM().printout(")");
            // }
        }
        else {
            // boolean assiStmt = false;
            // if ((ast.opType == 0) && (ast.parent instanceof ExprStmtAST)) {
            // assiStmt = true;
            // }
            // else {
            this.get_EM().printout("(");
            // }
            
            ast.e1.visit(this, null);
            this.get_EM().printout(" " + ast.op.getText() + " ");
            // if (assiStmt) {
            // ast.e2.visit(this, "assign");
            // }
            // else {
            ast.e2.visit(this, null);
            // }
            // if (!assiStmt) {
            this.get_EM().printout(")");
            // }
        }
        return null;
    }
    
    // Print path in the Formal Format
    public void printFormal(Path pathCondition) throws CompilationException
    {
        for (int i = 0; i < pathCondition.getPath().size(); i++) {
            if (pathCondition.getBranch().get(i) == 0) {
                this.get_EM().printout("not");
            }
            pathCondition.getPath().get(i).visit(this, "");
            this.get_EM().printout("\n");
        }
        this.get_EM().printToC();
    }
}
