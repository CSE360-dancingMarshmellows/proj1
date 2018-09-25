// Assignment #: 6
//         Name: Adrian Luna Salazar
//    StudentID: 1210186832
//      Lecture: 19401
//  Description: This class creates the panel for adding all the flights

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CreatePanel extends JPanel
 {
   private ArrayList<Flight> flightList;
   private JButton button1;
   private SelectPanel selectPanel;
   private JLabel errorMessage;
   private JTextField airlines, flightNum, depCity, depDay, depTime, arrivCity, arrivDay, arrivTime, airfare;
   private JTextArea flightInfo;

 //Constructor initializes components and organize them using certain layouts
 public CreatePanel(ArrayList<Flight> flightList, SelectPanel selectPanel)
  {
    this.flightList = flightList;
    this.selectPanel = selectPanel;
    
    //organize components here
    
    //layout for the west side of panel
    
    //the main panel holding the west side panels
    JPanel panel1 = new JPanel(new BorderLayout());
    
    //this panel holds the error message in red
    JPanel panel2 = new JPanel(new GridLayout(1,1));
    
    //red printed error message 
    errorMessage = new JLabel("");
    panel2.add(errorMessage);
    
    //this pair of panels holds text and input for adding a flight
    JPanel panel3West = new JPanel(new GridLayout(10,1));
    JPanel panel3East = new JPanel(new GridLayout(10,1));
    
    airlines = new JTextField(15);
    flightNum = new JTextField();
    depCity = new JTextField();
    depDay = new JTextField();
    depTime = new JTextField();
    arrivCity = new JTextField();
    arrivDay = new JTextField();
    arrivTime = new JTextField();
    airfare = new JTextField();
   
    panel3West.add(new JLabel ("Enter a name of Airlines"));
    panel3East.add(airlines);
    panel3West.add(new JLabel("Enter a flight number"));
    panel3East.add(flightNum);
    panel3West.add(new JLabel("Enter a departure city"));
    panel3East.add(depCity);
    panel3West.add(new JLabel("Enter a departure day"));
    panel3East.add(depDay);
	panel3West.add(new JLabel("Enter a departure time"));
	panel3East.add(depTime);
	panel3West.add(new JLabel("Enter an arrival city"));
	panel3East.add(arrivCity);
	panel3West.add(new JLabel("Enter an arrival day"));
	panel3East.add(arrivDay);
	panel3West.add(new JLabel("Enter an arrival time"));
	panel3East.add(arrivTime);
	panel3West.add(new JLabel("Airfare"));
	panel3East.add(airfare);
    
	//this panel holds the buttons and centers it
    JPanel panel4 = new JPanel(new GridLayout(1,3));
    
    button1 = new JButton("Create a flight");
    panel4.add(new JLabel(""));
    panel4.add(button1);
    panel4.add(new JLabel(""));
    button1.addActionListener(new ButtonListener());

    //adding sub panels to main panels 
	panel1.add(panel2, BorderLayout.NORTH);
	
	panel1.add(panel3West, BorderLayout.WEST);
	
	panel1.add(panel3East, BorderLayout.EAST);
    
    panel1.add(panel4, BorderLayout.SOUTH);
    
    //setting the default panel to border layout and adding west side panel
    setLayout(new BorderLayout());
    add(panel1,BorderLayout.WEST);
    
    //layout for east side of panel
    
    //creating JTextArea for printed flight info
    flightInfo = new JTextArea(15,30);
    flightInfo.setEditable(false);
    
    //adding scroll bar with the text area
    JScrollPane scroll = new JScrollPane(flightInfo);
    
    //addind east side to default panel
    add(scroll, BorderLayout.EAST);
  }

  //ButtonListener is a listener class that listens to
  //see if the button "Create a flight" is pushed.
  //When the event occurs, it adds a flight using the information
  //entered by a user.
  //It also performs error checking.
  private class ButtonListener implements ActionListener
    {
         public void actionPerformed(ActionEvent event)
         {
        	 //try and catch to see if the inputs are correct
        	 try{
        		 String air = airlines.getText();
        		 int number = Integer.parseInt(flightNum.getText());
	             String dCity = depCity.getText();
	             String dDay = depDay.getText();
	             String dTime = depTime.getText();
	             String aCity = arrivCity.getText(); 
	             String aDay = arrivDay.getText(); 
	             String aTime = arrivTime.getText();
	             Double fare = Double.parseDouble(airfare.getText());
	             
	             //throws exception e if input is empty
	             if (air.length() == 0 || dCity.length() == 0 || dDay.length() == 0 || dTime.length() == 0  
	            		|| aCity.length() == 0 || aDay.length() == 0 || aTime.length() == 0){
	            	 
	            	 Exception e = new Exception();
	            	 throw(e);
	             }
	             //throws exception nfe if input is't a number
	             if (number < 0 || fare < 0){
	            	 Exception nfe = new Exception();
	            	 throw nfe;
	             }
	             //add flight if everything is correct
	             else{
	            	 Flight flight1 = new Flight();
	            	 flight1.setAirlines(air);
	            	 flight1.setFlightNum(number);
	            	 flight1.setDeparture(dCity, dDay, dTime);
	            	 flight1.setArrival(arrivCity.getText(), arrivDay.getText(), arrivTime.getText());
	            	 flight1.setAirfare(Double.parseDouble(airfare.getText()));
	            	 flightList.add(flight1);
	            	 
	            	 //calls addCheckBox from SelectPanel class
	            	 selectPanel.addCheckBox(flight1);
	            	
	            	 errorMessage.setText("Flight added.");
	            	 errorMessage.setForeground(Color.red);
	            	 
	            	 //loop used to print info in text area
	            	 String text = "";
	            	 for (int i = 0; i < flightList.size(); i++){
	            		 text = text + flightList.get(i).toString() + "\n";
	            	 }
	            	 flightInfo.setText(text);
	             }
        	 }
        	 catch(NumberFormatException nfe){
        		 errorMessage.setText("Please enter a number for its flight number and its airfare");
        		 errorMessage.setForeground(Color.red);
        	 }
            catch(Exception e)
        	 {
            	e.printStackTrace();
            	errorMessage.setText("Please enter all fields");
            	errorMessage.setForeground(Color.red);
        	 } 
         } //end of actionPerformed method
    } //end of ButtonListener class
 }