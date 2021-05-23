package Display;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Export.MyBarChart;
import Export.MyLineChart;
import Export.MyScatterPlot;
import Export.WriteTableToTxt;

public class ExportWindow implements ActionListener{
	private String exportBy = "";
	private String [] sqlCountryTable;
	
	private JButton exportTimeLine = new JButton("Export To Time Line");
	private JButton exportBarCharts = new JButton("Export to Bar Charts");
	private JButton exportScatterPlot = new JButton("Export Scatter Plots");
	private int problems;
	
	private Hashtable<String, Hashtable<String, Hashtable<String, String>>> tablesData ;
	
	public ExportWindow( Hashtable<String, Hashtable<String, Hashtable<String, String>>> tablesData, String [] sqlCountryTable, int problems) {
		
		this.sqlCountryTable = sqlCountryTable;
		this.tablesData = tablesData;
		this.problems = problems;
		
		WriteTableToTxt txtFile = new WriteTableToTxt(this.tablesData, this.exportBy, this.sqlCountryTable);
		txtFile.writeToTxt();
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(85, 85, 50, 85));
		
		exportTimeLine.setBounds(10, 20, 80, 25);
		panel.add(exportTimeLine);
		exportTimeLine.addActionListener(this);
		
		exportBarCharts.setBounds(10, 20, 80, 25);
		panel.add(exportBarCharts);
		exportBarCharts.addActionListener(this);
		
		exportScatterPlot.setBounds(10, 20, 80, 25);
		panel.add(exportScatterPlot);
		exportScatterPlot.addActionListener(this);
		
		frame.add(panel);
		frame.setSize(720, 350);
		frame.setTitle("Ask a question"); 
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent event) {		
		if(event.getSource() == exportTimeLine ) {
			MyLineChart lineChart = new MyLineChart();	
			lineChart.doExport();
		}
		
		if(event.getSource() == exportBarCharts) {
			MyBarChart barChart = new MyBarChart();	
			barChart.doExport();
		}
		
		if(event.getSource() == exportScatterPlot &&  problems == 2) {
			MyScatterPlot scatterPlot = new MyScatterPlot();
			scatterPlot.doExport();
		}
		
	}
	
	
	
	
}
