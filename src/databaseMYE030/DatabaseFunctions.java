package databaseMYE030;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFunctions {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/MYE030";
	private static String username = "root";
	private static String password = "";
	
	// firstString -> problem as key
	// As value a Hashtable where key is country for each problem
	// and as value country's data
	private Hashtable<String, Hashtable<String, Hashtable<String, String>>> tablesData = new Hashtable<String, Hashtable<String, Hashtable<String, String>>>();
	
	
	public DatabaseFunctions() {
		
	}
	
	
	public Hashtable<String, Hashtable<String, Hashtable<String, String>>> getTablesData(ArrayList<String> tablesNameToPrint, ArrayList<String>  col1, ArrayList<String> col2) throws Exception {
		
		for(int i = 0; i<tablesNameToPrint.size(); i++) {
			Hashtable<String, Hashtable<String, String>> tablesDataWithCountryYears = new Hashtable<String,Hashtable<String, String>>();
			for(int j = 0; j<col1.size(); j++) {
				try {
					MyQuery myQuery = new MyQuery(tablesNameToPrint.get(i), col1.get(j), col2);

					Connection connection = getConnection();
					Statement statement = connection.createStatement();
					
					ResultSet result = statement.executeQuery(myQuery.makeQuery());
					
					Hashtable<String, String> tablesDataWithYearAndData = new Hashtable<String,String>();
					int k = 0; 
					
					while(result.next()) {
						
						tablesDataWithYearAndData.put(col2.get(k), result.getString("country_DATA"));
						k++;
					}
					tablesDataWithCountryYears.put(col1.get(j), tablesDataWithYearAndData);
					
					System.out.println("All records have been selected");
					
				}catch(Exception e) {System.out.println(e);}
			}
			this.tablesData.put(tablesNameToPrint.get(i), tablesDataWithCountryYears);
			
			
		}
		return this.tablesData;
		
	}
	
	public Connection getConnection() throws Exception{
		try {
			Class.forName(driver);
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			
			return connection;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	public ArrayList <String> fillDropDownButtonsWithData(String problem, String queryCase) throws Exception {
		
		MyQuery myQuery = new MyQuery();
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery(myQuery.makeInitQuery(problem, queryCase));
		ArrayList <String> dropDownButtonsArray = new ArrayList <String> ();
		
		while(result.next()) {
			
			if(!(dropDownButtonsArray.contains(result.getString(queryCase + "_ID")))) {
				dropDownButtonsArray.add(result.getString(queryCase + "_ID"));
			}
			
		}
		return dropDownButtonsArray;
		
	}
}
