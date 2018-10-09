package net.avdw.maze;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.avdw.code.analyser.SpeedAnalyser;
import net.avdw.maze.analyser.CrossroadAnalyser;
import net.avdw.maze.analyser.IMazeAnalyser;
import net.avdw.maze.generator.IMazeGenerator;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.module.RecursiveBacktrackerModule;
import net.avdw.maze.renderer.IMazeRenderer;
import org.apache.commons.math3.stat.Frequency;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Logger;

import java.util.Set;
import java.util.stream.IntStream;

public class MazeRunner {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: \n{message}")
                .activate();

        Injector injector = Guice.createInjector(new RecursiveBacktrackerModule());
        injector.getInstance(IMazeGenerator.class).generate();
        injector.getInstance(IMazeRenderer.class).render();

        SpeedAnalyser speedAnalyser = new SpeedAnalyser(500, ()->injector.getInstance(IMazeGenerator.class).generate());
        Logger.info(speedAnalyser.analyse());

        Frequency frequency = new Frequency();
        IntStream.range(0, 500).forEach(num-> {
            injector.getInstance(IMazeGenerator.class).generate();
            frequency.merge(injector.getInstance(IMazeAnalyser.class).analyse());
        });
        Logger.info(frequency);
    }
}
