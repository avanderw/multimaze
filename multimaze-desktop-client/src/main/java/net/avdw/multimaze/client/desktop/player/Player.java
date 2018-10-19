package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Player extends Sprite {
    @Inject
    Player(@Named("player-texture") Texture texture) {
        super(texture);
    }
}
