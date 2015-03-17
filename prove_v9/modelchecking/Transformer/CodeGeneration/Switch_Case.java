package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class Switch_Case {
	public int lbOut;
	public int[] lb_case;
	public int[] value_case;
	public String[] str_case;
	public int current_case;
	public int lb_default;
	//public String str_default;
	public boolean has_break;
	//public boolean has_default;	
	public int pos_default;
	
	public String newline() {
		String newline_str = "\r\n";
		return newline_str;
	}
	
	public Switch_Case() {
		lbOut = current_case = lb_default = -1; pos_default = -1;
		has_break = false; //has_default = false;
		lb_case = new int[50];
		value_case = new int[50];
		str_case = new String[50];
		for (int i = 0; i < 50; i++) {
			str_case[i] = "";
			lb_case[i] = 0;
			value_case[i] = 0;
		}
		//str_default = "";
	}
	public void setValLabel(int val, int label) {
		current_case++;
		value_case[current_case] = val;
		lb_case[current_case] = label;
	}
	public void setStrCase(String str) {
		//System.out.println("Testing: " + str);
		if (current_case != -1) {						
			str_case[current_case] = new String(str);
			if (has_break)
				str_case[current_case] += "goto lb" + lbOut + ";"+newline();
				has_break = false;
		}
	}
	public void setValLabelOfDefault(int label) {
		current_case++;
		lb_case[current_case] = label;
		lb_default = label;
		pos_default = current_case;
		//has_default = true;
	}
	public void print() {
		for (int i=0; i<=current_case; i++) {
			System.out.println("Case thu " + i);
			System.out.println("Label " + lb_case[i]);
			System.out.println("Value " + value_case[i]);
			System.out.println("Content\n\t" + str_case[i]);
		}
		System.out.println("Default");
		System.out.println("Label " + lb_default);
		//System.out.println("Content\n\t" + str_default);
	}
	public void setBreak() {
		has_break = true;
	}
}