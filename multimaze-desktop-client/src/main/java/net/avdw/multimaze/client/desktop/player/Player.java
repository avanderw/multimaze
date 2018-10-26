package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.model.MazeCell;
import org.pmw.tinylog.Logger;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Random;

public class Player extends Actor {
    private final Sprite sprite;
    private Integer offset;
    private final IMaze maze;
    private final Random random;
    public MazeCell mazeCell;

    @Inject
    Player(@Named("player-texture") Texture texture, @Named("player-texture-offset") Integer offset, IMaze maze, @Named("player-random") Random random, @Named("maze-cell-size") Integer pixmapSize, PlayerInputListener inputListener) {
        Logger.trace(String.format("texture=%s, offset=%s, maze=%s, random=%s", texture, offset, maze, random));
        this.offset = offset;
        this.maze = maze;
        this.random = random;
        sprite = new Sprite(texture);

        setSize(texture.getWidth(), texture.getHeight());
        mazeCell = maze.cells().get(random.nextInt(maze.cells().size()));
        mazeCell = maze.cells().get(0);
//        setPosition(mazeCell.col * pixmapSize, (maze.rowCount() - mazeCell.row -1) * pixmapSize);
        setPosition(mazeCell.col * pixmapSize,  mazeCell.row * pixmapSize);

        addListener(inputListener);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        Logger.trace(String.format("x=%s, y=%s", x, y));
        super.setPosition(x + offset, y + offset);
        sprite.setPosition(x + offset, y + offset);
    }

    @Override
    public String toString() {
        return String.format("mazeCell=%s", mazeCell);
    }

}
