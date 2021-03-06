//CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class AddPanel extends JPanel {
	private JButton addTask, processPaths, savePaths, resetTasks, updateTask, critical;
	private JLabel errorMessage;
	private JTextField name, duration, dependencies;
	private JPanel allPaths;
	private JPanel allTasks;
	private JPanel pathTasks;
	private JPanel viewPaths;
	private int tasks;
	private int paths;
	private ArrayList<JLabel> pathLabels;
	private ArrayList<JRadioButton> taskLabels;
	private ButtonGroup taskButtons;
	private ArrayList<Task> taskList;
	private ArrayList<Path> pathList;
	private int criticalPathDur;
	private ArrayList<String> pathInfo;
	private ArrayList<String> taskInfo;

	public AddPanel() {
		tasks = 0;
		paths = 0;
		criticalPathDur = 0;
		pathInfo = new ArrayList<String>();
		taskInfo = new ArrayList<String>();
		taskList = new ArrayList<Task>();
		pathList = new ArrayList<Path>();
		pathLabels = new ArrayList<JLabel>();
		taskLabels = new ArrayList<JRadioButton>();
		taskButtons = new ButtonGroup();
		
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
		
		JPanel outputButtons = new JPanel(new GridLayout(1,1));
		
		processPaths = new JButton("Process");
		processPaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clearLabels();
				updateTasks();
				updatePaths();
			}
		});
		
		savePaths = new JButton("Save");
		savePaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String fileName = (String)JOptionPane.showInputDialog(null, "Please enter a name for the report:", "Report File Name", JOptionPane.PLAIN_MESSAGE);
				if ((fileName != null) && (fileName.length() > 0)) {
					int i = 0;
					while (i < tasks) {
						taskInfo.add(taskList.get(i).toString());
						i++;
					}
					Write writeFile = new Write(pathInfo, taskInfo, fileName, tasks, paths);
					return;
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid file name.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		critical = new JButton("Critical");
		critical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = 0;
				clearLabels();
				updateTasks();
				criticalPaths();
			}
		});
		
		outputButtons.add(new JLabel(""));
		outputButtons.add(processPaths);
		outputButtons.add(new JLabel(""));
		outputButtons.add(critical);
		outputButtons.add(new JLabel(""));
		outputButtons.add(savePaths);
		outputButtons.add(new JLabel (""));
		
		processPanel.add(processTitle, BorderLayout.NORTH);
		processPanel.add(scroll, BorderLayout.WEST);
		processPanel.add(scroll2, BorderLayout.EAST);
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
		addTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Task currTask = new Task();
				taskAttempt(currTask);
			}
		});
		
		buttons.add(new JLabel(""));
		buttons.add(addTask);
		
		updateTask = new JButton("Update");
		updateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int i = 0;
				while (i < tasks) {
					if (name.getText().equals(taskList.get(i).getName())) {
						taskList.remove(i);
						tasks--;
						Task currentTask = new Task();
						taskAttempt(currentTask);
						updateTasks();
						return;
					}
					i++;
				}
			}
		});
		
		buttons.add(new JLabel(""));
		buttons.add(updateTask);
		buttons.add(new JLabel(""));

		resetTasks = new JButton("Reset");
		resetTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clearLabels();
				tasks = 0;
				paths = 0;
				taskList = new ArrayList<Task>();
			}
		});
		
		buttons.add(resetTasks);
		buttons.add(new JLabel(""));
		
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
	
	public void taskAttempt(Task currTask) {
		try {
			String taskName = name.getText().trim();
			String taskDep = dependencies.getText();
			
			if (taskName.length() == 0 || duration.getText().length() == 0) {
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
				int check = addTask(currTask);
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
		taskButtons = new ButtonGroup();
		
		while (i < tasks) {
			String taskString = taskList.get(i).toString();
			JRadioButton taskLabel = new JRadioButton(taskString);
			taskLabel.addActionListener(new taskSelect());
			taskLabels.add(taskLabel);
			taskButtons.add(taskLabel);
			i++;
		}
		i = 0;
		while (i < tasks) {
			allTasks.add(taskLabels.get(i));
			i++;
		}
		allTasks.repaint();
	}
	
	public void criticalPaths() {
		PathBuilder pathBuild = new PathBuilder(taskList);
		if (pathBuild.getCycle() == true) {
			JOptionPane.showMessageDialog(null,  "A task dependency creates a cyclical path. Please revise input.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		if (pathBuild.doesNotExist() == true) {
			JOptionPane.showMessageDialog(null,  "One or more task has a dependency that does not exist.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		if (pathBuild.getBroken() == true) {
			JOptionPane.showMessageDialog(null,  "One or more paths are broken or incomplete.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		paths = pathBuild.getPaths();
		int i = 0;
		int c = 0;
		while (i < paths) {
			if (i == 0 || pathBuild.getPath(i).getDuration() == criticalPathDur) {
				criticalPathDur = pathBuild.getPath(i).getDuration();
				JLabel criticalPath = new JLabel(pathBuild.getPath(i).toString());
				pathLabels.add(criticalPath);
				c++;
			}
			i++;
		}
		i = 0;
		while (i < c) {
			allPaths.add(pathLabels.get(i));
			i++;
		}
	}
	
	public void updatePaths() {
		pathInfo = new ArrayList<String>();
		PathBuilder pathBuild = new PathBuilder(taskList);
		if (pathBuild.getCycle() == true) {
			JOptionPane.showMessageDialog(null,  "A task dependency creates a cyclical path. Please revise input.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		if (pathBuild.doesNotExist() == true) {
			JOptionPane.showMessageDialog(null,  "One or more task has a dependency that does not exist.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		if (pathBuild.getBroken() == true) {
			JOptionPane.showMessageDialog(null,  "One or more paths are broken or incomplete.", "Input Error", JOptionPane.ERROR_MESSAGE);
		}
		paths = pathBuild.getPaths();
		pathList = pathBuild.getPathList();
		if (pathList.size() > 0) {
			criticalPathDur = pathList.get(0).getDuration();
		}
		int i = 0;
		while (i < paths) {
			JLabel pathLabel = new JLabel(pathBuild.getPath(i).toString());
			pathLabels.add(pathLabel);
			pathInfo.add(pathLabel.getText());
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
			taskButtons.remove(taskLabels.get(i));
			i++;
		}
		pathLabels = new ArrayList<JLabel>();
		taskLabels = new ArrayList<JRadioButton>();
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
			while (j >= 0 && taskList.get(j).getName().compareTo(key.getName()) >= 0) {
				taskList.set(j+1, taskList.get(j));
				j--;
			}
			taskList.set(j+1, key);
			i++;
		}
	}
	
	private class taskSelect implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JRadioButton source = (JRadioButton) e.getSource();
			int i = 0;
			while (i < tasks) {
				if (source.equals(taskLabels.get(i))) {
					name.setText(taskList.get(i).getName());
					duration.setText(Integer.toString(taskList.get(i).getDuration()));
					dependencies.setText(taskList.get(i).depToString());
					return;
				}
				i++;
			}
		}
	}
}
