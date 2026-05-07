package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The class for testing guiGameFlow class instantation
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GuiGameFlowInstanceTest {
    @Test
    /**
     * This method tests guiGameFlow instance
     * 
     */
    void testGameRun() {

    //Arrange & Act (Trivial so just checking if obj gets created)
    GuiGameFlow gui = new GuiGameFlow();
    //Assert
    assertNotNull(gui);
    }
}
