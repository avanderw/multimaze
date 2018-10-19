package net.avdw.multimaze.client.desktop.maze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Maze extends Sprite {
    @Inject
    Maze(@Named("maze-texture") Texture texture) {
        super(texture);
    }
}
