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

    public Room(int width, int height) {
        entities = new LinkedList<Entity>();

        cells = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cells[w][h] = new Cell();
            }
        }

        Point playerStart = new Point(width/2, height/2);
        player = new Player(this, playerStart);
        addEntity(player, playerStart);

        //TODO: initialize entities
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
