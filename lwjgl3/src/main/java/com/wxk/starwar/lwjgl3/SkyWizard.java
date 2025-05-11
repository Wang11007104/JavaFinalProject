package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.Gdx;

public class autoMonster extends movingObj {

    private float attackCooldown = 2.0f;  // 每 2 秒射一次
    private float attackTimer = attackCooldown;

    public autoMonster(String texturePath, float startX, float startY, float width, float height, int monMode, boolean showImage) {
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
            case 100:
                bloodCount = 10;
                break;
            default:
                break;
        }

        oriBlood = bloodCount;
    }

    public void update() {
        float bx = Gdx.graphics.getWidth() - texture.getWidth();
        float by = Gdx.graphics.getHeight() - texture.getHeight();

        // 向下子彈
        if (monMode == 0) {
            vx = 0;
            vy = -300;
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
            if (x - oriX > -120 || x == bx) {
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

        if (monMode == 99) { // boss
            if (x - oriX > -1 || x == bx) {
                vx = -150;
            } else if (x - oriX < -500 || x == 0) {
                vx = 150;
            }
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
