package transform.CodeGeneration;

import java.util.ArrayList;

import org.antlr.runtime.Token;

import transform.AST.BinExprAST;
import transform.AST.CompilationException;
import transform.AST.ExprStmtAST;
import transform.AST.IntLiteralAST;
import transform.AST.TernaryExprAST;
import transform.AST.VarExprAST;
import system.Variable;
import system.VariableReIndex;

public class ReIndexVisitor        extends DoNothingVisitor
{
    ArrayList<VariableReIndex> var;
    ArrayList<Variable> reIndexVar;
    ArrayList<Variable> listVar;
    
    public ReIndexVisitor(ArrayList<Variable> list)
    {
        this.var = new ArrayList<VariableReIndex>();
        this.reIndexVar = new ArrayList<Variable>();
        this.listVar = list;
    }
    
    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        // this.indentString();
        if (!(ast.e instanceof TernaryExprAST)) {
            // ast.line = this.line;
            // this.em.setFilter(true);
        }
    	return ast.e.visit(this, o);
        //return null;
        
        // String short_if = (String) ast.e.visit(this, o);
        // if ((short_if != null) && short_if.equals("short_if")) {
        // return null;
        // }
        
        // this.em.printout(";" + this.newline());
        // if (!(ast.e instanceof TernaryExprAST)) {
        // // ast.line_str = this.em.setFilter(false);
        // }
    }
    
    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        if ((ast.opType == BinExprAST.ASSIGN)) { // && (ast.parent instanceof ExprStmtAST)) {
            Token token = (Token) ast.e1.visit(this, o);
            if ((Boolean) (ast.e2.visit(this, token)) == true) {
                int i = this.findVar(token.getText());
                if (i >= 0) {
                    this.var.get(i).increaseIndex();
                    int index = this.var.get(i).getIndex();
                    if (index > 0) {
                        String name = token.getText();
                        token.setText(name + "_" + index); // reindex
                        if (this.findVarName(token.getText()) == -1) {
                            int in = this.getIndex(name);
                            if (in >= 0) {
                                Variable v = this.listVar.get(in);
                                this.reIndexVar.add(new Variable(v.getType(), token.getText()));
                            }
                        }
                    }
                }
                return true;
            }
            else {
                int i = this.findVar(token.getText());
                if (i >= 0) {
                    int index = this.var.get(i).getIndex();
                    if (index > 0) {
                        String name = token.getText();
                        token.setText(name + "_" + index); // reindex
                        // if (this.findVarName(token.getText()) == -1) {
                        // int in = this.getIndex(name);
                        // if (in >= 0) {
                        // Variable v = this.listVar.get(in);
                        // this.reIndexVar.add(new Variable(token.getText(), v.getType()));
                        // }
                        // }
                    }
                }
                return false;
            }
        }
        
        if (o instanceof Token) {
            Token token = (Token) o;
            boolean b = (Boolean) ast.e1.visit(this, token);
            b = b || (Boolean) ast.e2.visit(this, token);
            return b;
        }
        
        ast.e1.visit(this, "other");
        ast.e2.visit(this, "other");
        
        return false;
    }
    
    private int getIndex(String name)
    {
        if (this.listVar != null) {
            for (int i = 0; i < this.listVar.size(); i++) {
                if (this.listVar.get(i).getName().equals(name)) {
                    return i;
                }
            }
            return -1;
        }
        else {
            return -1;
        }
    }
    
    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        if (o instanceof Token) {
            if (ast.name.getText().equals(((Token) o).getText())) {
                int i = this.findVar(ast.name.getText());
                if (i < 0) {
                    VariableReIndex vr = new VariableReIndex(ast.name.getText());
                    this.var.add(vr);
                }
                else {
                    int index = this.var.get(i).getIndex();
                    if (index > 0) {
                        String name = ast.name.getText();
                        ast.name.setText(name + "_" + index); // reindex
                        // Token token = ast.name;
                        // if (this.findVarName(token.getText()) == -1) {
                        // int in = this.getIndex(name);
                        // if (in >= 0) {
                        // Variable v = this.listVar.get(in);
                        // this.reIndexVar.add(new Variable(v.getType(), token.getText()));
                        // }
                        // }
                    }
                }
                return true;
            }
            else {
                int i = this.findVar(ast.name.getText());
                if (i >= 0) {
                    int index = this.var.get(i).getIndex();
                    if (index > 0) {
                        String name = ast.name.getText();
                        ast.name.setText(name + "_" + index); // reindex
                        // Token token = ast.name;
                        // if (this.findVarName(token.getText()) == -1) {
                        // int in = this.getIndex(name);
                        // if (in >= 0) {
                        // Variable v = this.listVar.get(in);
                        // this.reIndexVar.add(new Variable(token.getText(), v.getType()));
                        // }
                        // }
                    }
                }
                return false;
            }
        }
        else if (o instanceof String) {
            if (o.equals("other")) {
                int i = this.findVar(ast.name.getText());
                if (i >= 0) {
                    int index = this.var.get(i).getIndex();
                    if (index > 0) {
                        String name = ast.name.getText();
                        ast.name.setText(name + "_" + index); // reindex
                        // Token token = ast.name;
                        // if (this.findVarName(token.getText()) == -1) {
                        // int in = this.getIndex(name);
                        // if (in >= 0) {
                        // Variable v = this.listVar.get(in);
                        // this.reIndexVar.add(new Variable(token.getText(), v.getType()));
                        // }
                        // }
                    }
                }
            }
            return false;
        }
        else {
            return ast.name;
        }
    }
    
    // IntLiteralAST
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
        return false;
    }
    
    public ArrayList<Variable> getReIndexVar()
    {
        return this.reIndexVar;
    }
    
    // get the index of the varName in varReindex
    private int findVar(String varName)
    {
        if (this.var.size() <= 0) {
            return -1;
        }
        else {
            for (int i = 0; i < this.var.size(); i++) {
                if (varName.equals(this.var.get(i).getVarName())) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    // get the index of the varName in reIndexVar
    private int findVarName(String varName)
    {
        if (this.reIndexVar.size() <= 0) {
            return -1;
        }
        else {
            for (int i = 0; i < this.reIndexVar.size(); i++) {
                if (varName.equals(this.reIndexVar.get(i).getName())) {
                    return i;
                }
            }
            return -1;
        }
    }
}

