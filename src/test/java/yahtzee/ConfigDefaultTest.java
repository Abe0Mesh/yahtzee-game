package yahtzee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files; //For configing drive files allowing us to delete
import java.nio.file.Path; //For reading file path for yahtzeeconfig.txt

/**
 * The class for testing default values
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ConfigDefaultTest {
    
    @BeforeEach 
                
    /**
     * This method sets up my test function,
     * due to config file being change so much, this makes sure its deleted and recreated before each test
     */
    void setup() throws IOException {
        Files.deleteIfExists(Path.of("src/main/resources/yahtzeeConfig.txt"));

    }


    @Test
    /**
     * This method tests default config
     * 
     */
    void testDefaultConfig() {
        
    //Arrange
    ConfigFile file = new ConfigFile();
    //Act
    int[] data = file.readingFile();
    //Assert
    assertEquals(6, data[0]);
    assertEquals(5, data[1]);
    assertEquals(3, data[2]);
    }
}
