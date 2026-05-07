package yahtzee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * The class holds the label for game screen
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GameScreen {
    // transfer everything from gameGUI to here 

    private JPanel gamePanel;
    private JPanel diePanel;
    private HashMap<String, JLabel> labels = new HashMap<>();
    private HashMap<String, JButton> buttons = new HashMap<>();
    private ArrayList<JButton> diceButtons = new ArrayList<>();
    /**
     * Method builds a constructor for my game screen panel
     * 
     */
    public GameScreen() {
        gamePanel = new JPanel(new BorderLayout());

        Font headerFont = new Font("Serif", Font.BOLD | Font.ITALIC, 23);
        Font scoreTextFont = new Font("SansSerif", Font.BOLD, 16);

        // card layout allows me to make difference screens like menu screen and game screen
        JPanel mainPanel = new JPanel(new CardLayout());
        CardLayout cl = (CardLayout)(mainPanel.getLayout());

        // scorecard panel
        JPanel scoreCardPanel = new JPanel();
        scoreCardPanel.setLayout(new GridLayout(0,2, 10, 5));
        scoreCardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        scoreCardPanel.setPreferredSize(new Dimension(300,0));
        gamePanel.add(scoreCardPanel, BorderLayout.WEST);
        scoreCardPanel.setBackground(Color.LIGHT_GRAY);

        // upper scoreline
        JLabel upperSec = new JLabel("Upper Section");
        upperSec.setFont(headerFont);
        scoreCardPanel.add(upperSec);
        scoreCardPanel.add(new JLabel("")); // empty filler label for formating

        //scoreline one
        JButton scoreLineOne = new JButton("Aces");
        scoreCardPanel.add(scoreLineOne);
        buttons.put("aces", scoreLineOne); // adding refrence to buttons hashmap
        JLabel scoreOne = new JLabel("");
        scoreCardPanel.add(scoreOne);
        scoreOne.setFont(scoreTextFont);
        labels.put("acesScore", scoreOne); // adding refrence to labels hashmap


        JButton scoreLineTwo = new JButton("Twos");
        scoreCardPanel.add(scoreLineTwo);
        buttons.put("twos", scoreLineTwo);
        JLabel scoreTwo = new JLabel("");
        scoreCardPanel.add(scoreTwo);
        scoreTwo.setFont(scoreTextFont);
        labels.put("twosScore", scoreTwo);

        JButton scoreLineThree = new JButton("Threes");
        scoreCardPanel.add(scoreLineThree);
        buttons.put("threes", scoreLineThree);
        JLabel scoreThree = new JLabel("");
        scoreCardPanel.add(scoreThree);
        scoreThree.setFont(scoreTextFont);
        labels.put("threesScore", scoreThree);

        JButton scoreLineFour = new JButton("Fours");
        scoreCardPanel.add(scoreLineFour);
        buttons.put("fours", scoreLineFour);
        JLabel scoreFour = new JLabel("");
        scoreCardPanel.add(scoreFour);
        scoreFour.setFont(scoreTextFont);
        labels.put("foursScore", scoreFour);

        JButton scoreLineFive = new JButton("Fives");
        scoreCardPanel.add(scoreLineFive);
        buttons.put("fives", scoreLineFive);
        JLabel scoreFive = new JLabel("");
        scoreCardPanel.add(scoreFive);
        scoreFive.setFont(scoreTextFont);
        labels.put("fivesScore", scoreFive);

        JButton scoreLineSix = new JButton("Sixes");
        scoreCardPanel.add(scoreLineSix);
        buttons.put("sixes", scoreLineSix);
        JLabel scoreSix = new JLabel("");
        scoreCardPanel.add(scoreSix);
        scoreSix.setFont(scoreTextFont);
        labels.put("sixesScore", scoreSix);

        JLabel upperTotalText = new JLabel("  Upper Total");
        scoreCardPanel.add(upperTotalText);
        JLabel upperTotal = new JLabel("0"); //upper Total is a INT val of score of upper total 
        scoreCardPanel.add(upperTotal);
        upperTotalText.setFont(scoreTextFont);
        upperTotal.setFont(scoreTextFont);
        labels.put("upperTotal", upperTotal);


        JLabel scoreBonusText = new JLabel("  Bonus");
        scoreCardPanel.add(scoreBonusText);
        JLabel scoreBonus = new JLabel("X");
        scoreCardPanel.add(scoreBonus);
        scoreBonus.setFont(scoreTextFont);
        scoreBonusText.setFont(scoreTextFont);
        labels.put("bonus", scoreBonus);


        // Lower Scoreline
        JLabel lowerScore = new JLabel("Lower Section");
        scoreCardPanel.add(lowerScore);
        lowerScore.setFont(headerFont);
        scoreCardPanel.add(new JLabel("")); // empty filler label for formating
        

        
        JButton threeOfKind = new JButton("Three of a Kind");
        scoreCardPanel.add(threeOfKind);
        buttons.put("threeK", threeOfKind);
        JLabel scoreThreeKind = new JLabel("");
        scoreCardPanel.add(scoreThreeKind);
        scoreThreeKind.setFont(scoreTextFont);
        labels.put("threeKScore", scoreThreeKind);
        
        



        JButton fourOfKind = new JButton("Four of a Kind");
        scoreCardPanel.add(fourOfKind);
        buttons.put("fourK", fourOfKind);
        JLabel scoreFourKind = new JLabel("");
        scoreCardPanel.add(scoreFourKind);
        scoreFourKind.setFont(scoreTextFont);
        labels.put("fourKScore", scoreFourKind);

        JButton fullHouse = new JButton("Full House");
        scoreCardPanel.add(fullHouse);
        buttons.put("fullHouse", fullHouse);
        JLabel scoreFullHouse= new JLabel("");
        scoreCardPanel.add(scoreFullHouse);
        scoreFullHouse.setFont(scoreTextFont);
        labels.put("scoreFullHouse", scoreFullHouse);
        

        JButton smStraight = new JButton("Small Straight");
        scoreCardPanel.add(smStraight);
        buttons.put("smStraight", smStraight);
        JLabel scoreSmStraight = new JLabel("");
        scoreCardPanel.add(scoreSmStraight);
        scoreSmStraight.setFont(scoreTextFont);
        labels.put("scoreSmStraight", scoreSmStraight);

        JButton lgStraight = new JButton("Large Straight");
        scoreCardPanel.add(lgStraight);
        buttons.put("lgStraight", lgStraight);
        JLabel scoreLgStraight = new JLabel("");
        scoreCardPanel.add(scoreLgStraight);
        scoreLgStraight.setFont(scoreTextFont);
        labels.put("scoreLgStraight", scoreLgStraight);


        JButton yahtzeeLine = new JButton("Yahtzee");
        scoreCardPanel.add(yahtzeeLine);
        buttons.put("yahtzeeLine", yahtzeeLine);
        JLabel scoreYahtzee = new JLabel("");
        scoreCardPanel.add(scoreYahtzee);
        scoreYahtzee.setFont(scoreTextFont);
        labels.put("scoreYahtzee", scoreYahtzee);

        JButton chance = new JButton("Chance");
        scoreCardPanel.add(chance);
        buttons.put("chance", chance);
        JLabel scoreChance = new JLabel("");
        scoreCardPanel.add(scoreChance);
        scoreChance.setFont(scoreTextFont);
        labels.put("scoreChance", scoreChance);

        JLabel lowerTotalText = new JLabel("  Lower Total");
        scoreCardPanel.add(lowerTotalText);
        JLabel lowerTotal = new JLabel("0");
        lowerTotal.setFont(scoreTextFont);
        lowerTotalText.setFont(scoreTextFont);
        scoreCardPanel.add(lowerTotal);
        labels.put("lowerTotal", lowerTotal);



        JLabel grandTotalText = new JLabel(" GRAND TOTAL");
        scoreCardPanel.add(grandTotalText);
        JLabel grandTotal = new JLabel("0");
        scoreCardPanel.add(grandTotal);
        grandTotal.setFont(new Font("SansSerif", Font.BOLD, 17));
        grandTotalText.setFont(new Font("SansSerif", Font.BOLD, 17));
        labels.put("grandTotal", grandTotal);
    
    
        // DICE

        // Die Panel
        diePanel = new JPanel();
        diePanel.setPreferredSize(new Dimension(400,0));
        gamePanel.add(diePanel, BorderLayout.EAST);
        diePanel.setBackground(Color.LIGHT_GRAY);
        diePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));







        // center panel
        JPanel center = new JPanel(); // possibly use borderlayout() here 

        gamePanel.add(center, BorderLayout.CENTER);
        JLabel playerTurn = new JLabel("Player 1's Turn");
        playerTurn.setFont(new Font("SansSerif", Font.BOLD, 30));
        labels.put("playerTurn", playerTurn);
        center.add(playerTurn);
        center.setBackground(new Color(173, 216, 230));

        JButton dieRerollButton = new JButton("Roll");
        dieRerollButton.setFont(new Font("SansSerif", Font.BOLD, 26));
        center.add(dieRerollButton);
        buttons.put("roll", dieRerollButton);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 26));
        center.add(saveButton);
        buttons.put("save", saveButton);

        JLabel gameOverLabel = new JLabel("");
        gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.RED);
        center.add(gameOverLabel);
        labels.put("gameOverLabel", gameOverLabel);

        gamePanel.add(center, BorderLayout.CENTER);




    }
     /**
     * Method works as a getter for a button inside the buttons hashmap
     * @param key this takes in a hashmap key
     */
    public JButton getButtons(String key){
        return buttons.get(key); // getter for button refrences in hash map

    }
    /**
     * Method works as a getter for a labels inside the labels hashmap
     * @param key this takes in a hashmap key
     */
    public JLabel getLabels(String key){
        return labels.get(key); // getter for label refrences in hash map
    }
    /**
     * Method works to update the text inside a label
     * @param key this takes in a hashmap key
     * @param scoreText this takes in the new text that will be used to update the label
     */
    public void updateScoreLabels(String key, String scoreText){
        labels.get(key).setText(scoreText);
    }
    /**
     * Method wroks as a getter for game panel
     */
    public JPanel getGamePanel() {
        return gamePanel;
    }
    /**
     * Method works to build my die pictures/buttons depedning on the configs
     * @param diceCount the configurations of how many die to play with
     * @param listener takes in a listener so we can build die layout to fix diecount
     */
    public void buildDiceUi(int diceCount, ActionListener listener) {
        diePanel.removeAll();
        diceButtons.clear();

        for (int i = 0; i < diceCount; i++) {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(100,100));
            btn.setActionCommand(String.valueOf(i));

            diceButtons.add(btn);
            diePanel.add(btn);
            btn.addActionListener(listener);
        }
        diePanel.revalidate();
        diePanel.repaint();
        


    }
    /**
     * Method works asa getter for die buttons array
     * @param index this takes in a index to use to search through array
     */
    public JButton getDieButton(int index) {
        return diceButtons.get(index);
    }

    
}
