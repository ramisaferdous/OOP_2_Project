import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User{
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<Customer> customersCollection = new ArrayList<>();
    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }
    private static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BD Railway  +++++++++++++\n\nTo Further Proceed, Please enter a value.");
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
            }
        }
        while (desiredOption != 0);
    }


    static void displayMainMenu() {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
    }

}
