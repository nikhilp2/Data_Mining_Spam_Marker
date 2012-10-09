import java.io.*;
import java.util.*;
import java.util.Map.Entry;


@SuppressWarnings("serial")
public class Features extends HashMap<String, Integer>{
	
	public ArrayList<File> Files;
	public ArrayList<WordFrequencyPair> WordFreqList;
	public ArrayList<String> KeyList;
	
	public ArrayList<WordFrequencyPair> getWordFreqList()
	{
		return WordFreqList;
	}
	
	public ArrayList<String> getKeyList()
	{
		return KeyList;
	}

	
	//This is req
	
	public Features(String dirName, Stop_Word_List swlist, int noKeys)
	{
		super();
		try 
		{
		
		KeyList = new ArrayList<String>();
		WordFreqList = new ArrayList<WordFrequencyPair>();
		Files = new ArrayList<File>();
		
		FileList fList = new FileList(dirName);
		
		Files = fList.getFiles();
		// Step 1, add tokens from all files
		for (File f : Files){
			addTokens(f, swlist);
		}
		
		// Step 2, get the term - frequency pairs and sort them
		WordFreqList = getWordFrequencyArray();
		
		Collections.sort(WordFreqList);
		Collections.reverse(WordFreqList);
		
		// Step 3, get the most top keys and save them into KeyList
		for (int i = 0; i < noKeys && i < this.size(); i ++)
		{
			WordFrequencyPair wp = WordFreqList.get(i);
			KeyList.add(wp.getWord());
		}
		}
	 catch (Exception e)
	 {
		System.err.println("Error: " + e.getMessage());
	}
		
	}
	
	public Features(String fname)
	{
		// Just read the vocabulary from a file, each line will be a keyword.
		super();
		WordFreqList = new ArrayList<WordFrequencyPair>();
		Files = new ArrayList<File>();
		
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(fname);

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader inputFile = new BufferedReader(new InputStreamReader(in));
			
			KeyList = new ArrayList<String>();
			String line;
			while ((line = inputFile.readLine()) != null){
				KeyList.add(line);
			}
			
			
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void addTokens(File f, Stop_Word_List swlist)
	{
		// Add all the tokens from a file to the vocabulary
		StringTokenizer token = ExtractBody.ExtractFromFile(f.getAbsolutePath());
		while (token.hasMoreTokens()) {
			String str = token.nextToken().toLowerCase();
			if (!swlist.contains(str)) {
				
				addToken(str);
			}
		}
	}
	
	public void addToken(String str)
	{
		// Add one tokens from a file to the vocabulary
		if (! str.isEmpty())
		{
			if (this.containsKey(str))
			{
				int count = get(str);
				put(str, new Integer(count + 1));
			}
			else
			{
				put(str, new Integer(1));
			}
		}
	}

	public ArrayList<WordFrequencyPair> getWordFrequencyArray() 
	{
		// Return an array of comparable objects (e.g. for sorting)
		ArrayList<WordFrequencyPair> wfp = new ArrayList<WordFrequencyPair>();
		
		for (Iterator<Entry<String, Integer>> e = this.entrySet().iterator(); e.hasNext();) {
			@SuppressWarnings("rawtypes")
			Map.Entry kv = (Map.Entry) e.next();
			String str = (String) kv.getKey();
			
			int notokens = get(str);
			
			wfp.add(new WordFrequencyPair(str, notokens));
		}
		return wfp;
	}
	
	// not req for testing
	public void SaveToFile(String fname)
	{
		// Save the Vocabulary to a text file, with each line being one keyword
		try {
			FileWriter outFile = new FileWriter(fname);
			PrintWriter out = new PrintWriter(outFile);

			for (int i = 0; i < KeyList.size(); i ++)
			{
				//if(!KeyList.contains(KeyList.get(i)))
				out.println(KeyList.get(i));
			}
			
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int sizeofhashset()
	{
	return KeyList.size();
	}

}
