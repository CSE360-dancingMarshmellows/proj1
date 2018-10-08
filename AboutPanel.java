import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class AboutPanel extends JPanel{
	private JScrollPane aboutScroll;
	private JTextArea aboutPanel;
	
	public AboutPanel() {
		aboutPanel = new JTextArea(15,70);
		aboutPanel.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(aboutPanel);
		add(scroll);
		
		String str = "Overview:\n\n";
		
		str += "   This program is designed to help in the process of task scheduling. The program works by taking several tasks, their duration \n"
				+ "(which must be an integer) and the possible dependencies of each task as input. It then creates an array of structures for each \n"
				+ "path containing task names, duration, and dependencies and sorts the paths in decreasing order with respect to their duration. \n"
				+ "Each path will then be printed as output with the involved task names and its duration time formatted for users to easily read. \n\n" + 
				"   This program will be able to handle input errors such as incorrect input types, missing input types, or anything else that may \n"
				+ "arise, and the program will wait to process the input data until all of the information has been received. An assumption is made for \n"
				+ "this phase of the program that any starting task, or tasks, will not have dependencies. \n\n";
		
		//about each group member
		str += "Team member:\n\n";
		
		//Sarah
		str += "\n\n";
		
		//Adrian
		str += "My name is Adrian Luna, I'm a computer science major. For this software, my contributitions were mostly front end. Meaning I\n"
				+ "took care of how the program is presented. I formatted the GUI, and organized the back end code according to the requirements.\n"
				+ "I contributed towards the design and layout of the final project, and helped out on the back end code.\n\n";
		
		//Tyler
		str += "\n\n";
		
		//Will
		str += "\n\n";
		
		aboutPanel.setText(str);
	}
}

