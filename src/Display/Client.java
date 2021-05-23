package Display;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;


import Export.MyLineChart;
import Export.WriteTableToTxt;
import databaseMYE030.DatabaseFunctions;

public class Client implements ActionListener {
	
	 private String[] sqlTables = {
			"hapiscoreWhr",
			"hdiHumanDevelopmentIndex",
			"naturalGasProductionPerPerson",
			"naturalGasProductionTotal",
			"naturalGasProvenReservesperPerson",
			"nuclearPowerGenerationPerPerson",
			"nuclearPowerGenerationTotal",
			"oilConsumptionPerCap",
			"oilConsumptionTotal",
			"oilProductionPerPerson",
			"oilProductionTotal",
			"populationTotalpopulationTotal",
			"siPovGini"};
	
	private String [] sqlCountryTables = {"Afghanistan"
			, "Albania"
			, "Algeria"
			, "Angola"
			, "Argentina"
			, "Armenia"
			, "Australia"
			, "Austria"
			, "Azerbaijan"
			, "Bahrain"
			, "Bangladesh"
			, "Belarus"
			, "Belgium"
			, "Belize"
			, "Benin"
			, "Bhutan"
			, "Bolivia"
			, "Bosnia and Herzegovina"
			, "Botswana"
			, "Brazil"
			, "Bulgaria"
			, "Burkina Faso"
			, "Burundi"
			, "Cambodia"
			, "Cameroon"
			, "Canada"
			, "Central African Republic"
			, "Chad"
			, "Chile"
			, "China"
			, "Colombia"
			, "Comoros"
			, "Congo, Dem. Rep."
			, "Congo, Rep."
			, "Costa Rica"
			, "Cote d'Ivoire"
			, "Croatia"
			, "Cuba"
			, "Cyprus"
			, "Czech Republic"
			, "Denmark"
			, "Djibouti"
			, "Dominican Republic"
			, "Ecuador"
			, "Egypt"
			, "El Salvador"
			, "Estonia"
			, "Eswatini"
			, "Ethiopia"
			, "Finland"
			, "France"
			, "Gabon"
			, "Gambia"
			, "Georgia"
			, "Germany"
			, "Ghana"
			, "Greece"
			, "Guatemala"
			, "Guinea"
			, "Guyana"
			, "Haiti"
			, "Honduras"
			, "Hungary"
			, "Iceland"
			, "India"
			, "Indonesia"
			, "Iran"
			, "Iraq"
			, "Ireland"
			, "Israel"
			, "Italy"
			, "Jamaica"
			, "Japan"
			, "Jordan"
			, "Kazakhstan"
			, "Kenya"
			, "Kuwait"
			, "Kyrgyz Republic"
			, "Lao"
			, "Latvia"
			, "Lebanon"
			, "Lesotho"
			, "Liberia"
			, "Libya"
			, "Lithuania"
			, "Luxembourg"
			, "Madagascar"
			, "Malawi"
			, "Malaysia"
			, "Maldives"
			, "Mali"
			, "Malta"
			, "Mauritania"
			, "Mauritius"
			, "Mexico"
			, "Moldova"
			, "Mongolia"
			, "Montenegro"
			, "Morocco"
			, "Mozambique"
			, "Myanmar"
			, "Namibia"
			, "Nepal"
			, "Netherlands"
			, "New Zealand"
			, "Nicaragua"
			, "Niger"
			, "Nigeria"
			, "North Macedonia"
			, "Norway"
			, "Oman"
			, "Pakistan"
			, "Palestine"
			, "Panama"
			, "Paraguay"
			, "Peru"
			, "Philippines"
			, "Poland"
			, "Portugal"
			, "Qatar"
			, "Romania"
			, "Russia"
			, "Rwanda"
			, "Saudi Arabia"
			, "Senegal"
			, "Serbia"
			, "Sierra Leone"
			, "Singapore"
			, "Slovak Republic"
			, "Slovenia"
			, "Somalia"
			, "South Africa"
			, "South Korea"
			, "South Sudan"
			, "Spain"
			, "Sri Lanka"
			, "Sudan"
			, "Suriname"
			, "Sweden"
			, "Switzerland"
			, "Syria"
			, "Tajikistan"
			, "Tanzania"
			, "Thailand"
			, "Togo"
			, "Trinidad and Tobago"
			, "Tunisia"
			, "Turkey"
			, "Turkmenistan"
			, "Uganda"
			, "Ukraine"
			, "United Arab Emirates"
			, "United Kingdom"
			, "United States"
			, "Uruguay"
			, "Uzbekistan"
			, "Venezuela"
			, "Vietnam"
			, "Yemen"
			, "Zambia"
			, "Zimbabwe"
			, "Andorra"
			, "Antigua and Barbuda"
			, "Bahamas"
			, "Barbados"
			, "Brunei"
			, "Cape Verde"
			, "Dominica"
			, "Equatorial Guinea"
			, "Eritrea"
			, "Fiji"
			, "Grenada"
			, "Guinea-Bissau"
			, "Kiribati"
			, "Liechtenstein"
			, "Marshall Islands"
			, "Micronesia, Fed. Sts."
			, "Palau"
			, "Papua New Guinea"
			, "Samoa"
			, "Sao Tome and Principe"
			, "Seychelles"
			, "Solomon Islands"
			, "St. Kitts and Nevis"
			, "St. Lucia"
			, "St. Vincent and the Grenadines"
			, "Timor-Leste"
			, "Tonga"
			, "Vanuatu"
			, "North Korea"
			, "Holy See"
			, "Monaco"
			, "Nauru"
			,"San Marino",
			"Tuvalu"};	
	
