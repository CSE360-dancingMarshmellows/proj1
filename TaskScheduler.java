//CSE 360 Fall 2018

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskScheduler extends JApplet {
	
	private int APPLET_WIDTH = 800, APPLET_HEIGHT = 300;

	private JTabbedPane tPane;
	private AddPanel addPanel;
	private ViewPanel viewPanel;
	private HelpPanel helpPanel;
	private AboutPanel aboutPanel;
	private ArrayList<Task> taskList;

	public void init() {
		taskList = new ArrayList<Task>();

		viewPanel = new ViewPanel(taskList);
		addPanel = new AddPanel(viewPanel);
		helpPanel = new HelpPanel();
		aboutPanel = new AboutPanel();

		tPane = new JTabbedPane();
		tPane.addTab("Create Task", addPanel);
		tPane.addTab("View Tasks", viewPanel);
		tPane.addTab("Help", helpPanel);
		tPane.addTab("About", aboutPanel);
		getContentPane().add(tPane);
		setSize (APPLET_WIDTH, APPLET_HEIGHT);
	}
}
