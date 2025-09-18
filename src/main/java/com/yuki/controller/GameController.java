package main.java.com.yuki.controller;

import main.java.com.yuki.pojo.Game;
import main.java.com.yuki.server.GameServer;
import main.java.com.yuki.server.impl.GameServerImpl;

import java.util.Scanner;

public class GameController {
    private final GameServer gameServer = new GameServerImpl();
    public GameController() {
    }

    public void playSimpleGame(){
        int diceCount = 0;
        int playerCount = 0;
        int taxiwayLength = 0;
        int runwayLength = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Game!");
        while(true){
            try{
                System.out.print("Please enter the number of dice to roll each turn : ");
                diceCount = Integer.parseInt(scanner.nextLine());
                if(diceCount<=0){
                    System.out.println("The number must be greater than 0. Please re-enter.");
                    continue;
                }
                System.out.print("Please enter the number of players (2-4) : ");
                playerCount = Integer.parseInt(scanner.nextLine());
                if(playerCount<2||playerCount>4){
                    System.out.println("The number must be between 2 and 4. Please re-enter .");
                    continue;
                }
                System.out.print("Please enter the length of the taxiway (minimum 20) : ");
                taxiwayLength = Integer.parseInt(scanner.nextLine());
                if(taxiwayLength<20){
                    System.out.println("The length must be at least 20. Please re-enter.");
                    continue;
                }
                System.out.print("Please enter the length of the runway (minimum 6) : ");
                runwayLength = Integer.parseInt(scanner.nextLine());
                if(runwayLength<6){
                    System.out.println("The length must be at least 6. Please re-enter.");
                    continue;
                }
                Game game = gameServer.createGame(playerCount, taxiwayLength, runwayLength, diceCount);
                gameServer.showGameInfo(game);
                System.out.println("Press Enter to start the game...");
                scanner.nextLine();
                gameServer.startGame(game);
                System.out.println("Next game? (y/n)");
                String next = scanner.nextLine();
                if(!next.equalsIgnoreCase("y")){
                    System.out.println("Thank you for playing! Goodbye!");
                    break;
                }
                System.out.println("Starting a new game...");
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
