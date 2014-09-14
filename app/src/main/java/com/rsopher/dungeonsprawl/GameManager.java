package com.rsopher.dungeonsprawl;

import com.koushikdutta.async.http.WebSocket;

/**
 * Created by revan on 9/13/14.
 */
public class GameManager {
    public Room room;
    public WebSocket webSocket;
    private GameActivity activity;

    public GameManager(WebSocket webSocket, GameActivity activity) {
        this.room = generateRoom(20, 10);
        this.webSocket = webSocket;
        this.activity = activity;
    }

    public Room generateRoom(int w, int h) {
        //TODO save old rooms to stack
        return new Room(w, h, this);
    }

    public void turn() {
        activity.unDimScreen();
        room.turn();
    }

    public void setPlayerAction(String key) {
        room.player.setAction(key);
    }

    public void notifyRoomChange() {
        //TODO use actual score
        webSocket.send("" + 69);
        activity.dimScreen();
        this.room = generateRoom(20, 10);
    }
}
