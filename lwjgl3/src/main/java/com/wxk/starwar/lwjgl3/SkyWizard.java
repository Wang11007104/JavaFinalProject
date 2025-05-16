package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 遊戲開始
 * @author B11007104 王星凱 B11230227 連羽健
 * @version 1.0
 * @date 2025-05-16
*/
public class SkyWizard extends ApplicationAdapter {
    public static SpriteBatch batch;
    private BitmapFont font;
    private Texture firstscreen;
    public static Texture explode;
    private Texture whiteTexture;
    public static int stageEvent = 0;
    private Stage stage;
    private ImageButton pageButton;
    private ImageButton pageButton1;
    private ImageButton pageButton2;
    private ImageButton starButton1;
    private ImageButton starButton2;
    private ImageButton starButton3;
    private ShapeRenderer shapeRenderer;
    // private SpriteBatch batch1;
    public static movingObj wizardPlayer;
    private autoMonster monster1;
    private autoMonster monster2;
    private autoMonster monster3;
    private autoMonster monster4;
    private autoMonster monster5;
    private autoMonster monster6;
    private autoMonster monster7;
    private autoMonster monster8;
    private autoMonster monster9;
    private autoMonster monster10;
    private autoMonster monster11;
    private autoMonster monster12;
    private Music backgroundMusic;
    private int countTimer = 0;
    private ShapeRenderer shapeRenderer1;
    private Array<Circle> circles;
    public boolean showImage = true;
    public static ArrayList<movingObj> allObjs = new ArrayList<>();
    public static ArrayList<movingObj> allmonsters = new ArrayList<>();
    public static int countPoint = 0;
    public static int firstRender = 0;
    public static int firstRender1 = 0;
    private Timer.Task timerHandle;
    private int previousBloodCount = 15;
    private float bloodLine;
    private int monster3OriBlood;
    private int addMonster = 0;
    private int mp = 0;// 每擊中20次能釋放大招
    private boolean ace = false;// 放大朝狀態
    private boolean isWin;

    /**
     * Circle 類別表示一個從螢幕上方往下移動的圓形物件。
     * 用於背景的白色圓點動畫效果。
     *
     */
    private class Circle {
        float x, y, radius, speedY;

        Circle() {
            reset(); // 一開始就初始化隨機位置
        }

        void reset() {
            x = MathUtils.random(0, Gdx.graphics.getWidth());
            y = MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + 300);
            radius = MathUtils.random(2, 6);
            speedY = MathUtils.random(50, 150);
        }

