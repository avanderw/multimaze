package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PlayerTextureGenerator {
    private Integer playerSize;
    private Pixmap pixmapCache;

    @Inject
    PlayerTextureGenerator(@Named("player-size") Integer playerSize) {
        this.playerSize = playerSize;
        pregeneratePixmap();
    }

    public Texture generate() {
        return new Texture(pixmapCache);
    }

    private void pregeneratePixmap() {
        pixmapCache = new Pixmap(playerSize, playerSize, Pixmap.Format.RGBA4444);
        pixmapCache.drawCircle(playerSize / 2, playerSize / 2, playerSize);
    }

}
