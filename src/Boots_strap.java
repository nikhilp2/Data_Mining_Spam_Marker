//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.InputStreamReader;
//import java.io.LineNumberReader;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import weka.classifiers.Evaluation;
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.bayes.NaiveBayesUpdateable;
//import weka.core.Instances;
//import weka.core.converters.CSVLoader;
//
//public class Boots_strap
//{
//	public static void main(String[] args) 
//	{
//		
//		int filecounter=0;
//		try
//		{
//		CSVLoader Loader= new CSVLoader();
//		CSVLoader Loader1= new CSVLoader();
//		
//		//This is a local variable
//		int label_Counter=0;
//		boolean Flag=false;
//		
//		String	dataset_for_training= "C:/R/10";
//		String 	dataset_for_testing=  "C:/R/11";
//		String 	dataset_for_temp=  "C:/R/09";
//		
//		//File list for all the three folders
//		FileList TestingList = new FileList(dataset_for_testing);
//		FileList TrainingList = new FileList(dataset_for_training);
//		FileList TempingList = new FileList(dataset_for_temp);
//		
//		//Array list for all the three folders
//		ArrayList<String> Testing_File_Names = new ArrayList<String>();
//		ArrayList<String> Trainong_File_Names = new ArrayList<String>();
//		ArrayList<String> Temp_File_Names = new ArrayList<String>();
//		
//		//Array list for getting files for all the three
//		ArrayList<File> Training_Files = new ArrayList<File>();
//		ArrayList<File> Testing_Files = new ArrayList<File>();
//		ArrayList<File> Temp_Files = new ArrayList<File>();
//		
//		//Getting the file name and files of each of the folder 
//		Testing_File_Names=TestingList.getFileNames();
//		Trainong_File_Names=TrainingList.getFileNames();
//		Temp_File_Names=TempingList.getFileNames();
//		
//		//To get the actual file
//		Testing_Files=TestingList.getFiles();
//		Training_Files=TrainingList.getFiles();
//		Temp_Files=TempingList.getFiles();
//		
//					
//		for (int i=0;i<5;i++) 
//		  {
//					Loader.setFile(new File(Training_Files.get(0).getAbsolutePath()));
//					Instances training11 =Loader.getDataSet();
//					training11.deleteAttributeAt(0);
//					training11.setClassIndex(training11.numAttributes()-1);
//					
//					NaiveBayesUpdateable nbc=new NaiveBayesUpdateable();
//					nbc.buildClassifier(training11);
//								
//					
//										
//					Loader.setFile(new File(Testing_Files.get(i).getAbsolutePath()));
//					Instances t1 = Loader.getDataSet();
//					t1.deleteAttributeAt(0);
//					t1.setClassIndex(t1.numAttributes()-1);
//					t1.classIndex();
//					
//
//					//Creating an instance of Weka in order to get the training and testing 	
//					//**** Train data evaluation***// 
//					Evaluation e = new Evaluation(training11); 
//				    e.evaluateModel(nbc, t1);
//				    
//				    //**** Classify Instance****//
//				    System.out.println(e.toSummaryString());
//				    
//				    // *** Confusion Matrix****//
//				    System.out.println(e.toMatrixString());
//				    
//					
//					double[] Distribution= new double[2000];
//					
//					
//					//Open testing in the input mode to read the lines
// 					FileInputStream Test_input_stream = new FileInputStream(Testing_Files.get(i).getAbsolutePath());
// 					DataInputStream Testin = new DataInputStream(Test_input_stream);
// 					BufferedReader Testbr = new BufferedReader(new InputStreamReader(Testin));
//					
//					//Opening both the temp and train in output mode 
// 					FileWriter Train_output_stream = new FileWriter(Training_Files.get(0).getAbsolutePath(),true);
// 					BufferedWriter trainout = new BufferedWriter(Train_output_stream);
//					FileWriter Temp_output_stream = new FileWriter(Temp_Files.get(i).getAbsolutePath());
//					BufferedWriter tempout = new BufferedWriter(Temp_output_stream);
//					
//					String strLine="";
//					String word="";
//					try 
//					{
//						//This is the loop for wrirting the things inside
//						while ((strLine = Testbr.readLine()) != null)   
//						{	
//																	
//							if(Flag)
//							{
//								tempout.write(strLine);
//								Distribution= nbc.distributionForInstance(training11.get(label_Counter));
//								if(Distribution[0]>Distribution[1])
//								{
//									tempout.write("Nonspam");
//									tempout.write("\n");
//								}
//								else
//								{
//									tempout.write("spam");
//									tempout.write("\n");
//								}
//								label_Counter++;
//							}
//							else
//							{
//								Flag=true;
//							}
//						
//
//							
//						}
//						label_Counter=0;
//						Flag=false;
//						tempout.close();
//						
//						//Open temp in the input mode to read the lines
//	 					FileInputStream Temp_input_stream = new FileInputStream(Temp_Files.get(i).getAbsolutePath());
//	 					DataInputStream Tempin = new DataInputStream(Temp_input_stream);
//	 					BufferedReader Tempbr = new BufferedReader(new InputStreamReader(Tempin));
//	 					
//
//						//This is the one that is used to be allocated for the training 
//	 					PrintWriter trainoutprint = new PrintWriter(trainout);
//						
//	 					
//	 					//Reading files from the temp data
//	 					while ((strLine = Tempbr.readLine()) != null)   
//						{	
//	 						trainoutprint.print(strLine.trim());
//	 						trainoutprint.print("\n");
//						}
//	 					trainoutprint.close();
//					}
//					catch (Exception e1)
//					{
//						//Catch exception if any
//						System.err.println("Error: " + e1.getMessage());
//						e1.printStackTrace();
//					}
//		  }
//						
//		}
//		catch (Exception e2)
//		{
//			//Catch exception if any
//			System.err.println("Error: " + e2.getMessage());
//			e2.printStackTrace();
//		}
//		}
//	}
