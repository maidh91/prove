package transform.DependenceGraph;

public class CondDep
{
    Node dependNode;
    boolean reached_branch;
    String var_depend;
    
    public CondDep(Node tmConDepNode, Boolean branch, String new_var_depend)
    {
        this.dependNode = tmConDepNode;
        this.reached_branch = branch;
        this.var_depend = new_var_depend;
    }
    
    public Node getDependNode()
    {
        return this.dependNode;
    }
    
    public boolean getBranch()
    {
        return this.reached_branch;
    }
}
