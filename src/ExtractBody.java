import java.io.*;
import java.util.*;


// This class will take in a filename of parsed file

public class ExtractBody
{
	public static StringTokenizer BreakStringToWord(String str) 
	{

		StringTokenizer token = new StringTokenizer(str);

		return token;
	}
	
	public static StringTokenizer ExtractFromFile(String fname)
	{
		StringTokenizer token = null;
		String Body="";
		
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(fname);

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader inputFile = new BufferedReader(new InputStreamReader(in));
			
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ((line = inputFile.readLine()) != null)
			{
				lines.add(line);
			}
			
			for(int i=0;i<lines.size();i++)
			{
			
				Body = Body+" "+lines.get(i);
			}
			
			
			token = BreakStringToWord(Body);
		} 
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		}
		
		return token;
	}
	
	public static StringTokenizer ExtractFromLine(String line)
	{
		StringTokenizer token = null;
		try {
				
			token = BreakStringToWord(line);
			
			} 
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		}
		
		return token;
	}
}
