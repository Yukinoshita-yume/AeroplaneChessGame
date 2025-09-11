package main.java.com.yuki.pojo;

import java.util.HashMap;

public class Map {
    private final int taxiwayLength;//外围跑道长度
    private final int runwayLength;//结束跑道长度
    private final int playerCount;//玩家数量
    private Player[] players;
    private int[] playerInMap;//记录滑行道上每个位置的玩家
    public Map(int taxiwayLength, int runwayLength, int playerCount) {
        this.taxiwayLength = taxiwayLength;
        this.runwayLength = runwayLength;
        this.playerCount = playerCount;
        this.players = new Player[playerCount];
        this.playerInMap = new int[taxiwayLength];
        for(int i=0;i<taxiwayLength;i++){
            playerInMap[i] = -1;//初始化为-1，表示该位置没有玩家
        }
        for (int i = 0; i < playerCount; i++) {
            int position = (i * taxiwayLength) / playerCount;//均匀分布玩家位置
            int runwayPosition = (position+taxiwayLength-1)%taxiwayLength;//玩家进入结束点的位置
            players[i] = new Player(i, position,runwayPosition);
            playerInMap[position] = i;//记录玩家位置
        }
    }
    public void playerMove(int id){
    }
    public int getTaxiwayLength() {
        return taxiwayLength;
    }
    public int getRunwayLength() {
        return runwayLength;
    }
}
