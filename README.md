
# 2025 JAVA 期末專案範例
- 成員：
  連羽健B11230227
  王星凱B11007104
  
- 分工說明：
  王星凱:
  程式架構、功能構想、程式介紹
  撰寫內容(詳情見下方功能表):1~12
  
  連羽健:
  程式修改、簡報介紹、影片錄影
  撰寫內容(詳情見下方功能表):3~12
  
- [報告影片連結]()

## 遊戲說明

### 1. 遊戲流程
- 擊中敵人得分
- 打倒BOSS及獲勝

### 2. 操作方式
- 玩家移動：上下左右
- 射擊：space
- 使用技能：A
- 與場景互動：點選畫面按鈕

## 指令及執行檔說明

### 下載完整repo後開啟Terminal執行以下指令
```
cd .\lwjgl3\build\libs\
java -jar starwar-1.0.0.jar
```

## 檔案重點結構
```
JavaFinalProject\lwjgl3/
 ┣ 📂build
 ┃ ┣ 📂libs
 ┃ ┃ ┗ 📜starwar-1.0.0.jar
 ┣ 📂src
 ┃ ┗ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂wxk
 ┃ ┃ ┃ ┃ ┃ ┗ 📂starwar
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂lwjgl3
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜autoMonster.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DesktopLauncher.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜movingObj.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SkyWizard.java
 ┃ ┣ 📂docs
 ┃ ┃ ┗ 📂javadoc
 ┃ ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂wxk
 ┃ ┃ ┃ ┃ ┃ ┗ 📂starwar
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂lwjgl3
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜autoMonster.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DesktopLauncher.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜movingObj.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜package-summary.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜package-tree.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SkyWizard.html

JavaFinalProject\assets/
 ┣ 📜assets.txt
 ┣ 📜bgm.mp3
 ┣ 📜bigbu.png
 ┣ 📜buttonCheat.png
 ┣ 📜buttonIns.png
 ┣ 📜buttonLevel.png
 ┣ 📜buttonplay.png
 ┣ 📜desktop.ini
 ┣ 📜dragon1.png
 ┣ 📜dragon12.png
 ┣ 📜explode.png
 ┣ 📜fire.mp3
 ┣ 📜fire.png
 ┣ 📜firstscreen.png
 ┣ 📜ghost.png
 ┣ 📜insSpace.png
 ┣ 📜insSpace1.png
 ┣ 📜insUDLR.png
 ┣ 📜insUDLR1.png
 ┣ 📜libgdx.png
 ┣ 📜monfire.png
 ┣ 📜monster2.png
 ┣ 📜redboss.png
 ┣ 📜supper.png
 ┗ 📜wizard.png
```


## 一、SkyWizard 基礎功能列表

---

### ✅ 1. 遊戲初始化與畫面佈局
- 建立遊戲視窗（DesktopLauncher設定大小、標題、背景顏色）。
- 載入素材（create:圖片、音效、字型等資源）。

---

### ✅ 2. 玩家控制（左右移動、發射子彈）
- 鍵盤輸入控制（上下左右鍵）。
- 空白鍵發射子彈。
- A發射大招

---

### ✅ 3. 敵人生成與排列
- 敵人以陣列和得分形式生成，並由monMode決定移動模式。
- 隨著分數越高出現不同的怪物。

---

### ✅ 4. 敵人移動邏輯
- 敵人群體橫向移動。
- 碰到邊界時群體往返。
- 中心點以旋轉向外

---

### ✅ 5. 敵人發射子彈
- 敵人可依時間間隔發射子彈。
- 子彈往下移動，攻擊玩家。

---

### ✅ 6. 碰撞偵測（玩家子彈 vs 敵人）
- 子彈擊中敵人：敵人血量減一、子彈消失。
- 子彈消失後產生爆炸與音效。

---

### ✅ 7. 碰撞偵測（敵人子彈 vs 玩家 / 敵人接觸玩家）
- 玩家被子彈擊中扣血，死亡時遊戲結束。
- 敵人和敵人的子彈不與敵人碰撞。
- 玩家碰到敵人後扣血。

---

### ✅ 8. 得分系統
- 擊中敵人可獲得分數。
- 分數顯示於畫面。

