package transform.DependenceGraph;

import java.util.ArrayList;

public class CondEdge
{
    Node node;
    ArrayList<CondDep> listDepNode;
    
    public CondEdge(Node runNode)
    {
        this.node = runNode;
        this.listDepNode = new ArrayList<CondDep>();
    }
    
    public void addCondDepNode(CondDep condDep)
    {
        if (condDep != null) {
            if (this.listDepNode.contains(condDep)) {
            }
            else {
                if (this.listDepNode == null) {
                    this.listDepNode = new ArrayList<CondDep>();
                    this.listDepNode.add(condDep);
                }
                else {
                    this.listDepNode.ensureCapacity(1);
                    this.listDepNode.add(condDep);
                }
            }
        }
    }
    
    public Node getNode()
    {
        return this.node;
    }
    
    public ArrayList<CondDep> getListDepNode()
    {
        return this.listDepNode;
    }
}
