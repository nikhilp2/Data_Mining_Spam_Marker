import java.io.*;
import java.util.Vector;


public class Spma_Words extends  Vector<String>
{
  
private static final long serialVersionUID = 1L;

public Spma_Words()
  {
  
  }

  public Spma_Words (String flist) 
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
      System.err.println("Error reading spam_words list "+flist);
      e.printStackTrace();
    }
  }
  
  public boolean containsword (String tw ){
    return super.contains(tw.trim().toLowerCase());
  } 
}