---

### ✅ 9. 生命值與遊戲結束條件
- 玩家擁有初始生命值（20 條命）。
- 禽賊先擒王。
- 困難(無限)模式玩家死後遊戲結束

---

### ✅ 10. 關卡系統
- 每波Boss級敵人結束後進入下一關。
- 敵人速度提升、排列變化增加難度。

---

### ✅ 11. 音效與背景音樂
- 包含射擊聲、關卡背景音樂。

---

### ✅ 12. 主選單與重新開始功能
- 顯示開始畫面、操作說明、困難模式。
- Game Over 後回到主選單。

---

## 二、UML 類別圖 (Class Diagram)

```mermaid
classDiagram
    class Constants {
        <<static>>
        +int FRAMEWIDTH
        +int FRAMEHEIGHT
        +int PLAYERWIDTH
        +int PLAYERHEIGHT
        +int PLAYERMAXLEVEL
        +int PLAYERESTUSNUM
        +int PLAYERINITIALEXP
        +int TRIANGLEWIDTH
        +int TRIANGLEHEIGHT
        +int TRIANGLEATTACKSTARTTIME
        +int TRIANGLEATTACKENDTIME
        +int TRIANGLEATTACKCOOLDOWN
        +int SMALLTRIANGLEDETECTZONE
        +int TRIANGLEDETECTZONE
        +int BIGTRIANGLEDETECTZONE
        +int playerHPLevel
        +int playerHPLevelTemp
        +double playerActualHP
        +int playerSTRLevel
        +int playerSTRLevelTemp
        +double playerActualSTR
        +int playerDEXLevel
        +int playerDEXLevelTemp
        +double playerActualDEX
        +double playerActualEnergy
        +double playerActualSpeed
        +int levelUpCost
        +float currentVolume
        +int nRuns

        +double getActualHP()
        +double getActualSTR()
        +double getActualDEX()
        +double getActualEnergy()
        +double levelUpCost()
        +double nextLevelUpCost()
    }

    class Player {
        -double x
        -double y
        -final int width
        -final int height
        -final Color color
        -int speed
        -int currentHealth
        -double currentEnergy
        -int exp
        -int estus
        -boolean invincible
        -boolean attacking
        -boolean healing
        -int attackCoolDown
        -int healingTimer
        -int healingCoolDown
        -boolean dodging
        -int dodgeTimer
        -int dodgeCoolDown
        +double dodgedx
        +double dodgedy
        +double dodgeSpeed
        -boolean knockBacking
        +double knockBackdx
        +double knockBackdy
        +int knockBackSpeed
        +int knockBackTimer
        +int knockBackCoolDown

        +Player(int, int, int, int, Color, int, int)
        +void move(boolean, boolean, boolean, boolean)
        +void dodge(boolean, boolean, boolean, boolean)
        +void knockBack(double, double, int, int)
        +void attack()
        +void getHurt(int)
        +boolean isAttacking()
        +boolean isKnockBacking()
        +boolean isDodging()
        +boolean isHealing()
        +boolean isInvincible()
        +void draw(Graphics)
        +int getHealth()
        +void increaseHealth()
        +void restoreHealth()
        +void restoreEnergy()
        +int getEstus()
        +void restoreEstus()
        +void increaseExp(int)
        +void decreaseExp(int)
        +int getExp()
        +int getEnergy()
        +double getX()
        +double getY()
        +double getCenterX()
        +double getCenterY()
        +int getWidth()
        +Rectangle getBounds()
    }
    
    Player --> Constants
    
    class PlayerUI {
        +static void draw(Graphics g, int maxHealth, int currentHealth, int maxEnergy, int currentEnergy, int exp, int estus)
    }
    
    PlayerUI --> Player

    class Enemy {
        - double x
        - double y
        - int width
        - int height
        - Color oriColor
        - Color color
        - int maxHealth
        - int currentHealth
        - int speed
        - int state
        - boolean attacking
        - boolean knockBacking
        - boolean damagePlayer
        - boolean getHurting
        - int getHurtCounter
        - int getHurtTimer
        - int attackDamage
        - double knockBackdx
        - double knockBackdy
        - int knockBackSpeed
        - int knockBackTimer
        - int detectZone
        + Enemy()
        + Enemy(int, int, int, int, Color, int, int, int)
        + void move(double, double)
        + void stateIdle(double, double)
        + void stateMove(double, double)
        + void stateKnockBack()
        + void getHurt(int)
        + void knockBack(double, double, int, int)
        + void draw(Graphics)
        + double getCenterX()
        + double getCenterY()
        + double getMaxHealth()
        + double getHealth()
        + int getDamage()
        + boolean isAttacking()
        + Rectangle getBounds()
    }
    
    Enemy --> Constants

    class Triangle {
        + double centerX
        + double centerY
        + double dx
        + double dy
        + int[] xPoints
        + int[] yPoints
        + double attackdx
        + double attackdy
        + int attackSpeed
        + int attackTimer
        + int attackStartTime
        + int attackEndTime
        + int attackCoolDown
        + Triangle(int, int, int, int, Color, int, int, int)
        + void move(double, double)
        + void stateIdle(double, double)
        + void stateMove(double, double)
        + void stateKnockBack()
        + void stateAttack(double, double)
        + void rotateTri()
        + void draw(Graphics)
        + double getCenterX()
        + double getCenterY()
        + double getHealth()
        + boolean isAttacking()
        + Rectangle getBounds()
    }

    Triangle --|> Enemy
    Triangle --> Constants
    
    class BigTriangle {
        - int phase
        - int moveType
        - int moveTypeTimer
        - int attackType
        - int attackTypeCounter
        - double rotationRadius
        - int rotateDirection
        - boolean isSummoned
        - int phaseChangeTimer
        - List<Enemy> enemiesToAdd
        + BigTriangle(int x, int y, int w, int h, Color c, int attackDamage, int health, int detectZone)
        + void move(double playerX, double playerY)
        + void draw(Graphics g)
        + void stateIdle(double playerX, double playerY)
        + void stateMove(double playerX, double playerY)
        + void stateAttack(double playerX, double playerY)
        + void statePhaseChange()
        + void getHurt(int damage)
        + int getDamage()
        + void stateKnockBack()
        + void knockBack(double x, double y, int s, int t)
        + void sprintAttack(double playerX, double playerY)
        + void heavyAttack(double playerX, double playerY)
        + void sprintsprintAttack(double playerX, double playerY)
        + void summonTriangleMinions(int num)
        + int getPhase()
    }
    
    BigTriangle --|> Triangle
    BigTriangle --> Constants

    class Bullet {
        - double x
        - double y
        - int width
        - int height
        - Color color
        - int speed
        - int damage
        - double dx
        - double dy
        + Bullet(int x, int y, int w, int h, Color c, double dx, double dy)
        + void move()
        + int getDamage()
        + boolean outOfScreen()
        + int getWidth()
        + int getHeight()
        + void draw(Graphics g)
        + Rectangle getBounds()
    }
    
    Bullet --> Player
    
    class Coin {
        - double x
        - double y
        - int width
        - int height
        - int exp
        - Color color
        - int[] xPoints
        - int[] yPoints
        - double dx
        - double dy
        + Coin(double x, double y, int exp)
        + void move()
        + void draw(Graphics g)
        + int getExp()
        + Rectangle getBounds()
    }
    
    Coin --> Player

    class CampFire {
        - int x
        - int y
        - int width
        - int interactWidth
        - int height
        + CampFire(int x, int y)
        + void draw(Graphics g)
        + void drawText(Graphics g)
        + int getCenterX()
        + int getCenterY()
        + int getWidth()
        + Rectangle getBounds()
    }
    
    CampFire --> Player

    class Explode {
        -double x
        -double y
        -double centerX
        -double centerY
        -double width
        -double height
        -double w
        -double h
        -Color color
        -int damage = 15
        -int maxHealth = 0
        -int currentHealth = 0
        +Explode(int x, int y, int w, int h, Color c, int health)
        +void getHurt(int damage)
        +void draw(Graphics g)
        +int getDamage()
        +double getCenterX()
        +double getCenterY()
        +double getHealth()
        +Rectangle getBounds()
    }
    
    Explode --> Bullet

    class SpaceInvaderPanel {
        -Timer timer1
        -Timer timer2
        -Timer timer3
        -Timer timer4
        -ArrayList~Enemy~ enemies
        -ArrayList~Bullet~ bullets
        -List~Enemy~ enemiesToAdd
        -ArrayList~Explode~ explodes
        -ArrayList~Coin~ coins
        -Player player
        -CampFire campFire
        -boolean paused
        -boolean playerInput
        -boolean enemyMove
        -boolean isGameOver
        -boolean showBonfireText
        -boolean inBonfire
        -int spawnEnemiesMaxDelay
        -int spawnEnemiesMinDelay
        -int spawnEnemiesDelay
        -int spawnEnemiesStep
        -int bigTriangleDelay
        -boolean stopSpawmEnemies
        -boolean enemyFall
        -boolean changeMusic
        -boolean wPressed
        -boolean aPressed
        -boolean sPressed
        -boolean dPressed
        -boolean ePressed
        -boolean escPressed
        -int rPressed
        -int spacePressed
        -int mouseLeftClicked
        -int mouseRightClicked
        -int leftClickX
        -int leftClickY
        -JLayeredPane layeredPane
        -JPanel settingsPanel
        -GameOverPanel gameOverPanel
        -LevelUpPanel levelUpPanel
        -MusicPlayer musicPlayer
        +SpaceInvaderPanel(JLayeredPane lp)
        +void spawnEnemies()
        +void paintComponent(Graphics g)
        +void actionPerformed(ActionEvent e)
        +void checkCollisions()
        +void deleteOutOfScreenBullets()
        +void pauseGame()
        +void resumeGame()
        +void restartGame()
        +void disablePlayerInput()
        +void keyPressed(KeyEvent e)
        +void keyReleased(KeyEvent e)
        +void keyTyped(KeyEvent e)
        +void mousePressed(MouseEvent e)
        +void mouseClicked(MouseEvent e)
        +void mouseEntered(MouseEvent e)
        +void mouseExited(MouseEvent e)
        +void mouseReleased(MouseEvent e)
        +void setSettingsPanel(SettingsPanel settingPanel)
        +void setGameOverPanel(GameOverPanel gameOverPanel)
        +void setLevelUpPanel(LevelUpPanel levelUpPanel)
        +void setMusicPlayer(MusicPlayer musicPlayer)
    }

    class StartPanel {
        -SettingsPanel settingsPanel
        -MusicPlayer musicPlayer
        +StartPanel(SpaceInvaderPanel gamePanel, MusicPlayer musicPlayer)
        +void styleTextOnlyButton(JButton button)
        +void setSettingsPanel(SettingsPanel settingPanel)
        +void setMusicPlayer(MusicPlayer musicPlayer)
    }

class SettingsPanel {
        -StartPanel startPanel
        -JLayeredPane layeredPane
        -boolean isBlackBackground
        -boolean showResumeButton
        -MusicPlayer musicPlayer
        -JButton resumeButton

        +SettingsPanel(SpaceInvaderPanel gamePanel, StartPanel startPanel, JLayeredPane layeredPane, MusicPlayer musicPlayer)
        +JPanel createVolumeControlPanel()
        +void customizeButton(JButton button)
        +void styleTextOnlyButton(JButton button)
        +void setBlackBackground(boolean isBlackBackground)
        +void setStartPanel(StartPanel startPanel)
        +void setMusicPlayer(MusicPlayer musicPlayer)
        +void showResumeButton(boolean show)
        +void paintComponent(Graphics g)
    }
    
    class LevelUpPanel {
        -boolean confirmLevelUp
        -SpaceInvaderPanel gamePanel
        -MusicPlayer musicPlayer
        -JLabel hpTextLabel
        -JLabel strTextLabel
        -JLabel dexTextLabel
        -JLabel totalLevelLabel
        -JLabel nextLevelUpCostLabel
        -JLabel currentExpLabel

        +LevelUpPanel(SpaceInvaderPanel gamePanel, MusicPlayer musicPlayer)
        +JPanel createLevelInfoPanel()
        +JPanel createLevelControlPanel()
        +JPanel createConfirmExitPanel()
        +void addHPUI(JPanel panel)
        +void addSTRUI(JPanel panel)
        +void addDEXUI(JPanel panel)
        +void setupStatPanel(JPanel panel, String labelName, StatGetter baseLevelGetter, StatGetter tempLevelGetter, StatSetter tempLevelSetter, StatLabelSetter textSetter, StatLabelGetter labelGetter)
        +void confirmLevelUp()
        +void exitLevelUp()
        +void updateLevelInfo()
        +void styleTextOnlyButton(JButton button)
        +void paintComponent(Graphics g)
        +void setMusicPlayer(MusicPlayer musicPlayer)
    }
    
    class GameOverPanel {
        -float alpha
        -Timer fadeTimer
        -Timer restartTimer
        -SpaceInvaderPanel gamePanel
        -MusicPlayer musicPlayer
        
        +GameOverPanel(SpaceInvaderPanel gamePanel, MusicPlayer musicPlayer)
        +void triggerFadeIn()
        +void paintComponent(Graphics g)
    }
    
    class MusicPlayer {
        -Map<String, List<Clip>> clipPool
        -float currentVolume
        +MusicPlayer()
        +void load(String id, String filepath, int count)
        +void playSegment(String id, float startSec, float endSec, boolean loop)
        +void stopById(String id)
        +void stopAll()
        +void adjustVolume(float delta)
        +void setVolume(float v)
        +float getCurrentVolume()
        +String getVolumeText()
        -void applyVolume(Clip clip)
    }
    
    
    
    SpaceInvaderPanel --> SettingsPanel
    SpaceInvaderPanel --> LevelUpPanel
    SpaceInvaderPanel --> GameOverPanel
    
    StartPanel --> SpaceInvaderPanel
    SettingsPanel --> SpaceInvaderPanel
    LevelUpPanel --> SpaceInvaderPanel
    GameOverPanel --> SpaceInvaderPanel
    
    SpaceInvaderPanel --> MusicPlayer
    StartPanel --> MusicPlayer
    SettingsPanel --> MusicPlayer
    LevelUpPanel --> MusicPlayer
    GameOverPanel --> MusicPlayer
    
    SpaceInvaderPanel --> Constants
    SettingsPanel --> Constants
    LevelUpPanel --> Constants
    GameOverPanel --> Constants

```

