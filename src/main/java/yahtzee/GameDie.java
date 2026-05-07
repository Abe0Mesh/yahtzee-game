package yahtzee;


import java.util.Random; //replacment for rand() 


/**
 * The class that holds all methods in relation to game dice
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameDie {

    private static Random rand = new Random();  //replaces c++ rand()
    ConfigFile config = new ConfigFile();
  
    int[] arr = config.readingFile();


    /**
     * 
     * Method rolls a dice with random chance of numbers
     * 
     */
    int rollDie()
    // this function simulates the rolling of a single die
    {   

        //Number of sides now effected by config file
        int roll = rand.nextInt(arr[0]) + 1; //rand.nextInt is zero index so we +1 to move range from 0-config to 1-config
        return roll;
    }
    /**
     * Method totals all the players dice rolls
     * 
     * @param hand this takes in a int array with the current players hand
     */
    int totalAllDice(int hand[])
    // this function returns the total value of all dice in a hand
    {
        int total = 0;
        //replace 5 with dice in play so no out of bounds calls 
        for (int diePosition = 0; diePosition < hand.length ; diePosition++)
        {
            total += hand[diePosition];
        }
        return total;
    }
}

