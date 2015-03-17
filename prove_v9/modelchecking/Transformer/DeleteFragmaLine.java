package Transformer;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class DeleteFragmaLine {
	private static String inFile;
	public static void main(String[] args) {
		inFile = args[0];
		try{
			boolean checkFloat = false;
			BufferedReader bReader = new BufferedReader(new FileReader(inFile));
			String text = "";
			
			String line=null;
			while((line = bReader.readLine()) != null){
				if (!(line.contains("#") && line.contains("pragma") && line.contains("Jessie") && line.contains("Model")))
				{
					text = text + line;
					text = text + "\n";
				}
			}			
			
			bReader.close();
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(inFile));
			System.out.println(text);
			bWriter.write(text);
			bWriter.close();
		}
		catch(IOException e){
			System.out.println("File Error!");
		}
	}
}