## 三、流程圖 (Flow Chart)

```mermaid
flowchart TD

    Start(["Start"]) --> Create["create(): 初始化資源、角色、按鈕、背景音樂"]
    Create --> Render["render(): 遊戲主迴圈"]

    Render --> CheckStageEvent{"stageEvent 值?"}

    %% ========== Main Menu ==========
    CheckStageEvent -- 0 --> Menu["顯示主選單 (firstscreen.png)"]
    Menu --> BtnPlay["點擊 buttonPlay"] -->|stageEvent=1| Render
    Menu --> BtnIns["點擊 buttonIns"] -->|stageEvent=2| Render
    Menu --> BtnLevel["點擊 buttonLevel"] -->|stageEvent=3| Render

    %% ========== Instruction Pages ==========
    CheckStageEvent -- 2 --> Instruction1["顯示說明頁面 insSpace1"]
    Instruction1 --> ClickTo21["點擊螢幕"] -->|stageEvent=21| Render

    CheckStageEvent -- 21 --> Instruction2["顯示說明頁面 insUDLR1"]
    Instruction2 --> ClickTo0["點擊螢幕"] -->|stageEvent=0| Render

    %% ========== Gameplay ==========
    CheckStageEvent -- 1 --> Game["遊戲畫面開始"]

    Game --> TimerCheck["第一次進入時: 啟動 Timer 產生怪物"]
    Game --> KeyInput["keyClicked(): 方向鍵控制移動 & 發射 SPACE"]
    KeyInput --> AddBullet["產生 wizardBullet 並加入 allObjs"]

    Game --> UpdateLoop["遍歷 allObjs 執行 update() & draw()"]
    UpdateLoop --> CheckCollision{"是否子彈碰到怪物？"}

    CheckCollision -- 是 --> Damage["怪物血量 -1, POINT+1"]
    Damage --> IsDead{"血量 <= 0？"}
    IsDead -- 是 --> RemoveMonster["移除怪物"]
    IsDead -- 否 --> SkipDamage["不做事"]

    CheckCollision -- 否 --> ContinueGame["持續遊戲"]

    UpdateLoop --> CheckWin{"monster3 是否死亡？"}
    CheckWin -- 是 --> Win["Win: 清除所有物件, 重設資料, 返回 Menu"]
    CheckWin -- 否 --> CheckLose

    CheckLose{"是否只剩 wizardPlayer？"}
    CheckLose -- 是 --> Lose["Lose: 清除所有物件, 重設資料, 返回 Menu"]
    CheckLose -- 否 --> Render

    %% ========== Others ==========
    CheckStageEvent -- 3 --> Cheat["作弊模式 (未實作畫面)"] --> Render

    Win --> Render
    Lose --> Render
    ContinueGame --> Render
    RemoveMonster --> Render
    SkipDamage --> Render
    AddBullet --> Render
    TimerCheck --> Render

```

