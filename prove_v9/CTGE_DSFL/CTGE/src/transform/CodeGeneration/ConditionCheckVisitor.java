package transform.CodeGeneration;

import java.util.ArrayList;

import system.Parameter;
import system.Variable;
import transform.AST.*;

public class ConditionCheckVisitor extends DoNothingVisitor {

    ArrayList<Variable> listVar;
    ArrayList<Parameter> listPara;
    ArrayList<Double> para;
    ArrayList<Double> var;
    ArrayList<String> tc;

    public ConditionCheckVisitor(ArrayList<Parameter> listPara, ArrayList<Variable> listVar, ArrayList<String> testcase) {
        this.listVar = listVar;
        this.listPara = listPara;
        this.para = new ArrayList<Double>();
        this.tc = testcase;
        for (int i = 0; i < testcase.size(); i++) {
            System.out.println(testcase.get(i));
            para.add(Double.parseDouble(testcase.get(i)));
        }
        this.var = new ArrayList<Double>();
        for (int i = 0; i < listVar.size(); i++) {
            var.add((double) 0);
        }

    }

    public void clear() {
        var.clear();
        para.clear();
        for (int i = 0; i < tc.size(); i++) {
            para.add(Double.parseDouble(tc.get(i)));
        }
        for (int i = 0; i < listVar.size(); i++) {
            var.add((double) 0);
        }
    }

    //ProgramAST
    @Override
    public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException {
        return (double) 0;
    }

    // ExprStmtAST
    @Override
    public Object visitExprStmtAST(ExprStmtAST ast, Object o) throws CompilationException {
        if (!(ast.e instanceof TernaryExprAST)) {
            // ast.line = this.line;
            // this.em.setFilter(true);
        }
        ast.e.visit(this, o);
        return (double) 0;
    }

