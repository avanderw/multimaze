package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.inject.Inject;
import net.avdw.maze.model.MazeCell;
import org.pmw.tinylog.Logger;

import java.util.List;
import java.util.Set;

public class PlayerInputListener extends InputListener {
    private PlayerValidMovesAlgorithm playerValidMovesAlgorithm;

    @Inject
    PlayerInputListener(PlayerValidMovesAlgorithm playerValidMovesAlgorithm) {
        this.playerValidMovesAlgorithm = playerValidMovesAlgorithm;
    }
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Logger.trace(String.format("%s, %s, %s, %s, %s", event, x, y, pointer, button));
        Set<MazeCell> validMoves = playerValidMovesAlgorithm.determineValidMovesForPlayer((Player)event.getTarget());
        Logger.debug(String.format("%s valid moves", validMoves.size()));
        Logger.trace(validMoves);
        return true;
    }
}
