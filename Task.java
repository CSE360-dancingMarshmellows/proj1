//CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Task {

	private String name;
	private int duration;
	private String dependencies;

	/*public Task(String name; int duration) {
		this.name = name;
		this.duration = duration;
	}*/

	public Task() {
		name = new String("");
		duration = 0;
		dependencies = new String ("");
		//this.name = name;
		//this.duration = duration;
		//this.dependency = dependency;
		//this.dependencies = dependencies;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public String getDependencies() {
		return dependencies;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setDependencies(String dependencies) {
		this.dependencies = dependencies;
	}
	
	public String toString() {
		String result = "Task Name: " + name + "    Task Duration: " + Integer.toString(duration) + "    Task Dependencies: " + dependencies; 
		return result;
	}
}
