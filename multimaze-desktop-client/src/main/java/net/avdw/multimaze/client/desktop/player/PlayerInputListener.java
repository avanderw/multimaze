package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import org.pmw.tinylog.Logger;

public class PlayerInputListener extends InputListener {
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Logger.debug(String.format("%s, %s, %s, %s, %s", event, x, y, pointer, button));
        return true;
    }
}
