package CodeAnalyzer;

import ctg.CTFNode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;

import system.*;
import transform.CodeGeneration.AstSimulationVisitor;
import transform.*;
import transform.AST.*;
import transform.AST.CompilationException;
import transform.DependenceGraph.*;
import transform.CodeGeneration.*;

public class CodeAnalyzer {

    //Abstract Syntax Tree
    private AST astree;
    //Program Dependence Graph
    private PDG pdg;
    //Mapping Table
    private MappingTable mapTable;
    //Transform
    private Transform transform;

    //List Path
    public ArrayList<ArrayList<AST>> listPath;
    //List Branch
    private ArrayList<ArrayList<Integer>> listBranch;

    private DefaultMutableTreeNode controlFlowTree;

    //List Variable
    private ArrayList<Variable> listVar;
    //List Parameter
    public ArrayList<Parameter> listPara;
    //List Condition
    private ArrayList<Condition> listCon;

    public ArrayList<String> pathCon;

    public HashMap<Integer, Object> output;
    public HashMap<Integer, ExprTreeNode> exprOutput;

    //Current test case position
    private int currTestCasePos = 0;

    //Test case string
    private String testcase = "";

    //Number of unsolvable Condition
    private int numUnsolvableCon;

    //int temp1 = 1;
    //Constructor
    public CodeAnalyzer() {
        output = new HashMap<>();
        exprOutput = new HashMap<>();
    }

    public CodeAnalyzer(String strSourceFile) {
        this.listCon = new ArrayList<Condition>();
        transform = new Transform(strSourceFile);
        this.mapTable = transform.getMapTable(); // Mapping Table
        this.pdg = transform.getPdg();
        this.astree = transform.getAstree();
        this.listPara = transform.getListParameters(); // list Parameters
        this.listVar = transform.getListVariables(); // list Variables
    }

    //Load source file 
    public void loadFile(String strSourceFile) {
        this.listCon = new ArrayList<Condition>();
        transform = new Transform(strSourceFile);
        this.mapTable = transform.getMapTable(); // Mapping Table
        this.astree = transform.getAstree();
        this.pdg = transform.getPdg();
        this.listPara = transform.getListParameters(); // list Parameters
        this.listVar = transform.getListVariables(); // list Variables
        this.listPath = transform.getListPath();
        this.listBranch = transform.getListBranch();
    }

    //Scan for parameters list
    public ArrayList<String> getParaNameList() {
        ArrayList<String> listParaName = new ArrayList<String>();
        for (int i = 0; i < listPara.size(); i++) {
            listParaName.add(listPara.get(i).getName());
        }
        return listParaName;
    }

    //Scan for variables list	
    public ArrayList<String> getVarNameList() {
        ArrayList<String> listVarName = new ArrayList<String>();
        for (int i = 0; i < listVar.size(); i++) {
            listVarName.add(listVar.get(i).getName());
        }
        return listVarName;
    }

    //Standardize source code
    public String getStandardSource(String filename) {
        this.loadFile(filename);
        return transform.getStandardSourceFile();
    }

    public void ComputePathCondition() {
        pathCon = new ArrayList<>();
        for (int i = 0; i < listPath.size(); i++) {
            String con = "";
            ArrayList<Integer> branch = listBranch.get(i);
            ArrayList<AST> path = listPath.get(i);
            int j = -1;
            for (int k = 0; k < branch.size(); k++) {
                if (branch.get(k) != -1) {
                    j = k;
                }
            }
            if (j != -1) {
                if (branch.get(j) == 0) {
                    AST ast = path.get(j);
                    Condition stmCon = FindCondition(ast);
                    con = stmCon.getTruepath().get(0);
                } else if (branch.get(j) == 1) {
                    AST ast = path.get(j);
                    Condition stmCon = FindCondition(ast);
                    con = stmCon.getFalsepath().get(0);
                }
            }
            pathCon.add(con);
        }
    }

    public Condition FindCondition(AST ast) {
        for (int i = 0; i < listCon.size(); i++) {
            Condition condition = listCon.get(i);
            if (condition.getAst().equals(ast)) {
                return condition;
            }
        }
        return null;
    }

