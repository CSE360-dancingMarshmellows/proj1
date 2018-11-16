// CSE 360 Fall 2018

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Write {
	
	public Write(ArrayList<String> pathInfo, ArrayList<String> taskInfo, String fileName, int paths, int tasks) {
		File file = new File(fileName + ".txt");
		FileWriter fr = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime current = LocalDateTime.now();
		try {
			fr = new FileWriter(file);
			fr.write("Task Scheduler Report " + fileName + ":");
			fr.write(System.getProperty("line.separator"));
			fr.write("Date and time of Creation: " + formatter.format(current));
			fr.write(System.getProperty("line.separator"));
			fr.write("Tasks:");
			fr.write(System.getProperty("line.separator"));
			int i = 0;
			while (i < taskInfo.size()) {
				fr.write(taskInfo.get(i));
				fr.write(System.getProperty("line.separator"));
				i++;
			}
			fr.write("Paths:");
			fr.write(System.getProperty("line.separator"));
			i = 0;
			while (i < pathInfo.size()) {
				fr.write(pathInfo.get(i));
				fr.write(System.getProperty("line.separator"));
				i++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fr.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
