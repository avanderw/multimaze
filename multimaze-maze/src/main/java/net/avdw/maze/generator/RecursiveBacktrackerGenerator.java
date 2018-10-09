package net.avdw.maze.generator;

import net.avdw.maze.model.IMaze;

public class RecursiveBacktrackerGenerator implements IMazeGenerator {
    private IMaze maze;

    RecursiveBacktrackerGenerator(IMaze maze) {
        this.maze = maze;
    }

    @Override
    public void generate() {
        
    }
}
