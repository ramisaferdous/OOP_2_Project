import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User{
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<Customer> customersCollection = new ArrayList<>();
    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }
    public  static void main(String[] args) {
        int countNumOfUsers = 1;
        Permissions p1 = new Permissions();
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
                }
                else if (p1.isPrivilegedUserOrNot(username, password) == 0) {
                    System.out.println("You've standard/default privileges to access the data... You can just view customers data..." + "Can't perform any actions on them....");
                    c1.displayCustomersData();
                }
                else {
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
                    }
                    else if (desiredOption == 2) {
                        c1.displayCustomersData();
                        System.out.print("Enter the CustomerID to Search :\t");
                        String customerID = read1.nextLine();
                        System.out.println();
                        c1.searchUser(customerID);

                    }
                }
                while (desiredOption != 0);
                }
            }
            else if (desiredOption == 2)
            {
            }
        }
            while (desiredOption != 0);


        }




    static void displayMainMenu () {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(d) Press 3 to Login as Passenger.");
        System.out.println("\t\t(e) Press 4 to Register as Passenger.");
    }
}