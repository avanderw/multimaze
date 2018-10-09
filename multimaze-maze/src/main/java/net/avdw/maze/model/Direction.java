package net.avdw.maze.model;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    Direction opposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                throw new UnsupportedOperationException("unhandled direction");
        }
    }
}
