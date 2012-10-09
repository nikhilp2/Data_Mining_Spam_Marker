import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class Non_spamwords extends  Vector<String>
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Non_spamwords()
  {
  
  }

  public Non_spamwords (String flist) 
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
      System.err.println("Error reading Non_spam_words list "+flist);
      e.printStackTrace();
    }
  }
  
  //This will get the stop words if that is contained in the vector
  public boolean containsword (String tw ){
    return super.contains(tw.trim().toLowerCase());
  } 
}