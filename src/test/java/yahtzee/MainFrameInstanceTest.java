package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The class for testing Main Frame class instantation
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class MainFrameInstanceTest {
    @Test
    /**
     * This method tests MainFrame instance
     * 
     */
    void testGameRun() {

    //Arrange & Act (Trivial so just checking if obj gets created)
    MainFrame mainFrame = new MainFrame();
    
    //Assert
    assertNotNull(mainFrame);
    }
}
