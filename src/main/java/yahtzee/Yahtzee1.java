package yahtzee;

/*
Abraham Meshesha
CPSC 224
2/3/2026
Dr. Olivares

*/

import javax.swing.*;

/**
 * The public class for the Yahtzee game
 * This class serves as the entry point to start playing yahtzee 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class Yahtzee1 { 
    /**
     * Main method and entry point that launches Yahtzee
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GuiGameFlow gameRun = new GuiGameFlow();
            gameRun.runGame();
        });

    }
    
    
    
    }
    