        void update(float delta) {
            y -= speedY * delta;
            if (y + radius < 0)
                reset();
        }
    }

    /**
     * 處理鍵盤輸入事件，根據按鍵改變角色移動速度與發射子彈。
     * 
     * <p>
     * 具體功能：
     * </p>
     * <ul>
     * <li>按方向鍵（←→↑↓）控制角色移動方向和速度。</li>
     * <li>按空白鍵 (SPACE) 發射普通火球子彈，同時播放音效並增加能量值 mp。</li>
     * <li>當 mp 積滿（>= 20）時，觸發大招技能 ace 可用。</li>
     * <li>按 A 鍵且大招 ace 可用時，發射大招子彈，並播放音效，然後重置大招狀態。</li>
     * </ul>
     */
    private void keyClicked() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            wizardPlayer.vx = -300;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            wizardPlayer.vx = 300;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            wizardPlayer.vy = 300;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            wizardPlayer.vy = -300;
        } else {
            wizardPlayer.vx = 0;
            wizardPlayer.vy = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            autoMonster wizardBullet = new autoMonster("fire.png", wizardPlayer.x + 20,
                    wizardPlayer.y + wizardPlayer.h + 10, 45, 50, 1, true);
            allObjs.add(wizardBullet);

            Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("fire.mp3"));
            clickSound.play();
            mp++;
            if (mp >= 20) {
                ace = true;
                mp = 0;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A) && ace == true) {
            autoMonster wizardBullet = new autoMonster("bigbu.png", wizardPlayer.x - 45,
                    wizardPlayer.y + wizardPlayer.h + 10, 200, 200, 1, true);
            wizardBullet.bloodCount = 5;
            allObjs.add(wizardBullet);

            Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("fire.mp3"));
            clickSound.play();
            ace = false;
        }

    }

    /**
     * 新增一個圖片按鈕 (ImageButton) 並設定其位置、大小及名稱，
     * 並且為按鈕註冊點擊事件，根據不同按鈕名稱切換遊戲狀態 {@code stageEvent}。
     * 
     * <p>
     * 按鈕點擊時會根據名稱執行以下行為：
     * </p>
     * <ul>
     * <li>buttonPlay：切換遊戲狀態為 1（遊戲開始）。</li>
     * <li>buttonIns：切換遊戲狀態為 2（說明頁面）。</li>
     * <li>insSpace1：切換遊戲狀態為 21（說明第二頁）。</li>
     * <li>insUDLR1：切換遊戲狀態為 0（返回主畫面），並隱藏說明頁按鈕。</li>
     * <li>buttonLevel：切換遊戲狀態為 3（關卡選擇）。</li>
     * <li>其他名稱：輸出「未知按鈕」訊息。</li>
     * </ul>
     * 
     * @param picPath    按鈕圖片的路徑
     * @param x          按鈕在畫面上的 X 座標
     * @param y          按鈕在畫面上的 Y 座標
     * @param w          按鈕寬度
     * @param h          按鈕高度
     * @param buttonName 按鈕名稱，用於識別不同按鈕並觸發不同事件
     * @return 新建立的 ImageButton 物件
     */
    private ImageButton addButton(String picPath, int x, int y, int w, int h, String buttonName) {
        ImageButton imageButton;
        // 載入圖片
        Texture buttonTexture = new Texture(Gdx.files.internal(picPath));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);

        // 創建圖片按鈕
        imageButton = new ImageButton(buttonDrawable);
        imageButton.setPosition(x, y);
        imageButton.setSize(w, h);

        // 設定按鈕名稱，方便識別
        imageButton.setName(buttonName);

        // 加入點擊事件
        imageButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // if(x>=79 && x<=269 &&y>=16 &&y<=83){

                String clickedButton = event.getListenerActor().getName(); // 取得按鈕名稱

                switch (clickedButton) {
                    case "buttonPlay":
                        // System.out.println("Play" );
                        stageEvent = 1;
                        break;
                    case "buttonIns":
                        // System.out.println(" Ins");
                        stageEvent = 2;
                        break;
                    case "insSpace1":
                        // System.out.println(" InsSpace1");
                        stageEvent = 21;
                        break;
                    case "insUDLR1":
                        // System.out.println(" insUDLR1");
                        stageEvent = 0;
                        pageButton.setVisible(false);
                        pageButton1.setVisible(false);
                        break;
                    case "buttonLevel":
                        // System.out.println(" Cheat");
                        stageEvent = 3;
                        break;

                    default:
                        System.out.println("未知按鈕");

                }

                // }
            }
        });

        stage.addActor(imageButton);
        return imageButton;

    }

    /**
     * 定時產生兩種怪物，並加入遊戲物件與怪物管理清單。
     *
     * <p>
     * 透過 LibGDX 的 {@link Timer} 每 4 秒觸發一次，隨機設定怪物位置，
     * 並創建兩個不同參數的 {@link autoMonster} 實例：
     * </p>
     * <ul>
     * <li>怪物 m1：使用 "ghost.png" 圖片，隨機 X 座標在螢幕寬度 20% 至 80% 範圍，Y 座標在 30% 至 50% 範圍，類型為
     * 2。</li>
     * <li>怪物 m2：使用 "ghost.png" 圖片，隨機 X 座標介於 50 至 500，Y 座標介於 200 至 300，類型為 88。</li>
     * </ul>
     *
     * <p>
     * 這些怪物會加入到全局遊戲物件集合 {@code allObjs} 及怪物專屬集合 {@code allmonsters} 中管理。
     * </p>
     */
    private void scheduleMonsters() {
        timerHandle = new Timer.Task() {
            public void run() {
                float screenWidth = Gdx.graphics.getWidth();
                float screenHeight = Gdx.graphics.getHeight();

                float x1 = MathUtils.random(screenWidth * 0.2f, screenWidth * 0.8f);
                float y1 = MathUtils.random(screenHeight * 0.3f, screenHeight * 0.5f);
                autoMonster m1 = new autoMonster("ghost.png", x1, y1, 75, 100, 2, true);
                allObjs.add(m1);
                allmonsters.add(m1);

                float x2 = MathUtils.random(50, 500);
                float y2 = MathUtils.random(200, 300);
                autoMonster m2 = new autoMonster("ghost.png", x2, y2, 75, 100, 88, true);
                allObjs.add(m2);
                allmonsters.add(m2);
            }
        };
        Timer.schedule(timerHandle, 0, 4);

    }

    /**
     * 遊戲初始化方法，會在遊戲啟動時呼叫。
     * 
     * <p>
     * 主要功能包括：
     * </p>
     * <ul>
     * <li>載入並設定背景音樂，包含循環播放與音量調整，並開始播放音樂</li>
     * <li>建立 {@link SpriteBatch} 用於繪圖</li>
     * <li>載入預設字體並設定字體大小</li>
     * <li>載入初始背景與爆炸圖片資源</li>
     * <li>建立 {@link Stage}，並設定輸入處理器以監聽使用者操作</li>
     * <li>建立並設定多個按鈕（開始、說明、關卡與介紹頁面按鈕），部分按鈕初始為不可見</li>
     * <li>建立 {@link ShapeRenderer} 用於繪製形狀（如背景白色圓圈）</li>
     * <li>初始化遊戲角色與怪物物件，並加入遊戲物件集合管理</li>
     * <li>建立背景白色圓圈的動畫陣列，預設 100 個圓形物件</li>
     * <li>初始化怪物血條相關參數</li>
     * </ul>
     * 
     * @implNote 請確保遊戲資源檔（如音樂和圖片）已正確放置於資源目錄中，以避免執行時錯誤。
     */
    @Override
    public void create() {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));

        // 設置循環播放
        backgroundMusic.setLooping(true);

        // 設置音量（範圍 0.0 ~ 1.0）
        backgroundMusic.setVolume(0.5f);

        // 開始播放
        backgroundMusic.play();

        backgroundMusic.setLooping(true);

        batch = new SpriteBatch();
        font = new BitmapFont(); // 預設字體
        font.getData().setScale(2f);
        firstscreen = new Texture(Gdx.files.internal("firstscreen.png")); // 加載圖片
        explode = new Texture(Gdx.files.internal("explode.png"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        starButton1 = addButton("buttonplay.png", 200, 330, 375, 100, "buttonPlay");
        // starButton1.setVisible(false);

        starButton2 = addButton("buttonIns.png", 230, 185, 375, 100, "buttonIns");
        // starButton2.setVisible(false);

        starButton3 = addButton("buttonLevel.png", 200, 50, 375, 100, "buttonLevel");
        // starButton3.setVisible(false);

        pageButton = addButton("insSpace1.png", 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), "insSpace1");
        pageButton.setVisible(false);

        pageButton1 = addButton("insUDLR1.png", 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), "insUDLR1");
        pageButton1.setVisible(false);

        shapeRenderer = new ShapeRenderer();

        wizardPlayer = new movingObj("wizard.png", 260, 0, 75 / 2, 100 / 2, 200);

        monster1 = new autoMonster("wizard.png", 300, 350, 75, 100, 2, true);
        monster3 = new autoMonster("dragon1.png", 300, 500, 300, 300, 99, true);
        monster2 = new autoMonster("redboss.png", 150, 500, 300, 300, 88, true);

        allmonsters.add(monster1);
        allmonsters.add(monster3);
        // allmonsters.add(new autoMonster(null, countPoint, stageEvent, firstRender,
        // countTimer, countPoint, showImage)) //fornewone

        allObjs.add(wizardPlayer);
        // allObjs.add(monster1);
        allObjs.add(monster3);

        shapeRenderer = new ShapeRenderer();// for back ground white circle
        circles = new Array<>();
        for (int i = 0; i < 100; i++) {
            circles.add(new Circle());
        }

        bloodLine = monster3.w;
        monster3OriBlood = monster3.bloodCount;

    }

    /**
     * 渲染並更新遊戲主畫面。
     * 
     * 根據 {@code stageEvent} 狀態執行不同遊戲階段的渲染與邏輯：
     * <ul>
     * <li>200 - 遊戲勝負結算畫面，顯示「YOU WIN!」或「YOU LOSE」及分數，點擊重置遊戲</li>
     * <li>300 - 遊戲結束畫面 (GAME OVER)，顯示分數，點擊重置遊戲</li>
     * <li>0 - 遊戲開始畫面，顯示開始按鈕與背景</li>
     * <li>1 - 遊戲進行中，更新遊戲物件、怪物血條、背景特效，處理輸入、碰撞與勝負判定</li>
     * <li>2, 21 - 介紹畫面，顯示說明按鈕與背景</li>
     * <li>3 - 關卡進階畫面，控制怪物生成與遊戲難度，更新畫面與狀態</li>
     * </ul>
     * 
     * <p>
     * 繪製元素包括：
     * </p>
     * <ul>
     * <li>背景圖片與顏色填充</li>
     * <li>文字顯示（標題、分數、指示文字）</li>
     * <li>怪物與玩家的血條</li>
     * <li>動態圓圈背景特效</li>
     * <li>爆炸動畫效果</li>
     * <li>按鈕與 UI 控件</li>
     * </ul>
     * 
     * <p>
     * 同時處理輸入事件，包含滑鼠點擊與鍵盤操作，並根據遊戲狀態切換場景與重置資料。
     * </p>
     * 
     * @implNote 建議維持 {@code @Override} 註解，確保此方法為覆寫
     *           {@link com.badlogic.gdx.ApplicationListener#render()}，
     *           且方便維護與編譯器檢查。
     */
    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清除畫面

        countTimer++;

        if (stageEvent == 200) { // 結算畫面
            batch.begin();
            font.getData().setScale(3f);
            font.setColor(Color.WHITE);
            if (isWin) {
                font.draw(batch, "YOU WIN!", 180, 600);
            } else {
                font.draw(batch, "YOU LOSE", 180, 600);
            }
            font.getData().setScale(2f);
            font.draw(batch, "SCORE: " + countPoint, 200, 500);
            font.draw(batch, "Use mouse to click anywhere to return", 60, 400);
            batch.end();

            if (Gdx.input.justTouched()) {

                SkyWizard.countPoint = 0;
                stageEvent = 0;

            }
            return;
        }

        // System.out.println(stageEvent);
        if (stageEvent == 0) { // 開始畫面
            starButton1.setVisible(true);
            starButton2.setVisible(true);
            starButton3.setVisible(true);

            batch.begin();
            batch.setColor(0.53f, 0.81f, 0.92f, 1f);
            batch.draw(firstscreen, 0, 0, 600, 800);
            batch.end();

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }

        if (stageEvent == 1) { // 遊戲畫面
            starButton1.setVisible(false);
            starButton2.setVisible(false);
            starButton3.setVisible(false);

            // only run once
            if (firstRender == 0 && stageEvent == 1) {
                scheduleMonsters();
                firstRender++;
            }

            // for monster bloood line
            if (allObjs.contains(monster3) == true) {

                if (monster3.bloodCount < previousBloodCount) {
                    bloodLine -= (monster3.w) / monster3OriBlood;
                }

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // 或 ShapeType.Line 畫框線
                shapeRenderer.setColor(Color.RED); // 設定顏色
                shapeRenderer.rect(monster3.x, monster3.y - 10, monster3.w, 10);
                shapeRenderer.setColor(Color.GREEN); // 設定顏色
                shapeRenderer.rect(monster3.x, monster3.y - 10, bloodLine, 10);
                shapeRenderer.end();
                previousBloodCount = monster3.bloodCount;

            }
            // for monster bloood line

            // for back ground white circle
            float delta = Gdx.graphics.getDeltaTime();
            for (Circle c : circles) {
                c.update(delta);
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f);

            for (Circle c : circles) {
                shapeRenderer.circle(c.x, c.y, c.radius);
            }
            shapeRenderer.end();
            // for back ground white circle

            keyClicked();

            for (int i = 0; i < allObjs.size(); i++) {
                movingObj obj = allObjs.get(i);
                obj.update();
                if (obj.showImage) { // 利用是否顯示方式關掉圖片
                    obj.draw(batch);
                }
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(wizardPlayer.x, wizardPlayer.y + wizardPlayer.h + 5, wizardPlayer.w, 6);
            shapeRenderer.setColor(Color.GREEN);
            float hpRatio = (float) wizardPlayer.bloodCount / wizardPlayer.oriBlood;
            shapeRenderer.rect(wizardPlayer.x, wizardPlayer.y + wizardPlayer.h + 5, wizardPlayer.w * hpRatio, 6);
            shapeRenderer.end();
            /*
             * if(allObjs.size()==1){
             * stageEvent=11;
             * }
             */

            if (allObjs.contains(monster3) != true) { // win the game禽賊先擒王
                // 還須設置按鈕
                allObjs.clear();
                if (timerHandle != null) {
                    timerHandle.cancel();
                }
                System.out.println("win");
                allObjs.add(monster3);
                allObjs.add(wizardPlayer);
                wizardPlayer.allRestore();
                allmonsters.forEach(i -> i.allRestore()); // 對於每一個項目都做同一件事情
                bloodLine = monster3.w;
                monster3OriBlood = monster3.bloodCount;
                SkyWizard.firstRender = 0;
                SkyWizard.stageEvent = 0;
                isWin = true;
                stageEvent = 200;

                isWin = true;
                stageEvent = 200;
            }

            if (stageEvent == 100) { // lose
                // 還須設置按鈕

                allObjs.clear();
                if (timerHandle != null) {
                    timerHandle.cancel();
                }
                System.out.println("lose");
                allObjs.add(monster3);
                allObjs.add(wizardPlayer);
                wizardPlayer.allRestore();
                allmonsters.forEach(i -> i.allRestore()); // 對於每一個項目都做同一件事情
                bloodLine = monster3.w;
                monster3OriBlood = monster3.bloodCount;
                SkyWizard.firstRender = 0;
                SkyWizard.stageEvent = 0;
                isWin = false;
                stageEvent = 200;
            }

            // 得分
            batch.begin();
            font.draw(batch, "POINT:" + countPoint, 400, 790);
            batch.end();
            // 得分

            if (movingObj.explodeCount > 0) {
                SkyWizard.batch.begin();
                SkyWizard.batch.draw(explode, movingObj.explodeX - 20, movingObj.explodeY - 20, 100, 100);
                SkyWizard.batch.end();
                movingObj.explodeCount--;
            }
        }

        if (stageEvent == 2) { // 點擊介紹後畫面
            // addButton("insSpace.png", 100, 100, 100, 100, "insSpace");
            pageButton.setVisible(true);

            shapeRenderer.begin((ShapeRenderer.ShapeType.Filled));
            shapeRenderer.setColor(0.53f, 0.81f, 0.92f, 1f);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            shapeRenderer.end();

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            starButton1.setVisible(false);
            starButton2.setVisible(false);
            starButton3.setVisible(false);

        }

        if (stageEvent == 21) { // 介紹畫面下第一張
            // addButton("insSpace.png", 100, 100, 100, 100, "insSpace");
            pageButton1.setVisible(true);

            shapeRenderer.begin((ShapeRenderer.ShapeType.Filled));
            shapeRenderer.setColor(0.53f, 0.81f, 0.92f, 1f);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            shapeRenderer.end();

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();

        }

        if (stageEvent == 3) { // lv
            starButton1.setVisible(false);
            starButton2.setVisible(false);
            starButton3.setVisible(false);

            if (countPoint % 100 >= 10 && allObjs.contains(monster3) == false) {
                if (firstRender1 == 0) {
                    scheduleMonsters();
                    allObjs.add(monster2);
                    monster2.allRestore();
                    monster2.bloodCount = countPoint / 100 * 10 + 20;
                }
                firstRender1++;
            }

            if (countPoint % 10 == 5) {
                if (firstRender == 0) {
                    timerHandle = new Timer.Task() {
                        @Override
                        public void run() {
                            if (!allObjs.contains(monster1)) {
                                autoMonster m = new autoMonster("ghost.png", 300, 350, 75, 100, 2, true);
                                allObjs.add(m);
                                allmonsters.add(m);
                                addMonster++;

                                if (addMonster == 5) {
                                    if (timerHandle != null) {
                                        this.cancel();
                                    } // 在這裡取消自己
                                    addMonster = 0; // 重置計數
                                }
                            }
                        }
                    };

                    Timer.schedule(timerHandle, 0, (float) Math.random() * 4);
                    firstRender++;
                }

            }

            // for monster bloood line
            if (allObjs.contains(monster3) == true) {

                if (monster3.bloodCount < previousBloodCount) {
                    bloodLine -= (monster3.w) / monster3OriBlood;
                }

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // 或 ShapeType.Line 畫框線
                shapeRenderer.setColor(Color.RED); // 設定顏色
                shapeRenderer.rect(monster3.x, monster3.y - 10, monster3.w, 10);
                shapeRenderer.setColor(Color.GREEN); // 設定顏色
                shapeRenderer.rect(monster3.x, monster3.y - 10, bloodLine, 10);
                shapeRenderer.end();
                previousBloodCount = monster3.bloodCount;

            }
            // for monster bloood line

            // for back ground white circle
            float delta = Gdx.graphics.getDeltaTime();
            for (Circle c : circles) {
                c.update(delta);
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f);

            for (Circle c : circles) {
                shapeRenderer.circle(c.x, c.y, c.radius);
            }
            shapeRenderer.end();
            // for back ground white circle

            keyClicked();

            for (int i = 0; i < allObjs.size(); i++) {
                movingObj obj = allObjs.get(i);
                obj.update();
                if (obj.showImage) { // 利用是否顯示方式關掉圖片
                    obj.draw(batch);
                }
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(wizardPlayer.x, wizardPlayer.y + wizardPlayer.h + 5, wizardPlayer.w, 6);
            shapeRenderer.setColor(Color.GREEN);
            float hpRatio = (float) wizardPlayer.bloodCount / wizardPlayer.oriBlood;
            shapeRenderer.rect(wizardPlayer.x, wizardPlayer.y + wizardPlayer.h + 5, wizardPlayer.w * hpRatio, 6);
            shapeRenderer.end();

            if (stageEvent == 100) { // lose
                // 還須設置按鈕

                allObjs.clear();
                if (timerHandle != null) {
                    timerHandle.cancel();
                }
                System.out.println("lose");
                allObjs.add(monster3);
                allObjs.add(wizardPlayer);
                wizardPlayer.allRestore();
                allmonsters.forEach(i -> i.allRestore()); // 對於每一個項目都做同一件事情
                bloodLine = monster3.w;
                monster3OriBlood = monster3.bloodCount;
                SkyWizard.firstRender = 0;
                SkyWizard.firstRender1 = 0;
                SkyWizard.stageEvent = 300;

            }

            // 得分
            batch.begin();
            font.draw(batch, "POINT:" + countPoint, 400, 790);
            batch.end();
            // 得分

            if (movingObj.explodeCount > 0) {
                SkyWizard.batch.begin();
                SkyWizard.batch.draw(explode, movingObj.explodeX - 20, movingObj.explodeY - 20, 100, 100);
                SkyWizard.batch.end();
                movingObj.explodeCount--;
            }

        }

        if (stageEvent == 300) { // 結算畫面
            batch.begin();
            font.getData().setScale(3f);
            font.setColor(Color.WHITE);

            font.draw(batch, "GAME OVER!", 180, 600);

            font.getData().setScale(2f);
            font.draw(batch, "SCORE: " + countPoint, 200, 500);
            font.draw(batch, "Use mouse to click anywhere to return", 60, 400);
            batch.end();

            if (Gdx.input.justTouched()) {

                SkyWizard.countPoint = 0;
                stageEvent = 0;

            }
            return;
        }

    }

    /**
     * 釋放此物件所持有的外部資源，如貼圖、音效等。
     * 在物件不再使用時呼叫，避免記憶體與系統資源洩漏。
     */
    @Override
    public void dispose() {
        shapeRenderer.dispose();
        backgroundMusic.dispose();
        batch.dispose();
        font.dispose();
        if (whiteTexture != null) {
            whiteTexture.dispose(); // 釋放資源
        }
        stage.dispose();

        if (wizardPlayer != null) {
            wizardPlayer.dispose(); // 釋放資源
        }

    }
}
