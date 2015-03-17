package transform.CodeGeneration;

import transform.AST.ArrayTypeAST;
import transform.AST.BinExprAST;
import transform.AST.BoolLiteralAST;
import transform.AST.BoolTypeAST;
import transform.AST.BreakStmtAST;
import transform.AST.CallExprAST;
import transform.AST.CaseStmtAST;
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
import transform.AST.EmptyStmtListAST;
import transform.AST.ExprListAST;
import transform.AST.ExprStmtAST;
import transform.AST.FloatLiteralAST;
import transform.AST.FloatTypeAST;
import transform.AST.ForInitAST;
import transform.AST.ForStmtAST;
import transform.AST.FuncDeclAST;
import transform.AST.IfThenElseStmtAST;
import transform.AST.IfThenStmtAST;
import transform.AST.IntLiteralAST;
import transform.AST.IntTypeAST;
import transform.AST.LongTypeAST;
import transform.AST.ParaAST;
import transform.AST.ParaListAST;
import transform.AST.PointerTypeAST;
import transform.AST.ProgramAST;
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
import transform.AST.VarExprAST;
import transform.AST.VarInitializerAST;
import transform.AST.VoidTypeAST;
import transform.AST.WCharTTypeAST;
import transform.AST.WhileStmtAST;
import transform.CodeGeneration.Emitter;
import transform.CodeGeneration.Frame;
/**
 * This class is used to standardize the original source code file.
 * The purpose of this standardizing step is to make sure that
 * every statement has its own line (or line number)
 */

