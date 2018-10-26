package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.avdw.maze.generator.IMazeGenerator;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;

public class MultimazeMain {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class_name}.{method}:{line} [{level}] {message}")
                .level(Level.TRACE)
                .activate();

        Injector injector = Guice.createInjector(new MultimazeConfig());
        injector.getInstance(IMazeGenerator.class).generate();
        new LwjglApplication(injector.getInstance(ApplicationListener.class), injector.getInstance(LwjglApplicationConfiguration.class));


        //https://github.com/libgdx/libgdx/wiki/Managing-your-assets

    }
}
