package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing Max of a kind method
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class MaxKindTest {

   



    @Test
    /**
     * This method tests Max of a kind method(For four of a kind)
     * 
     */
    void MaxKindTest() {

        // Arrange
        GameHands gamehand = new GameHands();
        
        // Act
        int[] arr = {1,1,1,1};
        int results = gamehand.maxOfAKindFound(arr);

      
        // Assert
        assertEquals(4, results);
  
    }


}

 
