package net.avdw.maze.model;

import java.util.List;

public interface IMaze {
    void build();
    List<Cell> cells();
    Integer rowCount();
    Integer colCount();
}
