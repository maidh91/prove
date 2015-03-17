package transform.CodeGeneration;

import java.util.ArrayList;
import java.util.Stack;

import system.*;
import transform.AST.*;
import transform.DependenceGraph.*;



/***********************************************************************************
 * Visitor duyet qua cay AST de thuc hien simulation tim ra Execution History
 ***********************************************************************************/
public class SymbolicExecVisitor
        extends DoNothingVisitor
{
    /**
     * 
     */
    static final String GET_VARIABLE_NAME = "GetVariableName";
    /**
     * bien tra ve cua 1 function
     */
    static final String RET_VAR_NAME = "retvar";
    
    SimulationMemory simulation;
    ExecutionHistory execHistory;
    ArrayList<String> listInput;
    protected PDG pdg;
    ArrayList<Integer> branch;
    ArrayList<AST> path;
    //protected PointerTable pointerTable;
    
    boolean isReturned;
    ExprTreeNode retExprTree;
    
    private int paraCount;
    
    public SymbolicExecVisitor()
    {
        this.simulation = new SimulationMemory();
        this.execHistory = new ExecutionHistory();
        //this.pointerTable = new PointerTable();
        isReturned = false;
        paraCount = 0;
    }
    
    public SymbolicExecVisitor(ArrayList<String> list)
    {
        this.simulation = new SimulationMemory();
        this.execHistory = new ExecutionHistory();
        //this.pointerTable = new PointerTable();
        this.listInput = list;
        isReturned = false;
        paraCount = 0;
    }
    
    public SymbolicExecVisitor(PDG pdg, ArrayList<String> list, ArrayList<Integer> branch, ArrayList<AST> path)
    {
        this.pdg = pdg;
        this.simulation = new SimulationMemory();
        this.execHistory = new ExecutionHistory();
        //this.pointerTable = new PointerTable();
        this.listInput = list;
        this.branch = branch;
        this.path = path;
//        isReturned = false;
        paraCount = 0;
    }
    
    public ExecutionHistory getExecutionHistory()
    {
        return this.execHistory;
    }
    
    public ExprTreeNode getRetExprTree()
    {
        return retExprTree;
    }
    
    
    
    /*public PointerTable getPointerTable()
    {
        return this.pointerTable;
    }*/

    public void println(Object o)
    {
        System.out.println(o);
    }
    
    @SuppressWarnings("rawtypes")
    public void printArrayList(ArrayList list)
    {
        System.out.println("ArrayList:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("EndArrayList:");
    }
    
    // ProgramAST
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.dl.visit(this, o);
        return null;
    }
    
    // DeclarationListAST
    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.d.visit(this, o);
        ast.dl.visit(this, o);
        return null;
    }
    
    // DeclarationStmtAST
    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.dl.visit(this, o);
        return null;
    }
    
    // VarInitializer
    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        return ast.e.visit(this, o);
    }
    
    // VarDeclAST
    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        // ast.t.visit(this, o);
        TypeAST type;
        if (ast.t instanceof TypeListAST) {
            type = ((TypeListAST) ast.t).t;
        }
        else {
            type = ast.t;
        }
        
        int scope = this.simulation.currentScope;
        if (ast.init != null) {
            this.simulation.addNewVariable(type, ast.id.getText(), ast.init.visit(this, o), scope);
            // change in pdg
            int line = ast.line;
            Node node = this.pdg.findNodeAtLine(line);
            node.getDefinedVar().setScope(scope);
            
            // add executed node
            this.execHistory.addExecNode(new ExecNode(ast.line, 'N'));
        }
        else {
            this.simulation.addNewVariable(type, ast.id.getText(), scope);
        }
        
        return null;
    }
    
    // FuncDeclAST
    @Override
    public Object visitFuncDeclAST(FuncDeclAST fAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.simulation.enterScope();
        fAst.para.visit(this, o);
        fAst.retType.visit(this, o);
        
        TypeAST retType = ((TypeListAST) fAst.retType).t;
        int scope = this.simulation.currentScope;
        this.simulation.addNewVariable(retType, RET_VAR_NAME, scope);
        
        fAst.c.visit(this, o);
        
        this.simulation.exitScope();
        isReturned = false;
        return null;
    }
    
    // ParaListAST
    @Override
    public Object visitParaListAST(ParaListAST pAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        pAst.v.visit(this, o);
        pAst.p.visit(this, o);
        return null;
    }
    
    // ParaAST
    @Override
    public Object visitParaAST(ParaAST pAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        pAst.t.visit(this, o);
        TypeAST type = ((TypeListAST) pAst.t).t;
        if (this.listInput.size() > 0) {
            // get the input value from listInput
            String input = this.listInput.remove(0);
            Object value;
            int opType = BinExprAST.UNDEFINED;
            if (input.indexOf('.') != -1) {
                value = Float.parseFloat(input);
                opType = BinExprAST.FLOAT_LITTERAL;
            }
            else if (input.indexOf('"') != -1) {
                value = input.substring(1, input.length() - 1);
                opType = BinExprAST.STRING_LITTERAL;
            }
            else {
                value = Integer.parseInt(input);
                opType = BinExprAST.INT_LITTERAL;
            }
            int scope = this.simulation.currentScope;
            this.simulation.addNewVariable(type, pAst.id.getText(), new ExprTreeNode(opType, null, value, pAst.id.getText(), paraCount++), scope);
        }
        else {
            // System.out.println("Error input");
        }
        return null;
    }
    
    // StmtListAST
    @Override
    public Object visitStmtListAST(StmtListAST sAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        sAst.o.visit(this, o);
        sAst.s.visit(this, o);
        return null;
    }
    
    // CompStmtAST
    @Override
    public Object visitCompStmtAST(CompStmtAST cAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        cAst.s.visit(this, o);
        return null;
    }
    
    // ExprListAST
    @Override
    public Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.e.visit(this, o);
        ast.l.visit(this, o);
        return null;
    }
    
    /***********************************************************************************
     * Handling ExprAST
     ***********************************************************************************/
    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.line = ast.parent.line;
        
        String name = ast.name.getText();
        if (o.equals(GET_VARIABLE_NAME)) {
            return name;
        }
        else if (o.equals("address")) {
            /*
             * return the record pointed by pointer
             */
            Node node = this.pdg.findNodeAtLine(ast.line);
            if (node != null) {
                VariableUsed usedVar = node.findUsedVar(name);
                if (usedVar != null) {
                    int scope = this.simulation.getScopeOfVariable(name);
                    usedVar.setScope(scope);
                }
            }
            Variable record = this.simulation.getAddressOf(name);
            return record;
        }
        else if (o.equals("get_pointed_address")) {
            /*
             * return the varName pointed by pointer.
             * if the pointer was not point to any record,
             * this is a free pointer.
             */
            Object value = this.simulation.getValueOfVariable(name);
            if (value instanceof Variable) {
                return ((Variable) value).varName;
            }
            else {
                return name;
            }
        }
        else if (o.equals("get_pointer_value")) {
            Node node = this.pdg.findNodeAtLine(ast.line);
            if (node != null) {
                VariableUsed usedVar = node.findUsedVar(name);
                if (usedVar != null) {
                    int scope = this.simulation.getScopeOfVariable(name);
                    usedVar.setScope(scope);
                }
            }
//            Object value = this.simulation.getValueOfVariable(name);
//            if (value instanceof Variable) {
//                return ((Variable) value).value;
//            }
//            else {
//                return value;
//            }
            ExprTreeNode value = (ExprTreeNode)this.simulation.getExprTreeOfVariable(name);
//            if (value instanceof Variable) {
//                return ((Variable) value).value;
//            }
//            else {
                return value;
//            }
        }
        else {
            Node node = this.pdg.findNodeAtLine(ast.line);
            if (node != null) {
                VariableUsed usedVar = node.findUsedVar(name);
                if (usedVar != null) {
                    int scope = this.simulation.getScopeOfVariable(name);
                    usedVar.setScope(scope);
                }
            }
//            return this.simulation.getValueOfVariable(name);
            return this.simulation.getExprTreeOfVariable(name);
        }
    }
    
    // CharLiteralAST
    @Override
    public Object visitCharLiteralAST(CharLiteralAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        String str = ast.literal.getText();
        str = str.substring(1, str.length() - 1);
//        return str;
        return new ExprTreeNode(BinExprAST.CHAR_LITTERAL, null, str);
    }
    
    // StringLiteralAST
    @Override
    public Object visitStringLiteralAST(StringLiteralAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        String str = ast.literal.getText();
        str = str.substring(1, str.length() - 1);
//        return str;
        return new ExprTreeNode(BinExprAST.STRING_LITTERAL, null, str);
    }
    
    // IntLiteralAST
    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
