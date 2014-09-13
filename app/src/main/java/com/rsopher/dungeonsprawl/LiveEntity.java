package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/13/14.
 */
public abstract class LiveEntity extends Entity {
    public LiveEntity(Room room, Point point){
        super(room, point);
    }

    public boolean canMoveToPoint(Point point) {
        //TODO pathfinding?
        Cell cell = room.getCell(point);
        return cell != null && cell.getContents() == null;
    }

    public void moveToPoint(Point point) {
        if (canMoveToPoint(point)) {
            room.moveEntity(this, point);
        }
    }

    public void moveLeft() {
        moveToPoint(new Point(point.x, point.y - 1));
    }
    public void moveRight() {
        moveToPoint(new Point(point.x, point.y + 1));
    }
    public void moveUp() {
        moveToPoint(new Point(point.x - 1, point.y));
    }
    public void moveDown() {
        moveToPoint(new Point(point.x + 1, point.y));
    }
}
