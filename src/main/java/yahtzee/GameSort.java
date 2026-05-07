package yahtzee;

/**
 * This class holds methods which hold sorting algortims for Yahtzee
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameSort {
    /**
     * Method sorts players die roll my accedning order using bubble sort
     * 
     * @param array this takes in a array of integers which holds the pre soreted die rolls
     * @param size this takes in the amount of die in play 
     */

    void sortArray(int array[], int size)
    // bubble sort from Gaddis chapter 8
    {
        boolean swap;
        int temp;
    
        do
        {
            swap = false;
            for (int count = 0; count < (size - 1); count++)
            {
                if (array[count] > array[count + 1])
                {
                    temp = array[count];
                    array[count] = array[count + 1];
                    array[count + 1] = temp;
                    swap = true;
                }
            }
        } while (swap);
    }

}
