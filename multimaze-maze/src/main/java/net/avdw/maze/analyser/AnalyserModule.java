package net.avdw.maze.analyser;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class AnalyserModule extends AbstractModule {
    @Override
    public void configure() {
        bind(IMazeAnalyser.class).to(FrequencyAnalyser.class);

        Multibinder<IMazeAnalyser> analyserBinder = Multibinder.newSetBinder(binder(), IMazeAnalyser.class);
        analyserBinder.addBinding().to(CrossroadAnalyser.class);
        analyserBinder.addBinding().to(DeadEndAnalyser.class);
        analyserBinder.addBinding().to(JunctionAnalyser.class);
        analyserBinder.addBinding().to(StraightawayAnalyser.class);
        analyserBinder.addBinding().to(TurnAnalyser.class);
    }
}
