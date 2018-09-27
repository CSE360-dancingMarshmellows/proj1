//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class AddPanel extends JPanel {
	private ArrayList<Task> taskList;
	private JButton addTask;
	private ViewPanel viewPanel;
	private JLabel errorMessage;
	private JTextField name, duration, dependencies;


	public AddPanel(ArrayList<Task> taskList, ViewPanel viewPanel) {
		this.taskList = taskList;
		this.viewPanel = viewPanel;
		
		JPanel createPanel = new JPanel(new BorderLayout());

		JPanel welcome = new JPanel(new GridLayout(1, 1));
		errorMessage = new JLabel("");
		welcome.add(new JLabel("Welcome to Task Scheduler"));
		welcome.add(errorMessage);

		JPanel fields = new JPanel(new GridLayout(10,1));
		JPanel labels = new JPanel(new GridLayout(10,1));

		name = new JTextField();
		duration = new JTextField();
		dependencies = new JTextField();

		labels.add(new JLabel("Enter Name of the Task"));
		fields.add(name);
		labels.add(new JLabel("Enter Duration of the Task"));
		fields.add(duration);
		labels.add(new JLabel("Enter any Dependencies the Task Has"));
		fields.add(dependencies);

		JPanel buttons = new JPanel(new GridLayout(1, 3));
		addTask = new JButton("Add Task");
		buttons.add(new JLabel(""));
		buttons.add(addTask);
		buttons.add(new JLabel(""));
		addTask.addActionListener(new ButtonListener());

		createPanel.add(welcome, BorderLayout.NORTH);
		createPanel.add(labels, BorderLayout.WEST);
		createPanel.add(fields, BorderLayout.EAST);
		createPanel.add(buttons, BorderLayout.SOUTH);

		setLayout(new BorderLayout());
		add(createPanel, BorderLayout.WEST);
	}


private class ButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		try {
			String taskName = name.getText();
			int taskDur = Integer.parseInt(duration.getText());
			String taskDep = dependencies.getText();

			if (taskName.length() == 0 || taskDur == 0) {
				Exception e = new Exception();
				throw(e);
			}

			if (taskDur < 0 ) {
				Exception nfe = new Exception();
				throw nfe;
			}

			else {
				Task task1 = new Task();
				task1.setName(taskName);
				task1.setDuration(taskDur);
				if (taskDep.length() != 0) {
					task1.setDependencies(taskDep);
				}
				taskList.add(task1);
			}
		}
		catch(NumberFormatException nfe) {
			errorMessage.setText("Please enter an integer for Task Duration.");
			errorMessage.setForeground(Color.red);
		}
		catch(Exception e) {
			e.printStackTrace();
			errorMessage.setText("Please enter both Task Name and Duration");
			errorMessage.setForeground(Color.red);
		}
	}
}
}
