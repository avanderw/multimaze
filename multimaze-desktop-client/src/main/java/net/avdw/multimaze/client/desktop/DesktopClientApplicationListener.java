package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.avdw.multimaze.client.desktop.maze.MazeController;
import net.avdw.multimaze.client.desktop.maze.MazeTexture;

public class DesktopClientApplicationListener implements ApplicationListener {
    Texture mazeTexture;
    SpriteBatch batch;
    float elapsed;
    private MazeController mazeController;

    @Inject
    DesktopClientApplicationListener(MazeController mazeController) {
        this.mazeController = mazeController;
    }

    public void create () {
        batch = new SpriteBatch();
        mazeController.generateMaze();
        mazeController.mapMazeToTexture();
    }

    public void resize (int width, int height) {
    }

    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(mazeTexture, 100, 100);
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
