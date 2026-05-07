package yahtzee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * This class tests writing to the lower scorecard
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class LowerScoreCardWriteTest {

    @Test
    /**
     * This method tests scorecard writng to lower scorecard
     * 
     */
    void testScoreCardWrite() {

        // Arrange
        ScoreCard scorecard = new ScoreCard();
        int playerId = 1;
        int[] data = {1,5};

        // Act
        scorecard.writingScoreCard(playerId, data);

        int[] result = scorecard.readingScoreCard(playerId);

        // Assert
        assertEquals(5, result[1]);
    }
    
}
