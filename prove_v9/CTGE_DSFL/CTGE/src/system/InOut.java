package system;

import java.io.*;


public class InOut 
{
	public InOut(){}
	
	public String readFile(String filename)
	{
		String result = "";
		try {
			FileReader fstream = new FileReader(filename);
			BufferedReader in = new BufferedReader(fstream);
			String temp;
			while((temp = in.readLine())!= null)
			{
				result += temp;
				result += "\n";
			}
			in.close();
		}
		catch (Exception e) {
			System.err.println("Error occur when read file" + filename);
		}
		return result;
	}
	
	public void writeFile(String filename, String content)
	{
		try {
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            out.close();
        }
        catch (Exception e) {
            System.err.println("Error occur when write file " + filename);
            e.printStackTrace();
        }
	}

}
