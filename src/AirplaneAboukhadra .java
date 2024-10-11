/*
Name: Salma Aboukhadra

Date: 10/8/2023

Description: An application that keeps track of the passengers of each plane
with additional features to help the airline edit their passenger list

 */

import java.util.*;
/*public class
    {
        //no code goes here
    }*/
    //class for a person
    class Person{
        //attributes of the person class
        private String Firstname;
        private String LastName;
        private String phone;

        //constructor
        public Person(String firstname, String lastName, String phone) {
            this.Firstname = firstname;
            this.LastName = lastName;
            this.phone = phone;
        }

        //getters and setters for attributes in this class
        public String getFirstname() {
            return Firstname;
        }

        public void setFirstname(String firstname) {
            this.Firstname = firstname;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            this.LastName = lastName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        //to string method to print the attributtes of this class based on inputs
        public String toString(){
            String s = "";
            s = "First name:" + Firstname + "\n";
            s = s + "Last name:" + LastName + "\n";
            s = s + "Phone:" + phone  + "\n";
            return s;
        }

        //equals method that compares the last name of two passengers and if they are the same compare first name
       public boolean equals(Object o){
            if (this == o) {
                return true;
             }

            if (!(o instanceof Person)){
                    return false;
                }
            Person person = (Person) o;
            return LastName.equals(person.LastName) && Firstname.equals(person.Firstname);
            }

       }


       //passenger class that inherits the person class because a passenger is-a person
    class Passenger extends Person{
        //extra attributes that are specific to the passenger class
        private int seatNumber;
        private String classType;
        private String ticketId;

        //constructor
        public Passenger(String firstname, String lastName, String phone, int seatNumber, String classType, String ticketId) {
            super(firstname, lastName, phone); //super method that includes the attributes from the person class that need to be inherited
            this.seatNumber = seatNumber;
            this.classType = classType;
            this.ticketId = ticketId;
        }

        //getters and setters
        public int getSeatNumber() {
            return seatNumber;
        }

        public String getClassType() {
            return classType;
        }

        public String getTicketId() {
            return ticketId;
        }

        public void setTicketId(){
            this.ticketId = ticketId;
        }

        public void setClassType(String classType){
            this.classType = classType;
        }

        public void changeSeatNumber(int seatNumber){
            this.seatNumber = seatNumber;

        }

        //to string method for this class
        @Override
        public String toString(){
            String s = "";
            s = super.toString(); //prints the attributes of the person class
            s = s + "Seat number:" + seatNumber + "\n";
            s = s + "Class Type:" + classType + "\n";
            s = s + "Ticket Id:" + ticketId  + "\n";
            return s;
        }
    }

    //interface of abstract method that will be implemented in a later class
    interface list {
        public boolean add(Object o); //method to add passenger
        public Object search(Object o); //method to look for a passenger
        public boolean delete(Object o); //method to delete a passenger
        public void printLast(); //printing last name method
        public void takeOff(); //identifies if the flight took of or not
    }

    //class that will actually define the abstract methods in the interface
    class Airplane implements list{

        private Passenger[] passengers; //instantiate passnegers array
        private static int count = 0; //instatiation of the number of passengers on the plane
        private boolean takenOff; //variable for whether the plane took of or not
        private int planeNum; //plane number instantiation

        //constructor
        public Airplane(int planeNum){
            this.planeNum = planeNum;
            this.passengers = new Passenger[10]; //10 spaces in array
            this.takenOff = false;
            this.count = 0;

        }

        //getters and setters
        public int getPlaneNum(){
            return planeNum;
        }

        public void setPlaneNum(int num) {
            this.planeNum = planeNum;
        }

        public static int getCount() {
            return count;
        }

        //prints passenger list
        @Override
        public String toString() {
            String passengerList = "";
            for (int i = 0; i < count; i++) {
                if (passengers[i] != null) {
                    passengerList += passengers[i].toString() + "\n";
                }
            }
            return passengerList;
        }


        //method that adds passengers to the array
        @Override
        public boolean add(Object o) {
            if (!takenOff && o instanceof Passenger) { //if the plane hasn't taken off check
                Passenger passenger = (Passenger) o;
                if (count < passengers.length) { //goes through array
                    passengers[count] = passenger;
                    count++; //adds to number of pasengers in the array
                    return true;
                } else {
                    System.out.println("Plane is full. Cannot add more passengers."); //if no space of plane
                }
            } else {
                System.out.println("Cannot add passenger after take off."); //prints if plane already gone
            }
            return false;
        }

        //searches for a passenger using last name method
        @Override
        public Object search(Object o) {
            if (o instanceof String) {
                String lastName = (String) o;
                for (int i = 0; i < count; i++) { //goes through the entire array
                    if (passengers[i].getLastName().equalsIgnoreCase(lastName)) { //finds the last name
                        return passengers[i]; //returns the passenger
                    }
                }
            }
            return null;
        }

        //deletes a passeneger
        @Override
        public boolean delete(Object o) {
            if (o instanceof String) {
                String lastName = (String) o; //when o is the last name
                for (int i = 0; i < count; i++) { //looks through the array loop
                    if (passengers[i].getLastName().equalsIgnoreCase(lastName)) { //finds the last name needed
                        passengers[i] = null; //deletes passenger from array
                        count--; //bring passenger count down
                        return true;
                    }
                }
            }
            return false;
        }

        //print last name of passengers
        @Override
        public void printLast() {
            for (int i = 0; i < count; i++) {
                if (passengers[i] != null) {
                    System.out.println("Last name: " + passengers[i].getLastName());
                }
            }
        }
        //determines whether the plane took off or not
        @Override
        public void takeOff() {
            this.takenOff = true;
        }

    }

/*class Driver {
       public static void main(String[]args) {
          Scanner in = new Scanner(System.in);

          Scanner kb = new Scanner(System.in);
          Airplane plane = new Airplane(817345);
          Passenger p1 = new Passenger("Bobbys", "Smith", "123456789", 1, "First class", "916-222-3333");
          Passenger p2 = new Passenger("Johnny", "Apples", "987654321", 8, "Business class", "818-000-1234");
          Passenger p3 = new Passenger("Tommy", "Jerrys", "567123489", 32, "Economy class","202-222-3333");
          Passenger p4 = new Passenger("Candy", "Cruze", "982134567", 15, "Premium Economy class","707-444-5555");

          //this passenger will be added after take off
          Passenger p5 = new Passenger("Kalotiii", "Aaronn", "762134589", 5, "Economy plus","817-222-6666");

          plane.add(p1);
          plane.add(p2);
          plane.add(p3);
          plane.add(p4);
          System.out.println("The palne is about to take off");
          plane.takeOff();
          System.out.println("adding a passenger after take off");
          plane.add(p5);
          String repeat = "";
          while(plane.getCount() > 0)
          {

             System.out.println("Here is the list of the passengers in this plane");
             System.out.println("There are " + plane.getCount() + " Passengers on this plane");
             System.out.println(plane + "\n");

             System.out.println("Testing the printLast method to display the last names");
             plane.printLast();
             System.out.println();

             System.out.println("Testing the static method getCount");
             System.out.println("This train has " + plane.getCount() + " Passengers\n");

             System.out.print("Enter the last name of the passenger to search for: ");
             String lastName = in.nextLine();
             System.out.println(plane.search(lastName));
             System.out.println();

             System.out.println("Testing the delete method");
             System.out.print("Enter the last name of the passenger to be deleted: ");
             String last = in.nextLine();
             plane.delete(last);
             System.out.println("Passenger " + last + " has been removed from the list\n");

             System.out.println("Here is the updated list");
             System.out.println(plane);
             System.out.println("*********************");
             System.out.print("Press any key to continue : ");
             repeat = kb.nextLine();
          }
          System.out.println("No passenger left on this airplane");

       }
    }*/

    /*Complete the following driver to include the required code.*/
    /*20 points is allocated for this driver*/

class yourDriver
    {
        public static void main(String[] args){
            //instantiating an object of the airplane class
            Airplane United = new Airplane(309823);

            //Creating 5 objects
            Passenger p1 = new Passenger("Salma", "Aboukhadra", "019783197", 5, "Business Class", "938647267");
            Passenger p2 = new Passenger("Desiree", "Montano", "238923432", 6, "First Class", "65364346");
            Passenger p3 = new Passenger("Ryan", "Calhoun", "23234327", 7, "Economy Class", "3464534");
            Passenger p4 = new Passenger("Eleanor", "Champlin-Wilson", "452245225", 9, "Economy Premium Class", "94363363");
            Passenger p5 = new Passenger("Luke", "Phan", "325223553", 1, "Business Class", "563265879");

            // Add passengers to the United Plane
            United.add(p1);
            United.add(p2);
            United.add(p3);
            United.add(p4);
            United.add(p5);

            //Display the info of this flight
            // Display information about the plane and its passengers
            System.out.println("Airplane Information:\n" + United);

            // Display the last names of passengers
            System.out.println("Last Names of Passengers:");
            United.printLast();

            // Search for a passenger by last name
            System.out.print("Enter the last name of the passenger to search for: ");
            Scanner scanner = new Scanner(System.in);
            String lastNameToSearch = scanner.nextLine();
            Object foundPassenger = United.search(lastNameToSearch);
            if (foundPassenger != null) {
                System.out.println("Passenger found:\n" + foundPassenger);
            } else {
                System.out.println("Passenger not found.");
            }

            // Delete a passenger by last name
            System.out.print("Enter the last name of the passenger to delete: ");
            String lastNameToDelete = scanner.nextLine();
            boolean deleted = United.delete(lastNameToDelete);
            if (deleted) {
                System.out.println("Passenger " + lastNameToDelete + " has been removed from the list.");
            } else {
                System.out.println("Passenger not found. Deletion failed.");
            }

            // Display updated information about the plane and its passengers
            System.out.println("Updated Airplane Information:\n" + United);

            // Check if the plane has taken off
            if (United.getCount() > 0) {
                System.out.println("The plane has not taken off yet.");
            } else {
                System.out.println("The plane has taken off.");
            }
        }
    }