## 四、序列圖 (Sequence Diagram)

```mermaid
sequenceDiagram
    participant Player
    participant SpaceInvaderPanel
    participant Timer1
    participant Timer2
    participant Timer3
    participant Timer4
    participant Enemy
    participant Bullet
    participant Explode
    participant Coin
    participant CampFire
    participant MusicPlayer
    participant GameOverPanel
    participant LevelUpPanel
    participant SettingsPanel

    SpaceInvaderPanel ->> Player: Create player instance
    SpaceInvaderPanel ->> CampFire: Create campfire instance
    SpaceInvaderPanel ->> Timer1: Start timer1 (15ms interval)
    SpaceInvaderPanel ->> Timer2: Start timer2 (big triangle delay)
    SpaceInvaderPanel ->> Timer4: Start timer4 (enemy spawn delay)

    Timer1 ->> SpaceInvaderPanel: actionPerformed
    SpaceInvaderPanel ->> Player: Move player
    SpaceInvaderPanel ->> Enemy: Move enemies (if enabled)
    SpaceInvaderPanel ->> Bullet: Move bullets
    SpaceInvaderPanel ->> SpaceInvaderPanel: Check collisions
    SpaceInvaderPanel ->> SpaceInvaderPanel: Check bonfire interaction
    SpaceInvaderPanel ->> SpaceInvaderPanel: Repaint screen

    Timer2 ->> SpaceInvaderPanel: Spawn BigTriangle
    SpaceInvaderPanel ->> Enemy: Add BigTriangle
    SpaceInvaderPanel ->> MusicPlayer: Play BigTriangle Phase 1 music

    Timer3 ->> SpaceInvaderPanel: BigTriangle defeated, play phase 2
    SpaceInvaderPanel ->> MusicPlayer: Play BigTriangle Phase 2 music

    Timer4 ->> SpaceInvaderPanel: Spawn regular enemies
    SpaceInvaderPanel ->> Enemy: Create new Triangle instances
    SpaceInvaderPanel ->> MusicPlayer: Play enemy spawn sound

    SpaceInvaderPanel ->> Bullet: Fire bullet (on left click)
    SpaceInvaderPanel ->> MusicPlayer: Play fireball sound

    SpaceInvaderPanel ->> Player: Dodge (on space press)
    SpaceInvaderPanel ->> MusicPlayer: Play dodge sound

    SpaceInvaderPanel ->> Player: Heal (on 'R' press)
    SpaceInvaderPanel ->> MusicPlayer: Play heal sound

    SpaceInvaderPanel ->> Player: Game Over (on death)
    SpaceInvaderPanel ->> GameOverPanel: Trigger game over screen
    SpaceInvaderPanel ->> MusicPlayer: Play game over sound

    Player ->> CampFire: Check for bonfire interaction
    SpaceInvaderPanel ->> LevelUpPanel: Show level-up panel on bonfire interaction

    SpaceInvaderPanel ->> SettingsPanel: Pause game on ESC press
    SpaceInvaderPanel ->> MusicPlayer: Adjust volume on BigTriangle phase change

```
