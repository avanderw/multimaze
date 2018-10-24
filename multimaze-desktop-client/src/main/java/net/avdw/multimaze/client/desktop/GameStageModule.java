package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.multimaze.client.desktop.maze.MazeTextureGenerator;
import net.avdw.multimaze.client.desktop.player.Player;
import org.pmw.tinylog.Logger;

public class GameStageModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maze-row-count")).toInstance(32);
        bind(Integer.class).annotatedWith(Names.named("maze-col-count")).toInstance(32);
        bind(Integer.class).annotatedWith(Names.named("maze-cell-size")).toInstance(15);
        bind(Integer.class).annotatedWith(Names.named("maze-cell-padding")).toInstance(2);
    }

    @Provides
    @Named("maze-texture")
    Texture mazeTexture(MazeTextureGenerator mazeTextureGenerator) {
        return mazeTextureGenerator.mapMazeToTexture();
    }

    @Provides
    @Named("maze-image")
    Image mazeImage(@Named("maze-texture") Texture texture) {
        return new Image(texture);
    }

    @Provides
    Stage stage(Viewport viewport, @Named("maze-image") Image maze, Player player) {
        Logger.trace(String.format("viewport=%s, maze=%s, player=%s", viewport, maze, player));
        Stage stage = new Stage();
        stage.setViewport(viewport);
        stage.addActor(maze);
        stage.addActor(player);
        return stage;
    }
}
