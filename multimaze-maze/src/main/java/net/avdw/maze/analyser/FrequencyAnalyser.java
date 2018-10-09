package net.avdw.maze.analyser;

import org.apache.commons.math3.stat.Frequency;

import javax.inject.Inject;
import java.util.Set;

public class FrequencyAnalyser implements IMazeAnalyser {
    private Set<IMazeAnalyser> analyserSet;

    @Inject
    FrequencyAnalyser(Set<IMazeAnalyser> analyserSet) {
        this.analyserSet = analyserSet;
    }
    @Override
    public Frequency analyse() {
        Frequency frequency = new Frequency();
        analyserSet.stream().forEach(analyser->frequency.merge(analyser.analyse()));
        return frequency;
    }
}
