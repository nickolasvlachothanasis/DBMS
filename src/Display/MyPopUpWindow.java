package Display;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class MyPopUpWindow {

	private String [] countries;
	
	public MyPopUpWindow(String [] countries) {
		this.countries = countries; 
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(85, 85, 50, 85));
		panel.setLayout(new GridLayout(0, 1));
		
		JList message = new JList(this.countries);
		message.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		message.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		message.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(message);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		
		panel.add(message);
		
		frame.add(panel);
		frame.setSize(720, 350);
		frame.setTitle("Options For Countries"); 
		frame.setVisible(true);
	}
	
}
