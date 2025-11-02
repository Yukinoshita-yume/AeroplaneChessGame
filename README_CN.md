# 飞行棋
## Link
- GitHub仓库: [点击跳转]
- ssh:
```
git@github.com:Yukinoshita-yume/AeroplaneChessGame.git
```
- https:
```
https://github.com/Yukinoshita-yume/AeroplaneChessGame.git
```
## 玩法说明
运行程序-->选择骰子数量,1或2 --> 选择玩家人数(无限制)->每个玩家依次掷骰子->根据骰子点数移动棋子->遇到其他玩家，将其返回起点-->第一个到达终点的玩家获胜。
## 代码说明
### 项目结构
```
└─main
    └─java
        └─com
            └─yuki
                ├─controller
                │   └─GameController        
                ├─pojo
                │   ├─Dice              // 骰子类
                │   ├─Game              // 游戏类
                │   ├─Map               // 地图类
                │   └─Player            // 玩家类
                ├─server
                │   ├─impl
                │   │  ├─DiceShakerImpl
                │   │  └─GameServerImpl
                │   ├─DiceShaker
                │   └─GameServer
                └─GameApplication
```
- 采用三层架构。但由于没有数据库，DAO层省略。
- controller层：负责与用户交互，获取用户输入并显示游戏状态。
- service层：包含游戏逻辑，如骰子掷点、游戏进度等。
- pojo层：定义游戏中的实体类，如玩家、地图和骰子。
### 主要类说明
- Player

    | 属性             | 说明                              |
    |----------------|---------------------------------|
    | id--           | 唯一识别id                          |
    | home           | 起始位置。之所以专门设置home属性，是因为回到起点时需要这个参数 |
    | locationRunway | 玩家在跑道上的位置                       |
    | locationTaxiway| 玩家在滑行道上的位置                      |
    | runwayPosition | 跑道的位置                           |
    | getRunway      | 玩家是否到达跑道                        |
    | isEnd          | 玩家是否到达终点                        |

- Map

    | 属性            |  说明 |
    |---------------|--------------------------------------|
    | taxiwayLength | 地图参数                                                                                                          |
    | runwayLength  | 地图参数                                                                                                          |
    | players       | 保存玩家的数组                                                                                                       |
    | playerInMap   | 玩家在地图上的位置。数字代表玩家的编号。-1代表没有玩家<br/>这就代表同一个位置只能有一个玩家。那如果玩家在起点时，另一个玩家也走上来了呢？<br/>为了规避这个问题，玩家在起点时，用-1参数表示。代表玩家在地图外 |

- Dice

    | 属性   | 说明     |
    |------ |----------|
    | minValue | 参数 |
    | maxValue | 参数 |

- Game  

    | 属性            | 说明                |
    |---------------|-------------------|
    | playerCount   | 玩家数量             |
    | players       | 玩家数组             |
    | map           | 地图                |
    | dice          | 骰子                |
    | diceCount     | 骰子数量             |
    | isGameOver    | 游戏是否结束          |
    | winnerOrder   | 记录玩家获胜顺序        |
  | winnerCount   | 记录已获胜玩家数量       |
  | currentPlayerIndex | 当前玩家索引        |

## 设计模式

本项目使用了多种设计模式，以确保代码的可维护性、灵活性和遵循SOLID原则：

### 1. 策略模式（Strategy Pattern）
- **接口与实现**：项目采用基于接口的设计，使用接口和具体实现类
  - `GameServer` 接口和 `GameServerImpl` 实现类
  - `DiceShaker` 接口和 `DiceShakerImpl` 实现类
- **优势**：允许在不修改客户端代码的情况下轻松替换实现。例如，可以添加不同的骰子掷点策略或游戏服务器实现，而无需更改控制器层。

### 2. 依赖倒置原则（Dependency Inversion Principle，DIP）
- **实现方式**：`GameController` 依赖于 `GameServer` 接口，而不是具体的 `GameServerImpl` 类
- **优势**：减少层之间的耦合，使代码更加灵活和可测试。控制器不需要了解具体的实现细节，便于维护和扩展。

### 3. 三层架构模式（Three-Layer Architecture）
- **架构设计**：项目采用三层架构模式：
  - **Controller层**（`GameController`）：处理用户输入/输出，协调游戏流程
  - **Service层**（`GameServer`）：包含游戏操作的核心业务逻辑
  - **POJO层**（`Game`、`Player`、`Map`、`Dice`）：包含数据模型和领域实体
- **优势**：职责分离，使代码更加组织化、可维护且易于理解。每一层都有明确的职责。

### 4. 封装模式（Encapsulation）
- **实现方式**：所有POJO类使用私有字段和公共的getter/setter方法
- **优势**：保护内部状态，提供对对象属性的受控访问，确保数据完整性。

## 代码逻辑难点
1. 玩家位置的表示
   - 玩家位置分为滑行道和跑道两部分，使用两个属性分别表示玩家在滑行道和跑道上的位置。
   - 通过boolean属性标识玩家是否进入跑道，简化位置计算逻辑。
2. 玩家碰撞处理
   - 使用地图数组记录每个位置上的玩家编号，便于检测玩家碰撞。
   - 当玩家移动到有其他玩家的位置时，将被碰撞的玩家位置重置为起点。
   - 如果玩家在起点位置，允许其他玩家进入起点位置。
3. 玩家移动逻辑
   - 根据骰子点数计算玩家的新位置，考虑滑行道和跑道的转换。
   - 处理玩家移动过程中可能遇到的各种情况，如进入跑道、碰撞等。
   - 由于滑行道是环形的，使用取模运算处理滑行道位置的循环。
   - 环形滑行道还带来一个问题。如果跑道的位置在滑行道的尽头，是一个较大的数。而投掷骰子可能会让玩家绕过0点，从而无法进入跑道。<br/>为了解决这个问题，我为玩家移动可能遇到的所有情况都专门写了代码<br/>在这种情况下，我会先判断玩家是否能够直接进入跑道，如果不能，再判断玩家是否绕过了0点，从而进入跑道。
4. 游戏结束判断
   - 通过boolean属性标识游戏是否结束，简化游戏状态管理。
   - 记录玩家获胜顺序，方便显示最终结果。
## 运行说明
- 确保已安装Java开发环境(JDK 17或更高), 建议jdk23

[点击跳转]: https://www.oracle.com/java/technologies/downloads/