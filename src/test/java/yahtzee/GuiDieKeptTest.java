package yahtzee;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
/**
 * The class for testing that roll only changes unkept dice
 *
 * 
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class GuiDieKeptTest {


    @Test
    /**
     * This method tests that roll only changes unkept dice
     * 
     */
    void rollDieOnlyChangesUnkeptDice() throws Exception {
        GuiGameFlow flow = new GuiGameFlow();
        Field f = flow.getClass().getDeclaredField("DICE_IN_PLAY");
        f.setAccessible(true);
        f.setInt(flow, 3);

        GameScreen game = new GameScreen();
        game.buildDiceUi(3, e -> {});

        boolean[] keptDice = { false, true, false };
        int[] hand = { 999, 888, 777 }; 

        flow.rollDie(keptDice, hand, game);

        assertNotEquals(999, hand[0]);
        assertEquals(888, hand[1]); // kept die shouldnt change
        assertNotEquals(777, hand[2]);
    }
}
