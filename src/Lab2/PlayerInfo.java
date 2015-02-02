package Lab2;

import java.util.Random;

/**
 * Created by fiona on 2/2/15.
 */
public class PlayerInfo {

    int secretNumber = generateNumber();
    int highestGuess=100;
    int lowestGuess=1;
    int numOfGuesses=0;


    private static int generateNumber(){
        Random rand = new Random();
        return rand.nextInt(99)+1;
    }

}
