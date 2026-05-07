package yahtzee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * This class tests upper score card writing
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class UpperScoreCardWriteTest {


    @Test
    /**
     * This method tests scorecard writng to upper scorecard
     * 
     */
    void testScoreCardWrite() {

        // Arrange
        ScoreCard scorecard = new ScoreCard();
        int playerId = 1;
        int[] data = {0,5};

        // Act
        scorecard.writingScoreCard(playerId, data);

        int[] result = scorecard.readingScoreCard(playerId);

        // Assert
        assertEquals(5, result[0]);
    }
    
}
