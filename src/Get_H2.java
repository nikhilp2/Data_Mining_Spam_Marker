//import java.net.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.io.*;
//import org.apache.commons.validator.routines.UrlValidator;
//import net.htmlparser.jericho.*;
//
//
//public class Get_H2 {
//    
//	public String clean(String InDir) throws Exception
//	{
//		String g="";
//		int filecounter=0;
//		UrlValidator urlValidator = new UrlValidator();
//		try
//		{
//			FileList InputList = new FileList(InDir);
//			
//			 ArrayList<String> InFileNames = new ArrayList<String>();
//			  ArrayList<File> Input_Files = new ArrayList<File>();
//			  Input_Files = InputList.getFiles();
//			  InFileNames = InputList.getFileNames();
//			  
//		
//			  for (File f : Input_Files) 
//			  {
//			  
//			  System.out.println(f);
//			  FileInputStream file_input_stream = new FileInputStream(InFileNames.get(filecounter));
//			  DataInputStream in = new DataInputStream(file_input_stream);
//			  BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
//			  URLConnection urlConnection = null;
//			  String Line;
//			  
//			  while ((Line = br.readLine()) != null)
//			  {   
//				  if (urlValidator.isValid(Line)) {
//				  
//				  if (Line.indexOf(':')==-1 && Line.indexOf('/')==-1) 
//					MasonTagTypes.register();
//				  URL u = new URL (Line);
//				  urlConnection = u.openConnection();
//				  urlConnection.setDoOutput(true);
//				  urlConnection.setConnectTimeout(100000);
//				  
//				  HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection ();
//				  huc.setRequestMethod ("GET");  //OR  
//				 // huc.setRequestMethod ("HEAD");
//				  huc.connect () ; 
//				  int code = huc.getResponseCode() ;
//				  if(code==200||code==301 )
//				  {
//					  Source source=new Source(new URL(Line));
//					 g= displaySegments(source.getAllElements(HTMLElementName.H2));
//					   
//				  }
//			  }	 
//		}
//	}
//}
//		catch (MalformedURLException e)
//		{
//			 System.err.println("Error: " + e.getMessage());
//			  e.printStackTrace();
//		}
//		return g;
//	
//	
//	}
//
//	private String displaySegments(List<Element> list)
//	{
//		 String g="";
//		for (Segment segment : list) 
//		{
//			 
//			  g=String.valueOf(segment);
//			  g=g.trim().replaceAll("\\<.*?\\>","");
//			  g=g.trim().replaceAll("( )+", " ");
//			  g=g.trim().replaceAll(" \t\r\n","");
//			  g=g.trim().replaceAll(" +"," ");			 	
//		}
//		return g;
//		
//	}
//	
//	
//	}