public class PrettyOutputVisitor
        extends DoNothingVisitor
{
    Emitter em;
    Frame frm;
    int scope = 0;
    int line = 1;
    /**
     * this property is turn on/off if we want
     * the file contains lines number or not.
     * it was set in by method 'visitProgramAST'
     */
    boolean outputLine;
    
    /*
     * Constructor
     */
    public PrettyOutputVisitor(String outputVerify_c, boolean debug) throws CompilationException
    {
        this.em = new Emitter();
        this.frm = new Frame();
        this.em.setFilename(outputVerify_c);
        
        if (debug == false) {
            this.em.print_to_console = false;
        }
        else {
            System.out.println("\n\nOUTPUT_C: (" + outputVerify_c + ")");
        }
    }
    
    public void setModeOutputLine(boolean on)
    {
        this.outputLine = on;
    }
    
    public String newline()
    {
        String newline_str = "\r\n";
        this.line++;
        return newline_str;
    }
    
    @SuppressWarnings("unused")
    private int getKey()
    {
        return this.frm.getNewLabel();
    }
    
    public void inScope()
    {
        this.scope++;
    }
    
    public void outScope()
    {
        this.scope--;
    }
    
    /*
     * print indent of lines
     */
    public void indentString()
    {
        String indent = "";
        for (int i = 0; i < this.scope; i++) {
            indent += "\t";
        }
        String print_line;
        if (this.line < 10) {
            print_line = "  " + this.line;
        }
        else if (this.line < 100) {
            print_line = " " + this.line;
        }
        else {
            print_line = "" + this.line;
        }
        
        if (!this.outputLine) {
            this.em.printout(indent);
        }
        else {
            this.em.printout(print_line + ":  " + indent);
        }
    }
    
    // ProgramAST
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        String option = (String) o;
        if (option != null) {
            if (option.equals("output_line")) {
                this.setModeOutputLine(true);
            }
            else {
                this.setModeOutputLine(false);
            }
        }
        ast.dl.visit(this, o);
        this.em.printToC();
        return null;
    }
    
    // DeclarationListAST
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        ast.d.visit(this, o);
        if (ast.dl != null) {
            ast.dl.visit(this, o);
        }
        return null;
    }
    
    // VarDeclAST
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        String checkForInit = (String) o;
        if ((checkForInit != null) && checkForInit.equals("forinit")) {
            ast.t.visit(this, o);
            this.em.printout(ast.id.getText());
            if (ast.init != null) {
                this.em.printout(" = ");
                ast.init.visit(this, o);
            }
            return null;
        }
        
        this.indentString();
        if (ast.t instanceof ArrayTypeAST) {
            ast.t.visit(this, ast.id.getText());
            return null;
        }
        ast.t.visit(this, o);
        // em.printout(" ");
        this.em.printout(ast.id.getText());
        if (ast.init != null) {
            this.em.printout(" = ");
            ast.init.visit(this, o);
        }
        this.em.printout(";" + this.newline());
        return null;
    }
    
    // PointerTypeAST
    @Override
    public Object visitPointerTypeAST(PointerTypeAST ast, Object o) throws CompilationException
    {
        ast.t.visit(this, o);
        this.em.printout("*"); // day chi la giai phap tam thoi, can tim hieu them
        return null;
    }
    
    // IntTypeAST
    @Override
    public Object visitIntTypeAST(IntTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("int");
        return null;
    }
    
    // initializer
    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException
    {
        if (ast.e != null) {
            ast.e.visit(this, o);
        }
        return null;
    }
    
    // IntLiteralAST
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.literal.getText());
        return ast.literal.getText();
    }
    
    // BoolTypeAST
    @Override
    public Object visitBoolTypeAST(BoolTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("bool");
        return null;
    }
    
    // VoidTypeAST
    @Override
    public Object visitVoidTypeAST(VoidTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("void");
        return "voidType";
    }
    
    // FloatTypeAST
    @Override
    public Object visitFloatTypeAST(FloatTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("float");
        return null;
    }
    
    // CharTypeAST
    @Override
    public Object visitCharTypeAST(CharTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("char");
        return null;
    }
    
    // FloatLiteralAST
    @Override
    public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.literal.getText());
        return null;
    }
    
    // BoolLiteralAST
    @Override
    public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.literal.getText());
        return null;
    }
    
    // StringLiteralAST
    @Override
    public Object visitStringLiteralAST(StringLiteralAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.literal.getText());
        return null;
    }
    
    // CharLiteralAST
    @Override
    public Object visitCharLiteralAST(CharLiteralAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.literal.getText());
        return null;
    }
    
    // ArrayTypeAST
    @Override
    public Object visitArrayTypeAST(ArrayTypeAST ast, Object o) throws CompilationException
    {
        String id = (String) o;
        ast.type.visit(this, o);
        if (id != null) {
            this.em.printout(id);
            ast.el.visit(this, "arraytype");
            this.em.printout(";" + this.newline());
        }
        return null;
    }
    
    // DeclarationStmtAST
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        ast.dl.visit(this, o);
        return null;
    }
    
    /*
     * / ProcDeclPartAST
     * public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o)
     * throws CompilationException {
     * ast.o.visit(this, o);
     * ast.p.visit(this, o);
     * return null;
     * }
     */

    // FuncDeclAST
    @Override
    public Object visitFuncDeclAST(FuncDeclAST fAst, Object o) throws CompilationException
    {
        fAst.line = this.line;
        this.indentString();
        fAst.retType.visit(this, o);
        this.em.printout(fAst.name.getText());
        this.em.printout("(");
        fAst.para.visit(this, o);
        this.em.printout(")" + this.newline());
        fAst.c.visit(this, o);
        return null;
    }
    
    // ParaListAST
    @Override
    public Object visitParaListAST(ParaListAST ast, Object o) throws CompilationException
    {
        String checkMorePara = (String) o;
        if ((checkMorePara != null) && checkMorePara.equals("morePara")) {
            this.em.printout(", ");
        }
        ast.v.visit(this, o);
        
        if (ast.p.v != null) {
            String morePara = "morePara";
            ast.p.visit(this, morePara);
        }
        else {
            ast.p.visit(this, o);
        }
        
        return null;
    }
    
    // ParaAST
    @Override
    public Object visitParaAST(ParaAST ast, Object o) throws CompilationException
    {
        ast.t.visit(this, null);
        this.em.printout(ast.id.getText());
        if (ast.t instanceof ArrayTypeAST) { // an error here
            this.em.printout("[]");
        }
        return ast.id.getText();
    }
    
    // CompStmtAST
    @Override
    public Object visitCompStmtAST(CompStmtAST ast, Object o) throws CompilationException
    {
        this.indentString();
        this.em.printout("{" + this.newline());
        String checkSwitch = (String) o;
        if ((checkSwitch != null) && checkSwitch.equals("switch")) {
            ast.s.visit(this, "switch");
        }
        else {
            ast.s.visit(this, "block");
        }
        return null;
    }
    
    // StmtListAST
    @Override
    public Object visitStmtListAST(StmtListAST ast, Object o) throws CompilationException
    {
        String checkBlock = (String) o;
        if ((checkBlock != null) && (checkBlock.equals("block") || checkBlock.equals("switch"))) {
            this.inScope();
        }
        ast.o.visit(this, null);
        if ((checkBlock != null) && (checkBlock.equals("case") || checkBlock.equals("switch"))) {
            ast.s.visit(this, o);
        }
        else {
            ast.s.visit(this, "statement");
        }
        return null;
    }
    
    // EmptyStmtListAST
    @Override
    public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o) throws CompilationException
    {
        String checkBlock = (String) o;
        if (checkBlock != null) {
            if (checkBlock.equals("switch")) {
                this.outScope();
                this.outScope();
                this.indentString();
                this.em.printout("}" + this.newline());
            }
            if (checkBlock.equals("block")) {
                this.indentString();
                this.em.printout("}" + this.newline());
            }
            if (checkBlock.equals("statement")) {
                this.outScope();
                this.indentString();
                this.em.printout("}" + this.newline());
            }
        }
        return null;
    }
    
    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.name.getText());
        return ast.name.getText();
    }
    
    // EleExprAST
    // public Token name;
    // public ExprListAST e;
    @Override
    public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.name.getText());
        ast.e.visit(this, "arraytype");
        return null;
    }
    
    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        this.indentString();
        if (!(ast.e instanceof TernaryExprAST)) {
            ast.line = this.line;
            this.em.setFilter(true);
        }
        String short_if = (String) ast.e.visit(this, o);
        if ((short_if != null) && short_if.equals("short_if")) {
            return null;
        }
        this.em.printout(";" + this.newline());
        if (!(ast.e instanceof TernaryExprAST)) {
            ast.line_str = this.em.setFilter(false);
        }
        return null;
    }
    
    // UnaryExprAST
    @Override
    public Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException
    {
        if ((ast.opType == 4) || (ast.opType == 5)) {
            // dau x++, x--
            ast.e.visit(this, o);
            if(ast.opType == 4)
            	this.em.printout(" + 1");
            else
            	this.em.printout(" - 1");
        }
        else {
            this.em.printout(ast.op.getText());
            ast.e.visit(this, o);
        }
        return null;
    }
    
    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        String checkAssign = (String) o;
        boolean checkAssgn = false;
        if ((checkAssign != null) && checkAssign.equals("assign")) {
            checkAssgn = true;
        }
        
        if ((ast.opType >= 24) && (ast.opType <= 28)) {
            // opType: + - * / %
            if (!checkAssgn) {
                this.em.printout("(");
            }
            ast.e1.visit(this, null);
            this.em.printout(" " + ast.op.getText() + " ");
            ast.e2.visit(this, null);
            if (!checkAssgn) {
                this.em.printout(")");
            }
        }
        else {
            boolean assiStmt = false;
            if ((ast.opType == 0) && (ast.parent instanceof ExprStmtAST)) {
                assiStmt = true;
            }
            else {
                this.em.printout("(");
            }
            
            ast.e1.visit(this, null);
            this.em.printout(" " + ast.op.getText() + " ");
            if (assiStmt) {
                ast.e2.visit(this, "assign");
            }
            else {
                ast.e2.visit(this, null);
            }
            if (!assiStmt) {
                this.em.printout(")");
            }
        }
        return null;
    }
    
    /*
     * TernaryExprAST
     * x == 0 ? y = 0 : y = 1;
     * this method will reformat this expression
     * under the if then else format
     */
    @Override
    public Object visitTernaryExprAST(TernaryExprAST ast, Object o) throws CompilationException
    {
        ast.l1 = this.line;
        this.em.printout("if (");
        
        this.em.setFilter(true);
        ast.e1.visit(this, o);
        ast.s1 = this.em.setFilter(false);
        
        this.em.printout(")" + this.newline());
        this.inScope();
        ast.l2 = this.line;
        this.indentString();
        this.em.setFilter(true);
        ast.e2.visit(this, o);
        this.outScope();
        this.em.printout(";" + this.newline());
        ast.s2 = this.em.setFilter(false);
        this.indentString();
        this.em.printout("else" + this.newline());
        this.inScope();
        ast.l3 = this.line;
        this.indentString();
        this.em.setFilter(true);
        ast.e3.visit(this, o);
        this.outScope();
        this.em.printout(";" + this.newline());
        ast.s3 = this.em.setFilter(false);
        return "short_if";
    }
    
    // IfThenStmtAST
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.printout("if (");
        
        this.em.setFilter(true);
        ast.e.visit(this, o);
        ast.line_str = this.em.setFilter(false);
        
        this.em.printout(")" + this.newline());
        if (ast.s instanceof CompStmtAST) {
            ast.s.visit(this, o);
        }
        else {
            this.inScope();
            ast.s.visit(this, o);
            this.outScope();
        }
        return null;
    }
    
    // IfThenElseStmtAST
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.printout("if (");
        
        this.em.setFilter(true);
        ast.e.visit(this, o);
        ast.line_str = this.em.setFilter(false);
        
        this.em.printout(")" + this.newline());
        if (ast.s1 instanceof CompStmtAST) {
            ast.s1.visit(this, o);
        }
        else {
            this.inScope();
            ast.s1.visit(this, o);
            this.outScope();
        }
        this.indentString();
        
        ast.line_else = this.line;
        
        this.em.printout("else" + this.newline());
        if (ast.s2 instanceof CompStmtAST) {
            ast.s2.visit(this, o);
        }
        else {
            this.inScope();
            ast.s2.visit(this, o);
            this.outScope();
        }
        return null;
    }
    
    // SwitchStmtAST
    @Override
    public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o) throws CompilationException
    {
        // public ExprAST e;
        // public OneStmtAST o;
        this.indentString();
        this.em.printout("switch (");
        ast.e.visit(this, null);
        this.em.printout(")" + this.newline());
        ast.o.visit(this, "switch");
        this.inLineCase = false;
        return null;
    }
    
    boolean inLineCase = false;
    
    // CaseStmtAST
    @Override
    public Object visitCaseStmtAST(CaseStmtAST ast, Object o) throws CompilationException
    {
        // public ExprAST e;
        // public StmtListAST s;
        if (this.inLineCase) {
            this.outScope();
        }
        this.indentString();
        this.em.printout("case ");
        ast.e.visit(this, null);
        this.em.printout(": " + this.newline());
        this.inScope();
        this.inLineCase = true;
        ast.s.visit(this, "case");
        return null;
    }
    
    // DefaultStmtAST
    @Override
    public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o) throws CompilationException
    {
        if (this.inLineCase) {
            this.outScope();
        }
        this.indentString();
        this.em.printout("default:" + this.newline());
        this.inScope();
        ast.s.visit(this, "case");
        return null;
    }
    
    // BreakStmtAST
    @Override
    public Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.printout("break;" + this.newline());
        return null;
    }
    
    // WhileStmtAST
    @Override
    public Object visitWhileStmtAST(WhileStmtAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.printout("while (");
        this.em.setFilter(true);
        ast.e.visit(this, o);
        ast.line_str = this.em.setFilter(false);
        this.em.printout(")" + this.newline());
        if (ast.o instanceof CompStmtAST) {
            ast.o.visit(this, o);
        }
        else {
            this.inScope();
            ast.o.visit(this, o);
            this.outScope();
        }
        return null;
    }
    
    /*
     * ContStmtAST
     * Continue statement ast
     */
    @Override
    public Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.printout("continue;" + this.newline());
        return null;
    }
    
    // DoStmtAST
    @Override
    public Object visitDoStmtAST(DoStmtAST ast, Object o) throws CompilationException
    {
        this.indentString();
        this.em.printout("do" + this.newline());
        if (ast.o instanceof CompStmtAST) {
            ast.o.visit(this, o);
        }
        else {
            this.inScope();
            ast.o.visit(this, o);
            this.outScope();
        }
        ast.line = this.line;
        this.indentString();
        this.em.printout("while (");
        this.em.setFilter(true);
        ast.e.visit(this, o);
        ast.line_str = this.em.setFilter(false);
        this.em.printout(");" + this.newline());
        return null;
    }
    
    // ExprListAST
    @Override
    public Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException
    {
        String check = (String) o;
        if ((check != null) && check.equals("for")) {
            ast.line = this.line;
            this.em.setFilter(true);
            ast.e.visit(this, o);
            ast.line_str = this.em.setFilter(false);
            ast.line_str += ";\r\n";
            ast.l.visit(this, "for2");
        }
        if ((check != null) && check.equals("for2")) {
            this.em.printout(", ");
            ast.line = this.line;
            this.em.setFilter(true);
            ast.e.visit(this, o);
            ast.line_str = this.em.setFilter(false);
            ast.line_str += ";\r\n";
            ast.l.visit(this, "for2");
        }
        if ((check != null) && check.equals("arraytype")) {
            this.em.printout("[");
            ast.e.visit(this, o);
            this.em.printout("]");
            ast.l.visit(this, o);
        }
        if ((check != null) && check.equals("CallExpr")) {
            ast.e.visit(this, o);
            ast.l.visit(this, "CallExpr2");
        }
        if ((check != null) && check.equals("CallExpr2")) {
            this.em.printout(", ");
            ast.e.visit(this, o);
            ast.l.visit(this, o);
        }
        return null;
    }
    
    /*
     * ForStmtAST
     * public ForInitAST e1;
     * public ExprAST e2;
     * public ExprListAST e3;
     * public OneStmtAST o;
     */
    @Override
    public Object visitForStmtAST(ForStmtAST ast, Object o) throws CompilationException
    {
        if (ast.e1.type == 2) {
            // for (i=1,j=1; ... ; ...)
            ast.line = this.line;
            this.indentString();
            this.em.printout("for (");
            ast.e1.visit(this, "for");
            this.em.printout("; ");
            if (ast.e2 != null) {
                this.em.setFilter(true);
                ast.e2.visit(this, o);
                ast.s2 = this.em.setFilter(false);
            }
            this.em.printout("; ");
            ast.e3.visit(this, "for");
            this.em.printout(")" + this.newline());
        }
        else {
            ast.line = this.line;
            this.indentString();
            if (ast.e1.type == 1) {
                // for (int i=1; ... ; ...)
                // em.setForInit(true);
                this.em.printout("for (");
                ast.e1.visit(this, "forinit");
                this.em.printout("; ");
                // String for_init = em.setForInit(false);
                /*
                 * String[] arr_init = for_init.split(";");
                 * arr_init[0] = arr_init[0].trim();arr_init[1] = arr_init[1].trim();
                 * if (arr_init.length == 3) arr_init[2] = arr_init[2].trim();
                 */
                // indentString();
                // em.printout(for_init + ";" + newline());
            }
            else {
                // ast.line = this.line;
                // this.indentString();
                this.em.printout("for ( ; ");
            }
            if (ast.e2 != null) {
                this.em.setFilter(true);
                ast.e2.visit(this, o);
                ast.s2 = this.em.setFilter(false);
            }
            this.em.printout("; ");
            ast.e3.visit(this, "for");
            this.em.printout(")" + this.newline());
        }
        
        if (ast.o instanceof CompStmtAST) {
            ast.o.visit(this, o);
        }
        else {
            this.inScope();
            ast.o.visit(this, o);
            this.outScope();
        }
        return null;
    }
    
    // ForInitAST
    // public int type; //1:localVarDecl, 2: expressions, 3:null
    // public DeclarationListAST d;
    // public ExprListAST e;
    @Override
    public Object visitForInitAST(ForInitAST ast, Object o) throws CompilationException
    {
        if (ast.type == 1) {
            ast.d.visit(this, o);
        }
        if (ast.type == 2) {
            ast.e.visit(this, o);
        }
        return null;
    }
    
    // RetStmtAST
    @Override
    public Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.setFilter(true);
        this.em.printout("return ");
        ast.e.visit(this, o);
        this.em.printout(";" + this.newline());
        ast.line_str = this.em.setFilter(false);
        return null;
    }
    
    // DefineDirectiveAST #define
    @Override
    public Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) throws CompilationException
    {
        ast.line = this.line;
        this.indentString();
        this.em.printout("#define ");
        this.em.printout(ast.id.getText());
        this.em.printout(" ");
        ast.l.visit(this, o);
        this.em.printout(this.newline());
        return null;
    }
    
    // Function Call
    // CallExprAST
    @Override
    public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException
    {
        this.em.printout(ast.name.getText() + "(");
        ast.e.visit(this, "CallExpr");
        this.em.printout(")");
        return null;
    }
    
    /**
     * the following methods are about TypeAST
     */
    
    // extended grammar:
    // TypeListAST
    // TypeAST t;
    // TypeListAST tl;
    @Override
    public Object visitTypeListAST(TypeListAST ast, Object o) throws CompilationException
    {
        ast.t.visit(this, o);
        this.em.printout(" ");
        ast.tl.visit(this, o);
        return null;
    }
    
    // DoubleTypeAST
    @Override
    public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("double");
        return null;
    }
    
    // WCharTTypeAST
    @Override
    public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("wchar_t");
        return null;
    }
    
    // SignedTypeAST
    @Override
    public Object visitSignedTypeAST(SignedTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("signed");
        return null;
    }
    
    // UnsignedTypeAST
    @Override
    public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("unsigned");
        return null;
    }
    
    // LongTypeAST
    @Override
    public Object visitLongTypeAST(LongTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("long");
        return null;
    }
    
    // ShortTypeAST
    @Override
    public Object visitShortTypeAST(ShortTypeAST ast, Object o) throws CompilationException
    {
        this.em.printout("short");
        return null;
    }
    
    public Emitter get_EM()
    {
        return this.em;
    }
}

/*
 * ExprStmtAST
 * DeclarationStmtAST => VarDeclAST
 * IfThenStmtAST
 * IfThenElseStmtAST
 * WhileStmtAST
 * DoStmtAST
 * ForStmtAST
 * 
 * BreakStmtAST
 * ContStmtAST
 * RetStmtAST
 * 
 * SwitchStmtAST
 * CaseStmtAST
 * DefaultStmtAST
 * 
 * CompStmtAST
 */
