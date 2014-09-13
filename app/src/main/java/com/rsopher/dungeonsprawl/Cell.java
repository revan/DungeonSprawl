package com.rsopher.dungeonsprawl;

/**
 * Created by revan on 9/13/14.
 */
public class Cell {
    private Entity contents;

    public Cell(Entity contents) {
        this.contents = contents;
    }
    public Cell() {
        this.contents = null;
    }

    public Entity getContents() {
        return contents;
    }

    public void setContents(Entity contents) {
        this.contents = contents;
    }

    public char toChar() {
        return contents == null ? '.' : contents.toChar();
    }
}
