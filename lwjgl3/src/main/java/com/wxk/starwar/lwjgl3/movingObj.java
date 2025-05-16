package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class movingObj {
    public Texture texture;
    public float x, y;// 即時的位置
    public float w, h;
    public float vx, vy;
    // public boolean offImage=false;
    public int monMode;
    public boolean showImage = true;
    public int bloodCount = 20, oriBlood = 20;// wizaed.blood
    public float oriX, oriY;
    public static float explodeX, explodeY, explodeCount;

    /**
     * 建立一個移動物件，並初始化其貼圖、位置、尺寸與移動模式。
     *
     * @param texturePath 貼圖檔案路徑，用於載入圖像
     * @param startX      初始 X 座標
     * @param startY      初始 Y 座標
     * @param width       物件寬度
     * @param height      物件高度
     * @param monMode     行為模式代號（用於判斷不同移動邏輯）
     */
    public movingObj(String texturePath, float startX, float startY, float width, float height, int monMode) {
        this.texture = new Texture(texturePath); // 加載飛船圖片
        this.x = startX; // 設定飛船的 X 座標
        this.y = startY; // 設定飛船的 Y 座標
        oriX = startX;
        oriY = startY;
        this.w = width;
        this.h = height;
        this.monMode = monMode;
        vx = 0;
        vy = 0;

    }

    /**
     * 更新移動物件的位置與碰撞狀態。
     * 
     * 執行流程包括：
     * - 根據速度與 deltaTime 計算新位置
     * - 限制物件在畫面邊界內（子彈模式 0 和 1 除外）
     * - 與其他物件進行碰撞偵測與處理
     * - 不處理兩個子彈或兩個怪物間的碰撞
     * - 處理怪物與子彈碰撞：雙方扣血
     * - 若任一方血量歸 0，則將其從場上移除並加分
     * - 若主角血量歸 0，觸發遊戲結束事件（stageEvent = 100）
     */
    public void update() {
        float unmovX, unmovY;
        unmovX = x;
        unmovY = y;

        float deltaTime = Gdx.graphics.getDeltaTime();
        x += vx * deltaTime;
        y += vy * deltaTime;

        // 限制邊界
        if (monMode != 0 && monMode != 1) { // 給超出範圍後刪掉mode0需要出邊界
            x = Math.max(0, Math.min(x, Gdx.graphics.getWidth() + 80 - texture.getWidth()));
            y = Math.max(0, Math.min(y, Gdx.graphics.getHeight() + 100 - texture.getHeight()));
        }

        int index = SkyWizard.allObjs.indexOf(this);
        // System.out.println(index);
        // System.err.println();
        for (int i = index; i < SkyWizard.allObjs.size(); i++) {
            // System.err.print(i);
            movingObj obj = SkyWizard.allObjs.get(i);

            if (this != obj) { // 自己不跟自己碰撞
                boolean b = collide(this, obj);
                // System.out.println(b);
                if (b) {

                    boolean areBullets = (this.monMode < 2 && obj.monMode < 2);
                    boolean areMonsters = (this.monMode >= 2 && this.monMode < 100 && obj.monMode >= 2
                            && obj.monMode < 100);
                    boolean monAndMonBu = (this.monMode * obj.monMode == 0 && (this.monMode + obj.monMode) >= 2
                            && (this.monMode + obj.monMode) < 100);

                    if (!(areMonsters)) {

                        if (!(areBullets) && !(monAndMonBu)) {// 碰撞扣血
                            this.bloodCount -= 1;
                            obj.bloodCount--;
                            if (this.monMode == 1) {
                                explodeX = this.x;
                                explodeY = this.y;
                                explodeCount = 5;
                            }
                            if (obj.monMode == 1) {
                                explodeX = obj.x;
                                explodeY = obj.y;
                                explodeCount = 5;
                            }

                        }

                        if (obj != SkyWizard.wizardPlayer && obj.bloodCount <= 0) {
                            SkyWizard.allObjs.remove(obj);
                            if (!(Math.abs(this.monMode - obj.monMode) == 200)) {
                                SkyWizard.countPoint++;
                            }
                        }
                        if (this != SkyWizard.wizardPlayer && this.bloodCount <= 0) {
                            SkyWizard.allObjs.remove(this);
                            if (!(Math.abs(this.monMode - obj.monMode) == 200)) {
                                SkyWizard.countPoint++;
                            }
                        }

                    }

                    if (SkyWizard.wizardPlayer.bloodCount == 0) {
                        SkyWizard.stageEvent = 100;
                    }

                    /*
                     * 不要動
                     * x=unmovX;
                     * y=unmovY;
                     */
                }
            }
        }

    }

    /**
     * 根據傳入的texture、xy座標、寬和高在視窗畫圖
     * 
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, x, y, w, h);
        batch.end();
    }

    /**
     * 判斷兩個物件之間是否發生碰撞。
     *
     * 透過比較兩個物件的座標和寬高，判斷它們的矩形區域是否有重疊。
     * - 如果 obj1 和 obj2 在 X 與 Y 軸方向都有重疊，代表它們撞在一起了。
     * - 在 Y 軸偵測時保留 20 像素容錯範圍，避免太早碰撞。
     *
     * @param obj1 第一個物件
     * @param obj2 第二個物件
     * @return 如果 obj1 和 obj2 有重疊（碰撞），回傳 true；否則 false
     */
    public boolean collide(movingObj obj1, movingObj obj2) {

        // System.out.println("x="+obj1.x+" x2="+obj2.x);

        if ((obj2.x - obj1.x <= obj1.w && obj2.x - obj1.x >= -obj2.w) &&
                (obj2.y - obj1.y <= obj1.h && obj2.y - obj1.y >= -obj2.h + 20)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 重製物件的xy座標
     * 重製物件的血量
     */
    public void allRestore() {
        x = oriX;
        y = oriY;
        bloodCount = oriBlood;

    }

    /**
     * 釋放此物件所持有的外部資源，如貼圖、音效等。
     * 在物件不再使用時呼叫，避免記憶體與系統資源洩漏。
     */
    public void dispose() {
        texture.dispose();
    }

}
