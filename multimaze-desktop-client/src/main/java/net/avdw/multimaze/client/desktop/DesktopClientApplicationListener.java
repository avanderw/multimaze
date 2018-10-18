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
import net.avdw.multimaze.client.desktop.maze.Maze;
import net.avdw.multimaze.client.desktop.player.Player;

public class DesktopClientApplicationListener implements ApplicationListener {

    private Injector injector;
    private SpriteBatch batch;
    private float elapsed;

    private Player player;
    private Maze maze;
    private Integer cellPadding;

    @Inject
    DesktopClientApplicationListener(Injector injector) {
        this.injector = injector;
    }

    public void create () {
        batch = new SpriteBatch();
        maze = injector.getInstance(Maze.class);
        player = injector.getInstance(Player.class);
        cellPadding = injector.getInstance(Key.get(Integer.class, Names.named("maze-cell-padding")));
    }

    public void resize (int width, int height) {
    }

    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(maze.texture, 0, 0);
        batch.draw(player.texture, cellPadding, cellPadding);
        batch.end();
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
        player.dispose();
        maze.dispose();
        batch.dispose();
    }
}
