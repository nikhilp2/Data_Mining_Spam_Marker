
public class Main {
	
	public static void main(String[] args) throws Exception 
	{
							String Clean_tweets= "C:/R/01";// output
							String Input_Folder_Name = "C:/R/02";// Input
							String Junk_tweet="C:/R/03";
							String	links="C:/R/04";
							String	urls="C:/R/05";
							String Output_dir="C:/R/06";
		
							// A file for Matrix,Rules 
							String Rules_for_tweets="Rule.txt";
			
							// Voacbulary buid after cleaning the tweets
							String vocbulary = "Frequency.txt";
			
							// Different type of matrix for Apriori
							String Aoriori_Matrix = "Ap.csv";
			
							// List of stop_words
							String Stopword_list = "english.txt";
		
							// My List of Spam words and Non-spam words 
							String Spam_words="Spam.txt";
							String Nonspam_words="Nospam.txt";

						     // Get links
							Get_Links gl = new Get_Links();
		 					 //gl.clean(links,Junk_tweet);
					
						  // Read Links from URL of tweets	
						
						  //url.clean(links);
					
		
						 //These two are the objects and for both of the classes
						  //Boots_strap b = new Boots_strap();
						 // b.bootstrap();
				
				
//				*******************Step-1****************
				
					//Clean the data by removing all the junk!    	
					Cleaner cln = new Cleaner();
					//cln.preprocessing_data( Input_Folder_Name,Clean_tweets);		
					
				
//				************ Step-2*************************
				
				// Removes Junk but leave #,@ and Numbers		
				
					Tweet_with_Special sc = new Tweet_with_Special();
					//sc.clean(Junk_tweet,Input_Folder_Name);
					
				
//				************** Step-3 ***************************													
				//Building the vocabulary by removing the stop words and save it a file						
				Stop_Word_List stop_words_list = new Stop_Word_List(Stopword_list);
				//Vocabulary vocbulary1 = new Vocabulary(Clean_tweets, stop_words_list, 100010);
				//vocbulary1.SaveToFile("Frequecy.txt");
				
				
//				***********************Step-4********************
				// My rules to classifing into spam or nonspam this is nothing but 
				// my lables
					Non_spamwords non_spam=new Non_spamwords(Nonspam_words);
					Spma_Words spam=new Spma_Words(Spam_words);
					Rules_for_classifier Ruler = new Rules_for_classifier();
					Rules_with_URL_opener t = new Rules_with_URL_opener();
				//t.Rule(Junk_tweet,Rules_for_tweets, non_spam, spam,links);
				//Ruler.Rule( Junk_tweet,Rules_for_tweets,non_spam,spam);
				
				
//				********************** Step-4 ********************					
				// Building the Tweet term matrix for vocabulary and the tweets 					
				
					String 	label_Matrix = "C:/R/06";
					String	Unlabel_Matrix="C:/R/09";
					
					Features voc1 = new Features("Frequency.txt");
					
					Feature_Matrix_Generator fmg = new Feature_Matrix_Generator();
					//fmg.TFMat(Clean_tweets, label_Matrix, voc1,Rules_for_tweets);

					Feature_Matrix_For_Testing ft = new Feature_Matrix_For_Testing();
					//ft.TFMat(Junk_tweet, Unlabel_Matrix, voc1);
					
					
//				********************* Step-5********************
				
				// Apriori needs different type of matrix... so build a Matrix for Apriori
				Features voc11 = new Features("Frequency.txt");
				//Apriori_MatrixGenerator apriori = new Apriori_MatrixGenerator();
				//apriori.TFMat(Clean_tweets, Aoriori_Matrix, voc11,Rules_for_tweets,vocbulary);
								
 				
//				****************** Step-6*********************
				
				// Finally, give your matrix to weka and see your results 
//				Weka_Runner runner = new Weka_Runner();
//				runner.Supervised(voc1);
									
				Weka_Runner wk = new Weka_Runner();
				wk.Training( voc11);
				
		
				
				
	}
}