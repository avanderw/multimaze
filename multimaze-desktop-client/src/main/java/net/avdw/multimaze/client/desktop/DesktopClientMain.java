package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.avdw.maze.generator.IMazeGenerator;
import org.apache.commons.lang3.StringUtils;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopClientMain {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new DesktopClientModule());
        injector.getInstance(IMazeGenerator.class).generate();
        new LwjglApplication(injector.getInstance(ApplicationListener.class), injector.getInstance(LwjglApplicationConfiguration.class));

//        for (int i=0; i < Math.pow(2, 4); i++) {
//            System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 4, "0"));
//        }

        //http://www.gamefromscratch.com/post/2018/09/27/Free-Game-Art-Asset-Packs.aspx
        //http://www.gamefromscratch.com/post/2018/10/02/MagicaVoxel-0992-Released.aspx
        //http://www.gamefromscratch.com/post/2018/10/03/A-Tale-Of-Two-Particle-Systems.aspx
        //https://github.com/libgdx/libgdx/wiki/Managing-your-assets

    }
}
