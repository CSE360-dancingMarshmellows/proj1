//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class ViewPanel extends JPanel {

	private int tasks;
	private int paths;
	private JPanel leftPanel; // will hold tasks on the left 
	private JPanel rightPanel; // will hold paths on the right
	private JPanel buttonPanel;
	private JPanel allPaths;
	private JPanel viewPaths;
	private JButton reset;
	private ArrayList<JLabel> pathLabels;
	private ArrayList<Task> taskList;
	private JTextArea taskInfo;
	private JLabel tasksLabel = new JLabel("Tasks");

	public ViewPanel(ArrayList<Task> taskList) {
		leftPanel = new JPanel(new BorderLayout());
		rightPanel = new JPanel();
		buttonPanel = new JPanel(new GridLayout(1, 3));
		
		leftPanel.add(tasksLabel, BorderLayout.NORTH);
		
		this.taskList = taskList;
		tasks = 0;
		paths = 0;
		setLayout(new BorderLayout());
		viewPaths = new JPanel(new GridLayout(1, 3));
		reset = new JButton("Clear Tasks");
		
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(reset);
		buttonPanel.add(new JLabel(""));
		reset.addActionListener(new ButtonListener());
		allPaths = new JPanel();
		allPaths.setLayout(new BoxLayout(allPaths, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(allPaths);
		rightPanel.add(scroll, BorderLayout.NORTH);
		rightPanel.add(viewPaths, BorderLayout.SOUTH);
		
		taskInfo = new JTextArea(15,30);
		taskInfo.setEditable(false);
		JScrollPane scroll_left = new JScrollPane(taskInfo);
		leftPanel.add(scroll_left);
		
		add(buttonPanel, BorderLayout.SOUTH);
		add(rightPanel, BorderLayout.EAST);
		add(leftPanel, BorderLayout.WEST);
		pathLabels = new ArrayList<JLabel>();
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
		clearLabels();
	
		i = 0;
		while (i < paths) {
			String pathString = pathBuild.conString(i);
			JLabel pathLabel = new JLabel(pathString);
			pathLabels.add(pathLabel);
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
	}
	
	public void clearLabels() {
		int i = 0;
		while (i < pathLabels.size()) {
			allPaths.remove(pathLabels.get(i));
			i++;
		}
		pathLabels = new ArrayList<JLabel>();
		allPaths.revalidate();
		allPaths.repaint();
	}
	public void printTasks() {
		String s = "";
		for(int i = 0; i < taskList.size(); i++) {
			s += taskList.get(i).toString();
		}
		taskInfo.setText(s);
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			taskList = new ArrayList<Task>();
			clearLabels();
			taskInfo.setText("");
			tasks = 0;
			paths = 0;
			pathLabels = new ArrayList<JLabel>();
		}
	}
}
