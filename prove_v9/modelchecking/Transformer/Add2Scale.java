package Transformer;

import java.io.*;
public class Add2Scale {
	private static String inFile;
	private static String outFile;
	public static void main(String[] args) {
		inFile = args[0];
		outFile = args[1];
		String line;
		try{
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(outFile));
			BufferedReader bReader = new BufferedReader(new FileReader(inFile));
			bWriter.write("true\n");
			while((line = bReader.readLine()) != null){
				bWriter.write(line+"\n");
			}
			bWriter.close();
			bReader.close();
		}
		catch(IOException e){
			System.out.println("File Error!");
		}
	}
}

