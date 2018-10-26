package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.avdw.maze.generator.IMazeGenerator;
import net.avdw.maze.generator.RecursiveBacktrackerGenerator;
import net.avdw.maze.model.GridMaze;
import net.avdw.maze.model.IMaze;
import net.avdw.multimaze.client.desktop.maze.MazeConfig;
import net.avdw.multimaze.client.desktop.player.Player;
import net.avdw.multimaze.client.desktop.player.PlayerConfig;
import org.pmw.tinylog.Logger;

public class MultimazeConfig extends AbstractModule {
    private static final Integer WIDTH = 640;
    private static final Integer HEIGHT= 480;
    @Override
    public void configure() {
        Logger.trace("");
        bind(ApplicationListener.class).to(MultimazeRenderer.class);
        bind(Viewport.class).to(ExtendViewport.class);
        bind(IMazeGenerator.class).to(RecursiveBacktrackerGenerator.class);
        bind(IMaze.class).to(GridMaze.class);

        install(new PlayerConfig());
        install(new MazeConfig());
    }

    @Provides
    LwjglApplicationConfiguration lwjglApplicationConfiguration() {
        Logger.trace("");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "multimaze-desktop-client";
        config.useGL30 = Boolean.FALSE;
        config.width = 640;
        config.height = 480;
        return config;
    }

    @Provides
    OrthographicCamera orthographicCamera() {
        Logger.trace("");
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(Boolean.FALSE, WIDTH, HEIGHT);
        return camera;
    }

    @Provides
    ExtendViewport viewport(OrthographicCamera camera) {
        Logger.trace("");
        return new ExtendViewport(WIDTH, HEIGHT, camera);
    }


    @Provides @Singleton
    Stage stage(Viewport viewport, @Named("maze-image") Image maze, Player player, @Named("player-move-indicator") Image validMoveIndicator) {
        Logger.trace(String.format("viewport=%s, maze=%s, player=%s", viewport, maze, player));
        Stage stage = new Stage();
        stage.setViewport(viewport);
        stage.addActor(maze);
        stage.addActor(player);
        stage.addActor(validMoveIndicator);
        return stage;
    }

}
