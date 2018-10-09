package net.avdw.maze.renderer;

import com.google.inject.Inject;
import net.avdw.maze.model.Cell;
import net.avdw.maze.model.Direction;
import net.avdw.maze.model.IMaze;
import org.pmw.tinylog.Logger;

import java.util.*;
import java.util.stream.IntStream;

public class GridTextRenderer implements IMazeRenderer {
    private IMaze maze;
    private Map<String, String[]> tileMap = new HashMap();

    @Inject
    public GridTextRenderer(IMaze maze) {
        this.maze = maze;

        tileMap.put("", new String[]{"┏┓", "┗┛"});

        tileMap.put(Direction.NORTH.name(), new String[]{"┃┃", "┗┛"});
        tileMap.put(Direction.SOUTH.name(), new String[]{"┏┓", "┃┃"});
        tileMap.put(Direction.EAST.name(), new String[]{"┏━", "┗━"});
        tileMap.put(Direction.WEST.name(), new String[]{"━┓", "━┛"});

        tileMap.put(Direction.NORTH.name() + Direction.SOUTH, new String[]{"┃┃", "┃┃"});
        tileMap.put(Direction.NORTH.name() + Direction.WEST, new String[]{"┛┃", "━┛"});
        tileMap.put(Direction.NORTH.name() + Direction.EAST, new String[]{"┃┗", "┗━"});
        tileMap.put(Direction.SOUTH.name() + Direction.WEST, new String[]{"━┓", "┓┃"});
        tileMap.put(Direction.SOUTH.name() + Direction.EAST, new String[]{"┏━", "┃┏"});
        tileMap.put(Direction.EAST.name() + Direction.WEST, new String[]{"━━", "━━"});

        tileMap.put(Direction.NORTH.name() + Direction.SOUTH + Direction.EAST, new String[]{"┃┗", "┃┏"});
        tileMap.put(Direction.NORTH.name() + Direction.SOUTH + Direction.WEST, new String[]{"┛┃", "┓┃"});
        tileMap.put(Direction.NORTH.name() + Direction.EAST + Direction.WEST, new String[]{"┛┗", "━━"});
        tileMap.put(Direction.SOUTH.name() + Direction.EAST + Direction.WEST, new String[]{"━━", "┓┏"});

        tileMap.put(Direction.NORTH.name() + Direction.SOUTH + Direction.EAST + Direction.WEST, new String[]{"┛┗", "┓┏"});
    }

    @Override
    public void render() {
        StringBuilder render = new StringBuilder();
        IntStream.range(0, maze.rowCount()).forEach(row->{
            IntStream.range(0, 2).forEach(tile->{
                IntStream.range(0, maze.colCount()).forEach(col-> {
                    Optional<Cell> currCell = maze.cells().stream()
                            .filter(cell -> cell.row == row)
                            .filter(cell -> cell.col == col)
                            .findAny();
                    currCell.ifPresent(cell -> render.append(tileMap.get(cell.key())[tile]));
                });
                render.append("\n");
            });
        });
        Logger.info(render);
    }
}
