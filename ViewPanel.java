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
	private JButton process;
	private ArrayList<JLabel> pathLabels;
	private ArrayList<JLabel> taskLabels;
	private ArrayList<Task> taskList;

	public ViewPanel(ArrayList<Task> myTasks) {
		this.taskList = myTasks;
		tasks = 0;
		paths = 0;
		setLayout(new BorderLayout());
		viewPaths = new JPanel(new GridLayout(1, 3));
		reset = new JButton("Clear Tasks");
		process = new JButton("Process Paths");
		viewPaths.add(reset);
		viewPaths.add(new JLabel(""));
		viewPaths.add(process);
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clearLabels();
				tasks = 0;
				paths = 0;
				taskList = new ArrayList<Task>();
			}
		});
		
		process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updatePaths();
				pathTasks.revalidate();
				pathTasks.repaint();
			}
		});
		
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
		taskList.add(currTask);
		tasks++;
		sortTasks();
		clearLabels();		
		updateTasks();
		return 1;
	}
	
	public void updateTasks() {
		int i = 0;
		while (i < tasks) {
			String taskString = taskList.get(i).toString();
			JLabel taskLabel = new JLabel(taskString);
			taskLabels.add(taskLabel);
			i++;
		}
		i = 0;
		while (i < tasks) {
			allTasks.add(taskLabels.get(i));
			i++;
		}
		allTasks.repaint();
	}
	
	public void updatePaths() {
		PathBuilder pathBuild = new PathBuilder(taskList);
		if (pathBuild.getCycle() == true) {
			JOptionPane.showMessageDialog(null,  "A task dependency creates a cyclical path. Please revise input.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		if (pathBuild.doesNotExist() == true) {
			JOptionPane.showMessageDialog(null,  "One or more task has a dependency that does not exist.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		paths = pathBuild.getPaths();
		int i = 0;
		while (i < paths) {
			JLabel pathLabel = new JLabel(pathBuild.getPath(i).toString());
			pathLabels.add(pathLabel);
			i++;
		}
		i = 0;
		while (i < paths) {
			allPaths.add(pathLabels.get(i));
			i++;
		}
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
}
