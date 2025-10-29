# Aeroplane Chess
## Link
- GitHub Repository: [Click to Open]
- SSH:
```
git@github.com:Yukinoshita-yume/AeroplaneChessGame.git
```  
- HTTPS:
```
https://github.com/Yukinoshita-yume/AeroplaneChessGame.git
```

## How to Play
Run the program → Select the number of dice (1 or 2) → Choose the number of players (no limit) → Each player rolls the dice in turn → Move the piece according to the dice value → When landing on another player’s position, send that player back to the starting point → The first player to reach the finish line wins.

## Code Description
### Project Structure
```
└─main
    └─java
        └─com
            └─yuki
                ├─controller
                │   └─GameController        
                ├─pojo
                │   ├─Dice              // Dice class
                │   ├─Game              // Game class
                │   ├─Map               // Map class
                │   └─Player            // Player class
                ├─server
                │   ├─impl
                │   │  ├─DiceShakerImpl
                │   │  └─GameServerImpl
                │   ├─DiceShaker
                │   └─GameServer
                └─GameApplication
```

- Follows a three-layer architecture, but the DAO layer is omitted since there is no database.
- **Controller layer:** Handles user interaction, receives input, and displays game status.
- **Service layer:** Contains core game logic, such as dice rolling and game progression.
- **POJO layer:** Defines entities like players, map, and dice.

### Main Class Descriptions
- **Player**

  | Property         | Description                                                                 |
      |------------------|------------------------------------------------------------------------------|
  | id               | Unique identifier                                                            |
  | home             | Starting position. Defined separately to handle returning to the start point. |
  | locationRunway   | Player’s position on the runway                                               |
  | locationTaxiway  | Player’s position on the taxiway                                              |
  | runwayPosition   | Position of the runway                                                        |
  | getRunway        | Whether the player has reached the runway                                     |
  | isEnd            | Whether the player has reached the finish line                                |

- **Map**

  | Property        | Description                                                                 |
      |-----------------|------------------------------------------------------------------------------|
  | taxiwayLength   | Map parameter                                                                |
  | runwayLength    | Map parameter                                                                |
  | players         | Array of all players                                                         |
  | playerInMap     | The position of each player on the map. Numbers represent player IDs; -1 means no player.<br/>This ensures only one player occupies a position. If a player is at the start and another arrives there, this is handled by using -1 to indicate the player is outside the map. |

- **Dice**

  | Property  | Description |
      |-----------|-------------|
  | minValue  | Parameter   |
  | maxValue  | Parameter   |

- **Game**

  | Property           | Description                    |
      |--------------------|--------------------------------|
  | playerCount        | Number of players              |
  | players            | Array of players               |
  | map                | Map                            |
  | dice               | Dice                           |
  | diceCount          | Number of dice                 |
  | isGameOver         | Whether the game has ended      |
  | winnerOrder        | The order of player victories   |
  | winnerCount        | Number of players who have won  |
  | currentPlayerIndex | Index of the current player     |

## Key Logic Challenges
1. **Representing Player Positions**
    - Player positions are divided into taxiway and runway, stored in two separate attributes.
    - A boolean flag indicates whether a player has entered the runway, simplifying movement calculations.

2. **Collision Handling**
    - A map array records which player occupies each position for collision detection.
    - If a player lands on another’s position, the hit player is sent back to the start.
    - Players are allowed to occupy the start position even if others are there.

3. **Player Movement Logic**
    - Player movement is calculated based on dice rolls, accounting for transitions between taxiway and runway.
    - Various movement scenarios (entering runway, collision, etc.) are handled.
    - Since the taxiway is circular, modulo operations handle position looping.
    - The circular track introduces a problem: if the runway entry point is near the end of the taxiway, dice rolls might wrap around 0 and miss it.<br/>To fix this, custom logic checks if the player can directly enter the runway; if not, it checks whether the player passes 0 and should enter from there.

4. **Game End Detection**
    - A boolean flag marks when the game ends, simplifying state management.
    - The victory order is recorded for displaying results.

## Run Instructions
- Ensure the Java Development Kit (JDK 17 or higher) is installed. JDK 23 is recommended.

[Click to Open]: https://www.oracle.com/java/technologies/downloads/
