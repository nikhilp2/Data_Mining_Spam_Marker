import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Rules_with_URL_opener 
{
	public void Rule(String dirName, String SaveName,Non_spamwords non,Spma_Words spa,String Links) 
	{
		FileList fList = new FileList(dirName);
		@SuppressWarnings("unused")
		FileList LinkList = new FileList(Links);
		
	ArrayList<File> FileNames = new ArrayList<File>();
				
		try 
		{
			int autoGenerated=1;
			FileWriter outFile = new FileWriter(SaveName);
			PrintWriter out = new PrintWriter(outFile);
			String strLine="";
			String result="";
			
			FileNames = fList.getFiles();
			
			for(File f :FileNames)
			{
			FileInputStream fstream_ip = new FileInputStream(f.getAbsolutePath());
			DataInputStream in = new DataInputStream(fstream_ip);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((strLine = br.readLine()) != null)   
			{
				result=classify(strLine,non,spa,Links);
				System.out.println(result);
				out.print(++autoGenerated);
				out.print(",");
				out.print(strLine);
				out.print(",");
				out.print(result);
				out.print("\n");
				
			}
		}	
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String classify(String line,Non_spamwords non,Spma_Words spa, String links) throws Exception 
	{
		int counter=0;
		boolean flag=false;
		String result="";
		String lenString="";
		String[] words = line.split(" ");
		
		for(String s:words)
		{
			lenString=lenString.concat(s);
			 
		}
		if(lenString.length()<10)
		{
			return "spam";
		}
		for(String s:words)
		{
			if(non.containsword(s))
			{
			counter++;
			}
			else if(spa.containsword(s))
			{
			counter--;
			}
		}
		String tagcounts=null;
		
		for(int i=0;i<words.length;i++)
		{
			tagcounts=tagcounts+words[i];
		}
			int excounter=0;
			int atcounter=0;
			int hashcounter=0;
			char[] sarray=tagcounts.toCharArray();
			for(int i=0;i<sarray.length;i++)
			{
				if(sarray[i]=='!')
				{
					excounter++;
				}
				if(sarray[i]=='@')
				{
					atcounter++;
				}
				if(sarray[i]=='#')
				{
					hashcounter++;
				}
			}
			if(excounter>4||atcounter>3||hashcounter>4)
			{
				flag=true;
			}
			excounter=0;
			atcounter=0;
			tagcounts="";
			hashcounter=0;
		
		if(counter>=0&&flag==false)
		{
			flag=false;
		}
		else
		{
			flag=true;
		}
		if(flag)	
	    result="spam";
		else
		result="nospam";
		return result;
				
	}
}