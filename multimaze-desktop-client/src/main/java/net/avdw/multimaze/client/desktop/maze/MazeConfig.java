package net.avdw.multimaze.client.desktop.maze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.multimaze.client.desktop.maze.MazeTextureGenerator;
import net.avdw.multimaze.client.desktop.player.Player;
import org.pmw.tinylog.Logger;

public class MazeConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maze-row-count")).toInstance(32);
        bind(Integer.class).annotatedWith(Names.named("maze-col-count")).toInstance(32);
        bind(Integer.class).annotatedWith(Names.named("maze-cell-size")).toInstance(15);
        bind(Integer.class).annotatedWith(Names.named("maze-cell-padding")).toInstance(2);
    }

    @Provides @Singleton
    @Named("maze-texture")
    Texture mazeTexture(MazeTextureGenerator mazeTextureGenerator) {
        return mazeTextureGenerator.mapMazeToTexture();
    }

    @Provides
    @Named("maze-image")
    Image mazeImage(@Named("maze-texture") Texture texture) {
        return new Image(texture);
    }

}
