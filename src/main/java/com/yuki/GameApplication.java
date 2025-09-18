package main.java.com.yuki;

import main.java.com.yuki.controller.GameController;
import main.java.com.yuki.pojo.Game;

import java.util.Scanner;

public class GameApplication {
    public static void main(String[] args){
        GameController gameController = new GameController();
        gameController.playSimpleGame();
    }
}
