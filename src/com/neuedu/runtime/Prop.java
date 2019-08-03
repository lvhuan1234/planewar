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

public class Prop extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED*2;

    public Prop() {
        this(0,0, ImageMap.get("bl01"));
    }

    public Prop(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

    }

    private  boolean right =true;

    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
            setY(getY()+speed);
        } else {
            setX(getX()-speed);

        }

        borderTesting();
    }

    //边缘碰撞
    public void borderTesting() {
        if (getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            right = false;
        } else if (getX() < 0) {
            right = true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
