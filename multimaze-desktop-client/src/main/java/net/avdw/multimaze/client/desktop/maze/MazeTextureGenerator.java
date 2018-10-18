package net.avdw.multimaze.client.desktop.maze;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.avdw.maze.model.MazeCell;
import net.avdw.maze.model.IMaze;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class MazeTextureGenerator {
    private final IMaze maze;
    private final Integer pixmapSize;
    private final Integer cellPadding;
    private final Map<Integer, Pixmap> pixmapCache = new HashMap<>();

    @Inject
    MazeTextureGenerator(IMaze maze, @Named("maze-cell-size") Integer pixmapSize, @Named("maze-cell-padding") Integer cellPadding) {
        this.maze = maze;
        this.pixmapSize = pixmapSize;
        this.cellPadding = cellPadding;

        pregeneratePixmaps();
    }

    public Texture mapMazeToTexture() {
        Integer textureWidth = pixmapSize * maze.colCount();
        Integer textureHeight = pixmapSize * maze.rowCount();
        Texture texture = new Texture(textureWidth, textureHeight, Pixmap.Format.RGBA4444);

        IntStream.range(0, textureHeight).forEach(row ->
                IntStream.range(0, textureWidth).forEach(col -> {
                    Optional<MazeCell> cell = maze.cells().stream()
                            .filter(c -> c.row == row)
                            .filter(c -> c.col == col)
                            .findAny();
                    cell.ifPresent(c -> texture.draw(pixmapCache.get(c.key()), pixmapSize * col, pixmapSize * row));
                })
        );
        return texture;
    }

    private void pregeneratePixmaps() {
        for (int i = 0; i < Math.pow(2, 4); i++) {
            pixmapCache.put(i, mapKeyToPixmap(i));
        }
    }

    private Pixmap mapKeyToPixmap(Integer key) {
        Integer sectionSize = pixmapSize / 3;
        Integer padding = (sectionSize - cellPadding);

        Pixmap pixmap = new Pixmap(pixmapSize, pixmapSize, Pixmap.Format.RGBA4444);
        pixmap.setFilter(Pixmap.Filter.NearestNeighbour);
        pixmap.setColor(0xFFFFFFFF);

        pixmap.fillRectangle(sectionSize - padding, sectionSize - padding, sectionSize + 2 * padding, sectionSize + 2 * padding);
        if ((key & 0b1000) == 0b1000) {
            pixmap.fillRectangle(sectionSize - padding, 0, sectionSize + 2 * padding, sectionSize);
        }
        if ((key & 0b0100) == 0b0100) {
            pixmap.fillRectangle(sectionSize - padding, 2 * sectionSize, sectionSize + 2 * padding, sectionSize);
        }
        if ((key & 0b0010) == 0b0010) {
            pixmap.fillRectangle(2 * sectionSize, sectionSize - padding, sectionSize, sectionSize + 2 * padding);
        }
        if ((key & 0b0001) == 0b0001) {
            pixmap.fillRectangle(0, sectionSize - padding, sectionSize, sectionSize + 2 * padding);
        }

        return pixmap;
    }

}
