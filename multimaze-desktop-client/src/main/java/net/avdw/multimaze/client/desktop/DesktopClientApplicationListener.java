package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import net.avdw.multimaze.client.desktop.pixelmap.MazePixmapFromKeyGenerator;

public class DesktopClientApplicationListener implements ApplicationListener {
    Texture texture;
    SpriteBatch batch;
    float elapsed;

    public void create () {
        texture = new Texture(new MazePixmapFromKeyGenerator().map(0b1011, 32));
        batch = new SpriteBatch();
    }

    public void resize (int width, int height) {
    }

    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 100+100*(float)Math.cos(elapsed), 100+25*(float)Math.sin(elapsed));
        batch.end();
    }

    public void pause () {
    }

    public void resume () {
    }

    public void dispose () {
        texture.dispose();
        batch.dispose();
    }
}
