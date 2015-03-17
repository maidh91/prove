package Transformer.CodeGeneration;

public class SymEntry {	
	/*
	private Token id;
	private Type type;
	private int index;
	*/
	private String value;//newly added property using to store the literal
	
	public SymEntry(String str) {
		value = str;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String str) {
		value = str;
	}
	
	/*
	public SymEntry(Token id,int index) { 
		this.id = id;
		type = null;
		value = null;
		this.index = index;
	}
	
	public SymEntry(Token id,Type type) { 
		this.id = id;
		this.type = type;
		value = null;
		index = -1;
	}
	public SymEntry(Token id,Type type,String v) { 
		this.id = id;
		this.type = type;
		value = v;
		index = -1;
	}

	public String getLexeme() {
		return id.Lexeme;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type t) {
		type = t;
	}
	
	public Token getId() {
		return id;
	}
	public String toString() {
		return id.Lexeme + " " + type.toString();
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	*/
}
