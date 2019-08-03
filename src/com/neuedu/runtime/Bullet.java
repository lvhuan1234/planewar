package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.List;
import java.util.Random;


public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 7;

    public int BloodPackage = 5;

    private int hp =100;

    private Random random = new Random();

    public Bullet() {
        this(0, 0,ImageMap.get("myb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {
        move();

        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //子弹与敌方飞机碰撞
    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                enemyPlaneList.remove(enemyPlane);
                gameFrame.bulletList.remove(this);
                gameFrame.score += enemyPlane.getType() * 5;
                /*if (gameFrame.hp < 100) {
                    //if (random.nextInt(1000)>990) {
                    gameFrame.hp += BloodPackage;
                    //}
                }*/
            }
        }
    }

    //子弹与boss的碰撞
    public void collisionTesting2(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {
            if (hp>0) {
                hp-=2;
            }else {

                gameFrame.bulletList.remove(this);
                gameFrame.score += 50;
                gameFrame.gameOver=true;
            }


        }
    }
}

