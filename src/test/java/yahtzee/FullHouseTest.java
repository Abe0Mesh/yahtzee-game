package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing Full house method 
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class FullHouseTest {

   



    @Test
    /**
     * This method tests full house method
     * 
     */
    void MaxFullHouse() {

        // Arrange
        GameHands gamehand = new GameHands();
        
        // Act
        int[] arr = {2,2,3,3,3};
        boolean results = gamehand.fullHouseFound(arr);

      
        // Assert
        assertTrue(results);
  
    }


}

 
