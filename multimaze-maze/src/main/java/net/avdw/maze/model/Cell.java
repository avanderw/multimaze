package net.avdw.maze.model;

import org.pmw.tinylog.Logger;

import java.util.*;

public class Cell {
    public final Integer row;
    public final Integer col;
    private final Map<Direction, WallState> wallStateMap = new HashMap();
    private final Map<Direction, Cell> neighbourCellMap = new HashMap();

    Cell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
        Arrays.stream(Direction.values()).forEach(direction -> wallStateMap.put(direction, WallState.CLOSED));
    }

    void linkNeighbour(Direction direction, Cell cell) {
        if (neighbourCellMap.containsKey(direction)) {
            return;
        }

        neighbourCellMap.put(direction, cell);
        cell.linkNeighbour(direction.opposite(), this);
    }

    public String key() {
        StringBuilder key = new StringBuilder();
        wallStateMap.computeIfPresent(Direction.NORTH, (direction, wallState) -> {
            key.append(wallState == WallState.OPEN ? Direction.NORTH : "");
            return wallState;
        });
        return key.toString();
    }

    @Override
    public String toString() {
        return String.format("{row=%s, col=%s, walls=%s, neighbours=%s}", row, col, wallStateMap, neighbourCellMap.keySet());
    }

}
