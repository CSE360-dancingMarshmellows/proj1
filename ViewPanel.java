//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class ViewPanel extends JPanel {

	private int tasks;
	private int paths;
	private JPanel allPaths;
	private JPanel allTasks;
	private JPanel pathTasks;
	private JPanel viewPaths;
	private JButton reset;
	private ArrayList<JLabel> pathLabels;
	private ArrayList<JLabel> taskLabels;
	private ArrayList<Task> taskList;

	public ViewPanel(ArrayList<Task> taskList) {
		this.taskList = taskList;
		tasks = 0;
		paths = 0;
		setLayout(new BorderLayout());
		viewPaths = new JPanel(new GridLayout(1, 3));
		reset = new JButton("Clear Tasks");
		viewPaths.add(new JLabel(""));
		viewPaths.add(reset);
		viewPaths.add(new JLabel(""));
		reset.addActionListener(new ButtonListener());
		allPaths = new JPanel();
		allPaths.setLayout(new BoxLayout(allPaths, BoxLayout.Y_AXIS));
		allPaths.add(new JLabel("Paths:"));
		allTasks = new JPanel();
		allTasks.setLayout(new BoxLayout(allTasks, BoxLayout.Y_AXIS));
		allTasks.add(new JLabel("Tasks:"));
		pathTasks = new JPanel();
		pathTasks.setLayout(new BoxLayout(pathTasks, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(allPaths);
		JScrollPane scroll2 = new JScrollPane(allTasks);
		pathTasks.add(scroll);
		pathTasks.add(scroll2);
		add(pathTasks, BorderLayout.NORTH);
		add(viewPaths, BorderLayout.SOUTH);
		pathLabels = new ArrayList<JLabel>();
		taskLabels = new ArrayList<JLabel>();
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
		PathBuilder pathBuild = new PathBuilder(taskList);
		paths = pathBuild.getPaths();
		sortTasks();
		clearLabels();
	
		i = 0;
		while (i < paths) {
			String pathString = pathBuild.conString(i);
			JLabel pathLabel = new JLabel(pathString);
			pathLabels.add(pathLabel);
			i++;
		}
		i = 0;
		while (i < tasks) {
			String taskString = taskList.get(i).toString();
			JLabel taskLabel = new JLabel(taskString);
			taskLabels.add(taskLabel);
			i++;
		}
		
		updateLabels();
		return 1;
	}
	
	public void updateLabels() {
		int i = 0;
		while (i < paths) {
			allPaths.add(pathLabels.get(i));
			i++;
		}
		allPaths.repaint();
		i = 0;
		while (i < tasks) {
			allTasks.add(taskLabels.get(i));
			i++;
		}
		allTasks.repaint();
	}
	
	
	public void clearLabels() {
		int i = 0;
		while (i < pathLabels.size()) {
			allPaths.remove(pathLabels.get(i));
			i++;
		}
		i = 0;
		while (i < taskLabels.size()) {
			allTasks.remove(taskLabels.get(i));
			i++;
		}
		pathLabels = new ArrayList<JLabel>();
		taskLabels = new ArrayList<JLabel>();
		allPaths.revalidate();
		allTasks.revalidate();
		allPaths.repaint();
		allTasks.repaint();
	}
	
	public void sortTasks() {
		int n = tasks;
		int i = 1;
		while (i < n) {
			Task key = taskList.get(i);
			int j = i-1;
			while (j >= 0 && taskList.get(j).getDuration() < key.getDuration()) {
				taskList.set(j+1, taskList.get(j));
				j--;
			}
			taskList.set(j+1, key);
			i++;
		}
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			taskList = new ArrayList<Task>();
			clearLabels();
			tasks = 0;
			paths = 0;
		}
	}
}
