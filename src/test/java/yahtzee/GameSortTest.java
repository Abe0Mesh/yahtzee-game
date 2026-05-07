package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This class tests game sort method
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameSortTest {



    @Test
    /**
     * This method tests dice array sorter
     * 
     */
    void gameSortTest() {
        //arrange
            GameSort gamesort = new GameSort();
        //act
        int[] arr = {2,3,1};
         gamesort.sortArray(arr, 3);

        //assert
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(3, arr[2]);





    }






    
}
