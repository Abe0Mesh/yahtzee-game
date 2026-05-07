package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing writing config
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ConfigWriteTest {

   



    @Test
    /**
     * This method tests config writng
     * 
     */
    void testConfigWrite() {

        // Arrange
        ConfigFile file = new ConfigFile();
        int[] data = {1, 2, 3};

        // Act
        file.writingFile(data);

        int[] result = file.readingFile();

        // Assert
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
    }


}
