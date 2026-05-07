package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing config read 
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ConfigReadTest {


    @Test
    /**
     * This method tests config reading
     * 
     */
    void testConfigRead() {
        //Arrange
        ConfigFile configfile = new ConfigFile();
        //Act
        int[] arr = configfile.readingFile();
        
        //Assert
        assertNotNull(arr);
    }
}
