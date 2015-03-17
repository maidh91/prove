package Transformer.CounterExample;

public class Variable {
	
	private String variableName;
	
	private String variableValue;

	/**
	 * 
	 */
	public Variable() {
		super();
		// TODO Auto-generated constructor stub
		variableName = "";
		variableValue = "";
	}

	/**
	 * @param variableName
	 * @param variableValue
	 */
	public Variable(String variableName, String variableValue) {
		super();
		this.variableName = variableName;
		this.variableValue = variableValue;
	}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

	/**
	 * @param variableName the variableName to set
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	/**
	 * @return the variableValue
	 */
	public String getVariableValue() {
		return variableValue;
	}

	/**
	 * @param variableValue the variableValue to set
	 */
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}
	
	

}
