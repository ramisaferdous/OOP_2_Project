import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TrainReservation implements DisplayClass{
    Train train =new Train();
    int trainIndexIntrainList;

    void bookTrain(String trainNo, int numOfTickets, String userID) {
        boolean isFound = false;
        for (Train t2 : train.getTrainList()) {
            if (trainNo.equalsIgnoreCase(t2.getTrainNumber())) {
                for (Customer customer : Customer.customerCollection) {
                    if (userID.equals(customer.getUserID())) {
                        isFound = true;
                        t2.setNoOfSeatsInTheTrain(t2.getNoOfSeats() - numOfTickets);
                        if (!t2.isCustomerAlreadyAdded(t2.getListOfRegisteredCustomersInATrain(), customer)) {
                            t2.addNewCustomerTotrain(customer);
                        }
                        if (istrainAlreadyAddedToCustomerList(customer.trainsRegisteredByUser, t2)) {
                            addNumberOfTicketsToAlreadyBookedtrain(customer, numOfTickets);
                            if (trainIndex(train.getTrainList(), train) != -1) {
                                customer.addExistingtrainToCustomerList(trainIndex(train.getTrainList(), train), numOfTickets);
                            }
                        } else {
                            customer.addNewtrainToCustomerList(t2);
                            addNumberOfTicketsForNewtrain(customer, numOfTickets);
                        }
                        break;
                    }
                }
            }
        }
        if (!isFound) {
            System.out.println("Invalid train Number...! No train with the  ID \"" + trainNo + "\" was found...");
        } else {
            System.out.printf("\n %50s You've booked %d tickets for train \"%5s\"...", "", numOfTickets, trainNo.toUpperCase());
        }
    }
    void cancelReservation(String userID) {
        String trainNum = "";
        Scanner read = new Scanner(System.in);
        int index = 0, ticketsToBeReturned;
        boolean isFound = false;
        for (Customer customer : Customer.customerCollection) {
            if (userID.equals(customer.getUserID())) {
                if (customer.gettrainsRegisteredByUser().size() != 0) {
                    System.out.printf("%50s %s Here is the list of all the train registered by you %s", " ", "++++++++++++++", "++++++++++++++");
                    displaytrainsRegisteredByOneUser(userID);
                    System.out.print("Enter the train Number of the train you want to cancel : ");
                    trainNum = read.nextLine();
                    System.out.print("Enter the number of tickets to cancel : ");
                    int numOfTickets = read.nextInt();
                    Iterator<Train> trainIterator = customer.gettrainsRegisteredByUser().iterator();
                    while (trainIterator.hasNext()) {
                        Train train = trainIterator.next();
                        if (trainNum.equalsIgnoreCase(train.getTrainNumber())) {
                            isFound = true;
                            int numOfTicketsFortrain = customer.getNumOfTicketsBookedByUser().get(index);
                            while (numOfTickets > numOfTicketsFortrain) {
                                System.out.print("ERROR!!! Number of tickets cannot be greater than " + numOfTicketsFortrain + " for this train. Please enter the number of tickets again : ");
                                numOfTickets = read.nextInt();
                            }
                            if (numOfTicketsFortrain == numOfTickets) {
                                ticketsToBeReturned = train.getNoOfSeats() + numOfTicketsFortrain;
                                customer.numOfTicketsBookedByUser.remove(index);
                               trainIterator.remove();
                            } else {
                                ticketsToBeReturned = numOfTickets + train.getNoOfSeats();
                                customer.numOfTicketsBookedByUser.set(index, (numOfTicketsFortrain - numOfTickets));
                            }
                            train.setNoOfSeatsInThetrain(ticketsToBeReturned);
                            break;
                        }
                        index++;
                    }

                }else{
                    System.out.println("No train Has been Registered by you with ID \"\"" + trainNum.toUpperCase() +"\"\".....");
                }
//                index++;
                if (!isFound) {
                    System.out.println("ERROR!!! Couldn't find train with ID \"" + trainNum.toUpperCase() + "\".....");
                }
            }
        }
    }
    void addNumberOfTicketsToAlreadyBookedtrain(Customer customer, int numOfTickets) {
        int newNumOfTickets = customer.numOfTicketsBookedByUser.get(trainIndexIntrainList) + numOfTickets;
        customer.numOfTicketsBookedByUser.set(trainIndexIntrainList, newNumOfTickets);
    }
    void addNumberOfTicketsForNewtrain(Customer customer, int numOfTickets) {
        customer.numOfTicketsBookedByUser.add(numOfTickets);
    }
    boolean istrainAlreadyAddedToCustomerList(List<Train> TRAIN_LIST, Train train) {
        boolean addedOrNot = false;
        for (Train train1 : TRAIN_LIST) {
            if (train1.getTrainNumber().equalsIgnoreCase(train.getTrainNumber())) {
                this.trainIndexIntrainList = TRAIN_LIST.indexOf(train1);
                addedOrNot = true;
                break;
            }
        }
        return addedOrNot;
    }
    @Override
    public void displaytrainsRegisteredByOneUser(String userID) {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        System.out.printf("| Num  | train SCHEDULE\t\t\t   | train NO |  Booked Tickets  | \tFROM ====>>       | \t====>> TO\t   | \t   train TIME |  GATE  |  train STATUS  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        for (Customer customer : Customer.customerCollection) {
            List<Train> t = customer.gettrainsRegisteredByUser();
            int size = customer.gettrainsRegisteredByUser().size();
            if (userID.equals(customer.getUserID())) {
                for (int i = 0; i < size; i++) {
                    System.out.println(toString((i + 1), t.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
                }
            }
        }
    }
    public String toString(int serialNum, Train train, Customer customer) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s  |   %-6sHrs |  %-4s  |", serialNum, train.getTrainSchedule(), train.getTrainNumber(), customer.numOfTicketsBookedByUser.get(serialNum - 1), train.getFromWhichCity(), train.getToWhichCity(), train.gettrainTime(), train.getplatform());
    }
    int trainIndex(List<Train> Train_List, Train train) {
        int i = -1;
        for (Train train1 : Train_List) {
            if (train1.equals(train)) {
                i = Train_List.indexOf(train1);
            }
        }
        return i;
    }

  
}
