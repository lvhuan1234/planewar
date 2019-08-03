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

public class BossBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED*10;

    public BossBullet() {
        this(0,0,ImageMap.get("bob1"));
    }

    public BossBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),
                image.getHeight(null),null);
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
            gameFrame.bossBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bossBulletList.remove(this);
            if (gameFrame.hp>0) {
                gameFrame.hp-=30;
            }else {
                gameFrame.gameOver = true;
            }

        }
    }
}
