package Transformer.CounterExample;

public class Entry {
	
	private int line;
	
	private String code;

	/**
	 * 
	 */
	public Entry() {
		super();
		// TODO Auto-generated constructor stub
		line = -1;
		code = "";
	}

	/**
	 * @param line
	 * @param code
	 */
	public Entry(int line, String code) {
		super();
		this.line = line;
		this.code = code;
	}

	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
