import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Train extends TrainDistance {
    private final String TrainSchedule;
    private final String TrainNumber;
    private final String fromWhichCity;
    private final String platform;
    private final String toWhichCity;
    private double distanceInMiles;
    private double distanceInKm;
    private String TrainTime;
    private int numOfSeatsInTheTrain;
    private List<Customer> listOfRegisteredCustomersInATrain;
    private int customerIndex;
    private static int nextTrainDay = 0;
    private static final List<Train> TRAIN_LIST = new ArrayList<>();

    Train() {
        this.TrainSchedule = null;
        this.TrainNumber = null;
        this.numOfSeatsInTheTrain = 0;
        toWhichCity = null;
        fromWhichCity = null;
        this.platform = null;
    }

    Train(String TrainSchedule, String TrainNumber, int numOfSeatsInTheTrain, String[][] chosenDestinations, String[] distanceBetweenTheCities, String platform) {
        this.TrainSchedule = TrainSchedule;
        this.TrainNumber = TrainNumber;
        this.numOfSeatsInTheTrain = numOfSeatsInTheTrain;
        this.fromWhichCity = chosenDestinations[0][0];
        this.toWhichCity = chosenDestinations[1][0];
        this.distanceInMiles = Double.parseDouble(distanceBetweenTheCities[0]);
        this.distanceInKm = Double.parseDouble(distanceBetweenTheCities[1]);
        this.TrainTime = calculateTrainTime(distanceInMiles);
        this.listOfRegisteredCustomersInATrain = new ArrayList<>();
        this.platform = platform;
    }


    public String calculateTrainTime(double distanceBetweenTheCities) {
        double groundSpeed = 450;
        double time = (distanceBetweenTheCities / groundSpeed);
        String timeInString = String.format("%.4s", time);
        String[] timeArray = timeInString.replace('.', ':').split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        int modulus = minutes % 5;
        // Changing train time to make minutes near/divisible to 5.
        if (modulus < 3) {
            minutes -= modulus;
        } else {
            minutes += 5 - modulus;
        }
        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }
        if (hours <= 9 && Integer.toString(minutes).length() == 1) {
            return String.format("0%s:%s0", hours, minutes);
        } else if (hours <= 9 && Integer.toString(minutes).length() > 1) {
            return String.format("0%s:%s", hours, minutes);
        } else if (hours > 9 && Integer.toString(minutes).length() == 1) {
            return String.format("%s:%s0", hours, minutes);
        } else {
            return String.format("%s:%s", hours, minutes);
        }
    }

    boolean isCustomerAlreadyAdded(List<Customer> customersList, Customer customer) {
        boolean isAdded = false;
        for (Customer customer1 : customersList) {
            if (customer1.getUserID().equals(customer.getUserID())) {
                isAdded = true;
                customerIndex = customersList.indexOf(customer1);
                break;
            }
        }
        return isAdded;
    }

    void addNewCustomerToFlight(Customer customer) {
        this.listOfRegisteredCustomersInATrain.add(customer);
    }


    public void displayTrainSchedule() {

        Iterator<Train> TrainIterator = TRAIN_LIST.iterator();
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        System.out.printf("| Num  | Train SCHEDULE\t\t\t   | Train NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t  Train TIME |  GATE  |   DISTANCE(MILES/KMS)  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        int i = 0;
        while (TrainIterator.hasNext()) {
            i++;
            Train t1 = TrainIterator.next();
            System.out.println(t1.toString(i));
            System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        }
    }
    @Override
    public String toString(int i) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9s | %-21s | %-22s  |   %-6sHrs |  %-4s  |  %-8s / %-11s|", i, TrainSchedule, TrainNumber, numOfSeatsInTheTrain, fromWhichCity, toWhichCity, TrainTime, platform, distanceInMiles, distanceInKm);
    }

    public int getNoOfSeats() {
        return numOfSeatsInTheTrain;
    }

    public List<Train> getTrainList() {
        return TRAIN_LIST;
    }

    public List<Customer> getListOfRegisteredCustomersInATrain() {
        return listOfRegisteredCustomersInATrain;
    }

    public String getTrainNumber() {
        return TrainNumber;
    }
    public String getplatform() {
        return platform;
    }

    public void setNoOfSeatsInTheTrain(int numOfSeatsInTheTrain) {
        this.numOfSeatsInTheTrain = numOfSeatsInTheTrain;
    }

    public void addNewCustomerTotrain(Customer customer) {

        this.listOfRegisteredCustomersInATrain.add(customer);
    }



    public String getTrainSchedule() {
        return TrainSchedule;
    }

    public String getFromWhichCity() {
        return fromWhichCity;
    }

    public void setNoOfSeatsInThetrain(int numOfSeatsInTheTrain) {
        this.numOfSeatsInTheTrain = numOfSeatsInTheTrain;
    }

    public String gettrainTime() {
        return TrainTime;
    }

    public String getToWhichCity() {
        return toWhichCity;
    }
}