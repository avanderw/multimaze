package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import net.avdw.maze.generator.IMazeGenerator;
import net.avdw.maze.generator.RecursiveBacktrackerGenerator;
import net.avdw.maze.model.GridMaze;
import net.avdw.maze.model.IMaze;
import net.avdw.multimaze.client.desktop.maze.MazeModule;

public class DesktopClientModule extends AbstractModule {
    @Override
    public void configure() {
        install(new MazeModule());


        bind(ApplicationListener.class).to(DesktopClientApplicationListener.class);
        bind(IMazeGenerator.class).to(RecursiveBacktrackerGenerator.class);
        bind(IMaze.class).to(GridMaze.class);
    }

    @Provides
    LwjglApplicationConfiguration lwjglApplicationConfiguration() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "multimaze-desktop-client";
        config.useGL30 = Boolean.FALSE;
        config.width = 640;
        config.height = 480;
        return config;
    }
}
