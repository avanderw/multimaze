package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class DesktopClientApplicationListener implements ApplicationListener {

    private Injector injector;
    private SpriteBatch batch;
    private float elapsed;

    private Texture mazeTexture;

    @Inject
    DesktopClientApplicationListener(Injector injector) {
        this.injector = injector;
    }

    public void create () {
        batch = new SpriteBatch();
        mazeTexture = injector.getInstance(Key.get(Texture.class, Names.named("maze-texture")));
    }

    public void resize (int width, int height) {
    }

    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(mazeTexture, 0, 0);
        batch.end();
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
        mazeTexture.dispose();
        batch.dispose();
    }
}
