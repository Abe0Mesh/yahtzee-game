package yahtzee;


import java.awt.*;
import java.util.*;
import javax.swing.*;
//make a third hash map so hashmaps for buttons labels and textfield, 
// then in my controller I would refrence the save button 
// and in that save butter it will call .getText()

/**
 * This class builds my config screen panel
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ConfigScreen {
    private JPanel configPanel;
    private HashMap<String, JLabel> cLabels = new HashMap<>();
    private HashMap<String, JButton> cButtons = new HashMap<>();
    private HashMap<String, JTextField> cInputs = new HashMap<>();
    


    /**
     * this method builds a constructor for my config screen panel
     */
    public ConfigScreen() {
        configPanel = new JPanel(new GridBagLayout());
        configPanel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel currentConfigText = new JLabel("Configurations");
        currentConfigText.setFont(new Font("SansSerif", Font.BOLD, 45));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 200, 0); // adding filler space/gap bellow title
        configPanel.add(currentConfigText, gbc);



        JLabel sideOfDiceText = new JLabel("Dice Sides: 5"); // maybe import config file reader
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0); // adding filler space/gap bellow title
        configPanel.add(sideOfDiceText, gbc);
        cLabels.put("sideOfDieText", sideOfDiceText);
        JTextField sideOfDiceInput = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        configPanel.add(sideOfDiceInput, gbc);
        cInputs.put("sideOfDiceInput", sideOfDiceInput);


        JLabel diceCountText = new JLabel("Dice In Hand: 4");
        gbc.gridx = 0;
        gbc.gridy = 2;
        configPanel.add(diceCountText, gbc);
        cLabels.put("diceCountText", diceCountText);
        JTextField dieCountInput = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        configPanel.add(dieCountInput, gbc);
        cInputs.put("dieCountInput", dieCountInput);


        JLabel rollsPerTurnText = new JLabel("Rolls per turn: 3");
        gbc.gridx = 0;
        gbc.gridy = 3;
        configPanel.add(rollsPerTurnText, gbc);
        cLabels.put("rollsPerTurnText", rollsPerTurnText);
        JTextField rollsPerTurnInput = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        configPanel.add(rollsPerTurnInput, gbc);
        cInputs.put("rollsPerTurnInput", rollsPerTurnInput);

        JButton saveB = new JButton ("Save");
        gbc.gridx = 0;
        gbc.gridy = 4;
        configPanel.add(saveB, gbc);
        cButtons.put("saveB", saveB);
        gbc.insets = new Insets(50, 0, 50, 0); // adding gap space
        JButton menuB = new JButton("Menu");
        gbc.gridx = 1;
        gbc.gridy = 4;
        configPanel.add(menuB, gbc);
        cButtons.put("menuB",menuB);


        // error label if user puts in invalid input
        JLabel invalid = new JLabel("INVALID INPUT TRY AGAIN");
        invalid.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.insets = new Insets(100, 0, 50, 0); // adding gap space
        gbc.gridx = 0;
        gbc.gridy = 5;
        invalid.setForeground(Color.RED);
        configPanel.add(invalid, gbc);
        invalid.setVisible(false);
        cLabels.put("invalid", invalid);

        



    }
    /**
     * method works as a getter for config panel field
     */
    public JPanel getConfigPanel() {
        return configPanel;
    }
    /**
     * this method works as a getter for config labels hash map
     * @param key takes in key for hash map
     */
    public JLabel getCLabels(String key){
        return cLabels.get(key);
    }
    /**
     * this method works as a getter for config buttons hash map
     * @param key takes in key for hash map
     */
    public JButton getCButtons(String key){
        return cButtons.get(key);
    }
    /**
     * this method works as a getter for textField inputs
     * @param key takes in key for hash map
     */
    public String getInput(String key){
        return cInputs.get(key).getText().trim(); // .trim() gets rid of whtiespace, so we can turn string input into int to write to config.txt
        // grabs input from text fields and returns it based on which key/textfield is passed as arg
    }
    /**
     * this works as a setter for config labels
     * @param key takes in key for hash map
     * @param newVal takes in new text value for labels
     */
    public void setConfigLabel(String key, String newVal){
        JLabel tempLabel = cLabels.get(key);
        if (tempLabel != null) {
        tempLabel.setText(newVal);
        }
        else {
            System.out.println("invalid hash key used");
        }
    }
    /**
     * This method shows text stating that invalid input was found
     */
    public void invalidInput(){
        getCLabels("invalid").setVisible(true);
    }
    /**
     * this method turns off invalid input text
     */
    public void validInput(){
        getCLabels("invalid").setVisible(false);

    }
    
}
