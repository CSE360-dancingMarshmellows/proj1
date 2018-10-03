//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class ViewPanel extends JPanel {
	private ArrayList<Task> taskList;
	private int tasks;
	private JPanel allTasks;
	private JPanel viewTasks;
	private JButton reset;

	public ViewPanel(ArrayList<Task> taskList) {
		this.taskList = taskList;
		tasks = 0;
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

	public int addTask(Task currTask) {
		int i = 0;
		while (i < tasks) {
			String currName = currTask.getName();
			String name = taskList.get(i).getName();
			if (currName.equals(name)) {
				return 2;
			}
			i++;
		}
		i = 0;
		boolean found;
		int deps = currTask.getDependency();
		while (i < deps) {
			found = false;
			int j = 0;
			while (j < tasks) {
				String dep = currTask.getDependencies(i);
				String name = taskList.get(j).getName();
				if (dep.equals(name)) {
					found = true;
				}
				j++;
			}
			if (found == false) {
				return 3;
			}
			i++;
		}
		taskList.add(currTask);
		tasks++;
		JLabel newTask = new JLabel(currTask.toString());
		allTasks.add(newTask);
		return 1;
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			/*taskList = null;
			remove(viewTasks);
			allTasks = null;*/
		}
	}
}
