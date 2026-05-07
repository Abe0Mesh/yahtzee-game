package yahtzee;

public class Player {
    private String name;
    //private Scorecard scorecard;
    private boolean[] usedLines = new boolean[13];
    private int[] scoreLineResults = new int[13];
    private int score;
    private int playerID;
    Player(){
        //default cons to use in multiplayer for now 
    }
    Player(String name, int score, boolean[] usedLines, int playerID){
        this.name = name;
        //this.scorecard = scorecard;
        this.usedLines = usedLines;
        this.score = score;
        this.playerID = playerID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }
    public void setUsedLines(boolean[] usedLines){ // we could use this to reset used scorelines for a new game/replay button 
        this.usedLines = usedLines;                
    }
    public void setUsed(int index, boolean state){
        usedLines[index] = state;
    }
    public boolean getUsed(int index) {
        return usedLines[index];
    }
    public boolean[] getUsedArr(){
        return usedLines;
    }
    public String returnName(){
        return name;
    }
    public int returnScore(){
        return score;
    }
    public int returnPlayerID(){
        return playerID;
    }
    public boolean[] returnUsedLines(){
        return usedLines;
    }
    public void setScoreResults(int index, int score){
        scoreLineResults[index] = score;
    }
    public int[] getScoreLineResults() {
        return scoreLineResults;
    }
}
