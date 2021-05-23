package Export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import com.mysql.cj.xdevapi.Client;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WriteTableToTxt {

	private Hashtable<String, Hashtable<String, Hashtable<String, String>>> tablesData ;
	private String exportCase;
	private String [] sqlCountryTable;
	
	public WriteTableToTxt(Hashtable<String, Hashtable<String, Hashtable<String, String>>> tablesData, String exportCase, String [] sqlCountryTable) {
		this.tablesData = tablesData;
		this.exportCase = exportCase;
		this.sqlCountryTable = sqlCountryTable;
	}
	
	public WriteTableToTxt() {
		
	}
	
	// ~~~ TXT MODE ~~~
	// First Line "ExportCase"
	// Next -> Problem
	// Next -> CountryId
	// Next -> yearID
	// Next -> counrtyData,yearID=counrtyData,...,yearID=counrtyData
	// Next "\n"
	// Next -> new Problem OR EOF
	public void writeToTxt() {
		try {
		      FileWriter myWriter = new FileWriter("hashTable.txt");
		      Set<String> keys = this.tablesData.keySet();
		      
		      myWriter.write(this.exportCase + "\n");
		      int count = 0;
		      for(String i: keys){
		    	  
		    	  myWriter.write(i + "\n");
		    	  
		    	  Set<String> keys2 = this.tablesData.get(i).keySet();
		    	  
		    	  for(String j:keys2) {
		    		  myWriter.write(this.sqlCountryTable[Integer.parseInt(j)] + "\n" );
		    		  Set<String> keys3 = this.tablesData.get(i).get(j).keySet();
		    		  for(String k:keys3) {
		    			  myWriter.write(k + "," + this.tablesData.get(i).get(j).get(k) + ",");
		    			  
		    		  }
		    		  myWriter.write("\n");
		    	  }
		    	  count ++;
		    	  if(count < keys.size()) {myWriter.write("\n");}
		    	  
		      }
		      
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		    } catch (IOException e) {
		    	
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      
		    }
		    
	}
	
	public Hashtable<String, Hashtable<String, Hashtable<String, String>>> readFromTxt(){
		Hashtable<String, Hashtable<String, Hashtable<String, String>>> table = new Hashtable<String, Hashtable<String, Hashtable<String, String>>>();
		String exportCase;
		String key1 = null;
		String key2 = null;
		try {
		      File myObj = new File("hashTable.txt");
		      Scanner myReader = new Scanner(myObj);
		      Hashtable<String, String> tableWithYears = new Hashtable<String, String>();
		      Hashtable<String, Hashtable<String, String>> tableWithCountry = new Hashtable<String, Hashtable<String,String>>();
		      
		      exportCase = myReader.nextLine();
		      
		      while (myReader.hasNextLine()) {
		        String data1 = myReader.nextLine();
		        String data3 = "";
		        
		        if(data1 != " "){key1 = data1;}
		        while(true) {
		        	if (data3 == "") {
				        String data2 = myReader.nextLine();
				        if(data2 != "\n" && data2 != null){key2 = data2;}
		        	}
			        String text = myReader.nextLine();
					String[] strArray = text.split(",");
				
					for(int i = 0; i < strArray.length; i = i+2){
						if((i+1) < strArray.length){
							tableWithYears.put(strArray[i], strArray[i+1]);
						}else{
							break;
						}
					}
			        tableWithCountry.put(key2, tableWithYears);
			        if(data3 == null) {break;}
			        if(myReader.hasNext() == false) {break;}
			        data3 = myReader.nextLine();
			        if(data3 != "\n" && data3 != null){key2 = data3;continue;}
			        if(data3 == "\n"){break;}
		        }
		        if(myReader.hasNext() == false) {break;}
				this.tablesData.put(key1, tableWithCountry);
				if(data3 == "\n") {continue;}
				if(data3 == null) {break;}
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return table;
	}
	
	public void writeToXml() {
		try {
		      FileWriter myWriter = new FileWriter("hashTable.xml");
		      Set<String> keys = this.tablesData.keySet();
		      
		      int count = 0;
		      for(String i: keys){
		    	  
		    	  myWriter.write("<" + i + ">\n\t");
		    	  
		    	  Set<String> keys2 = this.tablesData.get(i).keySet();
		    	  
		    	  for(String j:keys2) {
		    		  myWriter.write(j + "\n" );
		    		  Set<String> keys3 = this.tablesData.get(i).get(j).keySet();
		    		  for(String k:keys3) {
		    			  myWriter.write(k + "," + this.tablesData.get(i).get(j).get(k) + ",");
		    			  
		    		  }
		    		  myWriter.write("\n");
		    	  }
		    	  count ++;
		    	  if(count < keys.size()) {myWriter.write("\n");}
		    	  
		      }
		      
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		    } catch (IOException e) {
		    	
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      
		    }
		
	}
	
	public Hashtable<String, Hashtable<String, Hashtable<String, String>>> getTablesData() {
		return this.tablesData;
	}
	

	
}
