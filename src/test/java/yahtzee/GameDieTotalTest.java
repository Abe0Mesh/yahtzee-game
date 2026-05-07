package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing game die
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameDieTotalTest {

   



    @Test
    /**
     * This method tests totaling game die
     * 
     */
    void GameDieTotalTest() {

        // Arrange
        GameDie die = new GameDie();
        int[] data = {1, 2, 3};

        // Act
        int totalDie = die.totalAllDice(data);


        // Assert
        assertEquals(6, totalDie);
  
    }


}
