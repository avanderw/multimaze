package net.avdw.multimaze.desktop.launcher;

import net.avdw.multimaze.client.desktop.MultimazeMain;
import net.avdw.multimaze.server.MultimazeServer;

public class DesktopLauncher {
    public static void main(String[] args) {
        MultimazeServer.main(args);
        MultimazeMain.main(args);
    }
}
