package classification.basic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class naiveBayes {	

//*********************************************************************************************************//
	public static HashMap map1 = new HashMap(); // for -1  training set
	public static HashMap map2 = new HashMap(); // for +1  training set
	public static HashMap map3 = new HashMap(); // for -1   test set
	public static HashMap map4 = new HashMap(); // for +1   test set
	public static int totalpositivecount = 0;
	public static int totalnegativecount = 0;
//**********************************************************************************************************//

public static int countColumnSize(String str) throws Exception
{
    int count = 0;
    
    for (int i=0;i<=str.length()-1;i++)
    {
        if (str.charAt(i) == ' ' && str.charAt(i+1)!=' ')
        {
            count++;
        }
    }
   // System.out.println(count);
    return count;
} //count columns size


public static int countRowSize(String datasetfilename) throws Exception
{
    int count = 0;
    Scanner rowlist = new Scanner(new File(datasetfilename));
	
	while(rowlist.hasNext()){
		rowlist.nextLine();
		count++;
	}
    //System.out.println(count);
    return count;
} //count row size

/**************
 * Function: Calculate probability
 * Arguments: 
 * 				type : -1 or +1
 * 				n 	 : total elemts in type
 * 				vocab: total lines in dataset
 * 
 */
public static void calculateProbability(String[] ncount,String[] pcount)//int type,float n,int vocab)
{
	float prob[] = new float[10];
	int PTotal = (int) TrainingData.positivecounter;
	int NTotal = (int) TrainingData.negativecounter;
	
	/*****
	 int nk[] = new int[10];
	if(type == -1){
	for(int i=1;i<10;i++){
			nk[i] = groupingIndexs.counter[i];
		}	
	}//if -1
	if(type == +1){
		for(int i=1;i<10;i++){
			nk[i] = groupingIndexs.counter2[i];
		}	
	}//if +1
	System.out.println("Probability of "+type+"'s = ");
	for(int i=1;i<10;i++){
		prob[i] = ((nk[i]+1)/(n+vocab));
		System.out.println(i+". P(("+(nk[i])+")+1)/("+n+"+"+vocab+")) : "+prob[i]);
	}// for     P((nk+1)/(n+vocab))
	*/
}//calculate probability



/*************************************************************************************************************
 * function : trueOrfalse
 * arguments: PNcounter = total positive or negative
 * 			  rowsize = total no.of rows
 * 		      key = array containing <index>:<value> 
 * @throws IOException 
 *************************************************************************************************************/
public static void trueOrfalse(String testdatasetfilename) throws IOException{
	int truecounter =0;int falsecounter =0;
	String[] pcount = new String[3000];
	String[] ncount = new String[3000];
	FileWriter outputfile = new FileWriter("D:\\CU DENVER\\4th Semester\\Data Mining and Analytics\\Assignmentss\\Assignment 4\\A4. Package\\Output\\Basic, poker.txt");
  	Scanner list = new Scanner(new File(testdatasetfilename));
  	Scanner outputlist = new Scanner(new File(testdatasetfilename));
	try{
	for(int i=1;i<107;i++){
		//System.out.print("\n"+counter+"\nTest  Training\n");
  		 for(int j=1;j<10;j++){
  	       //System.out.print(j+":"+TestData.testdata[i][j]+" ");
  	     String key = (j+":"+TestData.testdata[i][j]);
  	    Integer count = (Integer)TrainingData.map1.get(key);
  	   //System.out.println(key+" occured "+count+" in -1");
  	   	ncount[j] = String.valueOf(count);
  	   	if(ncount[j]== "null"){ int x =0;	ncount[j] =String.valueOf(x);}
  	    Integer count2 = (Integer)TrainingData.map2.get(key);
  	   //System.out.println(key+" occured "+ncount[j]+" in -1\n");
  	    pcount[j] = String.valueOf(count2);
  	  if(pcount[j]== "null"){ int x =0;	pcount[j] =String.valueOf(x);}
  	  //System.out.println(key+" occured "+pcount[j]+" in +1 ----\n");
  	   }//for
  		float nvalue = 1;
		float pvalue = 1;
		float PProb,NProb;
  	for(int j=1;j<10;j++){
  		//System.out.println(nvalue+"*"+Integer.parseInt(ncount[j])+"/"+TrainingData.negativecounter);
  		nvalue =nvalue*(Integer.parseInt(ncount[j])/(TrainingData.negativecounter));
  		//System.out.println(Integer.parseInt(ncount[j]));
  	}
  	for(int j=1;j<10;j++){
  		//System.out.println(nvalue+"*"+Integer.parseInt(pcount[j])+"/"+TrainingData.positivecounter);
  		pvalue =pvalue*(Integer.parseInt(pcount[j])/(TrainingData.positivecounter));
  		//System.out.println(Integer.parseInt(pcount[j]));
  	}
  	NProb = nvalue*(TrainingData.negativecounter/TrainingData.rowsize);
  	PProb = pvalue*(TrainingData.positivecounter/TrainingData.rowsize);
    //System.out.println("Negative -> "+NProb);//nvalue*((TrainingData.negativecounter/TrainingData.rowsize)));
  	//System.out.println("Positive -> "+PProb);//pvalue*(TrainingData.positivecounter/TrainingData.rowsize));
  	
  	while(list.hasNext()){
  	String word = list.next();
  	String line = outputlist.nextLine();
  	//System.out.println(word);
  	
  	if(word.contains("-1") || word.contains("+1")){
  	if(PProb > NProb){
  		String P ="+1";
  		if(word.contains(P))
  		{
  			outputfile.write(line+"-> True"+System.getProperty("line.separator" ));
  			//System.out.println("True");
  			truecounter++;
  			word = list.nextLine();
  		}//if
  		else{
  			outputfile.write(line+"-> False"+System.getProperty("line.separator" ));
  			//System.out.println("False");
  			falsecounter++;
  			word = list.nextLine();
  		}//else
  		}//if
  		//System.out.println("Positive is True");
  	else{
  		String N ="-1";
  		if(word.contains(N))
  		{
  			outputfile.write(line+"-> True"+System.getProperty("line.separator" ));
  			//System.out.println("True");
  			truecounter++;
  			word = list.nextLine();
  		}//if
  		else{
  			outputfile.write(line+"-> False"+System.getProperty("line.separator" ));
  		//	System.out.println("False");
  			falsecounter++;
  			word = list.nextLine();
  		}//if
  		//System.out.println("Negative is True");
  		}//else
  	if(!list.hasNext()){
  		outputfile.write("Total Instances = "+(truecounter + falsecounter)+System.getProperty("line.separator" ));
  		outputfile.write("Total true predications = "+truecounter+System.getProperty("line.separator" ));
  		outputfile.write("Total False predications = "+falsecounter+System.getProperty("line.separator" ));
  		outputfile.write("Accuracy = "+Math.round((float)((float)truecounter/(float)(truecounter+falsecounter))*100) +"%"+System.getProperty("line.separator" ));
  		System.out.println("Done");
  	  	break;
  	}
  	}//if
  	
  	//word =list.nextLine();
  	}//while 
  	outputfile.close();
  	//System.out.println("\n");
  	//counter++;
  }//for
	
	}//try
	catch(Exception e){
		e.printStackTrace();
	}
}

/*********************************************
 *Function: Main 
 * @param args
 * @throws Exception
 */
public static void main(String[] args) throws Exception
{
	String trainingdatasetfilename  ="D:/CU DENVER/4th Semester/Data Mining and Analytics/Assignmentss/Assignment 4/A4. Package/Datasets/breast_cancer, training.txt";
	String testdatasetfilename  ="D:/CU DENVER/4th Semester/Data Mining and Analytics/Assignmentss/Assignment 4/A4. Package/Datasets/breast_cancer, test.txt";
	TestData.readTestData(testdatasetfilename);
	System.out.println("\nREADING TRANING DATA\n");
	TrainingData.readTrainingData(trainingdatasetfilename);
	//readTrainingData("D:/CU DENVER/4th Semester/Data Mining and Analytics/Assignmentss/Assignment 4/A4. Package/Datasets/breast_cancer, training.txt");
	System.out.println("READING TEST DATA\n");
	//TestData.readTestData(testdatasetfilename);
	//readTestData("D:/CU DENVER/4th Semester/Data Mining and Analytics/Assignmentss/Assignment 4/A4. Package/Datasets/breast_cancer, test.txt");
	System.out.println("TESTING PREDICTIONS. . . . \n");
	trueOrfalse(testdatasetfilename);
	classification.Adaboost.NBAdaboost.main(null);
}//main
}//naiveBayes Class
