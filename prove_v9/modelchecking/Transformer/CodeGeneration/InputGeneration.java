package Transformer.CodeGeneration;

import java.io.*;
import java.util.*;

public class InputGeneration {
	private LinkedList<Integer> list;
	public final int MAX = 1000;
	public final int MIN = -1000;
	
	public InputGeneration() {
		list = new LinkedList<Integer>();
	}
	public void addConstant(int cons) {
		list.add(new Integer(cons));
	}
	public void printList() {
		int i;
		for (i=0; i<list.size(); i++)
			System.out.println(i + ": "  + list.get(i).intValue());
		System.out.println();
	}
	public void sortList() {
		Collections.sort(list);
	}
	public void printFile(String filename) {
		String output = "";
		int i;
		int index_before = MIN;
		for (i=0; i<=list.size(); i++) {
			if (i == list.size()) {
				output += index_before + " " + MAX + "\r\n";
				break;
			}
			output += index_before + " " + list.get(i).intValue() + "\r\n";
			index_before = list.get(i).intValue();
		}
		try{
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			out.close();
		}catch (Exception e){}
	}
} 