package main.java.com.yuki.pojo;

public class Game {
    private final int playerCount;//玩家数量
    private final Player[] players;
    private final Map map;
    private final Dice dice;
    private int diceCount = 1;
    private boolean isGameOver = false;
    private final int[] winnerOrder;//记录玩家获胜顺序
    private int winnerCount = 0;//记录已获胜玩家数量
    private int currentPlayerIndex = 0;//当前玩家索引
    public Game(int playerCount, int taxiwayLength, int runwayLength, int diceCount) {
        this.playerCount = playerCount;
        this.players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            int position = (i * taxiwayLength) / playerCount;//均匀分布玩家位置
            int runwayPosition = (position+taxiwayLength-1)%taxiwayLength;//玩家进入结束点的位置
            players[i] = new Player(i, position,runwayPosition);
        }
        this.map = new Map(taxiwayLength, runwayLength, players);
        this.dice = new Dice(6, 1);
        this.diceCount = diceCount;
        this.winnerOrder = new int[playerCount];
    }
    // Getter 和 Setter 方法
    public int getPlayerCount() {
        return playerCount;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Map getMap() {
        return map;
    }

    public Dice getDice() {
        return dice;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public int[] getWinnerOrder() {
        return winnerOrder;
    }

    public int getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(int winnerCount) {
        this.winnerCount = winnerCount;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

}
