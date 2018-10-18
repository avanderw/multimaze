package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.avdw.maze.model.MazeCell;

public class Player {
    public Texture texture;
    public MazeCell mazeCell;

    @Inject
    Player(@Named("player-texture") Texture texture) {
        this.texture = texture;
    }

    public void dispose() {
        texture.dispose();
    }
}
