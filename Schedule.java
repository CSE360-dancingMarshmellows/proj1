// Assignment #: 6
//         Name: Adrian Luna Salazar
//    StudentID: 1210186832
//      Lecture: 19401
//  Description: The Schedule class descrives a departure or an arrival
//               information of a flight including a city, time, and date.
//               It also provides their accessor, mutator methods,
//               and toString method.

public class Schedule {

 private String city;
 private String time;
 private String date;

 /************************************************************************
 Constructor method to initialize intance variables.
 ************************************************************************/
 public Schedule()
  {
   city = new String("?");
   time= new String("?");
   date = new String("?");
  }

 /************************************************************************
 Accessor method:
 This method returns a city of departure or arrival.
 ************************************************************************/
 public String getCity()
  {
   return city;
  }

 /************************************************************************
 Accessor method:
 This method returns time of departure or arrival.
 ************************************************************************/
 public String getTime()
  {
   return time;
  }

 /************************************************************************
 Accessor method:
 This method returns a date of departure or arrival.
 ************************************************************************/
 public String getDate()
  {
   return date;
  }

 /************************************************************************
  Modifier method:
  This method sets the city of departure or arrival.
 ************************************************************************/
 public void setCity(String place)
  {
   city = place;
  }

 /************************************************************************
  Modifier method:
  This method sets the time of departure or arrival.
 ************************************************************************/
 public void setTime(String someTime)
  {
   time = someTime;
  }

 /************************************************************************
  Modifier method:
  This method sets the date of departure or arrival.
 ************************************************************************/
 public void setDate(String someDate)
  {
   date = someDate;
  }

 /*****************************************************************************
 This method return a string containing the attribute information in
 departure or arrival.
 *****************************************************************************/
 public String toString()
  {
   String result;

   result = city + "," + date + "," + time;

   return result;
  }

}

