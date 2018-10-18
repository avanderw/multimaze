package net.avdw.multimaze.client.desktop.maze;

import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Maze {
    public Texture texture;

    @Inject
    Maze(@Named("maze-texture") Texture texture) {
        this.texture = texture;
    }

    public void dispose() {
        texture.dispose();
    }
}
