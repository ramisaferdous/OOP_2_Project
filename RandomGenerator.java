import java.util.Random;

public class RandomGenerator {
     private String randomNum;


    public void randomIDGen() {
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));

        while (Integer.parseInt(randomID) < 20000) {
            randomID = Integer.toString(rand.nextInt(1000000));
        }
        setRandomNum(randomID);
    }

    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }
    public String getRandomNumber() {

        return randomNum;
    }

}
