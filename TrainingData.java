package classification.basic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TrainingData {
	
    //*********************************************************************************************************//
		public static HashMap map1 = new HashMap(); // for -1  training set
		public static HashMap map2 = new HashMap(); // for +1  training set
		public static HashMap map3 = new HashMap(); // for -1   test set
		public static HashMap map4 = new HashMap(); // for +1   test set
		public static int totalpositivecount = 0;
		public static int totalnegativecount = 0;
		public static String[] negativeindex = new String[1000];
		public static int[] negativevalue = new int[1000];
		public static String[] positiveindex = new String[1000];
		public static int[] positivevalue = new int[1000];
		public static float positivecounter =0;
    	public static float negativecounter =0;
    	public static int columnsize=0;
    	public static int rowsize=0;
	//**********************************************************************************************************//

	public static void readTrainingData(String datasetfilename) throws Exception { // READING TRAINING DATASET

		
	/*****************************************************************************************************************
	 * Function : readTrainingData()
	 * Arguments: Filename
	 * Output: <INDEX>:<VALUE> = <COUNT>
	 */	
	//public static void readTrainingData(String datasetfilename) throws Exception { // READING TRAINING DATASET
		
	    try {
	    	Scanner rowlist = new Scanner(new File(datasetfilename));
	    	String rowvalue = rowlist.nextLine();
	    	columnsize = naiveBayes.countColumnSize(rowvalue);
	    	rowsize = naiveBayes.countRowSize(datasetfilename);
	    	//System.out.println(rowsize);
	    	Scanner list = new Scanner(new File(datasetfilename));
	    	
	    	String[] splitwordpositive;
	    	String[] splitwordnegative;
	    	
	    	while (list.hasNext()) {
	    		
		    	 if (!list.hasNext()){
			        	break;
					}//if
		        String word = list.next();
		        //System.out.println(word);
		        if(word.equals("-1")){
		        	//System.out.println("Under -1");
		        for(int i=0;i<columnsize;i++){
	        		word = list.next();
	        		//System.out.println(word);
	        		splitwordnegative = word.split(":");
	        		//System.out.println(Integer.parseInt(splitwordnegative[0]));
	        		//totalnegativecount =totalnegativecount + Integer.parseInt(splitwordnegative[1]);
	        		//splitword[1]+=splitword[1];
	        		if(map1.containsKey(word)) {
	      	          Integer count = (Integer)map1.get(word);
	      	          map1.put(word, new Integer(count.intValue() + 1));
	      	        } else {
	      	           map1.put(word, new Integer(1));
	      	        }
	        		//System.out.println(totalvaluecount[0]);
		        }//for
		      negativecounter++;
	        word = list.nextLine();
	        } // if -1
		       if(word.equals("+1")){
		    	   //System.out.println("Under +1");
		    	   for(int i=0;i<columnsize;i++){
		        		word = list.next();
		        		//System.out.println(word);
		        		splitwordpositive = word.split(":");
		        		//System.out.println(Integer.parseInt(splitwordpositive[1]));
		        		totalpositivecount =totalpositivecount + Integer.parseInt(splitwordpositive[1]);
		        		if(map2.containsKey(word)) {
		      	          Integer count = (Integer)map2.get(word);
		      	          map2.put(word, new Integer(count.intValue() + 1));
		      	        } else {
		      	           map2.put(word, new Integer(1));
		      	        }
		        	}//for
		    	   positivecounter++;
		    	   //word = list.nextLine();
		        } // if +1 */
		       //word = list.nextLine();
		      }//while
	    	
	    	//System.out.println("FOR -1");
		    ArrayList arraylist = new ArrayList(map1.keySet());
		    Collections.sort(arraylist);
		    for (int i = 0; i < arraylist.size(); i++) {
		      String key = (String)arraylist.get(i);
		      negativeindex[i]=key;
		      Integer count = (Integer)map1.get(key);
		      negativevalue[i] = count;
		    //System.out.println(negativeindex[i]+" = "+negativevalue[i]);
		    }
		    //System.out.println("\n\nFOR +1\n");
		    ArrayList arraylist2 = new ArrayList(map2.keySet());
		    Collections.sort(arraylist2);
		    for (int i = 0; i < arraylist2.size(); i++) {
		      String key = (String)arraylist2.get(i);
		      positiveindex[i]=key;
		      Integer count = (Integer)map2.get(key);
		      positivevalue[i] = count;
			  //System.out.println(positiveindex[i]+" = "+positivevalue[i]);
		      //System.out.print(key+" = "+count);
		    }
		    System.out.println("\nNo.of columns = " + columnsize);
		    System.out.println("No.of rows = " + rowsize);
		    //System.out.println("Sum of +ve <value> E(+) = " + totalpositivecount);
		    //System.out.println("Sum of -ve <value> E(-) = " + totalnegativecount);
		    System.out.println("Positive Lines = "+ positivecounter);
		    System.out.println("Negative Lines = "+ negativecounter);
		    System.out.println("Positive Probability P(+/totallabels)="+ (float)(positivecounter/rowsize));
		    System.out.println("Negative Probability P(-/totallabels)="+ (float)(negativecounter/rowsize));
		    //groupingIndexs.indexvaluescount(datasetfilename);
		    System.out.println("\n");
		    //naiveBayes.calculateProbability(-1, totalnegativecount, columnsize);
		    //System.out.println("\n\n");
		    //naiveBayes.calculateProbability(+1, totalpositivecount, columnsize);
		    //System.out.println(index[1]+" = "+value[1]);
		    
		   	 	
		    }//try
	    catch (IOException e) 
		{
		    e.printStackTrace();
		}//catch	
	} // readData
}
