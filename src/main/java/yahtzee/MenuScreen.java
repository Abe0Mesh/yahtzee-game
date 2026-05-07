package yahtzee;

import java.awt.*;

import javax.swing.*;
/**
 * The class holds the label for my menu screen
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class MenuScreen {
    private JPanel menuPanel;
    private JButton playButton;
    private JButton menuConfigButton;
    private JButton continueButton;

    /**
     * Method builds a constructor for my menu screen panel
     * 
     */
    public MenuScreen() {
        menuPanel = new JPanel(new GridBagLayout());

        // gbc allows us to control grid bag layout and move labels and button positions
        GridBagConstraints gbc = new GridBagConstraints();
        playButton = new JButton("PLAY");
        playButton.setFont(new Font("SansSerif", Font.BOLD, 40));
        menuConfigButton = new JButton("Configurations");
        menuConfigButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        continueButton.setVisible(false);

        menuPanel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("YAHTZEE");
        title.setFont(new Font("SansSerif", Font.BOLD, 100));
        

        gbc.gridx = 0; // center in middle
        gbc.gridy = 0; // first row 
        gbc.weighty = 0.1;
        gbc.insets = new Insets(50, 0, 0, 0); // adding filler space/gap bellow title
        menuPanel.add(title, gbc);


        JPanel menuOptions = new JPanel();
        menuOptions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        menuOptions.setPreferredSize(new Dimension(300,300));
        menuOptions.setBackground(Color.LIGHT_GRAY);
        menuOptions.add(playButton);
        menuOptions.add(menuConfigButton);
        menuOptions.add(continueButton);
        menuOptions.setLayout(new GridLayout(3, 1));

        gbc.gridx = 0; // center in middle
        gbc.gridy = 1; // second row below title
        gbc.weighty = 0.9;
        gbc.insets = new Insets(20,0,0,0); // reset prevous filler config from above
        menuPanel.add(menuOptions, gbc); // adding panel to panel


    }
    /**
     * Method works as a getter for the menuPanel field
     */
    public JPanel getMenuPanel() {
        return menuPanel;
    }
     /**
     * Method works as a getter for the playButton private field
     */
    public JButton getPlayButton() {
        return playButton;
    }
     /**
     * Method works as a getter for the configButton 
     */
    public JButton getConfigButton() {
        return menuConfigButton;
    }
     /**
     * Method works as a getter for the continueButton 
     */
    public JButton getContinueButton() {
        return continueButton;
    }
    
     /**
     * Method works as a way to hide the continue button if a save file exists or not
     * @param show takes in a boolean variable
     */
    public void showContinue(boolean show) {
        continueButton.setVisible(show);
    }
    
}
