package system;

import java.util.*;

import transform.AST.*;
import transform.CodeGeneration.*;
import transform.DependenceGraph.*;
public class Path 
{
	ArrayList<Integer> line;
	ArrayList<AST> path;
	ArrayList<Integer> branch;
	ArrayList<Variable> listVariableReIndexed;
	
	
	public Path(Path oldpath)
	{
		this.line = new ArrayList<Integer>();
		this.path = new ArrayList<AST>();
		this.branch = new ArrayList<Integer>();
		for(int i=0; i<oldpath.getPath().size(); i++)
		{
			this.line.add(oldpath.getLine().get(i));
			this.path.add(oldpath.getPath().get(i));
			this.branch.add(oldpath.getBranch().get(i));
		}
	}
	
	public ArrayList<Integer> getLine() {
		return this.line;
	}

	public Path(ExecutionHistory eh, MappingTable mapTable) throws CompilationException, CloneNotSupportedException
	{
		this.line = new ArrayList<Integer>();
		this.path = new ArrayList<AST>();
		this.branch = new ArrayList<Integer>();
		
		int stmtID;
		CopyASTVisitor visitor = new CopyASTVisitor();
		for (int i = 0; i < eh.size(); i++)
		{
			//Get ID of the statement
			stmtID = eh.get(i).getNode().getID();
			//Get the AST of the statement
			AST ast = (AST)mapTable.getNode(stmtID).getStatementAST().visit(visitor, null);
			
			if(ast instanceof IfThenStmtAST)
			{
				ast = ((IfThenStmtAST) ast).e;
			}
			if(ast instanceof IfThenElseStmtAST)
			{
				ast = ((IfThenElseStmtAST) ast).e;
			}
			if(ast instanceof WhileStmtAST)
			{
				ast = ((WhileStmtAST) ast).e;
			}
			if(ast instanceof DoStmtAST)
			{
				ast = ((DoStmtAST) ast).e;
			}
			// not put return statement to path
			if ((ast != null)) //!(ast instanceof RetStmtAST) &&  
			{ 
                // condition
				this.line.add(stmtID);
				this.path.add(ast);
				if (eh.get(i).getState() == 'T') {
					this.branch.add(1);
				}
				else if (eh.get(i).getState() == 'F') {
					this.branch.add(0);
				}
				else {
					this.branch.add(-1);
				}
			}
		}
	}

	public ArrayList<AST> getPath() {
		return path;
	}

	public void setPath(ArrayList<AST> path) {
		this.path = path;
	}

	public ArrayList<Integer> getBranch() {
		return branch;
	}

	public void setBranch(ArrayList<Integer> branch) {
		this.branch = branch;
	}

	public ArrayList<Variable> getListVariableReIndexed() {
		return listVariableReIndexed;
	}

	public void setListVariableReIndexed(ArrayList<Variable> listVariableReIndexed) {
		this.listVariableReIndexed = listVariableReIndexed;
	}

	public void reIndex(ArrayList<Variable> listVar) throws CompilationException {
		ReIndexVisitor visitor = new ReIndexVisitor(listVar);
		for (int i = 0; i < this.path.size(); i++) {
            this.path.get(i).visit(visitor, null);
        }
        this.listVariableReIndexed = visitor.getReIndexVar();
	}

	public void negate(int branch) {
		int b;
        if (this.branch.get(branch) == 1) {
            b = 0;
        }
        else {
            b = 1;
        }
        this.branch.set(branch, b);
        for(int i=this.path.size()-1; i>branch; i--)
        {
        	this.path.remove(i);
        	this.branch.remove(i);
        	this.line.remove(i);
        }
	}
	
	public void modify(PDG pdg)
	{
		ArrayList<Integer> DependenceList = new ArrayList<Integer>();
		DependenceList.add(0);
		int i=0;
		while(i<path.size())
		{
			int depNodeID = pdg.findNodeAtLine(line.get(i)).getConDep().getNode().getID();
			boolean b = pdg.findNodeAtLine(line.get(i)).getConDep().getBranch();
			boolean check = false;
			for(int j=0; j<DependenceList.size();j++)
			{
				System.out.println("Size" + DependenceList.size());
				if(depNodeID == line.get(DependenceList.get(j))|| depNodeID == 1)
				{
					check = true;
				}
			}
			if(check==true)
			{
				if((depNodeID == 1)||(b == true && branch.get(i)==1)||(b == false && branch.get(i)==0))
				{
					DependenceList.add(i);
					System.out.println("Add"+ DependenceList.size());
				}
				else
				{
					path.remove(i);
					line.remove(i);
					branch.remove(i);
					i--;
				}
			}
			else
			{
				System.out.println("check");
				path.remove(i);
				line.remove(i);
				branch.remove(i);
				i--;
			}
			i++;
		}
	}

	public String temp(ArrayList<Parameter> listPara, ArrayList<Variable> listVar) throws CompilationException
	{
		TempVisitor visitor = new TempVisitor(listPara, listVar);
		int line = 1;
		String value ="";
		for (int i = 0; i < this.path.size(); i++) {
			while(line<this.line.get(i))
			{
				value += "\n";
				line++;
			}
			String temp = (String) this.path.get(i).visit(visitor, null);
			value += temp + "\n";
			line++;
        }
		return value;
	}
}
