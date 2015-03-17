package Transformer;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class CheckFloat {
	private static String inFile;
	private static String outFile;
	public static void main(String[] args) {
		inFile = args[0];
		outFile = args[1];
		try{
			boolean checkFloat = false;
			BufferedReader bReader = new BufferedReader(new FileReader(inFile));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(outFile));
			
			String line=null;
			while((line = bReader.readLine()) != null){
				checkFloat = (checkFloat|line.matches("float .*"));		//return type is float
				checkFloat = (checkFloat|line.matches(".*\\([\t ]*float .*"));	//arguments is float
				checkFloat = (checkFloat|line.matches(".*[\t ]+float .*"));		//declare float variables
				checkFloat = (checkFloat|line.matches(".*[-+]?[0-9]*\\.[0-9]+([eE][-+]?[0-9]+)?.*"));
				
				if(checkFloat == true) break;
			}			
			if(checkFloat == false) bWriter.write("false");
			else bWriter.write("true");
			bWriter.close();
		}
		catch(IOException e){
			System.out.println("File Error!");
		}
	}
}
