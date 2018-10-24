package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.pmw.tinylog.Logger;

public class Player extends Actor {
    private final Image image;

    @Inject
    Player(@Named("player-texture") Texture texture) {
        Logger.trace(String.format("texture=%s", texture));
        image = new Image(texture);
        setSize(texture.getWidth(), texture.getHeight());
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Logger.debug(String.format("%s, %s, %s, %s, %s", event, x, y, pointer, button));
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
    }
}
