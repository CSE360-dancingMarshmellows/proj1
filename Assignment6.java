// Assignment #: 6
//         Name: Adrian Luna Salazar
//    StudentID: 1210186832
//      Lecture: 19401
//  Description: The Assignment 6 class creates a Tabbed Pane with
//               two tabs, one to make a list of flights and one for
//               a customer to select some, and adds it as its Applet content
//               and also sets its size.

import javax.swing.*;
import java.util.*;

public class Assignment6 extends JApplet
 {

   private int APPLET_WIDTH = 800, APPLET_HEIGHT = 300;

   private JTabbedPane tPane;
   private CreatePanel createPanel;
   private SelectPanel selectPanel;
   private ArrayList<Flight> flightList;

   //The method init initializes the Applet with a Pane with two tabs
   public void init()
    {
     //list of flights to be used in every panel
     flightList = new ArrayList<Flight>();

     //customer selection panel uses the list of flights
     selectPanel = new SelectPanel(flightList);


     createPanel = new CreatePanel(flightList, selectPanel);


     //create a tabbed pane with two tabs
     tPane = new JTabbedPane();
     tPane.addTab("Flight Creation", createPanel);
     tPane.addTab("Flight Selection", selectPanel);

     getContentPane().add(tPane);
     setSize (APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
    }
}

