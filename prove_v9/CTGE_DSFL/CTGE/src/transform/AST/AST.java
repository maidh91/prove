package transform.AST;

import java.util.ArrayList;
import java.util.HashMap;
import transform.CodeGeneration.Visitor;

public abstract class AST
        implements Cloneable
{
    public AST parent;
    public int line;
    public ArrayList<Integer> path;
    public ArrayList<Integer> posInPath;
    public HashMap<Object, ArrayList<AST>> output;
    
    public AST()
    {
        output = new HashMap<>();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    abstract public Object visit(Visitor a, Object o) throws CompilationException;
}
