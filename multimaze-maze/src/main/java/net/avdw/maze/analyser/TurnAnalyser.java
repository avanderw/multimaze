package net.avdw.maze.analyser;

import net.avdw.maze.model.Direction;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.model.WallState;
import org.apache.commons.math3.stat.Frequency;

import javax.inject.Inject;

public class TurnAnalyser implements IMazeAnalyser {
    private IMaze maze;

    @Inject
    TurnAnalyser(IMaze maze) {
        this.maze = maze;
    }

    @Override
    public Frequency analyse() {
        Frequency frequency = new Frequency();
        maze.cells().stream()
                .filter(cell -> cell.wallStateMap.values().stream()
                        .filter(wallState -> wallState == WallState.OPEN)
                        .count() == 2
                )
                .filter(cell -> cell.wallStateMap.get(Direction.NORTH) == WallState.OPEN
                        && cell.wallStateMap.get(Direction.SOUTH) == WallState.CLOSED

                        || cell.wallStateMap.get(Direction.SOUTH) == WallState.OPEN
                        && cell.wallStateMap.get(Direction.NORTH) == WallState.CLOSED

                        || cell.wallStateMap.get(Direction.EAST) == WallState.OPEN
                        && cell.wallStateMap.get(Direction.WEST) == WallState.CLOSED

                        || cell.wallStateMap.get(Direction.WEST) == WallState.OPEN
                        && cell.wallStateMap.get(Direction.EAST) == WallState.CLOSED
                )
                .forEach(cell -> frequency.addValue(CellType.TURN));
        return frequency;
    }
}