//        return new Integer(ast.literal.getText());
        return new ExprTreeNode(BinExprAST.INT_LITTERAL, null, new Integer(ast.literal.getText()));
    }
    
    // FloatLiteralAST
    @Override
    public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
//        return new Float(ast.literal.getText());
        return new ExprTreeNode(BinExprAST.FLOAT_LITTERAL, null, new Float(ast.literal.getText()));
    }
    
    // UnaryExprAST
    @Override
    public Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        Object val;
        if (ast.opType == UnaryExprAST.ADDR_OF) {
            if (o.equals(GET_VARIABLE_NAME)) {
                return ast.e.visit(this, GET_VARIABLE_NAME);
            }
            else {
                val = ast.e.visit(this, "address");
            }
        }
        else if (ast.opType == UnaryExprAST.INDIRECTION) {
            if (o.equals("get_pointer_value")) {
                val = ast.e.visit(this, "get_pointer_value");
            }
            else {
                val = ast.e.visit(this, "get_pointed_address");
            }
        }
        else {
            val = ast.e.visit(this, o);
        }
        
        if ((ast.opType >= UnaryExprAST.PRE_INC) && (ast.opType <= UnaryExprAST.POST_DEC)) {
            int opType;
            boolean pre_increment; // true if (++i or --i), false if (i++ or i--)
            String varName = "";
            if (ast.e instanceof VarExprAST) {
                varName = ((VarExprAST) ast.e).name.getText();
            }
            else if (ast.e instanceof EleExprAST) {
                varName = ((EleExprAST) ast.e).name.getText();
            }
            
            if (ast.opType == UnaryExprAST.PRE_INC) {
                // ++i
                opType = BinExprAST.PLUS;
                pre_increment = true;
            }
            else if (ast.opType == UnaryExprAST.PRE_DEC) {
                // --i
                opType = BinExprAST.MINUS;
                pre_increment = true;
            }
            else if (ast.opType == UnaryExprAST.POST_INC) {
                // i++
                opType = BinExprAST.PLUS;
                pre_increment = false;
            }
            else {
                // i--
                opType = BinExprAST.MINUS;
                pre_increment = false;
            }
            ExprAST addingOneBinExpr = new BinExprAST(ast.e, opType, null, new NullExprAST());
//            Object new_value = addingOneBinExpr.visit(this, "increment");
//            this.simulation.updateValueOfVariable(varName, new_value);
            ExprTreeNode new_value = (ExprTreeNode)addingOneBinExpr.visit(this, "increment");
            this.simulation.updateExprTreeOfVariable(varName, new_value);
            if (pre_increment) {
                // increment first then return the new value
                return new_value;
            }
            else {
                // return the old value and also
                return val;
            }
        }
        else if (ast.opType == UnaryExprAST.UNARY_MINUS) {
            // -i
            ExprAST zeroMinusBinExpr = new BinExprAST(new NullExprAST(), BinExprAST.MINUS, null, ast.e);
            return zeroMinusBinExpr.visit(this, "zero_minus");
        }
        else if (ast.opType == UnaryExprAST.LOGICAL_NOT) {
            // !bool
            if (val instanceof Boolean) {
                boolean v = ((Boolean) val).booleanValue();
                return !v;
            }
        }
        
        return val;
    }
    
    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
