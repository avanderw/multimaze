package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.multimaze.client.desktop.maze.Maze;
import net.avdw.multimaze.client.desktop.player.Player;
import org.pmw.tinylog.Logger;

public class DesktopClientApplicationListener implements ApplicationListener {

    private Injector injector;
    private Stage stage;
    private SpriteBatch batch;
    private float elapsed;

    private Actor player;
    private Sprite maze;
    private Integer cellPadding;

    @Inject
    DesktopClientApplicationListener(Injector injector) {
        this.injector = injector;
    }

    public void create () {
        stage = injector.getInstance(Stage.class);
        maze = injector.getInstance(Maze.class);
        player = injector.getInstance(Player.class);

        cellPadding = injector.getInstance(Key.get(Integer.class, Names.named("maze-cell-padding")));
        player.setPosition(cellPadding, cellPadding);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(player);
//        Gdx.input.setInputProcessor(new InputAdapter(){
//            @Override
//            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                Vector3 unprojected = camera.unproject(new Vector3(screenX, screenY, 0));
//                if(player.getBoundingRectangle().contains(unprojected.x, unprojected.y))
//                    Logger.debug(String.format("%s Clicked", player));
//
//                return true;
//            }
//        });
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        maze.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
        batch.dispose();
        stage.dispose();
    }
}
