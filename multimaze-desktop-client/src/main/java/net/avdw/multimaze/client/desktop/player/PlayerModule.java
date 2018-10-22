package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.multimaze.client.desktop.input.*;

public class PlayerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("player-size")).toInstance(11);

        Multibinder<AAction> playerActions = Multibinder.newSetBinder(binder(), AAction.class);
        playerActions.addBinding().to(MoveUp.class);
        playerActions.addBinding().to(MoveDownInputProcessor.class);
        playerActions.addBinding().to(MoveLeft.class);
        playerActions.addBinding().to(MoveRight.class);
    }

    @Provides
    @Named("player-texture")
    Texture playerTexture(PlayerTextureGenerator playerTextureGenerator) {
        return playerTextureGenerator.generate();
    }
}
