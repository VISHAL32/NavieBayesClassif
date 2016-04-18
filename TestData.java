package classification.basic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TestData {
	public static int i =0 ,j =1;
	public static char[][] testdata = new char[4000][20];
	public static void readTestData(String datasetfilename){
	//public void readTestdata(){
		
		for(int i=0;i<4000;i++){
     		 for(int j=0;j<20;j++){
     	        testdata[i][j] = 0;
     		 }//for
     	 	}//for
		i=0;
	try {
		//String datasetfilename ="Datasets/breast_cancer, test.txt";
    	Scanner list = new Scanner(new File(datasetfilename));
    	String splitword;
    	while (list.hasNext()) {
    		String word = list.next();
	    	 if(word.contains("-1") || word.contains("+1")){
	    		 word =list.next();
	    		 i++;j=1;
	    	 }//if
	    	splitword = Arrays.toString(word.split(":"));
	    	testdata[i][j] =splitword.charAt(4);
	    	j++;
	    	if (!list.hasNext()){
	        	break;
	    }//if
    	}//while
    /*	
    	for(int i=1;i<107;i++){
   		 for(int j=1;j<10;j++){
   	       System.out.print(j+":"+testdata[i][j]+" ");
   		 }//for
   		System.out.print("\n");
   	 	}//for
   	 */	
	}//try
    catch (IOException e) 
	{
	    e.printStackTrace();
	}//catch
	}//OrganizeData
}//organizeData
