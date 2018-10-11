package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopClientMain {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "multimaze-desktop-client";
        config.useGL30 = Boolean.FALSE;
        config.width = 480;
        config.height = 320;

        new LwjglApplication(new DesktopClientApplicationListener(), config);

        //http://www.gamefromscratch.com/post/2018/09/27/Free-Game-Art-Asset-Packs.aspx
        //http://www.gamefromscratch.com/post/2018/10/02/MagicaVoxel-0992-Released.aspx
        //http://www.gamefromscratch.com/post/2018/10/03/A-Tale-Of-Two-Particle-Systems.aspx
        //https://github.com/libgdx/libgdx/wiki/Managing-your-assets

    }
}
