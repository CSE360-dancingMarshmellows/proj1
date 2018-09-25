// CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Task {

	private String name;
	private Int duration;
	private Int dependency;
	private String[] dependencies;

	public Task (String name; Int duration) {
		this.name = name;
		this.duration = duration;
	}

	public Task (String name; Int duration, Int dependency, String[] dependencies) {
		this.name = name;
		this.duration = duration;
		this.dependency = dependency;
		this.dependencies = dependencies;
	}

	public String getName() {
		return name;
	}

	public String getDuration() {
		return String(duration);
	}

	public String getDependencies(Int index) {
		return dependencies[index];
	}
}
