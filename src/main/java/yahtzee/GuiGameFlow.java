package yahtzee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * This class is my controller for my GUI
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */

public class GuiGameFlow {

    private int rollsLeft;
    private int tempCurrentScore;
    private int addedUpdieValue;
    private int currentCount;
    private int[] totalScore;
    private int[] checkCurrentScore;
    private int DICE_IN_PLAY;
    private int rollsPerHand;
    private boolean bonusAdded = false;
    private boolean[] scorelinesUsed;
    private JButton rollB;
    private  int[] hand;
    private boolean[] keptDice;
    private ActionListener dieListener;
    private int curPlayer = 1; // setting curPlayer to 1 so we always start at player 1
    private int maxPlayers; // this variable will read from config or where we store max player data
    
    GameDie die = new GameDie();
    GameHands hands = new GameHands();
    GameSort sort = new GameSort();
    ConfigFile config = new ConfigFile();
    ScoreCard scoreCard = new ScoreCard();
    MainFrame mainFrame = new MainFrame();
    Multiplayer multiPlayer; // inilize after reading from config file and grabbing max player (post gui selection)
    SaveFile saveFile = new SaveFile();

        
    /**
     * This method works as the main event/game flow for my gui
     */
    void runGame(){
        
        mainFrame.Screen(); // keep at top so obj's instanced in Screen method exist prior other statments
        // using same object that I used in MainFrame
        MenuScreen menu = mainFrame.getMenuObj();
        PlayerScreen player = mainFrame.getPlayerObj();
        GameScreen game = mainFrame.getGameObj();
        ConfigScreen configScreen = mainFrame.getConfigObj();
        EndScreen end = mainFrame.getEndObj();
        
        // setting up configurations based on config text file
        int[] fileArr = config.readingFile();
        configScreen.setConfigLabel("sideOfDieText", "Dice Sides: " + fileArr[0]);
        configScreen.setConfigLabel("diceCountText", "Dice In Hand: " + fileArr[1]);
        configScreen.setConfigLabel("rollsPerTurnText", "Rolls per turn: " + fileArr[2]);


        mainFrame.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Play Button sends to player screen
        JButton playB = menu.getPlayButton();
        playB.addActionListener(e -> {
            mainFrame.switchPlayscreen();
            saveFile.deleteSave();
            menu.showContinue(false);
            end.resetRankingVis();

        });
        JButton configB = menu.getConfigButton();
        configB.addActionListener(e -> {
            mainFrame.switchConfigScreen();
        });

        //Config Buttons
        JButton cSave = configScreen.getCButtons("saveB");
        cSave.addActionListener(e -> {
            // current config
            int[] currentConfigs = config.readingFile();
            // die sides
            String tempSide = configScreen.getInput("sideOfDiceInput");
            int dieSides;
            if (tempSide.isEmpty()){ // this if else handles if user leaves config box empty
                dieSides = currentConfigs[0];
            }
            else {
                dieSides = Integer.parseInt(tempSide);
            }
            if(dieSides != currentConfigs[0]){
                if (dieSides >= 2  && dieSides < 13) {
                    configScreen.setConfigLabel("sideOfDieText", "Dice Sides: " + dieSides);
                    config.writingFile(new int[] {dieSides, currentConfigs[1], currentConfigs[2]}); // writing to config txt file 
                    configScreen.validInput();
                }
                else {
                    configScreen.invalidInput();
                }
            }


            currentConfigs = config.readingFile(); //refresh the current configs
            //number of dice
            String tempDice = configScreen.getInput("dieCountInput");
            int diceAmount;
            if(tempDice.isEmpty()) {
                diceAmount = currentConfigs[1];
            }
            else {
                diceAmount = Integer.parseInt(tempDice);
            }
            if(diceAmount != currentConfigs[1]){
                if (diceAmount >= 1  && diceAmount < 13) {
                    configScreen.setConfigLabel("diceCountText", "Die In Hand: " + diceAmount);
                    config.writingFile(new int[] {currentConfigs[0], diceAmount, currentConfigs[2]}); // writing to config txt file
                    configScreen.validInput();
                }
                else {
                    configScreen.invalidInput();
                }

            }


            currentConfigs = config.readingFile(); //refresh the current configs
            //number of dice
            String tempRolls = configScreen.getInput("rollsPerTurnInput");
            int diceRolls;
            if(tempRolls.isEmpty()) {
                diceRolls = currentConfigs[2];
            }
            else {
                diceRolls = Integer.parseInt(tempRolls);
            }
            if(diceRolls != currentConfigs[2]){
                if (diceRolls >= 1  && diceRolls < 11) {
                    configScreen.setConfigLabel("rollsPerTurnText", "Rolls Per Turn: " + diceRolls);
                    config.writingFile(new int[] {currentConfigs[0], currentConfigs[1], diceRolls}); // writing to config txt file
                    configScreen.validInput();
                }
                else {
                    configScreen.invalidInput();
                }
            }
            // making sure configs update for upcoming game
            int[] cfg = config.readingFile();
            DICE_IN_PLAY = cfg[1];
            rollsPerHand = cfg[2];
            hand = new int[DICE_IN_PLAY];
            keptDice = new boolean[DICE_IN_PLAY];
            die = new GameDie();
            rollsLeft = rollsPerHand;
            rollB.setEnabled(true);
            rollB.setText("Roll");
            game.buildDiceUi(DICE_IN_PLAY, dieListener);
        });
        JButton menuB = configScreen.getCButtons("menuB");
        menuB.addActionListener(e -> {
            configScreen.validInput();
            mainFrame.switchMenuScreen();

        });
        //Player buttons

        //one player
        JButton playerOneButton = player.getOnePlayer();
        playerOneButton.addActionListener(e ->{
            maxPlayers = 1;
            multiPlayer = new Multiplayer(maxPlayers);
            startFreshRound(game);
            mainFrame.switchGameScreen();
        });
        //two player
        JButton playerTwoButton = player.getTwoPlayer();
        playerTwoButton.addActionListener(e ->{
            maxPlayers = 2;
            multiPlayer = new Multiplayer(maxPlayers);
            startFreshRound(game);
            mainFrame.switchGameScreen();
        });
        //three player
        JButton playerThreeButton = player.getThreePlayer();
        playerThreeButton.addActionListener(e->{
            maxPlayers = 3;
            multiPlayer = new Multiplayer(maxPlayers);
            startFreshRound(game);
            mainFrame.switchGameScreen();
        });
        //four player
        JButton playerFourButton = player.getFourPlayer();
        playerFourButton.addActionListener(e->{
            maxPlayers = 4;
            multiPlayer = new Multiplayer(maxPlayers);
            startFreshRound(game);
            mainFrame.switchGameScreen();
        }); 
        
        //
        // Main Game Flow
        //

        // reset all players scoreLines
        //MOVE THIS AFTER IMPELMETNING PLAY AGAIN SO WE ONLY RESET AFTER PLAY AGAIN LISTENER AS THIS WILL 
        // RESET DATA IF PLAYER CLOSES GAME, CAUSING PROBLEMS WITH SAVING AND LOADING GAME DATA
        // scoreCard.resetAllScorecards(maxPlayers);
        
        fileArr = config.readingFile();
        DICE_IN_PLAY = fileArr[1]; 
        rollsPerHand = fileArr[2];
        hand = new int[DICE_IN_PLAY];

        keptDice = new boolean[20];


        // DICE LOGIC
        dieListener = e -> {
            
            JButton clickedBtn = (JButton) e.getSource(); // finds which button from arr was clicked
            int i = Integer.parseInt(e.getActionCommand()); // grabs buttons index in arr
            keptDice[i] = !keptDice[i];

            if (keptDice[i]) {
                clickedBtn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            }
            else {
                clickedBtn.setBorder(null);
            }


        };
        rollsLeft = rollsPerHand;
        rollB = game.getButtons("roll");
        rollB.addActionListener(e -> {
            if (rollsLeft > 0) {
                rollDie(keptDice, hand, game);
                rollsLeft--;
            }
            if (rollsLeft == 0) {
                rollB.setEnabled(false);
                rollB.setText("No Rolls Left");
            }
        });

        game.buildDiceUi(DICE_IN_PLAY, dieListener);

        //SCORE LINE 
        
        //okay and then when they click a score card I just rest rolls left back to max and set rollb back to enabled, also remeber to fix text aswell
        scorelinesUsed = new boolean[13];
        int[] writeNewScore = new int[2];

        // set score labels before any buttons hit
        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));

        
        JButton aces = game.getButtons("aces");
        aces.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);  // grabbing previous score to add score + new button points
            currentCount = 0;
                    for (int diePosition = 0; diePosition < DICE_IN_PLAY; diePosition++)
                        {
                            if (hand[diePosition] == 1) {
                               currentCount++;
                            }
                        }
                        
                        multiPlayer.getPlayer(curPlayer).setUsed(0,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true
                        addedUpdieValue =  1 * currentCount;
                        multiPlayer.getPlayer(curPlayer).setScoreResults(0, addedUpdieValue);
                        tempCurrentScore = checkCurrentScore[0]; //checking upper scorecard score
                        tempCurrentScore += addedUpdieValue; //add current score and new score together
                        writeNewScore[0] = 0; //setting 0 cuz upper scorecard
                        writeNewScore[1] = tempCurrentScore; //write new score to file
                        scoreCard.writingScoreCard(curPlayer, writeNewScore);
                        bonusScoreCheck(game); // checking for bonus 

                        // reseting rolls post scorecard choice
                        Arrays.fill(keptDice, false); // reset kept die before rolling
                        rollsLeft = rollsPerHand - 1;
                        rollB.setEnabled(true);
                        rollB.setText("Roll");
                        for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                            game.getDieButton(i).setBorder(null);
                        }
                        rollDie(keptDice, hand, game); // force a roll after scoreline choice

                        checkGameOver(game, multiPlayer, end);
                        
                // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                        curPlayer++;
                        

                        if (curPlayer > maxPlayers) curPlayer = 1;

                        //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                        // aces.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(0));
                        refreshButtons(game); 

                        // changing label indicating current player turn + score
                        game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                        


        });
        JButton twos = game.getButtons("twos");
        twos.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);  // grabbing previous score to add score + new button points
            currentCount = 0;
                    for (int diePosition = 0; diePosition < DICE_IN_PLAY; diePosition++)
                        {
                            if (hand[diePosition] == 2) {
                               currentCount++;
                            }
                        }
                        multiPlayer.getPlayer(curPlayer).setUsed(1,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true
                        addedUpdieValue =  2 * currentCount;
                        multiPlayer.getPlayer(curPlayer).setScoreResults(1, addedUpdieValue);
                        tempCurrentScore = checkCurrentScore[0]; //checking upper scorecard score
                        tempCurrentScore += addedUpdieValue; //add current score and new score together
                        writeNewScore[0] = 0; //setting 0 cuz upper scorecard
                        writeNewScore[1] = tempCurrentScore; //write new score to file
                        scoreCard.writingScoreCard(curPlayer, writeNewScore);

                        bonusScoreCheck(game); // checking for bonus 

                        // reseting rolls post scorecard choice
                        Arrays.fill(keptDice, false); // reset kept die before rolling
                        rollsLeft = rollsPerHand - 1;
                        rollB.setEnabled(true);
                        rollB.setText("Roll");
                        for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                            game.getDieButton(i).setBorder(null);
                        }
                        rollDie(keptDice, hand, game); // force a roll after scoreline choice
                        checkGameOver(game, multiPlayer, end);

                        // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                        curPlayer++; 
                        if (curPlayer > maxPlayers) curPlayer = 1;

                        //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                        // twos.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(1)); 
                        refreshButtons(game);

                        // changing label indicating current player turn + score
                        game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                        

                        

        });
        JButton threes = game.getButtons("threes");
        threes.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);  // grabbing previous score to add score + new button points
            currentCount = 0;
                    for (int diePosition = 0; diePosition < DICE_IN_PLAY; diePosition++)
                        {
                            if (hand[diePosition] == 3) {
                               currentCount++;
                            }
                        }
                        multiPlayer.getPlayer(curPlayer).setUsed(2,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true
                        addedUpdieValue =  3 * currentCount;
                        multiPlayer.getPlayer(curPlayer).setScoreResults(2, addedUpdieValue);
                        tempCurrentScore = checkCurrentScore[0]; //checking upper scorecard score
                        tempCurrentScore += addedUpdieValue; //add current score and new score together
                        writeNewScore[0] = 0; //setting 0 cuz upper scorecard
                        writeNewScore[1] = tempCurrentScore; //write new score to file
                        scoreCard.writingScoreCard(curPlayer, writeNewScore);

                        bonusScoreCheck(game); // checking for bonus 

                        // reseting rolls post scorecard choice
                        Arrays.fill(keptDice, false); // reset kept die before rolling
                        rollsLeft = rollsPerHand - 1;
                        rollB.setEnabled(true);
                        rollB.setText("Roll");
                        for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                            game.getDieButton(i).setBorder(null);
                        }
                        rollDie(keptDice, hand, game); // force a roll after scoreline choice
                        checkGameOver(game, multiPlayer, end);

                        // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                        curPlayer++;
                        if (curPlayer > maxPlayers) curPlayer = 1;

                        //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                        // threes.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(2)); 
                        refreshButtons(game);

                        // changing label indicating current player turn + score
                        game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                        

                        

        });

        JButton fours = game.getButtons("fours");
        fours.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);  // grabbing previous score to add score + new button points
            currentCount = 0;
                    for (int diePosition = 0; diePosition < DICE_IN_PLAY; diePosition++)
                        {
                            if (hand[diePosition] == 4) {
                               currentCount++;
                            }
                        }
                        multiPlayer.getPlayer(curPlayer).setUsed(3,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true
                        addedUpdieValue =  4 * currentCount;
                        multiPlayer.getPlayer(curPlayer).setScoreResults(3, addedUpdieValue);
                        tempCurrentScore = checkCurrentScore[0]; //checking upper scorecard score
                        tempCurrentScore += addedUpdieValue; //add current score and new score together
                        writeNewScore[0] = 0; //setting 0 cuz upper scorecard
                        writeNewScore[1] = tempCurrentScore; //write new score to file
                        scoreCard.writingScoreCard(curPlayer, writeNewScore);

                        bonusScoreCheck(game); // checking for bonus 

                        // reseting rolls post scorecard choice
                        Arrays.fill(keptDice, false); // reset kept die before rolling
                        rollsLeft = rollsPerHand - 1;
                        rollB.setEnabled(true);
                        rollB.setText("Roll");
                        for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                            game.getDieButton(i).setBorder(null);
                        }
                        rollDie(keptDice, hand, game); // force a roll after scoreline choice
                        checkGameOver(game, multiPlayer, end);

                        // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                        curPlayer++;
                        if (curPlayer > maxPlayers) curPlayer = 1;

                        //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                        // fours.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(3)); 
                        refreshButtons(game);

                        // changing label indicating current player turn + score
                        game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                        


        });
        JButton fives = game.getButtons("fives");
        fives.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);  // grabbing previous score to add score + new button points
            currentCount = 0;
                    for (int diePosition = 0; diePosition < DICE_IN_PLAY; diePosition++)
                        {
                            if (hand[diePosition] == 5) {
                               currentCount++;
                            }
                        }
                        multiPlayer.getPlayer(curPlayer).setUsed(4,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true
                        addedUpdieValue =  5 * currentCount;
                        multiPlayer.getPlayer(curPlayer).setScoreResults(4, addedUpdieValue);
                        tempCurrentScore = checkCurrentScore[0]; //checking upper scorecard score
                        tempCurrentScore += addedUpdieValue; //add current score and new score together
                        writeNewScore[0] = 0; //setting 0 cuz upper scorecard
                        writeNewScore[1] = tempCurrentScore; //write new score to file
                        scoreCard.writingScoreCard(curPlayer, writeNewScore);

                        //updating total score
                        totalScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("upperTotal", "" + totalScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + totalScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (totalScore[0] + totalScore[1]));

                        bonusScoreCheck(game); // checking for bonus 

                        // reseting rolls post scorecard choice
                        Arrays.fill(keptDice, false); // reset kept die before rolling
                        rollsLeft = rollsPerHand - 1;
                        rollB.setEnabled(true);
                        rollB.setText("Roll");
                        for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                            game.getDieButton(i).setBorder(null);
                        }
                        rollDie(keptDice, hand, game); // force a roll after scoreline choice
                        checkGameOver(game, multiPlayer, end);

                        // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                        curPlayer++;
                        if (curPlayer > maxPlayers) curPlayer = 1;

                        //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                        // fives.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(4)); 
                        refreshButtons(game);

                        // changing label indicating current player turn + score
                        game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                        

        });
        JButton sixes = game.getButtons("sixes");
        sixes.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);  // grabbing previous score to add score + new button points
            currentCount = 0;
                    for (int diePosition = 0; diePosition < DICE_IN_PLAY; diePosition++)
                        {
                            if (hand[diePosition] == 6) {
                               currentCount++;
                            }
                        }
                        multiPlayer.getPlayer(curPlayer).setUsed(5,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true
                        addedUpdieValue =  6 * currentCount;
                        multiPlayer.getPlayer(curPlayer).setScoreResults(5, addedUpdieValue);
                        tempCurrentScore = checkCurrentScore[0]; //checking upper scorecard score
                        tempCurrentScore += addedUpdieValue; //add current score and new score together
                        writeNewScore[0] = 0; //setting 0 cuz upper scorecard
                        writeNewScore[1] = tempCurrentScore; //write new score to file
                        scoreCard.writingScoreCard(curPlayer, writeNewScore);

                        bonusScoreCheck(game); // checking for bonus 

                        // reseting rolls post scorecard choice
                        Arrays.fill(keptDice, false); // reset kept die before rolling
                        rollsLeft = rollsPerHand - 1;
                        rollB.setEnabled(true);
                        rollB.setText("Roll");
                        for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                            game.getDieButton(i).setBorder(null);
                        }
                        rollDie(keptDice, hand, game); // force a roll after scoreline choice
                            checkGameOver(game, multiPlayer, end);

                        // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                        curPlayer++;
                        if (curPlayer > maxPlayers) curPlayer = 1;

                        //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                        // sixes.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(5)); 
                        refreshButtons(game);

                        // changing label indicating current player turn + score
                        game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                        game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                        game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                        game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                        game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                        

        });

        JButton threeK = game.getButtons("threeK");
        threeK.addActionListener(e -> {
            if (hands.maxOfAKindFound(hand) >= 3)
                {
                    multiPlayer.getPlayer(curPlayer).setScoreResults(6, die.totalAllDice(hand));
                    tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
                    tempCurrentScore+= die.totalAllDice(hand); 
                    writeNewScore[0] = 1;
                    writeNewScore[1] = tempCurrentScore; //writing to lower scorecard
                    scoreCard.writingScoreCard(curPlayer, writeNewScore);
                }
            else {
                    multiPlayer.getPlayer(curPlayer).setScoreResults(6, 0);
                }
                multiPlayer.getPlayer(curPlayer).setUsed(6,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

                // reseting rolls post scorecard choice
                Arrays.fill(keptDice, false); // reset kept die before rolling
                rollsLeft = rollsPerHand - 1;
                rollB.setEnabled(true);
                rollB.setText("Roll");
                for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                    game.getDieButton(i).setBorder(null);
                }
                rollDie(keptDice, hand, game); // force a roll after scoreline choice
                checkGameOver(game, multiPlayer, end);
                // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                curPlayer++;
                if (curPlayer > maxPlayers) curPlayer = 1;

                //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                // threeK.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(6)); 
                refreshButtons(game);

                // changing label indicating current player turn + score
                game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                



        });
        JButton fourK = game.getButtons("fourK");
        fourK.addActionListener(e -> {
            if (hands.maxOfAKindFound(hand) >= 4)
                {
                    multiPlayer.getPlayer(curPlayer).setScoreResults(7, die.totalAllDice(hand));
                    tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
                    tempCurrentScore+= die.totalAllDice(hand); 
                    writeNewScore[0] = 1;
                    writeNewScore[1] = tempCurrentScore; //writing to lower scorecard
                    scoreCard.writingScoreCard(curPlayer, writeNewScore);
                }
                else {
                    multiPlayer.getPlayer(curPlayer).setScoreResults(7, 0);
                }
                multiPlayer.getPlayer(curPlayer).setUsed(7,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

                // reseting rolls post scorecard choice
                Arrays.fill(keptDice, false); // reset kept die before rolling
                rollsLeft = rollsPerHand - 1;
                rollB.setEnabled(true);
                rollB.setText("Roll");
                for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                    game.getDieButton(i).setBorder(null);
                }
                rollDie(keptDice, hand, game); // force a roll after scoreline choice
                checkGameOver(game, multiPlayer, end);

                // After each scoreline we increment to next player, if we increment more then max players go back to player one       
                curPlayer++;
                if (curPlayer > maxPlayers) curPlayer = 1;

                //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
                // fourK.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(7)); 
                refreshButtons(game);

                // changing label indicating current player turn + score
                game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
                checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
                game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
                game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
                game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
                game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
                


        });
        JButton fullHouse = game.getButtons("fullHouse");
        fullHouse.addActionListener(e -> {
            if (hands.fullHouseFound(hand)) {

                multiPlayer.getPlayer(curPlayer).setScoreResults(8, 25);
                tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
                tempCurrentScore += 25;
                writeNewScore[0] = 1;
                writeNewScore[1] = tempCurrentScore ;
                scoreCard.writingScoreCard(curPlayer, writeNewScore);
            }

            else {
                multiPlayer.getPlayer(curPlayer).setScoreResults(8,0);

            }
            multiPlayer.getPlayer(curPlayer).setUsed(8,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

            // reseting rolls post scorecard choice
            Arrays.fill(keptDice, false); // reset kept die before rolling
            rollsLeft = rollsPerHand - 1;
            rollB.setEnabled(true);
            rollB.setText("Roll");
            for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                game.getDieButton(i).setBorder(null);
            }
            rollDie(keptDice, hand, game); // force a roll after scoreline choice
            checkGameOver(game, multiPlayer, end);

            // After each scoreline we increment to next player, if we increment more then max players go back to player one       
            curPlayer++;
            if (curPlayer > maxPlayers) curPlayer = 1;

            //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
            // fullHouse.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(8)); 
            refreshButtons(game);

            // changing label indicating current player turn + score
            game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
            game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
            game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
            game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
            


        });

        JButton smStraight = game.getButtons("smStraight");
        smStraight.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            sort.sortArray(hand, DICE_IN_PLAY); 
            if (hands.maxStraightFound(hand) >= 4) {
                multiPlayer.getPlayer(curPlayer).setScoreResults(9, 30);
                tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
                tempCurrentScore += 30;
                writeNewScore[0] = 1;
                writeNewScore[1] = tempCurrentScore;
                scoreCard.writingScoreCard(curPlayer, writeNewScore);
            }
            else {
                multiPlayer.getPlayer(curPlayer).setScoreResults(9, 0);
            }
            multiPlayer.getPlayer(curPlayer).setUsed(9,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

            // reseting rolls post scorecard choice
            Arrays.fill(keptDice, false); // reset kept die before rolling
            rollsLeft = rollsPerHand - 1;
            rollB.setEnabled(true);
            rollB.setText("Roll");
            for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                game.getDieButton(i).setBorder(null);
            }
            rollDie(keptDice, hand, game); // force a roll after scoreline choice
            checkGameOver(game, multiPlayer, end);

            // After each scoreline we increment to next player, if we increment more then max players go back to player one       
            curPlayer++;
            if (curPlayer > maxPlayers) curPlayer = 1;

            //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
            // smStraight.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(9)); 
            refreshButtons(game);

            // changing label indicating current player turn + score
            game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
            game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
            game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
            game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
            

            
        });

        JButton lgStraight = game.getButtons("lgStraight");
        lgStraight.addActionListener(e -> {
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            sort.sortArray(hand, DICE_IN_PLAY);
            if (hands.maxStraightFound(hand) >= 5) {
                multiPlayer.getPlayer(curPlayer).setScoreResults(10, 40);
                tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
                tempCurrentScore += 40;
                writeNewScore[0] = 1;
                writeNewScore[1] = tempCurrentScore;
                scoreCard.writingScoreCard(curPlayer, writeNewScore);


            }
            else {
                multiPlayer.getPlayer(curPlayer).setScoreResults(10, 0);
            }
            multiPlayer.getPlayer(curPlayer).setUsed(10,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

            // reseting rolls post scorecard choice
            Arrays.fill(keptDice, false); // reset kept die before rolling
            rollsLeft = rollsPerHand - 1;
            rollB.setEnabled(true);
            rollB.setText("Roll");
            for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                game.getDieButton(i).setBorder(null);
            }
            rollDie(keptDice, hand, game); // force a roll after scoreline choice
            checkGameOver(game, multiPlayer, end);

            // After each scoreline we increment to next player, if we increment more then max players go back to player one       
            curPlayer++;
            if (curPlayer > maxPlayers) curPlayer = 1;

            //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
            // lgStraight.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(10)); 
            refreshButtons(game);

            // changing label indicating current player turn + score
            game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
            game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
            game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
            game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
            

            
        });
        JButton yahtzeeLine = game.getButtons("yahtzeeLine");
        yahtzeeLine.addActionListener(e -> {
            if (hands.maxOfAKindFound(hand) >= 5) {
                multiPlayer.getPlayer(curPlayer).setScoreResults(11, 50);
                tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
                tempCurrentScore += 50;
                writeNewScore[0] = 1;
                writeNewScore[1] = tempCurrentScore;
                scoreCard.writingScoreCard(curPlayer, writeNewScore);

                
            }
            else {
                multiPlayer.getPlayer(curPlayer).setScoreResults(11, 0);
            }
            multiPlayer.getPlayer(curPlayer).setUsed(11,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

            // reseting rolls post scorecard choice
            Arrays.fill(keptDice, false); // reset kept die before rolling
            rollsLeft = rollsPerHand - 1;
            rollB.setEnabled(true);
            rollB.setText("Roll");
            for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                game.getDieButton(i).setBorder(null);
            }
            rollDie(keptDice, hand, game); // force a roll after scoreline choice
            checkGameOver(game, multiPlayer, end);

            // After each scoreline we increment to next player, if we increment more then max players go back to player one       
            curPlayer++;
            if (curPlayer > maxPlayers) curPlayer = 1;

            //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
            // yahtzeeLine.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(11)); 
            refreshButtons(game);

            refreshButtons(game);

            // changing label indicating current player turn + score
            game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
            game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
            game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
            game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
            

        });
        JButton chance = game.getButtons("chance");
        chance.addActionListener(e -> {
            writeNewScore[0] = 1;
            multiPlayer.getPlayer(curPlayer).setUsed(12,true); //grab player ref of current player, then set their used scorecard for aces(index 0) to true

            multiPlayer.getPlayer(curPlayer).setScoreResults(12, die.totalAllDice(hand));
            tempCurrentScore = checkCurrentScore[1];//checking lower scorecard score
            tempCurrentScore += die.totalAllDice(hand);
            writeNewScore[0] = 1;
            writeNewScore[1] = tempCurrentScore;
            scoreCard.writingScoreCard(curPlayer, writeNewScore);

            // reseting rolls post scorecard choice
            Arrays.fill(keptDice, false); // reset kept die before rolling
            rollsLeft = rollsPerHand - 1;
            rollB.setEnabled(true);
            rollB.setText("Roll");
            for (int i = 0; i < DICE_IN_PLAY; i++) { // reseting green selected borders
                game.getDieButton(i).setBorder(null);
            }
            rollDie(keptDice, hand, game); // force a roll after scoreline choice
            checkGameOver(game, multiPlayer, end);

            // After each scoreline we increment to next player, if we increment more then max players go back to player one       
            curPlayer++;
            if (curPlayer > maxPlayers) curPlayer = 1;

            //Activate scoreline using nxt players usedScoreLine array, also using ! because we want to set button to true(enabled) if scorelineUsed is false
            // chance.setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(12)); 
            refreshButtons(game);

            // changing label indicating current player turn + score
            game.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");
            checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
            game.updateScoreLabels("bonus", (checkCurrentScore[0] >= 98) ? "✓" : "X");
            game.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
            game.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
            game.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));
            

        });

        // Save and Continue
        // hides the continueButton if a save doesn't exist
        mainFrame.getMenuObj().showContinue(saveFile.saveExists());
        
        // saveButton listener
        mainFrame.getGameObj().getButtons("save").addActionListener(e -> {
            saveFile.writeSave(rollsLeft, hand, curPlayer, maxPlayers, multiPlayer);
            JOptionPane.showMessageDialog(null, "Game Saved!");
        });

        // continueButton listener
        mainFrame.getMenuObj().getContinueButton().addActionListener(e -> {
            // System.out.println("Continue button clicked");
            int[][] saved = saveFile.readSave(maxPlayers);
            System.out.println("Save data: " + (saved != null ? "loaded" : "null"));
            if (saved != null) {
                try {
                    loadGameState(saved, game);
                    // System.out.println("loadGameState completed");
                    mainFrame.switchGameScreen();
                    // System.out.println("switched to game screen");
                } catch (Exception ex) {
                    ex.printStackTrace(); // prints the full error to console
                }
            }
        });
        

        //END SCREEN PANEL
        JButton endPlayAgain = end.getPlayAgainButton();
        endPlayAgain.addActionListener(e-> {
            saveFile.deleteSave();
            mainFrame.getMenuObj().showContinue(false);
            end.resetRankingVis(); // reset rankings 
            mainFrame.switchPlayscreen();

        });
        JButton endConfigButton = end.getEndConfigButton();
        endConfigButton.addActionListener(e-> {
            mainFrame.switchConfigScreen();
        });


        JButton endExitButton = end.getEndExitButton();
        endExitButton.addActionListener(e-> {
            System.exit(0);
            
        });

        
    }
    /**
     * This method updates dice after a roll including images
     * @param game takes in a gameObj refrence
     * @param keptDie takes in array of players kept die before a roll
     * @param hand takes in a array of current dice hand
     */
    void rollDie(boolean[] keptDice, int[] hand, GameScreen game) {
        for (int i = 0; i < DICE_IN_PLAY; i++) {
            if (!keptDice[i]) {
                int rolledVal = die.rollDie();
                hand[i] = rolledVal;
                JButton btn = game.getDieButton(i);
                //formatting die images and changing path to them dpeending on the roll 
                String path = "src/main/resources/YahtzeeMedia/Dice/d6-" + rolledVal + ".png";
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
            }


        }
    }
    /**
     * This method checks if player has reached bonus scoreline
     * @param gameObj takes in a gameObj refrence
     */
    void bonusScoreCheck(GameScreen gameObj) {

        int[] scores = scoreCard.readingScoreCard(curPlayer);
        int upper = scores[0];
        if (upper >= 63 && upper < 98) { // this way as after we add bonus it will always be 98 or bigger aftwards so no repeated bonus's given
            upper += 35;
            scoreCard.writingScoreCard(curPlayer, new int[]{0, upper});
            gameObj.updateScoreLabels("bonus", (upper >= 98) ? "✓" : "X"); // set label if bonus is set
            gameObj.updateScoreLabels("upperTotal", "" + upper);
            gameObj.updateScoreLabels("grandTotal", "" + (upper + scores[1]));

//bonusAdded
        }

    }
    /**
     * This method checks if game has reached the end
     * @param gameObj takes in a gameObj refrence
     * @param muliplayerObj takes in mutliplayer refrecne
     */
    private void checkGameOver(GameScreen gameObj, Multiplayer mutliplayerObj, EndScreen end) {
        boolean allFilled = true;
        for(int i = 0; i < maxPlayers; i++) { // i index used to represent all possible players, (i+1 cuz players are 1 indexed)
            for (boolean used : mutliplayerObj.getPlayer(i+1).getUsedArr()) {
                if (!used) {
                    allFilled = false;
                    break; 
                }
            } 
        }

        //compare cur player and maxPlayer so we only end game after the last players turn
        if(allFilled && curPlayer == maxPlayers) { // if all scorelines used/true then end game 
            rollB.setEnabled(false); // turn off roll button
            
            
            // Calculate winner 
            int[] finalScores = new int[maxPlayers];
            for (int i = 1; i <= maxPlayers; i++){
                int[] readingFinalScores = scoreCard.readingScoreCard(i);
                finalScores[i-1] = readingFinalScores[0]+readingFinalScores[1];

            }

            int[] rank = new int[maxPlayers];
            for (int i = 0; i < maxPlayers; i++) {
                rank[i] = i;
            }

            for (int i = 1; i < maxPlayers; i++) {
                int k = rank[i];
                int j = i-1;

                while (j >= 0 && finalScores[rank[j]] < finalScores[k]) {
                    rank[j + 1] = rank[j];
                    j--;
                }
                rank[j + 1] = k;
            }


            end.setWinnerLabel(rank[0]+1);  // +1 because player is 1 indexed 
            end.setFirstPlace(rank[0]+1, finalScores[rank[0]]);

            if (maxPlayers >= 2) end.setSecondPlace(rank[1]+1, finalScores[rank[1]]);
            if (maxPlayers >= 3) end.setThirdPlace(rank[2]+1, finalScores[rank[2]]);
            if (maxPlayers >= 4) end.setFourthPlace(rank[3]+1, finalScores[rank[3]]);
            
            mainFrame.switchEndScreen();
        }
    }
    private void loadGameState(int[][] saved, GameScreen gameObj) {
        System.out.println("saved array length: " + saved.length);
        for (int i = 0; i < saved.length; i++) {
            System.out.println("saved[" + i + "] length: " + saved[i].length);
        }
        rollsLeft  = saved[0][0];
        curPlayer  = saved[0][1];
        maxPlayers = saved[0][2];
        multiPlayer = new Multiplayer(maxPlayers);
        hand       = saved[1];
        DICE_IN_PLAY = hand.length;

        // restore each player's used lines and score results
        for (int p = 1; p <= maxPlayers; p++) {
            int[] usedRaw = saved[2 + (p - 1) * 2];
            int[] scores  = saved[2 + (p - 1) * 2 + 1];

            for (int i = 0; i < 13; i++) {
                multiPlayer.getPlayer(p).setUsed(i, usedRaw[i] == 1);
                multiPlayer.getPlayer(p).setScoreResults(i, scores[i]);
            }
        }

        // restore UI for current player's score labels
        String[] scoreKeys = {
            "acesScore", "twosScore", "threesScore", "foursScore", "fivesScore", "sixesScore",
            "threeKScore", "fourKScore", "scoreFullHouse", "scoreSmStraight",
            "scoreLgStraight", "scoreYahtzee", "scoreChance"
        };
        String[] buttonKeys = {
            "aces", "twos", "threes", "fours", "fives", "sixes",
            "threeK", "fourK", "fullHouse", "smStraight", "lgStraight", "yahtzeeLine", "chance"
        };

        // show current player's scores and disable buttons they've already used
        int[] curScores = multiPlayer.getPlayer(curPlayer).getScoreLineResults();
        for (int i = 0; i < 13; i++) {
            gameObj.updateScoreLabels(scoreKeys[i], "Score is " + curScores[i]);
            gameObj.getButtons(buttonKeys[i]).setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(i));
        }

        // restore totals from scorecard file
        checkCurrentScore = scoreCard.readingScoreCard(curPlayer);
        gameObj.updateScoreLabels("upperTotal", "" + checkCurrentScore[0]);
        gameObj.updateScoreLabels("lowerTotal", "" + checkCurrentScore[1]);
        gameObj.updateScoreLabels("grandTotal", "" + (checkCurrentScore[0] + checkCurrentScore[1]));

        // restore player turn label
        gameObj.updateScoreLabels("playerTurn", "Player " + curPlayer + "'s Turn");

        // restore roll button state
        if (rollsLeft == 0) {
            rollB.setEnabled(false);
            rollB.setText("No Rolls Left");
        } else {
            rollB.setEnabled(true);
            rollB.setText("Roll");
        }

        // rebuild dice UI and restore die images
        gameObj.buildDiceUi(DICE_IN_PLAY, dieListener);
        for (int i = 0; i < DICE_IN_PLAY; i++) {
            String path = "src/main/resources/YahtzeeMedia/Dice/d6-" + hand[i] + ".png";
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            gameObj.getDieButton(i).setIcon(new ImageIcon(img));
        }

        Arrays.fill(keptDice, false);
    }

    private void refreshButtons(GameScreen gameObj) {
        String[] buttonKeys = {
            "aces", "twos", "threes", "fours", "fives", "sixes",
            "threeK", "fourK", "fullHouse", "smStraight", "lgStraight", "yahtzeeLine", "chance"
        };
        for (int i = 0; i < 13; i++) {
            gameObj.getButtons(buttonKeys[i]).setEnabled(!multiPlayer.getPlayer(curPlayer).getUsed(i));
        }
    }

    private void startFreshRound(GameScreen gameObj) {
        scoreCard.resetAllScorecards(maxPlayers);
        curPlayer = 1;

        int[] configs = config.readingFile();
        DICE_IN_PLAY = configs[1];
        rollsPerHand = configs[2];
        hand = new int[DICE_IN_PLAY];
        keptDice = new boolean[DICE_IN_PLAY];
        rollsLeft = rollsPerHand;

        gameObj.buildDiceUi(DICE_IN_PLAY, dieListener);
        for (int i = 0; i < DICE_IN_PLAY; i++) {
            gameObj.getDieButton(i).setIcon(null);
            gameObj.getDieButton(i).setBorder(null);
        }

        refreshButtons(gameObj);
        gameObj.updateScoreLabels("playerTurn", "Player 1's Turn");
        gameObj.updateScoreLabels("bonus", "X");
        gameObj.updateScoreLabels("upperTotal", "0");
        gameObj.updateScoreLabels("lowerTotal", "0");
        gameObj.updateScoreLabels("grandTotal", "0");

        rollB.setEnabled(true);
        rollB.setText("Roll");
    }
    

    
}
