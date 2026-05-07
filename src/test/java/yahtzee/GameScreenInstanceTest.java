package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The class for testing Game Screen class instantation
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameScreenInstanceTest {
    @Test
    /**
     * This method tests Game Screen instance
     * 
     */
    void testGameRun() {

    //Arrange & Act (Trivial so just checking if obj gets created)
    GameScreen gameScreen = new GameScreen();
    //Assert
    assertNotNull(gameScreen);
    }
}
