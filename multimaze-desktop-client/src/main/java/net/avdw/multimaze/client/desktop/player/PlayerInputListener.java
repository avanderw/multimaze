package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.avdw.maze.model.MazeCell;
import org.pmw.tinylog.Logger;

import java.util.Set;

public class PlayerInputListener extends InputListener {
    private PlayerValidMovesAlgorithm playerValidMovesAlgorithm;
    private Image playerMoveIndicator;

    @Inject
    PlayerInputListener(PlayerValidMovesAlgorithm playerValidMovesAlgorithm, @Named("player-move-indicator") Image playerMoveIndicator) {
        this.playerValidMovesAlgorithm = playerValidMovesAlgorithm;
        this.playerMoveIndicator = playerMoveIndicator;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Logger.trace(String.format("%s, %s, %s, %s, %s", event, x, y, pointer, button));
        Set<MazeCell> validMoves = playerValidMovesAlgorithm.determineValidMovesForPlayer((Player) event.getTarget());
        Logger.debug(String.format("%s valid moves", validMoves.size()));
        Logger.trace(validMoves);
        playerMoveIndicator.setVisible(Boolean.TRUE);
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Logger.trace(String.format("%s, %s, %s, %s, %s", event, x, y, pointer, button));
        playerMoveIndicator.setVisible(Boolean.FALSE);
    }
}
