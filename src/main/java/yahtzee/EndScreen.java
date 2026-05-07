package yahtzee;

import java.awt.*;

import javax.swing.*;

public class EndScreen {

    private JPanel endPanel;
    private JButton playAgainButton;
    private JButton endConfigButton;
    private JButton exitButton;
    private JLabel winnerTitle;
    private JLabel firstPlace;
    private JLabel secondPlace;
    private JLabel thirdPlace;
    private JLabel fourthPlace;

    public EndScreen() {

        endPanel = new JPanel(new GridBagLayout());

        // gbc allows us to control grid bag layout and move labels and button positions
        GridBagConstraints gbc = new GridBagConstraints();

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 40));
        endConfigButton = new JButton("Configurations");
        endConfigButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 30));


        endPanel.setBackground(Color.LIGHT_GRAY);

        winnerTitle = new JLabel("GAME OVER");
        winnerTitle.setFont(new Font("SansSerif", Font.BOLD, 100));
        
        gbc.gridx = 0; // center in middle
        gbc.gridy = 0; // first row 
        gbc.weighty = 0.1;
        gbc.insets = new Insets(50, 0, 0, 0); // adding filler space/gap bellow title
        endPanel.add(winnerTitle, gbc);


        JPanel playerRanks = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;


        playerRanks.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        playerRanks.setBackground(Color.GRAY);
        playerRanks.setPreferredSize(new Dimension(300,350));
        // playerRanks.setLayout(new BoxLayout(playerRanks, BoxLayout.Y_AXIS));
        playerRanks.setLayout(new GridLayout(5, 1)); // maybe adjust the 4 based on max players??

        // maybe add weight and isets but its in the middle so kinda akward
        // also needs to display depending on max players 
        JLabel rankingTitle = new JLabel("RANKINGS");
        firstPlace = new JLabel("Player 1 : 60");
        secondPlace = new JLabel("Player 2 : 50");
        thirdPlace = new JLabel("Player 3 : 40");
        fourthPlace = new JLabel("Player 4 : 30");

        //only show based on current max player count
        secondPlace.setVisible(false);
        thirdPlace.setVisible(false);
        fourthPlace.setVisible(false);

        rankingTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        firstPlace.setFont(new Font("SansSerif", Font.BOLD, 30));
        secondPlace.setFont(new Font("SansSerif", Font.BOLD, 30));
        thirdPlace.setFont(new Font("SansSerif", Font.BOLD, 30));
        fourthPlace.setFont(new Font("SansSerif", Font.BOLD, 30));


        playerRanks.add(rankingTitle);
        playerRanks.add(firstPlace);
        playerRanks.add(secondPlace);
        playerRanks.add(thirdPlace);
        playerRanks.add(fourthPlace);
        gbc.insets = new Insets(0,0,0,0); // reset prevous filler config from above
        endPanel.add(playerRanks, gbc); // adding panel to panel




        JPanel menuOptions = new JPanel();
        menuOptions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        menuOptions.setPreferredSize(new Dimension(300,300));
        menuOptions.setBackground(Color.LIGHT_GRAY);
        menuOptions.add(playAgainButton);
        menuOptions.add(endConfigButton);
        menuOptions.add(exitButton);
        menuOptions.setLayout(new GridLayout(3, 1));

        gbc.gridx = 0; // center in middle
        gbc.gridy = 2; // second row below title
        gbc.weighty = 0.9;
        gbc.insets = new Insets(20,0,0,0); // reset prevous filler config from above
        endPanel.add(menuOptions, gbc); // adding panel to panel

    }

    /**
    * Method works as a getter for the endPanel field
    */
    public JPanel getEndPanel() {
        return endPanel;
    }
    /**
    * Method works as a getter for the playButton private field
    */
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }
     /**
     * Method works as a getter for the configButton button
     */
    public JButton getEndConfigButton() {
        return endConfigButton;
    }

    public JButton getEndExitButton(){
        return exitButton;
    }
    
    public void setWinnerLabel(int winningPlayer){
        winnerTitle.setText("PLAYER " + winningPlayer + " WINS");
    }
    
    // maybe set labels to hidden then set then visible in methdos below so we only show rankings for the max players like max player = 3 we dont need foruth place
    public void setFirstPlace(int firstPlaceID, int score) {
        firstPlace.setText("Player " + firstPlaceID + " : " + score);
    }

    public void setSecondPlace(int firstPlaceID, int score) {
        secondPlace.setText("Player " + firstPlaceID + " : " + score);
        secondPlace.setVisible(true);
    }


    public void setThirdPlace(int firstPlaceID, int score) {
        thirdPlace.setText("Player " + firstPlaceID + " : " + score);
        thirdPlace.setVisible(true);

    }

    public void setFourthPlace(int firstPlaceID, int score) {
        fourthPlace.setText("Player " + firstPlaceID + " : " + score);
        fourthPlace.setVisible(true);

    }
    public void resetRankingVis(){
        secondPlace.setVisible(false);
        thirdPlace.setVisible(false);
        fourthPlace.setVisible(false);
    }
}
