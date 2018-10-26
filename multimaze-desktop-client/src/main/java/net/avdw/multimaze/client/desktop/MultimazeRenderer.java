package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.pmw.tinylog.Logger;

public class MultimazeRenderer implements ApplicationListener {

    private Injector injector;
    private Stage stage;
    private float elapsed;

    @Inject
    MultimazeRenderer(Injector injector) {
        this.injector = injector;
    }

    public void create () {
        Logger.trace("");
        stage = injector.getInstance(Stage.class);
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
        Gdx.input.setInputProcessor(stage);
    }

    public void resize (int width, int height) {
        Logger.trace(String.format("width=%s, height=%s", width, height));
        stage.getViewport().update(width, height, Boolean.TRUE);
    }

    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void pause () {
        Logger.trace("");
    }

    public void resume () {
        Logger.trace("");
    }

    public void dispose () {
        Logger.trace("");
        stage.dispose();
    }
}
