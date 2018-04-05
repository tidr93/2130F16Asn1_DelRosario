/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2130f16asn1_delrosario;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.LinkedList;
import java.text.DecimalFormat;
/**
 *
 * @author Thomas
 * 101017215
 * COMP2130 Assignment 1
 * Extra features: Usage of inherited classes, main menu, polymorphic objects
 */
import java.text.ParseException;
public class Main {

    /**
     * @param args the command line arguments
     */
    // temp vars are global to the main class to be accessed by helpers
    static Scanner in = new Scanner(System.in);
    static Date _date;
    static String _source, _destination;
    static String _firstName, _lastName, temp;
    static int _number, _age;
    static int _id, _miles;
    static double _fare;
    static String flag;
    
    // output formatter + error output
    static String dateFormat = "dd/MM/yyyy";
    static DecimalFormat currency = new DecimalFormat("$###,###,###,###.00");
    static SimpleDateFormat format = new SimpleDateFormat(dateFormat);
    static String zeroError = "Must enter a number greater than zero.";
    static String numberError = "Must enter a valid number.";
    static String dateError = "Please enter a valid date only in the format specified.";
    static String stringError = "String cannot be empty.";
    static String flagError = "Invalid choice.";
    
    // ----- TEST MENU VARIABLES ----- //
    final static String HRULE = "-----------------------------------------";

    final static String choiceError = "Please enter a valid menu selection number.";
    final static String emptyListError = "No saved items.";
    
    static List<Thomas_TravelReservations> list = new LinkedList<>();
    
    // Parallel Arrays for menus/submenus
    final static String[] MAINLIST = {
        
        "Add a Reservation", 
        "Edit a Reservation", 
        "Delete a Reservation",
        "Search for a Reservation",
        "Display All Reservations",
        "Exit"
    
    };
    static final int[] MAINVALS = {1,2,3,4,5,6};
    
    final static String[] EDITLIST = {
        
        "Travel Date", 
        "Flight Details", 
        "Passenger Details", 
        "Back"
    
    };
    static int[] EDITVALS = {1,2,3,4};
    
    final static String[] FLIGHTLIST = {
    
        "Flight Number", 
        "Source", 
        "Destination", 
        "Fare", 
        "Back"
    
    };
    static int[] FLIGHTVALS = {1,2,3,4,5};
    
    final static String[] PASSLIST = {
        
        "First Name", 
        "Last Name", 
        "Age", 
        "Back"
    
    };
    static int[] PASSVALS = {1,2,3,4};
    
    final static String[] FFLIST = {
        
        "First Name", 
        "Last Name", 
        "Age", 
        "Frequent Flyer ID", 
        "Frequent Flyer Miles", 
        "Back"
        
    };
    static int[] FFVALS = {1,2,3,4,5,6};
    
    // ----- TEST MENU VARIABLES ----- //
    
    // Helper functions
    // validation
    public static boolean parseInt(String val) {
        
        try {
            
            Integer.parseInt(val);
            return true;
            
        } catch (NumberFormatException e) {
            
            return false;
            
        }
        
    }
    
    public static boolean parseDouble(String val) {
        
        try {
            
            Double.parseDouble(val);
            return true;
            
        } catch (NumberFormatException e) {
            
            return false;
            
        }
        
    }
    
    // prompt uses validation
    public static int intPrompt(String prompt) {
        
        do {
            
            System.out.print(prompt);
            temp = in.nextLine();
            if (parseInt(temp)) {
                // positive number check
                int holder = Integer.parseInt(temp);
                if (holder > 0) {
                    
                    return holder;
                    
                } else {
                    
                    System.out.println(zeroError);
                    
                }
                
            } else {
                
                System.out.println(numberError);
                
            }
            
        } while (true);
        
    }
    
    public static double doublePrompt(String prompt) {
        
        do {
            
            System.out.print(prompt);
            temp = in.nextLine();
            if (parseDouble(temp)) {
                
                double holder = Double.parseDouble(temp);
                if (holder > 0.0) {
                    
                    return holder;
                    
                } else {
                    
                    System.out.println(zeroError);
                    
                }
                return Double.parseDouble(temp);
                
            } else {
                
                System.out.println(numberError);
                
            }
            
        } while (true);
        
    }
    // also does validation
    public static Date datePrompt(String prompt) {
        
        Date holder;
        
        do {
            
            System.out.print(prompt);
            temp = in.nextLine();
            
            try {
                
                holder = format.parse(temp);
                return holder;
                
            } catch (ParseException e) {
                
                System.out.println(dateError);
                
            } 
            
        } while (true);
        
    }
    
