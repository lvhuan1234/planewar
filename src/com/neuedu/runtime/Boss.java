package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();

    private Random random = new Random();

    private int speed = FrameConstant.GAME_SPEED*7;

//    public Boss() {
//        /*for (int i = 0; i < 10; i++) {
//            imageList.add(ImageMap.get("boss0"+i));
//        }*/
//
//    }


    public Boss() {

    }

    public Boss(int x, int y) {
        super(x, y);
        imageList.add(ImageMap.get("boss01"));
        imageList.add(ImageMap.get("boss02"));
        imageList.add(ImageMap.get("boss03"));
        imageList.add(ImageMap.get("boss04"));
        imageList.add(ImageMap.get("boss05"));
        imageList.add(ImageMap.get("boss06"));
        imageList.add(ImageMap.get("boss07"));
        imageList.add(ImageMap.get("boss08"));
        imageList.add(ImageMap.get("boss09"));
    }

    int index = 0;


    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(imageList.get(index++), getX(), getY(), imageList.get(0).getWidth(null)/2,
                imageList.get(0).getHeight(null)/2, null);
        if (index >= 9) {
            index = 0;
        }
        fire();

    }

    private  boolean right = true;

    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
            setY(getY()+speed);
        } else {
            setX(getX()-speed);
            setY(getY()-speed);
        }

        borderTesting();

    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 950) {
            gameFrame.bossBulletList.add(new BossBullet(
                    getX() + (imageList.get(0).getWidth(null) /4) - (ImageMap.get("bob1").getWidth(null) / 2),
                    getY() + ImageMap.get("bob1").getHeight(null)/2,
                    ImageMap.get("bob1")
            ));
        }
    }

    public void borderTesting () {

            if (getX()+ imageList.get(0).getWidth(null)/2 >= FrameConstant.FRAME_WIDTH) {
                right = false;
            } else if (getX()< 0) {
                right = true;
            }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(),
                imageList.get(0).getWidth(null)/2-15, imageList.get(0).getHeight(null)/2-15);
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {

            gameFrame.gameOver = true;
        }
    }


}

