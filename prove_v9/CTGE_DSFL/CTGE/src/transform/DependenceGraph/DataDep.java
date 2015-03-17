package transform.DependenceGraph;

public class DataDep
{
    int lineID;
    String var_depend = null;
    Node node = null;
    
    String newline = "\r\n";
    String tab = "\t";
    
    public DataDep(Node nodeY, String varName)
    {
        this.node = nodeY;
        this.var_depend = varName;
    }
    
    public DataDep(int id, String var)
    {
        this.lineID = id;
        this.var_depend = var;
    }
    
    public void setVarName(String var)
    {
        this.var_depend = var;
    }
    
    public String getVarName()
    {
        return this.var_depend;
    }
    
    public int getID()
    {
        return this.lineID;
    }
    
    public void setNode(Node n)
    {
        this.node = n;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        if (this.node != null) {
            result =
                     "{Node " + this.node.getIndex() + " (lineID=" + this.node.getID() + "),     "
                             + this.var_depend + "}";
        }
        else {
            result = "{Node with lineID = " + this.lineID + ", " + this.var_depend + "}";
        }
        return result;
    }
    
    public Node getNode()
    {
        return this.node;
    }
}
