package net.avdw.multimaze.client.desktop.pixelmap;

import com.badlogic.gdx.graphics.Pixmap;

public class MazePixmapFromKeyGenerator {
    public Pixmap map(Integer key, Integer size) {
        Integer sectionSize = size / 4;

        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA4444);
        pixmap.setFilter(Pixmap.Filter.NearestNeighbour);
        pixmap.setColor(0xFFFFFFFF);

        pixmap.fillRectangle(sectionSize, sectionSize, sectionSize, sectionSize);
        if ((key & 0b1000) == 0b1000) {
            pixmap.fillRectangle(sectionSize, 0, sectionSize, sectionSize);
        }
        if ((key & 0b0100) == 0b0100) {
            pixmap.fillRectangle(sectionSize, 2 * sectionSize, sectionSize, sectionSize);
        }
        if ((key & 0b0010) == 0b0010) {
            pixmap.fillRectangle(2 * sectionSize, sectionSize, sectionSize, sectionSize);
        }
        if ((key & 0b0001) == 0b0001) {
            pixmap.fillRectangle(0, sectionSize, sectionSize, sectionSize);
        }

        return pixmap;
    }
}
