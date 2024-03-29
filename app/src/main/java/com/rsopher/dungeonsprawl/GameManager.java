package com.rsopher.dungeonsprawl;

import com.koushikdutta.async.http.WebSocket;

/**
 * Created by revan on 9/13/14.
 */
public class GameManager {
    public Room room;
    public WebSocket webSocket;
    private GameActivity activity;
    private int gold;

    public GameManager(WebSocket webSocket, GameActivity activity) {
        this.activity = activity;
        this.room = generateRoom();
        this.webSocket = webSocket;
        gold = 1;
        activity.hideScreen("Connected to host!");
    }

    public Room generateRoom() {
        int size = activity.calculateRoomSize();
        //TODO save old rooms to stack
        return new Room(size, size, this);
    }

    public void turn() {
        activity.unHideScreen();
        room.turn();
    }

    public void setPlayerAction(String key) {
        room.player.setAction(key);
    }

    /**
     * If this is the first message of a room, it will be in the form
     * of "goldXX..." where "XX..." is the gold total. Else, it's WASD.
     * TODO: replace this with less disgusting way (JSON)
     */
    public boolean maybeSetGold(String string) {
        if(string.startsWith("gold")) {
            gold = Integer.parseInt(string.replace("gold", ""));
            return true;
        }
        return false;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getGold() {
        return gold;
    }

    public void notifyRoomChange() {
        webSocket.send("" + gold);
        activity.hideScreen("You made it through the room.");
        this.room = generateRoom();
    }

    public void notifyGameOver() {
        webSocket.send("" + -1 * gold);
        activity.hideScreen("You Died!\nYou made " + getGold() + " gold.");
    }
}
