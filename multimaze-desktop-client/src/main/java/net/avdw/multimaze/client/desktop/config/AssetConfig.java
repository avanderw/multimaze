package net.avdw.multimaze.client.desktop.config;

import com.badlogic.gdx.graphics.Texture;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class AssetConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(Texture.class).annotatedWith(Names.named("maze-texture")).to(Texture.class);
    }
}
