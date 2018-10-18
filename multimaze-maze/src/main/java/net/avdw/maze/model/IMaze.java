package net.avdw.maze.model;

import java.util.List;

public interface IMaze {
    void build();
    List<MazeCell> cells();
    Integer rowCount();
    Integer colCount();
}
