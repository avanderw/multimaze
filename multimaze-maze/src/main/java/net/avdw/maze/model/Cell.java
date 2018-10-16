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

    public Integer key() {
        final Integer[] key = {0b0000};
        wallStateMap.computeIfPresent(Direction.NORTH, (direction, wallState) -> {
            if (wallState.equals(WallState.OPEN))
                key[0] = key[0] | 0b1000;
            return wallState;
        });
        wallStateMap.computeIfPresent(Direction.SOUTH, (direction, wallState) -> {
            if (wallState.equals(WallState.OPEN))
                key[0] = key[0] | 0b0100;
            return wallState;
        });
        wallStateMap.computeIfPresent(Direction.EAST, (direction, wallState) -> {
            if (wallState.equals(WallState.OPEN))
                key[0] = key[0] | 0b0010;
            return wallState;
        });
        wallStateMap.computeIfPresent(Direction.WEST, (direction, wallState) -> {
            if (wallState.equals(WallState.OPEN))
                key[0] = key[0] | 0b0001;
            return wallState;
        });
        return key[0];
    }

    @Override
    public String toString() {
        return String.format("{row=%s, col=%s, key=%s, walls=%s, neighbours=%s}", row, col, key(), wallStateMap, neighbourCellMap.keySet());
    }

}
