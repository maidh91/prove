package transform.CodeGeneration;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Emitter
{
    private final String prefixVarName = "m";
    private String switch_str = "";
    private boolean case_on = false;
    private String output = "";
    
    private boolean no_print = false;
    public boolean print_to_console = false;
    public boolean store_var = false;
    private boolean filter = false;
    private boolean no_print_in_mode_filter = false;
    
    private String filename = "";
    private String filterStr = "";
    
    private int[] scopeVariable;
    private String[] nameVariable;
    private int currentIndex = 0;
    
    public Emitter()
    {
        this.scopeVariable = new int[50];
        this.nameVariable = new String[50];
    }
    
    public void printout(String in)
    {
        if (this.no_print) {
            return;
        }
        
        if (!this.case_on) {
            if (this.print_to_console) {
                //System.out.print(in);
            }
            if (this.filter) {
                this.filterStr += in;
            }
            if (!this.no_print_in_mode_filter) {
                this.output += in;
            }
        }
        else {
            this.switch_str += in;
        }
    }
    
    public void setCaseOn(boolean on)
    {
        this.case_on = on;
    }
    
    public String setFilter(boolean on)
    {
        String result = null;
        this.filter = on;
        if (on == false) {
            result = this.filterStr;
            this.filterStr = "";
        }
        return result;
    }
    
    public String setFilter(boolean on, boolean no_print)
    {
        this.no_print_in_mode_filter = no_print;
        String result = null;
        this.filter = on;
        if (on == false) {
            result = this.filterStr;
            this.filterStr = "";
        }
        return result;
    }
    
    public void setNoPrint(boolean on)
    {
        this.no_print = on;
    }
    
    public void setFilename(String name)
    {
        this.filename = name;
    }
    
    public String getSwitch()
    {
        String returnStr = new String(this.switch_str);
        this.switch_str = "";
        return returnStr;
    }
    
    public String emitVAR(String varName, int scope)
    {
        if (this.store_var) {
            // storing name of variable and its scope to stack
            this.nameVariable[this.currentIndex] = new String(varName);
            this.scopeVariable[this.currentIndex] = scope;
            this.currentIndex++;
            return this.prefixVarName + scope + "_" + varName;
        }
        else {
            int correct_scope = this.checkScope(varName);
            if (scope == -1) {
                return this.prefixVarName + "_" + varName;
            }
            else {
                return this.prefixVarName + correct_scope + "_" + varName;
            }
        }
    }
    
    public int checkScope(String varName)
    {
        int i, new_scope = -1;
        for (i = this.currentIndex - 1; i >= 0; i--) {
            if (this.nameVariable[i].equals(varName)) {
                new_scope = this.scopeVariable[i];
                break;
            }
        }
        return new_scope;
    }
    
    public void deleteScope(int scope)
    {
        int i = this.currentIndex - 1;
        while (i >= 0) {
            if (this.scopeVariable[i] != scope) {
                break;
            }
            i--;
        }
        this.currentIndex = i + 1;
    }
    
    public void printToPromela()
    {
        if (this.filename.equals("")) {
            return;
        }
        try {
            FileWriter fstream = new FileWriter(this.filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.output);
            out.close();
        }
        catch (Exception e) {
        }
    }
    
    public void printToC()
    {
        if (this.filename.equals("")) {
            return;
        }
        try {
            FileWriter fstream = new FileWriter(this.filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.output);
            out.close();
        }
        catch (Exception e) {
        }
    }
    
    public void printToC(boolean isAppended)
    {
        if (this.filename.equals("")) {
            return;
        }
        try {
            FileWriter fstream = new FileWriter(this.filename, isAppended);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.output);
            out.close();
        }
        catch (Exception e) {
        }
    }
    
    public String toString()
    {
    	return this.output;
    }
    
    public void reset()
    {
    	this.output = "";
    }
}
