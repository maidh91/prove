package Transformer;

import java.io.*;
public class CreateScale {
	private static String fileName;
	public static void main(String[] args) {
		fileName = args[0];
		try{
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName));
			bWriter.write("false\n");
			bWriter.write("0\n");
			bWriter.write("0\n");
			bWriter.close();
		}
		catch(IOException e){
			System.out.println("File Error!");
		}
	}
}

