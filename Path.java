//CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Path {
	private int length;
	private int duration;
	private int name;
	private ArrayList<String> tasks;
	
	public Path(int name) {
		this.name = name;
		length = 0;
		duration = 0;
		tasks = new ArrayList<String>();
	}
	
	public int getLength() {
		return length;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getTasks(int index) {
		return tasks.get(index);
	}
	
	public int getTask() {
		return tasks.size();
	}
	
	public void setTaskList(ArrayList <String> soFar) {
		tasks = soFar;
	}
	
	public ArrayList<String> getTaskList() {
		return tasks;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setDuration(int duration) {
		this.duration = this.duration + duration;
	}
	
	public void setTasks(String name) {
		tasks.add(name);
	}
	
	public String toString() {
		String sum = "Path: ";
		int i = tasks.size()-1;
		while (i >= 0) {
			sum = sum + tasks.get(i) + " ";
			i--;
		}
		sum = sum + "     Length: " + length + "     " + " Duration: " + duration;
		return sum;
	}
}
