import java.io.*;
import java.util.*;
import java.io.IOException;

//أسد طه بلد ترم فريقوينسي ماتريكس

public class Feature_Matrix_Generator {
	
	int [] TFVector(String line, Features voc)
	{
		ArrayList<String> KeyList = voc.getKeyList();
		int n = KeyList.size();
				
		int [] TF = new int[n];
		
		for (int i = 0; i < n; i++){
			TF[i] = 0;
		}
		
		StringTokenizer token = ExtractBody.ExtractFromLine(line);
		
		while (token.hasMoreTokens()) 
		{
			String str = token.nextToken().toLowerCase();
			int index = KeyList.indexOf(str);
			if (index >= 0) {
				TF[index] = TF[index] + 1;
			}
		}
		
		return TF;
	}

	public void header(PrintWriter pw,int vocsize)
	{
		
		for(int k=1;k<=vocsize;k++)
		{
			pw.print("F"+k);
			pw.print(",");
		}
		pw.print("C");
		pw.print("\n");
	}
	
	public void TFMat(String dirName, String outfile, Features voc,String classification) throws IOException
	{
		FileList fList = new FileList(dirName);
		FileList outfList = new FileList(outfile);
		ArrayList<String> OutFileNames = new ArrayList<String>();
		
		OutFileNames = outfList.getFileNames();

		FileWriter file_output_stream = new FileWriter(OutFileNames.get(0));
		PrintWriter out = new PrintWriter(file_output_stream);
		 
		ArrayList<File> FileNames = new ArrayList<File>();
		
		int[] TF = null;
		int matrix=0,counter=1;
		boolean close=true;
		boolean header=true;
		try {
		
			String Input_stream_Line="";
			String Classification_Input_Stream_line="";
			
			FileNames = fList.getFiles();
			
			for(File f : FileNames)		
			{	
			FileInputStream file_stream_ip = new FileInputStream(f.getAbsolutePath());
			FileInputStream class_file_stream_ip = new FileInputStream(classification);
			
			DataInputStream in = new DataInputStream(file_stream_ip);
			DataInputStream Class = new DataInputStream(class_file_stream_ip);
			
			BufferedReader Input_buffer = new BufferedReader(new InputStreamReader(in));
			BufferedReader Classification_buffer = new BufferedReader(new InputStreamReader(Class));
			
			while (((Input_stream_Line = Input_buffer.readLine()) != null) && ((Classification_Input_Stream_line = Classification_buffer.readLine())!= null))   
			{  
					TF = TFVector(Input_stream_Line, voc);
					if(matrix<1000)
					{
					
					if(header)
					{
						header(out,voc.sizeofhashset());	
					}
					header=false;
														
				for (int i = 0; i < TF.length; i ++)
				{
					out.print(TF[i]);
					out.print(",");
				}
				
				String[] classwords=Classification_Input_Stream_line.split(",");
				out.print(classwords[classwords.length-1]);
				out.print("\n");
				matrix++;
			}
			else
			{
				out.close();
				close=false;
				header=true;
				file_output_stream=new FileWriter(OutFileNames.get(counter));
				out=new PrintWriter(file_output_stream);
				counter++;
				matrix=0;
			}
			}
			if(close)
			out.close();
			close=true;
		}
		}
		
			catch (IOException e) 
			{
			e.printStackTrace();
			}	
	}		
		
}
