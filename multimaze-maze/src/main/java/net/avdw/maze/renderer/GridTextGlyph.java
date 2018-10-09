package net.avdw.maze.renderer;

public enum GridTextGlyph {
    EW("━"), NS("┃"), SE("┏"), SW("┓"), NE("┗"), NW("┛");

    private String tile;

    GridTextGlyph(String tile) {

        this.tile = tile;
    }
}
