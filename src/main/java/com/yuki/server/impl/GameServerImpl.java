package main.java.com.yuki.server.impl;

import main.java.com.yuki.pojo.Game;
import main.java.com.yuki.pojo.Map;
import main.java.com.yuki.pojo.Player;
import main.java.com.yuki.server.GameServer;
import main.java.com.yuki.pojo.Dice;

public class GameServerImpl implements GameServer {

    @Override
    public Game createGame(int playerCount, int taxiwayLength, int runwayLength, int diceCount) {
        return new Game(playerCount, taxiwayLength, runwayLength, diceCount);
    }

    @Override
    public void showGameInfo(Game game) {
        int playerCount = game.getPlayerCount();
        Map map = game.getMap();
        int diceCount = game.getDiceCount();
        System.out.println("Game Info:");
        System.out.println("Number of players: "+playerCount);
        map.showMapInfo();
        System.out.println("Number of dice rolled each turn: "+diceCount);
    }

    @Override
    public void startGame(Game game) {

        System.out.println("Game Start!");
        int currentPlayerIndex = game.getCurrentPlayerIndex();
        boolean isGameOver = game.isGameOver();
        Player[] players = game.getPlayers();
        int playerCount = game.getPlayerCount();
        Map map = game.getMap();
        int diceCount = game.getDiceCount();
        int[] winnerOrder = game.getWinnerOrder();
        int winnerCount = game.getWinnerCount();
        Dice dice = game.getDice();
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
