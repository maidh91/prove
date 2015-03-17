package transform.DependenceGraph;

import java.util.ArrayList;

import system.VariableUsed;

public class Node
{
    int StatementID;
    TYPE StatementTYPE;
    VariableUsed definedVar = null;
    ArrayList<VariableUsed> usedVars = null;
    ArrayList<DataDep> dataDep = null;
    ControlDep conDep = null;
    ArrayList<Node> potDep = null;
    
    protected int indexInPDG = -1;
    
    /*
     * Constructor
     */
    public Node(int ID, TYPE Type)
    {
        this.StatementID = ID;
        this.StatementTYPE = Type;
        this.definedVar = null;
        this.usedVars = null;
        this.dataDep = null;
        this.conDep = null;
        this.potDep = null;
    }
    
    /**
     * Default Constructor
     */
    public Node(int id, TYPE type, ArrayList<DataDep> data, ControlDep control, ArrayList<Node> pot)
    {
        this.StatementID = id;
        this.StatementTYPE = type;
        this.dataDep = data;
        this.conDep = control;
        this.potDep = pot;
        if (this.dataDep != null) {
            this.usedVars = new ArrayList<VariableUsed>();
            for (int i = 0; i < this.dataDep.size(); i++) {
                String varName = this.dataDep.get(i).getVarName();
                
                if (!this.hasUsedVar(varName)) {
                    VariableUsed var = new VariableUsed(this.dataDep.get(i).getVarName(), -1);
                    this.usedVars.add(var);
                }
            }
        }
    }
    
    /**
     * this method use to check if a var is in the 'usedVars' or not.
     * 
     * @param varName
     * @return
     */
    private boolean hasUsedVar(String varName)
    {
        int size = this.usedVars.size();
        for (int i = 0; i < size; i++) {
            VariableUsed usedVar = this.usedVars.get(i);
            if (usedVar.getName().equals(varName)) {
                return true;
            }
        }
        return false;
    }
    
    public int getID()
    {
        return this.StatementID;
    }
    
    public ArrayList<DataDep> getDataDep()
    {
        return this.dataDep;
    }
    
    public void setIndex(int id)
    {
        this.indexInPDG = id;
    }
    
    public int getIndex()
    {
        return this.indexInPDG;
    }
    
    public void setDefinedVar(VariableUsed var)
    {
        this.definedVar = var;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        result += "StatementID = " + this.StatementID + "\n";
        result += "StatementTYPE = " + this.StatementTYPE + "\n";
        
        if (this.definedVar != null) {
            result += "definedVar = " + this.definedVar + "\n";
        }
        if (this.usedVars != null) {
            result += "usedVars = " + this.usedVars.get(0);
            for (int i = 1; i < this.usedVars.size(); i++) {
                result += ", " + this.usedVars.get(i);
            }
            result += "\n";
        }
        
        // result += "dataDep = ";
        if (this.dataDep != null) {
            result += "dataDep = " + "\n" + this.toString(this.dataDep);
        }
        /*
         * else
         * result += "null" + newline;
         */
        // result += "conDep = ";
        if (this.conDep != null) {
            result += "conDep = " + this.conDep.toString() + "\n";
        }
        /*
         * else
         * result += "null" + newline;
         */
        return result;
    }
    
    public String toString(ArrayList<DataDep> list)
    {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + list.get(i).toString() + "\n";
        }
        return result;
    }
    
    public ArrayList<VariableUsed> getUsedVars()
    {
        return this.usedVars;
    }
    
    public TYPE getStatementTYPE()
    {
        return this.StatementTYPE;
    }
    
    public ControlDep getConDep()
    {
        return this.conDep;
    }
    
    public void addPotDep(Node tmNode)
    {
        if (this.potDep == null) {
            this.potDep = new ArrayList<Node>();
            this.potDep.add(tmNode);
        }
        else {
            if (!this.potDep.contains(tmNode)) {
                this.potDep.ensureCapacity(1);
                this.potDep.add(tmNode);
            }
        }
    }
    
    public VariableUsed getDefinedVar()
    {
        return this.definedVar;
    }
    
    public ArrayList<Node> getPotDepList()
    {
        return this.potDep;
    }
    
    public void addControlDep(Node nodeY, boolean branch)
    {
        this.conDep = new ControlDep(nodeY, branch);
    }
    
    public void addDataDep(Node nodeY, String varName)
    {
        DataDep ddTemp = new DataDep(nodeY, varName);
        if (this.dataDep == null) {
            this.dataDep = new ArrayList<DataDep>();
            this.dataDep.add(ddTemp);
        }
        else {
            this.dataDep.ensureCapacity(1);
            this.dataDep.add(ddTemp);
        }
    }
    
    public boolean equals(Node n)
    {
        if ((this.StatementID != n.StatementID) || (this.StatementTYPE != n.StatementTYPE)) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * find a used var by name
     * 
     * @param name
     * @return
     */
    public VariableUsed findUsedVar(String name)
    {
        if (this.usedVars != null) {
            int size = this.usedVars.size();
            for (int i = 0; i < size; i++) {
                VariableUsed var = this.usedVars.get(i);
                if (var.getName().equals(name)) {
                    return var;
                }
            }
        }
        return null;
    }
}
