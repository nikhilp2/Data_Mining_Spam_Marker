import java.io.*;
import java.util.*;

// This class will take in a directory, and return all files in it.

public class FileList {
	public ArrayList<String> FileNames;
	public ArrayList<File> Files;
	
	public ArrayList<String> getFileNames()
	{
		return FileNames;
	}

	public ArrayList<File> getFiles() 
	{
		return Files;
	}

	public void AddAllFiles(File dir) {

		File[] files = dir.listFiles(); 
		for (File f : files) {
			if (f.isDirectory()) {
				AddAllFiles(f); 
			} 
			else 
			{
				Files.add(f);
				FileNames.add(f.getAbsolutePath());
			}
		}
	}

	public FileList(String links) {
		super();
		FileNames = new ArrayList<String>();
		Files = new ArrayList<File>();
		
		File dir = new File(links);
		AddAllFiles(dir);
	}
}
