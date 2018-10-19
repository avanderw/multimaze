package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    private Sprite player;
    private Sprite maze;
    private Integer cellPadding;

    @Inject
    DesktopClientApplicationListener(Injector injector) {
        this.injector = injector;
    }

    public void create () {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(Boolean.TRUE, 640, 480);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        maze = injector.getInstance(Maze.class);
        player = injector.getInstance(Player.class);

        cellPadding = injector.getInstance(Key.get(Integer.class, Names.named("maze-cell-padding")));

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(player.getBoundingRectangle().contains(screenX, screenY))
                    System.out.println("Image Clicked");

                return true;
            }
        });
    }

    public void resize (int width, int height) {
    }

    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        maze.draw(batch);
        player.draw(batch);
        batch.end();
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
        batch.dispose();
    }
}
