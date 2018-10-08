package net.avdw.code.analyser;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

import java.util.stream.IntStream;

public class SpeedAnalyser implements ICodeAnalyser {
    private final Integer observations;
    private final Runnable runnable;

    public SpeedAnalyser(Integer observations, Runnable runnable) {
        this.observations = observations;
        this.runnable = runnable;
    }

    @Override
    public StatisticalSummary analyse() {
        DescriptiveStatistics statisticalSummary = new DescriptiveStatistics();
        IntStream.range(0, observations).forEach(observation->{
            Long start = System.currentTimeMillis();
            runnable.run();
            statisticalSummary.addValue(System.currentTimeMillis() - start);
        });

        return statisticalSummary;
    }
}
