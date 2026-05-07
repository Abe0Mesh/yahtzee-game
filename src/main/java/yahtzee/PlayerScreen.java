package yahtzee;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;




/**
 * The class holds the label for the player screen
 * 
 * @author Aidan Amal
 * @version 1.0
 */
public class PlayerScreen {
        private JPanel playerPanel;
        private JButton onePlayer;
        private JButton twoPlayer;
        private JButton threePlayer;
        private JButton fourPlayer;
        private JButton menu;

        public PlayerScreen(){
            playerPanel = new JPanel(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            onePlayer = new JButton("1 player");
            onePlayer.setFont(new Font("SansSerif", Font.BOLD, 40));

            twoPlayer = new JButton("2 players");
            twoPlayer.setFont(new Font("SansSerif", Font.BOLD, 40));

            threePlayer = new JButton("3 players");
            threePlayer.setFont(new Font("SansSerif", Font.BOLD, 40));

            fourPlayer = new JButton("4 players");
            fourPlayer.setFont(new Font("SansSerif", Font.BOLD, 40));

            JLabel title = new JLabel("Choose number of players.");
            title.setFont(new Font("SansSerif", Font.BOLD, 60));

            gbc.gridx = 0; // center in middle
            gbc.gridy = 0; // first row 
            gbc.weighty = 0.1;
            gbc.insets = new Insets(50, 0, 0, 0); // adding filler space/gap bellow title
            playerPanel.add(title, gbc);

            JPanel playerOptions = new JPanel();
            playerOptions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            playerOptions.setPreferredSize(new Dimension(500,500));
            playerOptions.setBackground(Color.LIGHT_GRAY);
            playerOptions.add(onePlayer);
            playerOptions.add(twoPlayer);
            playerOptions.add(threePlayer);
            playerOptions.add(fourPlayer);
            playerOptions.setLayout(new GridLayout(2,2));

            gbc.gridx = 0; // center in middle
            gbc.gridy = 1; // second row below title
            gbc.weighty = 0.9;
            gbc.insets = new Insets(20,0,0,0); // reset prevous filler config from above
            playerPanel.add(playerOptions, gbc); // adding panel to panel
        }

        /**
        * Method works as a getter for the Player Pannel
        */
        public JPanel getPlayerPannel(){
            return playerPanel;
        }

        /**
        * Method works as a getter for the OnePlayer button
        */
        public JButton getOnePlayer(){
            return onePlayer;
        }

        /**
        * Method works as a getter for the twoPlayer button
        */
        public JButton getTwoPlayer(){
            return twoPlayer;
        }

        /**
        * Method works as a getter for the Three Player button
        */
        public JButton getThreePlayer(){
            return threePlayer;
        }

        /**
        * Method works as a getter for the four player button. 
        */
        public JButton getFourPlayer(){
            return fourPlayer;
        }

}
