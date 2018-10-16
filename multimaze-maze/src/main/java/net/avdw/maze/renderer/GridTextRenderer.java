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
    private Map<Integer, String[]> tileMap = new HashMap();

    @Inject
    public GridTextRenderer(IMaze maze) {
        this.maze = maze;

        tileMap.put(0b0000, new String[]{"┏┓", "┗┛"});

        tileMap.put(0b1000, new String[]{"┃┃", "┗┛"});
        tileMap.put(0b0100, new String[]{"┏┓", "┃┃"});
        tileMap.put(0b0010, new String[]{"┏━", "┗━"});
        tileMap.put(0b0001, new String[]{"━┓", "━┛"});

        tileMap.put(0b1100, new String[]{"┃┃", "┃┃"});
        tileMap.put(0b1001, new String[]{"┛┃", "━┛"});
        tileMap.put(0b1010, new String[]{"┃┗", "┗━"});
        tileMap.put(0b0101, new String[]{"━┓", "┓┃"});
        tileMap.put(0b0110, new String[]{"┏━", "┃┏"});
        tileMap.put(0b0011, new String[]{"━━", "━━"});

        tileMap.put(0b1110, new String[]{"┃┗", "┃┏"});
        tileMap.put(0b1101, new String[]{"┛┃", "┓┃"});
        tileMap.put(0b1011, new String[]{"┛┗", "━━"});
        tileMap.put(0b0111, new String[]{"━━", "┓┏"});

        tileMap.put(0b1111, new String[]{"┛┗", "┓┏"});
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