	private String [] countriesForEachProblem =  new String [sqlCountryTables.length];
	private String [] yearsForEachProblem = new String[300];
	
	private Hashtable<String, Hashtable<String, Hashtable<String, String>>> tablesData ;
	
	private ArrayList<String> problems = new ArrayList<String>();
	private ArrayList<String> year = new ArrayList<String>();
	private ArrayList<String> country = new ArrayList<String>();
	
	private JComboBox cmbList = new JComboBox(sqlTables);
	private JComboBox cmbCountryList = new JComboBox(sqlCountryTables);
	private JLabel tablesText = new JLabel("Number of selected problems: 0");
	private JButton button = new JButton("Ask a Question");
	private JLabel yearLabel = new JLabel("Year/s from 1800 - 2100 | Selected Years: 0");
	private JTextField yearText = new JTextField(20);
	private JLabel countryLabel = new JLabel("Country/ies | Selected Countries: 0");
	private JButton exportButton = new JButton("Export");
	private JButton optionsForCountries = new JButton("Options For Countries");

	private JPanel panel = new JPanel();
	private JFrame frame = new JFrame();
	
	private int countTables = 0;
	private int countYear = 0;
	private int countCountry = 0;
	
	public Client() {
		
		
		this.button.setBounds(10, 20, 80, 25);
		this.button.addActionListener(this);
		
		this.tablesData = new Hashtable<String, Hashtable<String, Hashtable<String, String>>>();
		
		this.panel.setBorder(BorderFactory.createEmptyBorder(85, 85, 50, 85));
		this.panel.setLayout(new GridLayout(0, 1));
		this.panel.add(button);
		this.panel.add(tablesText);
		
		
		this.cmbList.setSelectedIndex(0);
		this.cmbList.setBounds(10, 50, 80, 25);
		this.cmbList.addActionListener(this);
		this.panel.add(cmbList);
		
		this.tablesText.setBounds(100, 50, 165, 25);
		
		this.panel.add(yearLabel);
		this.panel.add(yearText);
		
		this.yearText.addActionListener(this);
		
		panel.add(countryLabel);
		
		this.cmbCountryList.setSelectedIndex(0);
		this.cmbCountryList.setBounds(10, 50, 80, 25);
		this.cmbCountryList.addActionListener(this);
		this.panel.add(cmbCountryList);
		
		this.optionsForCountries.setBounds(10, 20, 80, 25);
		this.optionsForCountries.addActionListener(this);
		this.panel.add(this.optionsForCountries);
		
		this.exportButton.setBounds(10, 20, 80, 25);
		this.panel.add(exportButton);
		this.exportButton.addActionListener(this);
		
		this.frame.add(this.panel);
		this.frame.setSize(720, 350);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle("Ask a question"); 
		this.frame.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {	
		new Client();
		
	} 

	@Override
	public void actionPerformed(ActionEvent event) {
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		if(event.getSource() == cmbList) {
			JComboBox cb = (JComboBox)event.getSource();
			String sqlTable = (String)cb.getSelectedItem();
			
			if(Arrays.stream(this.sqlTables).anyMatch(sqlTable :: equals)) {
				this.problems.add(sqlTable);
				this.countTables ++;
				setCountryAndYearTableForProblem(sqlTable);
			}
			
		}
		
		if(event.getSource() == cmbCountryList) {
			JComboBox cbCountries = (JComboBox)event.getSource();
			String sqlCountryItem = (String)cbCountries.getSelectedItem();
			if(Arrays.stream(this.countriesForEachProblem).anyMatch(sqlCountryItem :: equals)) {
				this.country.add(getPosition(sqlCountryItem));
				this.countCountry ++;
			}
		}
		
		if(event.getSource() == button && problems.size() > 0 && year.size() > 0 && country.size() > 0) {
			
			try {
				this.tablesData = dbFunctions.getTablesData(problems, country, year);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(event.getSource() == exportButton && this.tablesData.size() != 0) {
			ExportWindow newWindow =  new ExportWindow( this.tablesData, this.sqlCountryTables, this.problems.size());
		}
		
		if(event.getSource() == yearText) {
			if(Arrays.stream(this.yearsForEachProblem).anyMatch(yearText.getText() :: equals)) {
				countYear ++;
				year.add(yearText.getText());
				yearLabel.setText(this.yearLabel.getText().substring(0, this.yearLabel.getText().length() - 1)+ countYear);				
			}else {
				System.out.println("A date within the limits must be selected OR a problem must be selected");
			}
		}
		
		if(event.getSource() == optionsForCountries) {
			if(this.problems.size() != 0) {
				MyPopUpWindow ppWindow = new MyPopUpWindow(this.countriesForEachProblem);
			}else {
				System.out.println("Please Select a Problem/s");
			}
		}
		
		
		tablesText.setText("Number of selected problems: " + this.countTables);
		countryLabel.setText("Country/ies | Selected Countries: " + this.countCountry);
		
	}
	
	public String getPosition(String item) {
		for(int i = 0; i < this.sqlCountryTables.length; i++) {
			if(item == sqlCountryTables[i]) {
				return "" + i;
			}
		}
		return "";
	}
	
	public void setCountryAndYearTableForProblem(String problem) {
		ArrayList <String> countryPositionsThatIKeep = new ArrayList <String> ();
		ArrayList <String> yearPositionsThatIKeep = new ArrayList <String> ();
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		
		try {
			countryPositionsThatIKeep = dbFunctions.fillDropDownButtonsWithData(problem, "country");
			yearPositionsThatIKeep = dbFunctions.fillDropDownButtonsWithData(problem, "year");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.yearLabel.setText("Year/s from " + yearPositionsThatIKeep.get(0) + " - " + yearPositionsThatIKeep.get(yearPositionsThatIKeep.size()-1) + " | Selected Years: ");
		//TODO make a pop up window that tells how many years we have
		
		setCountrysDropDownButtonsTable(countryPositionsThatIKeep);
		setYearsDropDownButtonsTable(yearPositionsThatIKeep);
	}
	
	public void setCountrysDropDownButtonsTable(ArrayList <String> countryPositionsThatIKeep) {
		if(this.problems.size() == 1) {
			for(int i = 0; i < countryPositionsThatIKeep.size(); i++) {
				this.countriesForEachProblem[i] = this.sqlCountryTables[Integer.parseInt(countryPositionsThatIKeep.get(i))];
			}
		}else {
			String[] tempArray = new String[countryPositionsThatIKeep.size()];
			for(int i = 0; i < countryPositionsThatIKeep.size(); i++) {
				if(Arrays.stream(this.countriesForEachProblem).anyMatch(this.sqlCountryTables[Integer.parseInt(countryPositionsThatIKeep.get(i))] :: equals)) {
					tempArray[i] = this.sqlCountryTables[Integer.parseInt(countryPositionsThatIKeep.get(i))];
				}
			}
			this.countriesForEachProblem =  new String [tempArray.length];
			for(int i = 0; i < countryPositionsThatIKeep.size(); i++) {
				this.countriesForEachProblem[i] = tempArray[i];
			}
		}
		
	}
	
	public void setYearsDropDownButtonsTable(ArrayList <String> yearPositionsThatIKeep) {
		for(int i = 0; i < yearPositionsThatIKeep.size(); i++) {
			this.yearsForEachProblem[i] = yearPositionsThatIKeep.get(i);
		}
	}



}
