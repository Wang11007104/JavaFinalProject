
# 2025 JAVA 期末專案範例
- 成員：
  連羽健B11230227
  王星凱B11007104
  
- 分工說明：
  
  王星凱:
  程式架構、遊戲介面、功能構想與實現、程式介紹、完善.md檔、git log、流程圖、類別圖、時序圖
  撰寫內容(詳情見下方功能表):1~12
  
  連羽健:
  程式修改、簡報介紹、影片錄影
  撰寫內容(詳情見下方功能表):3~12
  
- [報告影片連結]()
- [git log連結](https://github.com/Wang11007104/JavaFinalProject/blob/main/commit%E6%AD%B7%E5%8F%B2%E7%B4%80%E9%8C%84.txt)
- [doc.html連結](file:///C:/Users/User/OneDrive/Documents/JavaFinalProject/lwjgl3/build/docs/javadoc/com/wxk/starwar/lwjgl3/SkyWizard.html)
- 
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
        SkyWizard <|-- movingObj
        movingObj <|-- autoMonster
         SkyWizard o-- "*" Circle
    class SkyWizard {
        +create()
        +render()
        +dispose()
        -keyClicked()
        -scheduleMonsters()
        -addButton(picPath, x, y, w, h, buttonName)
        int stageEvent
        boolean showImage
        boolean ace
        boolean isWin
        int countPoint
    }

    class Circle {
        +update(delta)
        -reset()
        float x
        float y
        float radius
        float speedY
    }

    class movingObj {
        +update()
        +draw(batch)
        +allRestore()
        +dispose()
        float x
        float y
        float w
        float h
        float vx
        float vy
        int bloodCount
        int oriBlood
        boolean showImage
        static int explodeCount
        static float explodeX
        static float explodeY
    }

    class autoMonster {
        +update()
        +draw(batch)
        +allRestore()
        +dispose()
        int bloodCount
        int type
        boolean isEnemy
    }



        class movingObj {
          -int sizeInFeet
          -canEat()
        }
        class autoMonster {
          +bool is_wild
          +run()
        }

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
actor Player
participant SkyWizard
participant Stage
participant ImageButton
participant "Timer.Task" as Timer
participant "movingObj / autoMonster" as Object

Player -> SkyWizard : 啟動遊戲
SkyWizard -> Stage : setInputProcessor
SkyWizard -> ImageButton : addButton() / setClickListener
SkyWizard -> SkyWizard : 初始化資源 / 音樂 / 角色
Player -> ImageButton : 點擊 [Play]
ImageButton -> SkyWizard : onClick()\nstageEvent = 1

SkyWizard -> Timer : scheduleMonsters()
Timer -> Object : 產生 autoMonster

Player -> SkyWizard : 按鍵操作（移動/SPACE/A）
SkyWizard -> Object : 建立子彈 / 大招

loop 遊戲進行中
    SkyWizard -> Object : update()
    Object -> SkyWizard : 傳回狀態（血量/死亡）
end

SkyWizard -> SkyWizard : 判定勝利或失敗

alt 玩家勝利
    SkyWizard -> Player : 顯示 YOU WIN
else 玩家失敗
    SkyWizard -> Player : 顯示 GAME OVER
end

Player -> SkyWizard : 點擊螢幕返回
SkyWizard -> SkyWizard : 重置資料 / 回主畫面

## 五、與 ChatGPT 的協作紀錄摘要
- 學習LibGDX框架與語法(create、render、左下角為(0，0)、圖片呼叫方式)
- 版本與libGDX相容性
- 修整build.gradle的各種設定
- vscode與libGDX的相容性
- 不同大小圓點由上而下寫法





```
