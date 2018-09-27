//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class ViewPanel extends JPanel {
	private ArrayList<Task> taskList;
	private JTextArea allTasks;
	private JPanel viewTasks;
	private JButton reset;

	public ViewPanel(ArrayList<Task> taskList) {
		this.taskList = taskList;
		
		setLayout(new BorderLayout());

		viewTasks = new JPanel(new GridLayout(1, 3));
		reset = new JButton("Clear Tasks");
		viewTasks.add(new JLabel(""));
		viewTasks.add(reset);
		viewTasks.add(new JLabel(""));
		reset.addActionListener(new ButtonListener());

		allTasks = new JTextArea(15,30);
		allTasks.setEditable(false);
		// Tasks are listed as strings in text area
		JScrollPane scroll = new JScrollPane(allTasks);
		add(scroll, BorderLayout.NORTH);
		add(viewTasks, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Checks to see if there are any tasks and deletes them if there are
			// Clears the text area
		}
	}
}
