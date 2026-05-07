package yahtzee;

/**
 * The class that holds all methods in relation to game hand / win conditions
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameHands {



    /**
     * Method checks if players hand is a max of a kind
     * 
     * @param hand this takes in a int array with the current players hand
     */
    int maxOfAKindFound(int hand[])
    // this function returns the count of the die value occurring most in the hand
    // but not the value itself
    {
        int maxCount = 0;
        for (int dieValue = 0; dieValue < hand.length; dieValue++)
        {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < hand.length; diePosition++)
            {
                if (hand[dieValue] == hand[diePosition])
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
        }
        return maxCount;
    }

    /**
     * Method checks if players hand is a max straight
     * 
     * @param hand this takes in a int array with the current players hand
     */
    int maxStraightFound(int hand[])
    // this function returns the length of the longest
    // straight found in a hand
    {
        if (hand.length == 0) { return 0;}

        int maxLength = 1;
        int curLength = 1;

        for (int counter = 1; counter < hand.length; counter++)
        {
            if (hand[counter] == hand[counter - 1] + 1) 
                curLength++;
            else if (hand[counter] > hand[counter - 1]+1)
                curLength = 1;

            if (curLength > maxLength)
                maxLength = curLength;
        }
        return maxLength;
    }
    
    /**
     * Method checks if players hand is a full house
     * 
     * @param hand this takes in a int array with the current players hand
     */


    boolean fullHouseFound(int hand[]) {
        // this function returns true if the hand is a full house
        // or false if it does not

        boolean found3k = false;
        boolean found2k = false;
        int currentCount;
        for (int i = 0; i < hand.length; i++) {

             currentCount = 0;

            for (int j = 0; j < hand.length; j++) {
                if (hand[i] == hand[j])
                    currentCount++;
            }

            if (currentCount == 3)
                found3k = true;

            if (currentCount == 2)
                found2k = true;
        }

        return found3k && found2k;
    }

}
