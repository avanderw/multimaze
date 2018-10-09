package net.avdw.maze;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.avdw.maze.generator.IMazeGenerator;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.module.RecursiveBacktrackerModule;
import net.avdw.maze.renderer.IMazeRenderer;
import org.pmw.tinylog.Configurator;

public class MazeRunner {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: \n{message}")
                .activate();

        Injector injector = Guice.createInjector(new RecursiveBacktrackerModule());
        injector.getInstance(IMazeGenerator.class).generate();
        injector.getInstance(IMazeRenderer.class).render();

    }
}
