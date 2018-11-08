//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class AddPanel extends JPanel {
	private JButton addTask, processPaths, savePaths, resetTasks, updateTask;
	private ViewPanel viewPanel;
	private JLabel errorMessage;
	private JTextField name, duration, dependencies;

	public AddPanel(ViewPanel viewPanel) {
		this.viewPanel = viewPanel;
		JPanel wholePanel = new JPanel(new BorderLayout());
		
		JPanel createPanel = new JPanel(new BorderLayout());
		JPanel processPanel = new JPanel(new BorderLayout());
		
		JPanel welcome = new JPanel(new GridLayout(2, 1));
		errorMessage = new JLabel("");
		welcome.add(new JLabel("Welcome to Task Scheduler"));
		welcome.add(new JLabel(""));
		welcome.add(errorMessage);

		JPanel addTitle = new JPanel(new GridLayout(2, 1));
		addTitle.add(new JLabel("Create or Modify Tasks"));
		
		JPanel processTitle = new JPanel(new GridLayout(2, 1));
		processTitle.add(new JLabel("Process and Save Paths"));
		
		JPanel taskOutput = new JPanel(new GridLayout(10, 1));
		taskOutput.add(new JLabel("Tasks:"));
		JPanel pathOutput = new JPanel(new GridLayout(10, 1));
		pathOutput.add(new JLabel("Paths:"));
		JPanel outputButtons = new JPanel(new GridLayout(1, 5));
		processPaths = new JButton("Process");
		savePaths = new JButton("Save");
		resetTasks = new JButton("Reset");
		outputButtons.add(new JLabel(""));
		outputButtons.add(processPaths);
		outputButtons.add(new JLabel(""));
		outputButtons.add(savePaths);
		outputButtons.add(new JLabel (""));
		outputButtons.add(resetTasks);
		outputButtons.add(new JLabel(""));
		
		processPanel.add(processTitle, BorderLayout.NORTH);
		processPanel.add(taskOutput, BorderLayout.WEST);
		processPanel.add(pathOutput, BorderLayout.EAST);
		processPanel.add(outputButtons, BorderLayout.SOUTH);
		
		JPanel fields = new JPanel(new GridLayout(8,1));
		JPanel labels = new JPanel(new GridLayout(8,1));

		name = new JTextField(10);
		duration = new JTextField(10);
		dependencies = new JTextField(10);

		labels.add(new JLabel("Enter Name of Task"));
		fields.add(name);
		labels.add(new JLabel("Enter Duration"));
		fields.add(duration);
		labels.add(new JLabel("Enter any Dependencies"));
		fields.add(dependencies);

		JPanel buttons = new JPanel(new GridLayout(1, 1));
		addTask = new JButton("Add Task");
		buttons.add(new JLabel(""));
		buttons.add(addTask);
		updateTask = new JButton("Update");
		buttons.add(new JLabel(""));
		buttons.add(updateTask);
		buttons.add(new JLabel(""));
		addTask.addActionListener(new ButtonListener());

		createPanel.add(addTitle, BorderLayout.NORTH);
		createPanel.add(labels, BorderLayout.WEST);
		createPanel.add(fields, BorderLayout.EAST);
		createPanel.add(buttons, BorderLayout.SOUTH);
		
		wholePanel.add(welcome, BorderLayout.NORTH);
		wholePanel.add(createPanel, BorderLayout.WEST);
		wholePanel.add(processPanel, BorderLayout.EAST);
		
		setLayout(new BorderLayout());
		add(wholePanel, BorderLayout.WEST);
	}

private class ButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		Task currTask = new Task();
		try {
			String taskName = name.getText().trim();
			String taskDep = dependencies.getText();
			
			if (taskName.length() == 0 || duration.getText().length() == 0) {
				System.out.println("check");
				Exception e = new Exception();
				throw e;
			}

			int taskDur = Integer.parseInt(duration.getText());

			if (taskDur <= 0 ) {
				
				NumberFormatException nfe = new NumberFormatException();
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
						deps[dep] = st.nextToken().trim();
						dep++;
						
					}
					currTask.setDependencies(deps, dep);

				}
				dependencies.setText("");
				int check = viewPanel.addTask(currTask);
				if (check == 2) {
					JOptionPane.showMessageDialog(null, "This task name already exists!", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (check == 1){
					errorMessage.setText("");
				}
			}
		}
		
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Please enter a positive integer for Task Duration!", "Input Error", JOptionPane.ERROR_MESSAGE);
			
			duration.setText("");
			dependencies.setText("");
		}
		
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter both Task Name and Duration!", "Input Error", JOptionPane.ERROR_MESSAGE);
			name.setText("");
			duration.setText("");
			dependencies.setText("");
		}
	}
}
}
