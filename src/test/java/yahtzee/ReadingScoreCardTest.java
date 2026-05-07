package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * The class for testing reading scorecard method
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ReadingScoreCardTest {


    @Test
    /**
     * This method tests config reading
     * 
     */
    void testConfigRead() {
        //Arrange
        ScoreCard scorecard = new ScoreCard();
        int playerId = 1;
        //Act
        int[] arr = scorecard.readingScoreCard(playerId);
        
        //Assert
        assertNotNull(arr);
    }
}
