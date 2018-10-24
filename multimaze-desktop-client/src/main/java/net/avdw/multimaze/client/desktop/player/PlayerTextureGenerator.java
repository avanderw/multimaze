package net.avdw.multimaze.client.desktop.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.pmw.tinylog.Logger;

public class PlayerTextureGenerator {
    private Integer playerSize;
    private Pixmap pixmapCache;

    @Inject
    PlayerTextureGenerator(@Named("player-size") Integer playerSize) {
        this.playerSize = playerSize;
        pregeneratePixmap();
    }

    public Texture generate() {
        Logger.trace(this);
        return new Texture(pixmapCache);
    }

    private void pregeneratePixmap() {
        pixmapCache = new Pixmap(playerSize, playerSize, Pixmap.Format.RGBA4444);
        pixmapCache.setColor(Color.RED);
        pixmapCache.fillCircle(playerSize/2, playerSize/2, playerSize/2);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
