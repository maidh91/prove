/**
 *
 */
package transform.CodeGeneration;

import transform.AST.*;

/**
 * @author Trung Hai
 *
 */
public class ConditionPrintVisitor
        extends FormalPathPrintVisitor {

    /**
     * @param fileName
     * @param debug
     * @throws CompilationException
     */
    public ConditionPrintVisitor(String fileName, boolean debug) throws CompilationException {
        super(fileName, debug);
    }

    // VarDeclAST
    //@Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException {
        /*this.indentString();
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
         }*/
        return null;
    }

    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException {

        if ((ast.opType >= 24) && (ast.opType <= 28)) {
            this.get_EM().printout("(");
            ast.e1.visit(this, null);
            this.get_EM().printout(ast.op.getText());
            ast.e2.visit(this, null);
            this.get_EM().printout(")");
        } else {
            this.get_EM().printout("(");
            ast.e1.visit(this, null);
            this.get_EM().printout(ast.op.getText());
            ast.e2.visit(this, null);
            this.get_EM().printout(")");
        }
        return null;
    }

    public String print(AST ast) throws CompilationException {
        this.get_EM().reset();
        this.get_EM().setCaseOn(false);
        this.get_EM().setFilter(false, false);
        ast.visit(this, "");
        return this.get_EM().toString();
    }
}
