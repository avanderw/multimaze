package net.avdw.maze.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.avdw.maze.generator.IMazeGenerator;
import net.avdw.maze.generator.RecursiveBacktrackerGenerator;
import net.avdw.maze.model.GridMaze;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.renderer.GridTextRenderer;
import net.avdw.maze.renderer.IMazeRenderer;

public class RecursiveBacktrackerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("defaultRowCount")).toInstance(15);
        bind(Integer.class).annotatedWith(Names.named("defaultColCount")).toInstance(37);
        bind(IMaze.class).to(GridMaze.class);
        bind(IMazeGenerator.class).to(RecursiveBacktrackerGenerator.class);
        bind(IMazeRenderer.class).to(GridTextRenderer.class);
    }
}
