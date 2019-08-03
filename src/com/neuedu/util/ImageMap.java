package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {

        map.put("s01",ImageMap.get("com\\neuedu\\imgs\\menu\\s_1.png"));

        map.put("bg01",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.png"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_1.0.png"));

        map.put("myb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_4.png"));
        map.put("myb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_1.png"));

        map.put("ep01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_3.png"));
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));

        map.put("epb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_1.png"));

        map.put("bl01",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\blood.png"));



        map.put("boss01",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_01.png"));
        map.put("boss02",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_02.png"));
        map.put("boss03",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_03.png"));
        map.put("boss04",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_04.png"));
        map.put("boss05",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_05.png"));
        map.put("boss06",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_06.png"));
        map.put("boss07",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_07.png"));
        map.put("boss08",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_08.png"));
        map.put("boss09",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_09.png"));

        map.put("bob1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\BossBullet.png"));

        map.put("o1",ImageUtil.getImage("com\\neuedu\\imgs\\over\\0.png"));
        map.put("o2",ImageUtil.getImage("com\\neuedu\\imgs\\over\\120.png"));
        map.put("o3",ImageUtil.getImage("com\\neuedu\\imgs\\over\\210.png"));
        map.put("o4",ImageUtil.getImage("com\\neuedu\\imgs\\over\\300.png"));
        map.put("o5",ImageUtil.getImage("com\\neuedu\\imgs\\over\\420.png"));
        map.put("o6",ImageUtil.getImage("com\\neuedu\\imgs\\over\\510.png"));
        map.put("o7",ImageUtil.getImage("com\\neuedu\\imgs\\over\\600.png"));
        map.put("o8",ImageUtil.getImage("com\\neuedu\\imgs\\over\\720.png"));
        map.put("o9",ImageUtil.getImage("com\\neuedu\\imgs\\over\\810.png"));



    }

    public static Image get(String key) {
        return map.get(key);
    }
}
