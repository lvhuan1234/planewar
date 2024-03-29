package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {
    private Image image;

    private int speed = FrameConstant.GAME_SPEED*5;

    public EnemyBullet() {

    }

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

    }

    @Override
    public void move() {
        if (getY()+image.getHeight(null)>0) {
            setY(getY()+speed);
        }

        borderTesting();
    }

    public void borderTesting() {
        if (getY()>FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    //敌方子弹与飞机碰撞
    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);

            if (gameFrame.hp>0) {
                gameFrame.hp-=10;
            }else {
                gameFrame.gameOver = true;
            }

        }
    }
}