    public static String strPrompt(String prompt) {
        
        String holder;
        
        do {
            
            System.out.print(prompt);
            holder = in.nextLine();
            
            if (holder.equalsIgnoreCase("")) {
                
                System.out.println(stringError);
                
            } else {
                
                return holder;
                
            }
            
        } while(true);
        
    }
    
    // ----- TEST MENU HELPER FUNCTION ----- //
    // check matching dynamic object types
    public static boolean checkType(Object toCheck, Class<?> type) {
        
        return type.isInstance(toCheck);
        
    }
    // build menu list for user prompt
    public static String displayMenu(String menuName, String[] names, int[] vals) {
        
        String output = menuName + "\n";
        
        for (int i = 0; i < names.length; i++) {
            
            output += vals[i] + ". " + names[i] + "\n";
            
        } return output;
        
    } 
    // main menu prompt
    public static int mainMenuPrompt() {
        
        do {
            
            System.out.println(HRULE);
            System.out.println(displayMenu("Main Menu:", MAINLIST, MAINVALS));
            System.out.print("Make a selection: ");
            String input = in.nextLine();
            if (parseInt(input)) {
                
                return Integer.parseInt(input);
                
            } else {
                
                System.out.println(choiceError);
                
            }
            
        } while (true);
        
        
    }
    // display list of reservation to select
    // return -2 => empty list
    // return -1 => out of bounds
    public static int searchHelper(String action) {
        
        if (list.isEmpty()) {
            
            return -2;
            
        } else {
            
            int index; // actual index starting from 0
            int displayed; // index that the user will see, starting from 1
            
            System.out.println(HRULE);
            System.out.println("Select a reservation to " + action + " below: ");
            for (int i = 0; i < list.size(); i++) {
                
                System.out.println(i+1 + ". " + list.get(i).toShortString(format));
            
            }
            
            displayed = intPrompt("Make a selection: ");
            index = displayed - 1;
            if (index >= list.size()) {
                
                return -1;
                
            } else {
                
                return index;
                
            }
            
        }
        
    }
    
