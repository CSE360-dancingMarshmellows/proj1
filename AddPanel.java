//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class AddPanel extends JPanel {
	private JButton addTask;
	private ViewPanel viewPanel;
	private JLabel errorMessage;
	private JTextField name, duration, dependencies;

	public AddPanel(ViewPanel viewPanel) {
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
		labels.add(new JLabel("           Enter any Dependencies the Task Has"));
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
				else if (check == 3) {
					JOptionPane.showMessageDialog(null, "One or more task dependencies do not exist!", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (check == 4) {
					JOptionPane.showMessageDialog(null,  "A task dependency creates a cyclical path. Please revise input.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (check == 1) {
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
