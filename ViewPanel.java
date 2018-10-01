
//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class ViewPanel extends JPanel {
	private ArrayList<Task> taskList;
	private JPanel allTasks;
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

		allTasks = new JPanel();
		allTasks.setLayout(new BoxLayout(allTasks, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(allTasks);
		add(scroll, BorderLayout.NORTH);
		add(viewTasks, BorderLayout.SOUTH);
	}

	public void addTask(Task currTask) {
		JLabel newTask = new JLabel(currTask.toString());
		allTasks.add(newTask);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Checks to see if there are any tasks and deletes them if there are
			// Clears the text area
		}
	}
}
