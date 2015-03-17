package transform.DependenceGraph;

import transform.AST.OneStmtAST;

public class MappingNode
{
    int StatementID;
    OneStmtAST oneStmt;
    
    public MappingNode(int id, OneStmtAST o)
    {
        this.StatementID = id;
        this.oneStmt = o;
    }
    
    @Override
    public String toString()
    {
        return this.StatementID + ": " + this.oneStmt.getClass();
    }
    
    public OneStmtAST getStatementAST()
    {
        return this.oneStmt;
    }
    
    public int getStatementID()
    {
        return this.StatementID;
    }
    
}
