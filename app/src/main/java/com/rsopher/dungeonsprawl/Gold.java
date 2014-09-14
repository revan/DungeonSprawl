package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/14/14.
 */
public class Gold extends Entity implements CanOverlap{
    private static final int MAX_GOLD_VALUE = 100;

    private int goldVal;

    public Gold(Room room, Point point) {
        super(room, point);

        goldVal = (int)(Math.random() * MAX_GOLD_VALUE);
    }

    @Override
    public void onOverlap() {
        room.getGameManager().addGold(goldVal);
    }

    @Override
    public char toChar() {
        return '$';
    }
}
