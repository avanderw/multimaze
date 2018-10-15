package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.avdw.maze.model.GridMaze;
import net.avdw.multimaze.client.desktop.config.AssetConfig;
import net.avdw.multimaze.client.desktop.config.LwjglConfig;
import net.avdw.multimaze.client.desktop.config.MazeConfig;
import net.avdw.multimaze.client.desktop.maze.MazeTexture;

public class DesktopClientModule extends AbstractModule {
    @Override
    public void configure() {
        install(new LwjglConfig());
        install(new MazeConfig());
        install(new AssetConfig());
        bind(ApplicationListener.class).to(DesktopClientApplicationListener.class);
    }
}
