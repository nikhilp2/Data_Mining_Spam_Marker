import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tweet_with_Special {

	@SuppressWarnings("unused")
	public void clean(String OutDir,String InDir)
	{
		int filecounter=0,CounterFile=0;
		
	
		try
		{
			FileList InputList = new FileList(InDir);
			FileList OutputList = new FileList(OutDir);
			
			 ArrayList<String> InFileNames = new ArrayList<String>();
			  ArrayList<String> OutFileNames = new ArrayList<String>();
			  
			  ArrayList<File> Input_Files = new ArrayList<File>();
			  ArrayList<File> Output_Files= new ArrayList<File>();

			  Input_Files = InputList.getFiles();
			  InFileNames = InputList.getFileNames();
			  OutFileNames = OutputList.getFileNames();
			  
			  for (File f : Input_Files) 
			  {
			  
			  System.out.println("i am inside!!!!");
			  int first,last;
			  FileInputStream file_input_stream = new FileInputStream(InFileNames.get(filecounter));
			  FileWriter file_output_stream = new FileWriter(OutFileNames.get(filecounter));
			  
			  
			  DataInputStream in = new DataInputStream(file_input_stream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			  PrintWriter out = new PrintWriter(file_output_stream);
			  String Line;
			  int LineCounter=0;
			  boolean closeflag=true;
			  while ((Line = br.readLine()) != null)
			  {  
				  if(LineCounter<1000)
					{
				  first = Line.indexOf('\t');
				  last = Line.indexOf('\t',first+1);
				  
				  String words = Line.substring(first+1, last);
				  
				  
				  while(words.startsWith("@"))
				  {
					  words = words.substring(words.indexOf(' ')+1);
					  
				  }
				  
				  while(words.startsWith("RT @") || words.startsWith("RT: @"))
				  {
				  	  
					  words = words.substring(words.indexOf(' ')+1);
					  words = words.substring(words.indexOf(' ')+1);	
				  }
				  
	
				  
				
				  	out.write(words);
					out.print("\n");
					System.out.println(words);
					LineCounter++;
					}
				  else
					{
						out.close();				
						file_output_stream = new FileWriter(OutFileNames.get(CounterFile));
						out = new PrintWriter(file_output_stream);
						filecounter++;
						LineCounter=0;
					}
			  }
					;
					if(closeflag)
					br.close();
					out.close();
					closeflag=true;
			  }
		}
		catch (Exception e)
		{
			  System.err.println("Error: " + e.getMessage());
			  e.printStackTrace();
		}
	}
	
	
}
