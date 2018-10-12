//CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PathBuilder {
	private ArrayList<Task> taskList;
	private ArrayList<Path> pathList;
	private int tasks;
	private int paths;
	private ArrayList<String> ends;
	private Boolean cycle;
	private Boolean found;
	private Boolean doe;
	
	public PathBuilder(ArrayList<Task> taskList) {
		this.taskList = new ArrayList<Task>();
		this.taskList = taskList;
		this.pathList = new ArrayList<Path>();
		tasks = taskList.size();
		paths = 0;
		ends = new ArrayList<String>();
		cycle = false;
		found = true;
		doe = false;
		
		int i = 0;
		while(i < tasks) {
			int [] cycles = new int [taskList.size()];
			assessTask(taskList.get(i));
			checkCycles(taskList.get(i), cycles);
			i++;
		}
		
		if (found == false) {
			doe = true;
		}
		
		i = 0;
		int [] depScores = new int [taskList.size()];
		while (i < ends.size()) {
			int j = 0;
			while (j < tasks) {
				if (taskList.get(j).getName().equals(ends.get(i)) && taskList.get(j).getDependency() > 0) {
					Path currPath = new Path(paths);
					followPath(taskList.get(j), currPath, depScores);
				}
				j++;
			}
			i++;
		}
		
		sortPaths();
	}
	
	public void assessTask(Task currTask) {
		int splitScore = 0;
		int i = 0;
		while (i < tasks) {
			int j = 0;
			while (j < taskList.get(i).getDependency()) {
				if (currTask.getName().equals(taskList.get(i).getDependencies(j))) {
					splitScore++;
				}
				j++;
			}
			i++;
		}
		i = 0;
		while (i < currTask.getDependency()) {
			found = false;
			int j = 0;
			while (j < tasks) {
				if (currTask.getDependencies(i).equals(taskList.get(j).getName())) {
					found = true;
				}
				j++;
			}
			i++;
		}
		if (splitScore == 0) {
			ends.add(currTask.getName());
		}
	}
	
	public void checkCycles(Task currTask, int [] cycles) {
		if (currTask.getDependency() != 0) {
			int j = 0;
			while (j < currTask.getDependency()) {
				int k = 0;
				while (k < tasks) {
					if (currTask.getDependencies(j).equals(taskList.get(k).getName())) {
						cycles[k] = cycles[k] + 1;
						if (cycles[k] > 1) {
							cycle = true;
							return;
						}
						checkCycles(taskList.get(k), cycles);
					}
					k++;
				}
				j++;
			}
		}
		return;
	}
	
	
	public void followPath(Task currTask, Path currPath, int [] depScores) {
		if (currTask.getDependency() != 0) {
			int j = 0;
			while (j < currTask.getDependency()) {
				int k = 0;
				while (k < tasks) {
					if (currTask.getDependencies(j).equals(taskList.get(k).getName())) {
						// Check for cycles
						depScores[k] = depScores[k] + 1;
						if (depScores[k] > 1) {
							System.out.print("cycle");
							cycle = true;
							return;
						}
						if (j > 0) {
							// Additional path started for every dependency over 1
							Path newPath = new Path(paths);
							newPath.setDuration(currTask.getDuration());
							newPath.setTasks(currTask.getName());
							newPath.setLength(currPath.getLength() + 1);
							followPath(taskList.get(k), newPath, depScores);
						}
						else if (j == 0) {
							// Follow path up if first dependency
							currPath.setDuration(currTask.getDuration());
							currPath.setTasks(currTask.getName());
							currPath.setLength(currPath.getLength() + 1);
							followPath(taskList.get(k), currPath, depScores);
						}
					}
					k++;
				}
				j++;	
			}
		}
		// Base case, If dependencies = 0, a start is found that is not also an end
		else if (currTask.getDependency() == 0) {
			currPath.setDuration(currTask.getDuration());
			currPath.setLength(currPath.getLength() + 1);
			currPath.setTasks(currTask.getName());
			pathList.add(currPath);
			paths++;
			return;
		}
	}
	
	public void sortPaths() {
		int n = paths;
		int i = 1;
		while (i < n) {
			Path key = pathList.get(i);
			int j = i-1;
			while (j >= 0 && pathList.get(j).getDuration() < key.getDuration()) {
				pathList.set(j+1, pathList.get(j));
				j--;
			}
			pathList.set(j+1, key);
			i++;
		}
	}
	
	public int getPaths() {
		return paths;
	}
	
	public Path getPath(int index) {
		return pathList.get(index);
	}
	
	public Boolean getCycle() {
		if (cycle == true) {
			cycle = false;
			return true;
		}
		else {
			return false;
		}
	}

	public Boolean doesNotExist() {
		if (doe == true) {
			doe = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public String conString(int index) {
		return pathList.get(index).toString();
	}
	
	
}
