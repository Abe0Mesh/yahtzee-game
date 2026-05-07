// To be updated later

// package yahtzee;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.AfterEach;
// import static org.junit.jupiter.api.Assertions.*;
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;


// /**
//  * The class is for testing if it readSave can 
//  * read a txt file
//  * 
//  * @author Joaquin Silerio
//  * @version 1.0
//  */
// public class ReadSaveTest {

//     private SaveFile saveFile;
//     private Multiplayer multiPlayer;
//     private static final String TEST_PATH = "src/main/resources/savegame.txt";
    
//     @BeforeEach          
//     /**
//      * This method sets up my test function,
//      */
//     void setUp() {
//         saveFile = new SaveFile();
//         multiPlayer = new Multiplayer(4);

//         // player 1
//         multiPlayer.getPlayer(1).setUsed(0, true); // aces scoreline used
//         multiPlayer.getPlayer(1).setScoreResults(0, 3); // 3 aces

//         // player 2
//         multiPlayer.getPlayer(2).setUsed(6, true); // 3K scoreline used
//         multiPlayer.getPlayer(2).setScoreResults(6, 18); // 3 sixes
//     }

//     @AfterEach
//         /**
//      * This method deletes my test file
//      */
//     void deleteTest() {
//         new File(TEST_PATH).delete();
//     }


//     @Test
//     /**
//      * This method tests reading a SaveFile
//      * 
//      */
//     void testReadSave() {
//         int rollsLeft = 2;
//         int curPlayer = 1;
//         int maxPlayers = 4;
//         int[] hand = {1, 2, 3, 4, 5};


//         saveFile.writeSave(rollsLeft, hand, curPlayer, maxPlayers, multiPlayer);
//         int [][] result = saveFile.readSave(maxPlayers);

//         // verify game data
//         assertEquals(2, result[0][0], "rollsLeft should be 2");
//         assertEquals(1, result[0][1], "curPlayer should be 1");
//         assertEquals(4, result[0][2], "maxPlayers should be 4");

//         // verify dice values
//         assertEquals(1, result[1][0], "dice[0] should be 1");
//         assertEquals(2, result[1][1], "dice[1] should be 2");
//         assertEquals(3, result[1][2], "dice[2] should be 3");
//         assertEquals(4, result[1][3], "dice[3] should be 4");
//         assertEquals(5, result[1][4], "dice[4] should be 5");

//         // verify player 1 used lines
//         assertEquals(1, result[2][0], "player 1 aces should be used");
//         assertEquals(0, result[2][6], "player 1 threeK should not be used");

//         // verify player 1 scores
//         assertEquals(3, result[3][0], "player 1 aces score should be 3");

//         // verify player 2 used lines
//         assertEquals(0, result[4][0], "player 2 aces should not be used");
//         assertEquals(1, result[4][6], "player 2 threeK should be used");

//         // verify player 2 scores
//         assertEquals(18, result[5][6], "player 2 threeK score should be 18");
 
//     }

// }
