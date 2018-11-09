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
	
	public Write(String pathInfo, String fileName) {
		File file = new File("./" + fileName + ".txt");
		FileWriter fr = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime current = LocalDateTime.now();
		try {
			fr = new FileWriter(file);
			fr.write("Task Scheduler Report:\n\n");
			fr.write("Date and time of Creation: " + formatter.format(current) + "\n\n");
			fr.write(pathInfo);
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