//        Object val1;
        ExprTreeNode val1;
        if ("zero_minus".equals(o)) {
            // phep toan 0 - ast.e2
//            val1 = new Integer(0);
            val1 = new ExprTreeNode(BinExprAST.INT_LITTERAL, null, new Integer(0));
        }
        else {
            if (ast.e1 instanceof UnaryExprAST) {
                val1 = (ExprTreeNode)ast.e1.visit(this, "get_pointer_value");
            }
            else {
                val1 = (ExprTreeNode)ast.e1.visit(this, o);
            }
        }
        
//        Object val2;
        ExprTreeNode val2;
        if ("increment".equals(o)) {
            // phep toan ast.e1 + 1
//            val2 = new Integer(1);
            val2 = new ExprTreeNode(BinExprAST.INT_LITTERAL, null, new Integer(1));
        }
        else {
            if (ast.e2 instanceof UnaryExprAST) {
                if (((UnaryExprAST) ast.e2).opType == UnaryExprAST.INDIRECTION) {
                    val2 = (ExprTreeNode)ast.e2.visit(this, "get_pointer_value");
                }
                else {
                    val2 = (ExprTreeNode)ast.e2.visit(this, o);
                }
            }
            else {
                val2 = (ExprTreeNode)ast.e2.visit(this, o);
            }
        }
        
        // cac loai phep gan =, +=, -=, *=, /=, %=
        if ((ast.opType >= BinExprAST.ASSIGN) && (ast.opType <= BinExprAST.MOD_ASSIGN)) {
            // them phep gan dia chi pointer vao pointer table
            if (ast.opType == BinExprAST.ASSIGN) {
                if (ast.e2 instanceof UnaryExprAST) {
                    //String pointerName = (String) ast.e1.visit(this, GET_VARIABLE_NAME);
                    //String varName = (String) ast.e2.visit(this, GET_VARIABLE_NAME);
                    // System.out.println(pointerName + " " + varName);
                    
                    //this.pointerTable.add(new PointerRecord(pointerName, varName, ast.line));
                    
                    // System.out.println(this.pointerTable.toString());
                }
            }
            
            String varName = "";
            if (ast.e1 instanceof VarExprAST) {
                varName = ((VarExprAST) ast.e1).name.getText();
            }
            else if (ast.e1 instanceof EleExprAST) {
                varName = ((EleExprAST) ast.e1).name.getText();
            }
            else if (ast.e1 instanceof UnaryExprAST) {
                varName = (String) ast.e1.visit(this, "get_pointer_name");
            }
            
            int scope;
            if (ast.opType == BinExprAST.ASSIGN) {
                // phep gan =
//                scope = this.simulation.updateValueOfVariable(varName, val2);
                scope = this.simulation.updateExprTreeOfVariable(varName, val2);
                // set scope
                Node node = this.pdg.findNodeAtLine(ast.line);
                node.getDefinedVar().setScope(scope);
            }
            else {
                // phep gan +=, -=, *=, /=, %=
                // opType cua phep gan: 1, 2, 3, 4, 5
                // opType cua phep toan tuong ung: 24, 25, 26, 27, 28
                ExprAST binExpr = new BinExprAST(ast.e1, ast.opType + 23, null, ast.e2);
//                Object val = binExpr.visit(this, o);
                ExprTreeNode val = (ExprTreeNode)binExpr.visit(this, o);
//                scope = this.simulation.updateValueOfVariable(varName, val);
                scope = this.simulation.updateExprTreeOfVariable(varName, val);
                // set scope
                Node node = this.pdg.findNodeAtLine(ast.line);
                node.getDefinedVar().setScope(scope);
            }
            return scope;
        }
        // phep luan ly giua cac Boolean
        else if ((ast.opType == BinExprAST.LOGICAL_OR) || (ast.opType == BinExprAST.LOGICAL_AND)) {
            // phep luan ly hoac-va || &&
            // 1. so sanh giua 2 boolean => return Boolean
//            if ((val1 instanceof Boolean) && (val2 instanceof Boolean)) {
            if ((val1.opType == BinExprAST.BOOL_LITTERAL) && (val2.opType == BinExprAST.BOOL_LITTERAL)) {
//                boolean v1 = ((Boolean) val1).booleanValue();
//                boolean v2 = ((Boolean) val2).booleanValue();
                ArrayList<ExprTreeNode> childs = new ArrayList<>();
                childs.add(val1);
                childs.add(val2);
                if (ast.opType == BinExprAST.LOGICAL_OR) {
                    // phep luan ly ||
//                    return new Boolean(v1 || v2);
                    return new ExprTreeNode(BinExprAST.LOGICAL_OR, childs, null);
                }
                else {
                    // phep luan ly &&
//                    return new Boolean(v1 && v2);
                    return new ExprTreeNode(BinExprAST.LOGICAL_AND, childs, null);
                }
            }
        }
        // cac loai phep so sanh
        else if (ast.opType == BinExprAST.EQUAL) {
            // phep so sanh ==
            // 1. so sanh == giua 2 so nguyen => return Boolean
            // 2. so sanh == giua 2 so thuc => return Boolean
            // 3. so sanh == giua 2 String => return Boolean
            // 4. so sanh == giua so nguyen va thuc => return Boolean
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.EQUAL, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//            if ((val1.opType == INT_LITTERAL) && (val2.opType == INT_LITTERAL)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Boolean(v1 == v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Boolean(v1 == v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Boolean(v1 == v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Boolean(v1 == v2);
//            }
//            else if ((val1 instanceof String) && (val2 instanceof String)) {
//                String v1 = (String) val1;
//                String v2 = (String) val2;
//                return new Boolean(v1.equals(v2));
//            }
        }
        else if (ast.opType == BinExprAST.NOT_EQUAL) {
            // phep so sanh !=
            // 1. so sanh != giua 2 so nguyen => return Boolean
            // 2. so sanh != giua 2 so thuc => return Boolean
            // 3. so sanh != giua 2 String => return Boolean
            // 4. so sanh != giua so nguyen va thuc => return Boolean
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.NOT_EQUAL, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Boolean(v1 != v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Boolean(v1 != v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Boolean(v1 != v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Boolean(v1 != v2);
//            }
//            else if ((val1 instanceof String) && (val2 instanceof String)) {
//                String v1 = (String) val1;
//                String v2 = (String) val2;
//                return new Boolean(!v1.equals(v2));
//            }
        }
        else if ((ast.opType >= BinExprAST.LESS_OR_EQUAL) && (ast.opType <= BinExprAST.LESS_THAN)) {
            // phep so sanh <= (18) >= (19) > (20) < (21)
            // 1. so sanh giua 2 so nguyen => return Boolean
            // 2. so sanh giua 2 so thuc => return Boolean
            // 3. so sanh giua so nguyen va thuc => return Boolean
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.LESS_OR_EQUAL, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                if (ast.opType == BinExprAST.LESS_OR_EQUAL) {
//                    // phep <=
//                    return new Boolean(v1 <= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) {
//                    // phep >=
//                    return new Boolean(v1 >= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_THAN) {
//                    // phep >
//                    return new Boolean(v1 > v2);
//                }
//                else {
//                    // phep <
//                    return new Boolean(v1 < v2);
//                }
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                if (ast.opType == BinExprAST.LESS_OR_EQUAL) {
//                    // phep <=
//                    return new Boolean(v1 <= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) {
//                    // phep >=
//                    return new Boolean(v1 >= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_THAN) {
//                    // phep >
//                    return new Boolean(v1 > v2);
//                }
//                else {
//                    // phep <
//                    return new Boolean(v1 < v2);
//                }
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                if (ast.opType == BinExprAST.LESS_OR_EQUAL) {
//                    // phep <=
//                    return new Boolean(v1 <= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) {
//                    // phep >=
//                    return new Boolean(v1 >= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_THAN) {
//                    // phep >
//                    return new Boolean(v1 > v2);
//                }
//                else {
//                    // phep <
//                    return new Boolean(v1 < v2);
//                }
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                if (ast.opType == BinExprAST.LESS_OR_EQUAL) {
//                    // phep <=
//                    return new Boolean(v1 <= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) {
//                    // phep >=
//                    return new Boolean(v1 >= v2);
//                }
//                else if (ast.opType == BinExprAST.GREATER_THAN) {
//                    // phep >
//                    return new Boolean(v1 > v2);
//                }
//                else {
//                    // phep <
//                    return new Boolean(v1 < v2);
//                }
//            }
        }
        // cac loai phep toan
        else if (ast.opType == BinExprAST.PLUS) {
            // phep toan +
            // 1. cong giua 2 so nguyen => return so nguyen
            // 2. cong giua 2 so thuc => return so thuc
            // 3. cong giua 2 string => return string
            // 4. cong giua so nguyen va thuc => return so thuc
            // 5. cong giua so nguyen va string => return string
            // 6. cong giua so thuc va string => return string
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.PLUS, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Integer(v1 + v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 + v2);
//            }
//            else if ((val1 instanceof String) && (val2 instanceof String)) {
//                String v1 = (String) val1;
//                String v2 = (String) val2;
//                return (v1 + v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 + v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Float(v1 + v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof String)) {
//                int v1 = ((Integer) val1).intValue();
//                String v2 = (String) val2;
//                return String.valueOf(v1) + v2;
//            }
//            else if ((val1 instanceof String) && (val2 instanceof Integer)) {
//                String v1 = (String) val1;
//                int v2 = ((Integer) val2).intValue();
//                return v1 + String.valueOf(v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof String)) {
//                float v1 = ((Float) val1).floatValue();
//                String v2 = (String) val2;
//                return String.valueOf(v1) + v2;
//            }
//            else if ((val1 instanceof String) && (val2 instanceof Float)) {
//                String v1 = (String) val1;
//                float v2 = ((Float) val2).floatValue();
//                return v1 + String.valueOf(v2);
//            }
        }
        else if (ast.opType == BinExprAST.MINUS) {
            // phep toan -
            // 1. tru giua 2 so nguyen => return so nguyen
            // 2. tru giua 2 so thuc => return so thuc
            // 3. tru giua 2 so nguyen va thuc => return so thuc
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.MINUS, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Integer(v1 - v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 - v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 - v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Float(v1 - v2);
//            }
        }
        else if (ast.opType == BinExprAST.STAR) {
            // phep toan *
            // 1. nhan giua 2 so nguyen => return so nguyen
            // 2. nhan giua 2 so thuc => return so thuc
            // 3. nhan giua 2 so nguyen va thuc => return so thuc
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.STAR, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Integer(v1 * v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 * v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 * v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Float(v1 * v2);
//            }
        }
        else if (ast.opType == BinExprAST.DIV) {
            // phep toan /
            // 1. chia giua 2 so nguyen => return so nguyen
            // 2. chia giua 2 so thuc => return so thuc
            // 3. chia giua 2 so nguyen va thuc => return so thuc
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.DIV, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Integer(v1 / v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Float)) {
//                float v1 = ((Float) val1).floatValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 / v2);
//            }
//            else if ((val1 instanceof Integer) && (val2 instanceof Float)) {
//                int v1 = ((Integer) val1).intValue();
//                float v2 = ((Float) val2).floatValue();
//                return new Float(v1 / v2);
//            }
//            else if ((val1 instanceof Float) && (val2 instanceof Integer)) {
//                float v1 = ((Float) val1).floatValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Float(v1 / v2);
//            }
        }
        else if (ast.opType == BinExprAST.MOD) {
            // phep toan %
            // 1. mod giua 2 so nguyen => return so nguyen
            
            ArrayList<ExprTreeNode> childs = new ArrayList<>();
            childs.add(val1);
            childs.add(val2);
            return new ExprTreeNode(BinExprAST.MOD, childs, null);
            
//            if ((val1 instanceof Integer) && (val2 instanceof Integer)) {
//                int v1 = ((Integer) val1).intValue();
//                int v2 = ((Integer) val2).intValue();
//                return new Integer(v1 % v2);
//            }
        }
        
        return null;
    }
    
    // CallExprAST
    @Override
    public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        if (ast.name.getText().equals("println")) {
            Object value = ast.e.e.visit(this, o);
            this.println(value);
            return null;
        }
        this.println("Simulation of Call Function is NOT SUPPORTED !!!");
        return null;
    }
    
    // EleExprAST
    // Bieu thuc truy xuat mang: array[...][...][...]
    @Override
    public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.println("Simulation of Array manipulation is NOT SUPPORTED !!!");
        // String varName = ast.name.getText();
        ast.e.visit(this, o);
        return null;
    }
    
    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.e.visit(this, o);
        this.execHistory.addExecNode(new ExecNode(ast.line, 'N'));
        
        return null;
    }
    
    // IfThenStmtAST
    @Override
    public Object visitIfThenStmtAST(IfThenStmtAST iAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
//        boolean value = ((Boolean) iAst.e.visit(this, o)).booleanValue();
        boolean value = true;
        for (int i = 0; i < path.size(); i++)
            if (path.get(i).equals(iAst.e))
            {
                value = branch.get(i) == 0;
                break;
            }
        
        if (value) {
            this.execHistory.addExecNode(new ExecNode(iAst.line, 'T'));
            this.simulation.enterScope();
            iAst.s.visit(this, o);
            this.simulation.exitScope();
        }
        else {
            this.execHistory.addExecNode(new ExecNode(iAst.line, 'F'));
        }
        return null;
    }
    
    // IfThenElseStmtAST
    @Override
    public Object visitIfThenElseStmtAST(IfThenElseStmtAST iAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
//        boolean value = ((Boolean) iAst.e.visit(this, o)).booleanValue();
        boolean value = true;
        for (int i = 0; i < path.size(); i++)
            if (path.get(i).equals(iAst.e))
            {
                value = branch.get(i) == 0;
                break;
            }
        if (value) {
            this.execHistory.addExecNode(new ExecNode(iAst.line, 'T'));
            this.simulation.enterScope();
            iAst.s1.visit(this, o);
            this.simulation.exitScope();
        }
        else {
            this.execHistory.addExecNode(new ExecNode(iAst.line, 'F'));
            this.simulation.enterScope();
            iAst.s2.visit(this, o);
            this.simulation.exitScope();
        }
        return null;
    }
    
    // ForStmtAST
    // for (int i = 1; i < 10; i++) print(x)
    // <==> int i = 1; while (i < 10) {print(x); i++}
    @Override
    public Object visitForStmtAST(ForStmtAST fAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
    	/*System.out.println("Here");
        this.simulation.enterScope();
        if (fAst.e1 != null) {
            fAst.e1.visit(this, o);
        }
        boolean value = true;
        if (fAst.e2 != null) {
            value = ((Boolean) fAst.e2.visit(this, o)).booleanValue();
        }
        if (value) {
            this.execHistory.addExecNode(new ExecNode(fAst.line, 'T'));
        }
        else {
            this.execHistory.addExecNode(new ExecNode(fAst.line, 'F'));
        }
        while (value) {
            this.simulation.enterScope();
            fAst.o.visit(this, o);
            this.simulation.exitScope();
            if (fAst.e3 != null) {
                fAst.e3.visit(this, o);
            }
            if (fAst.e2 != null) {
                value = ((Boolean) fAst.e2.visit(this, o)).booleanValue();
            }
            if (value) {
                this.execHistory.addExecNode(new ExecNode(fAst.line, 'T'));
            }
            else {
                this.execHistory.addExecNode(new ExecNode(fAst.line, 'F'));
            }
        }
        this.simulation.exitScope();*/
        return null;
    }
    
    // ForInitAST
    @Override
    public Object visitForInitAST(ForInitAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        if (ast.d != null) {
            // for (int i = 1 ; ...)
            ast.d.visit(this, o);
        }
        if (ast.e != null) {
            // for (i = 1, j = 2, k = 3 ; ...)
            return ast.e.visit(this, o);
        }
        return null;
    }
    
    // WhileStmtAST
    @Override
    public Object visitWhileStmtAST(WhileStmtAST wAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
//    	int count = 0;
//        boolean value = ((Boolean) wAst.e.visit(this, o)).booleanValue();
//        while (value) {
//        	count++;
        	this.execHistory.addExecNode(new ExecNode(wAst.line, 'T'));
            this.simulation.enterScope();
            wAst.o.visit(this, o);
//            value = ((Boolean) wAst.e.visit(this, o)).booleanValue();
            this.simulation.exitScope();
//        }
//        if(count == 0)
//        	this.execHistory.addExecNode(new ExecNode(wAst.line, 'F'));
        return null;
    }
    
    // DoStmtAST
    @Override
    public Object visitDoStmtAST(DoStmtAST wAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        boolean value;
        do {
            this.simulation.enterScope();
            wAst.o.visit(this, o);
            this.simulation.exitScope();
            
            value = ((Boolean) wAst.e.visit(this, o)).booleanValue();
            if (value) {
                this.execHistory.addExecNode(new ExecNode(wAst.line, 'T'));
            }
            else {
                this.execHistory.addExecNode(new ExecNode(wAst.line, 'F'));
            }
        }
        while (value);
        return null;
    }
    
    // RetStmtAST
    @Override
    public Object visitRetStmtAST(RetStmtAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        if (ast.e != null) {
            this.execHistory.addExecNode(new ExecNode(ast.line, 'N'));
//            Object value = ast.e.visit(this, "get_pointer_value");
            ExprTreeNode value = (ExprTreeNode) ast.e.visit(this, "get_pointer_value");
            // Object value = ast.e.visit(this, o);
//            this.simulation.updateValueOfVariable(RET_VAR_NAME, value);
            this.simulation.updateExprTreeOfVariable(RET_VAR_NAME, value);
            retExprTree = value;
            isReturned = true;
        }
        return null;
    }
    
    // /////////////////////////////////////////////////////////////////////////////////
    // chua xu ly cac cau lenh ben duoi
    // BreakStmtAST
    @Override
    public Object visitBreakStmtAST(BreakStmtAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.println("BreakStmtAST: " + ast.line);
        return null;
    }
    
    // ContStmtAST
    @Override
    public Object visitContStmtAST(ContStmtAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.println("ContStmtAST: " + ast.line);
        return null;
    }
    
    // SwitchStmtAST
    @Override
    public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.println("SwitchStmtAST: " + sAst.line);
        sAst.e.visit(this, o);
        sAst.o.visit(this, o);
        return null;
    }
    
    // CaseStmtAST
    @Override
    public Object visitCaseStmtAST(CaseStmtAST cAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.println("CaseStmtAST: " + cAst.line);
        cAst.e.visit(this, o);
        cAst.s.visit(this, o);
        return null;
    }
    
    // DefaultStmtAST
    @Override
    public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o) throws CompilationException
    {
        if (isReturned) return null;
        this.println("DefaultStmtAST: " + dAst.line);
        dAst.s.visit(this, o);
        return null;
    }
    
    //
    // /////////////////////////////////////////////////////////////////////////////////
    
    // /////////////////////////////////////////////////////////////////////////////////
    // chua can xet cac ham ben duoi
    // TernaryExprAST
    @Override
    public Object visitTernaryExprAST(TernaryExprAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.e1.visit(this, o);
        ast.e2.visit(this, o);
        ast.e3.visit(this, o);
        return null;
    }
    
    // TypeListAST
    @Override
    public Object visitTypeListAST(TypeListAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.t.visit(this, o);
        ast.tl.visit(this, o);
        return null;
    }
    
    // ArrayTypeAST
    @Override
    public Object visitArrayTypeAST(ArrayTypeAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.type.visit(this, o);
        ast.el.visit(this, o);
        return null;
    }
    
    // PointerTypeAST
    @Override
    public Object visitPointerTypeAST(PointerTypeAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.t.visit(this, o);
        return null;
    }
    
    // ArrayInitializer
    @Override
    public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.v.visit(this, o);
        return null;
    }
    
    // VarInitializerList
    @Override
    public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.v.visit(this, o);
        ast.vl.visit(this, o);
        return null;
    }
    
    // ArrayInitializerList
    @Override
    public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        ast.a.visit(this, o);
        ast.al.visit(this, o);
        return null;
    }
    
    // BoolLiteralAST
    @Override
    public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o) throws CompilationException
    {
        if (isReturned) return null;
        return null;
    }
    
}
