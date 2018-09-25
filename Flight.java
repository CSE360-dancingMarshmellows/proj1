// Assignment #: 6
//         Name: Adrian Luna Salazar
//    StudentID: 1210186832
//      Lecture: 19401
//  Description: The Flight class describes attributes in a flight
//               and provides accessor and mutator methods for each
//               instance variables as well as toString method.

import java.text.NumberFormat;

public class Flight
 {
  private String airlines;
  private int flightNum;
  private Schedule departure;
  private Schedule arrival;
  private double airfare;

  /************************************************************************
   Constructor method to initialize each variable.
  ************************************************************************/
  public Flight()
   {
    airlines = new String("?");
    flightNum = 0;
    departure = new Schedule();
    arrival = new Schedule();
    airfare = 0.0;
   }

  /************************************************************************
  Accessor method:
  This method returns the airlines name of a flight
  ************************************************************************/
  public String getAirlines()
   {
    return airlines;
   }

  /************************************************************************
  Accessor method:
  This method returns the flight number of a flight
  ************************************************************************/
  public int getFlightNum()
   {
    return flightNum;
   }

  /************************************************************************
  Accessor method:
  This method returns the departure schedule of a flight
  ************************************************************************/
  public Schedule getDeparture()
   {
    return departure;
   }

  /************************************************************************
  Accessor method:
  This method returns the arrival schedule of a flight
  ************************************************************************/
  public Schedule getArrival()
   {
    return arrival;
   }

  /************************************************************************
  Accessor method:
  This method returns the price of a flight
  ************************************************************************/
  public double getAirfare()
   {
    return airfare;
   }

  /************************************************************************
  Mutator method:
  This method sets the airlines name of a flight
  ************************************************************************/
  public void setAirlines(String airlinesName)
   {
    airlines = airlinesName;
   }

  /************************************************************************
  Mutator method:
  This method sets the flight number of a flight
  ************************************************************************/
  public void setFlightNum(int fNum)
   {
    flightNum = fNum;
   }

  /************************************************************************
  Mutator method:
  This method sets the departure schedule of a flight
  ************************************************************************/
  public void setDeparture(String someCity, String someDate, String someTime)
   {
    departure.setCity(someCity);
    departure.setDate(someDate);
    departure.setTime(someTime);
   }

  /************************************************************************
  Mutator method:
  This method sets the arrival schedule of a flight
  ************************************************************************/
  public void setArrival(String someCity, String someDate, String someTime)
   {
    arrival.setCity(someCity);
    arrival.setDate(someDate);
    arrival.setTime(someTime);
   }

  /************************************************************************
  Mutator method:
  This method sets the price of a flight
  ************************************************************************/
  public void setAirfare(double amount)
   {
    airfare = amount;
   }

  /************************************************************************
  This method returns a String containing attribute(variable) values
  of a flight.
  ************************************************************************/
  public String toString()
   {
    NumberFormat money = NumberFormat.getCurrencyInstance();
    String result = "\nAirlines:\t" + airlines + "\n" +
            "Number:\t" + flightNum + "\n" +
            "Departure:\t" + departure + "\n" +
            "Arrival:\t" + arrival + "\n" +
            "Airfare:\t" + money.format(airfare) + "\n";

    result += "\n";

    return result;
   }

} //end of Flight class
