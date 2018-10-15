package net.avdw.multimaze.client.desktop.config;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.AbstractModule;

public class LwjglConfig extends AbstractModule {
    @Override
    public void configure() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "multimaze-desktop-client";
        config.useGL30 = Boolean.FALSE;
        config.width = 480;
        config.height = 320;

        bind(LwjglApplicationConfiguration.class).toInstance(config);
    }
}
