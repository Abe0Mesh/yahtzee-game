package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The class for testing configScreen class instantation
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ConfigScreenInstanceTest {
    @Test
    /**
     * This method tests configScreen instance
     * 
     */
    void testGameRun() {

    //Arrange & Act (Trivial so just checking if obj gets created)
    ConfigScreen configScreen = new ConfigScreen();
    //Assert
    assertNotNull(configScreen);
    }
}
