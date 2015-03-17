package transform.DependenceGraph;

import java.util.ArrayList;

public class MappingTable
{
    ArrayList<MappingNode> mapTable;
    String newline = "\r\n";
    
    public MappingTable()
    {
        this.mapTable = new ArrayList<MappingNode>();
    }
    
    public void addMappingNode(MappingNode n)
    {
        this.mapTable.add(n);
    }
    
    @Override
    public String toString()
    {
        String result = this.newline + this.newline + "Mapping Tables: " + this.newline + this.newline;
        for (int i = 0; i < this.mapTable.size(); i++) {
            result += this.mapTable.get(i).toString() + this.newline;
        }
        return result;
    }
    
    public MappingNode get(int i)
    {
        return this.mapTable.get(i);
    }
    
    // Find the node that has the statement ID equal to stmtID
    public MappingNode getNode(int stmtID)
    {
        if (this.mapTable != null) {
            MappingNode mapNode = null;
            for (int i = 0; i < this.mapTable.size(); i++) {
                mapNode = this.mapTable.get(i);
                if (mapNode.getStatementID() == stmtID) {
                    return mapNode;
                }
            }
        }
        
        return null;
    }
    
    public int size()
    {
    	return mapTable.size();
    }
}
