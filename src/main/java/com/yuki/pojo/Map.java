package main.java.com.yuki.pojo;

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
    public void playerMove(int id, int steps) {
        Player player = players[id];
        if(player.isEnd()){//玩家已到达终点
            System.out.println("Player"+id+" has reached the end and cannot move.");
            return;
        }
        if(!player.isGetRunway()){//玩家未到达结束点
            int locationTaxiway = player.getLocationTaxiway();
            int runwayPosition = player.getRunwayPosition();
            if(player.getHome()!=locationTaxiway){
                playerInMap[locationTaxiway] = -1;
            }
            if(locationTaxiway<runwayPosition){
                if(locationTaxiway+steps<runwayPosition){//未到达结束点
                    moveInTaxiway(id, steps, player, locationTaxiway, taxiwayLength, playerInMap, players);
                }else{//到达或超过结束点
                    int stepsToRunway = runwayPosition - locationTaxiway;//到达结束点所需步数
                    moveToRunway(id, steps, player, locationTaxiway, stepsToRunway, runwayLength);
                }
            }
            else{
                if(locationTaxiway+steps<taxiwayLength+runwayPosition){//未到达结束点
                    moveInTaxiway(id, steps, player, locationTaxiway, taxiwayLength, playerInMap, players);
                }else{//到达或超过结束点
                    int stepsToRunway = taxiwayLength+runwayPosition - locationTaxiway;//到达结束点所需步数
                    moveToRunway(id, steps, player, locationTaxiway, stepsToRunway, runwayLength);
                }
            }
        }
        else {
            int positionInRunway = player.getLocationRunway();
            int newLocationRunway = positionInRunway(positionInRunway, steps, runwayLength);
            player.setLocationRunway(newLocationRunway);
            System.out.println("Player"+id+" move form tail"+positionInRunway+" to tail"+newLocationRunway+".");
            if(newLocationRunway==runwayLength){
                player.setEnd(true);
                System.out.println("Player"+id+" has reached the end!");
            }
        }
    }

    private static void moveToRunway(int id, int steps, Player player, int locationTaxiway, int stepsToRunway, int runwayLength) {
        int remainingSteps = steps - stepsToRunway;//剩余步数
        player.setGetRunway(true);//玩家到达跑道
        int newLocationRunway = positionInRunway(0, remainingSteps, runwayLength);
        player.setLocationRunway(newLocationRunway);
        System.out.println("Player"+id+" move form position"+locationTaxiway+" to tail"+newLocationRunway+".");
        if(newLocationRunway==runwayLength){
            player.setEnd(true);
            System.out.println("Player"+id+" has reached the end!");
        }
    }

    private static void moveInTaxiway(int id, int steps, Player player, int locationTaxiway, int taxiwayLength, int[] playerInMap, Player[] players) {
        int newLocation = (locationTaxiway + steps) % taxiwayLength;
        System.out.println("Player"+id+" move form position"+locationTaxiway+" to position"+newLocation+".");
        int newLocationPlayer = playerInMap[newLocation];
        if(newLocationPlayer!=-1){//新位置有玩家
            Player otherPlayer = players[newLocationPlayer];
            otherPlayer.setLocationTaxiway(otherPlayer.getHome());//将其他玩家移回家
            System.out.println("Player"+newLocationPlayer+" is sent back to home position"+otherPlayer.getHome()+".");
        }
        playerInMap[newLocation] = id;//更新玩家位置
        player.setLocationTaxiway(newLocation);
    }

    private static int positionInRunway(int originalPosition, int steps, int runwayLength) {
        int temp = (steps%(2*runwayLength)+2*runwayLength)% (2*runwayLength);
        if(originalPosition+temp<=runwayLength){
            return originalPosition+temp;
        }else if(originalPosition+temp<=2*runwayLength){
            return 2*runwayLength-(originalPosition+temp);
        }else{
            return originalPosition+temp-2*runwayLength;
        }
    }
    public int getTaxiwayLength() {
        return taxiwayLength;
    }
    public int getRunwayLength() {
        return runwayLength;
    }
}
