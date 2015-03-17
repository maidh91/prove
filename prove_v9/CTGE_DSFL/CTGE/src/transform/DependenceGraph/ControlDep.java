package transform.DependenceGraph;

public class ControlDep
{
    Node node = null;
    boolean branch;
    
    public ControlDep()
    {
        this.node = null;
    }
    
    public ControlDep(Node n, boolean b)
    {
        this.node = n;
        this.branch = b;
    }
    
    public ControlDep(ControlDep controlDep)
    {
        this.node = controlDep.node;
        this.branch = controlDep.branch;
    }
    
    public boolean isEmpty()
    {
        return this.node == null;
    }
    
    public void setNode(Node n)
    {
        this.node = n;
    }
    
    public void setBranch(boolean b)
    {
        this.branch = b;
    }
    
    public void set(Node n, boolean b)
    {
        this.node = n;
        this.branch = b;
    }
    
    public void set(ControlDep that)
    {
        this.node = that.node;
        this.branch = that.branch;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        result = "{Node " + this.node.getIndex() + " (lineID=" + this.node.getID() + "),     " + this.branch + "}";
        return result;
    }
    
    public Node getNode()
    {
        return this.node;
    }
    
    public boolean getBranch()
    {
    	return this.branch;
    }
}
