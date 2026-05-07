package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The class for testing Menu class instantation
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class MenuScreenInstanceTest {
    @Test
    /**
     * This method tests Menu screen instance
     * 
     */
    void testGameRun() {

    //Arrange & Act (Trivial so just checking if obj gets created)
    MenuScreen menuScreen = new MenuScreen();
    //Assert
    assertNotNull(menuScreen);
    }
}
