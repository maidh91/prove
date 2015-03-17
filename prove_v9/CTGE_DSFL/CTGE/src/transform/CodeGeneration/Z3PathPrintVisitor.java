/**
 * 
 */
package transform.CodeGeneration;

import system.Path;
import transform.AST.*;

/**
 * @author Trung Hai
 * 
 */
public class Z3PathPrintVisitor
        extends FormalPathPrintVisitor
{
    
    /**
     * @param fileName
     * @param debug
     * @throws CompilationException
     */
    public Z3PathPrintVisitor(String fileName, boolean debug) throws CompilationException
    {
        super(fileName, debug);
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
        if (ast.init != null) {
            this.get_EM().printout("(");
            this.get_EM().printout("= ");
            this.get_EM().printout(ast.id.getText());
            this.get_EM().printout(" ");
            ast.init.visit(this, o);
            this.get_EM().printout(")");
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
            if (ast.op.getText().equals("/")) {
                this.get_EM().printout("div ");
            }
            else if (ast.op.getText().equals("%")) {
                this.get_EM().printout("mod ");
            }
            else {
                this.get_EM().printout(ast.op.getText() + " ");
            }
            ast.e1.visit(this, null);
            this.get_EM().printout(" ");
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
            if (ast.op.getText().equals("||")) {
                this.get_EM().printout("or ");
            }
            else if (ast.op.getText().equals("&&")) {
                this.get_EM().printout("and ");
            }
            else if (ast.op.getText().equals("==")) {
                this.get_EM().printout("= ");
            }
            else {
                this.get_EM().printout(ast.op.getText() + " ");
            }
            ast.e1.visit(this, null);
            this.get_EM().printout(" ");
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
    
    public void printSMT2(Path pathCondition) throws CompilationException
    {
        for (int i = 0; i < pathCondition.getPath().size(); i++) {
            this.get_EM().printout("(assert ");
            if (pathCondition.getBranch().get(i) == 0) {
                this.get_EM().printout("(not ");
            }
            pathCondition.getPath().get(i).visit(this, "");
            if (pathCondition.getBranch().get(i) == 0) {
                this.get_EM().printout(")");
            }
            this.get_EM().printout(")");
            this.get_EM().printout("\n");
        }
        this.get_EM().printout("(check-sat)\n");
        this.get_EM().printout("(model)\n");
        this.get_EM().printToC(true);
    }
}