    @Override
    public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o) throws CompilationException {
        ast.dl.visit(this, o);
        return (double) 0;
    }

    @Override
    public Object visitDeclarationListAST(DeclarationListAST ast, Object o) throws CompilationException {
        String var;
        Object value;
        var = (String) ast.d.visit(this, "c");
        if (ast.dl instanceof EmptyDeclarationListAST) {
            return (double) 0;
        } else {
            value = (Object) ast.dl.visit(this, o);
            int i = this.findVar(var);
            if (i >= 0) {
                this.var.set(i, (Double) value);
            } else {
                i = this.findPara(var);
                value = (Object) ast.dl.visit(this, o);
                this.para.set(i, (Double) value);
            }
        }
        return (double) 0;
        //return var + " = " + value;
    }

    @Override
    public Object visitVarDeclAST(VarDeclAST ast, Object o) throws CompilationException {
        Double value;
        int i = this.findVar(ast.id.getText());
        if (i >= 0) {
            value = (Double) ast.init.visit(this, o);
            this.var.set(i, value);
        } else {
            i = this.findPara(ast.id.toString());
            if (i >= 0) {
                value = (Double) ast.init.visit(this, o);
                this.para.set(i, value);
            }
        }
        if (o == "c") {
            return ast.id.getText();
        }
        return (double) 0;
        //return null;
    }

    @Override
    public Object visitVarInitializerAST(VarInitializerAST ast, Object o) throws CompilationException {
        return (double) ast.e.visit(this, o);
    }

    @Override
    public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException {
        Double value = (double) 0;
        switch (ast.name.getText()) {
            case "cos":
                value = Math.cos((Double) ast.e.visit(this, o));
                break;
            case "sin":
                value = Math.sin((Double) ast.e.visit(this, o));
                break;
            case "tan":
                value = Math.tan((Double) ast.e.visit(this, o));
                break;
            case "exp":
                value = Math.exp((Double) ast.e.visit(this, o));
                break;
            case "log":
                value = Math.log((Double) ast.e.visit(this, o));
                break;
            case "pow":
                value = Math.pow((Double) ast.e.visit(this, o), (Double) ast.e.l.visit(this, o));
                break;
            case "sqrt":
                value = Math.sqrt((Double) ast.e.visit(this, o));
                break;
            case "abs":
                value = Math.abs((Double) ast.e.visit(this, o));
                break;
        }
        return (double) value;
    }

    @Override
    public Object visitExprListAST(ExprListAST ast, Object o) throws CompilationException {
        Object value = ast.e.visit(this, o);
        if (value instanceof Double) {
            return (Double) value;
        }
        return (double) 0;
    }

    @Override
    public Object visitEmptyExprListAST(EmptyExprListAST ast, Object o) throws CompilationException {
        return (double) 0;
    }

    @Override
    public Object visitIntLiteralAST(IntLiteralAST ast, Object o) throws CompilationException {
        return (double) Integer.parseInt(ast.literal.getText());
    }

    @Override
    public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o) throws CompilationException {
        return Double.parseDouble(ast.literal.getText());
    }

    // BinExprAST
    @Override
    public Object visitBinExprAST(BinExprAST ast, Object o) throws CompilationException {
        if ((ast.opType == BinExprAST.ASSIGN)) { // && (ast.parent instanceof ExprStmtAST)) {

            String var = (String) ast.e1.visit(this, "c");
            Double temp = (double) 0;
            int i = this.findVar(var);
            if (i >= 0) {
                temp = (Double) ast.e2.visit(this, o);
                this.var.set(i, temp);
            } else {
                i = this.findPara(var);
                if (i >= 0) {
                    temp = (Double) ast.e2.visit(this, o);
                    this.para.set(i, temp);
                }
            }
            return -1;
        } else {
            Double val1 = (Double) ast.e1.visit(this, 0);
            Double val2 = (Double) ast.e2.visit(this, 0);
            Double value = (double) 0;
            switch (ast.opType) {
                case BinExprAST.PLUS:
                    value = (Double) val1 + (Double) val2;
                    break;
                case BinExprAST.MINUS:
                    value = (Double) val1 - (Double) val2;
                    break;
                case BinExprAST.STAR:
                    value = (Double) val1 * (Double) val2;
                    break;
                case BinExprAST.DIV:
                    if ((Double) val2 != 0) {
                        value = (Double) val1 / (Double) val2;
                    } else {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.MOD:
                    value = (Double) val1 % (Double) val2;
                    break;
                case BinExprAST.LOGICAL_AND:
                    if ((Integer) o == 0) {
                        if ((Double) val1 == 0 && (Double) val2 == 0) {
                            value = (double) 0;
                        } else {
                            value = (Double) val1 + (Double) val2;
                        }
                    } else {
                        if ((Double) val1 != 0 || (Double) val2 != 0) {
                            value = (double) 0;
                        } else {
                            value = (double) 1;
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.LOGICAL_OR:
                    if ((Integer) o == 0) {
                        if ((Double) val1 == 0 || (Double) val2 == 0) {
                            value = (double) 0;
                        } else {
                            value = Math.min((Double) val1, (Double) val2);
                        }
                    } else {
                        if ((Double) val1 != 0 && (Double) val2 != 0) {
                            value = (double) 0;
                        } else {
                            value = (Double) val1 + (Double) val2;
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.NOT_EQUAL:
                    if ((Integer) o == 0) {
                        if ((Double) val1 != (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = (double) 1;
                        }
                    } else {
                        if (((Double) val1 == (Double) val2)) {
                            value = (double) 0;
                        } else {
                            value = Math.abs((Double) val1 - (Double) val2);
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.EQUAL:
                    if ((Integer) o == 0) {
                        if ((Double) val1 == (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = Math.abs((Double) val1 - (Double) val2);
                        }
                    } else {
                        if ((Double) val1 != (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = (double) 1;
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.LESS_OR_EQUAL:
                    if ((Integer) o == 0) {
                        if ((Double) val1 <= (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = Math.abs((Double) val1 - (Double) val2);
                        }
                    } else {
                        if ((Double) val1 > (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = 1 + Math.abs((Double) val1 - (Double) val2);
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.GREATER_OR_EQUAL:
                    if ((Integer) o == 0) {
                        if ((Double) val1 >= (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = Math.abs((Double) val1 - (Double) val2);
                        }
	        			//if(value == 0)
                        //System.out.prDoubleln(">="+val1 + " "+ val2);
                    } else {
                        if ((Double) val1 < (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = 1 + Math.abs((Double) val1 - (Double) val2);
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.LESS_THAN:
                    if ((Integer) o == 0) {
                        if ((Double) val1 < (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = 1 + Math.abs((Double) val1 - (Double) val2);
                        }
                    } else {
                        if ((Double) val1 >= (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = Math.abs((Double) val1 - (Double) val2);
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
                case BinExprAST.GREATER_THAN:
                    if ((Integer) o == 0) {
                        if ((Double) val1 > (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = 1 + Math.abs((Double) val1 - (Double) val2);
                        }
                    } else {
                        if ((Double) val1 <= (Double) val2) {
                            value = (double) 0;
                        } else {
                            value = Math.abs((Double) val1 - (Double) val2);
                        }
                    }
                    if ((Double) value > 100) {
                        value = (double) 100;
                    }
                    break;
            }
            return value;
        }
    }

    @Override
    public Object visitUnaryExprAST(UnaryExprAST ast, Object o) throws CompilationException {
        Double value = (double) 0;
        if (ast.opType == UnaryExprAST.LOGICAL_NOT) {
            if ((Double) o == 0) {
                value = (Double) ast.e.visit(this, null);
            } else {
                if ((Double) ast.e.visit(this, null) == 0) {
                    value = (double) 1;
                } else {
                    value = (double) 0;
                }
            }
        }
        return value;
    }

    // VarExprAST
    @Override
    public Object visitVarExprAST(VarExprAST ast, Object o) throws CompilationException {
        double val = 0;
        int i = this.findVar(ast.name.getText());
        if (i >= 0) {
            val = (Double) this.var.get(i);
        } else {
            i = this.findPara(ast.name.getText());
            if (i >= 0) {
                val = (Double) this.para.get(i);
            }
        }
        if (o == "c") {
            return ast.name.getText();
        }
        return val;
    }

    // get the index of the varName in varReindex
    private int findVar(String varName) {
        if (this.listVar.size() <= 0) {
            return -1;
        } else {
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
        } else {
            for (int i = 0; i < this.listPara.size(); i++) {
                if (paraName.equals(this.listPara.get(i).getName())) {
                    return i;
                }
            }
            return -1;
        }
    }

}
