package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;


import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameFrame extends Frame {

    //创建背景对象
    private Background background = new Background();

    //创建飞机就对象
    private Plane plane = new Plane();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //boss子弹集合
    public final List<BossBullet> bossBulletList = new CopyOnWriteArrayList<>();
    //小红心
    public final List<Prop> propList = new CopyOnWriteArrayList<>();

    public Boss boss = new Boss(0,0);

    public Over over = new Over(150,300);



    public boolean gameOver = false;

    public int score = 0;

    public int hp = 100;

    private Random random = new Random();


    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            background.draw(g);
            plane.draw(g);
            plane.collisionTesting(propList);

            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }

            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);
                bullet.collisionTesting2(boss);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTesting(plane);
            }

            if (score>=100){
                boss.draw(g);
            }


            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.draw(g);
            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.collisionTesting(plane);
            }

            boss.collisionTesting(plane);

            for (Prop prop : propList) {
                prop.draw(g);
            }






            g.setFont(new Font("楷体", Font.BOLD, 25));
            g.setColor(new Color(80, 255, 138));
            g.drawString("得分：" + score, 100, 100);

            g.setColor(Color.WHITE);
            g.drawRect(100, 150, 100, 20);
            g.setColor(Color.RED);
            g.fillRect(100, 150, hp, 20);


//            if (gameOver==true) {
//                g.setColor(Color.CYAN);
//                g.setFont(new Font("楷体", Font.BOLD, 40));
//                g.drawString("游戏结束",300,400);
//            }

        }

        if (gameOver){
            over.draw(g);
        }

    }


    /**
     * 使用这个方法初始化窗口
     */
    public void init() {
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);

        enableInputMethods(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });





        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        //添加一些敌方飞机


        for (int i = 0; i < 1000; i++) {
            double x = Math.random()*800;
            double y =Math.random()*1500;
            if (i%70==0){
                enemyPlaneList.add(new EnemyPlane((int)x,-(int)y,1));
                enemyPlaneList.add(new EnemyPlane((int)x,-(int)y,2));

            }

        }


        //enemyPlaneList.add(new EnemyPlane(300, 0, 1));


        //添加一些血包
        for (int i = 0; i < 1000; i++) {
            double x = Math.random()*800;
            double y =Math.random()*2000;
            if (i%70==0){
                propList.add(new Prop((int)x,-(int)y,ImageMap.get("bl01")));

            }

        }



        setVisible(true);


    }

    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
