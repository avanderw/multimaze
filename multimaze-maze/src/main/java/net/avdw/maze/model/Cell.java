package net.avdw.maze.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cell {
    public final Integer row;
    public final Integer col;
    public final Map<Direction, WallState> wallStateMap = new HashMap();
    public final Map<Direction, Cell> neighbourCellMap = new HashMap();
    public Boolean visited = Boolean.FALSE;

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
        wallStateMap.computeIfPresent(Direction.SOUTH, (direction, wallState) -> {
            key.append(wallState == WallState.OPEN ? Direction.SOUTH : "");
            return wallState;
        });
        wallStateMap.computeIfPresent(Direction.EAST, (direction, wallState) -> {
            key.append(wallState == WallState.OPEN ? Direction.EAST : "");
            return wallState;
        });
        wallStateMap.computeIfPresent(Direction.WEST, (direction, wallState) -> {
            key.append(wallState == WallState.OPEN ? Direction.WEST : "");
            return wallState;
        });
        return key.toString();
    }

    @Override
    public String toString() {
        return String.format("{row=%s, col=%s, key=%s, walls=%s, neighbours=%s}", row, col, key(), wallStateMap, neighbourCellMap.keySet());
    }

}
