import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.gui.beans.Loader;


public class Weka_Runner 
{
	CSVLoader Loader= new CSVLoader();
	public void Training(Features voc) throws Exception
	{
		String OutputMatrixFolderName = "";
		
		FileList fList = new FileList(OutputMatrixFolderName);
		
		ArrayList<File> FileNames = new ArrayList<File>();
		
		// Get the term frequency from a single file, given the vocabulary
		FileNames = fList.getFiles();
		
		//for (File f : FileNames)
		//{
			Loader.setFile(new File(FileNames.get(0).getAbsolutePath()));
			Instances training =Loader.getDataSet();
			training.deleteAttributeAt(0);
			training.setClassIndex(training.numAttributes()-1);
			
			NaiveBayes classifier= new NaiveBayes();
			classifier.buildClassifier(training);
			System.out.println(classifier);
			
			//This is an instance for declaring the classification distribution
			
			double[] Distributions= new double[10000];
			LineNumberReader  lnrtrain = new LineNumberReader(new FileReader(new File(FileNames.get(0).getAbsolutePath())));
			lnrtrain.skip(Long.MAX_VALUE);
			
//			for(int i=0;i<(lnrtrain.getLineNumber());i++)
//			{
//				Distributions= classifier.distributionForInstance(training.get(i));
//				System.out.println(training.get(i));
//			    System.out.println("["+Distributions[0]+","+Distributions[1]);
//			}
		
		 
		String InputFolderName = "";
		String OutputFolderName = "";
		OutputMatrixFolderName = "";
		
		
		//This is an instance of split in order to pre process the data
		
		
		//This is an instance of feature generation matrix in order to build the matrix
		
		
		//Creating instance for reading the directory
		fList = new FileList(OutputMatrixFolderName);
		
		// Get the term frequency from a single file, given the vocabulary
		FileNames = fList.getFiles();
				
		//for (File f : FileNames)
		//{
			Loader.setFile(new File(FileNames.get(0).getAbsolutePath()));
			Instances testing = Loader.getDataSet();
			testing.deleteAttributeAt(0);
			testing.setClassIndex(testing.numAttributes()-1);
			testing.classIndex();
			
			LineNumberReader  lnrtest = new LineNumberReader(new FileReader(new File(FileNames.get(0).getAbsolutePath())));
			lnrtest.skip(Long.MAX_VALUE);
			
//			for(int i=0;i<(lnrtest.getLineNumber());i++)
//			{
//				Distributions= classifier.distributionForInstance(testing.get(i));
//				System.out.println(testing.get(i));
//			    System.out.println(+Distributions[0]+","+Distributions[1]);
//			}
//			
		 
		Evaluation e = new Evaluation(training); 
	    e.evaluateModel(classifier, testing);    
	    System.out.println(e.toSummaryString());
	    
	    System.out.println(e.toMatrixString());
	    
	}
}