/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transform.CodeGeneration;

import java.util.ArrayList;
import transform.AST.BinExprAST;

/**
 *
 * @author ZoZo
 */
public class ExprTreeNode
{
    public int opType;
    public ArrayList<ExprTreeNode> childs;
    public Object value;
    public String paraName;
    public int paraIndex;
    
    public ExprTreeNode()
    {
        this.opType = BinExprAST.UNDEFINED;
        this.childs = null;
        this.value = null;
        this.paraName = null;
        this.paraIndex = -1;
    }
    
    public ExprTreeNode(int opType, ArrayList<ExprTreeNode> childs, Object value)
    {
        this.opType = opType;
        this.childs = childs;
        this.value = value;
        this.paraName = null;
        this.paraIndex = -1;
    }
    
    public ExprTreeNode(int opType, ArrayList<ExprTreeNode> childs, Object value, String paraName, int paraIndex)
    {
        this.opType = opType;
        this.childs = childs;
        this.value = value;
        this.paraName = paraName;
        this.paraIndex = paraIndex;
    }

    @Override
    public String toString() {
        String s = "";
        if (false) {}
        else if (paraName != null)
        {
            s = paraName;
        }
        else if (opType == BinExprAST.INT_LITTERAL)
        {
            s = ((Integer)value) + "";
        }
        else if (opType == BinExprAST.FLOAT_LITTERAL)
        {
            s = ((Float)value) + "";
        }
        else if (opType == BinExprAST.CHAR_LITTERAL)
        {
            s = ((String)value);
        }
        else if (opType == BinExprAST.STRING_LITTERAL)
        {
            s = ((String)value) + "";
        }
        else if (opType == BinExprAST.BOOL_LITTERAL)
        {
            s = ((Boolean)value) + "";
        }
        else if (opType == BinExprAST.LOGICAL_OR)
        {
            s = "(" + childs.get(0).toString() + "||" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.LOGICAL_AND)
        {
            s = "(" + childs.get(0).toString() + "&&" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.EQUAL)
        {
            s = "(" + childs.get(0).toString() + "==" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.NOT_EQUAL)
        {
            s = "(" + childs.get(0).toString() + "!=" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.LESS_OR_EQUAL)
        {
            s = "(" + childs.get(0).toString() + "<=" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.GREATER_OR_EQUAL)
        {
            s = "(" + childs.get(0).toString() + ">=" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.LESS_THAN)
        {
            s = "(" + childs.get(0).toString() + "<" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.GREATER_THAN)
        {
            s = "(" + childs.get(0).toString() + ">" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.PLUS)
        {
            s = "(" + childs.get(0).toString() + "+" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.MINUS)
        {
            s = "(" + childs.get(0).toString() + "-" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.STAR)
        {
            s = "(" + childs.get(0).toString() + "*" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.DIV)
        {
            s = "(" + childs.get(0).toString() + "/" + childs.get(1).toString() + ")";
        }
        else if (opType == BinExprAST.MOD)
        {
            s = "(" + childs.get(0).toString() + "%" + childs.get(1).toString() + ")";
        }
        return s;
    }
    
    public Object evaluate(ArrayList<String> params)
    {
        if (false) {}
        else if (opType == BinExprAST.INT_LITTERAL)
        {
            if (paraName == null) return ((Integer)value);
            else return Integer.parseInt(params.get(paraIndex));
        }
        else if (opType == BinExprAST.FLOAT_LITTERAL)
        {
            if (paraName == null) return ((Float)value);
            else return Float.parseFloat(params.get(paraIndex));
        }
        else if (opType == BinExprAST.CHAR_LITTERAL)
        {
            if (paraName == null) return ((String)value);
            else return params.get(paraIndex);
        }
        else if (opType == BinExprAST.STRING_LITTERAL)
        {
            if (paraName == null) return ((String)value);
            else return params.get(paraIndex);
        }
        else if (opType == BinExprAST.BOOL_LITTERAL)
        {
            if (paraName == null) return ((Boolean)value);
            else return Boolean.parseBoolean(params.get(paraIndex));
        }
        else if (opType == BinExprAST.LOGICAL_OR)
        {
            return (Boolean)childs.get(0).evaluate(params) || (Boolean)childs.get(1).evaluate(params);
        }
        else if (opType == BinExprAST.LOGICAL_AND)
        {
            return (Boolean)childs.get(0).evaluate(params) && (Boolean)childs.get(1).evaluate(params);
        }
        else if (opType == BinExprAST.EQUAL)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) == (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) == (Float)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.CHAR_LITTERAL || childs.get(0).opType == BinExprAST.STRING_LITTERAL)
            {
                return (String)childs.get(0).evaluate(params) == (String)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.BOOL_LITTERAL)
            {
                return (Boolean)childs.get(0).evaluate(params) == (Boolean)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.NOT_EQUAL)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) != (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) != (Float)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.CHAR_LITTERAL || childs.get(0).opType == BinExprAST.STRING_LITTERAL)
            {
                return (String)childs.get(0).evaluate(params) != (String)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.BOOL_LITTERAL)
            {
                return (Boolean)childs.get(0).evaluate(params) != (Boolean)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.LESS_OR_EQUAL)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) <= (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) <= (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.GREATER_OR_EQUAL)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) >= (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) >= (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.LESS_THAN)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) < (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) < (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.GREATER_THAN)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) > (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) > (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.PLUS)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) + (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) + (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.MINUS)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) - (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) - (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.STAR)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) * (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) * (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.DIV)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) / (Integer)childs.get(1).evaluate(params);
            }
            else if (childs.get(0).opType == BinExprAST.FLOAT_LITTERAL)
            {
                return (Float)childs.get(0).evaluate(params) / (Float)childs.get(1).evaluate(params);
            }
        }
        else if (opType == BinExprAST.MOD)
        {
            if (childs.get(0).opType == BinExprAST.INT_LITTERAL)
            {
                return (Integer)childs.get(0).evaluate(params) % (Integer)childs.get(1).evaluate(params);
            }
        }
        return null;
    }
}
