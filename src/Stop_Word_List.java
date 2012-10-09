import java.io.*;
import java.util.Vector;


public class Stop_Word_List extends  Vector<String>
{
private static final long serialVersionUID = 1L;

public Stop_Word_List()
  {
  
  }

  public Stop_Word_List (String flist) 
  {
    super();
    try {
      BufferedReader in = new BufferedReader(new FileReader(flist));
      String word = null;
      while ( (word = in.readLine()) != null )
        {
          this.add(word.trim().toLowerCase());
        }
    }
    catch (IOException e){
      System.err.println("Error reading stopword list "+flist);
      e.printStackTrace();
    }
  }
  
  public boolean contains (String tw ){
    return super.contains(tw.toLowerCase());
  } 
}
