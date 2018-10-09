package net.avdw.code.analyser;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SpeedAnalyserTest {

    @Test
    public void testAnalyserAccuracySuccess() {
        SpeedAnalyser speedAnalyser = new SpeedAnalyser(30, ()->{
            try {
                TimeUnit.MILLISECONDS.sleep(30L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        StatisticalSummary summaryStatistics = speedAnalyser.analyse();
        assertEquals(30.0, summaryStatistics.getMean(), summaryStatistics.getStandardDeviation());
    }
}