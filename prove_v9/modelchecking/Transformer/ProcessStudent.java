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
		try{
			boolean checkFloat = false;
			BufferedReader bReader = new BufferedReader(new FileReader(inFile));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(inFile));
			
			String line=null;
			while((line = bReader.readLine()) != null){
				if (!(line.contains("#") && line.contains("pragma") && line.contains("Jessie") && line.contains("Model")))
				{
					text += line;
				}
			}			
			bWriter.write(text);
			bWriter.close();
		}
		catch(IOException e){
			System.out.println("File Error!");
		}
	}
}
