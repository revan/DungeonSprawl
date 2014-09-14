package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/14/14.
 */
public class Rock extends Entity{
    public Rock(Room room, Point point) {
        super(room, point);
    }

    @Override
    public char toChar() {
        return 'X';
    }
}
