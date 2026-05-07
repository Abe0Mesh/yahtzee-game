package yahtzee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
To add multiple player handlining in scorecard we need to hanlde how we call scorecard methods in gameflow
To do this the main focus is fixing the button/scoreline logic
    -Make sure buttons stay visible based on which buttons each player has used, we can do this by giving each player their own scorelinesUsed[] array and set button usability based on this arr
    -Make sure after each scoreline button click it itterates to next players data (data including scorelines used, and current scores, aswell as display current user at the top of gui text (e.g Player 2's Turn))

 */


/**
 * The class that holds all methods in relation to reading and writing to the config file
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ScoreCard {
    /**
     * Method reads from scorecard file and if file dosnt exist it creates one with default vals
     * 
     */
    int[] readingScoreCard(int playerId) {
        File file = new File("src/main/resources/player" + playerId + "Scorecard.txt");
        int[] data = new int[3];



        if (!file.exists()) {
            //In my ScoreCard file Upper Scorecard is first line and Lower Scorecard is second line
            // 2 as first index states that we are setting default value
            writingScoreCard(playerId, new int[] {2,0}); // 0 0 default 0 score for both up and low scorecard
            
            
        }
        try {
            Scanner fileReader = new Scanner(file);
            int i = 0;
            while(fileReader.hasNextInt() && i < 3) {
                data[i] = fileReader.nextInt();
                i++;


            }
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No data in file");
        }
        return data; 
    }

    /**
     * Method writes to scorecard file 
     * 
     * @param newData This is an array of the new value the user choose to modify the config file with 
     */
    void writingScoreCard(int playerId, int[] newData) {
        File file = new File("src/main/resources/player" + playerId + "Scorecard.txt");
        int[] currentData = readingScoreCard(playerId);
        try (PrintWriter fileWriter = new PrintWriter(file)) {

            // So because in my switch case I indivualy change each of the two scores, not both at the same time
            // I have
            // First index in new data represents if we are changing upper(0) or lower(1) scorecard. 
            if (newData[0] == 0) {
            fileWriter.println(newData[1]); //changes current upper config
            fileWriter.println(currentData[1]); //keeps current lower config
            }
            //changing data if planning to modify lower
            if (newData[0] == 1) {
                fileWriter.println(currentData[0]); //keeps current upper config
                fileWriter.println(newData[1]); //changes current lower config
            }
            if (newData[0] == 2) {
                fileWriter.println(0); // writes default values
                fileWriter.println(0);
            }
            

        }
        catch (FileNotFoundException e) {
            System.out.println("No File Exists");

        }

    }


    /**
     * Method prints out scoreline option menu
     * 
     * 
     */
    void scoreLineMenu() {
        System.out.println("""
            _Upper Section_
            1: One Line
            2: Two Line
            3: Three Line
            4: Four Line
            5: Five Line
            6: Six Line
            _Lower Section_
            7: Three of a kind
            8: Four of a kind
            9: Full House
            10: Small Straight
            11: Large Straight
            12: Yahtzee
            13: Chance  
        """);               
    }
    void resetAllScorecards(int maxPlayer) {
        int resetUpper[] = {0,0};
        int resetLower[] = {1,0};
        for (int i = 1; i <= maxPlayer; i++) {
        writingScoreCard(i, resetUpper); //resets upper scorecard
        writingScoreCard(i, resetLower); //resets lower scorecard
        }
    }



}
