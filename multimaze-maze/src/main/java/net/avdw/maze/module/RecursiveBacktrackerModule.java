package net.avdw.maze.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.avdw.maze.analyser.AnalyserModule;
import net.avdw.maze.generator.IMazeGenerator;
import net.avdw.maze.generator.RecursiveBacktrackerGenerator;
import net.avdw.maze.model.GridMaze;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.renderer.GridTextRenderer;
import net.avdw.maze.renderer.IMazeRenderer;

public class RecursiveBacktrackerModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new AnalyserModule());
        bind(Integer.class).annotatedWith(Names.named("maze-row-count")).toInstance(9);
        bind(Integer.class).annotatedWith(Names.named("maze-col-count")).toInstance(9);
        bind(IMaze.class).to(GridMaze.class);
        bind(IMazeGenerator.class).to(RecursiveBacktrackerGenerator.class);
        bind(IMazeRenderer.class).to(GridTextRenderer.class);
    }
}
