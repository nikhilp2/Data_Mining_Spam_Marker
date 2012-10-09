import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cleaner {

// Removes all the unwanted data like Date's,links,etc....
	
	public void preprocessing_data(String Input_Folder_Directory,String Output_Folder_Directory)
	{
		int filecounter=0;
	
		try
		{
			FileList InputList = new FileList(Input_Folder_Directory);
			FileList OutputList = new FileList(Output_Folder_Directory);
			
			ArrayList<String> Input_File_Names = new ArrayList<String>();
		    ArrayList<String> Output_File_Names = new ArrayList<String>();
			Input_File_Names = InputList.getFileNames();
			Output_File_Names = OutputList.getFileNames();
			  
			    
			  ArrayList<File> Input_Files = new ArrayList<File>();
			  Input_Files = InputList.getFiles();
			  
			  for (@SuppressWarnings("unused") File f : Input_Files) 
			  {	  
			  int first,last;
			  FileInputStream file_input_stream = new FileInputStream(Input_File_Names.get(filecounter));
			  FileWriter file_output_stream = new FileWriter(Output_File_Names.get(filecounter));
			  
			  //Buffer reader in and out 
			  DataInputStream in = new DataInputStream(file_input_stream);
			  BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			  BufferedWriter output_buffer_reader = new BufferedWriter(file_output_stream);

			  String Line;

			  //Read File Line By Line
			  while ((Line = buffer_reader.readLine()) != null)
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

				 
				  words= words.replaceAll("#[a-zA-Z||1-9]*", "").replaceAll("@[a-zA-Z]*(_||[a-zA-Z])*", "").replaceAll(" +"," ")	
						  .replaceAll("http:\\/\\/[^ ]*","").replaceAll("[^\\p{L}\\p{N}]", " ").replaceAll("-?\\d+","").replaceAll("RT", "");



				  output_buffer_reader.write(words);
				  output_buffer_reader.newLine();
				  System.out.println(words);
			  }
					filecounter++;
					buffer_reader.close();
					output_buffer_reader.close();
			  }
		}
		catch (Exception e)
		{
			  System.err.println("Error: " + e.getMessage());
			  e.printStackTrace();
		}
	}
	
	
}
