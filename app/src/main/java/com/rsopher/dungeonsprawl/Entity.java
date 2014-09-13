package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/13/14.
 */
public abstract class Entity {
    protected Room room;
    public Point point;

    public Entity(Room room, Point point){
        this.room = room;
        this.point = point;
    }

    public void act(){
        return;
    }

    /**
     * @return a character to print in the grid display
     */
    public abstract char toChar();

    public String getName(){
        return this.getClass().getName();
    }
}
