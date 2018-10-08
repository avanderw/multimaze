package net.avdw.multimaze.desktop.launcher;

public class DesktopLauncher {
    public static void main(String[] args) {
        if (!serverFound) {
            startServer();
        }

        launchDesktopClient();
    }
}
