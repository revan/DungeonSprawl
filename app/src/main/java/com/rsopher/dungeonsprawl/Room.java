package com.rsopher.dungeonsprawl;

import android.graphics.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a room with its cells.
 * Created by revan on 9/13/14.
 */
public class Room {
    public Player player;
    private Cell[][] cells;
    private List<Entity> entities;
    private GameManager game;
    private int width;
    private int height;

    private static int ROCK_MAX = 100;
    private static int ZOMBIE_MAX = 10;
    private static int GOLD_MAX = 5;

    public Room(int width, int height, GameManager game) {
        this.width = width;
        this.height = height;

        entities = new LinkedList<Entity>();

        cells = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cells[w][h] = new Cell();
            }
        }

        this.game = game;

        Point playerStart = Player.chooseSpot(this);
        player = new Player(this, playerStart);
        addEntity(player, playerStart);

        Point doorStart = Door.chooseSpot(this);
        addEntity(new Door(this, doorStart), doorStart);

        for(int i=0; i < Math.random() * ROCK_MAX; i++) {
            Point rockPoint = Rock.chooseSpot(this);
            addEntity(new Rock(this, rockPoint), rockPoint);
        }

        for(int i=0; i < Math.random() * ZOMBIE_MAX; i++) {
            Point zombiePoint = Zombie.chooseSpot(this);
            addEntity(new Zombie(this, zombiePoint), zombiePoint);
        }

        for(int i=0; i < Math.random() * GOLD_MAX; i++) {
            Point goldPoint = Gold.chooseSpot(this);
            addEntity(new Gold(this, goldPoint), goldPoint);
        }
    }

    public void addEntity(Entity entity, Point point) {
        cells[point.x][point.y].setContents(entity);
        entities.add(entity);
    }
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void turn() {
        for (Entity entity : entities) {
            entity.act();
        }
    }

    public void moveEntity(Entity entity, Point point) {
        cells[entity.point.x][entity.point.y].setContents(null);
        cells[point.x][point.y].setContents(entity);
        entity.point = point;
    }

    public boolean pointInBounds(Point point) {
        return point.x >= 0 && point.x < cells.length
                && point.y >= 0 && point.y < cells[0].length;
    }

    public Cell getCell(Point point) {
        if (pointInBounds(point)) {
            return cells[point.x][point.y];
        }
        return null;
    }

    public GameManager getGameManager() {
        return game;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int w = 0; w < cells.length; w++) {
            for (int h = 0; h < cells[w].length; h++) {
                sb.append(cells[w][h].toChar());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
