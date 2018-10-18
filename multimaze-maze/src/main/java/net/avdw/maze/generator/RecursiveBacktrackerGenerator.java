package net.avdw.maze.generator;

import net.avdw.maze.model.MazeCell;
import net.avdw.maze.model.Direction;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.model.WallState;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RecursiveBacktrackerGenerator implements IMazeGenerator {
    private IMaze maze;

    @Inject
    RecursiveBacktrackerGenerator(IMaze maze) {
        this.maze = maze;
    }

    @Override
    public void generate() {
        maze.build();
        Integer startRow = ThreadLocalRandom.current().nextInt(0, maze.rowCount());
        Integer startCol = ThreadLocalRandom.current().nextInt(0, maze.colCount());
        Optional<MazeCell> startCell = maze.cells().stream()
                .filter(cell -> cell.row == startRow)
                .filter(cell -> cell.col == startCol)
                .findAny();

        startCell.ifPresent(cell -> visit(cell));
    }

    private void visit(MazeCell mazeCell) {
        mazeCell.visited = Boolean.TRUE;
        List<Map.Entry<Direction, MazeCell>> neighboursToVisit = mazeCell.neighbourCellMap.entrySet().stream()
                .filter(directionCellEntry -> directionCellEntry.getValue().visited == Boolean.FALSE)
                .collect(Collectors.toList());
        Collections.shuffle(neighboursToVisit);

        neighboursToVisit.stream().forEach(entry -> {
                    if (!entry.getValue().visited) {
                        mazeCell.wallStateMap.put(entry.getKey(), WallState.OPEN);
                        entry.getValue().wallStateMap.put(entry.getKey().opposite(), WallState.OPEN);
                        visit(entry.getValue());
                    }
                }
        );
    }
}
