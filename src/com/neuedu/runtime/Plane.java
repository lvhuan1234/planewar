package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;


import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private boolean up,right,down,left;

    private boolean fire;

    private int speed = FrameConstant.GAME_SPEED *3;

    public Plane() {
        this((FrameConstant.FRAME_WIDTH-ImageMap.get("my01").getWidth(null))/2,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("my01").getHeight(null), ImageMap.get("my01"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        fire();
        if (fire) {
            index++;
            if (index>=10) {
                index = 0;
            }
        }
    }

    private int index = 0;

    /**
     * 开火方法，
     * 判断开关是否打开
     * 创建一个子弹对象放入gameFrame里的子弹集合中
     */
    public void fire() {
        if (fire && index ==0 ) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                    getX()+(image.getWidth(null)/2)-(ImageMap.get("myb01").getWidth(null)/2),
                    getY() - ImageMap.get("myb01").getHeight(null),
                    ImageMap.get("myb01")

            ));
        }
    }

    //飞机移动的方法
    @Override
    public void move() {
        if (up) {
            setY(getY()-speed);
        }
        if (right) {
            setX(getX()+speed);
        }
        if (down) {
            setY(getY()+speed);
        }
        if (left) {
            setX(getX()-speed);
        }

        borderTesting();
    }

    //关于边缘碰撞的测试
    public void borderTesting() {
        if (getX()<0) {
            setX(0);
        }
        if (getX()>FrameConstant.FRAME_WIDTH-ImageMap.get("my01").getWidth(null)) {
            setX(FrameConstant.FRAME_WIDTH-ImageMap.get("my01").getWidth(null));
        }
        if (getY()<30) {
            setY(30);
        }
        if (getY()>FrameConstant.FRAME_HEIGHT-ImageMap.get("my01").getHeight(null)) {
            setY(FrameConstant.FRAME_HEIGHT-ImageMap.get("my01").getHeight(null));
        }

    }

    //按键表示飞机移动
    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
    }

    public void keyReleased (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = false;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
