package net.avdw.multimaze.client.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.avdw.maze.generator.IMazeGenerator;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;

public class DesktopClientMain {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        Injector injector = Guice.createInjector(new DesktopClientModule());
        injector.getInstance(IMazeGenerator.class).generate();
        new LwjglApplication(injector.getInstance(ApplicationListener.class), injector.getInstance(LwjglApplicationConfiguration.class));



        //http://www.gamefromscratch.com/post/2018/09/27/Free-Game-Art-Asset-Packs.aspx
        //http://www.gamefromscratch.com/post/2018/10/02/MagicaVoxel-0992-Released.aspx
        //http://www.gamefromscratch.com/post/2018/10/03/A-Tale-Of-Two-Particle-Systems.aspx
        //https://github.com/libgdx/libgdx/wiki/Managing-your-assets

    }
}
