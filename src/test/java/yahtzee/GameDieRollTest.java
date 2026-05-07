package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing game die roll
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameDieRollTest {

   



    @Test
    /**
     * This method tests rolling game die
     * 
     */
    void GameDieRollTest() {

        // Arrange
        GameDie die = new GameDie();
        ConfigFile config = new ConfigFile();
  
        // Act
        int[] arr = config.readingFile();
        int roll = die.rollDie();
        // Assert
        assertTrue(roll >= 1 && roll <= (arr[0])); 
  
    }


}
