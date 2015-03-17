package ctg;

import CodeAnalyzer.CodeAnalyzer;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import system.*;
import transform.AST.AST;
import transform.AST.CompilationException;

public class Control 
{
        public static Control s_GoldInstance = new Control();
        
        public static Control s_EvoInstance = new Control();
        
        public static Control s_TestInstance = new Control();
    
	//In out control
	private InOut io;
	
	//Standard source file
	private String standardFile;
	//Standardized source
	private String standardSource;
	//Original source
	private String originalSource;
        
        public static ArrayList<ArrayList<String>> T;
        private static String markCon;
        public static ArrayList<Integer> goldExecPath;
        public static ArrayList<Integer> evoExecPath;
        
        public static int totalPassed;
        public static int totalFailed;
        public static ArrayList<Boolean> testResult;
	
	//Code analyzer
	private CodeAnalyzer codeAnalyzer;
	
	
	//Constructor
	private Control()
	{
		io = new InOut();
		codeAnalyzer = new CodeAnalyzer();
	}
	
	//Read source file
	public String readSourceFile(String filename)
	{
		originalSource = io.readFile(filename);
		return originalSource;
	}
	
	
	//Standardize Source 
	public String standardizeSource(String filename)
	{
		standardFile = codeAnalyzer.getStandardSource(filename);
		standardSource = io.readFile(standardFile);
		return standardSource;
	}
        
        public DefaultMutableTreeNode getTree()
        {
            return codeAnalyzer.getTree();
        }
        
        public ArrayList<AST> getPath(int pathIndex)
        {
            return codeAnalyzer.listPath == null ? null :  codeAnalyzer.listPath.get(pathIndex);
        }
        
        public Object getOutput(int testcaseID)
        {
            return codeAnalyzer.output.get(testcaseID);
        }
        
	//Get parameter list
	public ArrayList<String> getParaList()
	{
		return this.codeAnalyzer.getParaNameList();
	}
	
	//Get condition list
	public ArrayList<String> getConditionList() throws CompilationException
	{
		return this.codeAnalyzer.getConditionList();
	}
	
	
	public ArrayList<Integer> getSlide() {
		return this.codeAnalyzer.getSlide();
	}
        
