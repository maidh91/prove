package transform.CodeGeneration;

import transform.AST.*;
import transform.DependenceGraph.Node;
import transform.DependenceGraph.PDG;

import java.util.*;

public class ControlFlowGraphVisitor 
	extends DoNothingVisitor
{
	PDG mPdg;
	ArrayList<ArrayList<AST>> listPath;
	ArrayList<ArrayList<Integer>> dependence;
	ArrayList<ArrayList<Integer>> branch;
        boolean isReturned;
	
	public ControlFlowGraphVisitor(PDG pdg)
	{
		mPdg = pdg;
		listPath = new ArrayList<ArrayList<AST>>();
		dependence = new ArrayList<ArrayList<Integer>>();
		branch = new ArrayList<ArrayList<Integer>>();
                isReturned = false;
	}
	
	public ArrayList<ArrayList<AST>> getListPath()
	{
		return this.listPath;
	}
	
	public ArrayList<ArrayList<Integer>> getListBranch()
	{
		return this.branch;
	}
    
    public boolean find(int conline, boolean branch, int i)
    {
    	int size = this.dependence.get(i).size();
    	if(size == 0)
    		return false;
    	else
    	{
    		for(int j= size-1; j>=0; j--)
    		{
    			if(this.dependence.get(i).get(j) == -1)
    				return false;
    			if(this.dependence.get(i).get(j)== conline)
    			{
    				if(this.branch.get(i).get(j) == 0 && branch == true)
    					return true;
    				if(this.branch.get(i).get(j) == 1 && branch == false)
    					return true;
    			}
    		}
    	}
    	return false;
    }
    
    // ProgramAST
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	ArrayList<AST> temp = new ArrayList<AST>();
    	temp.add(ast);
    	this.listPath.add(temp);
    	ArrayList<Integer> temp1 = new ArrayList<Integer>();
    	temp1.add(1);
    	this.dependence.add(temp1);
    	ArrayList<Integer> temp2 = new ArrayList<Integer>();
    	temp2.add(0);
    	this.branch.add(temp2);
    	ast.dl.visit(this, o);
    	return null;
    }
    
    // FuncDeclAST
    @Override
    public Object visitFuncDeclAST(FuncDeclAST fAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
        fAst.c.visit(this, o);
        isReturned = false;
        return null;
    }
    
    // DeclarationListAST
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
        ast.d.visit(this, o);
        ast.dl.visit(this, o);
        return null;
    }
    
    // DeclarationStmtAST
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	try
    	{
	    	Node node = mPdg.findNodeAtLine(ast.line);
	    	int conditionline = node.getConDep().getNode().getID();
	        boolean branch = node.getConDep().getBranch();
	        int size = this.listPath.size();
	        for(int i=0; i<size; i++)
	        {
	        	if(find(conditionline, branch, i))
	        	{
	        		this.listPath.get(i).add(ast);
	        		this.dependence.get(i).add(ast.line);
	        		this.branch.get(i).add(-1);
	        	}
	        }
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
        return null;
    }
    
    // CompStmtAST
    @Override
    public Object visitCompStmtAST(CompStmtAST cAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
        cAst.s.visit(this, o);
        return null;
    }
    
    // StmtListAST
    @Override
    public Object visitStmtListAST(StmtListAST sAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
        sAst.o.visit(this, o);
        sAst.s.visit(this, o);
        return null;
    }
    
    //EmptyStmtAST
    @Override
    public Object visitEmptyStmtListAST(EmptyStmtListAST eAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	return null;
    }
    
    //VarDeclAST
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	return ast;
    }
    
    //ExprAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	Node node = mPdg.findNodeAtLine(ast.line);
    	int conditionline = node.getConDep().getNode().getID();
        boolean branch = node.getConDep().getBranch();
        int size = this.listPath.size();
        for(int i=0; i<size; i++)
        {
        	if(find(conditionline, branch, i))
        	{
        		this.listPath.get(i).add(ast);
        		this.dependence.get(i).add(ast.line);
        		this.branch.get(i).add(-1);
        	}
        }
    	return null;
    }
    
    //RetStmt
    @Override
    public Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	Node node = mPdg.findNodeAtLine(ast.line);
    	int conditionline = node.getConDep().getNode().getID();
    	boolean branch = node.getConDep().getBranch();
    	int size = this.listPath.size();
    	for(int i=0; i<size; i++)
    	{
    		if(find(conditionline, branch, i))
    		{
    			this.listPath.get(i).add(ast);
    			this.dependence.get(i).add(-1);
    			this.branch.get(i).add(-1);
    		}
    	}
        isReturned = true;
    	return null;
    }
    
    // IfThenStmtAST
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST iAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	ExprAST mAst;
		try
		{
			mAst = (BinExprAST) iAst.e.visit(this, o);
		}
		catch(ClassCastException e)
		{
			mAst = (UnaryExprAST) iAst.e.visit(this, o);
		}
    	Node node = mPdg.findNodeAtLine(iAst.line);
    	int conditionline = node.getConDep().getNode().getID();
    	boolean branch = node.getConDep().getBranch();
    	int size = this.listPath.size();
    	for(int i=0; i<size; i++)
    	{
    		if(find(conditionline, branch, i))
    		{
    			ArrayList<AST> temp = new ArrayList<AST>();
        		for(int j=0; j<this.listPath.get(i).size(); j++)
        		{
        			temp.add(this.listPath.get(i).get(j));
        		}
        		temp.add(mAst);
        		this.listPath.get(i).add(mAst);
        		ArrayList<Integer> temp1 = new ArrayList<Integer>();
        		for(int j=0; j<this.dependence.get(i).size(); j++)
        		{
        			temp1.add(this.dependence.get(i).get(j));
        		}
        		temp1.add(iAst.line);
        		this.dependence.get(i).add(iAst.line);
        		ArrayList<Integer> temp2 = new ArrayList<Integer>();
        		for(int j=0; j<this.branch.get(i).size(); j++)
        		{
        			temp2.add(this.branch.get(i).get(j));
        		}
        		this.branch.get(i).add(0);
        		temp2.add(1);
        		this.listPath.add(temp);
        		this.dependence.add(temp1);
        		this.branch.add(temp2);

	        }
	    }
    	iAst.s.visit(this, o);
        return null;
    }
    
    // IfThenElseStmtAST
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST iAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	try
        {
    		ExprAST mAst;
    		try
    		{
    			mAst = (BinExprAST) iAst.e.visit(this, o);
    		}
    		catch(ClassCastException e)
    		{
    			mAst = (UnaryExprAST) iAst.e.visit(this, o);
    		}
        	Node node = mPdg.findNodeAtLine(iAst.line);
	        int conditionline = node.getConDep().getNode().getID();
	        boolean branch = node.getConDep().getBranch();
	        int size = this.listPath.size();
	        for(int i=0; i<size; i++)
	        {
	        	if(find(conditionline, branch, i))
	        	{	
	        		ArrayList<AST> temp = new ArrayList<AST>();
	        		for(int j=0; j<this.listPath.get(i).size(); j++)
	        		{
	        			temp.add(this.listPath.get(i).get(j));
	        		}
	        		temp.add(mAst);
	        		this.listPath.add(temp);
	        		this.listPath.get(i).add(mAst);
	        		ArrayList<Integer> temp1 = new ArrayList<Integer>();
	        		for(int j=0; j<this.dependence.get(i).size(); j++)
	        		{
	        			temp1.add(this.dependence.get(i).get(j));
	        		}
	        		temp1.add(iAst.line);
	        		this.dependence.get(i).add(iAst.line);
	        		ArrayList<Integer> temp2 = new ArrayList<Integer>();
	        		for(int j=0; j<this.branch.get(i).size(); j++)
	        		{
	        			temp2.add(this.branch.get(i).get(j));
	        		}
	        		this.branch.get(i).add(0);
	        		temp2.add(1);
	        		this.dependence.add(temp1);
	        		this.branch.add(temp2);
	        	}
	        }
	        iAst.s1.visit(this, o);
	        iAst.s2.visit(this, o);
        }
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    // WhileStmtAST
    @Override
    public Object visitWhileStmtAST (WhileStmtAST wAst, Object o) throws CompilationException
    {
        //if (isReturned) return null;
        //this.mapTable.addMappingNode(new MappingNode(wAst.line, wAst));
        //wAst.e.visit(this, o);
        //wAst.o.visit(this, o);
    	try
        {
    		ExprAST mAst;
    		try
    		{
    			mAst = (BinExprAST) wAst.e.visit(this, o);
    		}
    		catch(ClassCastException e)
    		{
    			mAst = (UnaryExprAST) wAst.e.visit(this, o);
    		}
        	Node node = mPdg.findNodeAtLine(wAst.line);
	        int conditionline = node.getConDep().getNode().getID();
	        boolean branch = node.getConDep().getBranch();
	        int size = this.listPath.size();
	        for(int i=0; i<size; i++)
	        {
	        	if(find(conditionline, branch, i))
	        	{	
	        		ArrayList<AST> temp = new ArrayList<AST>();
	        		for(int j=0; j<this.listPath.get(i).size(); j++)
	        		{
	        			temp.add(this.listPath.get(i).get(j));
	        		}
	        		temp.add(mAst);
	        		this.listPath.add(temp);
	        		this.listPath.get(i).add(mAst);
	        		ArrayList<Integer> temp1 = new ArrayList<Integer>();
	        		for(int j=0; j<this.dependence.get(i).size(); j++)
	        		{
	        			temp1.add(this.dependence.get(i).get(j));
	        		}
	        		temp1.add(wAst.line);
	        		this.dependence.get(i).add(wAst.line);
	        		ArrayList<Integer> temp2 = new ArrayList<Integer>();
	        		for(int j=0; j<this.branch.get(i).size(); j++)
	        		{
	        			temp2.add(this.branch.get(i).get(j));
	        		}
	        		this.branch.get(i).add(0);
	        		temp2.add(1);
	        		this.dependence.add(temp1);
	        		this.branch.add(temp2);
	        	}
	        }
	        wAst.o.visit(this, o);
        }
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    //BinExprAST
    @Override
    public Object visitBinExprAST (BinExprAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	return ast;
    }
    
    @Override
    public Object visitUnaryExprAST (UnaryExprAST ast, Object o) throws CompilationException
    {
        //if (isReturned) return null;
    	return ast;
    }
}
