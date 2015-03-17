package system;

import java.util.ArrayList;

import transform.AST.*;

public class Condition 
{
	int stmtID;
	boolean hastc;
	AST ast;
	String condition;
	ArrayList<String> truepath;
	ArrayList<String> falsepath;
	ArrayList<Integer> truecon;
	ArrayList<Integer> falsecon;
	String truetc;
	public boolean hastruetc;
	String falsetc;
	public boolean hasfalsetc;
	
	public Condition()
	{
		stmtID = 0;
		ast = null;
		truepath = new ArrayList<String>();
		falsepath = new ArrayList<String>();
		truecon = new ArrayList<Integer>();
		falsecon = new ArrayList<Integer>();
		truetc = "";
		falsetc = "";
	}
	
	public ArrayList<Integer> getTruecon() {
		return truecon;
	}
	
	public boolean isHastc() {
		return hastc;
	}

	public void setHastc(boolean hastc) {
		this.hastc = hastc;
	}

	public void setTruecon(ArrayList<Integer> truecon) {
		this.truecon = truecon;
	}

	public ArrayList<Integer> getFalsecon() {
		return falsecon;
	}

	public void setFalsecon(ArrayList<Integer> falsecon) {
		this.falsecon = falsecon;
	}

	public AST getAst() {
		return ast;
	}

	public void setAst(AST ast) {
		this.ast = ast;
	}

	public int getStmtID() {
		return stmtID;
	}

	public void setStmtID(int stmtID) {
		this.stmtID = stmtID;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ArrayList<String> getTruepath() {
		return truepath;
	}

	public void setTruepath(ArrayList<String> truepath) {
		this.truepath = truepath;
	}

	public ArrayList<String> getFalsepath() {
		return falsepath;
	}

	public void setFalsepath(ArrayList<String> falsepath) {
		this.falsepath = falsepath;
	}

	public String getTruetc() {
		return truetc;
	}

	public void setTruetc(String truetc) {
		this.truetc = truetc;
	}

	public String getFalsetc() {
		return falsetc;
	}

	public void setFalsetc(String falsetc) {
		this.falsetc = falsetc;
	}
}
