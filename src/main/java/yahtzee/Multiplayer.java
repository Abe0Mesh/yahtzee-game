package yahtzee;

public class Multiplayer {
    // read from config or where we store max player num
    private Player[] players;


    // we'll take in max player val from controller just for saftey n we'll use maxVal in controller
    // also useful data so no point of hiding it/priviting it in this class
    Multiplayer(int maxPlayers) {

        players = new Player[maxPlayers];  // creates refrences based on max player
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(); // creates objects based on max players
        }
    }

    Player getPlayer(int playerId) {
        int i = playerId - 1;// -1 because array is 0 indexed but player id's r 1 indexed
        if (i < 0 || i >= players.length) {
            // error feedback if we index outside of expected player id's
            System.out.println("Player id doesnt exist"); 
            return null;
        }
        return players[i];
    
    }


    



    
}
