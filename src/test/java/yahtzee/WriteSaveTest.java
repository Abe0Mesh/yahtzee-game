package yahtzee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * The class is for testing if it writeSave can 
 * write to a txt file
 * 
 * @author Joaquin Silerio
 * @version 1.0
 */
public class WriteSaveTest {

    private SaveFile saveFile;
    private Multiplayer multiPlayer;
    private static final String TEST_PATH = "src/main/resources/savegame.txt";
    
    @BeforeEach          
    /**
     * This method sets up my test function,
     */
    void setUp() {
        saveFile = new SaveFile();
        multiPlayer = new Multiplayer(4);

        // player 1
        multiPlayer.getPlayer(1).setUsed(0, true); // aces scoreline used
        multiPlayer.getPlayer(1).setScoreResults(0, 3); // 3 aces

        // player 2
        multiPlayer.getPlayer(2).setUsed(6, true); // 3K scoreline used
        multiPlayer.getPlayer(2).setScoreResults(6, 18); // 3 sixes
    }

    @AfterEach
        /**
     * This method deletes my test file
     */
    void deleteTest() {
        new File(TEST_PATH).delete();
    }


    @Test
    /**
     * This method tests writing to a SaveFile
     * 
     */
    void testWriteSave() {
        int rollsLeft = 2;
        int curPlayer = 1;
        int maxPlayers = 4;
        int[] hand = {1, 2, 3, 4, 5};

        saveFile.writeSave(rollsLeft, hand, curPlayer, maxPlayers, multiPlayer);

        // verify file was created
        File file = new File(TEST_PATH);
        assertTrue(file.exists(), "Save file should exist after writeSave");
        assertTrue(file.length() > 0, "Save file should not be empty");
    }

}
