package main.java.com.yuki.server;

import main.java.com.yuki.pojo.Game;

public interface GameServer {
    Game createGame(int playerCount, int taxiwayLength, int runwayLength, int diceCount);
    void showGameInfo(Game game);
    void startGame(Game game);
}
