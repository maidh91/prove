package transform.CodeGeneration;

import java.util.ArrayList;

import system.Parameter;
import system.Variable;
import transform.AST.BinExprAST;
import transform.AST.CompilationException;
import transform.AST.DeclarationListAST;
import transform.AST.DeclarationStmtAST;
import transform.AST.EmptyDeclarationListAST;
import transform.AST.ExprStmtAST;
import transform.AST.IntLiteralAST;
import transform.AST.TernaryExprAST;
import transform.AST.VarDeclAST;
import transform.AST.VarExprAST;
import transform.AST.VarInitializerAST;

public class TempVisitor extends DoNothingVisitor
{
    ArrayList<Variable> listVar;
    ArrayList<Parameter> listPara;
    ArrayList<String> para;
    ArrayList<String> var;
    
    public TempVisitor(ArrayList<Parameter> listPara, ArrayList<Variable> listVar)
    {
        this.listVar = listVar;
        this.listPara = listPara;
        para = new ArrayList<String>();
        var = new ArrayList<String>();
        para.clear();
        var.clear();
        for(int i=0; i<listVar.size(); i++)
        	var.add("");
        for(int j=0; j<listPara.size(); j++)
        	para.add("");
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
        
        // String short_if = (String) ast.e.visit(this, o);
        // if ((short_if != null) && short_if.equals("short_if")) {
        // return null;
        // }
        
        // this.em.printout(";" + this.newline());
        // if (!(ast.e instanceof TernaryExprAST)) {
        // // ast.line_str = this.em.setFilter(false);
        // }
    }
    
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
    	return ast.dl.visit(this, o);
    	
    }
    
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
    	String var;
    	String value;
    	var = (String)ast.d.visit(this, o);
    	if(ast.dl instanceof EmptyDeclarationListAST)
    	{
    		value = (String)ast.d.visit(this, "c");
    	}
    	else
    	{
    		value = (String) ast.dl.visit(this, o);
	    	int i = this.findVar(var);
	    	if(i>=0)
	    	{	
	    		this.var.set(i, value);
	    	}
	    	else
	    	{
	    		i = this.findPara(var);
	    		value = (String) ast.dl.visit(this, o);
	    		this.para.set(i, value);
	    	}
    	}
    	return var + " = " + value;
    }
    
    
    
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
    	String temp = null;
    	int i = this.findVar(ast.id.getText());
    	if(i>=0)
    	{
    		temp = (String) ast.init.visit(this, o);
    		if(temp != "")
    			this.var.set(i, temp);
    	}
    	else
    	{
    		i = this.findPara(ast.id.toString());
    		if(i>=0)
    		{
    			temp = (String) ast.init.visit(this, o);
    			if(temp != "")
    				this.para.set(i, temp);
    		}
    	}
    	if(o == "c")
    	{
    		return temp;
    	}
    	else
    	{
    		return ast.id.getText();
    	}
    }
    
    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException
    {
    	return (String) ast.e.visit(this, o);
    }
    
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
    	return ast.literal.getText();
    }
    
	// BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        if ((ast.opType == BinExprAST.ASSIGN)) { // && (ast.parent instanceof ExprStmtAST)) {
        	String var = (String) ast.e1.visit(this, o);
        	String temp = "";
            int i = this.findVar(var);
            if (i >= 0)
            {
            	temp = (String)ast.e2.visit(this, "c");
            	this.var.set(i, temp);
            }
            else 
            {
            	i = this.findPara(var);
            	if(i >= 0)
            	{
                	temp = (String) ast.e2.visit(this, "c");
                	this.para.set(i, temp);
            	}
            }
        	return var + " = " + temp;
        }
        else
        {
        	if(o == "c")
        	{
	        	String output = "";
	        	
	        	String val1 = (String)ast.e1.visit(this, "c");
	        	String val2 = (String)ast.e2.visit(this, "c");
	        	if(val1.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")&& val2.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
	        	{
	        		int temp = Integer.parseInt(val1);
	        		switch(ast.opType)
	        		{        	
			        	case 24:
			        		temp += Integer.parseInt(val2);
			        		break;
			        	case 25:
			        		temp -= Integer.parseInt(val2);
			        		break;
			        	case 26:
			        		temp *= Integer.parseInt(val2);
			        		break;
			        	case 27:
			        		temp /= Integer.parseInt(val2);
			        		break;
			        	case 28:
			        		temp %= Integer.parseInt(val2);
			        		break;
	        		}
	        		output += temp;
	        	}
	        	else
	        	{
	        		output += "(";
	        		output += (String) ast.e1.visit(this, "c");
	        		switch(ast.opType)
	        		{        	
		        	case 24:
		        		output += " + ";
		        		break;
		        	case 25:
		        		output += " - ";
		        		break;
		        	case 26:
		        		output += " * ";
		        		break;
		        	case 27:
		        		output += " / ";
		        		break;
		        	case 28:
		        		output += " % ";
		        		break;
	        		}
	        		output += (String) ast.e2.visit(this, "c") + ")";
	        	}
	        	return output;
        	}
        	else
        	{
        		return "";
        	}
        }
    }
    
    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
    	String value = ast.name.getText();
    	String val = "";
        int i = this.findVar(ast.name.getText());
        if (i >= 0) 
        {
        	if(this.var.get(i) != "")
        		val = this.var.get(i);
        	else
        		val = value;
        }
        else
        {
        	i = this.findPara(ast.name.getText());
        	if(i>=0)
        	{
        		if(this.para.get(i) != "")
        			val = this.para.get(i);
        		else
        			val = value;
        	}
        }
        if(o == "c")
        	return val;
        return value;
    }
    
    
    // get the index of the varName in varReindex
    private int findVar(String varName)
    {
        if (this.listVar.size() <= 0) {
            return -1;
        }
        else {
            for (int i = 0; i < this.listVar.size(); i++) {
                if (varName.equals(this.listVar.get(i).getName())) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    private int findPara(String paraName) {
    	if (this.listPara.size() <= 0) {
            return -1;
        }
        else {
            for (int i = 0; i < this.listPara.size(); i++) {
                if (paraName.equals(this.listPara.get(i).getName())) {
                    return i;
                }
            }
            return -1;
        }
	}
    
}
