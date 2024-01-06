import java.util.ArrayList;
import java.util.List;

public class Train {
    private final String TrainSchedule;
    private final String TrainNumber;
    private final String fromWhichCity;
    private final String platform;
    private final String toWhichCity;
    private double distanceInMiles;
    private double distanceInKm;
    private String Time;
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
        this.Time = calculateTrainTime(distanceInMiles);
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
}