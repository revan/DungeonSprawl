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

    public static Point chooseSpot(Room room) {
        Point point;
        do {
            int x = (int)(Math.random()*room.getWidth());
            int y = (int)(Math.random()*room.getHeight());
            point = new Point(x, y);
        } while(!room.pointInBounds(point) || room.getCell(point).getContents() != null);

        return  point;
    }

    /**
     * @return a character to print in the grid display
     */
    public abstract char toChar();

    public String getName(){
        return this.getClass().getName();
    }
}
