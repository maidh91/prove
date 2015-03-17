package transform.DependenceGraph;

public class ExecNode
{
    public Node node;
    public int lineID;
    public char state;
    
    public ExecNode(Node node, char state)
    {
        this.node = node;
        this.state = state;
    }
    
    public ExecNode(int line, char state)
    {
        this.lineID = line;
        this.state = state;
        this.node = null;
    }
    
    int indexInExecutionHistory = -1;
    String newline = "\r\n";
    
    @Override
    public String toString()
    {
        String result = "";
        if (this.state == 'T') {
            result += "State = Condition_True";
        }
        else if (this.state == 'F') {
            result += "State = Condition_False";
        }
        else if (this.state == 'N') {
            result += "State = Assign_Stmt";
        }
        result += this.newline;
        if (this.node != null) {
            result += this.node.toString();
        }
        else {
            result += "LineID = " + this.lineID;
        }
        return result;
    }
    
    public Node getNode()
    {
        return this.node;
    }
    
    public char getState()
    {
        return this.state;
    }
    
    public boolean equals(ExecNode en)
    {
        if ((this.lineID != en.lineID) || (this.state != en.state) || !this.node.equals(en.node)) {
            return false;
        }
        else {
            return true;
        }
    }
    
}