        //Generate test case for solvable condition
	public void InitCTG()
	{
            try
            {
                this.codeAnalyzer.getConditionList();
                this.codeAnalyzer.scanCondition();
                this.codeAnalyzer.ComputePathCondition();
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
	}
        
        public static String PrintTestCase()
        {
            String s = "";
            if (T != null)
            {
                for (int i = 0; i < T.size(); i++)
                {
                    ArrayList<String> testcase = T.get(i);
                    for (String param : testcase)
                    {
                        s += param + " ";
                    }
                    s += "\n";
                }
            }
            return s;
        }
        
        public static void RunCTG()
        {
            T = new ArrayList<>();
            goldExecPath = new ArrayList<>();
            evoExecPath = new ArrayList<>();
            markCon = "";
            for (int i = 0; i < s_GoldInstance.codeAnalyzer.pathCon.size(); i++)
            {
                String con = s_GoldInstance.codeAnalyzer.pathCon.get(i);
                ArrayList<String> testcase = s_GoldInstance.codeAnalyzer.solve_constraint(AND(con, NOT(markCon)));
                combine(testcase);
            }
        }
        
        public static Object Run(ArrayList<String> testcase)
        {
            return s_TestInstance.codeAnalyzer.Run(testcase);
        }
        
        public static void combine(ArrayList<String> testcase)
        {
            if (testcase == null) return;
            
            T.add(testcase);
            
            int alpha = s_GoldInstance.codeAnalyzer.symbolic_exec((ArrayList<String>)testcase.clone(), T.size() - 1);
            int beta = s_EvoInstance.codeAnalyzer.symbolic_exec((ArrayList<String>)testcase.clone(), T.size() - 1);
            goldExecPath.add(alpha);
            evoExecPath.add(beta);
            String alphaCon = s_GoldInstance.codeAnalyzer.pathCon.get(alpha);
            String betaCon = s_EvoInstance.codeAnalyzer.pathCon.get(beta);
            
            markCon = OR(markCon, AND(alphaCon, betaCon));
            
            String con1 = AND(AND(alphaCon, NOT(betaCon)), NOT(markCon));
            ArrayList<String> t1 = s_GoldInstance.codeAnalyzer.solve_constraint(con1);
            combine(t1);
            
            String con2 = AND(AND(NOT(alphaCon), betaCon), NOT(markCon));
            ArrayList<String> t2 = s_GoldInstance.codeAnalyzer.solve_constraint(con2);
            combine(t2);
        }
        
        public static String NOT(String condition)
        {
            condition = condition.replace("assert", "not");
            String[] lines = condition.split("\n");
            String s = lines[0];
            for (int i = 1; i < lines.length; i++)
                s = "(or " + s + " " + lines[i] + ")";
            return "(assert " + s + ")\n";
        }
        
        public static String AND(String condition1, String condition2)
        {
            return condition1 + condition2;
        }
        
        public static String OR(String condition1, String condition2)
        {
            if (condition1 == "") return condition2;
            if (condition2 == "") return condition1;
            condition1 = condition1.replace("(assert ", "");
            String[] lines = condition1.split("\n");
            String s = lines[0].substring(0, lines[0].length() - 1);
            for (int i = 1; i < lines.length; i++)
            {
                lines[i] = lines[i].substring(0, lines[i].length() - 1);
                s = "(and " + s + " " + lines[i] + ")";
            }
            
            condition2 = condition2.replace("(assert ", "");
            String[] lines2 = condition2.split("\n");
            String s2 = lines2[0].substring(0, lines2[0].length() - 1);
            for (int i = 1; i < lines2.length; i++)
            {
                lines2[i] = lines2[i].substring(0, lines2[i].length() - 1);
                s2 = "(and " + s2 + " " + lines2[i] + ")";
            }
            return "(assert (or " + s + " " + s2 + "))\n";
        }
        
        public ArrayList<AST> FindBugInEvo(int testcaseID)
        {
            ArrayList<AST> bugs = new ArrayList<>();
            int evoExecPathID = evoExecPath.get(testcaseID);
            ArrayList<String> testcase = T.get(testcaseID);
            Object expectedOutput = s_GoldInstance.getOutput(testcaseID);
            bugs = s_EvoInstance.codeAnalyzer.FindBug(testcase, expectedOutput, evoExecPathID);
            return bugs;
        }
        
        public ArrayList<AST> FindBugInGold(int testcaseID)
        {
            ArrayList<AST> bugs = new ArrayList<>();
            int goldExecPathID = goldExecPath.get(testcaseID);
            ArrayList<String> testcase = T.get(testcaseID);
            Object expectedOutput = s_EvoInstance.getOutput(testcaseID);
            bugs = s_GoldInstance.codeAnalyzer.FindBug(testcase, expectedOutput, goldExecPathID);
            return bugs;
        }
        
        public double evaluateRankingPoint(AST ast)
        {
            int failedCount = 0;
            int passedCount = 0;
            for (int i = 0; i < T.size(); i++)
            {
                int evoPathIndex = Control.evoExecPath.get(i);
                ArrayList<AST> path = Control.s_EvoInstance.getPath(evoPathIndex);
                for (AST node : path)
                {
                    if (ast.equals(node))
                    {
                        if (testResult.get(i)) passedCount++;
                        else failedCount++;
                        break;
                    }
                }
            }
            double f = (1.00 * failedCount) / totalFailed;
            double p = (1.00 * passedCount) / totalPassed;
            return 1.00 * (f / (f + p));
        }

	//Generate test case for solvable condition
	public String GenerateSolvable()
	{
		return this.codeAnalyzer.GenerateSolvable();
	}
	
	//Show all test case
	public String showAllTestCase()
	{
		return this.codeAnalyzer.showAllTestCase();
	}

	//Scan program to collect conditions
	public String scanCondition() {
		return this.codeAnalyzer.scanCondition();
	}
	
	//Get all test cases with true output
	public ArrayList<Boolean> getTrueList()
	{
		return codeAnalyzer.getTrueList();
	}
	
	//Get all test cases with false output
	public ArrayList<Boolean> getFalseList()
	{
		return codeAnalyzer.getFalseList();
	}

	//Get the previous test case
	public ArrayList<Integer> getPrevTestCase() {
		return codeAnalyzer.getPrevTestCase();
	}
	
	//Get the next test case
	public ArrayList<Integer> getNextTestCase()
	{
		return codeAnalyzer.getNextTestCase();
	}
}
