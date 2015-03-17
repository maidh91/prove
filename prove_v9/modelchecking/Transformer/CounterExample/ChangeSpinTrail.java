package Transformer.CounterExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
		
public class ChangeSpinTrail {

	/**
	 * @param args
	 */
	static Map<Integer, String> mapLineNumber2LineStrOfLexer ;
	static Map<Integer, String> mapLineNumber2LineStrOfLParser ;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		String sInputFileName = args[0];
		String sOutputFileName = args[1];
		
		FileReader fr;
		List<String> lstLines = new LinkedList<String>();
		String sResult = ""; 
		try {
	      fr = new FileReader (new File(sInputFileName));
	      BufferedReader br = new BufferedReader (fr);
	      String sLine = br.readLine();
	      boolean bIsVar = false;
	      while (sLine != null) {
	    	  if (sLine.contains("local vars proc") ){
	    		  bIsVar = true;
	    		  sResult += sLine + '\n';
	    		  sLine = br.readLine();
	    		  continue;
	    	  }
	    	  
	    	  if (sLine.contains("chan c") ){
	    		  bIsVar = false;
	    		  sResult += sLine + '\n';
	    		  sLine = br.readLine();
	    		  continue;
	    	  }
	    	  
	    	  if (bIsVar)
	    	  {
	    		  int iLastIndex = sLine.lastIndexOf("_i");
	    		  if (iLastIndex != -1)
	    		  {
	    			  String sPart1 = sLine.substring(0, iLastIndex);
	    			  String sPart2 = sLine.substring(iLastIndex + "_i".length(), sLine.length());
	    			  String sNewLine  = sPart1 + sPart2;
	    			  sResult += sNewLine + '\n';
	    		  }
	    	  }else{
	    		  sResult += sLine + '\n';  
	    	  }
	    	  
	    	  sLine = br.readLine();
	      }
	      br.close();
	   	      
	   
		FileWriter fstream = new FileWriter(sOutputFileName);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(sResult);
		out.close();
	    	
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	   System.out.println("Finish");
	}
	
}
	
	
	
	
