package yahtzee;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.File;


/**
 * This class takes in my 3 sub panels and builds one main panel
 * @author Abraham Meshesha
 * @version 1.0
 */
public class MainFrame {
    private JFrame frame;
    private CardLayout cl;
    private JPanel master;
    MenuScreen menu;
    GameScreen game;
    PlayerScreen player;
    ConfigScreen config;
    EndScreen end;

    /**
     * this method builds a main screen using my 3 sub panels screens
     */
    public void Screen() {
        
        frame = new JFrame("Yahtzee");
        cl = new CardLayout();
        master = new JPanel(cl);
        frame.setLayout(new BorderLayout());



        
        // making objects of my two screens (which are nested panels)
        menu = new MenuScreen();
        game = new GameScreen();
        player = new PlayerScreen();
        config = new ConfigScreen();
        end = new EndScreen();
        // add the nested panels to the master panel
        master.add(menu.getMenuPanel(), "MENU");
        master.add(player.getPlayerPannel(), "PLAYER");
        master.add(game.getGamePanel(), "GAME");
        master.add(config.getConfigPanel(), "CONFIG");
        master.add(end.getEndPanel(), "END");

        master.setBackground(Color.BLUE);
        frame.setBackground(Color.RED);
        switchMenuScreen();
        frame.add(master, BorderLayout.CENTER);

        // setting up frame specs
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(1080, 720));
        


    }
    /**
     * this method works as a getter for the game obj
     */
    public GameScreen getGameObj() {
        return game;
    }
    /**
     * this method works as a getter for the player obj
     */
    public PlayerScreen getPlayerObj(){
        return player;
    }
    /**
     * this method is used as a getter for menu obj
     */
    public MenuScreen getMenuObj() {
        return menu;
    }
    /**
     * this method is used as a getter for config obj
     */
    public ConfigScreen getConfigObj(){
        return config;
    }
    public EndScreen getEndObj(){
        return end;
    }
    /**
     * This method switches to game screen panel
     */
    public void switchGameScreen() {
        cl.show(master, "GAME");
        frame.repaint(); // screen refresh
    }
    public void switchPlayscreen(){
        cl.show(master, "PLAYER");
        frame.repaint();
    }
    /**
     * This method switches to menu screen panel
     */
    public void switchMenuScreen() {
        cl.show(master, "MENU");
        frame.repaint(); // screen refresh
    }
    /**
     * This method switches to config screen panel
     */
    public void switchConfigScreen() {
        cl.show(master, "CONFIG");
        frame.repaint(); // screen refresh
    }
    /**
     * This method switches to end screen panel
     */
    public void switchEndScreen(){
        // adding winning audio when switching to end screen
        try {         
            File soundFile = new File("src/main/resources/YahtzeeMedia/audio/Victory_Sound_Effect.wav")   ;    

            AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            }
            catch(Exception e) {
            System.out.println("Erorr outputing Victory Sound Effect");
            e.printStackTrace();
        }
        cl.show(master, "END");

        frame.repaint();
    }
    /**
     * this method works as a getter for the frame field refrence
     */
    public JFrame getFrame(){
        return frame;
    }

}
