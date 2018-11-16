// CSE 360 Fall 2018
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
				+ "Each path will then be printed as output with the involved task names and its duration time formatted for users to easily read. \n"
				+ "Once tasks have been added, the user may select and update them at any time or save all displayed information in a report file.\n\n" + 
				"   This program will be able to handle input errors such as incorrect input types, missing input types, or anything else that may \n"
				+ "arise, and the program will wait to process the input data until all of the information has been received. An assumption is made for \n"
				+ "this phase of the program that any starting task, or tasks, will not have dependencies. \n\n";
		
		//about each group member
		str += "Team member:\n\n";
		
		//Sarah
		str += "I am Sarah Bulle, a student currently majoring in Digital Culture with a focus in Media Processing. For this software, I coded\n"
				+ "the logical operations of the software, such as the collection and processing of user input, throwing and catching of errors,\n"
				+ "the construction of tasks and paths and their generation into output.\n\n";
		
		//Adrian
		str += "My name is Adrian Luna, I'm a computer science major. For this software, my contributitions were mostly front end. Meaning I\n"
				+ "took care of how the program is presented. I formatted the GUI, and organized the back end code according to the requirements.\n"
				+ "I contributed towards the design and layout of the final project, and helped out on the back end code.\n\n";
		
		//Tyler
		str += "My name is Tyler Martin and I am a Computer Science major. For this software I mainly assisted in logistical planning\n"
				+ "and helped tweak the design in order to better satisfy the project requirements. I also took into consideration factors such as \n"
				+ "readability in order to make the software more user-friendly.\n\n";
		
		//Will
		str += "My name is William Topping and I am a Computer Science major. For this software I mostly assisted in the editing and the layout\n"
				+ "to ensure readability and to satisfy requirements. I also performed some final tests to make sure the software is user-friendly\n"
				+ "and is properly functioning according to the user documentation.\n\n";
		
		aboutPanel.setText(str);
	}
}

