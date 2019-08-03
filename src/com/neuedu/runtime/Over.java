package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Over extends BaseSprite implements Drawable {

    private List<Image> imageList = new ArrayList<>();



//    public Over() {
//        for (int i = 0; i < 10; i++) {
//            imageList.add(ImageMap.get("o"+i));
//        }
//    }

    public Over(int x, int y) {
        super(x, y);
        imageList.add(ImageMap.get("01"));
        imageList.add(ImageMap.get("o2"));
        imageList.add(ImageMap.get("o3"));
        imageList.add(ImageMap.get("o4"));
        imageList.add(ImageMap.get("o5"));
        imageList.add(ImageMap.get("o6"));
        imageList.add(ImageMap.get("o7"));
        imageList.add(ImageMap.get("o8"));
        imageList.add(ImageMap.get("o9"));

    }

//    public Over(int x, int y, List<Image> imageList) {
//        super(x, y);
//        this.imageList = imageList;
//
//    }

    int index = 0;

    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++),getX(),getY(),imageList.get(index++).getWidth(null),
                imageList.get(index++).getHeight(null),null);
        if (index>=9) {
            index=0;
        }
    }
}
