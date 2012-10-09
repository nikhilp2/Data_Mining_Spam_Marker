import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Get_Links {

	public void clean(String OutDir,String InDir)
	{
		int filecounter=0;
	
		try
		{
			FileList InputList = new FileList(InDir);
			FileList OutputList = new FileList(OutDir);
			
			 ArrayList<String> InFileNames = new ArrayList<String>();
			  ArrayList<String> OutFileNames = new ArrayList<String>();
			  
			  ArrayList<File> Input_Files = new ArrayList<File>();
			  new ArrayList<File>();

			  Input_Files = InputList.getFiles();
			  InFileNames = InputList.getFileNames();
			  OutFileNames = OutputList.getFileNames();
			  
			  int httpcount=0;
			  int urlCount=0;
			  boolean linkcout=false;
			  boolean httpflag=true;
			  
			  for (@SuppressWarnings("unused") File f : Input_Files) 
			  {
			  FileInputStream file_input_stream = new FileInputStream(InFileNames.get(filecounter));
			  FileWriter file_output_stream = new FileWriter(OutFileNames.get(filecounter));
			  
			  //Buffer reader in and out 
			  DataInputStream in = new DataInputStream(file_input_stream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  BufferedWriter out = new BufferedWriter(file_output_stream);
			  
			  String Line;
			  String[] URL = new String[1000];
			  String[] words = new String[1000];

			  while ((Line = br.readLine()) != null)
			  {    
				  words = Line.split(" "); 
				  for (String f1 : words) 
				  {
					  if(f1.startsWith("http")||httpflag)
					  {
						  if(f1.startsWith("http"))
							{
							  	
							  		URL[urlCount]=words[httpcount];
									out.write(URL[urlCount]);
									System.out.println("got the link");	

								urlCount++;
								httpcount++;
								linkcout=true;
							}
							else
							{
								httpcount++;
								
							}
						  
					  }						
					 
				  } 
				  if (linkcout==false)
					out.write("No_link");
				  	out.newLine();
				  	httpcount=0;
					urlCount=0;
					linkcout=false;
					httpflag=true;
			  }
					filecounter++;
					br.close();
					out.close();
			  }
		}
		catch (Exception e)
		{
			  System.err.println("Error: " + e.getMessage());
			  e.printStackTrace();
		}
	}
	
	
}
