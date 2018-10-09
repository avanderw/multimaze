package net.avdw.maze.analyser;

import net.avdw.maze.model.IMaze;
import net.avdw.maze.model.WallState;
import org.apache.commons.math3.stat.Frequency;

import javax.inject.Inject;

public class CrossroadAnalyser implements IMazeAnalyser {
    private IMaze maze;

    @Inject
    CrossroadAnalyser(IMaze maze) {
        this.maze = maze;
    }

    @Override
    public Frequency analyse() {
        Frequency frequency = new Frequency();
        maze.cells().stream()
                .filter(cell -> cell.wallStateMap.values().stream()
                        .filter(wallState -> wallState == WallState.OPEN)
                        .count() == 4
                )
                .forEach(cell -> frequency.addValue(CellType.CROSSROAD));
        return frequency;
    }
}
