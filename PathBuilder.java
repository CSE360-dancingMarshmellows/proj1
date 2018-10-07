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
	
	public PathBuilder(ArrayList<Task> taskList) {
		this.taskList = new ArrayList<Task>();
		this.taskList = taskList;
		this.pathList = new ArrayList<Path>();
		tasks = taskList.size();
		paths = 0;
		ends = new ArrayList<String>();

		int i = 0;
		while(i < tasks) {
			assessTask(taskList.get(i));
			i++;
		}
		i = 0;
		while (i < ends.size()) {
			int j = 0;
			while (j < tasks) {
				if (taskList.get(j).getName().equals(ends.get(i)) && taskList.get(j).getDependency() > 0) {
					Path currPath = new Path(paths);
					followPath(taskList.get(j), currPath);
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
		if (splitScore == 0) {
			ends.add(currTask.getName());
		}
	}
	
	public void followPath(Task currTask, Path currPath) {
		if (currTask.getDependency() != 0) {
			int j = 0;
			while (j < currTask.getDependency()) {
				int k = 0;
				while (k < tasks) {
					if (currTask.getDependencies(j).equals(taskList.get(k).getName())) {
						if (j > 0) {
							// Additional path started for every dependency over 1
							Path newPath = new Path(paths);
							newPath.setDuration(currTask.getDuration());
							newPath.setTasks(currTask.getName());
							newPath.setLength(currPath.getLength() + 1);
							followPath(taskList.get(k), newPath);
						}
						else if (j == 0) {
							// Follow path up if first dependency
							currPath.setDuration(currTask.getDuration());
							currPath.setTasks(currTask.getName());
							currPath.setLength(currPath.getLength() + 1);
							followPath(taskList.get(k), currPath);
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
		int n = paths-1;
		int i = 0;
		while (i < n) {
			int max = i;
			int j = i+1;
			while (j < paths) {
				if (pathList.get(j).getDuration() > pathList.get(max).getDuration()) {
					max = j;
				}
				Path temp = pathList.get(max);
				pathList.set(max, pathList.get(i));
				pathList.set(i, temp);
				j++;
			}
			i++;
		}
	}
	
	public int getPaths() {
		return paths;
	}
	
	
	public String conString(int index) {
		return pathList.get(index).toString();
	}
	
	
}
