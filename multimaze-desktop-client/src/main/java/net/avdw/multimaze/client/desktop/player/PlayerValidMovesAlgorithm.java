package net.avdw.multimaze.client.desktop.player;

import com.google.inject.Inject;
import net.avdw.maze.model.Direction;
import net.avdw.maze.model.IMaze;
import net.avdw.maze.model.MazeCell;
import net.avdw.maze.model.WallState;
import org.pmw.tinylog.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlayerValidMovesAlgorithm {
    private final IMaze maze;

    @Inject
    PlayerValidMovesAlgorithm(IMaze maze) {
        this.maze = maze;
    }
    public Set<MazeCell> determineValidMovesForPlayer(Player player) {
        Logger.trace(String.format("%s", player));
        Set<MazeCell> validMoves = new HashSet<>();

        Optional<MazeCell> startingMazeCell = maze.cells().stream()
                .filter(c-> c.col.equals(player.mazeCell.col))
                .filter(c-> c.row.equals(player.mazeCell.row))
                .findAny();

        startingMazeCell.ifPresent(mazeCell -> {
            validMoves.add(mazeCell);
            validMoves.addAll(traverseMaze(Direction.NORTH, mazeCell));
            validMoves.addAll(traverseMaze(Direction.SOUTH, mazeCell));
            validMoves.addAll(traverseMaze(Direction.EAST, mazeCell));
            validMoves.addAll(traverseMaze(Direction.WEST, mazeCell));
        });


        return validMoves;
    }

    private Set<MazeCell> traverseMaze(Direction direction, MazeCell cell) {
        Set<MazeCell> validMoves = new HashSet<>();
        cell.wallStateMap.computeIfPresent(direction, (dir, wallState) -> {
            if (wallState == WallState.OPEN) {
                validMoves.add(cell.neighbourCellMap.get(direction));
                validMoves.addAll(traverseMaze(direction, cell.neighbourCellMap.get(direction)));
            }
            return wallState;
        });

        return validMoves;
    }
}
