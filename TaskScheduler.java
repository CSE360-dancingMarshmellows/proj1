// CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskScheduler extends JApplet {
	// Code for making GUI Java Applet
	private JTabbedPane tPane;
   	private AddPanel addPanel;
   	private ViewPanel viewPanel;
	private ArrayList taskList;

	public void init() {
		taskList = new ArrayList();
		// Code for initializing GUI
		addPanel = new AddPanel();
		viewPanel = new ViewPanel();
	}
}
