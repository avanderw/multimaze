package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.util.Random;

public class PlayerConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("player-texture-size")).toInstance(11);
        bind(Integer.class).annotatedWith(Names.named("player-texture-offset")).toInstance(2);
    }

    @Provides @Singleton @Named("player-random")
    Random random() {
        return new Random();
    }

    @Provides
    @Named("player-texture")
    Texture playerTexture(PlayerTextureGenerator playerTextureGenerator) {
        return playerTextureGenerator.generate();
    }

    @Provides
    @Named("player-move-indicator")
    Image playerMoveIndicator () {
        return new Image();
    }
}
