package transform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;

import system.*;
import transform.AST.*;
//import transform.CodeGeneration.AstPrinterVisitor;
import transform.CodeGeneration.Ast2GraphVisitor;
import transform.CodeGeneration.Ast2MappingTableVisitor;
import transform.CodeGeneration.AstPrinterVisitor;
import transform.CodeGeneration.ControlFlowGraphVisitor;
import transform.CodeGeneration.PrettyOutputVisitor;
import transform.CodeGeneration.ConditionScanVisitor;
import transform.CodeGeneration.VariableVisitor;
import transform.CodeGeneration.Visitor;
import transform.DependenceGraph.*;
import transform.Parser.CPPLexer;
import transform.Parser.CPPParser;
import system.PathNode;
public class Transform 
{
	/**
     * Source File
     */
	String originalSourceFile;
    String standardSourceFile;
    
    /**
     * Abstract Syntax Tree
     */
    AST astree;
    /**
     * Mapping Table
     */
    MappingTable mapTable;
    /**
     * Program Dependence Graph
     */
    PDG pdg;
    
    ArrayList<ArrayList<AST>> listPath;
    ArrayList<ArrayList<Integer>> listBranch;
    
    /**
     * this object contains all parameters of program
     */
    ArrayList<Parameter> listParameters;
    /**
     * this object contains all variables of program
     */
    ArrayList<Variable> listVariables;
    
    public Transform(String strSourceFile)
    {
    	this.originalSourceFile = strSourceFile;
    	try
    	{
    		File sourceFile = new File(originalSourceFile);
    		String filename = sourceFile.getName();
    		String output = "OUTPUT"+ File.separatorChar + "OUT_FOR " + filename;
    		File outFolder = new File(output);
            if (!outFolder.exists()) {
                outFolder.mkdirs();
            }
            this.standardSourceFile = outFolder.getAbsolutePath()+File.separatorChar+ filename;
            
            /*
             * using ANTLR library to lexer and parser the original code
             * then create AST Tree
             */
            CPPParser parser =
                               new CPPParser(new CommonTokenStream(new CPPLexer(new ANTLRReaderStream(
                                       new BufferedReader(new FileReader(strSourceFile))))));
            try {
                this.astree = parser.parse();
            }
            catch (NullPointerException e) {
                throw new NullPointerException("Can't standardize the source code!!!");
            }
            
            //Standardize source
            Visitor walkerC = new PrettyOutputVisitor(this.standardSourceFile, false);
            this.astree.visit(walkerC, "no_output_line");
            
            parser = new CPPParser(new CommonTokenStream(new CPPLexer(new ANTLRReaderStream(new BufferedReader(
                            new FileReader(new File(this.standardSourceFile)))))));
            this.astree = parser.parse();
           
            // Print the AST structure to text file
//        	Visitor walkerPrint = new AstPrinterVisitor(this.standardSourceFile);
//        	astree.visit(walkerPrint, null);
//        	System.out.println(this.standardSourceFile + ": AST internal structure");
        		
           /**
            * Creating Program Dependence Graph (PDG)
            */
           Ast2GraphVisitor ast2PDG = new Ast2GraphVisitor();
           this.astree.visit(ast2PDG, "");
           this.pdg = ast2PDG.getProgramDependenceGraph();
           
           
           ControlFlowGraphVisitor cfgVisitor = new ControlFlowGraphVisitor(pdg);
           astree.visit(cfgVisitor, null);
           
           listPath = cfgVisitor.getListPath();
           
           for (int i = 0; i < listPath.size(); i++)
           {
               ArrayList<AST> path = listPath.get(i);
               int j = 0;
               for (; j < path.size(); j++)
                   if (path.get(j).getClass().getSimpleName().equals("RetStmtAST")) break;
               j++;
               while (j < path.size()) path.remove(j);
           }
           
           listBranch = cfgVisitor.getListBranch();
//           for(int i=0; i<listPath.size(); i++)
//           {
//        	   for(int j=0; j<listPath.get(i).size(); j++)
//        	   {
//        		   System.out.println(listPath.get(i).get(j).getClass().toString() + " " + listBranch.get(i).get(j));
//        	   }
//        	   System.out.println("Next");
//           }
           
           /**
            * Creating Mapping Table
            */
           Ast2MappingTableVisitor ast2Table = new Ast2MappingTableVisitor();
           this.astree.visit(ast2Table, "");
           this.mapTable = ast2Table.getMappingTable(); // get the mapping table
           
           /**
            * Collecting Variable and Parameter
            */
           VariableVisitor varVisitor = new VariableVisitor();
           this.astree.visit(varVisitor, null); // Get list variables of the program
           this.listParameters = varVisitor.getListPara();
           this.listVariables = varVisitor.getListVar();
       	}
    	catch (NullPointerException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get standardize source's filename
	public String getStandardSourceFile() {
		return standardSourceFile;
	}

	//Get AST tree
	public AST getAstree() {
		return astree;
	}

	//Get mapping table
	public MappingTable getMapTable() {
		return mapTable;
	}

	//Get program dependence graph
	public PDG getPdg() {
		return pdg;
	}
	
	//Get list path
	public ArrayList<ArrayList<AST>> getListPath()
	{
		return this.listPath;
	}
	
	//get list branch
	public ArrayList<ArrayList<Integer>> getListBranch()
	{
		return this.listBranch;
	}

	//get list Parameter
	public ArrayList<Parameter> getListParameters() {
		return listParameters;
	}

	//get list variable
	public ArrayList<Variable> getListVariables() {
		return listVariables;
	}
	
	//Scan for condition list	
	public ArrayList<Condition> updateConList(ArrayList<Condition> conlist)
	{
		ConditionScanVisitor visitor = new ConditionScanVisitor(listParameters, listVariables, conlist);
		PathNode node = new PathNode();
		try
		{
	        for(int i=0; i< listPath.size(); i++)
	        {
	     	   for(int j=0; j<listPath.get(i).size(); j++)
	     	   {
	     		   node.con = i;
	     		   node.pos = j;
	     		   node.branch = listBranch.get(i).get(j);
	     		   listPath.get(i).get(j).visit(visitor, node);
	     	   }
	     	   visitor.clear();
	        }
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return visitor.getCon();
	}
}
