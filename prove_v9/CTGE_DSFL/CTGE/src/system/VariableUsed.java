package system;

public class VariableUsed {
	
	private String name;
	private int scope;
	
	public VariableUsed(String name, int scope)
	{
		this.name = name;
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}
	
	@Override
	public String toString()
	{
		String result = "";
		result += "varName: " + this.name + ",";
        result += " varScope: " + this.scope + ";";
        
        return result;
	}
}
