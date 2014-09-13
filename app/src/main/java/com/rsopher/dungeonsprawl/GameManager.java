package com.rsopher.dungeonsprawl;

/**
 * Created by revan on 9/13/14.
 */
public class GameManager {
    public Room room;

    public GameManager() {
        room = generateRoom(10, 10);
    }

    public Room generateRoom(int w, int h) {
        return new Room(w, h);
    }

    public void turn() {
        room.turn();
    }

    public void setPlayerAction(String key) {
        room.player.setAction(key);
    }
}
