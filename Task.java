//CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Task {

	private String name;
	private int duration;
	private String [] dependencies = {};
	private int dependency;

	public Task() {
		name = new String("");
		duration = 0;
		dependency = 0;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}
	
	public int getDependency() {
		return dependency;
	}

	public String getDependencies(int index) {
		return dependencies[index];
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setDependencies(String [] dependencies, int dependency) {
		this.dependencies = dependencies;
		this.dependency = dependency;
	}
	
	public String toString() {
		int i = 0;
		String dep = "";
		while (i < dependency) {
			dep = dep + dependencies[i] + " "; 
			i++;
		}
		String result = "Task Name: " + name + "\nTask Duration: " + Integer.toString(duration) + "\nTask Dependencies: " + dep + "\n\n"; 
		return result;
	}
}
