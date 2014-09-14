package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/13/14.
 */
public class Player extends LiveEntity implements CanOverlap {
    private String key;

    public Player(Room room, Point point) {
        super(room, point);
    }

    @Override
    public char toChar() {
        return '#';
    }

    @Override
    public void act() {
        switch(key.charAt(0)) {
            case 'w':
                moveUp();
                break;
            case 'a':
                moveLeft();
                break;
            case 's':
                moveDown();
                break;
            case 'd':
                moveRight();
                break;
        }
    }

    public void setAction(String key) {
        this.key = key;
    }

    /**
     * When overlapped by Zombie, game over.
     */
    @Override
    public void onOverlap() {
        room.getGameManager().notifyGameOver();
    }
}
