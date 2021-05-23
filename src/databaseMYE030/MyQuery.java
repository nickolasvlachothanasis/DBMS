package databaseMYE030;

import java.util.ArrayList;
import java.util.Hashtable;

public class MyQuery {

	private String tablesNameToPrint, countryId;
	private ArrayList <String>  yearsId;
	
	public MyQuery(String tablesNameToPrint, String countryId, ArrayList <String> yearsId) {
		
		this.tablesNameToPrint = tablesNameToPrint;
		this.countryId = countryId;
		this.yearsId = yearsId;
	}
	
	public MyQuery() {
		
	}
	
	public String makeQuery() {
		
		String query= "";
		
		
		String multipleNamesForFrom = "";
		multipleNamesForFrom = tablesNameToPrint;
		Hashtable<String, Hashtable<String, String>> tablesDataWithCountryYears = new Hashtable<String,Hashtable<String, String>>();
		
		String multipleNamesForWhereCountry = "";
		String multipleNamesForWhereYears = "";
		multipleNamesForWhereCountry = "country_ID" + " = '"+ this.countryId +"' AND (";
		for(int k = 0; k < this.yearsId.size(); k++) {
			multipleNamesForWhereYears = multipleNamesForWhereYears + "year_ID" + " = '"+ this.yearsId.get(k) +"'";
			if (k < this.yearsId.size() - 1) {
				multipleNamesForWhereYears = multipleNamesForWhereYears + " OR ";
			}
		}
		multipleNamesForWhereCountry = multipleNamesForWhereCountry + multipleNamesForWhereYears + ")";
	
		query = "SELECT country_DATA FROM " + multipleNamesForFrom + " WHERE " + multipleNamesForWhereCountry;
	
		return query;
	}
	
	public String makeInitQuery(String problem, String queryCase) {
		
		String query= "";
		
		query = "SELECT " + queryCase + "_ID FROM MYE030." + problem;
		return query;
		
	}
	
}
