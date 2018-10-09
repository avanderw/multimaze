package net.avdw.maze.analyser;

import net.avdw.maze.model.IMaze;
import net.avdw.maze.model.WallState;
import org.apache.commons.math3.stat.Frequency;

import javax.inject.Inject;

public class DeadEndAnalyser implements IMazeAnalyser {
    private IMaze maze;

    @Inject
    DeadEndAnalyser(IMaze maze) {
        this.maze = maze;
    }

    @Override
    public Frequency analyse() {
        Frequency frequency = new Frequency();
        maze.cells().stream()
                .filter(cell -> cell.wallStateMap.values().stream()
                        .filter(wallState -> wallState == WallState.OPEN)
                        .count() == 1
                )
                .forEach(cell -> frequency.addValue(CellType.DEAD_END));
        return frequency;
    }
}
