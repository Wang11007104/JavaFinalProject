package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.Gdx;

public class autoMonster extends movingObj {

    private float attackCooldown = 1.0f; // 每 1 秒射一次
    private float attackTimer = attackCooldown;
    private float angle = 0f; // 當前角度
    private float radius = 100f; // 半徑
    private float centerX, centerY; // 繞圈中心

    /**
     * 建立一個自動移動的怪物或子彈物件，並根據 monMode 設定其初始狀態。
     * 包含設定生命值（bloodCount）、初始座標、是否顯示圖片等屬性。
     *
     * 血量會依照 monMode 決定，例如：
     * - 0, 1：子彈，血量為 1
     * - 2：左右移動怪，血量為 3
     * - 3, 4：普通怪物，血量為 2
     * - 88：旋轉怪物，血量為 10（並記錄中心點）
     * - 99：Boss，血量為 15
     * - 100：特殊怪，血量為 20
     *
     * @param texturePath 圖片路徑，用於載入貼圖
     * @param startX      初始 X 座標
     * @param startY      初始 Y 座標
     * @param width       寬度
     * @param height      高度
     * @param monMode     行為模式代號
     * @param showImage   是否顯示圖片
     */
    public autoMonster(String texturePath, float startX, float startY, float width, float height, int monMode,
            boolean showImage) {
        super(texturePath, startX, startY, width, height, monMode);
        this.monMode = monMode;
        this.showImage = showImage;
        oriX = startX;
        oriY = startY;

        switch (monMode) {
            case 0: // 向下子彈
            case 1: // 向上子彈
                bloodCount = 1;
                break;
            case 2: // 左右怪
                bloodCount = 3;
                break;
            case 3:
            case 4:
                bloodCount = 2;
                break;
            case 99: // boss
                bloodCount = 15;
                break;
            case 88:
                centerX = this.x;
                centerY = this.y;
                bloodCount = 10;
                break;
            case 100:
                bloodCount = 20;
                break;
            default:
                break;
        }
        if (monMode == 88) {
            centerX = this.x;
            centerY = this.y;
        }

        oriBlood = bloodCount;
    }

    /**
     * 更新物件的狀態，包括位置、移動方向、角度與發射邏輯。
     * 根據 monMode 值，決定不同的移動模式（例如子彈向上/向下、怪物左右移動、Boss行為等）。
     * 當物件超出畫面邊界時會移除自身，某些模式下會自動發射子彈。
     *
     * monMode 說明：
     * 0 - 敵方子彈（向下移動）
     * 1 - 主角子彈（向上移動）
     * 2 - 怪物左右移動（從左側起始）
     * 3 - 怪物左右移動（從右側起始）
     * 4 - 垂直下降
     * 88 - 螺旋或圓形移動
     * 99 - Boss 左右移動並自動攻擊
     */
    public void update() {
        float bx = Gdx.graphics.getWidth() - texture.getWidth();
        float by = Gdx.graphics.getHeight() - texture.getHeight();

        // 向下子彈
        if (monMode == 0) {
            vx = 0;
            vy = -100;
            if (y < -150) {
                vy = 0;
                showImage = false;
                SkyWizard.allObjs.remove(this);
                return;
            }
        }

        // 向上子彈（主角發射）
        if (monMode == 1) {
            vx = 0;
            vy = 500;
            if (y > 790) {
                vy = 0;
                showImage = false;
                SkyWizard.allObjs.remove(this);
                return;
            }
        }

        if (monMode == 2) { // 左右怪（左起）
            if (x - oriX > -1 || x == bx) {
                vx = -150;
            } else if (x - oriX < -250 || x == 0) {
                vx = 150;
            }
        }

        if (monMode == 3) { // 右起
            if (x - oriX > 250 || x == bx) {
                vx = -150;
            } else if (x - oriX < 120 || x == 0) {
                vx = 150;
            }
        }

        if (monMode == 4) {
            vx = 0;
            vy = -150;
        }

        if (monMode == 88) {
            angle += 100 * Gdx.graphics.getDeltaTime();
            radius += 10 * Gdx.graphics.getDeltaTime(); // 半徑變大
            float radians = (float) Math.toRadians(angle);
            x = centerX + radius * (float) Math.cos(radians);
            y = centerY + radius * (float) Math.sin(radians);
        }

        if (monMode == 99) { // boss
            if (x - oriX > -1 || x == bx) {
                vx = -150;
            } else if (x - oriX < -500 || x == 0) {
                vx = 150;
            }
        }

        if (monMode == 88) {
            angle += 100 * Gdx.graphics.getDeltaTime();
            radius += 10 * Gdx.graphics.getDeltaTime(); // 半徑變大
            float radians = (float) Math.toRadians(angle);
            x = centerX + radius * (float) Math.cos(radians);
            y = centerY + radius * (float) Math.sin(radians);
        }
        super.update();

        // 怪物自動發射子彈
        if (monMode == 2 || monMode == 3 || monMode == 99) {
            attackTimer -= Gdx.graphics.getDeltaTime();
            if (attackTimer <= 0) {
                autoMonster bullet = new autoMonster("monfire.png", x + w / 2 - 10, y - 20, 20, 30, 0, true);
                SkyWizard.allObjs.add(bullet);
                attackTimer = attackCooldown;
            }
        }
    }
}
