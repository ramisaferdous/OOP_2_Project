import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<Customer> customersCollection = new ArrayList<>();

    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }

    public static void main(String[] args) {
        int countNumOfUsers = 1;
        Permissions p1 = new Permissions();
        TrainReservation bookingAndReserving = new TrainReservation();
        Train t1 = new Train();
        Customer c1 = new Customer();
        Scanner read = new Scanner(System.in);


        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BD Railway  +++++++++++++\n\nTo Further Proceed, Please enter a value.");
        System.out.println("\n***** Default Username && Password is xyz-xyz ***** Using Default Credentials will restrict you to just view the list of Passengers....\n");
        displayMainMenu();
        int desiredOption = read.nextInt();
        while (desiredOption < 0 || desiredOption > 8) {
            System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
            desiredOption = read.nextInt();
        }
        do {
            Scanner read1 = new Scanner(System.in);
            if (desiredOption == 1) {
                adminUserNameAndPassword[0][0] = "xyz";
                adminUserNameAndPassword[0][1] = "xyz";
                System.out.print("\nEnter the UserName to login to the System :     ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to login to the System :    ");
                String password = read1.nextLine();
                System.out.println();

                if (p1.isPrivilegedUserOrNot(username, password) == -1) {
                    System.out.printf("\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n", "");
                } else if (p1.isPrivilegedUserOrNot(username, password) == 0) {
                    System.out.println("You've standard/default privileges to access the data... You can just view customers data..." + "Can't perform any actions on them....");
                    c1.displayCustomersData();
                } else {
                    System.out.printf("%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....", "", username);

                    do {
                        System.out.printf("%-30s (a) Enter 1 to add new Passenger....\n", "");
                        System.out.printf("%-30s (b) Enter 2 to search a Passenger....\n", "");
                        System.out.printf("%-30s (c) Enter 3 to update the Data of the Passenger....\n", "");
                        System.out.printf("%-30s (d) Enter 4 to delete a Passenger....\n", "");
                        System.out.printf("%-30s (e) Enter 5 to Display all Passengers....\n", "");
                        System.out.printf("%-30s (g) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        desiredOption = read.nextInt();
                        if (desiredOption == 1) {
                            c1.addNewCustomer();
                        } else if (desiredOption == 2) {
                            c1.displayCustomersData();
                            System.out.print("Enter the CustomerID to Search :\t");
                            String customerID = read1.nextLine();
                            System.out.println();
                            c1.searchUser(customerID);

                        } else if (desiredOption == 3) {
                            c1.displayCustomersData();
                            System.out.print("Enter the CustomerID to Update its Data :\t");
                            String customerID = read1.nextLine();
                            if (customersCollection.size() > 0) {
                                c1.editUserInfo(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 4) {
                            c1.displayCustomersData();
                            System.out.print("Enter the CustomerID to Delete its Data :\t");
                            String customerID = read1.nextLine();
                            if (customersCollection.size() > 0) {
                                c1.deleteUser(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 5) {
                            c1.displayCustomersData();
                        } else if (desiredOption == 0) {

                            System.out.println("Thanks for Using BD Railway Ticketing System...!!!");

                        } else {
                            System.out.println("Invalid Choice.....You've Have to login again...");

                            desiredOption = 0;
                        }
                    } while (desiredOption != 0);
                }

            } else if (desiredOption == 2) {


                System.out.print("\nEnter the UserName to Register :    ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to Register :     ");
                String password = read1.nextLine();
                while (p1.isPrivilegedUserOrNot(username, password) != -1) {
                    System.out.print("ERROR!!! Admin with same UserName already exist. Enter new UserName:   ");
                    username = read1.nextLine();
                    System.out.print("Enter the Password Again:   ");
                    password = read1.nextLine();
                }

                /*Setting the credentials entered by the user.....*/
                adminUserNameAndPassword[countNumOfUsers][0] = username;
                adminUserNameAndPassword[countNumOfUsers][1] = password;

                /*Incrementing the numOfUsers */
                countNumOfUsers++;
            } else if (desiredOption == 3) {
                System.out.print("\n\nEnter the Email to Login : \t");
                String userName = read1.nextLine();
                System.out.print("Enter the Password : \t");
                String password = read1.nextLine();
                String[] result = p1.isPassengerRegistered(userName, password).split("-");

                if (Integer.parseInt(result[0]) == 1) {
                    int desiredChoice;
                    System.out.printf("\n\n%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....", "", userName);
                    do {
                        System.out.printf("\n\n%-60s+++++++++ 3rd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "", "", userName);
                        System.out.printf("%-40s (a) Enter 1 to Book Ticket....\n", "");
                        System.out.printf("%-40s (b) Enter 2 to update your Data....\n", "");
                        System.out.printf("%-40s (c) Enter 3 to delete your account....\n", "");
                        System.out.printf("%-40s (d) Enter 4 to Display Train Schedule....\n", "");
                        System.out.printf("%-40s (e) Enter 5 to Cancel a Ticket....\n", "");
                        System.out.printf("%-40s (g) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        System.out.print("Enter the desired Choice :   ");
                        desiredChoice = read.nextInt();
                        if (desiredChoice == 1) {
                            t1.displayTrainSchedule();
                            System.out.print("\nEnter the desired Train number to book :\t ");
                            String flightToBeBooked = read1.nextLine();
                            System.out.print("Enter the Number of tickets for " + flightToBeBooked + " train :   ");
                            int numOfTickets = read.nextInt();
                            while (numOfTickets > 10) {
                                System.out.print("ERROR!! You can't book more than 10 tickets at a time for single flight....Enter number of tickets again : ");
                                numOfTickets = read.nextInt();
                            }
                            bookingAndReserving.bookTrain(flightToBeBooked, numOfTickets, result[1]);
                        } else if (desiredChoice == 2) {

                            c1.editUserInfo(result[1]);
                        } else if (desiredChoice == 3) {

                            System.out.print("Are you sure to delete your account...It's an irreversible action...Enter Y/y to confirm...");
                            char confirmationChar = read1.nextLine().charAt(0);
                            if (confirmationChar == 'Y' || confirmationChar == 'y') {
                                c1.deleteUser(result[1]);
                                System.out.printf("User %s's account deleted Successfully...!!!", userName);
                                desiredChoice = 0;
                            } else {
                                System.out.println("Action has been cancelled...");
                            }
                        } else if (desiredChoice == 4) {
                            t1.displayTrainSchedule();
                            t1.displayMeasurementInstructions();
                        } else if (desiredChoice == 5) {

                            bookingAndReserving.cancelReservation(result[1]);
                        } else {

                            if (desiredChoice != 0) {
                                System.out.println("Invalid Choice......You've Have to login again...");
                            }
                            desiredChoice = 0;
                        }
                    } while (desiredChoice != 0);

                } else {
                    System.out.printf("\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n", "");
                }

            } else if (desiredOption == 4) {
                c1.addNewCustomer();
            }
            displayMainMenu();
            desiredOption = read1.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
                System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
                desiredOption = read1.nextInt();
            }
        } while (desiredOption != 0);
    }



    static void displayMainMenu () {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(d) Press 3 to Login as Passenger.");
        System.out.println("\t\t(e) Press 4 to Register as Passenger.");
    }
}