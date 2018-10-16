package net.avdw.multimaze.client.desktop.maze;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.avdw.maze.model.Cell;
import net.avdw.maze.model.IMaze;

import java.util.Optional;
import java.util.stream.IntStream;

public class MazeTextureMapper {
    private final IMaze maze;
    private final Integer pixmapSize;

    @Inject
    MazeTextureMapper(IMaze maze, @Named("maze-cell-size") Integer pixmapSize) {
        this.maze = maze;
        this.pixmapSize = pixmapSize;
    }

    public Texture mapMazeToTexture() {
        Integer textureWidth = pixmapSize * maze.colCount();
        Integer textureHeight = pixmapSize * maze.rowCount();
        Texture texture = new Texture(textureWidth, textureHeight, Pixmap.Format.RGBA4444);

        IntStream.range(0, textureHeight).forEach(row ->
                IntStream.range(0, textureWidth).forEach(col -> {
                    Optional<Cell> cell = maze.cells().stream()
                            .filter(c -> c.row == row)
                            .filter(c -> c.col == col)
                            .findAny();
                    cell.ifPresent(c -> texture.draw(mapKeyToPixmap(c.key()), pixmapSize * col, pixmapSize * row));
                })
        );
        return texture;
    }

    private Pixmap mapKeyToPixmap(Integer key) {
        Integer sectionSize = pixmapSize / 3;

        Pixmap pixmap = new Pixmap(pixmapSize, pixmapSize, Pixmap.Format.RGBA4444);
        pixmap.setFilter(Pixmap.Filter.NearestNeighbour);
        pixmap.setColor(0xFFFFFFFF);

        pixmap.fillRectangle(sectionSize-2, sectionSize-2, sectionSize+4, sectionSize+4);
        if ((key & 0b1000) == 0b1000) {
            pixmap.fillRectangle(sectionSize-2, 0, sectionSize+4, sectionSize);
        }
        if ((key & 0b0100) == 0b0100) {
            pixmap.fillRectangle(sectionSize-2, 2 * sectionSize, sectionSize+4, sectionSize);
        }
        if ((key & 0b0010) == 0b0010) {
            pixmap.fillRectangle(2 * sectionSize, sectionSize-2, sectionSize, sectionSize+4);
        }
        if ((key & 0b0001) == 0b0001) {
            pixmap.fillRectangle(0, sectionSize-2, sectionSize, sectionSize+4);
        }

        return pixmap;
    }

}