    public DefaultMutableTreeNode getTree() {
        CTFNode root = new CTFNode(null);
        root.m_strCode = "ControlFLowTree";
        controlFlowTree = new DefaultMutableTreeNode(root);
        int level = 0;
        ArrayList<DefaultMutableTreeNode> preLevel = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> marks = new HashMap<>();
        preLevel.add(controlFlowTree);
        ArrayList<Integer> rootMark = new ArrayList<>();
        for (int i = 0; i < listPath.size(); i++) {
            rootMark.add(i);
        }
        marks.put(0, rootMark);
        while (preLevel.size() > 0) {
            ArrayList<DefaultMutableTreeNode> curLevel = new ArrayList<>();
            HashMap<Integer, ArrayList<Integer>> curMarks = new HashMap<>();
            for (int i = 0; i < preLevel.size(); i++) {
                for (int j = 0; j < listPath.size(); j++) {
                    ArrayList<AST> path = listPath.get(j);
                    if (marks.get(i).contains(j) && path.size() > level) {
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new CTFNode(path.get(level)));
                        int index = Find(curLevel, node);
                        if (index == -1) {
                            preLevel.get(i).add(node);
                            curLevel.add(node);
                            ArrayList<Integer> mark = new ArrayList<>();
                            mark.add(j);
                            curMarks.put(curLevel.size() - 1, mark);
                        } else {
                            curMarks.get(index).add(j);
                        }
                    }
                }
            }
            preLevel = (ArrayList<DefaultMutableTreeNode>) curLevel.clone();
            marks = (HashMap<Integer, ArrayList<Integer>>) curMarks.clone();
            level++;
        }
        return controlFlowTree;
    }

    private int Find(ArrayList<DefaultMutableTreeNode> list, DefaultMutableTreeNode node) {
        CTFNode CTFnode = (CTFNode) node.getUserObject();
        AST ASTnode = CTFnode.m_AST;
        for (int i = 0; i < list.size(); i++) {
            AST t = ((CTFNode) list.get(i).getUserObject()).m_AST;
            if (t.equals(ASTnode)) {
                return i;
            }
        }
        return -1;
    }

    //Scan for conditions list
    public ArrayList<String> getConditionList() throws CompilationException {
        ConditionPrintVisitor visitor = new ConditionPrintVisitor(null, true);
        ArrayList<String> listConditionExpr = new ArrayList<String>();
        for (int i = 0; i < this.mapTable.size(); i++) {
            AST ast = (AST) mapTable.get(i).getStatementAST();
            if (!(ast instanceof RetStmtAST)) {
                if (ast instanceof IfThenStmtAST) {
                    ast = ((IfThenStmtAST) ast).e;
                } else if (ast instanceof IfThenElseStmtAST) {
                    ast = ((IfThenElseStmtAST) ast).e;
                } else if (ast instanceof WhileStmtAST) {
                    ast = ((WhileStmtAST) ast).e;
                } else if (ast instanceof DoStmtAST) {
                    ast = ((DoStmtAST) ast).e;
                } else if (ast instanceof DeclarationStmtAST) {
                    ast = ((DeclarationStmtAST) ast).dl;
                } else if (ast instanceof ForStmtAST) {
                    ast = ((ForStmtAST) ast).e2;
                } else if (ast instanceof ExprStmtAST) {
                    ast = ((ExprStmtAST) ast).e;
                } else {
                    ast = null;
                }
                if (ast != null) {
                    Condition con = new Condition();
                    con.setStmtID(ast.line);
                    con.setAst(ast);
                    String conExp = visitor.print(ast);
                    con.setCondition(conExp);
                    listCon.add(con);
                    listConditionExpr.add(conExp);
                }
            }
        }
        return listConditionExpr;
    }

    //Generate test case for solvable conditions	
    public String GenerateSolvable() {
        int count = 0;
        String output = "";
        for (int i = 0; i < this.listCon.size(); i++) {
            GenNextTestCase(i);
        }

        for (int i = 0; i < this.listCon.size(); i++) {
            Condition temp = this.listCon.get(i);
            if (temp.isHastc()) {
                count++;
                output += "Condition: " + temp.getCondition() + "\n";
                output += "\tTrue: " + temp.getTruetc() + "\t" + temp.hastruetc + "\n";
                output += "\tFalse: " + temp.getFalsetc() + "\t" + temp.hasfalsetc + "\n";
            }
        }
        output = "Number of solvable condition: " + count + "\n" + output;
        return output;
    }

    //Generate next test case
    private void GenNextTestCase(int i) {
        Random r = new Random();
        boolean checkTruePath = false;
        boolean checkFalsePath = false;
        boolean check = false;
        for (int j = 0; j < this.listCon.get(i).getTruepath().size(); j++) {
            int pos = Math.abs(r.nextInt() % this.listCon.get(i).getTruepath().size());
            String res = GenTestCase(this.listCon.get(i).getTruepath().get(pos));
            if (!res.equals("")) {
                this.listCon.get(i).setTruetc(res);
                this.listCon.get(i).hastruetc = true;
                checkTruePath = true;
                break;
            }
        }
        for (int k = 0; k < this.listCon.get(i).getFalsepath().size(); k++) {
            int pos = Math.abs(r.nextInt() % this.listCon.get(i).getFalsepath().size());
            String res = GenTestCase(this.listCon.get(i).getFalsepath().get(pos));
            if (!res.equals("")) {

                this.listCon.get(i).setFalsetc(res);
                this.listCon.get(i).hasfalsetc = true;
                checkFalsePath = true;
                break;
            }

        }
        Random rand = new Random();
        if (checkFalsePath) {
            if (!checkTruePath) {
                ArrayList<String> temp = new ArrayList<String>();
                for (int j = 0; j < this.listPara.size(); j++) {
                    temp.add(String.valueOf(rand.nextInt() % 100));
                }
                this.listCon.get(i).setTruetc(temp.toString());
                this.listCon.get(i).hastruetc = false;
            }
            check = true;
        } else {
            if (checkTruePath) {
                ArrayList<String> temp = new ArrayList<String>();
                for (int j = 0; j < this.listPara.size(); j++) {
                    temp.add(String.valueOf(rand.nextInt() % 100));
                }
                this.listCon.get(i).setFalsetc(temp.toString());
                this.listCon.get(i).hasfalsetc = false;
                check = true;
            }
        }
        this.listCon.get(i).setHastc(check);
    }

    private String GenTestCase(String con) {
        String z3output = "Z3OUTPUT";
        File z3outFolder = new File(z3output);
        if (!z3outFolder.exists()) {
            z3outFolder.mkdirs();
        }

        String z3FilePath = z3outFolder.getAbsolutePath() + File.separatorChar + "Z3Formula.smt2";
        // Print the parameters, variables, and reindexed variables
        try {
            FileWriter fw = new FileWriter(z3FilePath);
            BufferedWriter out = new BufferedWriter(fw);

            // Print the parameters of program
            for (int i = 0; i < this.listPara.size(); i++) {
                Parameter p = this.listPara.get(i);
                out.write("(declare-const ");
                out.write(p.getName() + " ");
                switch (p.getType()) {
                    case "Int":
                        out.write(p.getType() + ")");
                        break;
                    case "Float":
                    case "Double":
                    case "Real":
                        out.write(" Real)");
                        break;
                }
                out.write("\n");
            }
            // Print variables of program
            for (int i = 0; i < this.listVar.size(); i++) {
                Variable v = this.listVar.get(i);
                out.write("(declare-const ");
                out.write(v.getName() + " ");
                switch (v.getType()) {

                    case "Int":
                        out.write(v.getType() + ")");
                        break;
                    case "Float":
                    case "Double":
                    case "Real":
                        out.write(" Real)");
                        break;
                }
                out.write("\n");
            }
            out.write(con);
            out.write("(check-sat)\n");
            out.write("(model)\n");
            out.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> testcase = this.getNewTestcase(z3FilePath);
        if (testcase != null) {
            ArrayList<String> temp = new ArrayList<String>();
            StringBuffer tc = new StringBuffer();
            for (int i = 0; i < testcase.size(); i++) {
                tc.append(testcase.get(i));
                tc.append("\n");
                temp.add(testcase.get(i));
            }
            return temp.toString();
        } else {
            return "";
        }
    }

    public Object Run(ArrayList<String> testcase) {
        AstSimulationVisitor visistor = new AstSimulationVisitor(pdg, (ArrayList<String>) testcase.clone());

        try {
            astree.visit(visistor, "get_pointer_value");
            ExecutionHistory excutionPath = visistor.getExecutionHistory();
            for (int i = 0; i < listPath.size(); i++) {
                ArrayList<AST> path = listPath.get(i);
                ArrayList<Integer> branch = listBranch.get(i);
                boolean isMatched = true;
                for (int j = 1; j < path.size(); j++) {
                    AST ast = path.get(j);
                    if (excutionPath.get(j - 1).lineID != ast.line) {
                        isMatched = false;
                        break;
                    }
                }
                if (isMatched) {
                    SymbolicExecVisitor visistor2 = new SymbolicExecVisitor(pdg, (ArrayList<String>) testcase.clone(), branch, path);
                    astree.visit(visistor2, "get_pointer_value");
                    return visistor.getRetValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int symbolic_exec(ArrayList<String> testcase, int testcaseIndex) {
        AstSimulationVisitor visistor = new AstSimulationVisitor(pdg, (ArrayList<String>) testcase.clone());

        try {
            astree.visit(visistor, "get_pointer_value");
            ExecutionHistory excutionPath = visistor.getExecutionHistory();
            for (int i = 0; i < listPath.size(); i++) {
                ArrayList<AST> path = listPath.get(i);
                ArrayList<Integer> branch = listBranch.get(i);
                boolean isMatched = true;
                for (int j = 1; j < path.size(); j++) {
                    AST ast = path.get(j);
                    if (excutionPath.get(j - 1).lineID != ast.line) {
                        isMatched = false;
                        break;
                    }
                }
                if (isMatched) {
                    SymbolicExecVisitor visistor2 = new SymbolicExecVisitor(pdg, (ArrayList<String>) testcase.clone(), branch, path);
                    astree.visit(visistor2, "get_pointer_value");
                    Object retValue = visistor.getRetValue();
                    ExprTreeNode retExpr = visistor2.getRetExprTree();
                    System.out.println(retExpr);
                    output.put(testcaseIndex, retValue);
                    exprOutput.put(testcaseIndex, retExpr);
                    for (int k = 0; k < path.size(); k++) {
                        AST nextNode = k < path.size() - 1 ? path.get(k + 1) : null;
                        if (path.get(k).output.containsKey(retExpr)) {
                            ArrayList<AST> a = path.get(k).output.get(retExpr);
                            if (!a.contains(nextNode)) {
                                a.add(nextNode);
                            }
                        } else {
                            ArrayList<AST> a = new ArrayList<>();
                            a.add(nextNode);
                            path.get(k).output.put(retExpr, a);
                        }
                    }
                    return i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<AST> FindBug(ArrayList<String> testcase, Object expectedOutput, int pathID) {
        ArrayList<AST> ret = new ArrayList<>();
        ArrayList<AST> path = listPath.get(pathID);
        for (int i = 0; i < path.size(); i++) {
            AST nextNode = i < path.size() - 1 ? path.get(i + 1) : null;
            AST node = path.get(i);
            Object[] keySet = node.output.keySet().toArray();
            for (int j = 0; j < keySet.length; j++) {
                ExprTreeNode key = (ExprTreeNode) keySet[j];
                Object retValue = key.evaluate(testcase);
                if (retValue != null && retValue.equals(expectedOutput)) {
                    ArrayList<AST> nextNodeList = node.output.get(key);
                    if ((nextNodeList.size() != 1 || (nextNodeList.get(0) != null && !nextNodeList.get(0).equals(nextNode)))) {
                        ret.add(node);
                    }
                }
            }
        }
        return ret;
    }

//        public ArrayList<AST> FindBugInGold(Object expectedOutput, int pathID)
//        {
//            ArrayList<AST> ret = new ArrayList<>();
//            ArrayList<AST> path = listPath.get(pathID);
//            for (int i = 0; i < path.size(); i++)
//            {
//                AST nextNode = i < path.size() - 1 ? path.get(i + 1) : null;
//                AST node = path.get(i);
//                if (node.output.containsKey(expectedOutput))
//                {
//                    ArrayList<AST> nextNodeList = node.output.get(expectedOutput);
//                    if ((nextNodeList.size() != 1 || (nextNodeList.get(0) != null && !nextNodeList.get(0).equals(nextNode))))
//                    {
//                        ret.add(node);
//                    }
//                }
//            }
//            return ret;
//        }
    public ArrayList<String> solve_constraint(String con) {
        String z3output = "Z3OUTPUT";
        File z3outFolder = new File(z3output);
        if (!z3outFolder.exists()) {
            z3outFolder.mkdirs();
        }

        String z3FilePath = z3outFolder.getAbsolutePath() + File.separatorChar + "Z3Formula.smt2";
        // Print the parameters, variables, and reindexed variables
        try {
            FileWriter fw = new FileWriter(z3FilePath);
            BufferedWriter out = new BufferedWriter(fw);

            // Print the parameters of program
            for (int i = 0; i < this.listPara.size(); i++) {
                Parameter p = this.listPara.get(i);
                out.write("(declare-const ");
                out.write(p.getName() + " ");
                switch (p.getType()) {
                    case "Int":
                        out.write(p.getType() + ")");
                        break;
                    case "Float":
                    case "Double":
                    case "Real":
                        out.write(" Real)");
                        break;
                }
                out.write("\n");
            }
            // Print variables of program
            for (int i = 0; i < this.listVar.size(); i++) {
                Variable v = this.listVar.get(i);
                out.write("(declare-const ");
                out.write(v.getName() + " ");
                switch (v.getType()) {

                    case "Int":
                        out.write(v.getType() + ")");
                        break;
                    case "Float":
                    case "Double":
                    case "Real":
                        out.write(" Real)");
                        break;
                }
                out.write("\n");
            }
            out.write(con);
            out.write("(check-sat)\n");
            out.write("(model)\n");
            out.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.getNewTestcase(z3FilePath);
    }

    private ArrayList<String> getNewTestcase(String z3FormulaFilename) {
        ArrayList<String> testcase = new ArrayList<String>();
        ArrayList<String> z3result = new ArrayList<String>();
        Runtime run = Runtime.getRuntime();
        try {
            //String runZ3 = "./Z3/z3.exe";
            //String config = " /m ";
            //String formulaFile = z3FormulaFilename;
            //Process pp = run.exec(runZ3 + config + formulaFile);
            //BufferedReader in = new BufferedReader(new InputStreamReader(pp.getInputStream()));
            Process pp = run.exec("./runZ3.sh");
            //pp.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(pp.getInputStream()));
            String line = in.readLine();
            if (line.contains("sat") && !line.contains("unsat")) {
                while ((line = in.readLine()) != null) {
                    if (line.contains("define")) {
                        String sub;
                        if (!line.contains(")\")")) { // not contain
                            sub = line.substring(8, line.length() - 1);
                        } else {
                            sub = line.substring(8, line.length() - 3);
                        }
                        Scanner sc = new Scanner(sub);
                        sc.useDelimiter(" ");
                        z3result.add(sc.next());
                        z3result.add(sc.next());
                        sc.close();
                    }
                }
                // process z3result
                for (int i = 0; i < this.listPara.size(); i++) {
                    for (int j = 0; j < z3result.size(); j += 2) {
                        if (z3result.get(j).equals(this.listPara.get(i).getName())) {
                            testcase.add(z3result.get(j + 1)); // add new value to testcase
                            break;
                        }
                    }
                    if (testcase.size() < i) {
                        Object result = 0;
                        Random ran1 = new Random();
                        double n = ran1.nextDouble() * 100;
                        switch (this.listPara.get(i).getType()) {
                            case "Int":
                                result = (int) n;
                                break;
                            case "Double":
                                result = (double) n;
                                break;
                            case "Float":
                                result = (float) n;
                                break;
                        }
                        testcase.add(result.toString());
                    }
                }
                if (testcase.size() < this.listPara.size()) {
                    int num = this.listPara.size() - testcase.size();
                    Random ran1 = new Random();
                    for (int i = 0; i < num; i++) {
                        Object result = 0;
                        double n = ran1.nextDouble() * 100;
                        switch (this.listPara.get(i).getType()) {
                            case "Int":
                                result = (int) n;
                                break;
                            case "Double":
                                result = (double) n;
                                break;
                            case "Float":
                                result = (float) n;
                                break;
                        }
                        testcase.add(result.toString());
                    }
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testcase;
    }

    public ArrayList<Integer> getSlide() {
        ArrayList<String> input = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(testcase, "[, ]");
        while (st.hasMoreTokens()) {
            input.add(st.nextToken());
        }
        AstSimulationVisitor simulationAST = new AstSimulationVisitor(this.pdg, input);
        try {
            this.astree.visit(simulationAST, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExecutionHistory eh = simulationAST.getExecutionHistory();
        eh.changeLineIdAtExecNodePointToNode(this.pdg);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < eh.size(); i++) {
            result.add(eh.get(i).getNode().getID());
        }
        return result;
    }

    //Get number of unsolvable condition
    public int getNumUnsolvableCon() {
        numUnsolvableCon = 0;
        for (int i = 0; i < this.listCon.size(); i++) {
            if (this.listCon.get(i).isHastc() == false) {
                numUnsolvableCon++;
            }
        }
        return numUnsolvableCon;
    }

    //Get number of parameter
    public int getNumPar() {
        return this.listPara.size();
    }

    //Check the fitness value of the testcase lists	
    public int[] check(Object[][] testcase) {
        int numCon = this.listCon.size();
        int numPar = this.listPara.size();
        int[] result = new int[numUnsolvableCon * 2];
        int count = 0;

        for (int i = 0; i < numCon; i++) {
            if (this.listCon.get(i).isHastc() == false) {
                ArrayList<String> temp = new ArrayList<String>();
                ArrayList<String> temp1 = new ArrayList<String>();
                for (int j = 0; j < numPar; j++) {
                    temp.add(testcase[count][j].toString());
                    temp1.add(testcase[count + 1][j].toString());
                }
                result[count] = checkCon(temp, i, 0);
                result[count + 1] = checkCon(temp1, i, 1);
                count += 2;
            }
        }
        return result;
    }

    //Check whether test case satisfy condition
    private int checkCon(ArrayList<String> testcase, int con, int branch) {
        System.out.println(testcase.get(0));
        double result = 100;
        double temp;
        int count = 0;
        ConditionCheckVisitor visitor = new ConditionCheckVisitor(this.listPara, this.listVar, testcase);
        try {
            boolean check;
            if (branch == 0) {
                for (int i = 0; i < this.listCon.get(con).getTruecon().size(); i++) {
                    temp = 0;
                    check = false;
                    int j;
                    ArrayList<AST> path = this.listPath.get(this.listCon.get(con).getTruecon().get(i));
                    ArrayList<Integer> lbranch = this.listBranch.get(this.listCon.get(con).getTruecon().get(i));
                    for (j = 0; path.get(j).line < this.listCon.get(con).getStmtID(); j++) {
                        count++;
                        double res = (double) path.get(j).visit(visitor, lbranch.get(j));
                        if (check == true) {
                            temp += 100;
                        }
                        if (res != 0) {
                            temp += res;
                            check = true;
                        }
                    }
                    temp += (double) path.get(j).visit(visitor, branch);
                    count++;
                    if (temp != 0) {
                        temp = temp / count + 1;
                    } else {
                        temp = 0;
                    }
                    if (temp < result) {
                        result = temp;
                    }
                    visitor.clear();
                }
            } else {
                for (int i = 0; i < this.listCon.get(con).getFalsecon().size(); i++) {
                    temp = 0;
                    check = false;
                    int j;
                    ArrayList<AST> path = this.listPath.get(this.listCon.get(con).getFalsecon().get(i));
                    ArrayList<Integer> lbranch = this.listBranch.get(this.listCon.get(con).getFalsecon().get(i));
                    for (j = 0; path.get(j).line < this.listCon.get(con).getStmtID(); j++) {
                        count++;
                        double res = (double) path.get(j).visit(visitor, lbranch.get(j));
                        if (check == true) {
                            temp += 100;
                        }
                        if (res != 0) {
                            temp += res;
                            check = true;
                        }
                    }
                    temp += (double) path.get(j).visit(visitor, branch);
                    count++;
                    if (temp != 0) {
                        temp = temp / count + 1;
                    } else {
                        temp = 0;
                    }
                    if (temp < result) {
                        result = temp;
                    }
                    visitor.clear();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException("Invalid Input!!!"); // if the input is invalid
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Type is not correct!!!");
        } catch (CompilationException e) {
            e.printStackTrace();
        }
        return (int) result;
    }

    public String updateResult(Object[][] res) {
        String output = "";
        int count = 0;
        int numCon = this.listCon.size();
        int numPar = this.listPara.size();
        for (int i = 0; i < numCon; i++) {
            if (this.listCon.get(i).isHastc() == false) {
                int j;
                String truetc = "[";
                String falsetc = "[";
                for (j = 0; j < numPar; j++) {
                    truetc += res[count][j].toString();
                    falsetc += res[count + 1][j].toString();
                    if (j < numPar - 1) {
                        truetc += ", ";
                        falsetc += ", ";
                    }
                }
                truetc += "]";
                this.listCon.get(i).setTruetc(truetc);
                falsetc += "]";
                this.listCon.get(i).setFalsetc(falsetc);
                if ((Integer) res[count][j] == 0) {
                    this.listCon.get(i).hastruetc = true;
                } else {
                    this.listCon.get(i).hastruetc = false;
                }

                if ((Integer) res[count + 1][j] == 0) {
                    this.listCon.get(i).hasfalsetc = true;
                } else {
                    this.listCon.get(i).hasfalsetc = false;
                }

                output += "Condition: " + this.listCon.get(i).getCondition() + "\n";
                output += "\tTrue: " + this.listCon.get(i).getTruetc() + "\t" + this.listCon.get(i).hastruetc + "\n";
                output += "\tFalse: " + this.listCon.get(i).getFalsetc() + "\t" + this.listCon.get(i).hasfalsetc + "\n";
                count += 2;
            }
        }
        output = "Number of unsolvable condition: " + this.numUnsolvableCon + "\n" + output;
        return output;
    }

    public String showAllTestCase() {
        String output = "";
        for (int i = 0; i < this.listCon.size(); i++) {
            Condition temp = this.listCon.get(i);
            output += "Condition " + (i + 1) + ": " + temp.getCondition() + "\n";
            output += "\tTrue: " + temp.getTruetc() + "\t" + (temp.hastruetc) + "\n";
            output += "\tFalse: " + temp.getFalsetc() + "\t" + (temp.hasfalsetc) + "\n";
        }
        return output;
    }

    public String scanCondition() {
        String output = "";
        listCon = transform.updateConList(listCon);
        for (int i = 0; i < this.listCon.size(); i++) {
            if (this.listCon.get(i).getCondition() == null) {
                this.listCon.remove(i);
                i--;
            } else {
                output += "Condition " + (i + 1) + ":" + this.listCon.get(i).getCondition() + "\n";
                output += "True:\n";
                for (int j = 0; j < this.listCon.get(i).getTruepath().size(); j++) {
                    output += this.listCon.get(i).getTruepath().get(j) + "\n";
                }
                output += "False:\n";
                for (int j = 0; j < this.listCon.get(i).getFalsepath().size(); j++) {
                    output += this.listCon.get(i).getFalsepath().get(j) + "\n";
                }
            }
        }
        return output;
    }

    public ArrayList<Boolean> getTrueList() {
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < this.listCon.size(); i++) {
            if (this.listCon.get(i).hastruetc == true) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    public ArrayList<Boolean> getFalseList() {
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < this.listCon.size(); i++) {
            if (this.listCon.get(i).hasfalsetc == true) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    public ArrayList<Integer> getPrevTestCase() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (currTestCasePos > 0) {
            currTestCasePos--;
        }
        if (currTestCasePos % 2 == 0) {
            testcase = this.listCon.get(currTestCasePos / 2).getTruetc();
        } else {
            testcase = this.listCon.get(currTestCasePos / 2).getFalsetc();
        }
        StringTokenizer st = new StringTokenizer(testcase, "[, ]");
        while (st.hasMoreTokens()) {
            int temp = Integer.parseInt(st.nextToken());
            result.add(temp);
        }
        return result;
    }

    public ArrayList<Integer> getNextTestCase() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (currTestCasePos < this.listCon.size() * 2 - 1) {
            currTestCasePos++;
        }
        if (currTestCasePos % 2 == 0) {
            testcase = this.listCon.get(currTestCasePos / 2).getTruetc();
        } else {
            testcase = this.listCon.get(currTestCasePos / 2).getFalsetc();
        }
        StringTokenizer st = new StringTokenizer(testcase, "[, ]");
        while (st.hasMoreTokens()) {
            result.add(Integer.parseInt(st.nextToken()));
        }
        return result;
    }

    public ArrayList<Parameter> getParaList() {
        return this.listPara;
    }
}
