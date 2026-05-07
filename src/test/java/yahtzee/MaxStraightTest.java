package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing max straight method
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class MaxStraightTest {

   



    @Test
    /**
     * This method tests max straight method
     * 
     */
    void MaxStraightTest() {

        // Arrange
        GameHands gamehand = new GameHands();
        
        // Act
        int[] arr = {1,2,3,4};
        int results = gamehand.maxStraightFound(arr);

      
        // Assert
        assertEquals(4, results);
  
    }


}

 
