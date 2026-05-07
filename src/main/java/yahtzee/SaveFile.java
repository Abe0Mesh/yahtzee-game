package yahtzee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveFile {

    private static final String PATH = "src/main/resources/savegame.txt";

    public void writeSave(int rollsLeft, int[] hand, int currentPlayer, int maxPlayers, Multiplayer mp) {
    try (PrintWriter pw = new PrintWriter(new File(PATH))) {
        pw.println(rollsLeft);
        pw.println(currentPlayer); // whose turn it is
        pw.println(maxPlayers);

        // dice values
        for (int d : hand) pw.print(d + " ");
        pw.println();

        // save each player's data
        for (int p = 1; p <= maxPlayers; p++) {
            Player player = mp.getPlayer(p);

            // used lines
            boolean[] used = player.getUsedArr();
            for (boolean b : used) pw.print((b ? 1 : 0) + " ");
            pw.println();

            // score line results
            for (int s : player.getScoreLineResults()) pw.print(s + " ");
            pw.println();
        }

    } catch (FileNotFoundException e) {
        System.out.println("Could not save.");
    }
}

    public int[][] readSave(int maxPlayers) {
        File file = new File(PATH);
        // System.out.println("Looking for save at: " + file.getAbsolutePath());
        // System.out.println("File exists: " + file.exists());
        if (!file.exists()) return null;

        try (Scanner sc = new Scanner(file)) {
            try {
                int rollsLeft = sc.nextInt();
                // System.out.println("rollsLeft: " + rollsLeft);
                int currentPlayer = sc.nextInt();
                // System.out.println("curPlayer: " + currentPlayer);
                int savedMaxPlayers = sc.nextInt();
                // System.out.println("savedMaxPlayers: " + savedMaxPlayers);

                int diceCount = new ConfigFile().readingFile()[1];
                // System.out.println("diceCount from config: " + diceCount);
                int[] dice = new int [diceCount];
                for (int i = 0; i < diceCount; i++) {
                    dice[i] = sc.nextInt();
                    // System.out.println("dice[" + i + "]: " + dice[i]);
                }

                // each player gets 13 usedLines and scores, making 26 values
                int[][] playerData = new int[savedMaxPlayers * 2][13];
                for (int p = 0; p < savedMaxPlayers; p++) {
                    // System.out.println("Reading player " + (p+1) + " usedLines");
                    for (int i = 0; i < 13; i++) {
                        playerData[p * 2][i] = sc.nextInt();
                    }
                    // System.out.println("Reading player " + (p+1) + " scores");
                    for (int i = 0; i < 13; i++) {
                        playerData[p * 2 + 1][i] = sc.nextInt();
                    }
                }    

                // build return array
                int[][] result = new int[2 + savedMaxPlayers * 2][];
                result[0] = new int[]{rollsLeft, currentPlayer, savedMaxPlayers};
                result[1] = dice;
                for (int i = 0; i < savedMaxPlayers * 2; i++) {
                    result[2 + i] = playerData[i];
                }
                // System.out.println("About to return result");
                // System.out.println("result length: " + result.length);
                return result;
            } catch (Exception ex) {
                // System.out.println("Exception inside readSave: ");
                ex.printStackTrace();
                return null;
            }
            

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException caught");
            return null;
        }
    }

    public boolean saveExists() {
        return new File(PATH).exists();
    }

    public void deleteSave() {
        new File(PATH).delete();
    }

}
