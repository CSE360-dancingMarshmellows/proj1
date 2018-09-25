// Assignment #: 6
//         Name: Adrian Luna Salazar
//    StudentID: 1210186832
//      Lecture: 19401
//  Description: This class selects the added flights and sums the total price

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SelectPanel extends JPanel
 {
  private ArrayList<Flight> flightList;
  private JTextField totPrice;
  private JPanel checkBoxes, pricePanel;
  private ArrayList <JCheckBox> arrayBoxes;

  public SelectPanel(ArrayList<Flight> flightList)
   {
      this.flightList = flightList;
      //arrayList with all the JCheckBoxes
      arrayBoxes = new ArrayList<JCheckBox>();
      
      //set default layout to border layout
      setLayout(new BorderLayout());
      
      //north layout
      
      //this panel aligns the check boxes vertically
      checkBoxes = new JPanel();
      checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.Y_AXIS));
      
      //adding scroll bar with check boxes panel to default panel
      JScrollPane scroll = new JScrollPane(checkBoxes);
      add(scroll);

      //south layout
      
      //panel with the price label and output
      pricePanel = new JPanel(new GridLayout(2,1));
      
      JLabel price = new JLabel("       Current Total Purchase");
      totPrice = new JTextField();
      totPrice.setEditable(false);
      pricePanel.add(price);
      pricePanel.add(totPrice);
      
      add(pricePanel, BorderLayout.SOUTH);
  } //end of constructor

 /** This method add checkBox for a given flight object to the **/
 /** left panel. It also creates a check box listener and        **/
 /** add it to the checkbox                                      **/
 public void addCheckBox(Flight flight1)
  {
	  //creates a new JCheckBox a new flight is added
	  JCheckBox flights = new JCheckBox(flight1.toString());
	  checkBoxes.add(flights);//adds to panel of JCheckBoxes 
	  arrayBoxes.add(flights);//adds to array of CheckBoxes
	  flights.addItemListener(new CheckBoxListener());//calls itemListener

  }

 /** CheckBoxListener defines an action when a checkbox is   **/
 /** selected or un-selected. It computes the total purchase **/
 /** amount based on the new selection                       **/
 private class CheckBoxListener implements ItemListener
  {
       public void itemStateChanged(ItemEvent event)
        {
    	   	//loop that adds total of all flights selected
            double purchase = 0.00;
            for (int i = 0; i < arrayBoxes.size(); i++){
            	if (arrayBoxes.get(i).isSelected() == true){
            		purchase += ((Flight) flightList.get(i)).getAirfare();
            	}
            }
            //sets output price 
            java.text.NumberFormat formatter = java.text.NumberFormat.getCurrencyInstance();
            totPrice.setText("" + formatter.format(purchase));
        }
  } 
} 
