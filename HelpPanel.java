import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class HelpPanel extends JPanel {
	private JScrollPane helpScroll;
	private JTextArea helpPanel;
	
	public HelpPanel() {
		helpPanel = new JTextArea(15,70);
		helpPanel.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(helpPanel);
		add(scroll);
		
		String str = "    To use Task Scheduler, start by going to the Create Task tab and filling out the input fields to create "
				+ "tasks and paths. Enter the name of a\n";
		str += "task into the name input field, and a duration into the duration field. You may add a dependency if the "
				+ "task depends on another task as well.\n";
		str += "Note that the duration of a task will only be valid in the form of an integer. You also may not enter"
				+ "the same name for a task twice. Once \n";
		str += "you have filled out the input fields, click the Add Task button to create the tasks. You may add as"
				+ " many tasks as you like. Once you have \n";
		str += "finished adding tasks, you can click on the View Tasks tab to view the tasks that you have added and "
				+ "any paths that have been created \n";
		str += "between them based on the dependencies you listed. The paths and the tasks will be listed in descending order "
				+ "of duration time. The name \n";
		str += "of each task contained in a path will be listed, as well as its length in number of tasks and its total duration. "
				+ "You may not enter tasks \n";
		str += "with dependencies in such a way that a cyclical path is created. If you would like to clear the task and path information "
				+ "from the tab and \n";
		str += "start again click the Clear Tasks button on the View Tasks tab. You may return to the Create Tasks tab and start again. "
				+ "To see more \n\n";
		str += "There was an update to this software. There was an inclusion of an update, critical, and save button. The purpose of the "
				+ "update button is to\n";
		str += "make any changes to the tasks that were added. This is to avoid pressing the reset button and losing all the tasks. The purpose"
				+ "of the critical \n";
		str += "button is to display the path with the longest duration. The purpose of the save button is to save all the tasks that are added into a "
				+ "test file.\n";
		str += "information about this software, click the About tab.";
		
		helpPanel.setText(str);
	}
}