    // main menu functions
    // create new object + store to list
    public static void add() {
        
        System.out.println(HRULE);
        System.out.println("Enter information below:");
        _date = datePrompt("Enter flight date(only format accepted is " + dateFormat + ": ");
        
        _number = intPrompt("Enter flight number: ");
        
        _source = strPrompt("Enter source location: ");
        _destination = strPrompt("Enter destination location: ");
        
        _fare = doublePrompt("Enter fare amount: $");
        
        System.out.println("Enter customer information.");
        _firstName = strPrompt("Enter first name: ");
        _lastName = strPrompt("Enter last name: ");
        _age = intPrompt("Enter age: ");
        
        do {
            
            System.out.print("Is the customer a frequent flyer? (Y/N): ");
            flag = in.nextLine();
            
            if(flag.equalsIgnoreCase("Y")) {
                
                _id = intPrompt("Enter frequent flyer ID: ");
                _miles = intPrompt("Enter miles amount: ");
                
                // Build object and add to list
                list.add(new Thomas_TravelReservations(_date, _number, _source, _destination, _fare, _firstName, _lastName, _age, _id, _miles));
                break;
                
            } else if (flag.equalsIgnoreCase("N")) {
                
                // Build object and add to list
                list.add(new Thomas_TravelReservations(_date, _number, _source, _destination, _fare, _firstName, _lastName, _age));
                break;
                
            } else {
                
                System.out.println(flagError);
                
            }
            
        } while (true);
        
    }
    // select object + property to edit
    public static void edit() {
        
        int index;
        int displayed;
        
        if ((index = searchHelper("edit")) == -1) {
            
            System.out.println("Selection does not exist.");
            return;
            
        } else if (index == -2) {
            
            System.out.println(emptyListError);
            return;
        
        } else {
            
            displayed = index + 1;
                
            System.out.println("Editing " + displayed + ". " + list.get(index).toShortString(format));
                
            int editIndex;
            
            do {
                
                System.out.println(HRULE + "\n" + displayMenu("What would you like to edit?", EDITLIST, EDITVALS));
                editIndex = intPrompt("Make a selection: ");
                
                if (editIndex > EDITLIST.length) {
                    
                    System.out.println(flagError);
                    continue;
                    
                }
                
                final int DATE = 1;
                final int FLIGHT = 2;
                final int PASSENGER = 3;
                final int CANCEL = 4;
                
                switch(editIndex) {
                    
                    case DATE: 
                        
                        System.out.println("Current travel date: " + format.format(list.get(index).getDate()));
                        list.get(index).setDate(datePrompt("Enter a new travel date: "));
                        break;
                        
                    case FLIGHT:
                            
                        int flightIndex;
                        final int NUMBER = 1;
                        final int SOURCE = 2;
                        final int DESTINATION = 3;
                        final int FARE = 4;
                        final int CANCEL2 = 5;
                            
                        do {
                                
                            System.out.println(HRULE);
                            System.out.println("Current Flight Details: ");
                            System.out.println(list.get(index).getFlight().toString());
                            System.out.println(displayMenu("Select an item to edit: ", FLIGHTLIST, FLIGHTVALS));

                            flightIndex = intPrompt("Make a selection: ");
                            if (flightIndex > CANCEL2) {
                                    
                                System.out.println(flagError);
                                continue;
                                    
                            }
                                
                            switch(flightIndex) {
                                    
                                case NUMBER:
                                        
                                    System.out.println("Current Flight Number: " + list.get(index).getFlight().getNumber());
                                    list.get(index).getFlight().setNumber(intPrompt("Enter a new flight number: "));
                                    break;
                                        
                                case SOURCE:
                                        
                                    System.out.println("Current Source: " + list.get(index).getFlight().getSource());
                                    list.get(index).getFlight().setSource(strPrompt("Enter a new source: "));
                                    break;
                                    
                                case DESTINATION:
                                        
                                    System.out.println("Current Destination: " + list.get(index).getFlight().getDestination());
                                    list.get(index).getFlight().setDestination(strPrompt("Enter a new destination: "));
                                    break;
                                    
                                case FARE:
                                        
                                    System.out.println("Current Fare: " + currency.format(list.get(index).getFlight().getFare()));
                                    list.get(index).getFlight().setFare(doublePrompt("Enter a new fare: "));
                                    break;
                                    
                                case CANCEL2: break;
                                    
                                default: System.out.println(flagError);
                                
                            }
                                
                                
                        } while (flightIndex != CANCEL2);
                            
                        break;
                            
                    case PASSENGER: 
                            
                        final int FIRSTNAME = 1;
                        final int LASTNAME = 2;
                        final int AGE = 3;
                        final int CANCEL3 = 4;
                        final int ID = 4;
                        final int MILES = 5;
                        final int CANCEL4 = 6;
                        int passIndex;
                            
                        // Differentiate if passenger at runtime is base or subclass
                        Class<?> base = Thomas_Passenger.class;
                        Class<?> sub = Thomas_FFPassenger.class;
                            
                        System.out.println(HRULE);
                        System.out.println("Current Passenger Details: ");
                        System.out.println(list.get(index).getPassenger().toString());
                            
                        if (checkType(list.get(index).getFlight(), base)) {
                            // Display regular passenger vars to edit
                            do {
                                    
                                System.out.println(displayMenu("Select an item to edit: ", PASSLIST, PASSVALS));
                                passIndex = intPrompt("Make a Selection: ");
                                if (passIndex > CANCEL3) {
                                        
                                    System.out.println(flagError);
                                    continue;
                                        
                                }
                                    
                                switch(passIndex) {
                                        
                                    case FIRSTNAME: 
                                            
                                        System.out.println("First Name: " + list.get(index).getPassenger().getFirstName());
                                        list.get(index).getPassenger().setFirstName(strPrompt("Enter a new first name: "));
                                        break;
                                            
                                    case LASTNAME: 
                                            
                                        System.out.println("Last Name: " + list.get(index).getPassenger().getLastName());
                                        list.get(index).getPassenger().setLastName(strPrompt("Enter a new last name: "));
                                        break;
                                            
                                    case AGE: 
                                            
                                        System.out.println("Age: " + list.get(index).getPassenger().getAge());
                                        list.get(index).getPassenger().setAge(intPrompt("Enter a new age: "));
                                        break;
                                            
                                    case CANCEL3:
                                            
                                        break;  
                                        
                                    default:
                                            
                                        System.out.println(flagError);
                                        
                                }
                                    
                            } while (passIndex != CANCEL3);
                                
                        } else {
                            // Display FF Thomas_Passenger vars
                            do {
                                
                                System.out.println(displayMenu("Select an item to edit: ", FFLIST, FFVALS));
                                passIndex = intPrompt("Make a selection: ");
                                    
                                if (passIndex > CANCEL4) {
                                        
                                    System.out.println(flagError);
                                    continue;
                                
                                }
                                    
                                switch(passIndex) {
                                        
                                    case FIRSTNAME: 
                                            
                                        System.out.println("First Name: " + list.get(index).getPassenger().getFirstName());
                                        list.get(index).getPassenger().setFirstName(strPrompt("Enter a new first name: "));
                                        break;
                                    
                                    case LASTNAME: 
                                            
                                        System.out.println("Last Name: " + list.get(index).getPassenger().getLastName());
                                        list.get(index).getPassenger().setLastName(strPrompt("Enter a new last name: "));
                                        break;
                                    
                                    case AGE: 
                                            
                                        System.out.println("Age: " + list.get(index).getPassenger().getAge());
                                        list.get(index).getPassenger().setAge(intPrompt("Enter a new age: "));
                                        break;
                                    
                                    case ID:
                                        
                                        System.out.println("Frequent Flyer ID: " + (Thomas_FFPassenger.class.cast(list.get(index).getPassenger()).getID()));
                                        Thomas_FFPassenger.class.cast(list.get(index).getPassenger()).setID(intPrompt("Enter a new ID: "));
                                        break;
                                    
                                    case MILES: 
                                            
                                        System.out.println("Frequent Flyer Miles: " + Thomas_FFPassenger.class.cast(list.get(index).getPassenger()).getMiles());
                                        Thomas_FFPassenger.class.cast(list.get(index).getPassenger()).setMiles(intPrompt("Enter new miles count: "));
                                        break;
                                    
                                    case CANCEL4: break;
                                        
                                    default: System.out.println(flagError);
                                        
                                }
                            
                            } while (passIndex != CANCEL4);
                        
                        }
                            
                        break;
                    
                    case CANCEL: break;
                
                } break;
            
            } while (true);
                
        }
        
    }
    // select object + delete from list
    public static void delete() {
        
        int index, displayed;
        if ((index = searchHelper("delete")) == -1) {
            
            System.out.println("Selection does not exist.");
            return;
            
        } else if (index == -2) {
            
            System.out.println(emptyListError);
        
        } else {
            
            displayed = index + 1;
            Thomas_TravelReservations temp = list.remove(index);
            System.out.println(displayed + ". " + temp.toShortString(format) + " removed.");
            return;
        
        }
        
    }
    // select + display object
    public static void search() {
        
        int index, displayed;
        if ((index = searchHelper("search")) == -1) {
            
            System.out.println("Selection does not exist.");
            return;
            
        } else if (index == -2) {
        
            System.out.println(emptyListError);
            return;
        
        } else {
            
            System.out.println("Displaying reservation: \n" + list.get(index).toString(format));
            
        }
        
        
    }
    // display all
    public static void displayAll() {
        
        if (list.isEmpty()) {
        
            System.out.println(emptyListError);
            return;
        
        }
        System.out.println("All Travel Reservations: " + HRULE);
        for (int i = 0; i < list.size(); i++) {
            
            System.out.println(list.get(i).toString(format));
            
        }
        
    }
    
    // ----- TEST MENU HELPER FUNCTION ----- //
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        // ------ MAIN MENU ------ //
        System.out.println("Flight Reservation Input System 0.1 alpha");
        int choice;
        final int ADD = 1, EDIT = 2, DELETE = 3, SEARCH = 4, DISPLAYALL = 5, EXIT = 6;
        
        do {
            
            choice = mainMenuPrompt();
            
            switch(choice) {
                
                case ADD: add();
                          break;
                case EDIT: edit();
                           break;
                case DELETE: delete();
                             break;
                case SEARCH: search();
                             break;
                case DISPLAYALL: displayAll();
                                 break;
                case EXIT: break;
                default: System.out.println(choiceError);
                
            }
            
        } while (choice != EXIT);
        
        System.out.println("System exiting.");
        
        // ------ MAIN MENU ------ //
        
    }
    
}
