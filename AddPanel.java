
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
		welcome.add(new JLabel("                                                                         Welcome to Task Scheduler"));
		welcome.add(errorMessage);

		JPanel fields = new JPanel(new GridLayout(10,1));
		JPanel labels = new JPanel(new GridLayout(10,1));

		name = new JTextField(30);
		duration = new JTextField(30);
		dependencies = new JTextField(30);

		labels.add(new JLabel("           Enter Name of the Task"));
		fields.add(name);
		labels.add(new JLabel("           Enter Duration of the Task"));
		fields.add(duration);
		labels.add(new JLabel("           Enter any Dependencies the Task Has                "));
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
		Task currTask = new Task();
		try {
			String taskName = name.getText();
			int taskDur = Integer.parseInt(duration.getText());
			String taskDep = dependencies.getText();
			
			if (taskName.length() == 0 || taskDur == 0) {
				Exception e = new Exception();
				throw e;
			}

			if (taskDur < 0 ) {
				Exception nfe = new Exception();
				throw nfe;
			}

			else {
				currTask.setName(taskName);
				name.setText("");
				currTask.setDuration(taskDur);
				duration.setText("");
				if (taskDep.length() != 0) {
					StringTokenizer st = new StringTokenizer(taskDep, " ");
					int dep = 1;
					String[] deps = new String[15];
					deps[0] = st.nextToken();
					while(st.hasMoreTokens()) {
						deps[dep] = st.nextToken();
						dep++;
					}
					currTask.setDependencies(deps, dep);

				}
				dependencies.setText("");
				taskList.add(currTask);
				viewPanel.addTask(currTask);
				errorMessage.setText("");
			}
		}
		catch(NumberFormatException nfe) {
			errorMessage.setText("           Please enter an integer for Task Duration.");
			errorMessage.setForeground(Color.red);
		}
		catch(Exception e) {
			errorMessage.setText("           Please enter both Task Name and Duration");
			errorMessage.setForeground(Color.red);
		}
	}
}
}
