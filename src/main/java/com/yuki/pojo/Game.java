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
    public void showGameInfo(){
        System.out.println("Game Info:");
        System.out.println("Number of players: "+playerCount);
        map.showMapInfo();
        System.out.println("Number of dice rolled each turn: "+diceCount);
    }
    public void startGame() {
        System.out.println("Game Start!");
        currentPlayerIndex = 0;
        while (!isGameOver) {
            Player currentPlayer = players[currentPlayerIndex];
            if(currentPlayer.isEnd()){//玩家已到达终点，跳过该玩家
                currentPlayerIndex = (currentPlayerIndex + 1) % playerCount;
                continue;
            }
            System.out.println("It's Player" + currentPlayer.getId() + "'s turn.");
            int totalSteps = 0;
            for (int i = 0; i < diceCount; i++) {
                int roll = dice.roll();
                totalSteps += roll;
                System.out.println("Player" + currentPlayer.getId() + " rolled a " + roll + ".");
            }
            System.out.println("Player" + currentPlayer.getId() + " will move " + totalSteps + " steps.");
            map.playerMove(currentPlayer.getId(), totalSteps);
            if(currentPlayer.isEnd() && !isGameOver){
                winnerOrder[winnerCount] = currentPlayer.getId();
                winnerCount++;
                if(winnerCount==playerCount-1){
                    isGameOver = true;
                    for(Player player:players){
                        if(!player.isEnd()){
                            winnerOrder[winnerCount] = player.getId();
                            break;
                        }
                    }
                    System.out.println("Game Over! The winner order is:");
                    for(int i=0;i<playerCount;i++){
                        System.out.println((i+1)+". Player"+winnerOrder[i]);
                    }
                }
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % playerCount;
        }
    }

}
