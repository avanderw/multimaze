package net.avdw.multimaze.client.desktop.maze;

import com.badlogic.gdx.graphics.Texture;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class MazeModule extends AbstractModule {
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
}
