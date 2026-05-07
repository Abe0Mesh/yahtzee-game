package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The class for testing yahtzee1 class instantation
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class YahtzeeRunInstanceTest {
    @Test
    /**
     * This method tests Yahtzee 
     * 
     */
    void testYahtzee() {

    //Arrange & Act (Trivial so just checking if obj gets created)
    Yahtzee1 yahtzee = new Yahtzee1();
    
    //Assert
    assertNotNull(yahtzee);
    }
}
