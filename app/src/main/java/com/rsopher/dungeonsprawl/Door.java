package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/13/14.
 */
public class Door extends Entity implements CanOverlap{
    public Door(Room room, Point point){
        super(room, point);
        occupiesSpace = true;
    }

    @Override
    public void onOverlap() {
        room.getGameManager().notifyRoomChange();
    }

    @Override
    public char toChar() {
        return '>';
    }
}
