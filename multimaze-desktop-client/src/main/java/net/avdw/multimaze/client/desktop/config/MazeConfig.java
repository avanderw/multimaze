package net.avdw.multimaze.client.desktop.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.avdw.maze.generator.RecursiveBacktrackerGenerator;
import net.avdw.maze.model.GridMaze;
import net.avdw.maze.model.IMaze;
import net.avdw.multimaze.client.desktop.maze.MazeGenerator;

public class MazeConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("defaultRowCount")).toInstance(9);
        bind(Integer.class).annotatedWith(Names.named("defaultColCount")).toInstance(9);

    }
}
