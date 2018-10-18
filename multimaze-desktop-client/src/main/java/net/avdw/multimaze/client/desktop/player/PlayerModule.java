package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.multimaze.client.desktop.maze.MazeTextureMapper;

public class PlayerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("player-size")).toInstance(7);
    }

    @Provides
    @Named("player-texture")
    Texture playerTexture(PlayerTextureGenerator playerTextureGenerator) {
        return playerTextureGenerator.generate();
    }
}
