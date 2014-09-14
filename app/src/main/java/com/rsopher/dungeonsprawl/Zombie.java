package com.rsopher.dungeonsprawl;

import android.graphics.Point;

/**
 * Created by revan on 9/14/14.
 */
public class Zombie extends LiveEntity {
    private static final int MINIMUM_PLAYER_DISTANCE = 5;
    private static final int SIGHT_DISTANCE = 5;

    public Zombie(Room room, Point point) {
        super(room, point);
    }

    public static Point chooseSpot(Room room) {
        Point point;
        do {
            point = LiveEntity.chooseSpot(room);
        } while(isWithinDistanceToPlayer(point, room.player, MINIMUM_PLAYER_DISTANCE));

        return point;
    }

    /**
     * Zombie moves toward player, but only if within SIGHT_DISTANCE.
     */
    @Override
    public void act() {
        if (isWithinDistanceToPlayer(point, room.player, SIGHT_DISTANCE)) {
            Point playerPosition = room.player.point;
            int xdiff = playerPosition.x - point.x;
            int ydiff = playerPosition.y - point.y;

            if (Math.abs(xdiff) > Math.abs(ydiff)) {
                if (xdiff > 0) {
                    moveDown();
                } else {
                    moveUp();
                }
            } else {
                if (ydiff > 0) {
                    moveRight();
                } else {
                    moveLeft();
                }
            }
        }
    }

    @Override
    public char toChar() {
        return 'Z';
    }

    public static boolean isWithinDistanceToPlayer(Point point, Player player, int distance) {
        return Math.abs(point.x - player.point.x) < distance
                && Math.abs(point.y - player.point.y) < distance;
    }
}
