import java.io.*;
import java.util.*;
import java.io.IOException;

public class Apriori_MatrixGenerator {
	
	// This class will generate the feature matrix for generating Apriori 
	
	String [] Term_Freq_Vector(String line, Features voc)
	{
		ArrayList<String> KeyList = voc.getKeyList();
		int n = KeyList.size();
				
		String [] Term_Frq = new String[n];
		
		for (int i = 0; i < n; i++){
			Term_Frq[i] = "No";
		}
		
		StringTokenizer token = ExtractBody.ExtractFromLine(line);
		
		while (token.hasMoreTokens()) {
			String str = token.nextToken().toLowerCase();
			int index = KeyList.indexOf(str);
			if (index >= 0) {
				Term_Frq[index] = "Yes";
			}
		}
		
		return Term_Frq;
	}
	
	public void TFMat(String Input_Folder, String Matrix_Save_location, Features v,String classification,String Vocabulary)
	{
		FileList fList = new FileList(Input_Folder);
		
		int MatrixCounte=0;
	
		ArrayList<File> FileNames = new ArrayList<File>();
		
		String[] Term_Frequency = null;
		
		try {
			FileWriter outFile = new FileWriter(Matrix_Save_location);
			File vocfile=new File(Vocabulary);
		
			PrintWriter out = new PrintWriter(outFile);
			
			String Input_String_Line="";
			String Class_Line="";
			
			FileInputStream vocabulary_ip = new FileInputStream(vocfile.getAbsolutePath());
			DataInputStream vocabuary_input = new DataInputStream(vocabulary_ip);
			BufferedReader vocabulary_br = new BufferedReader(new InputStreamReader(vocabuary_input));
			String vocreadLine="";
			String a ="@attribute";
			
			while (((vocreadLine = vocabulary_br.readLine()) != null))
			{
				if(MatrixCounte<100000)
				{
				String[] vocword=vocreadLine.split(" ");
				for(String s:vocword)
				{
				out.print(a);
				out.print("'");
				out.print(s);
				out.print("'");
				out.print(" ");
				out.print("{'Yes','No'}");
				}
				out.print("\n");
			}
			
			out.print(a);
			out.print("  ");
			out.print("'Class'");
			out.print("{'span','nospan'}");
			out.print("\n");
			out.print("@data");
			out.print("\n");
			
			MatrixCounte++;
		}
			FileNames = fList.getFiles();
			
			for(File f : FileNames)		
			{	
			FileInputStream fstream_ip = new FileInputStream(f.getAbsolutePath());
			FileInputStream cfstream_ip = new FileInputStream(classification);
			
			DataInputStream in = new DataInputStream(fstream_ip);
			DataInputStream Class = new DataInputStream(cfstream_ip);
			
			BufferedReader Inbr = new BufferedReader(new InputStreamReader(in));
			BufferedReader Classbr = new BufferedReader(new InputStreamReader(Class));
			
			while (((Input_String_Line = Inbr.readLine()) != null) && ((Class_Line = Classbr.readLine())!= null))   
			{  
				Term_Frequency = Term_Freq_Vector(Input_String_Line, v);
														
				for (int i = 0; i < Term_Frequency.length; i ++)
				{
					out.print("'");
					out.print(Term_Frequency[i]);
					out.print("'");
					out.print(",");
				}
				
				String[] class_words=Class_Line.split(",");
				out.print("'");
				out.print(class_words[class_words.length-1]);
				out.print("'");
				out.print("\n");
			}
			out.print("%");
			out.close();
			
		}
		}
			catch (IOException e) 
			{
			e.printStackTrace();
			}
		
	}
		
}
