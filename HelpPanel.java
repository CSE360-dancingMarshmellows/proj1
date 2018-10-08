import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class HelpPanel extends JPanel {
	private JScrollPane helpScroll;
	private JPanel helpPanel;
	
	public HelpPanel() {
		setLayout(new BorderLayout());
		String helpString = "    To use Task Scheduler, start by going to the Create Task tab and filling out the input fields to create "
				+ "tasks and paths. Enter the name of a";
		String helpString2 = "task into the name input field, and a duration into the duration field. You may add a dependency if the "
				+ "task depends on another task as well.";
		String helpString3 = "Note that the duration of a task will only be valid in the form of an integer. You also may not enter"
				+ "the same name for a task twice. Once ";
		String helpString4 = "you have filled out the input fields, click the Add Task button to create the tasks. You may add as"
				+ " many tasks as you like. Once you have ";
		String helpString5 = "finished adding tasks, you can click on the View Tasks tab to view the tasks that you have added and "
				+ "any paths that have been created ";
		String helpString6 = "between them based on the dependencies you listed. The paths and the tasks will be listed in descending order "
				+ "of duration time. The name ";
		String helpString7 = "of each task contained in a path will be listed, as well as its length in number of tasks and its total duration. "
				+ "You may not enter tasks ";
		String helpString8 = "with dependencies in such a way that a cyclical path is created. If you would like to clear the task and path information "
				+ "from the tab and ";
		String helpString9 = "start again click the Clear Tasks button on the View Tasks tab. You may return to the Create Tasks tab and start again. "
				+ "To see more ";
		String helpString10 = "information about this software, click the About tab.";
		JLabel helpLabel = new JLabel(helpString);
		JLabel helpLabel2 = new JLabel(helpString2);
		JLabel helpLabel3 = new JLabel(helpString3);
		JLabel helpLabel4 = new JLabel(helpString4);
		JLabel helpLabel5 = new JLabel(helpString5);
		JLabel helpLabel6 = new JLabel(helpString6);
		JLabel helpLabel7 = new JLabel(helpString7);
		JLabel helpLabel8 = new JLabel(helpString8);
		JLabel helpLabel9 = new JLabel(helpString9);
		JLabel helpLabel10 = new JLabel(helpString10);
		helpPanel = new JPanel(new GridLayout(10, 1));
		helpPanel.add(helpLabel);
		helpPanel.add(helpLabel2);
		helpPanel.add(helpLabel3);
		helpPanel.add(helpLabel4);
		helpPanel.add(helpLabel5);
		helpPanel.add(helpLabel6);
		helpPanel.add(helpLabel7);
		helpPanel.add(helpLabel8);
		helpPanel.add(helpLabel9);
		helpPanel.add(helpLabel10);
		helpScroll = new JScrollPane(helpPanel);
		add(helpPanel, BorderLayout.NORTH);
	}
}
